package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.*;
import com.github.binarywang.wxpay.bean.ecommerce.enums.FundBillTypeEnum;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.EcommerceService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.WechatPayUploadHttpPost;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.util.*;

@RequiredArgsConstructor
public class EcommerceServiceImpl implements EcommerceService {

  private static final Gson GSON = new GsonBuilder().create();

  // https://stackoverflow.com/questions/6873020/gson-date-format
  // gson default date format not match, so custom DateFormat
  // detail DateFormat: FULL,LONG,SHORT,MEDIUM
  private static final Gson GSON_CUSTOM = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
  private final WxPayService payService;

  @Override
  public ApplymentsResult createApply(ApplymentsRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/applyments/", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());

    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ApplymentsResult.class);
  }

  @Override
  public ApplymentsStatusResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/applyments/%s", this.payService.getPayBaseUrl(), applymentId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ApplymentsStatusResult.class);
  }

  @Override
  public ApplymentsStatusResult queryApplyStatusByOutRequestNo(String outRequestNo) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/applyments/out-request-no/%s", this.payService.getPayBaseUrl(), outRequestNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ApplymentsStatusResult.class);
  }

  @Override
  public TransactionsResult combine(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + tradeType.getCombineUrl();
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, TransactionsResult.class);
  }

  @Override
  public <T> T combineTransactions(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException {
    TransactionsResult result = this.combine(tradeType, request);
    return result.getPayInfo(tradeType, request.getCombineAppid(),
      request.getCombineMchid(), payService.getConfig().getPrivateKey());
  }

  @Override
  public CombineTransactionsNotifyResult parseCombineNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, notifyData)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    NotifyResponse response = GSON.fromJson(notifyData, NotifyResponse.class);
    NotifyResponse.Resource resource = response.getResource();
    String cipherText = resource.getCiphertext();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key);
      CombineTransactionsResult transactionsResult = GSON.fromJson(result, CombineTransactionsResult.class);

      CombineTransactionsNotifyResult notifyResult = new CombineTransactionsNotifyResult();
      notifyResult.setRawData(response);
      notifyResult.setResult(transactionsResult);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public CombineTransactionsResult queryCombineTransactions(String outTradeNo) throws WxPayException {
    String url = String.format("%s/v3/combine-transactions/out-trade-no/%s", this.payService.getPayBaseUrl(), outTradeNo);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, CombineTransactionsResult.class);
  }

  @Override
  public TransactionsResult partner(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + tradeType.getPartnerUrl();
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, TransactionsResult.class);
  }

  @Override
  public <T> T partnerTransactions(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException {
    TransactionsResult result = this.partner(tradeType, request);
    String appId = request.getSubAppid() != null ? request.getSubAppid() : request.getSpAppid();
    return result.getPayInfo(tradeType, appId,
      request.getSpMchid(), payService.getConfig().getPrivateKey());
  }

  @Override
  public PartnerTransactionsNotifyResult parsePartnerNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, notifyData)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    NotifyResponse response = GSON.fromJson(notifyData, NotifyResponse.class);
    NotifyResponse.Resource resource = response.getResource();
    String cipherText = resource.getCiphertext();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key);
      PartnerTransactionsResult transactionsResult = GSON.fromJson(result, PartnerTransactionsResult.class);

      PartnerTransactionsNotifyResult notifyResult = new PartnerTransactionsNotifyResult();
      notifyResult.setRawData(response);
      notifyResult.setResult(transactionsResult);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public PartnerTransactionsResult queryPartnerTransactions(PartnerTransactionsQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/pay/partner/transactions/out-trade-no/%s", this.payService.getPayBaseUrl(), request.getOutTradeNo());
    if (Objects.isNull(request.getOutTradeNo())) {
      url = String.format("%s/v3/pay/partner/transactions/id/%s", this.payService.getPayBaseUrl(), request.getTransactionId());
    }
    String query = String.format("?sp_mchid=%s&sub_mchid=%s", request.getSpMchid(), request.getSubMchid());
    String response = this.payService.getV3(url + query);
    return GSON.fromJson(response, PartnerTransactionsResult.class);
  }

  @Override
  public String closePartnerTransactions(PartnerTransactionsCloseRequest request) throws WxPayException {
    String url = String.format("%s/v3/pay/partner/transactions/out-trade-no/%s/close", this.payService.getPayBaseUrl(), request.getOutTradeNo());
    return this.payService.postV3(url, GSON.toJson(request));
  }

  @Override
  public FundBalanceResult spNowBalance(SpAccountTypeEnum accountType) throws WxPayException {
    String url = String.format("%s/v3/merchant/fund/balance/%s", this.payService.getPayBaseUrl(), accountType);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public FundBalanceResult spDayEndBalance(SpAccountTypeEnum accountType, String date) throws WxPayException {
    String url = String.format("%s/v3/merchant/fund/dayendbalance/%s?date=%s", this.payService.getPayBaseUrl(), accountType, date);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public FundBalanceResult subNowBalance(String subMchid) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/fund/balance/%s", this.payService.getPayBaseUrl(), subMchid);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public FundBalanceResult subNowBalance(String subMchid, SpAccountTypeEnum accountType) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/fund/balance/%s", this.payService.getPayBaseUrl(), subMchid);
    if (Objects.nonNull(accountType)) {
      url += "?account_type=" + accountType.getValue();
    }
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public FundBalanceResult subDayEndBalance(String subMchid, String date) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/fund/enddaybalance/%s?date=%s", this.payService.getPayBaseUrl(), subMchid, date);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/orders", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String response = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(response, ProfitSharingResult.class);
  }

  @Override
  public ProfitSharingResult queryProfitSharing(ProfitSharingQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/orders?sub_mchid=%s&transaction_id=%s&out_order_no=%s",
      this.payService.getPayBaseUrl(), request.getSubMchid(), request.getTransactionId(), request.getOutOrderNo());
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, ProfitSharingResult.class);
  }

  @Override
  public ProfitSharingOrdersUnSplitAmountResult queryProfitSharingOrdersUnsplitAmount(ProfitSharingOrdersUnSplitAmountRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/orders/%s/amounts",
      this.payService.getPayBaseUrl(), request.getTransactionId());
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, ProfitSharingOrdersUnSplitAmountResult.class);
  }

  @Override
  public ProfitSharingReceiverResult addReceivers(ProfitSharingReceiverRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/receivers/add", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ProfitSharingReceiverResult.class);
  }

  @Override
  public ProfitSharingReceiverResult deleteReceivers(ProfitSharingReceiverRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/receivers/delete", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ProfitSharingReceiverResult.class);
  }

  @Override
  public ReturnOrdersResult returnOrders(ReturnOrdersRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/returnorders", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ReturnOrdersResult.class);
  }

  @Override
  public ReturnOrdersResult queryReturnOrders(ReturnOrdersQueryRequest request) throws WxPayException {
    String subMchid = request.getSubMchid();
    String orderId = request.getOrderId();
    String outOrderNo = request.getOutOrderNo();
    String outReturnNo = request.getOutReturnNo();
    String url = null;
    if (StringUtils.isBlank(orderId)) {
      url = String.format("%s/v3/ecommerce/profitsharing/returnorders?sub_mchid=%s&out_order_no=%s&out_return_no=%s",
        this.payService.getPayBaseUrl(), subMchid, outOrderNo, outReturnNo);
    } else {
      url = String.format("%s/v3/ecommerce/profitsharing/returnorders?sub_mchid=%s&order_id=%s&out_return_no=%s",
        this.payService.getPayBaseUrl(), subMchid, orderId, outReturnNo);
    }
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, ReturnOrdersResult.class);
  }

  @Override
  public ProfitSharingResult finishOrder(FinishOrderRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/finish-order", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ProfitSharingResult.class);
  }

  @Override
  public RefundsResult refunds(RefundsRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/refunds/apply", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, RefundsResult.class);
  }

  @Override
  public RefundQueryResult queryRefundByRefundId(String subMchid, String refundId) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/refunds/id/%s?sub_mchid=%s", this.payService.getPayBaseUrl(), refundId, subMchid);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, RefundQueryResult.class);
  }


  @Override
  public ReturnAdvanceResult refundsReturnAdvance(String subMchid, String refundId) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/refunds/%s/return-advance", this.payService.getPayBaseUrl(), refundId);
    Map request = new HashMap();
    request.put("sub_mchid",subMchid);
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ReturnAdvanceResult.class);
  }


  @Override
  public ReturnAdvanceResult queryRefundsReturnAdvance(String subMchid, String refundId) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/refunds/%s/return-advance?sub_mchid=%s", this.payService.getPayBaseUrl(), refundId,subMchid);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, ReturnAdvanceResult.class);
  }


  @Override
  public RefundQueryResult queryRefundByOutRefundNo(String subMchid, String outRefundNo) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/refunds/out-refund-no/%s?sub_mchid=%s", this.payService.getPayBaseUrl(), outRefundNo, subMchid);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, RefundQueryResult.class);
  }

  @Override
  public RefundNotifyResult parseRefundNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, notifyData)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    NotifyResponse response = GSON.fromJson(notifyData, NotifyResponse.class);
    NotifyResponse.Resource resource = response.getResource();
    String cipherText = resource.getCiphertext();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key);
      RefundNotifyResult notifyResult = GSON.fromJson(result, RefundNotifyResult.class);
      notifyResult.setRawData(response);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public SubWithdrawResult subWithdraw(SubWithdrawRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/fund/withdraw", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, SubWithdrawResult.class);
  }

  @Override
  public SpWithdrawResult spWithdraw(SpWithdrawRequest request) throws WxPayException {
    String url = String.format("%s/v3/merchant/fund/withdraw", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, SpWithdrawResult.class);
  }

  @Override
  public SubWithdrawStatusResult querySubWithdrawByOutRequestNo(String subMchid, String outRequestNo) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/fund/withdraw/out-request-no/%s?sub_mchid=%s", this.payService.getPayBaseUrl(), outRequestNo, subMchid);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, SubWithdrawStatusResult.class);
  }

  @Override
  public SpWithdrawStatusResult querySpWithdrawByOutRequestNo(String outRequestNo) throws WxPayException {
    String url = String.format("%s/v3/merchant/fund/withdraw/out-request-no/%s", this.payService.getPayBaseUrl(), outRequestNo);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, SpWithdrawStatusResult.class);
  }

  @Override
  public void modifySettlement(String subMchid, SettlementRequest request) throws WxPayException {
    String url = String.format("%s/v3/apply4sub/sub_merchants/%s/modify-settlement", this.payService.getPayBaseUrl(), subMchid);
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
  }

  @Override
  public SettlementResult querySettlement(String subMchid) throws WxPayException {
    String url = String.format("%s/v3/apply4sub/sub_merchants/%s/settlement", this.payService.getPayBaseUrl(), subMchid);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, SettlementResult.class);
  }

  @Override
  public TradeBillResult applyBill(TradeBillRequest request) throws WxPayException {
    String url = String.format("%s/v3/bill/tradebill?%s", this.payService.getPayBaseUrl(), this.parseURLPair(request));
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, TradeBillResult.class);
  }

  @Override
  public FundBillResult applyFundBill(FundBillTypeEnum billType, FundBillRequest request) throws WxPayException {
    String url = String.format(billType.getUrl(), this.payService.getPayBaseUrl(), this.parseURLPair(request));
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, FundBillResult.class);
  }

  @Override
  public InputStream downloadBill(String url) throws WxPayException {
    return this.payService.downloadV3(url);
  }

  @Override
  public SubsidiesCreateResult subsidiesCreate(SubsidiesCreateRequest subsidiesCreateRequest) throws WxPayException{
    String url = String.format("%s/v3/ecommerce/subsidies/create", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(subsidiesCreateRequest));
    return GSON.fromJson(response, SubsidiesCreateResult.class);
  }

  @Override
  public  SubsidiesReturnResult subsidiesReturn(SubsidiesReturnRequest subsidiesReturnRequest) throws WxPayException{
    String url = String.format("%s/v3/ecommerce/subsidies/return", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(subsidiesReturnRequest));
    return GSON.fromJson(response, SubsidiesReturnResult.class);
  }


  @Override
  public SubsidiesCancelResult subsidiesCancel(SubsidiesCancelRequest subsidiesCancelRequest) throws WxPayException{
    String url = String.format("%s/v3/ecommerce/subsidies/cancel", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(subsidiesCancelRequest));
    return GSON.fromJson(response, SubsidiesCancelResult.class);
  }

  @Override
  public AccountCancelApplicationsResult createdAccountCancelApplication(AccountCancelApplicationsRequest accountCancelApplicationsRequest) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/account/cancel-applications", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(accountCancelApplicationsRequest));
    return GSON.fromJson(response, AccountCancelApplicationsResult.class);
  }

  @Override
  public AccountCancelApplicationsResult getAccountCancelApplication(String outApplyNo) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/account/cancel-applications/out-apply-no/%s", this.payService.getPayBaseUrl(), outApplyNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, AccountCancelApplicationsResult.class);
  }

  @Override
  public AccountCancelApplicationsMediaResult uploadMediaAccountCancelApplication(File imageFile) throws WxPayException, IOException {
    String url = String.format("%s/v3/ecommerce/account/cancel-applications/media", this.payService.getPayBaseUrl());
    try (FileInputStream s1 = new FileInputStream(imageFile)) {
      String sha256 = DigestUtils.sha256Hex(s1);
      try (InputStream s2 = new FileInputStream(imageFile)) {
        WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(URI.create(url))
          .withImage(imageFile.getName(), sha256, s2)
          .buildEcommerceAccount();
        String result = this.payService.postV3(url, request);
        return GSON.fromJson(result, AccountCancelApplicationsMediaResult.class);
      }
    }
  }

  /**
   * 校验通知签名
   *
   * @param header 通知头信息
   * @param data   通知数据
   * @return true:校验通过 false:校验不通过
   */
  private boolean verifyNotifySign(SignatureHeader header, String data) {
    String beforeSign = String.format("%s\n%s\n%s\n",
      header.getTimeStamp(),
      header.getNonce(),
      data);
    return payService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
  }

  /**
   * 对象拼接到url
   *
   * @param o 转换对象
   * @return 拼接好的string
   */
  private String parseURLPair(Object o) {
    Map<Object, Object> map = getObjectToMap(o);
    Set<Map.Entry<Object, Object>> set = map.entrySet();
    Iterator<Map.Entry<Object, Object>> it = set.iterator();
    StringBuilder sb = new StringBuilder();
    while (it.hasNext()) {
      Map.Entry<Object, Object> e = it.next();
      if (!"class".equals(e.getKey()) && e.getValue() != null) {
        sb.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, String.valueOf(e.getKey())))
          .append("=").append(e.getValue()).append("&");
      }
    }
    return sb.deleteCharAt(sb.length() - 1).toString();
  }

  public static Map<Object, Object> getObjectToMap(Object obj) {
    try {
      Map<Object, Object> result = new LinkedHashMap<>();
      final Class<? extends Object> beanClass = obj.getClass();
      final BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
      final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      if (propertyDescriptors != null) {
        for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
          if (propertyDescriptor != null) {
            final String name = propertyDescriptor.getName();
            final Method readMethod = propertyDescriptor.getReadMethod();
            if (readMethod != null) {
              result.put(name, readMethod.invoke(obj));
            }
          }
        }
      }
      return result;
    } catch (IllegalAccessException | IntrospectionException | InvocationTargetException ignored) {
      return null;
    }
  }

}
