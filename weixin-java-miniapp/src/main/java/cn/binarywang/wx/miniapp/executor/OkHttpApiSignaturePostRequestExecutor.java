package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkHttpApiSignaturePostRequestExecutor
    extends ApiSignaturePostRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private static final Logger logger =
      LoggerFactory.getLogger(OkHttpApiSignaturePostRequestExecutor.class);

  public OkHttpApiSignaturePostRequestExecutor(RequestHttp<?, ?> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMaApiResponse execute(
      String uri, Map<String, String> headers, String postEntity, WxType wxType)
      throws WxErrorException, IOException {
    //    logger.debug(
    //        "OkHttpApiSignaturePostRequestExecutor.execute uri:{}, headers:{}, postData:{}",
    //        uri,
    //        headers,
    //        postEntity);
    RequestBody body =
        RequestBody.Companion.create(
            postEntity, MediaType.parse("application/json; charset=utf-8"));
    Request.Builder builder = new Request.Builder();
    if (headers != null) {
      headers.forEach(builder::addHeader);
    }
    Request request = builder.url(uri).post(body).build();
    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    Map<String, String> respHeaders = new HashMap<>();
    Headers rHeaders = response.headers();
    for (String n : rHeaders.names()) {
      respHeaders.put(n, rHeaders.get(n));
    }
    return this.handleResponse(
        wxType, Objects.requireNonNull(response.body()).string(), respHeaders);
  }
}
