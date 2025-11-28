package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.payscore.PayScoreNotifyData;
import com.github.binarywang.wxpay.bean.payscore.UserAuthorizationStatusNotifyResult;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *  支付分相关服务类.
 *   微信支付分是对个人的身份特质、支付行为、使用历史等情况的综合计算分值，旨在为用户提供更简单便捷的生活方式。
 *   微信用户可以在具体应用场景中，开通微信支付分。开通后，用户可以在【微信—>钱包—>支付分】中查看分数和使用记录。
 *   （即需在应用场景中使用过一次，钱包才会出现支付分入口）
 *
 *  Created by doger.wang on 2020/05/12.
 * </pre>
 *
 * @author doger.wang
 */
public interface PayScoreService {



  /**
   * <pre>
   * 支付分商户预授权API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/permissions
   * </pre>
   *
   * @param request 请求对象
   * @return WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult permissions(WxPayScoreRequest request) throws WxPayException;


  /**
   * <pre>
   * 支付分查询与用户授权记录（授权协议号）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/permissions/authorization-code/{authorization_code}
   * </pre>
   *
   * @param authorizationCode
   * @return WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult permissionsQueryByAuthorizationCode(String authorizationCode) throws WxPayException;



  /**
   * <pre>
   * 解除用户授权关系（授权协议号）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/permissions/authorization-code/{authorization_code}/terminate
   * </pre>
   *
   * @param authorizationCode
   * @param reason
   * @return WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult permissionsTerminateByAuthorizationCode(String authorizationCode,String reason) throws WxPayException;





  /**
   * <pre>
   * 支付分查询与用户授权记录（openid）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_4shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/permissions/openid/{openid}
   * </pre>
   *
   * @param openId
   * @return WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult permissionsQueryByOpenId(String openId) throws WxPayException;





  /**
   * <pre>
   * 解除用户授权关系（openid）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_5.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/permissions/openid/{openid}/terminate
   * </pre>
   *
   * @param openId
   * @param reason
   * @return WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult permissionsTerminateByOpenId(String openId,String reason) throws WxPayException;





  /**
   * <pre>
   * 支付分创建订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder
   * </pre>
   *
   * @param request 请求对象
   * @return WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult createServiceOrder(WxPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分查询订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder
   * </pre>
   *
   * @param outOrderNo the out order no
   * @param queryId    the query id
   * @return the wx pay score result
   * @throws WxPayException     the wx pay exception
   */
  WxPayScoreResult queryServiceOrder(String outOrderNo, String queryId) throws WxPayException;

  /**
   * <pre>
   * 支付分取消订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/cancel
   * </pre>
   *
   * @param outOrderNo the out order no
   * @param reason     the reason
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult cancelServiceOrder(String outOrderNo, String reason) throws WxPayException;

  /**
   * <pre>
   * 支付分修改订单金额API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/modify
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult modifyServiceOrder(WxPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分完结订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_5.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/complete
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult completeServiceOrder(WxPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分订单收款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_6.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/pay
   *
   * </pre>
   *
   * @param outOrderNo the out order no
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult payServiceOrder(String outOrderNo) throws WxPayException;

  /**
   * <pre>
   * 支付分订单收款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_7.shtml
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/sync
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult syncServiceOrder(WxPayScoreRequest request) throws WxPayException;
  
  /**
   * <pre>
   * 授权/解除授权服务回调数据处理
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter4_4.shtml
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 return user authorization status notify result
   * @throws WxPayException the wx pay exception
   */
  UserAuthorizationStatusNotifyResult parseUserAuthorizationStatusNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 支付分回调内容解析方法
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
   * </pre>
   *
   * @param data the data
   * @return the wx pay score result
   */
  PayScoreNotifyData parseNotifyData(String data,SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 支付分回调NotifyData解密resource
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
   * </pre>
   *
   * @param data the data
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException;

}
