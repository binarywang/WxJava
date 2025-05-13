package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodSingleFileUploadResult;
import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * 小程序 提审素材上传接口
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author yangyh22
 * @since 2020/11/14
 */
public abstract class VodSingleUploadRequestExecutor<H, P> implements RequestExecutor<WxMaVodSingleFileUploadResult, File> {

  protected RequestHttp<H, P> requestHttp;
  protected String mediaName;
  protected String mediaType;
  protected String coverType;
  protected String sourceContext;
  protected File coverData;

  public VodSingleUploadRequestExecutor(RequestHttp<H, P> requestHttp, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) {
    this.requestHttp = requestHttp;
    this.mediaName = mediaName;
    this.mediaType = mediaType;
    this.coverType = coverType;
    this.coverData = coverData;
    this.sourceContext = sourceContext;

  }

  public static RequestExecutor<WxMaVodSingleFileUploadResult, File> create(RequestHttp<?, ?> requestHttp, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheVodSingleUploadRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp, mediaName, mediaType, coverType, coverData, sourceContext);
      case JODD_HTTP:
        return new JoddHttpVodSingleUploadRequestExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp, mediaName, mediaType, coverType, coverData, sourceContext);
      case OK_HTTP:
        return new OkHttpVodSingleUploadRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp, mediaName, mediaType, coverType, coverData, sourceContext);
      default:
        return null;
    }
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMaVodSingleFileUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }


}
