package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.exception.WxPayException;
import me.chanjar.weixin.common.util.http.apache.ByteArrayResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <pre>
 * 微信支付请求实现类，apache httpclient实现.
 * Created by Binary Wang on 2016/7/28.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayServiceApacheHttpImpl extends AbstractWxPayApacheHttpServiceImpl {

  @Override
  public byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpPost httpPost = this.createHttpPost(url, requestStr);
      CloseableHttpClient httpClient = this.createHttpClient(useKey);

      // 使用连接池的客户端，不需要手动关闭
      final byte[] bytes = httpClient.execute(httpPost, ByteArrayResponseHandler.INSTANCE);
      final String responseData = Base64.getEncoder().encodeToString(bytes);
      this.logRequestAndResponse(url, requestStr, responseData);
      wxApiData.set(new WxPayApiData(url, requestStr, responseData, null));
      return bytes;
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String post(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpPost httpPost = this.createHttpPost(url, requestStr);
      CloseableHttpClient httpClient = this.createHttpClient(useKey);

      // 使用连接池的客户端，不需要手动关闭
      try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
        String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        this.logRequestAndResponse(url, requestStr, responseString);
        if (this.getConfig().isIfSaveApiData()) {
          wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
        }
        return responseString;
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      }
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String post(String url, String requestStr, boolean useKey, String mimeType) throws WxPayException {
    try {
      HttpPost httpPost = this.createHttpPost(url, requestStr, mimeType);
      CloseableHttpClient httpClient = this.createHttpClient(useKey);

      // 使用连接池的客户端，不需要手动关闭
      try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
        String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        this.logRequestAndResponse(url, requestStr, responseString);
        if (this.getConfig().isIfSaveApiData()) {
          wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
        }
        return responseString;
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      if (this.getConfig().isIfSaveApiData()) {
        wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      }
      throw new WxPayException(e.getMessage(), e);
    }
  }

  CloseableHttpClient createHttpClient(boolean useKey) throws WxPayException {
    if (useKey) {
      // 使用SSL连接池客户端
      CloseableHttpClient sslHttpClient = this.getConfig().getSslHttpClient();
      if (null == sslHttpClient) {
        this.getConfig().initSslHttpClient();
        sslHttpClient = this.getConfig().getSslHttpClient();
      }
      return sslHttpClient;
    } else {
      // 使用普通连接池客户端
      CloseableHttpClient httpClient = this.getConfig().getHttpClient();
      if (null == httpClient) {
        this.getConfig().initHttpClient();
        httpClient = this.getConfig().getHttpClient();
      }
      return httpClient;
    }
  }
}
