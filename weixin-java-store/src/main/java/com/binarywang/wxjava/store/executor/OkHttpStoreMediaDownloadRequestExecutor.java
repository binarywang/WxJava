package com.binarywang.wxjava.store.executor;

import com.binarywang.wxjava.store.bean.image.StoreImageResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/** OkHttp implementation for 微信小店 image downloads. */
public class OkHttpStoreMediaDownloadRequestExecutor extends StoreMediaDownloadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  public OkHttpStoreMediaDownloadRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public StoreImageResponse execute(String uri, String data, WxType wxType) throws WxErrorException, IOException {
    if (data != null) {
      uri += (uri.contains("?") ? "&" : "?") + data;
    }
    try (Response response = requestHttp.getRequestHttpClient().newCall(new Request.Builder().url(uri).get().build()).execute()) {
      String contentType = response.header("Content-Type");
      if (contentType != null && contentType.startsWith("application/json")) {
        return JsonUtils.decode(response.body().string(), StoreImageResponse.class);
      }
      String fileName = extractFileNameFromContentString(response.header("Content-disposition"));
      String baseName = FilenameUtils.getBaseName(fileName);
      if (StringUtils.isBlank(baseName) || baseName.length() < 3) {
        baseName = String.valueOf(System.currentTimeMillis());
      }
      String extension = FilenameUtils.getExtension(fileName);
      if (StringUtils.isBlank(extension)) {
        extension = "unknown";
      }
      try (InputStream inputStream = response.body().byteStream()) {
        return new StoreImageResponse(createTmpFile(inputStream, baseName, extension, tmpDirFile), contentType);
      }
    }
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<StoreImageResponse> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(execute(uri, data, wxType));
  }
}
