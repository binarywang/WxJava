package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaDeviceSubscribeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.device.*;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.GET_SN_TICKET_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.SEND_DEVICE_SUBSCRIBE_MSG_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.CREATE_IOT_GROUP_ID_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.GET_IOT_GROUP_INFO_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.ADD_IOT_GROUP_DEVICE_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.DeviceSubscribe.REMOVE_IOT_GROUP_DEVICE_URL;

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

  @Override
  public String createIotGroupId(WxMaCreateIotGroupIdRequest createIotGroupIdRequest) throws WxErrorException {
    String responseContent = this.service.post(CREATE_IOT_GROUP_ID_URL, createIotGroupIdRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return jsonObject.get("group_id").getAsString();
  }

  @Override
  public WxMaIotGroupDeviceInfoResponse getIotGroupInfo(WxMaGetIotGroupInfoRequest getIotGroupInfoRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_IOT_GROUP_INFO_URL, getIotGroupInfoRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxGsonBuilder.create().fromJson(responseContent, WxMaIotGroupDeviceInfoResponse.class);
  }

  @Override
  public List<WxMaDeviceTicketRequest> addIotGroupDevice(WxMaIotGroupDeviceRequest request) throws WxErrorException {
    String responseContent = this.service.post(ADD_IOT_GROUP_DEVICE_URL, request.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(jsonObject.getAsJsonArray("device_list"), new TypeToken<List<WxMaDeviceTicketRequest>>() {
    }.getType());
  }

  @Override
  public List<WxMaDeviceTicketRequest> removeIotGroupDevice(WxMaIotGroupDeviceRequest request) throws WxErrorException {
    String responseContent = this.service.post(REMOVE_IOT_GROUP_DEVICE_URL, request.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(jsonObject.getAsJsonArray("device_list"), new TypeToken<List<WxMaDeviceTicketRequest>>() {
    }.getType());
  }
}
