package me.chanjar.weixin.common.executor;

import lombok.Getter;
import me.chanjar.weixin.common.bean.CommonUploadData;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Apache HttpClient 通用文件上传器
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on  2024/01/11
 */
public class CommonUploadRequestExecutorApacheImpl
  extends CommonUploadRequestExecutor<CloseableHttpClient, HttpHost> {

  public CommonUploadRequestExecutorApacheImpl(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, CommonUploadParam param, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (param != null) {
      CommonUploadData data = param.getData();
      InnerStreamBody part = new InnerStreamBody(data.getInputStream(), ContentType.DEFAULT_BINARY, data.getFileName(), data.getLength());
      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addPart(param.getName(), part)
        .setMode(HttpMultipartMode.RFC6532)
        .build();
      httpPost.setEntity(entity);
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
