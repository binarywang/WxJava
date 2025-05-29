package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ByteArrayResponseHandler extends AbstractResponseHandler<byte[]> {

  public static final ByteArrayResponseHandler INSTANCE = new ByteArrayResponseHandler();

  @Override
  public byte[] handleEntity(HttpEntity entity) throws IOException {
    return EntityUtils.toByteArray(entity);
  }
}
