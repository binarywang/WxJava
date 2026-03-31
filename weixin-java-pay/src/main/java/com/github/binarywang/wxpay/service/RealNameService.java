package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.realname.RealNameRequest;
import com.github.binarywang.wxpay.bean.realname.RealNameResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 微信支付实名验证相关服务类.
 * 详见文档：https://pay.wechatpay.cn/doc/v2/merchant/4011987607
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface RealNameService {
  /**
   * <pre>
   * 获取用户实名认证信息API.
   * 用于商户查询用户的实名认证状态，如果用户未实名认证，会返回引导用户实名认证的URL
   * 文档详见：https://pay.wechatpay.cn/doc/v2/merchant/4011987607
   * 接口链接：https://api.mch.weixin.qq.com/userinfo/realnameauth/query
   * </pre>
   *
   * @param request 请求对象
   * @return 实名认证查询结果
   * @throws WxPayException the wx pay exception
   */
  RealNameResult queryRealName(RealNameRequest request) throws WxPayException;

  /**
   * <pre>
   * 获取用户实名认证信息API（简化方法）.
   * 用于商户查询用户的实名认证状态，如果用户未实名认证，会返回引导用户实名认证的URL
   * 文档详见：https://pay.wechatpay.cn/doc/v2/merchant/4011987607
   * 接口链接：https://api.mch.weixin.qq.com/userinfo/realnameauth/query
   * </pre>
   *
   * @param openid 用户openid
   * @return 实名认证查询结果
   * @throws WxPayException the wx pay exception
   */
  RealNameResult queryRealName(String openid) throws WxPayException;
}
