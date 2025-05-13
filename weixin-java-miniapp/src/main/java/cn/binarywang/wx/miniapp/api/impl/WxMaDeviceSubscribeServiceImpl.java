package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaDeviceSubscribeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.device.WxMaDeviceSubscribeMessageRequest;
import cn.binarywang.wx.miniapp.bean.device.WxMaDeviceTicketRequest;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.GET_SN_TICKET_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.SEND_DEVICE_SUBSCRIBE_MSG_URL;

/**
 * 小程序设备订阅消息相关 API
 * 文档：
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 * @since 2021-12-16 17:13:35
 */

@RequiredArgsConstructor
public class WxMaDeviceSubscribeServiceImpl implements WxMaDeviceSubscribeService {

  private final WxMaService service;

  @Override
  public String getSnTicket(WxMaDeviceTicketRequest deviceTicketRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_SN_TICKET_URL, deviceTicketRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    String snTicket = jsonObject.get("sn_ticket").getAsString();
    return snTicket;
  }

  @Override
  public void sendDeviceSubscribeMsg(WxMaDeviceSubscribeMessageRequest deviceSubscribeMessageRequest) throws WxErrorException {
    String responseContent = this.service.post(SEND_DEVICE_SUBSCRIBE_MSG_URL, deviceSubscribeMessageRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }
}
