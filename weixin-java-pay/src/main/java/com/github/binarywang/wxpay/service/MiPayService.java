package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersRequest;
import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012503131">医保相关接口</a>
 * 医保相关接口
 * @author xgl
 * @date 2025/12/20
 */
public interface MiPayService {

  /**
   * <pre>
   * 医保自费混合收款下单
   *
   * 从业机构调用该接口向微信医保后台下单
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/partner/4012503131">医保自费混合收款下单</a>
   * </pre>
   *
   * @param request 下单参数
   * @return ReservationTransferNotifyResult 下单结果
   * @throws WxPayException the wx pay exception
   */
  MedInsOrdersResult medInsOrders(MedInsOrdersRequest request) throws WxPayException;





}
