package me.chanjar.weixin.mp.util.requestexecuter.voice;

import java.io.File;
import java.io.IOException;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/9.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class VoiceUploadRequestExecutor<H, P> implements RequestExecutor<Boolean, File> {
  protected RequestHttp<H, P> requestHttp;

  public VoiceUploadRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<Boolean> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<Boolean, File> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new VoiceUploadApacheHttpRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp);
      case JODD_HTTP:
      case OK_HTTP:
      default:
        return null;
    }
  }

}
