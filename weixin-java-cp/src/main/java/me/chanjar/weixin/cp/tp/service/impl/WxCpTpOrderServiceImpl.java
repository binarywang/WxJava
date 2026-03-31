package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.order.WxCpTpOrderDetails;
import me.chanjar.weixin.cp.bean.order.WxCpTpOrderListGetResult;
import me.chanjar.weixin.cp.tp.service.WxCpTpOrderService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.util.Date;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_ORDER;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_ORDER_LIST;

/**
 * 应用版本付费订单相关接口实现
 *
 * @author leigouqing  created on  2022年4月24日
 */
@RequiredArgsConstructor
public class WxCpTpOrderServiceImpl implements WxCpTpOrderService {

  /**
   * The Main service.
   */
  private final WxCpTpService mainService;

  /**
   * 获取订单详情
   * <p>
   * <a href='https://developer.work.weixin.qq.com/document/15219#%E8%8E%B7%E5%8F%96%E8%AE%A2%E5%8D%95%E8%AF%A6%E6%83%85'>文档地址</a>
   * </p>
   *
   * @param orderId 订单号
   * @return the order
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpTpOrderDetails getOrder(String orderId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(GET_ORDER);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("orderid", orderId);
    String result = this.mainService.post(url, jsonObject.toString());
    return WxCpTpOrderDetails.fromJson(result);
  }

  /**
   * 获取订单列表
   * <p>
   * <a href='https://developer.work.weixin.qq.com/document/15219#%E8%8E%B7%E5%8F%96%E8%AE%A2%E5%8D%95%E5%88%97%E8%A1%A8'>文档地址</a>
   * </p>
   *
   * @param startTime 起始时间
   * @param endTime   终止时间
   * @param testMode  指定拉取正式或测试模式的订单。默认正式模式。0-正式模式，1-测试模式。
   * @return the order list
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpTpOrderListGetResult getOrderList(Date startTime, Date endTime, Integer testMode) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(GET_ORDER_LIST);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("start_time", startTime.getTime() / 1000);
    jsonObject.addProperty("end_time", endTime.getTime() / 1000);
    jsonObject.addProperty("test_mode", testMode);
    String result = this.mainService.post(url, jsonObject.toString());
    return WxCpTpOrderListGetResult.fromJson(result);
  }
}
