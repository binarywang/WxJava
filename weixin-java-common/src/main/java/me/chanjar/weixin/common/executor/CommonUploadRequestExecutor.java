package me.chanjar.weixin.common.executor;

import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;

import java.io.IOException;

/**
 * 通用文件上传执行器
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on  2024/01/11
 */
public abstract class CommonUploadRequestExecutor<H, P> implements RequestExecutor<String, CommonUploadParam> {

  protected RequestHttp<H, P> requestHttp;

  public CommonUploadRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, CommonUploadParam data, ResponseHandler<String> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  /**
   * 构造通用文件上传执行器
   *
   * @param requestHttp 请求信息
   * @return 执行器
   */
  @SuppressWarnings("unchecked")
  public static RequestExecutor<String, CommonUploadParam> create(RequestHttp<?, ?> requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new CommonUploadRequestExecutorApacheImpl(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp);
      case JODD_HTTP:
        return new CommonUploadRequestExecutorJoddHttpImpl((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp);
      case OK_HTTP:
        return new CommonUploadRequestExecutorOkHttpImpl((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp);
      case HTTP_COMPONENTS:
        return new CommonUploadRequestExecutorHttpComponentsImpl(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }
}
