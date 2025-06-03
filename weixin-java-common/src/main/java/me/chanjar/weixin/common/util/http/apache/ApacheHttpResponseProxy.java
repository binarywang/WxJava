package me.chanjar.weixin.common.util.http.apache;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpResponseProxy;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

public class ApacheHttpResponseProxy implements HttpResponseProxy {

  private final CloseableHttpResponse httpResponse;

  public ApacheHttpResponseProxy(CloseableHttpResponse closeableHttpResponse) {
    this.httpResponse = closeableHttpResponse;
  }

  @Override
  public String getFileName() throws WxErrorException {
    Header[] contentDispositionHeader = this.httpResponse.getHeaders("Content-disposition");
    if (contentDispositionHeader == null || contentDispositionHeader.length == 0) {
      throw new WxErrorException("无法获取到文件名，Content-disposition为空");
    }

    return HttpResponseProxy.extractFileNameFromContentString(contentDispositionHeader[0].getValue());
  }
}
