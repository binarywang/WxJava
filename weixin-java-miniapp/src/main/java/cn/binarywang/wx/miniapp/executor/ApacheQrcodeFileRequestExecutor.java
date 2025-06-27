package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author wenqiang
 * @since 2020/12/25
 */
public class ApacheQrcodeFileRequestExecutor extends QrcodeRequestExecutor<CloseableHttpClient, HttpHost> {

  private final String filePath;

  public ApacheQrcodeFileRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, String filePath) {
    super(requestHttp);
    this.filePath = filePath;
  }

  /**
   * 执行http请求.
   *
   * @param uri           uri
   * @param qrcodeWrapper 数据
   * @param wxType        微信模块类型
   * @return 响应结果
   * @throws WxErrorException 自定义异常
   * @throws IOException      io异常
   */
  @Override
  public File execute(String uri, AbstractWxMaQrcodeWrapper qrcodeWrapper, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      httpPost.setConfig(
        RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build()
      );
    }

    httpPost.setEntity(new StringEntity(qrcodeWrapper.toJson(), ContentType.APPLICATION_JSON));

    try (final CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost);
         final InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
      Header[] contentTypeHeader = response.getHeaders("Content-Type");
      if (contentTypeHeader != null && contentTypeHeader.length > 0
        && ContentType.APPLICATION_JSON.getMimeType()
        .equals(ContentType.parse(contentTypeHeader[0].getValue()).getMimeType())) {
        String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
        throw new WxErrorException(WxError.fromJson(responseContent, wxType));
      }
      if (StringUtils.isBlank(filePath)) {
        return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg");
      }
      return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg", Paths.get(filePath).toFile());
    }
  }
}
