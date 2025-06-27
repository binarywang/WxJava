package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodSingleFileUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class ApacheVodSingleUploadRequestExecutor extends VodSingleUploadRequestExecutor<CloseableHttpClient, HttpHost> {

  public ApacheVodSingleUploadRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) {
    super(requestHttp, mediaName, mediaType, coverType, coverData, sourceContext);
  }

  @Override
  public WxMaVodSingleFileUploadResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (file != null) {
      MultipartEntityBuilder entityBuilder = MultipartEntityBuilder
        .create()
        .setMode(HttpMultipartMode.RFC6532)
        .addTextBody("media_name", mediaName)
        .addTextBody("media_type", mediaType)
        .addBinaryBody("media_data", file);

      if (coverType != null) {
        entityBuilder.addTextBody("cover_type", coverType);
      }
      if (coverData != null) {
        entityBuilder.addBinaryBody("cover_data", coverData);
      }
      if (sourceContext != null) {
        entityBuilder.addTextBody("source_context", sourceContext);
      }

      httpPost.setEntity(entityBuilder.build());
    }
    String responseContent = requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMaVodSingleFileUploadResult.fromJson(responseContent);
  }
}
