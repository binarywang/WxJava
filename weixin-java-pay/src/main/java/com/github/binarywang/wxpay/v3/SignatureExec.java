package com.github.binarywang.wxpay.v3;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class SignatureExec implements ClientExecChain {
  final ClientExecChain mainExec;
  final Credentials credentials;
  final Validator validator;
  /**
   * 额外受信任的主机列表，这些主机（如反向代理）也需要携带微信支付 Authorization 头
   */
  final Set<String> trustedHosts;

  SignatureExec(Credentials credentials, Validator validator, ClientExecChain mainExec) {
    this(credentials, validator, mainExec, Collections.emptySet());
  }

  SignatureExec(Credentials credentials, Validator validator, ClientExecChain mainExec, Set<String> trustedHosts) {
    this.credentials = credentials;
    this.validator = validator;
    this.mainExec = mainExec;
    this.trustedHosts = trustedHosts != null ? trustedHosts : Collections.emptySet();
  }

  protected HttpEntity newRepeatableEntity(HttpEntity entity) throws IOException {
    byte[] content = EntityUtils.toByteArray(entity);
    ByteArrayEntity newEntity = new ByteArrayEntity(content);
    newEntity.setContentEncoding(entity.getContentEncoding());
    newEntity.setContentType(entity.getContentType());

    return newEntity;
  }

  protected void convertToRepeatableResponseEntity(CloseableHttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();
    if (entity != null && !entity.isRepeatable()) {
      response.setEntity(newRepeatableEntity(entity));
    }
  }

  protected void convertToRepeatableRequestEntity(HttpRequestWrapper request) throws IOException {
    if (request instanceof HttpEntityEnclosingRequest) {
      HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
      if (entity != null) {
        ((HttpEntityEnclosingRequest) request).setEntity(new BufferedHttpEntity(entity));
      }
    }
  }

  @Override
  public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request,
                                       HttpClientContext context, HttpExecutionAware execAware)
    throws IOException, HttpException {
    String host = request.getURI().getHost();
    if (host != null && (host.endsWith(".mch.weixin.qq.com") || trustedHosts.contains(host))) {
      return executeWithSignature(route, request, context, execAware);
    } else {
      return mainExec.execute(route, request, context, execAware);
    }
  }

  private CloseableHttpResponse executeWithSignature(HttpRoute route, HttpRequestWrapper request,
                                                     HttpClientContext context, HttpExecutionAware execAware)
    throws IOException, HttpException {
    // 上传类不需要消耗两次故不做转换
    if (!(request.getOriginal() instanceof WechatPayUploadHttpPost)) {
      convertToRepeatableRequestEntity(request);
    }
    // 添加认证信息
    request.addHeader("Authorization",
      credentials.getSchema() + " " + credentials.getToken(request));

    // 执行
    CloseableHttpResponse response = mainExec.execute(route, request, context, execAware);

    // 对成功应答验签
    StatusLine statusLine = response.getStatusLine();
    if (statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 300) {
      convertToRepeatableResponseEntity(response);
      if (!(request.getOriginal() instanceof WxPayV3DownloadHttpGet)) {
        if (!validator.validate(response)) {
          throw new HttpException("应答的微信支付签名验证失败");
        }
      }
    }
    return response;
  }

}
