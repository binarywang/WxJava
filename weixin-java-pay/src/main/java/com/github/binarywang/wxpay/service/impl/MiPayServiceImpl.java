package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersRequest;
import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersResult;
import com.github.binarywang.wxpay.bean.mipay.MedInsRefundNotifyRequest;
import com.github.binarywang.wxpay.bean.notify.MiPayNotifyV3Result;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MiPayService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.security.cert.X509Certificate;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012503131">医保相关接口</a>
 * 医保相关接口
 * @author xgl
 * @date 2025/12/20
 */
@RequiredArgsConstructor
public class MiPayServiceImpl implements MiPayService {

  private final WxPayService payService;
  private static final Gson GSON = new GsonBuilder().create();


  @Override
  public MedInsOrdersResult medInsOrders(MedInsOrdersRequest request) throws WxPayException {

    String url = String.format("%s/v3/med-ins/orders", this.payService.getPayBaseUrl());
    X509Certificate validCertificate = this.payService.getConfig().getVerifier().getValidCertificate();

    RsaCryptoUtil.encryptFields(request, validCertificate);

    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, MedInsOrdersResult.class);
  }

  @Override
  public MedInsOrdersResult getMedInsOrderByMixTradeNo(String mixTradeNo, String subMchid) throws WxPayException {
    String url = String.format("%s/v3/med-ins/orders/mix-trade-no/%s?sub_mchid=%s", this.payService.getPayBaseUrl(), mixTradeNo, subMchid);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, MedInsOrdersResult.class);
  }

  @Override
  public MedInsOrdersResult getMedInsOrderByOutTradeNo(String outTradeNo, String subMchid) throws WxPayException {
    String url = String.format("%s/v3/med-ins/orders/out-trade-no/%s?sub_mchid=%s", this.payService.getPayBaseUrl(), outTradeNo, subMchid);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, MedInsOrdersResult.class);
  }

  @Override
  public MiPayNotifyV3Result parseMiPayNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException {
    return this.payService.baseParseOrderNotifyV3Result(notifyData, header, MiPayNotifyV3Result.class, MiPayNotifyV3Result.DecryptNotifyResult.class);
  }

  @Override
  public void medInsRefundNotify(MedInsRefundNotifyRequest request, String mixTradeNo) throws WxPayException {
    String url = String.format("%s/v3/med-ins/refunds/notify?mix_trade_no=%s", this.payService.getPayBaseUrl(), mixTradeNo);
    this.payService.postV3(url, GSON.toJson(request));
  }


}
