package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ApacheApiSignaturePostRequestExecutor extends ApiSignaturePostRequestExecutor<CloseableHttpClient, HttpHost> {

  public ApacheApiSignaturePostRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMaApiResponse execute(
      String uri, Map<String, String> headers, String postEntity, WxType wxType)
      throws WxErrorException, IOException {
    //    logger.debug(
    //        "ApacheApiSignaturePostRequestExecutor.execute uri:{}, headers:{}, postData:{}",
    //        uri,
    //        headers,
    //        postEntity);
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config =
          RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }

    if (headers != null) {
      headers.forEach(httpPost::addHeader);
    }

    if (postEntity != null) {
      StringEntity entity = new StringEntity(postEntity, ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8));
      httpPost.setEntity(entity);
    }

    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      Map<String, String> respHeaders = new HashMap<>();
      Header[] rHeaders = response.getAllHeaders();
      if (rHeaders != null) {
        for (Header h : rHeaders) {
          respHeaders.putIfAbsent(h.getName(), h.getValue());
        }
      }
      return this.handleResponse(wxType, responseContent, respHeaders);
    }
  }
}
