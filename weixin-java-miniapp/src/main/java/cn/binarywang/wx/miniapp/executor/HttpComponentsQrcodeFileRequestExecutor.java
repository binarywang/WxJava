package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.hc.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.http.hc.Utf8ResponseHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author altusea
 */
public class HttpComponentsQrcodeFileRequestExecutor extends QrcodeRequestExecutor<CloseableHttpClient, HttpHost> {

  private final String filePath;

  public HttpComponentsQrcodeFileRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, String filePath) {
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
    } catch (HttpException httpException) {
      throw new ClientProtocolException(httpException.getMessage(), httpException);
    }
  }
}
