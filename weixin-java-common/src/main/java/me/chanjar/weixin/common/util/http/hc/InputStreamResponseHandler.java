package me.chanjar.weixin.common.util.http.hc;

import org.apache.hc.client5.http.impl.classic.AbstractHttpClientResponseHandler;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStreamResponseHandler
 *
 * @author altusea
 */
public class InputStreamResponseHandler extends AbstractHttpClientResponseHandler<InputStream> {

  public static final HttpClientResponseHandler<InputStream> INSTANCE = new InputStreamResponseHandler();

  @Override
  public InputStream handleEntity(HttpEntity entity) throws IOException {
    return entity.getContent();
  }
}
