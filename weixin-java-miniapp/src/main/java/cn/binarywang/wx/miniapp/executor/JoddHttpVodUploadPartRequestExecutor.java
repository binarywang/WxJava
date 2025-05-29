package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodUploadPartResult;
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
public class JoddHttpVodUploadPartRequestExecutor extends VodUploadPartRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public JoddHttpVodUploadPartRequestExecutor(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp, String uploadId, Integer partNumber, Integer resourceType) {
    super(requestHttp, uploadId, partNumber, resourceType);

  }

  @Override
  public WxMaVodUploadPartResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    request.form("data", file);
    request.form("upload_id", uploadId);
    request.form("part_number", partNumber);
    request.form("resource_type", resourceType);

    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());

    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMaVodUploadPartResult.fromJson(responseContent);
  }
}
