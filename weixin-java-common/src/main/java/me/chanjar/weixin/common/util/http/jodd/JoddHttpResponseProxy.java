package me.chanjar.weixin.common.util.http.jodd;

import jodd.http.HttpResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpResponseProxy;

public class JoddHttpResponseProxy implements HttpResponseProxy {

  private final HttpResponse response;

  public JoddHttpResponseProxy(HttpResponse httpResponse) {
    this.response = httpResponse;
  }

  @Override
  public String getFileName() throws WxErrorException {
    String content = response.header("Content-disposition");
    return HttpResponseProxy.extractFileNameFromContentString(content);
  }
}
