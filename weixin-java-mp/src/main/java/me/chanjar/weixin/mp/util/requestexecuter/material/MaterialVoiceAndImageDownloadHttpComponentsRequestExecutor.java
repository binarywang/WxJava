package me.chanjar.weixin.mp.util.requestexecuter.material;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.hc.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MaterialVoiceAndImageDownloadHttpComponentsRequestExecutor
  extends MaterialVoiceAndImageDownloadRequestExecutor<CloseableHttpClient, HttpHost> {
  public MaterialVoiceAndImageDownloadHttpComponentsRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public InputStream execute(String uri, String materialId, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }

    Map<String, String> params = new HashMap<>();
    params.put("media_id", materialId);
    httpPost.setEntity(new StringEntity(WxGsonBuilder.create().toJson(params)));
    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost);
         InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
      // 下载媒体文件出错
      byte[] responseContent = IOUtils.toByteArray(inputStream);
      String responseContentString = new String(responseContent, StandardCharsets.UTF_8);
      if (responseContentString.length() <= 215) {
        try {
          WxError wxError = WxGsonBuilder.create().fromJson(responseContentString, WxError.class);
          if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
          }
        } catch (com.google.gson.JsonSyntaxException ex) {
          return new ByteArrayInputStream(responseContent);
        }
      }
      return new ByteArrayInputStream(responseContent);
    } catch (HttpException httpException) {
      throw new ClientProtocolException(httpException.getMessage(), httpException);
    }
  }
}
