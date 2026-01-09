package com.binarywang.spring.starter.wxjava.miniapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 {@link WxMaMultiServicesSharedImpl} 共享式多租户实现
 *
 * @author Binary Wang
 * created on 2026/1/9
 */
class WxMaMultiServicesSharedImplTest {

  private WxMaMultiServicesSharedImpl multiServices;
  private WxMaService sharedService;

  @BeforeEach
  void setUp() {
    // 创建共享的 WxMaService 实例
    sharedService = new WxMaServiceImpl();

    // 准备多个租户配置
    Map<String, WxMaConfig> configs = new HashMap<>();
    
    WxMaDefaultConfigImpl config1 = new WxMaDefaultConfigImpl();
    config1.setAppid("tenant1-appid");
    config1.setSecret("tenant1-secret");
    configs.put("tenant1", config1);

    WxMaDefaultConfigImpl config2 = new WxMaDefaultConfigImpl();
    config2.setAppid("tenant2-appid");
    config2.setSecret("tenant2-secret");
    configs.put("tenant2", config2);

    // 设置多配置到共享服务
    sharedService.setMultiConfigs(configs, "tenant1");

    // 创建共享式多租户服务
    multiServices = new WxMaMultiServicesSharedImpl(sharedService);
  }

  @Test
  void testGetWxMaService_shouldReturnServiceWithCorrectConfig() {
    // 获取租户1的服务
    WxMaService service1 = multiServices.getWxMaService("tenant1");
    assertNotNull(service1, "应返回非空的 WxMaService");
    assertEquals("tenant1-appid", service1.getWxMaConfig().getAppid(), "应返回正确的租户1配置");

    // 获取租户2的服务
    WxMaService service2 = multiServices.getWxMaService("tenant2");
    assertNotNull(service2, "应返回非空的 WxMaService");
    assertEquals("tenant2-appid", service2.getWxMaConfig().getAppid(), "应返回正确的租户2配置");
  }

  @Test
  void testGetWxMaService_withNullTenantId_shouldReturnNull() {
    WxMaService service = multiServices.getWxMaService(null);
    assertNull(service, "传入 null 租户ID应返回 null");
  }

  @Test
  void testGetWxMaService_withNonExistentTenant_shouldThrowException() {
    assertThrows(RuntimeException.class, () -> {
      multiServices.getWxMaService("non-existent");
    }, "访问不存在的租户应抛出异常");
  }

  @Test
  void testAddWxMaService_shouldAddConfigToSharedService() {
    // 创建新租户的配置
    WxMaService newService = new WxMaServiceImpl();
    WxMaDefaultConfigImpl config3 = new WxMaDefaultConfigImpl();
    config3.setAppid("tenant3-appid");
    config3.setSecret("tenant3-secret");
    newService.setWxMaConfig(config3);

    // 添加新租户
    multiServices.addWxMaService("tenant3", newService);

    // 验证能够获取新租户的服务
    WxMaService service3 = multiServices.getWxMaService("tenant3");
    assertNotNull(service3, "应能获取新添加的租户服务");
    assertEquals("tenant3-appid", service3.getWxMaConfig().getAppid(), "应返回正确的租户3配置");
  }

  @Test
  void testRemoveWxMaService_shouldRemoveConfig() {
    // 先确认租户1存在
    WxMaService service1 = multiServices.getWxMaService("tenant1");
    assertNotNull(service1, "租户1应该存在");

    // 移除租户1
    multiServices.removeWxMaService("tenant1");

    // 验证租户1已被移除（应抛出异常）
    assertThrows(RuntimeException.class, () -> {
      multiServices.getWxMaService("tenant1");
    }, "移除后访问租户1应抛出异常");
  }

  @Test
  void testSharedHttpClient_allTenantsUseSameServiceInstance() {
    WxMaService service1 = multiServices.getWxMaService("tenant1");
    WxMaService service2 = multiServices.getWxMaService("tenant2");

    // 验证返回的是同一个服务实例（共享模式的核心特性）
    assertSame(service1, service2, "共享模式下，所有租户应使用同一个 WxMaService 实例");
    assertSame(service1, sharedService, "返回的服务应该就是共享的服务实例");
  }
}
