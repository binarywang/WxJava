package me.chanjar.weixin.aispeech.api.impl;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import me.chanjar.weixin.aispeech.api.WxAispeechDialogService;
import me.chanjar.weixin.aispeech.api.WxAispeechKnowledgeService;
import me.chanjar.weixin.aispeech.api.WxAispeechService;
import me.chanjar.weixin.aispeech.config.WxAispeechConfigStorage;
import me.chanjar.weixin.aispeech.util.WxAispeechSignUtil;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;

public class WxAispeechServiceImpl implements WxAispeechService {
  private static final Gson GSON = new Gson();

  private final WxAispeechDialogService dialogService = new WxAispeechDialogServiceImpl(this);
  private final WxAispeechKnowledgeService knowledgeService = new WxAispeechKnowledgeServiceImpl(this);

  private WxAispeechConfigStorage configStorage;
  private CloseableHttpClient httpClient;
  private HttpHost proxy;

  @Override
  public WxAispeechDialogService getDialogService() {
    return dialogService;
  }

  @Override
  public WxAispeechKnowledgeService getKnowledgeService() {
    return knowledgeService;
  }

  @Override
  public WxAispeechConfigStorage getConfigStorage() {
    return configStorage;
  }

  @Override
  public void setConfigStorage(WxAispeechConfigStorage configStorage) {
    this.configStorage = configStorage;
    this.initHttp();
  }

  protected void initHttp() {
    HttpComponentsClientBuilder builder = configStorage.getHttpComponentsClientBuilder();
    if (builder == null) {
      builder = DefaultHttpComponentsClientBuilder.get();
    }

    builder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword() == null ? null :
        configStorage.getHttpProxyPassword().toCharArray());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.proxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    } else {
      this.proxy = null;
    }

    this.httpClient = builder.build();
  }

  protected String executeDialogPost(String path, Object requestBody, boolean withOpenToken, String appid)
    throws WxErrorException {
    String body = toBody(requestBody);
    String requestId = UUID.randomUUID().toString();
    long timestamp = System.currentTimeMillis() / 1000;
    String nonce = randomNonce();
    String sign = WxAispeechSignUtil.calcDialogSign(configStorage.getToken(), timestamp, nonce, body);
    String resolvedAppid = StringUtils.defaultIfBlank(appid, configStorage.getAppid());

    HttpPost request = new HttpPost(configStorage.getDialogApiBaseUrl() + path);
    request.setHeader("request_id", requestId);
    request.setHeader("timestamp", String.valueOf(timestamp));
    request.setHeader("nonce", nonce);
    request.setHeader("sign", sign);
    request.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
    if (withOpenToken) {
      if (StringUtils.isBlank(configStorage.getOpenAiToken())) {
        throw new WxErrorException("X-OPENAI-TOKEN不能为空，请先调用getAccessToken或手动设置");
      }
      request.setHeader("X-OPENAI-TOKEN", configStorage.getOpenAiToken());
    } else {
      if (StringUtils.isBlank(resolvedAppid)) {
        throw new WxErrorException("X-APPID不能为空");
      }
      request.setHeader("X-APPID", resolvedAppid);
    }
    request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
    return executeRequest(request);
  }

  protected String executeKnowledgeGet(String path, Map<String, String> queryParams) throws WxErrorException {
    try {
      URIBuilder builder = new URIBuilder(configStorage.getKnowledgeApiBaseUrl() + path);
      if (queryParams != null) {
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
          if (entry.getValue() != null) {
            builder.addParameter(entry.getKey(), entry.getValue());
          }
        }
      }
      HttpGet request = new HttpGet(builder.build());
      enrichKnowledgeHeaders(request, "");
      return executeRequest(request);
    } catch (Exception e) {
      throw toWxErrorException(e);
    }
  }

  protected String executeKnowledgePost(String path, Object requestBody) throws WxErrorException {
    String body = toBody(requestBody);
    HttpPost request = new HttpPost(configStorage.getKnowledgeApiBaseUrl() + path);
    request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
    enrichKnowledgeHeaders(request, body);
    return executeRequest(request);
  }

  protected String executeKnowledgePut(String path, Object requestBody) throws WxErrorException {
    String body = toBody(requestBody);
    HttpPut request = new HttpPut(configStorage.getKnowledgeApiBaseUrl() + path);
    request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
    enrichKnowledgeHeaders(request, body);
    return executeRequest(request);
  }

  protected String executeKnowledgeMultipartPost(String path, File file, String title, String description, String metadata)
    throws WxErrorException {
    HttpPost request = new HttpPost(configStorage.getKnowledgeApiBaseUrl() + path);
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName());
    if (StringUtils.isNotBlank(title)) {
      builder.addTextBody("title", title, ContentType.TEXT_PLAIN.withCharset(StandardCharsets.UTF_8));
    }
    if (StringUtils.isNotBlank(description)) {
      builder.addTextBody("description", description, ContentType.TEXT_PLAIN.withCharset(StandardCharsets.UTF_8));
    }
    if (StringUtils.isNotBlank(metadata)) {
      builder.addTextBody("metadata", metadata, ContentType.APPLICATION_JSON);
    }
    HttpEntity entity = builder.build();
    request.setEntity(entity);
    if (entity.getContentType() != null) {
      request.setHeader("Content-Type", entity.getContentType());
    }
    enrichKnowledgeHeaders(request, "");
    return executeRequest(request);
  }

  protected String executeKnowledgeDelete(String path) throws WxErrorException {
    HttpUriRequestBase request = new HttpUriRequestBase("DELETE", URI.create(configStorage.getKnowledgeApiBaseUrl() + path));
    enrichKnowledgeHeaders(request, "");
    return executeRequest(request);
  }

  private void enrichKnowledgeHeaders(HttpUriRequestBase request, String body) throws WxErrorException {
    if (StringUtils.isBlank(configStorage.getAppid())) {
      throw new WxErrorException("知识助理请求需要配置appid");
    }
    if (StringUtils.isBlank(configStorage.getSecretKey())) {
      throw new WxErrorException("知识助理请求需要配置secretKey");
    }

    String requestId = UUID.randomUUID().toString();
    long timestamp = System.currentTimeMillis() / 1000;
    String nonce = randomNonce();
    String signature = WxAispeechSignUtil.calcKnowledgeSignature(configStorage.getSecretKey(), timestamp, nonce,
      requestId, body);

    request.setHeader("X-APPID", configStorage.getAppid());
    request.setHeader("X-Request-ID", requestId);
    request.setHeader("X-Timestamp", String.valueOf(timestamp));
    request.setHeader("X-Nonce", nonce);
    request.setHeader("X-Signature", signature);
    if (!request.containsHeader("Content-Type")) {
      request.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
    }
  }

  private String executeRequest(HttpUriRequestBase request) throws WxErrorException {
    if (this.proxy != null) {
      RequestConfig requestConfig = RequestConfig.custom().setProxy(this.proxy).build();
      request.setConfig(requestConfig);
    }

    try (CloseableHttpResponse response = httpClient.execute(request)) {
      int statusCode = response.getCode();
      HttpEntity entity = response.getEntity();
      String body = entity == null ? "" : EntityUtils.toString(entity, StandardCharsets.UTF_8);
      if (statusCode >= 200 && statusCode < 300) {
        return body;
      }

      throw new WxErrorException(WxError.builder().errorCode(statusCode).errorMsg(body).build());
    } catch (IOException | ParseException e) {
      throw toWxErrorException(e);
    }
  }

  protected <T> T fromJson(String json, Class<T> clazz) {
    return GSON.fromJson(json, clazz);
  }

  private String toBody(Object requestBody) {
    if (requestBody == null) {
      return "{}";
    }
    if (requestBody instanceof String) {
      return (String) requestBody;
    }
    return GSON.toJson(requestBody);
  }

  private WxErrorException toWxErrorException(Exception e) {
    if (e instanceof WxErrorException) {
      return (WxErrorException) e;
    }
    return new WxErrorException(e);
  }

  private String randomNonce() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
  }
}
