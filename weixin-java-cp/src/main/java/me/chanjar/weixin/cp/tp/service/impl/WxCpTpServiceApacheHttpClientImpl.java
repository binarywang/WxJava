package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.apache.ApacheBasicResponseHandler;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The type Wx cp tp service apache http client.
 *
 * @author someone
 */
public class WxCpTpServiceApacheHttpClientImpl extends BaseWxCpTpServiceImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpClientType getRequestType() {
    return HttpClientType.APACHE_HTTP;
  }

  @Override
  public String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isSuiteAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getSuiteAccessToken();
    }

    synchronized (this.globalSuiteAccessTokenRefreshLock) {
      try {
        HttpPost httpPost = new HttpPost(configStorage.getApiUrl(WxCpApiPathConsts.Tp.GET_SUITE_TOKEN));
        if (this.httpProxy != null) {
          RequestConfig config = RequestConfig.custom()
            .setProxy(this.httpProxy).build();
          httpPost.setConfig(config);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("suite_id", this.configStorage.getSuiteId());
        jsonObject.addProperty("suite_secret", this.configStorage.getSuiteSecret());
        jsonObject.addProperty("suite_ticket", this.getSuiteTicket());
        StringEntity entity = new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);
        httpPost.setEntity(entity);

        String resultContent = getRequestHttpClient().execute(httpPost, ApacheBasicResponseHandler.INSTANCE);
        WxError error = WxError.fromJson(resultContent, WxType.CP);
        if (error.getErrorCode() != 0) {
          throw new WxErrorException(error);
        }
        jsonObject = GsonParser.parse(resultContent);
        String suiteAccussToken = jsonObject.get("suite_access_token").getAsString();
        int expiresIn = jsonObject.get("expires_in").getAsInt();
        this.configStorage.updateSuiteAccessToken(suiteAccussToken, expiresIn);
      } catch (IOException e) {
        throw new WxRuntimeException(e);
      }
    }
    return this.configStorage.getSuiteAccessToken();
  }

  @Override
  public void initHttp() {
    ApacheHttpClientBuilder apacheHttpClientBuilder = this.configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(this.configStorage.getHttpProxyHost())
      .httpProxyPort(this.configStorage.getHttpProxyPort())
      .httpProxyUsername(this.configStorage.getHttpProxyUsername())
      .httpProxyPassword(this.configStorage.getHttpProxyPassword());

    if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(this.configStorage.getHttpProxyHost(), this.configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public WxCpTpConfigStorage getWxCpTpConfigStorage() {
    return this.configStorage;
  }

}
