package com.github.binarywang.wxpay.service.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.github.binarywang.wxpay.bean.brandprofitsharing.BrandProfitSharingReceiverDeleteRequest;
import com.github.binarywang.wxpay.bean.brandprofitsharing.BrandProfitSharingReceiverRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.auth.Signer;
import com.github.binarywang.wxpay.v3.auth.WxPayCredentials;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;

/** 验证实际 HTTP 执行器保留明文传输且日志不泄露收款信息。 */
public class BrandSensitiveLoggingTest {
  private static final String NAME = "测试敏感姓名";
  private static final String ACCOUNT = "sensitive-receiver-account";

  @Test
  public void shouldKeepReceiverBodyOutOfTransportLogs() throws Exception {
    for (WxPayService pay : new WxPayService[]{new WxPayServiceApacheHttpImpl(), new WxPayServiceHttpComponentsImpl()}) {
      for (int route = 0; route < 4; route++) {
        for (boolean failure : new boolean[]{false, true}) {
          final String[] sent = new String[1];
          CloseableHttpClient client = new CloseableHttpClient() {
            @Override
            protected CloseableHttpResponse doExecute(HttpHost host, HttpRequest request, HttpContext context) throws IOException {
              sent[0] = EntityUtils.toString(((HttpEntityEnclosingRequest) request).getEntity(), StandardCharsets.UTF_8);
              BasicHttpResponse response = new BasicHttpResponse(new ProtocolVersion("HTTP", 1, 1), failure ? 400 : 200, "test");
              response.setEntity(new StringEntity(failure
                ? "{\"code\":\"PARAM_ERROR\",\"message\":\"" + NAME + ACCOUNT + "\"}"
                : "{\"name\":\"" + NAME + "\",\"account\":\"" + ACCOUNT + "\"}", ContentType.APPLICATION_JSON));
              return (CloseableHttpResponse) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class<?>[]{CloseableHttpResponse.class}, (proxy, method, args) ->
                  method.getName().equals("close") ? null : method.invoke(response, args));
            }
            @Override public void close() { }
            @Override public org.apache.http.params.HttpParams getParams() { return null; }
            @Override public org.apache.http.conn.ClientConnectionManager getConnectionManager() { return null; }
          };
          WxPayConfig config = new WxPayConfig();
          config.setApiV3HttpClient(client);
          pay.setConfig(config);
          Logger logger = (Logger) LoggerFactory.getLogger(pay.getClass());
          ListAppender<ILoggingEvent> appender = new ListAppender<>();
          appender.start();
          logger.addAppender(appender);
          try {
            try {
              if (route == 0) pay.getBrandProfitSharingService().addReceiver(BrandProfitSharingReceiverRequest.newBuilder()
                .brandMchId("brand").appid("app").type("MERCHANT_ID").account(ACCOUNT).name(NAME).build());
              else if (route == 1) pay.postV3("https://example.com/pay/v3/brand/profitsharing/returnorders", "{\"name\":\"" + NAME + "\",\"account\":\"" + ACCOUNT + "\"}");
              else if (route == 2) pay.postV3WithWechatpaySerial("https://example.com/pay/v3/brand/profitsharing/orders", "{\"name\":\"" + NAME + "\",\"account\":\"" + ACCOUNT + "\"}");
              else pay.getBrandProfitSharingService().deleteReceiver(BrandProfitSharingReceiverDeleteRequest.newBuilder()
                .brandMchId("brand").appid("app").type("MERCHANT_ID").account(ACCOUNT).build());
              Assert.assertFalse(failure, "HTTP errors must propagate");
            } catch (WxPayException e) {
              Assert.assertTrue(failure);
            }
            if (route != 3) Assert.assertTrue(sent[0].contains(NAME), "Signing transport must receive unchanged plaintext name");
            Assert.assertTrue(sent[0].contains(ACCOUNT));
            Assert.assertFalse(appender.list.isEmpty(), "Capture actual executor logs");
            for (ILoggingEvent event : appender.list) {
              Assert.assertFalse(event.getFormattedMessage().contains(NAME), "Sensitive name leaked into logs");
              Assert.assertFalse(event.getFormattedMessage().contains(ACCOUNT), "Sensitive account leaked into logs");
            }
          } finally {
            logger.detachAppender(appender);
            appender.stop();
          }
        }
      }
    }
  }

  @Test
  public void shouldSignCompleteBodyWithoutDebugLoggingAuthorization() throws Exception {
    Logger logger = (Logger) LoggerFactory.getLogger(WxPayCredentials.class);
    Level previous = logger.getLevel();
    logger.setLevel(Level.DEBUG);
    ListAppender<ILoggingEvent> appender = new ListAppender<>();
    appender.start();
    logger.addAppender(appender);
    try {
      final String[] signed = new String[1];
      WxPayCredentials credentials = new WxPayCredentials("merchant", message -> {
        signed[0] = new String(message, StandardCharsets.UTF_8);
        return new Signer.SignatureResult("sensitive-signature", "serial");
      }) {
        @Override protected long generateTimestamp() { return 123; }
        @Override protected String generateNonceStr() { return "nonce"; }
      };
      HttpPost request = new HttpPost("https://example.com/v3/brand/profitsharing/receivers/add");
      String body = "{\"name\":\"" + NAME + "\",\"account\":\"" + ACCOUNT + "\"}";
      request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
      String token = credentials.getToken(HttpRequestWrapper.wrap(request));
      Assert.assertEquals(signed[0], "POST\n/v3/brand/profitsharing/receivers/add\n123\nnonce\n" + body + "\n");
      Assert.assertTrue(token.contains("signature=\"sensitive-signature\""));
      for (ILoggingEvent event : appender.list) {
        String message = event.getFormattedMessage();
        Assert.assertFalse(message.contains(NAME));
        Assert.assertFalse(message.contains(ACCOUNT));
        Assert.assertFalse(message.contains("sensitive-signature"));
        Assert.assertFalse(message.toLowerCase().contains("authorization"));
      }
    } finally {
      logger.detachAppender(appender);
      appender.stop();
      logger.setLevel(previous);
    }
  }
}
