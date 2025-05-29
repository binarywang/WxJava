package me.chanjar.weixin.mp.util.requestexecuter.material;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * httpclient 实现的素材请求执行器.
 *
 * @author ecoolper
 * created on  2017/5/5
 */
@Slf4j
public class MaterialNewsInfoApacheHttpRequestExecutor
  extends MaterialNewsInfoRequestExecutor<CloseableHttpClient, HttpHost> {

  public MaterialNewsInfoApacheHttpRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialNews execute(String uri, String materialId, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }

    httpPost.setEntity(new StringEntity(WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId))));
    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      log.debug("响应原始数据：{}", responseContent);
      WxError error = WxError.fromJson(responseContent, WxType.MP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      } else {
        return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
      }
    }
  }
}
