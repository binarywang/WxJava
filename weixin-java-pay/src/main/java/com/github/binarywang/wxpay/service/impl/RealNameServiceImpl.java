package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.realname.RealNameRequest;
import com.github.binarywang.wxpay.bean.realname.RealNameResult;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.RealNameService;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 微信支付实名验证相关服务实现类.
 * 详见文档：https://pay.wechatpay.cn/doc/v2/merchant/4011987607
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class RealNameServiceImpl implements RealNameService {
  private final WxPayService payService;

  @Override
  public RealNameResult queryRealName(RealNameRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/userinfo/realnameauth/query";

    String responseContent = this.payService.post(url, request.toXML(), true);
    RealNameResult result = BaseWxPayResult.fromXML(responseContent, RealNameResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public RealNameResult queryRealName(String openid) throws WxPayException {
    RealNameRequest request = RealNameRequest.newBuilder()
      .openid(openid)
      .build();
    return this.queryRealName(request);
  }
}
