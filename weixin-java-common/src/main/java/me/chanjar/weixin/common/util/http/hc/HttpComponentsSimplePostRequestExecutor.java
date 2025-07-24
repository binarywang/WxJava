package me.chanjar.weixin.common.util.http.hc;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * ApacheSimplePostRequestExecutor
 *
 * @author altusea
 */
public class HttpComponentsSimplePostRequestExecutor extends SimplePostRequestExecutor<CloseableHttpClient, HttpHost> {

  public HttpComponentsSimplePostRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String postEntity, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }

    if (postEntity != null) {
      StringEntity entity = new StringEntity(postEntity, ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8));
      httpPost.setEntity(entity);
    }

    String responseContent = requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
    return this.handleResponse(wxType, responseContent);
  }

}
