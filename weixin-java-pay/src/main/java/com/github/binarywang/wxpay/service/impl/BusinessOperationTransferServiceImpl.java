package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BusinessOperationTransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.cert.X509Certificate;

/**
 * 运营工具-商家转账API实现
 *
 * @author WxJava Team
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
 */
@Slf4j
@RequiredArgsConstructor
public class BusinessOperationTransferServiceImpl implements BusinessOperationTransferService {

  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService wxPayService;

  @Override
  public BusinessOperationTransferResult createOperationTransfer(BusinessOperationTransferRequest request) throws WxPayException {
    // 设置默认appid
    if (StringUtils.isEmpty(request.getAppid())) {
      request.setAppid(this.wxPayService.getConfig().getAppId());
    }

    String url = String.format("%s/v3/fund-app/operation/mch-transfer/transfer-bills", this.wxPayService.getPayBaseUrl());
    
    // 如果传入了用户姓名，需要进行RSA加密
    if (StringUtils.isNotEmpty(request.getUserName())) {
      X509Certificate validCertificate = this.wxPayService.getConfig().getVerifier().getValidCertificate();
      RsaCryptoUtil.encryptFields(request, validCertificate);
    }

    String response = wxPayService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(response, BusinessOperationTransferResult.class);
  }

  @Override
  public BusinessOperationTransferQueryResult queryOperationTransfer(BusinessOperationTransferQueryRequest request) throws WxPayException {
    if (StringUtils.isNotEmpty(request.getOutBillNo())) {
      return queryOperationTransferByOutBillNo(request.getOutBillNo());
    } else if (StringUtils.isNotEmpty(request.getTransferBillNo())) {
      return queryOperationTransferByTransferBillNo(request.getTransferBillNo());
    } else {
      throw new WxPayException("商户单号(out_bill_no)和微信转账单号(transfer_bill_no)必须提供其中一个");
    }
  }

  @Override
  public BusinessOperationTransferQueryResult queryOperationTransferByOutBillNo(String outBillNo) throws WxPayException {
    String url = String.format("%s/v3/fund-app/operation/mch-transfer/transfer-bills/out-bill-no/%s",
      this.wxPayService.getPayBaseUrl(), outBillNo);
    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BusinessOperationTransferQueryResult.class);
  }

  @Override
  public BusinessOperationTransferQueryResult queryOperationTransferByTransferBillNo(String transferBillNo) throws WxPayException {
    String url = String.format("%s/v3/fund-app/operation/mch-transfer/transfer-bills/transfer-bill-no/%s",
      this.wxPayService.getPayBaseUrl(), transferBillNo);
    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BusinessOperationTransferQueryResult.class);
  }
}