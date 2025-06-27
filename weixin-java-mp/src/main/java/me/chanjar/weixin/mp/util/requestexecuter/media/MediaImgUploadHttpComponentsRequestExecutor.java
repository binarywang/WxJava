package me.chanjar.weixin.mp.util.requestexecuter.media;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.hc.Utf8ResponseHandler;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;

import java.io.File;
import java.io.IOException;

public class MediaImgUploadHttpComponentsRequestExecutor extends MediaImgUploadRequestExecutor<CloseableHttpClient, HttpHost> {
  public MediaImgUploadHttpComponentsRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
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
      .setMode(HttpMultipartMode.EXTENDED)
      .build();
    httpPost.setEntity(entity);

    String responseContent = requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
    WxError error = WxError.fromJson(responseContent, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return WxMediaImgUploadResult.fromJson(responseContent);
  }
}
