package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.payscore.PayScoreNotifyData;
import com.github.binarywang.wxpay.bean.payscore.UserAuthorizationStatusNotifyResult;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.exception.WxSignTestException;
import com.github.binarywang.wxpay.service.PayScoreService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author doger.wang
 * created on  2020/5/14 9:43
 */
@RequiredArgsConstructor
public class PayScoreServiceImpl implements PayScoreService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public WxPayScoreResult permissions(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/permissions";
    if(Strings.isNullOrEmpty(request.getAppid())){
      request.setAppid(config.getAppId());
    }
    if(Strings.isNullOrEmpty(request.getServiceId())){
      request.setServiceId(config.getServiceId());
    }
    if(Strings.isNullOrEmpty(request.getNotifyUrl())){
      String permissionNotifyUrl = config.getPayScorePermissionNotifyUrl();
      if (StringUtils.isBlank(permissionNotifyUrl)) {
        throw new WxPayException("授权回调地址未配置");
      }
      request.setNotifyUrl(permissionNotifyUrl);
    }
    String authorizationCode = request.getAuthorizationCode();
    if (StringUtils.isBlank(authorizationCode)) {
      throw new WxPayException("authorizationCode不允许为空");
    }
    String result = this.payService.postV3(url, request.toJson());
    return WxPayScoreResult.fromJson(result);

  }

  @Override
  public WxPayScoreResult permissionsQueryByAuthorizationCode(String authorizationCode) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(authorizationCode)) {
      throw new WxPayException("authorizationCode不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/authorization-code/%s", this.payService.getPayBaseUrl(), authorizationCode);
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

    uriBuilder.setParameter("service_id", config.getServiceId());
    try {
      String result = payService.getV3(uriBuilder.build().toString());
      return WxPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

  }

  @Override
  public WxPayScoreResult permissionsTerminateByAuthorizationCode(String authorizationCode, String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(authorizationCode)) {
      throw new WxPayException("authorizationCode不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/authorization-code/%s/terminate", this.payService.getPayBaseUrl(), authorizationCode);
    Map<String, Object> map = new HashMap<>(4);
    map.put("service_id", config.getServiceId());
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);

  }

  @Override
  public WxPayScoreResult permissionsQueryByOpenId(String openId) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(openId)) {
      throw new WxPayException("openId不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/openid/%s", this.payService.getPayBaseUrl(), openId);
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

    uriBuilder.setParameter("appid", config.getAppId());
    uriBuilder.setParameter("service_id", config.getServiceId());
    try {
      String result = payService.getV3(uriBuilder.build().toString());
      return WxPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

  }

  @Override
  public WxPayScoreResult permissionsTerminateByOpenId(String openId, String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(openId)) {
      throw new WxPayException("openId不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/openid/%s/terminate", this.payService.getPayBaseUrl(), openId);
    Map<String, Object> map = new HashMap<>(4);
    map.put("service_id", config.getServiceId());
    map.put("appid", config.getAppId());
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);

  }

  @Override
  public WxPayScoreResult createServiceOrder(WxPayScoreRequest request) throws WxPayException {
    boolean needUserConfirm = request.getNeedUserConfirm();
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";
    if(Strings.isNullOrEmpty(request.getAppid())){
      request.setAppid(config.getAppId());
    }
    if(Strings.isNullOrEmpty(request.getServiceId())){
      request.setServiceId(config.getServiceId());
    }
    if(Strings.isNullOrEmpty(request.getNotifyUrl())){
      request.setNotifyUrl(config.getPayScoreNotifyUrl());
    }
    String result = this.payService.postV3(url, request.toJson());
    WxPayScoreResult wxPayScoreCreateResult = WxPayScoreResult.fromJson(result);

    //补充算一下签名给小程序跳转用
    String currentTimeMillis = System.currentTimeMillis() + "";
    Map<String, String> signMap = new HashMap<>(8);
    signMap.put("mch_id", config.getMchId());
    if (needUserConfirm) {
      signMap.put("package", wxPayScoreCreateResult.getPackageX());
    } else {
      signMap.put("service_id", config.getServiceId());
      signMap.put("out_order_no", request.getOutOrderNo());
    }
    signMap.put("timestamp", currentTimeMillis);
    signMap.put("nonce_str", currentTimeMillis);
    signMap.put("sign_type", "HMAC-SHA256");
    String sign = AesUtils.createSign(signMap, config.getMchKey());
    signMap.put("sign", sign);
    wxPayScoreCreateResult.setPayScoreSignInfo(signMap);
    return wxPayScoreCreateResult;
  }

  @Override
  public WxPayScoreResult queryServiceOrder(String outOrderNo, String queryId) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";

    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

    if (StringUtils.isAllEmpty(outOrderNo, queryId) || !StringUtils.isAnyEmpty(outOrderNo, queryId)) {
      throw new WxPayException("out_order_no,query_id不允许都填写或都不填写");
    }
    if (StringUtils.isNotEmpty(outOrderNo)) {
      uriBuilder.setParameter("out_order_no", outOrderNo);
    }
    if (StringUtils.isNotEmpty(queryId)) {
      uriBuilder.setParameter("query_id", queryId);
    }
    uriBuilder.setParameter("service_id", config.getServiceId());
    uriBuilder.setParameter("appid", config.getAppId());
    try {
      String result = payService.getV3(uriBuilder.build().toString());
      return WxPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

  }

  @Override
  public WxPayScoreResult cancelServiceOrder(String outOrderNo, String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = String.format("%s/v3/payscore/serviceorder/%s/cancel", this.payService.getPayBaseUrl(), outOrderNo);
    Map<String, Object> map = new HashMap<>(4);
    map.put("appid", config.getAppId());
    map.put("service_id", config.getServiceId());
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult modifyServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/modify", this.payService.getPayBaseUrl(), outOrderNo);
    if(Strings.isNullOrEmpty(request.getAppid())){
      request.setAppid(config.getAppId());
    }
    if(Strings.isNullOrEmpty(config.getServiceId())){
      request.setServiceId(config.getServiceId());
    }
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult completeServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/complete", this.payService.getPayBaseUrl(), outOrderNo);
    if(Strings.isNullOrEmpty(request.getAppid())){
      request.setAppid(config.getAppId());
    }
    if(Strings.isNullOrEmpty(request.getServiceId())){
      request.setServiceId(config.getServiceId());
    }
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult payServiceOrder(String outOrderNo) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = String.format("%s/v3/payscore/serviceorder/%s/pay", this.payService.getPayBaseUrl(), outOrderNo);
    Map<String, Object> map = new HashMap<>(2);
    map.put("appid", config.getAppId());
    map.put("service_id", config.getServiceId());
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult syncServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/sync", this.payService.getPayBaseUrl(), outOrderNo);
    if(Strings.isNullOrEmpty(request.getAppid())){
      request.setAppid(config.getAppId());
    }
    if(Strings.isNullOrEmpty(request.getServiceId())){
      request.setServiceId(config.getServiceId());
    }
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public UserAuthorizationStatusNotifyResult parseUserAuthorizationStatusNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    PayScoreNotifyData response = parseNotifyData(notifyData, header);
    PayScoreNotifyData.Resource resource = response.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key);
      UserAuthorizationStatusNotifyResult notifyResult = GSON.fromJson(result, UserAuthorizationStatusNotifyResult.class);
      notifyResult.setRawData(response);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public PayScoreNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, data)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    return GSON.fromJson(data, PayScoreNotifyData.class);
  }

  @Override
  public WxPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException {
    PayScoreNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      return WxPayScoreResult.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key));
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  /**
   * 校验通知签名
   *
   * @param header 通知头信息
   * @param data   通知数据
   * @return true:校验通过 false:校验不通过
   */
  private boolean verifyNotifySign(SignatureHeader header, String data) throws WxSignTestException {
    String wxPaySign = header.getSigned();
    if(wxPaySign.startsWith("WECHATPAY/SIGNTEST/")){
      throw new WxSignTestException("微信支付签名探测流量");
    }
    String beforeSign = String.format("%s\n%s\n%s\n", header.getTimeStamp(), header.getNonce(), data);
    return payService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
  }
}
