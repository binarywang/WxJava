package com.binarywang.solon.wxjava.store.service;

import com.binarywang.wxjava.store.api.WxStoreService;

/**
 * 视频号 {@link WxStoreService} 所有实例存放类.
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
public interface WxStoreMultiServices {
  /**
   * 通过租户 Id 获取 WxStoreService
   *
   * @param tenantId 租户 Id
   * @return WxStoreService
   */
  WxStoreService getWxStoreService(String tenantId);

  /**
   * 根据租户 Id，从列表中移除一个 WxStoreService 实例
   *
   * @param tenantId 租户 Id
   */
  void removeWxStoreService(String tenantId);
}
