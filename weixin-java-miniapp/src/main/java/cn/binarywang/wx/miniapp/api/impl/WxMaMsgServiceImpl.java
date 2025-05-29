package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUniformMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUpdatableMsg;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Msg.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMaMsgServiceImpl implements WxMaMsgService {
  private final WxMaService service;

  @Override
  public boolean sendKefuMsg(WxMaKefuMessage message) throws WxErrorException {
    String responseContent = this.service.post(KEFU_MESSAGE_SEND_URL, message.toJson());
    return responseContent != null;
  }

  @Override
  public void sendSubscribeMsg(WxMaSubscribeMessage subscribeMessage) throws WxErrorException {
    String responseContent = this.service.post(SUBSCRIBE_MSG_SEND_URL, subscribeMessage.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }

  @Override
  public void sendUniformMsg(WxMaUniformMessage uniformMessage) throws WxErrorException {
    String responseContent = this.service.post(UNIFORM_MSG_SEND_URL, uniformMessage.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }

  @Override
  public JsonObject createUpdatableMessageActivityId() throws WxErrorException {
    final String responseContent = this.service.get(ACTIVITY_ID_CREATE_URL, null);
    return GsonParser.parse(responseContent);
  }

  @Override
  public void setUpdatableMsg(WxMaUpdatableMsg msg) throws WxErrorException {
    this.service.post(UPDATABLE_MSG_SEND_URL, WxMaGsonBuilder.create().toJson(msg));
  }

}
