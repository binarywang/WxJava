package me.chanjar.weixin.aispeech.config;

import me.chanjar.weixin.common.util.http.hc.HttpComponentsClientBuilder;

public interface WxAispeechConfigStorage {
  String getAppid();

  String getToken();

  String getAesKey();

  String getOpenAiToken();

  void setOpenAiToken(String openAiToken);

  String getSecretKey();

  String getDialogApiBaseUrl();

  String getKnowledgeApiBaseUrl();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

  HttpComponentsClientBuilder getHttpComponentsClientBuilder();
}
