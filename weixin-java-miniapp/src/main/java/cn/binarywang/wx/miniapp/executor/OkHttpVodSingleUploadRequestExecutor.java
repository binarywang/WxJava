package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodSingleFileUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class OkHttpVodSingleUploadRequestExecutor extends VodSingleUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {

  public OkHttpVodSingleUploadRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) {
    super(requestHttp, mediaName, mediaType, coverType, coverData, sourceContext);
  }

  @Override
  public WxMaVodSingleFileUploadResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {

    MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("media_data",
        file.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file));
    bodyBuilder.addFormDataPart("media_type", this.mediaType)
      .addFormDataPart("media_name", this.mediaName);

    if (coverType != null) {
      bodyBuilder.addFormDataPart("cover_type", this.coverType);
    }
    if (coverData != null) {
      bodyBuilder.addFormDataPart("cover_data",
        coverData.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), coverData));
    }
    if (sourceContext != null) {
      bodyBuilder.addFormDataPart("source_context", this.sourceContext);
    }
    Request request = new Request.Builder().url(uri).post(bodyBuilder.build()).build();

    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMaVodSingleFileUploadResult.fromJson(responseContent);
  }

}
