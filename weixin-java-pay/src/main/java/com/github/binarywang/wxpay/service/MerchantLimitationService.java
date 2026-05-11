package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.merchantlimitation.MerchantLimitationResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 商户被管控能力及原因查询 接口
 * <p>
 * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012165270">产品介绍</a>
 * </p>
 *
 * @author zhangyl
 */
public interface MerchantLimitationService {

  /**
   * 查询子商户管控情况
   * <p>
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012803072">接口文档</a>
   * </p>
   *
   * @param subMchId 子商户号
   * @return 子商户管控情况
   * @throws WxPayException the wx pay exception
   */
  MerchantLimitationResult fetchLimitations(String subMchId) throws WxPayException;

}
