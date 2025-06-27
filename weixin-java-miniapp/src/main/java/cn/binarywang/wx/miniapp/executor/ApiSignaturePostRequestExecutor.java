package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

public abstract class ApiSignaturePostRequestExecutor<H, P>
    implements RequestExecutor<WxMaApiResponse, WxMaApiResponse> {

  protected RequestHttp<H, P> requestHttp;

  public ApiSignaturePostRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public WxMaApiResponse execute(String uri, WxMaApiResponse data, WxType wxType)
      throws WxErrorException, IOException {
    throw new RemoteException("method not implemented yet.");
  }

  @Override
  public void execute(
      String uri, WxMaApiResponse data, ResponseHandler<WxMaApiResponse> handler, WxType wxType)
      throws WxErrorException, IOException {
    throw new RemoteException("method not implemented yet.");
  }

  public abstract WxMaApiResponse execute(
      String uri, Map<String, String> headers, String data, WxType wxType)
      throws WxErrorException, IOException;

  @NotNull
  public WxMaApiResponse handleResponse(
      WxType wxType, String responseContent, Map<String, String> headers) throws WxErrorException {
    if (responseContent.isEmpty()) {
      throw new WxErrorException("无响应内容");
    }
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    WxMaApiResponse response = new WxMaApiResponse();
    response.setContent(responseContent);
    response.setHeaders(headers);
    return response;
  }

  @SuppressWarnings("unchecked")
  public static ApiSignaturePostRequestExecutor<?, ?> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheApiSignaturePostRequestExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp);
      case JODD_HTTP:
        return new JoddApiSignaturePostRequestExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp);
      case OK_HTTP:
        return new OkHttpApiSignaturePostRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp);
      case HTTP_COMPONENTS:
        return new HttpComponentsApiSignaturePostRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }
}
