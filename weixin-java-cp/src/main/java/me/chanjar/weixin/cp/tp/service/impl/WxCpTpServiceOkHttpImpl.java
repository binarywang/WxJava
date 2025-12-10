package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.okhttp.DefaultOkHttpClientBuilder;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.impl.BaseWxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import okhttp3.*;

import java.io.IOException;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.GET_TOKEN;

/**
 * The type Wx cp service ok http.
 *
 * @author someone
 */
@Slf4j
public class WxCpTpServiceOkHttpImpl extends BaseWxCpTpServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.OK_HTTP;
  }

  @Override
  public String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isSuiteAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getSuiteAccessToken();
    }

    synchronized (this.globalSuiteAccessTokenRefreshLock) {
      // 得到 httpClient
      OkHttpClient client = getRequestHttpClient();

      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("suite_id", this.configStorage.getSuiteId());
      jsonObject.addProperty("suite_secret", this.configStorage.getSuiteSecret());
      jsonObject.addProperty("suite_ticket", this.getSuiteTicket());
      String jsonBody = jsonObject.toString();

      RequestBody requestBody = RequestBody.create(
        MediaType.get("application/json; charset=utf-8"),
        jsonBody
      );

      // 构建 POST 请求
      Request request = new Request.Builder()
        .url(this.configStorage.getApiUrl(WxCpApiPathConsts.Tp.GET_SUITE_TOKEN)) // URL 不包含查询参数
        .post(requestBody) // 使用 POST 方法
        .build();

      String resultContent = null;
      try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) {
          throw new IOException("Unexpected response code: " + response);
        }
        resultContent = response.body().string();
      } catch (IOException e) {
        log.error("获取 suite token 失败: {}", e.getMessage(), e);
        throw new WxRuntimeException("获取 suite token 失败", e);
      }

      WxError error = WxError.fromJson(resultContent, WxType.CP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }

      jsonObject = GsonParser.parse(resultContent);
      String suiteAccussToken = jsonObject.get("suite_access_token").getAsString();
      int expiresIn = jsonObject.get("expires_in").getAsInt();
      this.configStorage.updateSuiteAccessToken(suiteAccussToken, expiresIn);
    }
    return this.configStorage.getSuiteAccessToken();
  }

  @Override
  public void initHttp() {
    log.debug("WxCpServiceOkHttpImpl initHttp");
    //设置代理
    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(configStorage.getHttpProxyHost(),
        configStorage.getHttpProxyPort(),
        configStorage.getHttpProxyUsername(),
        configStorage.getHttpProxyPassword());
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
      clientBuilder.proxy(getRequestHttpProxy().getProxy());
      //设置授权
      clientBuilder.proxyAuthenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Proxy-Authorization", credential)
            .build();
        }
      });
      httpClient = clientBuilder.build();
    } else {
      httpClient = DefaultOkHttpClientBuilder.get().build();
    }
  }

//  @Override
//  public WxCpConfigStorage getWxCpConfigStorage() {
//    return this.configStorage;
//  }
}
