package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonObject;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.http.net.SocketHttpConnectionProvider;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.impl.BaseWxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;

/**
 * The type Wx cp service jodd http.
 *
 * @author someone
 */
public class WxCpTpServiceJoddHttpImpl extends BaseWxCpTpServiceImpl<HttpConnectionProvider, ProxyInfo> {
  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

  @Override
  public HttpConnectionProvider getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public ProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.JODD_HTTP;
  }

  @Override
  public String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isSuiteAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getSuiteAccessToken();
    }

    synchronized (this.globalSuiteAccessTokenRefreshLock) {
      // 构建请求 URL
      String url = this.configStorage.getApiUrl(WxCpApiPathConsts.Tp.GET_SUITE_TOKEN);

      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("suite_id", this.configStorage.getSuiteId());
      jsonObject.addProperty("suite_secret", this.configStorage.getSuiteSecret());
      jsonObject.addProperty("suite_ticket", this.getSuiteTicket());
      String jsonBody = jsonObject.toString();

      if (this.httpProxy != null) {
        httpClient.useProxy(this.httpProxy);
      }
      // 创建 POST 请求
      HttpRequest request = HttpRequest
        .post(url)
        .contentType("application/json")
        .body(jsonBody);  // 使用 .body() 设置请求体

      request.withConnectionProvider(httpClient);

      // 发送请求
      HttpResponse response = request.send();

      // 解析响应
      String resultContent = response.bodyText();
      WxError error = WxError.fromJson(resultContent, WxType.CP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }

      // 更新 access token
      jsonObject = GsonParser.parse(resultContent);
      String suiteAccussToken = jsonObject.get("suite_access_token").getAsString();
      int expiresIn = jsonObject.get("expires_in").getAsInt();
      this.configStorage.updateSuiteAccessToken(suiteAccussToken, expiresIn);
    }

    return this.configStorage.getSuiteAccessToken();
  }

  @Override
  public void initHttp() {
    if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(),
        configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = new SocketHttpConnectionProvider();
  }
//
//  @Override
//  public WxCpConfigStorage getWxCpConfigStorage() {
//    return this.configStorage;
//  }
}
