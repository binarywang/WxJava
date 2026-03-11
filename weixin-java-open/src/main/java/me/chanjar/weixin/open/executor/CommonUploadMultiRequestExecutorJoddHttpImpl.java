package me.chanjar.weixin.open.executor;

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
import me.chanjar.weixin.open.bean.CommonUploadMultiParam;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author xzh
 * @Description
 * @createTime 2024/08/14 16:43
 */
public class CommonUploadMultiRequestExecutorJoddHttpImpl extends CommonUploadMultiRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public CommonUploadMultiRequestExecutorJoddHttpImpl(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, CommonUploadMultiParam param, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    List<CommonUploadMultiParam.NormalParam> normalParams = param.getNormalParams();
    if (!CollectionUtils.isEmpty(normalParams)) {
      for (CommonUploadMultiParam.NormalParam normalParam : normalParams) {
        request.form(normalParam.getName(), new StringBody(normalParam.getValue(), ContentType.MULTIPART_FORM_DATA.withCharset(StandardCharsets.UTF_8)));
      }
    }

    CommonUploadParam uploadParam = param.getUploadParam();
    if (uploadParam != null) {
      request.form(uploadParam.getName(), new CommonUploadParamToUploadableAdapter(uploadParam.getData()));
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

