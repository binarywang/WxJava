package com.binarywang.spring.starter.wxjava.open.service;

import me.chanjar.weixin.open.api.WxOpenService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 微信开放平台 {@link WxOpenMultiServices} 默认实现
 *
 * @author Binary Wang
 */
public class WxOpenMultiServicesImpl implements WxOpenMultiServices {
  private final Map<String, WxOpenService> services = new ConcurrentHashMap<>();

  @Override
  public WxOpenService getWxOpenService(String tenantId) {
    return this.services.get(tenantId);
  }

  /**
   * 根据租户 Id，添加一个 WxOpenService 到列表
   *
   * @param tenantId      租户 Id
   * @param wxOpenService WxOpenService 实例
   */
  public void addWxOpenService(String tenantId, WxOpenService wxOpenService) {
    this.services.put(tenantId, wxOpenService);
  }

  @Override
  public void removeWxOpenService(String tenantId) {
    this.services.remove(tenantId);
  }
}
