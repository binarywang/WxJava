package me.chanjar.weixin.common.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadCustomizeResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheMinishopMediaUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsMinishopMediaUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpMinishopMediaUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpMinishopMediaUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;

import java.io.File;
import java.io.IOException;

public abstract class MinishopUploadRequestCustomizeExecutor<H, P> implements RequestExecutor<WxMinishopImageUploadCustomizeResult, File> {
  protected RequestHttp<H, P> requestHttp;
  protected String respType;
  protected String uploadType;
  protected String imgUrl;

  public MinishopUploadRequestCustomizeExecutor(RequestHttp<H, P> requestHttp, String respType, String imgUrl) {
    this.requestHttp = requestHttp;
    this.respType = respType;
    if (imgUrl == null || imgUrl.isEmpty()) {
      this.uploadType = "0";
    } else {
      this.uploadType = "1";
      this.imgUrl = imgUrl;
    }
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMinishopImageUploadCustomizeResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<WxMinishopImageUploadCustomizeResult, File> create(RequestHttp<?, ?> requestHttp, String respType, String imgUrl) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMinishopMediaUploadRequestCustomizeExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp, respType, imgUrl);
      case JODD_HTTP:
        return new JoddHttpMinishopMediaUploadRequestCustomizeExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp, respType, imgUrl);
      case OK_HTTP:
        return new OkHttpMinishopMediaUploadRequestCustomizeExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp, respType, imgUrl);
      case HTTP_COMPONENTS:
        return new HttpComponentsMinishopMediaUploadRequestCustomizeExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp, respType, imgUrl);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }
}
