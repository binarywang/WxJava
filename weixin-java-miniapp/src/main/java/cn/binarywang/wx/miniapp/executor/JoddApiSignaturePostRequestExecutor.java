package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoddApiSignaturePostRequestExecutor
    extends ApiSignaturePostRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  private static final Logger logger =
      LoggerFactory.getLogger(JoddApiSignaturePostRequestExecutor.class);

  public JoddApiSignaturePostRequestExecutor(RequestHttp<?, ?> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMaApiResponse execute(
      String uri, Map<String, String> headers, String postEntity, WxType wxType)
      throws WxErrorException, IOException {
    //    logger.debug(
    //        "JoddApiSignaturePostRequestExecutor.execute uri:{}, headers:{}, postData:{}",
    //        uri,
    //        headers,
    //        postEntity);
    HttpConnectionProvider provider = requestHttp.getRequestHttpClient();
    ProxyInfo proxyInfo = requestHttp.getRequestHttpProxy();

    HttpRequest request = HttpRequest.post(uri);
    if (proxyInfo != null) {
      provider.useProxy(proxyInfo);
    }
    if (headers != null) {
      headers.forEach(request::header);
    }
    request.withConnectionProvider(provider);
    if (postEntity != null) {
      request.contentType("application/json", "utf-8");
      request.bodyText(postEntity);
    }
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());
    Map<String, String> respHeaders = new HashMap<>();
    for (String n : response.headerNames()) {
      respHeaders.putIfAbsent(n, response.header(n));
    }
    return this.handleResponse(wxType, response.bodyText(), respHeaders);
  }
}
