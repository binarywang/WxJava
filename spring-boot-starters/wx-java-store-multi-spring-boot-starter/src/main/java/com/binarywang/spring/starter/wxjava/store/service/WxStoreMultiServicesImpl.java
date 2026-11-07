package com.binarywang.spring.starter.wxjava.store.service;

import com.binarywang.wxjava.store.api.WxStoreService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 视频号 {@link WxStoreMultiServices} 实现
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
public class WxStoreMultiServicesImpl implements WxStoreMultiServices {
  private final Map<String, WxStoreService> services = new ConcurrentHashMap<>();

  @Override
  public WxStoreService getWxStoreService(String tenantId) {
    return this.services.get(tenantId);
  }

  /**
   * 根据租户 Id，添加一个 WxStoreService 到列表
   *
   * @param tenantId         租户 Id
   * @param wxStoreService WxStoreService 实例
   */
  public void addWxStoreService(String tenantId, WxStoreService wxStoreService) {
    this.services.put(tenantId, wxStoreService);
  }

  @Override
  public void removeWxStoreService(String tenantId) {
    this.services.remove(tenantId);
  }
}
