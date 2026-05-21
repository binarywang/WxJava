package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.payscore.PartnerUserSignPlanEntity;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreSignPlanRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreSignPlanResult;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreUserSignPlanResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author UltramanNoa
 * @className PartnerPayScoreSignPlanService
 * @description 微信支付分签约计划接口
 * @createTime 2023/11/3 09:16
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
public interface PartnerPayScoreSignPlanService {

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
  WxPartnerPayScoreSignPlanResult createPlans(WxPartnerPayScoreSignPlanRequest request) throws WxPayException;

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
  WxPartnerPayScoreSignPlanResult queryPlans(@NonNull String merchantPlanNo, @NonNull String subMchid) throws WxPayException;

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
  WxPartnerPayScoreSignPlanResult stopPlans(@NonNull String merchantPlanNo, @NonNull String subMchid) throws WxPayException;

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
  WxPartnerPayScoreSignPlanResult signPlanServiceOrder(WxPartnerPayScoreSignPlanRequest request) throws WxPayException;

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
  WxPartnerPayScoreUserSignPlanResult createUserSignPlans(WxPartnerPayScoreSignPlanRequest request) throws WxPayException;

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
  PartnerUserSignPlanEntity queryUserSignPlans(@NonNull String merchantSignPlanNo, @NonNull String subMchid) throws WxPayException;

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
  PartnerUserSignPlanEntity stopUserSignPlans(@NonNull String merchantSignPlanNo, @NonNull String subMchid, @NonNull String stopReason) throws WxPayException;

  /**
   * <p>description： 回调通知校验解密  </p>
   * <p>author：UltramanNoa  </p>
   * <p>create Time： 2023/11/6 10:27   </p>
   * <p>version： v.1.0  </p>
   * @param
   * @param notifyData
   * @param header
   * @return {@link PartnerUserSignPlanEntity}
   **/
  PartnerUserSignPlanEntity parseSignPlanNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;
}
