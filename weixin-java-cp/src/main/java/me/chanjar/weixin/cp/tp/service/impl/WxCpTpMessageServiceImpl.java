package me.chanjar.weixin.cp.tp.service.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.message.*;
import me.chanjar.weixin.cp.tp.service.WxCpTpMessageService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Message.*;

/**
 * 企业微信第三方应用消息推送接口实现类.
 *
 * <p>代授权企业发送应用消息，所有方法均需传入授权企业的 corpId。</p>
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@RequiredArgsConstructor
public class WxCpTpMessageServiceImpl implements WxCpTpMessageService {

  private final WxCpTpService mainService;

  @Override
  public WxCpMessageSendResult send(WxCpMessage message, String corpId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(MESSAGE_SEND)
      + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    return WxCpMessageSendResult.fromJson(this.mainService.post(url, message.toJson(), true));
  }

  @Override
  public WxCpMessageSendStatistics getStatistics(int timeType, String corpId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(GET_STATISTICS)
      + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    return WxCpMessageSendStatistics.fromJson(
      this.mainService.post(url, WxCpGsonBuilder.create().toJson(ImmutableMap.of("time_type", timeType)), true));
  }

  @Override
  public WxCpLinkedCorpMessageSendResult sendLinkedCorpMessage(WxCpLinkedCorpMessage message, String corpId)
    throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(LINKEDCORP_MESSAGE_SEND)
      + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    return WxCpLinkedCorpMessageSendResult.fromJson(this.mainService.post(url, message.toJson(), true));
  }

  @Override
  public WxCpSchoolContactMessageSendResult sendSchoolContactMessage(WxCpSchoolContactMessage message, String corpId)
    throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(EXTERNAL_CONTACT_MESSAGE_SEND)
      + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    return WxCpSchoolContactMessageSendResult.fromJson(this.mainService.post(url, message.toJson(), true));
  }

  @Override
  public void recall(String msgId, String corpId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("msgid", msgId);
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(MESSAGE_RECALL)
      + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    this.mainService.post(url, jsonObject.toString(), true);
  }

}
