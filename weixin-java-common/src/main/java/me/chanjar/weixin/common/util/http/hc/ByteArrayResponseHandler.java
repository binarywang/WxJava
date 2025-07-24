package me.chanjar.weixin.common.util.http.hc;

import org.apache.hc.client5.http.impl.classic.AbstractHttpClientResponseHandler;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

/**
 * ByteArrayResponseHandler
 *
 * @author altusea
 */
public class ByteArrayResponseHandler extends AbstractHttpClientResponseHandler<byte[]> {

  public static final ByteArrayResponseHandler INSTANCE = new ByteArrayResponseHandler();

  @Override
  public byte[] handleEntity(HttpEntity entity) throws IOException {
    return EntityUtils.toByteArray(entity);
  }
}
