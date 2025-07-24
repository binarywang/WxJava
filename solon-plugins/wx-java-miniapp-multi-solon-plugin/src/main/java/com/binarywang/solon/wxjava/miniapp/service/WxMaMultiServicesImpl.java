package com.binarywang.solon.wxjava.miniapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 微信小程序 {@link WxMaMultiServices} 默认实现
 *
 * @author monch
 * created on 2024/9/6
 */
public class WxMaMultiServicesImpl implements WxMaMultiServices {
  private final Map<String, WxMaService> services = new ConcurrentHashMap<>();

  @Override
  public WxMaService getWxMaService(String tenantId) {
    return this.services.get(tenantId);
  }

  /**
   * 根据租户 Id，添加一个 WxMaService 到列表
   *
   * @param tenantId    租户 Id
   * @param wxMaService WxMaService 实例
   */
  public void addWxMaService(String tenantId, WxMaService wxMaService) {
    this.services.put(tenantId, wxMaService);
  }

  @Override
  public void removeWxMaService(String tenantId) {
    this.services.remove(tenantId);
  }
}
