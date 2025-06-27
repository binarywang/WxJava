package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.hc.Utf8ResponseHandler;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpComponentsApiSignaturePostRequestExecutor extends ApiSignaturePostRequestExecutor<CloseableHttpClient, HttpHost> {

  public HttpComponentsApiSignaturePostRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMaApiResponse execute(
    String uri, Map<String, String> headers, String postEntity, WxType wxType)
    throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
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
      Header[] rHeaders = response.getHeaders();
      if (rHeaders != null) {
        for (Header h : rHeaders) {
          respHeaders.putIfAbsent(h.getName(), h.getValue());
        }
      }
      return this.handleResponse(wxType, responseContent, respHeaders);
    } catch (HttpException httpException) {
      throw new ClientProtocolException(httpException.getMessage(), httpException);
    }
  }
}
