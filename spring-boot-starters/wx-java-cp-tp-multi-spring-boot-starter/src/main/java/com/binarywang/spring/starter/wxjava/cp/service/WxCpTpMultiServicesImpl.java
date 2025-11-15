package com.binarywang.spring.starter.wxjava.cp.service;


import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 企业微信 {@link WxCpTpMultiServices} 默认实现
 *
 * @author yl
 * created on 2023/10/16
 */
public class WxCpTpMultiServicesImpl implements WxCpTpMultiServices {
  private final Map<String, WxCpTpService> services = new ConcurrentHashMap<>();

  /**
   * 通过租户 Id 获取 WxCpTpService
   *
   * @param tenantId 租户 Id
   * @return WxCpTpService
   */
  @Override
  public WxCpTpService getWxCpTpService(String tenantId) {
    return this.services.get(tenantId);
  }

  /**
   * 根据租户 Id，添加一个 WxCpTpService 到列表
   *
   * @param tenantId    租户 Id
   * @param wxCpService WxCpTpService 实例
   */
  @Override
  public void addWxCpTpService(String tenantId, WxCpTpService wxCpService) {
    this.services.put(tenantId, wxCpService);
  }

  @Override
  public void removeWxCpTpService(String tenantId) {
    this.services.remove(tenantId);
  }
}
