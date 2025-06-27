package me.chanjar.weixin.channel.executor;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.hc.Utf8ResponseHandler;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;

import java.io.File;
import java.io.IOException;

public class HttpComponentsChannelFileUploadRequestExecutor extends ChannelFileUploadRequestExecutor<CloseableHttpClient, HttpHost> {
  public HttpComponentsChannelFileUploadRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (file != null) {
      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addBinaryBody("media", file)
        .setMode(HttpMultipartMode.EXTENDED)
        .build();
      httpPost.setEntity(entity);
    }
    return requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<String> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }
}
