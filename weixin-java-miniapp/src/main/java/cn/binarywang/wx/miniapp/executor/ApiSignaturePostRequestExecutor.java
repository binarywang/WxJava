package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import org.jetbrains.annotations.NotNull;

public abstract class ApiSignaturePostRequestExecutor<H, P>
    implements RequestExecutor<WxMaApiResponse, WxMaApiResponse> {

  protected RequestHttp<H, P> requestHttp;

  public ApiSignaturePostRequestExecutor(RequestHttp requestHttp) {
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

  public static ApiSignaturePostRequestExecutor create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheApiSignaturePostRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddApiSignaturePostRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpApiSignaturePostRequestExecutor(requestHttp);
      default:
        throw new IllegalArgumentException("非法请求参数");
    }
  }
}
