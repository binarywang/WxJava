package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;

import java.util.HashMap;
import java.util.Map;

/**
 * 手动验证多appId切换功能
 */
public class MultiAppIdSwitchoverManualTest {

  public static void main(String[] args) {
    WxPayService payService = new WxPayServiceImpl();

    String testMchId = "1234567890";
    String testAppId1 = "wx1111111111111111";
    String testAppId2 = "wx2222222222222222";
    String testAppId3 = "wx3333333333333333";

    // 配置同一个商户号，三个不同的appId
    WxPayConfig config1 = new WxPayConfig();
    config1.setMchId(testMchId);
    config1.setAppId(testAppId1);
    config1.setMchKey("test_key_1");

    WxPayConfig config2 = new WxPayConfig();
    config2.setMchId(testMchId);
    config2.setAppId(testAppId2);
    config2.setMchKey("test_key_2");

    WxPayConfig config3 = new WxPayConfig();
    config3.setMchId(testMchId);
    config3.setAppId(testAppId3);
    config3.setMchKey("test_key_3");

    Map<String, WxPayConfig> configMap = new HashMap<>();
    configMap.put(testMchId + "_" + testAppId1, config1);
    configMap.put(testMchId + "_" + testAppId2, config2);
    configMap.put(testMchId + "_" + testAppId3, config3);

    payService.setMultiConfig(configMap);

    // 测试1: 使用 mchId + appId 精确切换
    System.out.println("=== 测试1: 使用 mchId + appId 精确切换 ===");
    boolean success = payService.switchover(testMchId, testAppId1);
    System.out.println("切换结果: " + success);
    System.out.println("当前配置 - MchId: " + payService.getConfig().getMchId() + ", AppId: " + payService.getConfig().getAppId() + ", MchKey: " + payService.getConfig().getMchKey());
    verify(success, "切换应该成功");
    verify(testAppId1.equals(payService.getConfig().getAppId()), "AppId应该是 " + testAppId1);
    System.out.println("✓ 测试1通过\n");

    // 测试2: 仅使用 mchId 切换
    System.out.println("=== 测试2: 仅使用 mchId 切换 ===");
    success = payService.switchover(testMchId);
    System.out.println("切换结果: " + success);
    System.out.println("当前配置 - MchId: " + payService.getConfig().getMchId() + ", AppId: " + payService.getConfig().getAppId() + ", MchKey: " + payService.getConfig().getMchKey());
    verify(success, "仅使用mchId切换应该成功");
    verify(testMchId.equals(payService.getConfig().getMchId()), "MchId应该是 " + testMchId);
    System.out.println("✓ 测试2通过\n");

    // 测试3: 使用 switchoverTo 链式调用（精确匹配）
    System.out.println("=== 测试3: 使用 switchoverTo 链式调用（精确匹配） ===");
    WxPayService result = payService.switchoverTo(testMchId, testAppId2);
    System.out.println("返回对象: " + (result == payService ? "同一实例" : "不同实例"));
    System.out.println("当前配置 - MchId: " + payService.getConfig().getMchId() + ", AppId: " + payService.getConfig().getAppId() + ", MchKey: " + payService.getConfig().getMchKey());
    verify(result == payService, "应该返回同一实例");
    verify(testAppId2.equals(payService.getConfig().getAppId()), "AppId应该是 " + testAppId2);
    System.out.println("✓ 测试3通过\n");

    // 测试4: 使用 switchoverTo 链式调用（仅mchId）
    System.out.println("=== 测试4: 使用 switchoverTo 链式调用（仅mchId） ===");
    result = payService.switchoverTo(testMchId);
    System.out.println("返回对象: " + (result == payService ? "同一实例" : "不同实例"));
    System.out.println("当前配置 - MchId: " + payService.getConfig().getMchId() + ", AppId: " + payService.getConfig().getAppId() + ", MchKey: " + payService.getConfig().getMchKey());
    verify(result == payService, "应该返回同一实例");
    verify(testMchId.equals(payService.getConfig().getMchId()), "MchId应该是 " + testMchId);
    System.out.println("✓ 测试4通过\n");

    // 测试5: 切换到不存在的商户号
    System.out.println("=== 测试5: 切换到不存在的商户号 ===");
    success = payService.switchover("nonexistent_mch_id");
    System.out.println("切换结果: " + success);
    verify(!success, "切换到不存在的商户号应该失败");
    System.out.println("✓ 测试5通过\n");

    // 测试6: 切换到不存在的 appId
    System.out.println("=== 测试6: 切换到不存在的 appId ===");
    success = payService.switchover(testMchId, "wx9999999999999999");
    System.out.println("切换结果: " + success);
    verify(!success, "切换到不存在的appId应该失败");
    System.out.println("✓ 测试6通过\n");

    // 测试7: 添加新配置后切换
    System.out.println("=== 测试7: 添加新配置后切换 ===");
    String newAppId = "wx4444444444444444";
    WxPayConfig newConfig = new WxPayConfig();
    newConfig.setMchId(testMchId);
    newConfig.setAppId(newAppId);
    newConfig.setMchKey("test_key_4");
    payService.addConfig(testMchId, newAppId, newConfig);

    success = payService.switchover(testMchId, newAppId);
    System.out.println("切换结果: " + success);
    System.out.println("当前配置 - MchId: " + payService.getConfig().getMchId() + ", AppId: " + payService.getConfig().getAppId() + ", MchKey: " + payService.getConfig().getMchKey());
    verify(success, "切换到新添加的配置应该成功");
    verify(newAppId.equals(payService.getConfig().getAppId()), "AppId应该是 " + newAppId);
    System.out.println("✓ 测试7通过\n");

    System.out.println("==================");
    System.out.println("所有测试通过! ✓");
    System.out.println("==================");
  }

  /**
   * 验证条件是否为真，如果为假则抛出异常
   *
   * @param condition 待验证的条件
   * @param message   验证失败时的错误信息
   */
  private static void verify(boolean condition, String message) {
    if (!condition) {
      throw new RuntimeException("验证失败: " + message);
    }
  }
}
