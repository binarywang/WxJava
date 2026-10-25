package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentCancelRequest;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentQueryResult;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentRequest;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BrandApplymentService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

/** 连锁品牌入驻申请 API 实现。 */
@RequiredArgsConstructor
public class BrandApplymentServiceImpl implements BrandApplymentService {
  private static final Gson GSON = new Gson();
  private final WxPayService payService;

  @Override
  public BrandApplymentResult createApplyment(BrandApplymentRequest request) throws WxPayException {
    BrandApplymentRequest encrypted = GSON.fromJson(GSON.toJson(request), BrandApplymentRequest.class);
    RsaCryptoUtil.encryptFields(encrypted, this.payService.getConfig().getVerifier().getValidCertificate());
    String response = this.payService.postV3WithWechatpaySerial(
      this.payService.getPayBaseUrl() + "/v3/brand/applyments", GSON.toJson(encrypted));
    return GSON.fromJson(response, BrandApplymentResult.class);
  }

  @Override
  public BrandApplymentResult cancelApplyment(BrandApplymentCancelRequest request) throws WxPayException {
    String response = this.payService.postV3(this.payService.getPayBaseUrl()
      + "/v3/brand/applyments/cancel-applyment", GSON.toJson(request));
    return GSON.fromJson(response, BrandApplymentResult.class);
  }

  @Override
  public BrandApplymentQueryResult queryApplymentByBusinessCode(String businessCode) throws WxPayException {
    String response = this.payService.getV3(this.payService.getPayBaseUrl()
      + "/v3/brand/applyments/business-code/" + businessCode);
    return GSON.fromJson(response, BrandApplymentQueryResult.class);
  }

  @Override
  public BrandApplymentQueryResult queryApplymentByApplymentId(String applymentId) throws WxPayException {
    String response = this.payService.getV3(this.payService.getPayBaseUrl()
      + "/v3/brand/applyments/applyment-id/" + applymentId);
    return GSON.fromJson(response, BrandApplymentQueryResult.class);
  }
}
