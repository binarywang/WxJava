package me.chanjar.weixin.common.util.http.jodd;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MinishopUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * .
 *
 * @author ecoolper
 * created on  2017/5/5
 */
@Slf4j
public class JoddHttpMinishopMediaUploadRequestExecutor extends MinishopUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public JoddHttpMinishopMediaUploadRequestExecutor(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMinishopImageUploadResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    request.form("media", file);
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());

    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    log.info("responseContent: {}", responseContent);

    return WxMinishopImageUploadResult.fromJson(responseContent);
  }
}
