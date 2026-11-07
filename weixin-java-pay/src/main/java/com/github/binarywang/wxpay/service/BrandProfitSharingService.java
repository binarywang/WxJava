package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.brandprofitsharing.*;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.profitsharing.notify.ProfitSharingNotifyV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 连锁品牌分账 API v3，区别于普通服务商分账接口。
 * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012072637">官方开发指引</a>
 */
public interface BrandProfitSharingService {
  /**
   * 请求分账；异步受理，最终结果需查询。
   *
   * @param request 接口请求参数
   * @return 分账受理结果
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012692975">官方接口文档</a>
   */
  BrandProfitSharingResult profitSharing(BrandProfitSharingRequest request) throws WxPayException;

  /**
   * 查询分账或完结分账结果。
   *
   * @param request 接口请求参数
   * @return 分账执行结果
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467002">官方接口文档</a>
   */
  BrandProfitSharingResult query(BrandProfitSharingQueryRequest request) throws WxPayException;

  /**
   * 请求回退已分给商户接收方的资金。
   *
   * @param request 接口请求参数
   * @return 回退受理结果
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467097">官方接口文档</a>
   */
  BrandProfitSharingReturnResult returnOrder(BrandProfitSharingReturnRequest request) throws WxPayException;

  /**
   * 查询分账回退结果。
   *
   * @param request 接口请求参数
   * @return 回退执行结果
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467011">官方接口文档</a>
   */
  BrandProfitSharingReturnResult queryReturn(BrandProfitSharingReturnQueryRequest request) throws WxPayException;

  /**
   * 完结分账，解冻订单剩余资金。
   *
   * @param request 接口请求参数
   * @return 完结受理结果
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467016">官方接口文档</a>
   */
  BrandProfitSharingFinishResult finish(BrandProfitSharingFinishRequest request) throws WxPayException;

  /**
   * 查询订单剩余待分账金额（分）。
   *
   * @param transactionId 微信支付订单号
   * @return 剩余待分账金额
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467021">官方接口文档</a>
   */
  BrandProfitSharingAmountResult queryRemainingAmount(String transactionId) throws WxPayException;

  /**
   * 查询品牌最大分账比例（万分之一）。
   *
   * @param brandMchId 品牌主商户号
   * @return 品牌最大分账比例
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467022">官方接口文档</a>
   */
  BrandProfitSharingRatioResult queryMaxRatio(String brandMchId) throws WxPayException;

  /**
   * 添加品牌接收方。
   *
   * @param request 接口请求参数
   * @return 接收方添加结果
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467100">官方接口文档</a>
   */
  BrandProfitSharingReceiverResult addReceiver(BrandProfitSharingReceiverRequest request) throws WxPayException;

  /**
   * 删除品牌接收方。
   *
   * @param request 接口请求参数
   * @return 接收方删除结果
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012467103">官方接口文档</a>
   */
  BrandProfitSharingReceiverResult deleteReceiver(BrandProfitSharingReceiverDeleteRequest request) throws WxPayException;

  /**
   * 验签并解密分账动账通知；请传入完整通知签名头。
   *
   * @param notifyData 原始通知 JSON
   * @param header 微信支付通知签名头，不能为空
   * @return 解密后的分账动账通知
   * @throws WxPayException 微信支付接口、加密或验签异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4012075400">官方接口文档</a>
   */
  ProfitSharingNotifyV3Result parseNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;
}
