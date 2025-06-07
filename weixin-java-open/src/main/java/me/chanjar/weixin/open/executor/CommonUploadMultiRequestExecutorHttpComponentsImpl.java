package me.chanjar.weixin.open.executor;

import lombok.Getter;
import me.chanjar.weixin.common.bean.CommonUploadData;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.hc.Utf8ResponseHandler;
import me.chanjar.weixin.open.bean.CommonUploadMultiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.InputStreamBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHost;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author altusea
 */
public class CommonUploadMultiRequestExecutorHttpComponentsImpl extends CommonUploadMultiRequestExecutor<CloseableHttpClient, HttpHost> {

  public CommonUploadMultiRequestExecutorHttpComponentsImpl(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, CommonUploadMultiParam param, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (param != null) {
      MultipartEntityBuilder entity = MultipartEntityBuilder.create();

      List<CommonUploadMultiParam.NormalParam> normalParams = param.getNormalParams();
      if (!CollectionUtils.isEmpty(normalParams)) {
        for (CommonUploadMultiParam.NormalParam normalParam : normalParams) {
          entity.addPart(normalParam.getName(), new StringBody(normalParam.getValue(), ContentType.create("multipart/form-data", StandardCharsets.UTF_8)));
        }
      }

      CommonUploadParam uploadParam = param.getUploadParam();
      if (uploadParam != null) {
        CommonUploadData data = uploadParam.getData();
        InnerStreamBody part = new InnerStreamBody(data.getInputStream(), ContentType.DEFAULT_BINARY, data.getFileName(), data.getLength());
        entity.addPart(uploadParam.getName(), part)
          .setMode(HttpMultipartMode.EXTENDED);
      }

      httpPost.setEntity(entity.build());
    }
    String responseContent = requestHttp.getRequestHttpClient().execute(httpPost, Utf8ResponseHandler.INSTANCE);
    if (StringUtils.isEmpty(responseContent)) {
      throw new WxErrorException(String.format("上传失败，服务器响应空 url:%s param:%s", uri, param));
    }
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }

  /**
   * 内部流 请求体
   */
  @Getter
  public static class InnerStreamBody extends InputStreamBody {

    private final long contentLength;

    public InnerStreamBody(final InputStream in, final ContentType contentType, final String filename, long contentLength) {
      super(in, contentType, filename);
      this.contentLength = contentLength;
    }
  }
}
