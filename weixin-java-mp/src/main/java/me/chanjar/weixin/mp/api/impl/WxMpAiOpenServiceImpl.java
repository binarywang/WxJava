package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.api.WxMpAiOpenService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpAiConversationRequest;
import me.chanjar.weixin.mp.bean.WxMpAiConversationResponse;
import me.chanjar.weixin.mp.enums.AiLangType;
import me.chanjar.weixin.mp.util.requestexecuter.voice.VoiceUploadRequestExecutor;

import java.io.File;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.AiOpen.*;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/9.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMpAiOpenServiceImpl implements WxMpAiOpenService {
  private final WxMpService wxMpService;

  @Override
  public void uploadVoice(String voiceId, AiLangType lang, File voiceFile) throws WxErrorException {
    if (lang == null) {
      lang = AiLangType.zh_CN;
    }

    this.wxMpService.execute(VoiceUploadRequestExecutor.create(this.wxMpService.getRequestHttp()),
      String.format(VOICE_UPLOAD_URL.getUrl(this.wxMpService.getWxMpConfigStorage()), "mp3", voiceId, lang.getCode()),
      voiceFile);
  }

  @Override
  public String recogniseVoice(String voiceId, AiLangType lang, File voiceFile) throws WxErrorException {
    this.uploadVoice(voiceId, lang, voiceFile);
    return this.queryRecognitionResult(voiceId, lang);
  }

  @Override
  public String translate(AiLangType langFrom, AiLangType langTo, String content) throws WxErrorException {
    String response = this.wxMpService.post(String.format(TRANSLATE_URL.getUrl(this.wxMpService.getWxMpConfigStorage()),
      langFrom.getCode(), langTo.getCode()), content);

    WxError error = WxError.fromJson(response, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return GsonParser.parse(response).get("to_content").getAsString();
  }

  @Override
  public String queryRecognitionResult(String voiceId, AiLangType lang) throws WxErrorException {
    if (lang == null) {
      lang = AiLangType.zh_CN;
    }

    final String response = this.wxMpService.get(VOICE_QUERY_RESULT_URL,
      String.format("voice_id=%s&lang=%s", voiceId, lang.getCode()));
    WxError error = WxError.fromJson(response, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return GsonParser.parse(response).get("result").getAsString();
  }

  @Override
  public String intelligentConversation(String query, String sessionId) throws WxErrorException {
    return this.intelligentConversation(query, sessionId, AiLangType.zh_CN);
  }

  @Override
  public String intelligentConversation(String query, String sessionId, AiLangType lang) throws WxErrorException {
    if (lang == null) {
      lang = AiLangType.zh_CN;
    }

    // 构建请求JSON
    JsonObject request = new JsonObject();
    request.addProperty("query", query);
    request.addProperty("session_id", sessionId);
    request.addProperty("lang", lang.getCode());

    final String response = this.wxMpService.post(INTELLIGENT_CONVERSATION_URL.getUrl(this.wxMpService.getWxMpConfigStorage()), 
      request.toString());
    
    WxError error = WxError.fromJson(response, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return GsonParser.parse(response).get("reply").getAsString();
  }

  @Override
  public WxMpAiConversationResponse intelligentConversation(WxMpAiConversationRequest request) throws WxErrorException {
    // 构建请求JSON
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty("query", request.getQuery());
    requestJson.addProperty("session_id", request.getSessionId());
    requestJson.addProperty("lang", request.getLang() != null ? request.getLang().getCode() : AiLangType.zh_CN.getCode());

    final String response = this.wxMpService.post(INTELLIGENT_CONVERSATION_URL.getUrl(this.wxMpService.getWxMpConfigStorage()), 
      requestJson.toString());
    
    WxError error = WxError.fromJson(response, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    WxMpAiConversationResponse result = WxMpAiConversationResponse.fromJson(response);
    if (result.getReply() == null) {
      result.setReply(GsonParser.parse(response).get("reply").getAsString());
    }
    if (result.getSessionId() == null) {
      result.setSessionId(request.getSessionId());
    }
    
    return result;
  }
}
