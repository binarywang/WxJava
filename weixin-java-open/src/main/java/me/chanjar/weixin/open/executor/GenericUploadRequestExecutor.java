package me.chanjar.weixin.open.executor;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import lombok.Data;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 通用的上传请求执行器
 */
public class GenericUploadRequestExecutor implements RequestExecutor<String, InputStream> {

  private final Executor<?, ?> executor;

  /**
   * 构造通用执行器
   *
   * @param requestHttp http请求
   * @param httpMethod  http方法(POST PUT PATCH)
   * @param paramName   参数名
   * @param fileName    文件名
   */
  @SuppressWarnings("all")
  public GenericUploadRequestExecutor(RequestHttp<?, ?> requestHttp, String httpMethod, String paramName, String fileName) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        executor = new ApacheExecutor();
        break;
      case OK_HTTP:
        executor = new OkExecutor();
        break;
      case JODD_HTTP:
        executor = new JoddExecutor();
        break;
      default:
        throw new UnsupportedOperationException("使用了暂不支持的HTTP客户端:" + requestHttp.getRequestType());
    }
    executor.setRequestHttp((RequestHttp) requestHttp);
    executor.setHttpMethod(httpMethod);
    executor.setParamName(paramName);
    executor.setFileName(fileName);
  }

  @Override
  public String execute(String uri, InputStream data, WxType wxType) throws WxErrorException, IOException {
    String json = executor.execute(uri, data, wxType);
    WxError error = WxError.fromJson(json, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return json;
  }

  @Override
  public void execute(String uri, InputStream data, ResponseHandler<String> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  /**
   * 内部请求执行器
   *
   * @param <CLIENT> http客户端
   * @param <PROXY>  http代理
   */
  @Data
  public static abstract class Executor<CLIENT, PROXY> {

    private RequestHttp<CLIENT, PROXY> requestHttp;
    private String httpMethod;
    private String paramName;
    private String fileName;

    public abstract String execute(String uri, InputStream data, WxType wxType) throws WxErrorException, IOException;
  }

  /**
   * 阿帕奇执行器
   */
  public static class ApacheExecutor extends Executor<CloseableHttpClient, HttpHost> {

    @Override
    public String execute(String uri, InputStream data, WxType wxType) throws WxErrorException, IOException {
      HttpEntityEnclosingRequestBase bodyRequest;
      switch (getHttpMethod()) {
        case "POST":
          bodyRequest = new HttpPost(uri);
          break;
        case "PUT":
          bodyRequest = new HttpPut(uri);
          break;
        case "PATCH":
          bodyRequest = new HttpPatch(uri);
          break;
        default:
          throw new IllegalAccessError("不支持的请求方式:" + getHttpMethod());
      }
      if (getRequestHttp().getRequestHttpProxy() != null) {
        RequestConfig config = RequestConfig.custom().setProxy(getRequestHttp().getRequestHttpProxy()).build();
        bodyRequest.setConfig(config);
      }

      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addBinaryBody(getParamName(), data, ContentType.create("multipart/form-data", StandardCharsets.UTF_8),
          getFileName())
        .setMode(HttpMultipartMode.RFC6532)
        .build();
      bodyRequest.setEntity(entity);
      bodyRequest.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());

      return getRequestHttp().getRequestHttpClient().execute(bodyRequest, Utf8ResponseHandler.INSTANCE);
    }
  }

  /**
   * ok执行器
   */
  public static class OkExecutor extends Executor<OkHttpClient, HttpHost> {

    @Override
    public String execute(String uri, InputStream data, WxType wxType) throws WxErrorException, IOException {
      OkHttpClient client = getRequestHttp().getRequestHttpClient();
      byte[] bytes = IOUtils.toByteArray(data);
      RequestBody body = new MultipartBody.Builder()
        .setType(Objects.requireNonNull(MediaType.parse("multipart/form-data")))
        .addFormDataPart("media", getFileName(), RequestBody.create(bytes, MediaType.parse("application/octet-stream")))
        .build();

      Request request = new Request.Builder().url(uri).method(getHttpMethod(), body).build();
      Response response = client.newCall(request).execute();
      return response.body().string();
    }
  }

  /**
   * jodd执行器
   */
  public static class JoddExecutor extends Executor<HttpConnectionProvider, ProxyInfo> {

    @Override
    public String execute(String uri, InputStream data, WxType wxType) throws WxErrorException, IOException {
      HttpRequest request = HttpRequest.post(uri);
      if (getRequestHttp().getRequestHttpProxy() != null) {
        getRequestHttp().getRequestHttpClient().useProxy(getRequestHttp().getRequestHttpProxy());
      }
      request.withConnectionProvider(getRequestHttp().getRequestHttpClient());

      request.form(getParamName(), data);

      HttpResponse response = request.send();
      response.charset(StandardCharsets.UTF_8.name());
      return response.bodyText();
    }
  }
}
