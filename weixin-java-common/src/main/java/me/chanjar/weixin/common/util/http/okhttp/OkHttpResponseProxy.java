package me.chanjar.weixin.common.util.http.okhttp;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpResponseProxy;
import okhttp3.Response;

public class OkHttpResponseProxy implements HttpResponseProxy {

  private final Response response;

  public OkHttpResponseProxy(Response response) {
    this.response = response;
  }

  @Override
  public String getFileName() throws WxErrorException {
    String content = this.response.header("Content-disposition");
    return HttpResponseProxy.extractFileNameFromContentString(content);
  }
}
