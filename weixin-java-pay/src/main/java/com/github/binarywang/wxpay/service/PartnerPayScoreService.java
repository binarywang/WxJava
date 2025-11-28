package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.payscore.PayScoreNotifyData;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreResult;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerUserAuthorizationStatusNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *  服务商支付分相关服务类.
 *   微信支付分是对个人的身份特质、支付行为、使用历史等情况的综合计算分值，旨在为用户提供更简单便捷的生活方式。
 *   微信用户可以在具体应用场景中，开通微信支付分。开通后，用户可以在【微信—>钱包—>支付分】中查看分数和使用记录。
 *   （即需在应用场景中使用过一次，钱包才会出现支付分入口）
 *
 * @author hallkk
 * created on  2022/05/18
 */
public interface PartnerPayScoreService {


  /**
   * 商户预授权
   * @param request {@link WxPartnerPayScoreRequest} 请求对象
   *
   * @return WxPartnerPayScoreResult wx  partner payscore result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-auth/apply-partner-permissions.html">商户预授权</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions
   */
  WxPartnerPayScoreResult permissions(WxPartnerPayScoreRequest request) throws WxPayException;


  /**
   * 商户查询与用户授权记录 （authorization_code）
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-auth/get-partner-permissions-by-code.html">商户查询与用户授权记录</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/authorization-code/{authorization_code}
   *
   * @param serviceId 服务id
   * @param subMchid 特约子商户号
   * @param authorizationCode 授权协议号
   *
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult permissionsQueryByAuthorizationCode(String serviceId, String subMchid,
                                                              String authorizationCode) throws WxPayException;


  /**
   * 商户解除用户授权关系（authorization_code）
   *
   * @param serviceId         服务id
   * @param subMchid          特约子商户号
   * @param authorizationCode 授权协议号
   * @param reason            撤销原因
   *
   * @return WxPartnerPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   * @apiNote : <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-auth/terminate-partner-permissions-by-code.html">商户解除用户授权关系</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/authorization-code/{authorization_code}/terminate
   */
  WxPartnerPayScoreResult permissionsTerminateByAuthorizationCode(String serviceId, String subMchid,
                                                                  String authorizationCode, String reason) throws WxPayException;


  /**
   * 商户查询与用户授权记录（OpenID）
   *
   * @param serviceId 服务id
   * @param subMchid  特约子商户号
   * @param appId     服务商的公众号ID
   * @param subAppid  子商户的公众号ID
   * @param openId    服务商的用户标识
   * @param subOpenid 子商户的用户标识
   *
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-auth/get-partner-permissions-by-open-id.html">商户查询与用户授权记录</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/search
   */
  WxPartnerPayScoreResult permissionsQueryByOpenId(String serviceId, String appId, String subMchid, String subAppid,
                                                   String openId, String subOpenid) throws WxPayException;


  /**
   * 商户解除用户授权关系API（OpenID）
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-auth/terminate-partner-permissions-by-open-id.html">商户解除用户授权关系API</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/terminate
   *
   * @param serviceId 服务id
   * @param subMchid  特约子商户号
   * @param appId     服务商的公众号ID
   * @param subAppid  子商户的公众号ID
   * @param openId    服务商的用户标识
   * @param subOpenid 子商户的用户标识
   * @param reason    取消理由
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult permissionsTerminateByOpenId(String serviceId, String appId, String subMchid, String subAppid,
                                                       String openId, String subOpenid, String reason) throws WxPayException;


  /**
   * 支付分创建订单API.
   *
   * @param request 请求对象
   *
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-order/create-partner-service-order.html">创建订单</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder
   */
  WxPartnerPayScoreResult createServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * 支付分查询订单API.
   *
   * @param serviceId  服务ID
   * @param subMchid   子商户商户号
   * @param outOrderNo 商户订单号
   * @param queryId    单据查询ID
   *
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-order/get-partner-service-order.html">查询订单</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder
   */
  WxPartnerPayScoreResult queryServiceOrder(String serviceId, String subMchid,
                                            String outOrderNo, String queryId) throws WxPayException;

  /**
   * 支付分取消订单API.
   *
   * @param serviceId  服务ID
   * @param subMchid   子商户商户号
   * @param outOrderNo 商户订单号
   * @param reason     撤销原因
   *
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-order/cancel-partner-service-order.html">取消订单</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/cancel
   */
  WxPartnerPayScoreResult cancelServiceOrder(String serviceId, String appId, String subMchid,
                                             String outOrderNo, String reason) throws WxPayException;

  /**
   * 支付分修改订单金额API.
   *
   * @param request the request
   *
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-order/modify-partner-service-order.html">修改订单金额</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/modify
   */
  WxPartnerPayScoreResult modifyServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * 支付分完结订单API.
   *
   * @param request the request
   *
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-order/complete-partner-service-order.html">完结订单</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/complete
   */
  void completeServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * 订单收款
   *
   * @param serviceId  服务ID
   * @param subMchid   子商户商户号
   * @param outOrderNo 商户订单号
   *
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-order/collect-partner-service-order.html">订单收款</a>
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/pay
   */
  WxPartnerPayScoreResult payServiceOrder(String serviceId, String appId, String subMchid, String outOrderNo) throws WxPayException;

  /**
   * 同步订单信息
   *
   * @param request the request
   *
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/partner-service-order/sync-partner-service-order.html">同步订单信息</a>
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/sync
   */
  WxPartnerPayScoreResult syncServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 收付通子商户申请绑定支付分服务API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter9_1.shtml
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/partner/service-account-applications
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult applyServiceAccount(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询收付通子商户服务绑定结果API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter9_2.shtml
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/partner/service-account-applications/{out_apply_no}
   * </pre>
   *
   * @param outApplyNo 商户申请绑定单号
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult queryServiceAccountState(String outApplyNo) throws WxPayException;

  /**
   * 授权/解除授权服务回调通知
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   *
   * @return 解密后通知数据 return user authorization status notify result
   * @throws WxPayException the wx pay exception
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/partner-weixin-pay-score/authorization-de-authorization-service-callback-notification.html">授权/解除授权服务回调通知</a>
   */
  WxPartnerUserAuthorizationStatusNotifyResult parseUserAuthorizationStatusNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 支付分回调内容解析方法
   *
   * @param data the data
   * @return the wx pay score result
   */
  PayScoreNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException;

  /**
   * 支付分回调NotifyData解密resource
   *
   * @param data the data
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException;

}
