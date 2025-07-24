package com.binarywang.solon.wxjava.miniapp.service;


import cn.binarywang.wx.miniapp.api.WxMaService;

/**
 * 微信小程序 {@link WxMaService} 所有实例存放类.
 *
 * @author monch
 * created on 2024/9/6
 */
public interface WxMaMultiServices {
  /**
   * 通过租户 Id 获取 WxMaService
   *
   * @param tenantId 租户 Id
   * @return WxMaService
   */
  WxMaService getWxMaService(String tenantId);

  /**
   * 根据租户 Id，从列表中移除一个 WxMaService 实例
   *
   * @param tenantId 租户 Id
   */
  void removeWxMaService(String tenantId);
}
