package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaStableAccessTokenRequest;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
public class WxMaServiceHttpClientImpl extends BaseWxMaServiceImpl {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public void initHttp() {
    WxMaConfig configStorage = this.getWxMaConfig();
    ApacheHttpClientBuilder apacheHttpClientBuilder = configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.APACHE_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {

    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getAccessTokenUrl()) ?
      this.getWxMaConfig().getAccessTokenUrl() : StringUtils.isNotEmpty(this.getWxMaConfig().getApiHostUrl()) ?
      WxMaService.GET_ACCESS_TOKEN_URL.replace("https://api.weixin.qq.com", this.getWxMaConfig().getApiHostUrl()) :
      WxMaService.GET_ACCESS_TOKEN_URL;


    url = String.format(url, this.getWxMaConfig().getAppid(), this.getWxMaConfig().getSecret());

    HttpGet httpGet = null;
    CloseableHttpResponse response = null;
    try {
      httpGet = new HttpGet(url);
      if (this.getRequestHttpProxy() != null) {
        RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
        httpGet.setConfig(config);
      }
      response = getRequestHttpClient().execute(httpGet);
      return new BasicResponseHandler().handleResponse(response);
    } finally {
      if (httpGet != null) {
        httpGet.releaseConnection();
      }
      IOUtils.closeQuietly(response);
    }
  }

  @Override
  protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getAccessTokenUrl()) ?
      this.getWxMaConfig().getAccessTokenUrl() : StringUtils.isNotEmpty(this.getWxMaConfig().getApiHostUrl()) ?
      GET_STABLE_ACCESS_TOKEN.replace("https://api.weixin.qq.com", this.getWxMaConfig().getApiHostUrl()) :
      GET_STABLE_ACCESS_TOKEN;

    HttpPost httpPost = null;
    CloseableHttpResponse response = null;
    try {
      httpPost = new HttpPost(url);
      if (this.getRequestHttpProxy() != null) {
        RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
        httpPost.setConfig(config);
      }
      WxMaStableAccessTokenRequest wxMaAccessTokenRequest = new WxMaStableAccessTokenRequest();
      wxMaAccessTokenRequest.setAppid(this.getWxMaConfig().getAppid());
      wxMaAccessTokenRequest.setSecret(this.getWxMaConfig().getSecret());
      wxMaAccessTokenRequest.setGrantType("client_credential");
      wxMaAccessTokenRequest.setForceRefresh(forceRefresh);
      httpPost.setEntity(new StringEntity(wxMaAccessTokenRequest.toJson(), ContentType.APPLICATION_JSON));
      response = getRequestHttpClient().execute(httpPost);
      return new BasicResponseHandler().handleResponse(response);
    } finally {
      if (httpPost != null) {
        httpPost.releaseConnection();
      }
      IOUtils.closeQuietly(response);
    }
  }

}
