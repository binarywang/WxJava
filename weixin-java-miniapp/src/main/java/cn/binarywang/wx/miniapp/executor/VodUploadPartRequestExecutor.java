package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodUploadPartResult;
import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;

import java.io.File;
import java.io.IOException;

/**
 */
public abstract class VodUploadPartRequestExecutor<H, P> implements RequestExecutor<WxMaVodUploadPartResult, File> {

  protected RequestHttp<H, P> requestHttp;
  protected String uploadId;
  protected Integer partNumber;
  protected Integer resourceType;

  public VodUploadPartRequestExecutor(RequestHttp<H, P> requestHttp, String uploadId, Integer partNumber, Integer resourceType) {
    this.requestHttp = requestHttp;
    this.uploadId = uploadId;
    this.partNumber = partNumber;
    this.resourceType = resourceType;

  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<WxMaVodUploadPartResult, File> create(RequestHttp<?, ?> requestHttp, String uploadId, Integer partNumber, Integer resourceType) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheVodUploadPartRequestExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp,
          uploadId, partNumber, resourceType);
      case JODD_HTTP:
        return new JoddHttpVodUploadPartRequestExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp, uploadId, partNumber, resourceType);
      case OK_HTTP:
        return new OkHttpVodUploadPartRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp, uploadId, partNumber, resourceType);
      case HTTP_COMPONENTS:
        return new HttpComponentsVodUploadPartRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp,
          uploadId, partNumber, resourceType);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMaVodUploadPartResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

}
