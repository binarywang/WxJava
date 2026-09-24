package me.chanjar.weixin.open.api.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import org.mockito.ArgumentCaptor;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class WxOpenCode2SessionSecurityTest {
  private static final String SECRET = "FAKE_COMPONENT_SECRET";
  private static final String TOKEN = "FAKE_COMPONENT_TOKEN";
  private static final String SESSION = "FAKE_SESSION_KEY";
  private static final String RESULT = "{\"openid\":\"FAKE_OPENID\",\"session_key\":\"" + SESSION + "\"}";

  @Test
  public void encodesParametersAndKeepsGetExtensionAndNullBehavior() throws Exception {
    try (LogCapture logs = new LogCapture()) {
      TestService service = service();
      WxOpenComponentService component = service.getWxOpenComponentService();
      for (String code : new String[]{"NORMAL_CODE", "a b\n", "a+&=#?%2F", "中文\uD83D\uDE00", "", null}) {
        reset(service.executor);
        when(service.executor.execute(anyString(), isNull(), eq(WxType.Open))).thenReturn(RESULT);
        assertEquals(component.miniappJscode2Session("appid+&=", code).getSessionKey(), SESSION);
        ArgumentCaptor<String> uri = ArgumentCaptor.forClass(String.class);
        verify(service.executor).execute(uri.capture(), isNull(), eq(WxType.Open));
        Map<String, String> query = decode(URI.create(uri.getValue()).getRawQuery());
        assertEquals(query.size(), 5);
        assertEquals(query.get("appid"), "appid+&=");
        assertEquals(query.get("js_code"), String.valueOf(code));
        assertEquals(query.get("component_appid"), "component+&=");
        assertEquals(query.get("component_access_token"), TOKEN);
      }
      logs.assertSafe();
    }
  }

  @Test
  public void refreshesTokenWithoutLoggingLoginOrCredentialPayloads() throws Exception {
    try (LogCapture logs = new LogCapture()) {
      TestService service = service();
      when(service.executor.execute(anyString(), nullable(String.class), eq(WxType.Open)))
        .thenThrow(error(40001))
        .thenReturn("{\"component_access_token\":\"" + TOKEN + "\",\"expires_in\":7200}")
        .thenReturn(RESULT);
      assertEquals(service.getWxOpenComponentService().miniappJscode2Session("appid", "FAKE_CODE")
        .getSessionKey(), SESSION);
      verify(service.executor).execute(eq(WxOpenComponentService.API_COMPONENT_TOKEN_URL),
        contains(SECRET), eq(WxType.Open));
      logs.assertSafe();
    }
  }

  @Test
  public void protectsWechatTransportAndResponseParsingFailures() throws Exception {
    try (LogCapture logs = new LogCapture()) {
      TestService service = service();
      for (Exception failure : new Exception[]{error(40029), new IOException(SECRET + TOKEN),
        new IllegalArgumentException(SECRET + TOKEN), new IllegalStateException(SECRET + TOKEN)}) {
        reset(service.executor);
        when(service.executor.execute(anyString(), isNull(), eq(WxType.Open))).thenThrow(failure);
        try {
          service.getWxOpenComponentService().miniappJscode2Session("appid", "FAKE_CODE");
          fail("Expected failure");
        } catch (WxErrorException e) {
          assertEquals(e.getError().getErrorCode(), 40029);
          assertNull(e.getError().getJson());
          assertNull(e.getError().getErrorMsgEn());
          assertSafe(e);
        } catch (RuntimeException e) {
          assertSafe(e);
        }
      }
      reset(service.executor);
      when(service.executor.execute(anyString(), isNull(), eq(WxType.Open)))
        .thenReturn("{\"session_key\":\"" + SESSION + "\",\"expires_in\":");
      try {
        service.getWxOpenComponentService().miniappJscode2Session("appid", "FAKE_CODE");
        fail("Expected malformed response failure");
      } catch (WxRuntimeException e) {
        assertSafe(e);
      }
      logs.assertSafe();
    }
  }

  @Test
  public void firstTokenRequestFailureIsProtected() throws Exception {
    try (LogCapture logs = new LogCapture()) {
      TestService service = service();
      service.getWxOpenConfigStorage().expireComponentAccessToken();
      when(service.executor.execute(anyString(), anyString(), eq(WxType.Open)))
        .thenThrow(new IOException(SECRET + TOKEN));
      try {
        service.getWxOpenComponentService().miniappJscode2Session("appid", "FAKE_CODE");
        fail("Expected token failure");
      } catch (WxRuntimeException e) {
        assertSafe(e);
      }
      logs.assertSafe();
    }
  }

  @Test
  public void sensitiveEndpointMatchingDoesNotChangeOtherRequests() throws Exception {
    assertTrue(WxOpenServiceAbstractImpl.isSensitiveRequest(WxOpenComponentService.API_COMPONENT_TOKEN_URL));
    assertTrue(WxOpenServiceAbstractImpl.isSensitiveRequest(
      "https://api.weixin.qq.com/sns/component/jscode2session?appid=x"));
    assertFalse(WxOpenServiceAbstractImpl.isSensitiveRequest(
      "https://api.weixin.qq.com/sns/component/jscode2session-other?appid=x"));
    TestService service = service();
    IllegalArgumentException original = new IllegalArgumentException("ordinary failure");
    when(service.executor.execute(anyString(), eq("value=a%2Fb"), eq(WxType.Open))).thenThrow(original);
    try {
      service.get("https://api.weixin.qq.com/ordinary", "value=a%2Fb");
      fail("Expected failure");
    } catch (IllegalArgumentException e) {
      assertSame(e, original);
    }
  }

  private TestService service() {
    TestService service = new TestService();
    WxOpenInMemoryConfigStorage config = new WxOpenInMemoryConfigStorage();
    config.setComponentAppId("component+&=");
    config.setComponentAppSecret(SECRET);
    config.setComponentVerifyTicket("FAKE_TICKET");
    config.updateComponentAccessToken(TOKEN, 7200);
    service.setWxOpenConfigStorage(config);
    return service;
  }

  private WxErrorException error(int code) {
    return new WxErrorException(WxError.builder().errorCode(code).errorMsg(SECRET + TOKEN)
      .errorMsgEn(SECRET).json(SESSION).build(), new RuntimeException(SECRET));
  }

  private Map<String, String> decode(String query) throws Exception {
    Map<String, String> result = new HashMap<>();
    for (String pair : query.split("&")) {
      String[] parts = pair.split("=", 2);
      result.put(parts[0], URLDecoder.decode(parts[1], "UTF-8"));
    }
    return result;
  }

  private void assertSafe(Throwable e) {
    StringWriter trace = new StringWriter();
    e.printStackTrace(new PrintWriter(trace));
    assertNoSecrets(trace.toString());
    assertNull(e.getCause());
    assertEquals(e.getSuppressed().length, 0);
  }

  private static void assertNoSecrets(String text) {
    for (String secret : new String[]{SECRET, TOKEN, SESSION, "FAKE_CODE", "FAKE_OPENID", "FAKE_TICKET"}) {
      assertFalse(text.contains(secret), "Credentials must not appear in diagnostics");
    }
  }

  private static class LogCapture implements AutoCloseable {
    private final Logger[] loggers = {(Logger) LoggerFactory.getLogger(WxOpenServiceAbstractImpl.class),
      (Logger) LoggerFactory.getLogger(WxOpenComponentServiceImpl.class)};
    private final Level[] previous = new Level[loggers.length];
    private final ListAppender<ILoggingEvent> appender = new ListAppender<>();

    LogCapture() {
      appender.start();
      for (int i = 0; i < loggers.length; i++) {
        previous[i] = loggers[i].getLevel();
        loggers[i].setLevel(Level.DEBUG);
        loggers[i].addAppender(appender);
      }
    }

    void assertSafe() {
      assertFalse(appender.list.isEmpty());
      for (ILoggingEvent event : appender.list) {
        assertNoSecrets(event.getFormattedMessage());
      }
    }

    @Override
    public void close() {
      for (int i = 0; i < loggers.length; i++) {
        loggers[i].detachAppender(appender);
        loggers[i].setLevel(previous[i]);
      }
      appender.stop();
    }
  }

  private static class TestService extends WxOpenServiceImpl {
    @SuppressWarnings("unchecked")
    private final RequestExecutor<String, String> executor = mock(RequestExecutor.class);

    @Override
    public void initHttp() {
      // Keep the inherited component/token flow with a controlled transport.
    }

    @Override
    public String get(String uri, String data) throws WxErrorException {
      return super.execute(executor, uri, data);
    }

    @Override
    public String post(String uri, String data) throws WxErrorException {
      return super.execute(executor, uri, data);
    }
  }
}
