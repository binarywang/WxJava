package me.chanjar.weixin.mp.util.requestexecuter.media;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by ecoolper on 2017/5/5.
 *
 * @author ecoolper
 */
public class MediaImgUploadHttpRequestExecutor extends MediaImgUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public MediaImgUploadHttpRequestExecutor(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaImgUploadResult execute(String uri, File data, WxType wxType) throws WxErrorException, IOException {
    if (data == null) {
      throw new WxErrorException("文件对象为空");
    }

    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    request.form("media", data);
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return WxMediaImgUploadResult.fromJson(responseContent);
  }
}
