package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.businesscircle.BusinessCircleNotifyData;
import com.github.binarywang.wxpay.bean.businesscircle.PaidResult;
import com.github.binarywang.wxpay.bean.businesscircle.PointsNotifyRequest;
import com.github.binarywang.wxpay.bean.businesscircle.RefundResult;
import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 微信支付智慧商圈API
 * </pre>
 *
 * @author thinsstar
 */
public interface BusinessCircleService {
  /**
   * <pre>
   * 智慧商圈接口-商圈积分同步API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/businesscircle/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/businesscircle/points/notify
   * </pre>
   *
   * @param request 请求对象
   * @throws WxPayException the wx pay exception
   */
  void notifyPoints(PointsNotifyRequest request) throws WxPayException;

  BusinessCircleNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException;

  PaidResult decryptPaidNotifyDataResource(BusinessCircleNotifyData data) throws WxPayException;

  RefundResult decryptRefundNotifyDataResource(BusinessCircleNotifyData data) throws WxPayException;
}
