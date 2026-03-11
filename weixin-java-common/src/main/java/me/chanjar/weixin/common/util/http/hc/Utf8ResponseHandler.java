package me.chanjar.weixin.common.util.http.hc;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.impl.classic.AbstractHttpClientResponseHandler;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Utf8ResponseHandler
 *
 * @author altusea
 */
public class Utf8ResponseHandler extends AbstractHttpClientResponseHandler<String> {

  public static final HttpClientResponseHandler<String> INSTANCE = new Utf8ResponseHandler();

  @Override
  public String handleEntity(HttpEntity entity) throws IOException {
    try {
      return EntityUtils.toString(entity, StandardCharsets.UTF_8);
    } catch (final ParseException ex) {
      throw new ClientProtocolException(ex);
    }
  }
}
