package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodUploadPartResult;
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
public class OkHttpVodUploadPartRequestExecutor extends VodUploadPartRequestExecutor<OkHttpClient, OkHttpProxyInfo> {

  public OkHttpVodUploadPartRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp, String uploadId, Integer partNumber, Integer resourceType) {
    super(requestHttp, uploadId, partNumber, resourceType);

  }

  @Override
  public WxMaVodUploadPartResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {

    MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("data",
        file.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file));
    bodyBuilder.addFormDataPart("upload_id", uploadId)
      .addFormDataPart("part_number", String.valueOf(this.partNumber))
      .addFormDataPart("resource_type", String.valueOf(this.resourceType));

    Request request = new Request.Builder().url(uri).post(bodyBuilder.build()).build();

    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMaVodUploadPartResult.fromJson(responseContent);
  }

}
