package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.order.WxCpTpOrderDetails;
import me.chanjar.weixin.cp.bean.order.WxCpTpOrderListGetResult;

import java.util.Date;


/**
 * 应用版本付费订单相关接口
 *
 * @author leiguoqing  created on  2022年4月24日
 */
public interface WxCpTpOrderService {

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
  WxCpTpOrderDetails getOrder(String orderId) throws WxErrorException;


  /**
   * 获取订单列表
   * <p>
   * <a href='https://developer.work.weixin.qq.com/document/15219#%E8%8E%B7%E5%8F%96%E8%AE%A2%E5%8D%95%E5%88%97%E8%A1%A8'>文档地址</a>
   * </p>
   *
   * @param startTime 起始时间
   * @param endTime   终止时间
   * @param testMode  指定拉取正式或测试模式的订单。默认正式模式。0-正式模式，1-测试模式。
   * @return the order
   * @throws WxErrorException the wx error exception
   */
  WxCpTpOrderListGetResult getOrderList(Date startTime, Date endTime, Integer testMode) throws WxErrorException;
}
