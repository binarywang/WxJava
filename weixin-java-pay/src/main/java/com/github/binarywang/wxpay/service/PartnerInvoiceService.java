package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.invoice.InviteUrlResult;
import com.github.binarywang.wxpay.bean.invoice.GeneralInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceResult;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileResult;
import com.github.binarywang.wxpay.bean.invoice.ReverseInvoiceRequest;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 微信支付服务商电子发票 API。
 *
 * @author binarywang
 */
public interface PartnerInvoiceService {

  /**
   * 获取开通服务商电子发票能力邀请链接。
   *
   * @param subMchId 可选，指定要邀请开通能力的子商户号
   * @return 邀请链接
   * @throws WxPayException 微信支付异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4015941495">官方文档</a>
   */
  InviteUrlResult getInviteUrl(String subMchId) throws WxPayException;

  /**
   * 开具通用行业电子发票。接口受理成功后，请通过查询电子发票接口获取处理结果。
   *
   * @param request 开票申请
   * @throws WxPayException 微信支付异常
   * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4015792574">官方文档</a>
   */
  void issueGeneralInvoice(GeneralInvoiceRequest request) throws WxPayException;

  /**
   * 查询电子发票。
   *
   * @param fapiaoApplyId 开票申请单号
   * @param subMchId 子商户号
   * @param fapiaoId 可选，商户发票单号
   * @return 发票信息
   * @throws WxPayException 微信支付异常
   */
  InvoiceResult getInvoice(String fapiaoApplyId, String subMchId, String fapiaoId) throws WxPayException;

  /**
   * 冲红电子发票。
   *
   * @param request 冲红申请
   * @throws WxPayException 微信支付异常
   */
  void reverseInvoice(ReverseInvoiceRequest request) throws WxPayException;

  /**
   * 获取发票文件下载信息。
   *
   * @param fapiaoApplyId 开票申请单号
   * @param subMchId 子商户号
   * @param fapiaoId 可选，商户发票单号
   * @return 发票文件下载信息
   * @throws WxPayException 微信支付异常
   */
  InvoiceFileResult getInvoiceFileDownloadInfo(String fapiaoApplyId, String subMchId, String fapiaoId) throws WxPayException;
}
