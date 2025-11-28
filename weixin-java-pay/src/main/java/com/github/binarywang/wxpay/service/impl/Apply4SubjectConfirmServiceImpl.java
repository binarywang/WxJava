package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmCreateRequest;
import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmCreateResult;
import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmMerchantStateQueryResult;
import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmStateQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.Apply4SubjectConfirmService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * <pre>
 * 商户开户意愿确认
 * 产品文档：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/open/pay/chapter6_1_1.shtml">商户开户意愿确认流程</a>
 * </pre>
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
@Slf4j
@RequiredArgsConstructor
public class Apply4SubjectConfirmServiceImpl implements Apply4SubjectConfirmService {

  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  /**
   * <pre>
   * 提交申请单
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_1.shtml">间连商户开户意愿确认（提交申请单）</a>
   * </pre>
   *
   * @param request 申请请求参数
   * @return 审核结果
   * @throws WxPayException 异常
   */
  @Override
  public ApplySubjectConfirmCreateResult applyment(ApplySubjectConfirmCreateRequest request) throws WxPayException {
    String url = String.format("%s/v3/apply4subject/applyment", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ApplySubjectConfirmCreateResult.class);
  }

  /**
   * <pre>
   * 查询申请单审核结果
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_3.shtml">查询申请单审核结果</a>
   * </pre>
   *
   * @param businessCode 业务申请编号
   * @return 审核结果
   * @throws WxPayException 异常
   */
  @Override
  public ApplySubjectConfirmStateQueryResult queryApplyStatusByBusinessCode(String businessCode) throws WxPayException {
    String url = String.format("%s/v3/apply4subject/applyment?business_code=%s", this.payService.getPayBaseUrl(), businessCode);
    String result = payService.getV3(url);
    return GSON.fromJson(result, ApplySubjectConfirmStateQueryResult.class);
  }

  /**
   * <pre>
   * 查询申请单审核结果
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_3.shtml">查询申请单审核结果</a>
   * </pre>
   *
   * @param applymentId 申请编号
   * @return 审核结果
   * @throws WxPayException 异常
   */
  @Override
  public ApplySubjectConfirmStateQueryResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException {
    String url = String.format("%s/v3/apply4subject/applyment?applyment_id=%s", this.payService.getPayBaseUrl(), applymentId);
    String result = payService.getV3(url);
    return GSON.fromJson(result, ApplySubjectConfirmStateQueryResult.class);
  }

  /**
   * <pre>
   * 获取商户开户意愿确认状态
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_4.shtml">获取商户开户意愿确认状态API</a>
   * </pre>
   *
   * @param subMchId 微信支付分配的特约商户的唯一标识。
   * @return 确认状态结果
   * @throws WxPayException 异常
   */
  @Override
  public ApplySubjectConfirmMerchantStateQueryResult queryMerchantApplyStatusByMchId(String subMchId) throws WxPayException {
    String url = String.format("%s/v3/apply4subject/applyment/merchants/%s/state", this.payService.getPayBaseUrl(), subMchId);
    String result = payService.getV3(url);
    return GSON.fromJson(result, ApplySubjectConfirmMerchantStateQueryResult.class);
  }

  /**
   * <pre>
   * 撤销申请单
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_2.shtml">撤销申请单API</a>
   * </pre>
   *
   * @param businessCode 业务申请编号
   * @return 返回结果
   * @throws WxPayException 异常
   */
  @Override
  public void cancelApplyByBusinessCode(String businessCode) throws WxPayException {
    String url = String.format("%s/v3/apply4subject/applyment/%s/cancel", this.payService.getPayBaseUrl(), businessCode);
    payService.postV3WithWechatpaySerial(url, "");
  }

  /**
   * <pre>
   * 撤销申请单
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_2.shtml">撤销申请单API</a>
   * </pre>
   *
   * @param applymentId 申请编号
   * @return 返回结果
   * @throws WxPayException 异常
   */
  @Override
  public void cancelApplyByApplymentId(String applymentId) throws WxPayException {
    String url = String.format("%s/v3/apply4subject/applyment/%s/cancel", this.payService.getPayBaseUrl(), applymentId);
    payService.postV3WithWechatpaySerial(url, "");
  }
}
