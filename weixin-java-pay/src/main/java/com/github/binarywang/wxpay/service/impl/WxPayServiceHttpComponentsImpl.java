package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.exception.WxPayException;
import me.chanjar.weixin.common.util.http.apache.ByteArrayResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 微信支付请求实现类，apache httpconponents 实现.
 *
 * @author altusea
 */
public class WxPayServiceHttpComponentsImpl extends AbstractWxPayApacheHttpServiceImpl {

  @Override
  public byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpClientBuilder httpClientBuilder = createHttpClientBuilder(useKey);
      HttpPost httpPost = this.createHttpPost(url, requestStr);
      try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
        final byte[] bytes = httpClient.execute(httpPost, ByteArrayResponseHandler.INSTANCE);
        final String responseData = Base64.getEncoder().encodeToString(bytes);
        this.logRequestAndResponse(url, requestStr, responseData);
        wxApiData.set(new WxPayApiData(url, requestStr, responseData, null));
        return bytes;
      }
    } catch (Exception e) {
      this.logError(url, requestStr, e);
      wxApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
      throw new WxPayException(e.getMessage(), e);
    }
  }

  @Override
  public String post(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpClientBuilder httpClientBuilder = this.createHttpClientBuilder(useKey);
      HttpPost httpPost = this.createHttpPost(url, requestStr);
      try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
          String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
          this.logRequestAndResponse(url, requestStr, responseString);
          if (this.getConfig().isIfSaveApiData()) {
            wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
          }
          return responseString;
        }
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
      HttpClientBuilder httpClientBuilder = this.createHttpClientBuilder(useKey);
      HttpPost httpPost = this.createHttpPost(url, requestStr, mimeType);
      try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
          String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
          this.logRequestAndResponse(url, requestStr, responseString);
          if (this.getConfig().isIfSaveApiData()) {
            wxApiData.set(new WxPayApiData(url, requestStr, responseString, null));
          }
          return responseString;
        }
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
}
