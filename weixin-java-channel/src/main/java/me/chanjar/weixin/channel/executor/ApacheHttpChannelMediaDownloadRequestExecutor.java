package me.chanjar.weixin.channel.executor;

import me.chanjar.weixin.channel.bean.image.ChannelImageResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.apache.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
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

public class ApacheHttpChannelMediaDownloadRequestExecutor extends ChannelMediaDownloadRequestExecutor<CloseableHttpClient, HttpHost> {

  public ApacheHttpChannelMediaDownloadRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public ChannelImageResponse execute(String uri, String data, WxType wxType) throws WxErrorException, IOException {
    HttpGet httpGet = new HttpGet(this.appendDataToUri(uri, data));
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

      File file = this.saveTmpFile(inputStream, this.getFileName(response));
      return new ChannelImageResponse(file, contentType);
    }
  }

  private String getFileName(CloseableHttpResponse response) throws WxErrorException {
    Header[] contentDispositionHeader = response.getHeaders("Content-disposition");
    if (contentDispositionHeader == null || contentDispositionHeader.length == 0) {
      return createDefaultFileName();
    }
    return this.extractFileNameFromContentString(contentDispositionHeader[0].getValue());
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<ChannelImageResponse> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }
}
