package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.invoice.InviteUrlResult;
import com.github.binarywang.wxpay.bean.invoice.InviteUrlRequest;
import com.github.binarywang.wxpay.bean.invoice.GeneralInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceResult;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileResult;
import com.github.binarywang.wxpay.bean.invoice.ReverseInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.SubMerchantInvoiceStatus;
import com.github.binarywang.wxpay.bean.invoice.CardTemplateRequest;
import com.github.binarywang.wxpay.bean.invoice.CardTemplateResult;
import com.github.binarywang.wxpay.bean.invoice.DevelopmentConfigRequest;
import com.github.binarywang.wxpay.bean.invoice.DevelopmentConfigResult;
import com.github.binarywang.wxpay.bean.invoice.TitleUrlRequest;
import com.github.binarywang.wxpay.bean.invoice.TitleUrlResult;
import com.github.binarywang.wxpay.bean.invoice.BuyerInformation;
import com.github.binarywang.wxpay.bean.invoice.IndustryInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InsertCardRequest;
import com.github.binarywang.wxpay.bean.invoice.InviteMerchantQuery;
import com.github.binarywang.wxpay.bean.invoice.InviteMerchantResult;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileUploadRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileUploadResult;
import java.io.IOException;
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

  InviteUrlResult getInviteUrl(InviteUrlRequest request) throws WxPayException;

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

  /**
   * 检查子商户开票功能状态。
   *
   * @param subMchId 子商户号
   * @return 子商户电子发票能力状态
   * @throws WxPayException 微信支付异常
   */
  SubMerchantInvoiceStatus getSubMerchantInvoiceStatus(String subMchId) throws WxPayException;

  CardTemplateResult createCardTemplate(CardTemplateRequest request) throws WxPayException;

  DevelopmentConfigResult updateDevelopmentConfig(DevelopmentConfigRequest request) throws WxPayException;

  TitleUrlResult getUserTitleUrl(TitleUrlRequest request) throws WxPayException;

  BuyerInformation getUserTitle(String subMchId, String scene, String fapiaoApplyId) throws WxPayException;

  void issueRealEstateLeasingInvoice(IndustryInvoiceRequest request) throws WxPayException;

  void issueRefinedOilInvoice(IndustryInvoiceRequest request) throws WxPayException;

  void insertCards(InsertCardRequest request) throws WxPayException;

  InviteMerchantResult listInviteMerchants(InviteMerchantQuery query) throws WxPayException;

  InvoiceFileUploadResult uploadInvoiceFile(InvoiceFileUploadRequest request) throws WxPayException, IOException;
}
