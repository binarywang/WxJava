package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.AbstractResponseHandler;

import java.io.IOException;
import java.io.InputStream;

/**
 * 输入流响应处理器.
 *
 * @author altusea
 */
public class InputStreamResponseHandler extends AbstractResponseHandler<InputStream> {

  public static final ResponseHandler<InputStream> INSTANCE = new InputStreamResponseHandler();

  @Override
  public InputStream handleEntity(HttpEntity entity) throws IOException {
    return entity.getContent();
  }
}
