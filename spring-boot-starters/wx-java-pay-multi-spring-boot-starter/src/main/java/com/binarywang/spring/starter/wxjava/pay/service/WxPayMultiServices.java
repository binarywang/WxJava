package com.binarywang.spring.starter.wxjava.pay.service;

import com.github.binarywang.wxpay.service.WxPayService;

/**
 * 微信支付 {@link WxPayService} 所有实例存放类.
 *
 * @author Binary Wang
 */
public interface WxPayMultiServices {
  /**
   * 通过配置标识或appId获取 WxPayService.
   *
   * @param configKey 配置标识或appId
   * @return WxPayService
   */
  WxPayService getWxPayService(String configKey);

  /**
   * 根据配置标识或appId，从列表中移除一个 WxPayService 实例.
   *
   * @param configKey 配置标识或appId
   */
  void removeWxPayService(String configKey);
}
