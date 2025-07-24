package com.github.binarywang.wxpay.v3.auth;


import com.github.binarywang.wxpay.v3.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author spvycf & F00lish
 */
@Slf4j
public class WxPayValidator implements Validator {
  private final Verifier verifier;

  public WxPayValidator(Verifier verifier) {
    this.verifier = verifier;
  }

  @Override
  public final boolean validate(CloseableHttpResponse response) throws IOException {
    if (!ContentType.APPLICATION_JSON.getMimeType().equals(ContentType.parse(String.valueOf(response.getFirstHeader(
      "Content-Type").getValue())).getMimeType())) {
      return true;
    }
    Header serialNo = response.getFirstHeader("Wechatpay-Serial");
    Header sign = response.getFirstHeader("Wechatpay-Signature");
    Header timestamp = response.getFirstHeader("Wechatpay-TimeStamp");
    Header nonce = response.getFirstHeader("Wechatpay-Nonce");

    // todo: check timestamp
    if (timestamp == null || nonce == null || serialNo == null || sign == null) {
      return false;
    }

    String message = buildMessage(response);
    return verifier.verify(serialNo.getValue(), message.getBytes(StandardCharsets.UTF_8), sign.getValue());
  }

  protected final String buildMessage(CloseableHttpResponse response) throws IOException {
    String timestamp = response.getFirstHeader("Wechatpay-TimeStamp").getValue();
    String nonce = response.getFirstHeader("Wechatpay-Nonce").getValue();

    String body = getResponseBody(response);
    return timestamp + "\n"
      + nonce + "\n"
      + body + "\n";
  }

  protected final String getResponseBody(CloseableHttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();

    return (entity != null && entity.isRepeatable()) ? EntityUtils.toString(entity) : "";
  }
}
