package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Utf8ResponseHandler
 *
 * @author altusea
 */
public class Utf8ResponseHandler extends AbstractResponseHandler<String> {

  public static final ResponseHandler<String> INSTANCE = new Utf8ResponseHandler();

  @Override
  public String handleEntity(HttpEntity entity) throws IOException {
    return EntityUtils.toString(entity, StandardCharsets.UTF_8);
  }
}
