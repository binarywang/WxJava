package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.merchantlimitation.MerchantLimitationResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MerchantLimitationService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

/**
 * 商户被管控能力及原因查询 接口实现
 *
 * @author zhangyl
 */
@RequiredArgsConstructor
public class MerchantLimitationServiceImpl implements MerchantLimitationService {
  private final WxPayService payService;
  private static final Gson GSON = new GsonBuilder().create();

  @Override
  public MerchantLimitationResult fetchLimitations(String subMchId) throws WxPayException {
    String url = String.format("%s/v3/mch-operation-manage/merchant-limitations/sub-mchid/%s",
      this.payService.getPayBaseUrl(), subMchId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, MerchantLimitationResult.class);
  }
}
