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
}
