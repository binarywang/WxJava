package com.binarywang.spring.starter.wxjava.open.service;


import me.chanjar.weixin.open.api.WxOpenService;

/**
 * 微信开放平台 {@link WxOpenService} 所有实例存放类.
 *
 * @author binarywang
 */
public interface WxOpenMultiServices {
  /**
   * 通过租户 Id 获取 WxOpenService
   *
   * @param tenantId 租户 Id
   * @return WxOpenService
   */
  WxOpenService getWxOpenService(String tenantId);

  /**
   * 根据租户 Id，从列表中移除一个 WxOpenService 实例
   *
   * @param tenantId 租户 Id
   */
  void removeWxOpenService(String tenantId);
}
