package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class QrcodeRequestExecutor<H, P> implements RequestExecutor<File, AbstractWxMaQrcodeWrapper> {
  protected RequestHttp<H, P> requestHttp;

  protected QrcodeRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, AbstractWxMaQrcodeWrapper data, ResponseHandler<File> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<File, AbstractWxMaQrcodeWrapper> create(RequestHttp<?, ?> requestHttp, String path) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrcodeFileRequestExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp, path);
      case OK_HTTP:
        return new OkHttpQrcodeFileRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp, path);
      case HTTP_COMPONENTS:
        return new HttpComponentsQrcodeFileRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp, path);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<File, AbstractWxMaQrcodeWrapper> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrcodeFileRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp, null);
      case OK_HTTP:
        return new OkHttpQrcodeFileRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp, null);
      case HTTP_COMPONENTS:
        return new HttpComponentsQrcodeFileRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp, null);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }
}
