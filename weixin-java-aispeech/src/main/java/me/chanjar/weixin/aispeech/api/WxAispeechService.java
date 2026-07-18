package me.chanjar.weixin.aispeech.api;

import me.chanjar.weixin.aispeech.config.WxAispeechConfigStorage;

public interface WxAispeechService {
  WxAispeechDialogService getDialogService();

  WxAispeechKnowledgeService getKnowledgeService();

  WxAispeechConfigStorage getConfigStorage();

  void setConfigStorage(WxAispeechConfigStorage configStorage);
}
