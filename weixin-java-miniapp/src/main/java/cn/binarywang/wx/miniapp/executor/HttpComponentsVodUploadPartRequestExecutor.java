package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodUploadPartResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.hc.Utf8ResponseHandler;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpHost;

import java.io.File;
import java.io.IOException;

public class HttpComponentsVodUploadPartRequestExecutor extends VodUploadPartRequestExecutor<CloseableHttpClient, HttpHost> {

  public HttpComponentsVodUploadPartRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, String uploadId, Integer partNumber, Integer resourceType) {
    super(requestHttp, uploadId, partNumber, resourceType);

  }

  @Override
  public WxMaVodUploadPartResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (file != null) {
      MultipartEntityBuilder entityBuilder = MultipartEntityBuilder
        .create()
        .setMode(HttpMultipartMode.EXTENDED)
        .addTextBody("upload_id", uploadId)
        .addTextBody("part_number", String.valueOf(partNumber))
        .addTextBody("resource_type", String.valueOf(resourceType))
        .addBinaryBody("data", file);

      httpPost.setEntity(entityBuilder.build());
    }

    String responseContent = requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMaVodUploadPartResult.fromJson(responseContent);
  }
}
