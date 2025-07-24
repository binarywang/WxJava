package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.hc.BasicResponseHandler;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpHost;

import java.io.IOException;

/**
 * The type Wx cp service apache http client.
 *
 * @author altusea
 */
public class WxCpServiceHttpComponentsImpl extends BaseWxCpServiceImpl<CloseableHttpClient, HttpHost> {

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
    return HttpClientType.HTTP_COMPONENTS;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getAccessToken();
    }

    synchronized (this.globalAccessTokenRefreshLock) {
      String url = String.format(this.configStorage.getApiUrl(WxCpApiPathConsts.GET_TOKEN),
        this.configStorage.getCorpId(), this.configStorage.getCorpSecret());

      try {
        HttpGet httpGet = new HttpGet(url);
        if (this.httpProxy != null) {
          RequestConfig config = RequestConfig.custom()
            .setProxy(this.httpProxy).build();
          httpGet.setConfig(config);
        }
        String resultContent = getRequestHttpClient().execute(httpGet, BasicResponseHandler.INSTANCE);
        WxError error = WxError.fromJson(resultContent, WxType.CP);
        if (error.getErrorCode() != 0) {
          throw new WxErrorException(error);
        }

        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        this.configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
      } catch (IOException e) {
        throw new WxRuntimeException(e);
      }
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public void initHttp() {
    HttpComponentsClientBuilder apacheHttpClientBuilder = DefaultHttpComponentsClientBuilder.get();

    apacheHttpClientBuilder.httpProxyHost(this.configStorage.getHttpProxyHost())
      .httpProxyPort(this.configStorage.getHttpProxyPort())
      .httpProxyUsername(this.configStorage.getHttpProxyUsername())
      .httpProxyPassword(this.configStorage.getHttpProxyPassword().toCharArray());

    if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(this.configStorage.getHttpProxyHost(), this.configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }

}
