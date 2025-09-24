package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxDepositService;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *   微信押金支付服务实现
 * </pre>
 *
 * @author Binary Wang
 * created on  2024-09-24
 */
@Slf4j
@RequiredArgsConstructor
public class WxDepositServiceImpl implements WxDepositService {

  private final WxPayService payService;

  @Override
  public WxDepositUnifiedOrderResult unifiedOrder(WxDepositUnifiedOrderRequest request) throws WxPayException {
    request.checkAndSign(payService.getConfig());

    String url = payService.getPayBaseUrl() + "/pay/depositpay";
    String responseContent = payService.post(url, request.toXML(), false);
    WxDepositUnifiedOrderResult result = BaseWxPayResult.fromXML(responseContent, WxDepositUnifiedOrderResult.class);
    result.checkResult(payService, request.getSignType(), true);

    return result;
  }

  @Override
  public WxDepositOrderQueryResult queryOrder(WxDepositOrderQueryRequest request) throws WxPayException {
    request.checkAndSign(payService.getConfig());

    String url = payService.getPayBaseUrl() + "/pay/depositorderquery";
    String responseContent = payService.post(url, request.toXML(), false);
    WxDepositOrderQueryResult result = BaseWxPayResult.fromXML(responseContent, WxDepositOrderQueryResult.class);
    result.checkResult(payService, request.getSignType(), true);

    return result;
  }

  @Override
  public WxDepositConsumeResult consume(WxDepositConsumeRequest request) throws WxPayException {
    request.checkAndSign(payService.getConfig());

    String url = payService.getPayBaseUrl() + "/pay/depositconsume";
    String responseContent = payService.post(url, request.toXML(), false);
    WxDepositConsumeResult result = BaseWxPayResult.fromXML(responseContent, WxDepositConsumeResult.class);
    result.checkResult(payService, request.getSignType(), true);

    return result;
  }

  @Override
  public WxDepositUnfreezeResult unfreeze(WxDepositUnfreezeRequest request) throws WxPayException {
    request.checkAndSign(payService.getConfig());

    String url = payService.getPayBaseUrl() + "/pay/depositreverse";
    String responseContent = payService.post(url, request.toXML(), false);
    WxDepositUnfreezeResult result = BaseWxPayResult.fromXML(responseContent, WxDepositUnfreezeResult.class);
    result.checkResult(payService, request.getSignType(), true);

    return result;
  }

  @Override
  public WxDepositRefundResult refund(WxDepositRefundRequest request) throws WxPayException {
    request.checkAndSign(payService.getConfig());

    String url = payService.getPayBaseUrl() + "/pay/depositrefund";
    String responseContent = payService.post(url, request.toXML(), true);
    WxDepositRefundResult result = BaseWxPayResult.fromXML(responseContent, WxDepositRefundResult.class);
    result.checkResult(payService, request.getSignType(), true);

    return result;
  }
}