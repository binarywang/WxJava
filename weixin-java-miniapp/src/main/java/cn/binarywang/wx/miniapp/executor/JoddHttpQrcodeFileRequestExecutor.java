package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.net.MimeTypes;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestHttp;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author wenqiang
 * @since 2020/12/25
 */
public class JoddHttpQrcodeFileRequestExecutor extends QrcodeRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  private final String filePath;

  public JoddHttpQrcodeFileRequestExecutor(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp, String filePath) {
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
    HttpRequest request = HttpRequest.get(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());
    String contentTypeHeader = response.header("Content-Type");
    if (MimeTypes.MIME_APPLICATION_JSON.equals(contentTypeHeader)) {
      String responseContent = response.bodyText();
      throw new WxErrorException(WxError.fromJson(responseContent, wxType));
    }
    try (InputStream inputStream = new ByteArrayInputStream(response.bodyBytes())) {
      if (StringUtils.isBlank(filePath)) {
        return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg");
      }
      return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg", Paths.get(filePath).toFile());
    }
  }
}
