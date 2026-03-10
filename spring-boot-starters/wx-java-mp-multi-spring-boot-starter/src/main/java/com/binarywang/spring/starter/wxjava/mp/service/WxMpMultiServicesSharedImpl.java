package com.binarywang.spring.starter.wxjava.mp.service;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 微信公众号 {@link WxMpMultiServices} 共享式实现.
 * <p>
 * 使用单个 WxMpService 实例管理多个租户配置，通过 switchover 切换租户。
 * 相比 {@link WxMpMultiServicesImpl}，此实现共享 HTTP 客户端，节省资源。
 * </p>
 * <p>
 * 注意：由于使用 ThreadLocal 切换配置，在异步或多线程场景需要特别注意线程上下文切换。
 * </p>
 *
 * @author Binary Wang
 * created on 2026/1/9
 */
@RequiredArgsConstructor
public class WxMpMultiServicesSharedImpl implements WxMpMultiServices {
  private final WxMpService sharedWxMpService;

  @Override
  public WxMpService getWxMpService(String tenantId) {
    if (tenantId == null) {
      return null;
    }
    // 使用 switchover 检查配置是否存在，保持与隔离模式 API 行为一致（不存在时返回 null）
    if (!sharedWxMpService.switchover(tenantId)) {
      return null;
    }
    return sharedWxMpService;
  }

  @Override
  public void removeWxMpService(String tenantId) {
    if (tenantId != null) {
      sharedWxMpService.removeConfigStorage(tenantId);
    }
  }

  /**
   * 添加租户配置到共享的 WxMpService 实例
   *
   * @param tenantId     租户 ID
   * @param wxMpService  要添加配置的 WxMpService（仅使用其配置，不使用其实例）
   */
  public void addWxMpService(String tenantId, WxMpService wxMpService) {
    if (tenantId != null && wxMpService != null) {
      sharedWxMpService.addConfigStorage(tenantId, wxMpService.getWxMpConfigStorage());
    }
  }
}
