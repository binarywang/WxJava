package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.profitsharing.notify.ProfitSharingNotifyV3Response;
import com.github.binarywang.wxpay.bean.profitsharing.notify.ProfitSharingNotifyV3Result;
import com.github.binarywang.wxpay.bean.profitsharing.request.*;
import com.github.binarywang.wxpay.bean.profitsharing.result.*;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.ProfitSharingService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.auth.Verifier;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * @author Wang GuangXin 2019/10/22 10:13
 * @version 1.0
 */
public class ProfitSharingServiceImpl implements ProfitSharingService {
  private WxPayService payService;
  private static final Gson GSON = new GsonBuilder().create();

  public ProfitSharingServiceImpl(WxPayService payService) {
    this.payService = payService;
  }

  @Override
  public ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharing";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingResult multiProfitSharing(ProfitSharingRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/multiprofitsharing";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingV3Result profitSharingV3(ProfitSharingV3Request request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingV3Result.class);
  }

  @Override
  public ProfitSharingResult profitSharingFinish(ProfitSharingUnfreezeRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharingfinish";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReceiverResult addReceiver(ProfitSharingReceiverRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingaddreceiver";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingReceiverResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReceiverResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReceiverResult removeReceiver(ProfitSharingReceiverRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingremovereceiver";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingReceiverResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReceiverResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReceiverV3Result addReceiverV3(ProfitSharingReceiverV3Request request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/receivers/add", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReceiverV3Result.class);
  }

  @Override
  public ProfitSharingReceiverV3Result removeReceiverV3(ProfitSharingReceiverV3Request request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/receivers/delete", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReceiverV3Result.class);
  }

  @Override
  public ProfitSharingQueryResult profitSharingQuery(ProfitSharingQueryRequest request) throws WxPayException {
    request.setAppid(null);

    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingquery";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingQueryResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingQueryResult.class);
    result.formatReceivers();
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingV3Result profitSharingQueryV3(String outOrderNo, String transactionId) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/%s?transaction_id=%s", this.payService.getPayBaseUrl(),
      outOrderNo, transactionId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingV3Result.class);
  }

  @Override
  public ProfitSharingV3Result profitSharingQueryV3(String outOrderNo, String transactionId, String subMchId)
    throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/%s?sub_mchid=%s&transaction_id=%s",
      this.payService.getPayBaseUrl(), outOrderNo, subMchId, transactionId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingV3Result.class);
  }

  @Override
  public ProfitSharingV3Result profitSharingQueryV3(ProfitSharingQueryV3Request request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/%s?transaction_id=%s", this.payService.getPayBaseUrl(),
      request.getOutOrderNo(), request.getTransactionId());
    if(StringUtils.isNotEmpty(request.getSubMchId())){
      url += "&sub_mchid=" + request.getSubMchId();
    }
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingV3Result.class);
  }

  @Override
  public ProfitSharingOrderAmountQueryResult profitSharingOrderAmountQuery(ProfitSharingOrderAmountQueryRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingorderamountquery";

    final String responseContent = payService.post(url, request.toXML(), true);
    ProfitSharingOrderAmountQueryResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingOrderAmountQueryResult.class);
    result.checkResult(payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingOrderAmountQueryV3Result profitSharingUnsplitAmountQueryV3(String transactionId) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/transactions/%s/amounts", this.payService.getPayBaseUrl(), transactionId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingOrderAmountQueryV3Result.class);
  }

  @Override
  public ProfitSharingMerchantRatioQueryResult profitSharingMerchantRatioQuery(ProfitSharingMerchantRatioQueryRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingmerchantratioquery";

    final String responseContent = payService.post(url, request.toXML(), true);
    ProfitSharingMerchantRatioQueryResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingMerchantRatioQueryResult.class);
    result.checkResult(payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingMerchantRatioQueryV3Result profitSharingMerchantRatioQueryV3(String subMchId) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/merchant-configs/%s", this.payService.getPayBaseUrl(), subMchId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingMerchantRatioQueryV3Result.class);
  }

  @Override
  public ProfitSharingReturnResult profitSharingReturn(ProfitSharingReturnRequest returnRequest) throws WxPayException {
    returnRequest.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharingreturn";

    String responseContent = this.payService.post(url, returnRequest.toXML(), true);
    ProfitSharingReturnResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReturnResult.class);
    result.checkResult(this.payService, returnRequest.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReturnV3Result profitSharingReturnV3(ProfitSharingReturnV3Request request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/return-orders", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReturnV3Result.class);
  }

  @Override
  public ProfitSharingReturnResult profitSharingReturnQuery(ProfitSharingReturnQueryRequest queryRequest) throws WxPayException {
    queryRequest.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingreturnquery";

    String responseContent = this.payService.post(url, queryRequest.toXML(), true);
    ProfitSharingReturnResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReturnResult.class);
    result.checkResult(this.payService, queryRequest.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReturnV3Result profitSharingReturnQueryV3(String outOrderNo, String outReturnNo) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/return-orders/%s?out_order_no=%s", this.payService.getPayBaseUrl(),
      outReturnNo, outOrderNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingReturnV3Result.class);
  }

  @Override
  public ProfitSharingReturnV3Result profitSharingReturnQueryV3(String outOrderNo, String outReturnNo, String subMchId)
    throws WxPayException {
    String url = String.format("%s/v3/profitsharing/return-orders/%s?sub_mchid=%s&out_order_no=%s",
      this.payService.getPayBaseUrl(), outReturnNo, subMchId, outOrderNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingReturnV3Result.class);
  }

  @Override
  public ProfitSharingUnfreezeV3Result profitSharingUnfreeze(ProfitSharingUnfreezeV3Request request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/unfreeze", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingUnfreezeV3Result.class);
  }

  @Override
  public ProfitSharingNotifyV3Result parseProfitSharingNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    ProfitSharingNotifyV3Response response = parseNotifyData(notifyData, header);
    ProfitSharingNotifyV3Response.Resource resource = response.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key);
      return GSON.fromJson(result, ProfitSharingNotifyV3Result.class);
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public ProfitSharingBillV3Result profitSharingBill(ProfitSharingBillV3Request request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/bills?bill_date=%s", this.payService.getPayBaseUrl(),
      request.getBillDate());

    if (StringUtils.isNotBlank(request.getSubMchId())) {
      url = String.format("%s&sub_mchid=%s", url, request.getSubMchId());
    }
    if (StringUtils.isNotBlank(request.getTarType())) {
      url = String.format("%s&tar_type=%s", url, request.getTarType());
    }
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingBillV3Result.class);
  }



  private ProfitSharingNotifyV3Response parseNotifyData(String data, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, data)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    return GSON.fromJson(data, ProfitSharingNotifyV3Response.class);
  }

  /**
   * 校验通知签名
   *
   * @param header 通知头信息
   * @param data   通知数据
   * @return true:校验通过 false:校验不通过
   */
  private boolean verifyNotifySign(SignatureHeader header, String data) throws WxPayException {
    String beforeSign = String.format("%s%n%s%n%s%n", header.getTimeStamp(), header.getNonce(), data);
    Verifier verifier = this.payService.getConfig().getVerifier();
    if (verifier == null) {
      throw new WxPayException("证书检验对象为空");
    }
    return verifier.verify(header.getSerial(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSignature());
  }
}
