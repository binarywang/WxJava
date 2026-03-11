package com.binarywang.spring.starter.wxjava.miniapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.RequiredArgsConstructor;

/**
 * 微信小程序 {@link WxMaMultiServices} 共享式实现.
 * <p>
 * 使用单个 WxMaService 实例管理多个租户配置，通过 switchover 切换租户。
 * 相比 {@link WxMaMultiServicesImpl}，此实现共享 HTTP 客户端，节省资源。
 * </p>
 * <p>
 * 注意：由于使用 ThreadLocal 切换配置，在异步或多线程场景需要特别注意线程上下文切换。
 * </p>
 *
 * @author Binary Wang
 * created on 2026/1/9
 */
@RequiredArgsConstructor
public class WxMaMultiServicesSharedImpl implements WxMaMultiServices {
  private final WxMaService sharedWxMaService;

  @Override
  public WxMaService getWxMaService(String tenantId) {
    if (tenantId == null) {
      return null;
    }
    // 使用 switchover 检查配置是否存在，保持与隔离模式 API 行为一致（不存在时返回 null）
    if (!sharedWxMaService.switchover(tenantId)) {
      return null;
    }
    return sharedWxMaService;
  }

  @Override
  public void removeWxMaService(String tenantId) {
    if (tenantId != null) {
      sharedWxMaService.removeConfig(tenantId);
    }
  }

  /**
   * 添加租户配置到共享的 WxMaService 实例
   *
   * @param tenantId    租户 ID
   * @param wxMaService 要添加配置的 WxMaService（仅使用其配置，不使用其实例）
   */
  public void addWxMaService(String tenantId, WxMaService wxMaService) {
    if (tenantId != null && wxMaService != null) {
      sharedWxMaService.addConfig(tenantId, wxMaService.getWxMaConfig());
    }
  }
}
