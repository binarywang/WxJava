package com.binarywang.wxjava.store.executor;

import java.io.File;
import java.io.IOException;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/** OkHttp implementation for 微信小店 file uploads. */
public class OkHttpStoreFileUploadRequestExecutor extends StoreFileUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  public OkHttpStoreFileUploadRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
      .addFormDataPart("media", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
      .build();
    try (Response response = requestHttp.getRequestHttpClient().newCall(new Request.Builder().url(uri).post(body).build()).execute()) {
      return response.body().string();
    }
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<String> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(execute(uri, data, wxType));
  }
}
