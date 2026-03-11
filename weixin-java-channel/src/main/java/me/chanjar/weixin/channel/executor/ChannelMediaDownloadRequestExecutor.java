package me.chanjar.weixin.channel.executor;

import me.chanjar.weixin.channel.bean.image.ChannelImageResponse;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import org.apache.commons.io.IOUtils;

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
public abstract class ChannelMediaDownloadRequestExecutor<H, P> implements RequestExecutor<ChannelImageResponse, String> {

  protected RequestHttp<H, P> requestHttp;
  protected File tmpDirFile;

  private static final Pattern PATTERN = Pattern.compile(".*filename=\"(.*)\"");

  public ChannelMediaDownloadRequestExecutor(RequestHttp<H, P> requestHttp, File tmpDirFile) {
    this.requestHttp = requestHttp;
    this.tmpDirFile = tmpDirFile;
  }

  @SuppressWarnings("unchecked")
  public static RequestExecutor<ChannelImageResponse, String> create(RequestHttp<?, ?> requestHttp, File tmpDirFile) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheHttpChannelMediaDownloadRequestExecutor(
          (RequestHttp<org.apache.http.impl.client.CloseableHttpClient, org.apache.http.HttpHost>) requestHttp, tmpDirFile);
      case HTTP_COMPONENTS:
        return new HttpComponentsChannelMediaDownloadRequestExecutor(
          (RequestHttp<org.apache.hc.client5.http.impl.classic.CloseableHttpClient, org.apache.hc.core5.http.HttpHost>) requestHttp, tmpDirFile);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
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

  protected String createDefaultFileName() {
    return UUID.randomUUID().toString();
  }

  protected String extractFileNameFromContentString(String content) {
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
