package me.chanjar.weixin.mp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.api.WxMpAiOpenService;
import me.chanjar.weixin.mp.api.WxMpService;
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
}
