package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.brandprofitsharing.*;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.profitsharing.notify.ProfitSharingNotifyV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BrandProfitSharingService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/** 连锁品牌分账接口实现。 */
public class BrandProfitSharingServiceImpl implements BrandProfitSharingService {
  private static final Gson GSON = new Gson();
  private static final String PATH = "/v3/brand/profitsharing";
  private final WxPayService payService;

  /**
   * 创建连锁品牌分账服务。
   * @param payService 微信支付服务，提供 API v3 配置和 HTTP 执行能力
   */
  public BrandProfitSharingServiceImpl(WxPayService payService) {
    this.payService = payService;
  }

  @Override
  public BrandProfitSharingResult profitSharing(BrandProfitSharingRequest request) throws WxPayException {
    // 保留原始姓名，允许调用方在暂时失败后使用同一请求对象重试。
    BrandProfitSharingRequest encrypted = GSON.fromJson(GSON.toJson(request), BrandProfitSharingRequest.class);
    RsaCryptoUtil.encryptFields(encrypted, this.payService.getConfig().getVerifier().getValidCertificate());
    return GSON.fromJson(this.payService.postV3WithWechatpaySerial(url("/orders"), GSON.toJson(encrypted)),
      BrandProfitSharingResult.class);
  }

  @Override
  public BrandProfitSharingResult query(BrandProfitSharingQueryRequest request) throws WxPayException {
    String path = "/orders?sub_mchid=" + encode(request.getSubMchId())
      + "&transaction_id=" + encode(request.getTransactionId())
      + "&out_order_no=" + encode(request.getOutOrderNo());
    return GSON.fromJson(this.payService.getV3(url(path)), BrandProfitSharingResult.class);
  }

  @Override
  public BrandProfitSharingReturnResult returnOrder(BrandProfitSharingReturnRequest request) throws WxPayException {
    return GSON.fromJson(this.payService.postV3(url("/returnorders"), GSON.toJson(request)),
      BrandProfitSharingReturnResult.class);
  }

  @Override
  public BrandProfitSharingReturnResult queryReturn(BrandProfitSharingReturnQueryRequest request) throws WxPayException {
    String path = "/returnorders?sub_mchid=" + encode(request.getSubMchId())
      + "&out_return_no=" + encode(request.getOutReturnNo());
    if (request.getOrderId() != null) {
      path += "&order_id=" + encode(request.getOrderId());
    }
    if (request.getOutOrderNo() != null) {
      path += "&out_order_no=" + encode(request.getOutOrderNo());
    }
    return GSON.fromJson(this.payService.getV3(url(path)), BrandProfitSharingReturnResult.class);
  }

  @Override
  public BrandProfitSharingFinishResult finish(BrandProfitSharingFinishRequest request) throws WxPayException {
    return GSON.fromJson(this.payService.postV3(url("/finish-order"), GSON.toJson(request)),
      BrandProfitSharingFinishResult.class);
  }

  @Override
  public BrandProfitSharingAmountResult queryRemainingAmount(String transactionId) throws WxPayException {
    return GSON.fromJson(this.payService.getV3(url("/orders/" + encode(transactionId) + "/amounts")),
      BrandProfitSharingAmountResult.class);
  }

  @Override
  public BrandProfitSharingRatioResult queryMaxRatio(String brandMchId) throws WxPayException {
    return GSON.fromJson(this.payService.getV3(url("/brand-configs/" + encode(brandMchId))),
      BrandProfitSharingRatioResult.class);
  }

  @Override
  public BrandProfitSharingReceiverResult addReceiver(BrandProfitSharingReceiverRequest request) throws WxPayException {
    return GSON.fromJson(this.payService.postV3(url("/receivers/add"), GSON.toJson(request)),
      BrandProfitSharingReceiverResult.class);
  }

  @Override
  public BrandProfitSharingReceiverResult deleteReceiver(BrandProfitSharingReceiverDeleteRequest request) throws WxPayException {
    return GSON.fromJson(this.payService.postV3(url("/receivers/delete"), GSON.toJson(request)),
      BrandProfitSharingReceiverResult.class);
  }

  @Override
  public ProfitSharingNotifyV3Result parseNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    if (header == null) {
      throw new WxPayException("通知签名头不能为空");
    }
    return new ProfitSharingServiceImpl(this.payService).parseProfitSharingNotifyResult(notifyData, header);
  }

  private String url(String path) {
    return this.payService.getPayBaseUrl() + PATH + path;
  }

  private static String encode(String value) {
    try {
      return URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }
}
