package me.chanjar.weixin.channel.executor;

import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.File;

/**
 * 视频号小店 图片上传接口 请求的参数是File, 返回的结果是String
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public abstract class ChannelFileUploadRequestExecutor<H, P> implements RequestExecutor<String, File> {

  protected RequestHttp<H, P> requestHttp;

  public ChannelFileUploadRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<String, File> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheHttpChannelFileUploadRequestExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp);
      case HTTP_COMPONENTS:
        return new HttpComponentsChannelFileUploadRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }

}
