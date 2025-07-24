package com.binarywang.solon.wxjava.channel.service;

import me.chanjar.weixin.channel.api.WxChannelService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 视频号 {@link WxChannelMultiServices} 实现
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
public class WxChannelMultiServicesImpl implements WxChannelMultiServices {
  private final Map<String, WxChannelService> services = new ConcurrentHashMap<>();

  @Override
  public WxChannelService getWxChannelService(String tenantId) {
    return this.services.get(tenantId);
  }

  /**
   * 根据租户 Id，添加一个 WxChannelService 到列表
   *
   * @param tenantId         租户 Id
   * @param wxChannelService WxChannelService 实例
   */
  public void addWxChannelService(String tenantId, WxChannelService wxChannelService) {
    this.services.put(tenantId, wxChannelService);
  }

  @Override
  public void removeWxChannelService(String tenantId) {
    this.services.remove(tenantId);
  }
}
