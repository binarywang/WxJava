package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaOrderManagementService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.order.WxMaOrderManagementGetOrderDetailPath;
import cn.binarywang.wx.miniapp.bean.order.WxMaOrderManagementResult;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaOrderShippingInfoBaseResponse;
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

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.OrderManagement.*;


/**
 * @author xzh
 * @Description
 * @createTime 2025/01/16 15:31
 */
@Slf4j
@RequiredArgsConstructor
public class WxMaOrderManagementServiceImpl implements WxMaOrderManagementService {

  private final WxMaService wxMaService;

  /**
   * 查询订单详情路径
   * 注意事项
   * 如果没有配置过订单详情路径，会返回成功，其中path为''。
   *
   * @return WxMaOrderManagementGetOrderDetailPath
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderManagementGetOrderDetailPath getOrderDetailPath() throws WxErrorException {
    return request(GET_ORDER_DETAIL_PATH, new Object(), WxMaOrderManagementGetOrderDetailPath.class);

  }

  /**
   * 配置订单详情路径
   * 注意事项
   * 调用接口前需要先完成订单中心授权协议签署。
   * 请确保配置的path可正常跳转到小程序，并且path必须包含字符串“${商品订单号}”。
   *
   * @param path 订单详情路径
   * @return WxMaOrderManagementResult
   * @throws WxErrorException e
   */
  @Override
  public WxMaOrderManagementResult updateOrderDetailPath(String path) throws WxErrorException {
    JsonObject jsonObject = GsonHelper.buildJsonObject("path", path);
    return request(UPDATE_ORDER_DETAIL_PATH, jsonObject, WxMaOrderManagementResult.class);

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
