package me.chanjar.weixin.common.util.http.hc;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadCustomizeResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MinishopUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;

import java.io.File;
import java.io.IOException;

/**
 * ApacheMinishopMediaUploadRequestCustomizeExecutor
 *
 * @author altusea
 */
@Slf4j
public class HttpComponentsMinishopMediaUploadRequestCustomizeExecutor extends MinishopUploadRequestCustomizeExecutor<CloseableHttpClient, HttpHost> {

  public HttpComponentsMinishopMediaUploadRequestCustomizeExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, String respType, String imgUrl) {
    super(requestHttp, respType, imgUrl);
  }

  @Override
  public WxMinishopImageUploadCustomizeResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (this.uploadType.equals("0")) {
      if (file == null) {
        throw new WxErrorException("上传文件为空");
      }
      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addBinaryBody("media", file)
        .addTextBody("resp_type", this.respType)
        .addTextBody("upload_type", this.uploadType)
        .setMode(HttpMultipartMode.EXTENDED)
        .build();
      httpPost.setEntity(entity);
    }
    else {
      HttpEntity entity = MultipartEntityBuilder
              .create()
              .addTextBody("resp_type", this.respType)
              .addTextBody("upload_type", this.uploadType)
              .addTextBody("img_url", this.imgUrl)
              .setMode(org.apache.hc.client5.http.entity.mime.HttpMultipartMode.EXTENDED)
              .build();
      httpPost.setEntity(entity);
    }
    String responseContent = requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    log.info("responseContent: {}", responseContent);
    return WxMinishopImageUploadCustomizeResult.fromJson(responseContent);
  }
}
