package me.chanjar.weixin.channel.executor;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.bean.image.ChannelImageResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.apache.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.io.FileUtils.openOutputStream;

/**
 * 下载媒体文件请求执行器
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class ChannelMediaDownloadRequestExecutor implements RequestExecutor<ChannelImageResponse, String> {

  protected RequestHttp<CloseableHttpClient, HttpHost> requestHttp;
  protected File tmpDirFile;

  private static final Pattern PATTERN = Pattern.compile(".*filename=\"(.*)\"");

  public ChannelMediaDownloadRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, File tmpDirFile) {
    this.requestHttp = requestHttp;
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public ChannelImageResponse execute(String uri, String data, WxType wxType) throws WxErrorException, IOException {
    if (data != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? data : '&' + data;
    }

    HttpGet httpGet = new HttpGet(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpGet.setConfig(config);
    }

    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpGet);
      InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
      Header[] contentTypeHeader = response.getHeaders("Content-Type");
      String contentType = null;
      if (contentTypeHeader != null && contentTypeHeader.length > 0) {
        contentType = contentTypeHeader[0].getValue();
        if (contentType.startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
          // application/json; encoding=utf-8 下载媒体文件出错
          String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
          return JsonUtils.decode(responseContent, ChannelImageResponse.class);
        }
      }

      String fileName = this.getFileName(response);
      if (StringUtils.isBlank(fileName)) {
        fileName = String.valueOf(System.currentTimeMillis());
      }

      String baseName = FilenameUtils.getBaseName(fileName);
      if (StringUtils.isBlank(fileName) || baseName.length() < 3) {
        baseName = String.valueOf(System.currentTimeMillis());
      }
      String extension = FilenameUtils.getExtension(fileName);
      if (StringUtils.isBlank(extension)) {
        extension = "unknown";
      }
      File file = createTmpFile(inputStream, baseName, extension, tmpDirFile);
      return new ChannelImageResponse(file, contentType);
    }
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<ChannelImageResponse> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<ChannelImageResponse, String> create(RequestHttp<?, ?> requestHttp, File tmpDirFile) throws WxErrorException {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ChannelMediaDownloadRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp, tmpDirFile);
      default:
        throw new WxErrorException("不支持的http框架");
    }
  }

  /**
   * 创建临时文件
   *
   * @param inputStream 输入文件流
   * @param name        文件名
   * @param ext         扩展名
   * @param tmpDirFile  临时文件夹目录
   */
  public static File createTmpFile(InputStream inputStream, String name, String ext, File tmpDirFile)
    throws IOException {
    File resultFile = File.createTempFile(name, '.' + ext, tmpDirFile);
    resultFile.deleteOnExit();
    try (InputStream in = inputStream; OutputStream out = openOutputStream(resultFile)) {
      IOUtils.copy(in, out);
    }
    return resultFile;
  }

  private String getFileName(CloseableHttpResponse response) throws WxErrorException {
    Header[] contentDispositionHeader = response.getHeaders("Content-disposition");
    if (contentDispositionHeader == null || contentDispositionHeader.length == 0) {
      return createDefaultFileName();
    }
    return this.extractFileNameFromContentString(contentDispositionHeader[0].getValue());
  }

  private String createDefaultFileName() {
    return UUID.randomUUID().toString();
  }

  private String extractFileNameFromContentString(String content) throws WxErrorException {
    if (content == null || content.isEmpty()) {
      return createDefaultFileName();
    }
    Matcher m = PATTERN.matcher(content);
    if (m.matches()) {
      return m.group(1);
    }
    return createDefaultFileName();
  }

}
