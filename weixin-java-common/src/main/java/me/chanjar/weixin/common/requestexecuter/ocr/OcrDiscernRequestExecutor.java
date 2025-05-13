package me.chanjar.weixin.common.requestexecuter.ocr;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * .
 *
 * @author zhayueran
 * created on  2019/6/27 15:06
 */
public abstract class OcrDiscernRequestExecutor<H, P> implements RequestExecutor<String, File> {
  protected RequestHttp<H, P> requestHttp;

  public OcrDiscernRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<String> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<String, File> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new OcrDiscernApacheHttpRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp);
      default:
        return null;
    }
  }
}
