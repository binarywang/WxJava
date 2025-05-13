package me.chanjar.weixin.common.util.http.jodd;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.http.upload.ByteArrayUploadable;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.InputStreamData;
import me.chanjar.weixin.common.util.http.MediaInputStreamUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 文件输入流上传.
 *
 * @author meiqin.zhou91@gmail.com
 * created on  2022/02/15
 */
public class JoddHttpMediaInputStreamUploadRequestExecutor extends MediaInputStreamUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public JoddHttpMediaInputStreamUploadRequestExecutor(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaUploadResult execute(String uri, InputStreamData data, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    request.form("media", new ByteArrayUploadable(this.toByteArray(data.getInputStream()), data.getFilename()));
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());

    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMediaUploadResult.fromJson(responseContent);
  }

  public byte[] toByteArray(InputStream input) throws IOException {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      byte[] buffer = new byte[4096];
      int n = 0;
      while (-1 != (n = input.read(buffer))) {
        output.write(buffer, 0, n);
      }
      return output.toByteArray();
    }
  }
}
