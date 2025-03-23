package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaOrderShippingService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaOrderShippingIsTradeManagedRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.shipping.*;
import cn.binarywang.wx.miniapp.bean.shop.response.*;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.OrderShipping.*;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.OTHER.GET_CATEGORY;


/**
 * @author xzh
 * created on  2023/5/17 17:44
 */
@Slf4j
@RequiredArgsConstructor
public class WxMaOrderShippingServiceImpl implements WxMaOrderShippingService {

  private final WxMaService wxMaService;

  /**
   * 查询小程序是否已开通发货信息管理服务
   *
   * @param appId 待查询小程序的 appid，非服务商调用时仅能查询本账号
   * @return WxMaOrderShippingInfoBaseResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingIsTradeManagedResponse isTradeManaged(String appId) throws WxErrorException {
    WxMaOrderShippingIsTradeManagedRequest request = WxMaOrderShippingIsTradeManagedRequest.builder().appId(appId).build();
    return request(IS_TRADE_MANAGED, request, WxMaOrderShippingIsTradeManagedResponse.class);
  }

  /**
   * 发货信息录入接口
   *
   * @param request 请求
   * @return WxMaOrderShippingInfoBaseResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingInfoBaseResponse upload(WxMaOrderShippingInfoUploadRequest request) throws WxErrorException {
    return request(UPLOAD_SHIPPING_INFO, request, WxMaOrderShippingInfoBaseResponse.class);
  }

  /**
   * 发货信息合单录入接口
   *
   * @param request 请求
   * @return WxMaOrderShippingInfoBaseResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingInfoBaseResponse upload(WxMaOrderCombinedShippingInfoUploadRequest request) throws WxErrorException {
    return request(UPLOAD_COMBINED_SHIPPING_INFO, request, WxMaOrderShippingInfoBaseResponse.class);
  }

  /**
   * 查询订单发货状态
   * 你可以通过交易单号或商户号+商户单号来查询该支付单的发货状态。
   *
   * @param request 请求
   * @return WxMaOrderShippingInfoGetResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingInfoGetResponse get(WxMaOrderShippingInfoGetRequest request) throws WxErrorException {
    return request(GET_SHIPPING_INFO, request, WxMaOrderShippingInfoGetResponse.class);
  }

  /**
   * 查询订单列表
   * 你可以通过支付时间、支付者openid或订单状态来查询订单列表。
   *
   * @param request 请求
   * @return WxMaOrderShippingInfoGetListResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingInfoGetListResponse getList(WxMaOrderShippingInfoGetListRequest request) throws WxErrorException {
    return request(GET_SHIPPING_INFO_LIST, request, WxMaOrderShippingInfoGetListResponse.class);
  }

  /**
   * 确认收货提醒接口
   * 如你已经从你的快递物流服务方获知到用户已经签收相关商品，可以通过该接口提醒用户及时确认收货，以提高资金结算效率，每个订单仅可调用一次。
   *
   * @param request 请求
   * @return WxMaOrderShippingInfoBaseResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingInfoBaseResponse notifyConfirmReceive(WxMaOrderShippingInfoNotifyConfirmRequest request) throws WxErrorException {
    return request(NOTIFY_CONFIRM_RECEIVE, request, WxMaOrderShippingInfoBaseResponse.class);
  }

  /**
   * 消息跳转路径设置接口
   * 如你已经在小程序内接入平台提供的确认收货组件，可以通过该接口设置发货消息及确认收货消息的跳转动作，用户点击发货消息时会直接进入你的小程序订单列表页面或详情页面进行确认收货，进一步优化用户体验。
   *
   * @param path 商户自定义跳转路径
   * @return WxMaOrderShippingInfoBaseResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingInfoBaseResponse setMsgJumpPath(String path) throws WxErrorException {
    JsonObject jsonObject = GsonHelper.buildJsonObject("path", path);
    return request(SET_MSG_JUMP_PATH, jsonObject, WxMaOrderShippingInfoBaseResponse.class);
  }

  /**
   * 查询小程序是否已完成交易结算管理确认
   *
   * @param appId 待查询小程序的 appid，非服务商调用时仅能查询本账号
   * @return WxMaOrderShippingITMCCompletedResult
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingITMCCompletedResult isTradeManagementConfirmationCompleted(String appId) throws WxErrorException {
    JsonObject jsonObject = GsonHelper.buildJsonObject("appid", appId);
    return request(IS_TRADE_MANAGEMENT_CONFIRMATION_COMPLETED, jsonObject, WxMaOrderShippingITMCCompletedResult.class);
  }

  /**
   * 特殊发货报备
   *
   * @param orderId 需要特殊发货报备的订单号，可传入微信支付单号或商户单号
   * @param type    特殊发货报备类型，1为预售商品订单，2为测试订单
   * @param delayTo 预计发货时间的unix时间戳，type为1时必填，type为2可省略
   * @return WxMaOrderShippingInfoBaseResponse
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderShippingInfoBaseResponse opSpecialOrder(String orderId, Integer type, Long delayTo) throws WxErrorException {
    JsonObject jsonObject = GsonHelper.buildJsonObject("order_id", orderId, "type", type, "delay_to", delayTo);
    return request(OP_SPECIAL_ORDER, jsonObject, WxMaOrderShippingInfoBaseResponse.class);
  }

  private <T> T request(String url, Object request, Class<T> resultT) throws WxErrorException {
    String responseContent = this.wxMaService.post(url, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, resultT);
  }
}
