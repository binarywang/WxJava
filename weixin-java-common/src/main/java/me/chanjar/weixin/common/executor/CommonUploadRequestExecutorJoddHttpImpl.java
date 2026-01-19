package me.chanjar.weixin.common.executor;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.http.upload.Uploadable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.bean.CommonUploadData;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * JoddHttp 通用文件上传器
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on  2024/01/11
 */
public class CommonUploadRequestExecutorJoddHttpImpl extends CommonUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public CommonUploadRequestExecutorJoddHttpImpl(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, CommonUploadParam param, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    request.form(param.getName(), new CommonUploadParamToUploadableAdapter(param.getData()));

    // 添加额外的表单字段
    if (param.getFormFields() != null && !param.getFormFields().isEmpty()) {
      for (java.util.Map.Entry<String, String> entry : param.getFormFields().entrySet()) {
        request.form(entry.getKey(), entry.getValue());
      }
    }

    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());
    String responseContent = response.bodyText();
    if (responseContent.isEmpty()) {
      throw new WxErrorException(String.format("上传失败，服务器响应空 url:%s param:%s", uri, param));
    }
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }

  /**
   * 通用上传参数 到 Uploadable 的适配器
   */
  @Getter
  @AllArgsConstructor
  public static class CommonUploadParamToUploadableAdapter implements Uploadable<CommonUploadData> {

    private CommonUploadData content;

    @SneakyThrows
    @Override
    public byte[] getBytes() {
      return content.readAllBytes();
    }

    @Override
    public String getFileName() {
      return content.getFileName();
    }

    @Override
    public String getMimeType() {
      return null;
    }

    @SneakyThrows
    @Override
    public int getSize() {
      return (int) content.getLength();
    }

    @Override
    public InputStream openInputStream() {
      return content.getInputStream();
    }
  }
}
