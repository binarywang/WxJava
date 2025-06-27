package me.chanjar.weixin.common.util.http.apache;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * .
 *
 * @author ecoolper
 * created on  2017/5/4
 */
public class ApacheSimpleGetRequestExecutor extends SimpleGetRequestExecutor<CloseableHttpClient, HttpHost> {
  public ApacheSimpleGetRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String queryParam, WxType wxType) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }
    HttpGet httpGet = new HttpGet(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpGet.setConfig(config);
    }

    String responseContent = requestHttp.getRequestHttpClient().execute(httpGet, Utf8ResponseHandler.INSTANCE);
    return handleResponse(wxType, responseContent);
  }

}
