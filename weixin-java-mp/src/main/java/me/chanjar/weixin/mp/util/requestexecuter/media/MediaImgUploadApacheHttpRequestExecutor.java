package me.chanjar.weixin.mp.util.requestexecuter.media;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * Created by ecoolper on 2017/5/5.
 *
 * @author ecoolper
 */
public class MediaImgUploadApacheHttpRequestExecutor extends MediaImgUploadRequestExecutor<CloseableHttpClient, HttpHost> {
  public MediaImgUploadApacheHttpRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaImgUploadResult execute(String uri, File data, WxType wxType) throws WxErrorException, IOException {
    if (data == null) {
      throw new WxErrorException("文件对象为空");
    }

    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }

    HttpEntity entity = MultipartEntityBuilder
      .create()
      .addBinaryBody("media", data)
      .setMode(HttpMultipartMode.RFC6532)
      .build();
    httpPost.setEntity(entity);

    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent, WxType.MP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }

      return WxMediaImgUploadResult.fromJson(responseContent);
    } finally {
      httpPost.releaseConnection();
    }
  }
}
