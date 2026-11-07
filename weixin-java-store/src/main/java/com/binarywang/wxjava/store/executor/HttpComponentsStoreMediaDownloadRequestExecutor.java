package com.binarywang.wxjava.store.executor;

import com.binarywang.wxjava.store.bean.image.StoreImageResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.hc.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.http.hc.Utf8ResponseHandler;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HttpComponentsStoreMediaDownloadRequestExecutor extends StoreMediaDownloadRequestExecutor<CloseableHttpClient, HttpHost> {

  public HttpComponentsStoreMediaDownloadRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public StoreImageResponse execute(String uri, String data, WxType wxType) throws WxErrorException, IOException {
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

    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpGet)) {
      Header[] contentTypeHeader = response.getHeaders("Content-Type");
      String contentType = null;
      if (contentTypeHeader != null && contentTypeHeader.length > 0) {
        contentType = contentTypeHeader[0].getValue();
        if (contentType.startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
          // application/json; encoding=utf-8 下载媒体文件出错
          String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
          return JsonUtils.decode(responseContent, StoreImageResponse.class);
        }
      }

      try (InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
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
        return new StoreImageResponse(file, contentType);
      }
    } catch (HttpException httpException) {
      throw new ClientProtocolException(httpException.getMessage(), httpException);
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
  public void execute(String uri, String data, ResponseHandler<StoreImageResponse> handler, WxType wxType)
    throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }
}
