package me.chanjar.weixin.common.util.http.hc;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.InputStreamData;
import me.chanjar.weixin.common.util.http.MediaInputStreamUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;

import java.io.IOException;

/**
 * 文件输入流上传.
 *
 * @author altusea
 */
public class HttpComponentsMediaInputStreamUploadRequestExecutor extends MediaInputStreamUploadRequestExecutor<CloseableHttpClient, HttpHost> {

  public HttpComponentsMediaInputStreamUploadRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaUploadResult execute(String uri, InputStreamData data, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (data != null) {
      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addBinaryBody("media", data.getInputStream(), ContentType.DEFAULT_BINARY, data.getFilename())
        .setMode(HttpMultipartMode.EXTENDED)
        .build();
      httpPost.setEntity(entity);
    }
    String responseContent = requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMediaUploadResult.fromJson(responseContent);
  }
}
