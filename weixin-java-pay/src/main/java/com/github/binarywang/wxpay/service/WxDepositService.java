package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.request.WxDepositConsumeRequest;
import com.github.binarywang.wxpay.bean.request.WxDepositOrderQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxDepositRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxDepositUnfreezeRequest;
import com.github.binarywang.wxpay.bean.request.WxDepositUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxDepositConsumeResult;
import com.github.binarywang.wxpay.bean.result.WxDepositOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxDepositRefundResult;
import com.github.binarywang.wxpay.bean.result.WxDepositUnfreezeResult;
import com.github.binarywang.wxpay.bean.result.WxDepositUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *   微信押金支付相关接口.
 *   <a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=1">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=1</a>
 * </pre>
 *
 * @author Binary Wang
 * created on  2024-09-24
 */
public interface WxDepositService {

  /**
   * <pre>
   *   押金下单
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=2">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=2</a>
   *   用于商户发起押金支付，支持JSAPI、NATIVE、APP等支付方式
   * </pre>
   *
   * @param request 押金下单请求对象
   * @return wx deposit unified order result
   * @throws WxPayException wx pay exception
   */
  WxDepositUnifiedOrderResult unifiedOrder(WxDepositUnifiedOrderRequest request) throws WxPayException;

  /**
   * <pre>
   *   查询押金订单
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=3">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=3</a>
   *   通过商户订单号或微信订单号查询押金订单状态
   * </pre>
   *
   * @param request 查询押金订单请求对象
   * @return wx deposit order query result
   * @throws WxPayException wx pay exception
   */
  WxDepositOrderQueryResult queryOrder(WxDepositOrderQueryRequest request) throws WxPayException;

  /**
   * <pre>
   *   押金消费
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=4">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=4</a>
   *   用于对已支付的押金进行消费扣减
   * </pre>
   *
   * @param request 押金消费请求对象
   * @return wx deposit consume result
   * @throws WxPayException wx pay exception
   */
  WxDepositConsumeResult consume(WxDepositConsumeRequest request) throws WxPayException;

  /**
   * <pre>
   *   押金撤销
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=5">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=5</a>
   *   用于对已支付的押金进行撤销退还
   * </pre>
   *
   * @param request 押金撤销请求对象
   * @return wx deposit unfreeze result
   * @throws WxPayException wx pay exception
   */
  WxDepositUnfreezeResult unfreeze(WxDepositUnfreezeRequest request) throws WxPayException;

  /**
   * <pre>
   *   押金退款
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=6">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=6</a>
   *   用于对已消费的押金进行退款
   * </pre>
   *
   * @param request 押金退款请求对象
   * @return wx deposit refund result
   * @throws WxPayException wx pay exception
   */
  WxDepositRefundResult refund(WxDepositRefundRequest request) throws WxPayException;
}