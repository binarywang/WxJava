package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.request.WxPayRedpackQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendMiniProgramRedpackRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendMiniProgramRedpackResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 红包相关接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-12-26
 */
public interface RedpackService {
  /**
   * <pre>
   * 发送小程序红包.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/tools/miniprogram_hb.php?chapter=13_9&index=2
   *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendminiprogramhb
   * </pre>
   *
   * @param request 请求对象
   * @return the result
   * @throws WxPayException the exception
   */
  WxPaySendMiniProgramRedpackResult sendMiniProgramRedpack(WxPaySendMiniProgramRedpackRequest request) throws WxPayException;

  /**
   * 发送微信红包给个人用户.
   * <pre>
   * 文档详见:
   * 发送普通红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3
   *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack
   * 发送裂变红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_5&index=4
   *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack
   * </pre>
   *
   * @param request 请求对象
   * @return the wx pay send redpack result
   * @throws WxPayException the wx pay exception
   */
  WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request) throws WxPayException;

  /**
   * <pre>
   *   查询红包记录.
   *   用于商户对已发放的红包进行查询红包的具体信息，可支持普通红包和裂变包。
   *   请求Url：https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo
   *   是否需要证书：是（证书及使用说明详见商户证书）
   *   请求方式：POST
   * </pre>
   *
   * @param mchBillNo 商户发放红包的商户订单号，比如10000098201411111234567890
   * @return the wx pay redpack query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxPayException;

  /**
   * <pre>
   *   查询红包记录.
   *   用于商户对已发放的红包进行查询红包的具体信息，可支持普通红包和裂变包。
   *   请求Url：https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo
   *   是否需要证书：是（证书及使用说明详见商户证书）
   *   请求方式：POST
   * </pre>
   *
   * @param request 红包查询请求
   * @return the wx pay redpack query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRedpackQueryResult queryRedpack(WxPayRedpackQueryRequest request) throws WxPayException;
}
