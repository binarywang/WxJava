package com.binarywang.spring.starter.wxjava.cp.service;


import me.chanjar.weixin.cp.tp.service.WxCpTpService;

/**
 * 企业微信 {@link WxCpTpService} 所有实例存放类.
 *
 * @author yl
 * created on 2023/10/16
 */
public interface WxCpTpMultiServices {
  /**
   * 通过租户 Id 获取 WxCpTpService
   *
   * @param tenantId 租户 Id
   * @return WxCpTpService
   */
  WxCpTpService getWxCpTpService(String tenantId);

  void addWxCpTpService(String tenantId, WxCpTpService wxCpService);

  /**
   * 根据租户 Id，从列表中移除一个 WxCpTpService 实例
   *
   * @param tenantId 租户 Id
   */
  void removeWxCpTpService(String tenantId);
}
