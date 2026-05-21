package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.payscore.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PartnerPayScoreService;
import com.github.binarywang.wxpay.service.PartnerPayScoreSignPlanService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Objects;


/**
 * @author UltramanNoa
 * @className PartnerPayScoreSignPlanServiceImpl
 * @description 微信支付分签约计划接口实现
 * @createTime 2023/11/3 09:23
 *
 * <pre>
 * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-pay-score-plan/create-partner-pay-score-plan.html">微信支付分签约计划</a>
 * <br>
 * 文档更新时间：2023.10.13
 * <br>
 * 微信支付分签约计划是不同模式的支付分接口（随着国家大力推广教培行业先享后付政策,微信支付也紧跟政策于2023.07.25上线第一版签约计划接口以适用教培行业先享后付。于2023.10.13文档推至官网文档中心）
 * <br>
 * 免确认/需确认 用服务商创单接口 {@link PartnerPayScoreService} 需要用户授权
 * <br>
 * 签约计划，用单独创单接口 {@link PartnerPayScoreSignPlanService} 不需要用户授权
 * </pre>
 **/
@RequiredArgsConstructor
public class PartnerPayScoreSignPlanServiceImpl implements PartnerPayScoreSignPlanService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService ;

  /**
   * <p>description：创建支付分计划  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 11:58   </p>
   * <p>version： v.1.0  </p>
   *
   * @param request {@link WxPartnerPayScoreSignPlanRequest}
   *
   * @return {@link WxPartnerPayScoreSignPlanResult}
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-pay-score-plan/create-partner-pay-score-plan.html">创建支付分计划</a>
   **/
  @Override
  public WxPartnerPayScoreSignPlanResult createPlans(WxPartnerPayScoreSignPlanRequest request) throws WxPayException {
    String url = String.format("%s/v3/payscore/plan/partner/payscore-plans", payService.getPayBaseUrl());

    if (StringUtils.isBlank(request.getAppid())) {
      request.setAppid(payService.getConfig().getAppId());
    }
    if (StringUtils.isBlank((request.getServiceId()))) {
      request.setServiceId(payService.getConfig().getServiceId());
    }

    String result = payService.postV3(url, request.toJson());
    return WxPartnerPayScoreSignPlanResult.fromJson(result);
  }

  /**
   * <p>description： 查询支付分计划   </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 14:03   </p>
   * <p>version： v.1.0  </p>
   *
   * @param merchantPlanNo 路径参数：支付分计划商户侧单号
   * @param subMchid       子商户号
   *
   * @return {@link WxPartnerPayScoreSignPlanResult}
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-pay-score-plan/query-partner-pay-score-plan.html">查询支付分计划</a>
   **/
  @Override
  public WxPartnerPayScoreSignPlanResult queryPlans(@NonNull String merchantPlanNo, @NonNull String subMchid) throws WxPayException {
    String url = String.format("%s/v3/payscore/plan/partner/payscore-plans/merchant-plan-no/%s", payService.getPayBaseUrl(), merchantPlanNo);

    try {
      URI uri = new URIBuilder(url)
        .setParameter("sub_mchid", subMchid)
        .build();

      String result = payService.getV3(uri.toString());

      return WxPartnerPayScoreSignPlanResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }
  }

  /**
   * <p>description： 停止支付分计划  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 14:24   </p>
   * <p>version： v.1.0  </p>
   *
   * @param merchantPlanNo 路径参数：支付分计划商户侧单号
   * @param subMchid       子商户号
   *
   * @return {@link WxPartnerPayScoreSignPlanResult}
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-pay-score-plan/stop-partner-pay-score-plan.html">停止支付分计划</a>
   **/
  @Override
  public WxPartnerPayScoreSignPlanResult stopPlans(@NonNull String merchantPlanNo, @NonNull String subMchid) throws WxPayException {
    String url = String.format("%s/v3/payscore/plan/partner/payscore-plans/merchant-plan-no/%s/stop", payService.getPayBaseUrl(), merchantPlanNo);

    Map<String, String> params = Maps.newHashMap();
    params.put("sub_mchid", subMchid);

    String result = payService.postV3(url, WxGsonBuilder.create().toJson(params));
    return WxPartnerPayScoreSignPlanResult.fromJson(result);
  }

  /**
   * <p>description： 创建用户的签约计划详情对应的服务订单  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 14:53   </p>
   * <p>version： v.1.0  </p>
   *
   * @param request {@link WxPartnerPayScoreSignPlanRequest}
   *
   * @return {@link WxPartnerPayScoreSignPlanResult}
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-service-order/create-partner-service-order.html">创建用户的签约计划详情对应的服务订单</a>
   **/
  @Override
  public WxPartnerPayScoreSignPlanResult signPlanServiceOrder(WxPartnerPayScoreSignPlanRequest request) throws WxPayException {
    String url = String.format("%s/v3/payscore/sign-plan/partner/serviceorder", payService.getPayBaseUrl());

    if (StringUtils.isBlank(request.getAppid())) {
      request.setAppid(payService.getConfig().getAppId());
    }
    if (StringUtils.isBlank((request.getServiceId()))) {
      request.setServiceId(payService.getConfig().getServiceId());
    }

    String result = payService.postV3(url, request.toJson());
    return WxPartnerPayScoreSignPlanResult.fromJson(result);
  }

  /**
   * <p>description： 创建用户的签约计划  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 17:48   </p>
   * <p>version： v.1.0  </p>
   *
   * @param request {@link WxPartnerPayScoreSignPlanRequest}
   *
   * @return {@link WxPartnerPayScoreUserSignPlanResult}
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-user-sign-plan/create-partner-user-sign-plan.html">创建用户的签约计划</a>
   **/
  @Override
  public WxPartnerPayScoreUserSignPlanResult createUserSignPlans(WxPartnerPayScoreSignPlanRequest request) throws WxPayException {
    String url = String.format("%s/v3/payscore/sign-plan/partner/user-sign-plans", payService.getPayBaseUrl());

    if (StringUtils.isBlank(request.getAppid())) {
      request.setAppid(payService.getConfig().getAppId());
    }
    if (StringUtils.isBlank((request.getServiceId()))) {
      request.setServiceId(payService.getConfig().getServiceId());
    }

    String result = payService.postV3(url, request.toJson());
    return WxPartnerPayScoreUserSignPlanResult.fromJson(result);
  }

  /**
   * <p>description： 查询用户的签约计划 </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 18:01   </p>
   * <p>version： v.1.0  </p>
   *
   * @param merchantSignPlanNo 路径参数  商户签约计划号
   * @param subMchid           子商户号
   *
   * @return {@link PartnerUserSignPlanEntity}
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-user-sign-plan/query-partner-user-sign-plan.html">查询用户的签约计划</a>
   **/
  @Override
  public PartnerUserSignPlanEntity queryUserSignPlans(@NonNull String merchantSignPlanNo, @NonNull String subMchid) throws WxPayException {
    String url = String.format("%s/v3/payscore/sign-plan/partner/user-sign-plans/merchant-sign-plan-no/%s", payService.getPayBaseUrl(), merchantSignPlanNo);

    try {
      URI uri = new URIBuilder(url)
        .setParameter("sub_mchid", subMchid)
        .build();

      String result = payService.getV3(uri.toString());

      return PartnerUserSignPlanEntity.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }
  }

  /**
   * <p>description： 取消用户的签约计划   </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 18:01   </p>
   * <p>version： v.1.0  </p>
   *
   * @param merchantSignPlanNo 路径参数 商户签约计划号 subMchid – 子商户号
   * @param subMchid           子商户号
   * @param stopReason         停止签约计划原因
   *
   * @return {@link PartnerUserSignPlanEntity}
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-payscore-plan/partner-user-sign-plan/stop-partner-user-sign-plan.html">取消用户的签约计划</a>
   **/
  @Override
  public PartnerUserSignPlanEntity stopUserSignPlans(@NonNull String merchantSignPlanNo, @NonNull String subMchid, @NonNull String stopReason) throws WxPayException {
    String url = String.format("%s/v3/payscore/sign-plan/partner/user-sign-plans/merchant-sign-plan-no/%s/stop", payService.getPayBaseUrl(), merchantSignPlanNo);

    Map<String, String> params = Maps.newHashMap();
    params.put("sub_mchid", subMchid);
    params.put("stop_reason", stopReason);

    String result = payService.postV3(url, WxGsonBuilder.create().toJson(params));
    return PartnerUserSignPlanEntity.fromJson(result);
  }


  /**
   * <p>description：回调通知  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 18:28   </p>
   * <p>version： v.1.0  </p>
   *
   * @param notifyData 回调参数
   * @param header     签名
   *
   * @return {@link PartnerUserSignPlanEntity}
   **/
  @Override
  public PartnerUserSignPlanEntity parseSignPlanNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    PayScoreNotifyData response = parseNotifyData(notifyData, header);
    return decryptNotifyDataResource(response);
  }

  /**
   * <p>description： 检验并解析通知参数  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 18:30   </p>
   * <p>version： v.1.0  </p>
   * @param data
   * @param header
   * @return {@link PayScoreNotifyData}
   **/
  public PayScoreNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !payService.verifyNotifySign(header, data)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    return GSON.fromJson(data, PayScoreNotifyData.class);
  }

  /**
   * <p>description： 解析回调通知参数  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/3 18:27   </p>
   * <p>version： v.1.0  </p>
   *
   * @param data {@link PayScoreNotifyData}
   *
   * @return {@link PartnerUserSignPlanEntity}
   **/
  public PartnerUserSignPlanEntity decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException {
    PayScoreNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = payService.getConfig().getApiV3Key();
    try {
      return PartnerUserSignPlanEntity.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key));
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

}
