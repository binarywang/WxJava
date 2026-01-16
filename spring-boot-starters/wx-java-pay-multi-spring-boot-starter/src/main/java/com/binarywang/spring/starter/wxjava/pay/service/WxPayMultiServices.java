package com.binarywang.spring.starter.wxjava.pay.service;

import com.github.binarywang.wxpay.service.WxPayService;

/**
 * 微信支付 {@link WxPayService} 所有实例存放类.
 *
 * @author Binary Wang
 */
public interface WxPayMultiServices {
  /**
   * 通过配置标识获取 WxPayService.
   * <p>
   * 注意：configKey 是配置文件中定义的 key（如 wx.pay.configs.&lt;configKey&gt;.xxx），
   * 而不是 appId。如果使用 appId 作为配置 key，则可以直接传入 appId。
   * </p>
   *
   * @param configKey 配置标识（配置文件中 wx.pay.configs 下的 key）
   * @return WxPayService
   */
  WxPayService getWxPayService(String configKey);

  /**
   * 根据配置标识，从列表中移除一个 WxPayService 实例.
   * <p>
   * 注意：configKey 是配置文件中定义的 key（如 wx.pay.configs.&lt;configKey&gt;.xxx），
   * 而不是 appId。如果使用 appId 作为配置 key，则可以直接传入 appId。
   * </p>
   *
   * @param configKey 配置标识（配置文件中 wx.pay.configs 下的 key）
   */
  void removeWxPayService(String configKey);
}
