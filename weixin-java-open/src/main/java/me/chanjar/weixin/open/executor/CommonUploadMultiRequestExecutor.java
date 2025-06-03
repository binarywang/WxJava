package me.chanjar.weixin.open.executor;

import me.chanjar.weixin.open.bean.CommonUploadMultiParam;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;

import java.io.IOException;

/**
 * @author xzh
 * @Description
 * @createTime 2024/08/14 16:28
 */
public abstract class CommonUploadMultiRequestExecutor<H, P> implements RequestExecutor<String, CommonUploadMultiParam> {

  protected RequestHttp<H, P> requestHttp;

  public CommonUploadMultiRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, CommonUploadMultiParam data, ResponseHandler<String> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  /**
   * 构造通用文件上传执行器
   *
   * @param requestHttp 请求信息
   * @return 执行器
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public static RequestExecutor<String, CommonUploadMultiParam> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new CommonUploadMultiRequestExecutorApacheImpl(requestHttp);
      case JODD_HTTP:
        return new CommonUploadMultiRequestExecutorJoddHttpImpl(requestHttp);
      case OK_HTTP:
        return new CommonUploadMultiRequestExecutorOkHttpImpl(requestHttp);
      case HTTP_CLIENT_5:
        return new CommonUploadMultiRequestExecutorHttpClient5Impl(requestHttp);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }
}
