package me.chanjar.weixin.common.util.http.hc;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpResponseProxy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;

public class HttpComponentsResponseProxy implements HttpResponseProxy {

  private final CloseableHttpResponse response;

  public HttpComponentsResponseProxy(CloseableHttpResponse closeableHttpResponse) {
    this.response = closeableHttpResponse;
  }

  @Override
  public String getFileName() throws WxErrorException {
    Header[] contentDispositionHeader = this.response.getHeaders("Content-disposition");
    if (contentDispositionHeader == null || contentDispositionHeader.length == 0) {
      throw new WxErrorException("无法获取到文件名，Content-disposition为空");
    }

    return HttpResponseProxy.extractFileNameFromContentString(contentDispositionHeader[0].getValue());
  }
}
