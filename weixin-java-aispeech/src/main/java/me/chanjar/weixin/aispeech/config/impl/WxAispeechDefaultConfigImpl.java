package me.chanjar.weixin.aispeech.config.impl;

import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.aispeech.config.WxAispeechConfigStorage;
import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;

@Getter
@Setter
public class WxAispeechDefaultConfigImpl implements WxAispeechConfigStorage {
  private String appid;
  private String token;
  private String aesKey;
  private String openAiToken;
  private String secretKey;
  private String dialogApiBaseUrl = "https://openaiapi.weixin.qq.com";
  private String knowledgeApiBaseUrl = "https://weknora.weixin.qq.com";
  private String httpProxyHost;
  private int httpProxyPort;
  private String httpProxyUsername;
  private String httpProxyPassword;
  private HttpComponentsClientBuilder httpComponentsClientBuilder;

  @Override
  public String getAppid() {
    return appid;
  }

  @Override
  public String getToken() {
    return token;
  }

  @Override
  public String getAesKey() {
    return aesKey;
  }

  @Override
  public String getOpenAiToken() {
    return openAiToken;
  }

  @Override
  public void setOpenAiToken(String openAiToken) {
    this.openAiToken = openAiToken;
  }

  @Override
  public String getSecretKey() {
    return secretKey;
  }

  @Override
  public String getDialogApiBaseUrl() {
    return dialogApiBaseUrl;
  }

  @Override
  public String getKnowledgeApiBaseUrl() {
    return knowledgeApiBaseUrl;
  }

  @Override
  public String getHttpProxyHost() {
    return httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return httpProxyPassword;
  }

  @Override
  public HttpComponentsClientBuilder getHttpComponentsClientBuilder() {
    return httpComponentsClientBuilder;
  }
}
