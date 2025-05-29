package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodSingleFileUploadResult;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public class JoddHttpVodSingleUploadRequestExecutor extends VodSingleUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public JoddHttpVodSingleUploadRequestExecutor(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) {
    super(requestHttp, mediaName, mediaType, coverType, coverData, sourceContext);
  }

  @Override
  public WxMaVodSingleFileUploadResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    request.form("media_data", file);
    request.form("media_name", mediaName);
    request.form("media_type", mediaType);
    if (coverType != null) {
      request.form("cover_type", coverType);
    }
    if (coverData != null) {
      request.form("cover_data", coverData);
    }
    if (sourceContext != null) {
      request.form("source_context", sourceContext);
    }


    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());

    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMaVodSingleFileUploadResult.fromJson(responseContent);
  }
}
