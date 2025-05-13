package me.chanjar.weixin.mp.util.requestexecuter.material;

import com.google.common.collect.ImmutableMap;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class MaterialVoiceAndImageDownloadOkhttpRequestExecutor extends MaterialVoiceAndImageDownloadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public MaterialVoiceAndImageDownloadOkhttpRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public InputStream execute(String uri, String materialId, WxType wxType) throws WxErrorException, IOException {
    logger.debug("MaterialVoiceAndImageDownloadOkhttpRequestExecutor is running");
    OkHttpClient client = requestHttp.getRequestHttpClient();

    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
      WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId)));
    Request request = new Request.Builder().url(uri).get().post(requestBody).build();
    Response response = client.newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if ("text/plain".equals(contentTypeHeader) || "application/json; charset=utf-8".equals(contentTypeHeader)
      || "application/json; encoding=utf-8".equals(contentTypeHeader)) {
      String responseContent = response.body().string();
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
    }
    byte[] responseContent = IOUtils.toByteArray(response.body().source().inputStream());
    return new ByteArrayInputStream(responseContent);
  }
}
