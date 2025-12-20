package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersRequest;
import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersResult;
import com.github.binarywang.wxpay.bean.mipay.MedInsRefundNotifyRequest;
import com.github.binarywang.wxpay.bean.notify.MiPayNotifyV3Result;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
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

  /**
   * <pre>
   * 使用医保自费混合订单号查看下单结果
   *
   * 从业机构使用混合下单订单号，通过该接口主动查询订单状态，完成下一步的业务逻辑。
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/partner/4012503155">使用医保自费混合订单号查看下单结果</a>
   * </pre>
   *
   * @param mixTradeNo 医保自费混合订单号
   * @param subMchid   医疗机构的商户号
   * @return MedInsOrdersResult 下单结果
   * @throws WxPayException the wx pay exception
   */
  MedInsOrdersResult getMedInsOrderByMixTradeNo(String mixTradeNo, String subMchid) throws WxPayException;

  /**
   * <pre>
   * 使用从业机构订单号查看下单结果
   *
   * 从业机构使用从业机构订单号、医疗机构商户号，通过该接口主动查询订单状态，完成下一步的业务逻辑。
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/partner/4012503286">使用从业机构订单号查看下单结果</a>
   * </pre>
   *
   * @param outTradeNo 从业机构订单号
   * @param subMchid   医疗机构的商户号
   * @return MedInsOrdersResult 下单结果
   * @throws WxPayException the wx pay exception
   */
  MedInsOrdersResult getMedInsOrderByOutTradeNo(String outTradeNo, String subMchid) throws WxPayException;

  /**
   * <pre>
   * 解析医保混合收款成功通知
   *
   * 微信支付会通过POST请求向商户设置的回调URL推送医保混合收款成功通知，商户需要接收处理该消息，并返回应答。
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/partner/4012165722">医保混合收款成功通知</a>
   * </pre>
   *
   * @param notifyData 通知数据字符串
   * @return MiPayNotifyV3Result 医保混合收款成功通知结果
   * @throws WxPayException the wx pay exception
   */
  MiPayNotifyV3Result parseMiPayNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 医保退款通知
   *
   * 从业机构调用该接口向微信医保后台通知医保订单的退款成功结果
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/partner/4012166534">医保退款通知</a>
   * </pre>
   *
   * @param request 医保退款通知请求参数
   * @throws WxPayException the wx pay exception
   */
  void medInsRefundNotify(MedInsRefundNotifyRequest request) throws WxPayException;

}
