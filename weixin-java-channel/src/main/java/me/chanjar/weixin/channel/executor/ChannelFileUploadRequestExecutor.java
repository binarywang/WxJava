package me.chanjar.weixin.channel.executor;


import java.io.File;
import java.io.IOException;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 视频号小店 图片上传接口 请求的参数是File, 返回的结果是String
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public class ChannelFileUploadRequestExecutor implements RequestExecutor<String, File> {

  protected RequestHttp<CloseableHttpClient, HttpHost> requestHttp;

  public ChannelFileUploadRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    this.requestHttp = requestHttp;
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
        .setMode(HttpMultipartMode.RFC6532)
        .build();
      httpPost.setEntity(entity);
    }
    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      return Utf8ResponseHandler.INSTANCE.handleResponse(response);
    } finally {
      httpPost.releaseConnection();
    }
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<String> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<String, File> create(RequestHttp<?, ?> requestHttp) throws WxErrorException {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ChannelFileUploadRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp);
      default:
        throw new WxErrorException("不支持的http框架");
    }
  }

}
