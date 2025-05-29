package me.chanjar.weixin.mp.util.requestexecuter.material;

import java.io.IOException;

import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import okhttp3.OkHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

public abstract class MaterialNewsInfoRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialNews, String> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialNewsInfoRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<WxMpMaterialNews> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<WxMpMaterialNews, String> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MaterialNewsInfoApacheHttpRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp);
      case JODD_HTTP:
        return new MaterialNewsInfoJoddHttpRequestExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp);
      case OK_HTTP:
        return new MaterialNewsInfoOkhttpRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp);
      default:
        //TODO 需要优化抛出异常
        return null;
    }
  }

}
