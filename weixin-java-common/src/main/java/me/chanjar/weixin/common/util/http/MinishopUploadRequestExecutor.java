package me.chanjar.weixin.common.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheMinishopMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsMinishopMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpMinishopMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpMinishopMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;

import java.io.File;
import java.io.IOException;

public abstract class MinishopUploadRequestExecutor<H, P> implements RequestExecutor<WxMinishopImageUploadResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MinishopUploadRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMinishopImageUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<WxMinishopImageUploadResult, File> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMinishopMediaUploadRequestExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp);
      case JODD_HTTP:
        return new JoddHttpMinishopMediaUploadRequestExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp);
      case OK_HTTP:
        return new OkHttpMinishopMediaUploadRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp);
      case HTTP_COMPONENTS:
        return new HttpComponentsMinishopMediaUploadRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }
}
