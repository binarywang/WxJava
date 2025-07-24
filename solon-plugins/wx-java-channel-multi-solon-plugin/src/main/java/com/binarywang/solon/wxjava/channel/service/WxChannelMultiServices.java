package com.binarywang.solon.wxjava.channel.service;

import me.chanjar.weixin.channel.api.WxChannelService;

/**
 * 视频号 {@link WxChannelService} 所有实例存放类.
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
public interface WxChannelMultiServices {
  /**
   * 通过租户 Id 获取 WxChannelService
   *
   * @param tenantId 租户 Id
   * @return WxChannelService
   */
  WxChannelService getWxChannelService(String tenantId);

  /**
   * 根据租户 Id，从列表中移除一个 WxChannelService 实例
   *
   * @param tenantId 租户 Id
   */
  void removeWxChannelService(String tenantId);
}
