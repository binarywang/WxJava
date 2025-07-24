package me.chanjar.weixin.common.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheSimplePostRequestExecutor;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsSimplePostRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpSimplePostRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpSimplePostRequestExecutor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 简单的POST请求执行器，请求的参数是String, 返回的结果也是String
 *
 * @author Daniel Qian
 */
public abstract class SimplePostRequestExecutor<H, P> implements RequestExecutor<String, String> {
  protected RequestHttp<H, P> requestHttp;

  public SimplePostRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<String> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<String, String> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheSimplePostRequestExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp);
      case JODD_HTTP:
        return new JoddHttpSimplePostRequestExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp);
      case OK_HTTP:
        return new OkHttpSimplePostRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp);
      case HTTP_COMPONENTS:
        return new HttpComponentsSimplePostRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }

  @NotNull
  public String handleResponse(WxType wxType, String responseContent) throws WxErrorException {
    if (responseContent.isEmpty()) {
      throw new WxErrorException("无响应内容");
    }

    if (responseContent.startsWith("<xml>")) {
      //xml格式输出直接返回
      return responseContent;
    }

    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }
}
