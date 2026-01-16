package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * 测试一个商户号配置多个appId的场景
 *
 * @author Binary Wang
 */
public class MultiAppIdSwitchoverTest {

  private WxPayService payService;
  private final String testMchId = "1234567890";
  private final String testAppId1 = "wx1111111111111111";
  private final String testAppId2 = "wx2222222222222222";
  private final String testAppId3 = "wx3333333333333333";

  @BeforeMethod
  public void setup() {
    payService = new WxPayServiceImpl();

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
  }

  /**
   * 测试使用 mchId + appId 精确切换（原有功能，确保向后兼容）
   */
  @Test
  public void testSwitchoverWithMchIdAndAppId() {
    // 切换到第一个配置
    boolean success = payService.switchover(testMchId, testAppId1);
    assertTrue(success);
    assertEquals(payService.getConfig().getAppId(), testAppId1);
    assertEquals(payService.getConfig().getMchKey(), "test_key_1");

    // 切换到第二个配置
    success = payService.switchover(testMchId, testAppId2);
    assertTrue(success);
    assertEquals(payService.getConfig().getAppId(), testAppId2);
    assertEquals(payService.getConfig().getMchKey(), "test_key_2");

    // 切换到第三个配置
    success = payService.switchover(testMchId, testAppId3);
    assertTrue(success);
    assertEquals(payService.getConfig().getAppId(), testAppId3);
    assertEquals(payService.getConfig().getMchKey(), "test_key_3");
  }

  /**
   * 测试仅使用 mchId 切换（新功能）
   * 应该能够成功切换到该商户号的某个配置
   */
  @Test
  public void testSwitchoverWithMchIdOnly() {
    // 仅使用商户号切换，应该能够成功切换到该商户号的某个配置
    boolean success = payService.switchover(testMchId);
    assertTrue(success, "应该能够通过mchId切换配置");

    // 验证配置确实是该商户号的配置之一
    WxPayConfig currentConfig = payService.getConfig();
    assertNotNull(currentConfig);
    assertEquals(currentConfig.getMchId(), testMchId);

    // appId应该是三个中的一个
    String currentAppId = currentConfig.getAppId();
    assertTrue(
      testAppId1.equals(currentAppId) || testAppId2.equals(currentAppId) || testAppId3.equals(currentAppId),
      "当前appId应该是配置的appId之一"
    );
  }

  /**
   * 测试 switchoverTo 方法（带链式调用，使用 mchId + appId）
   */
  @Test
  public void testSwitchoverToWithMchIdAndAppId() {
    WxPayService result = payService.switchoverTo(testMchId, testAppId2);
    assertNotNull(result);
    assertEquals(result, payService, "switchoverTo应该返回当前服务实例，支持链式调用");
    assertEquals(payService.getConfig().getAppId(), testAppId2);
  }

  /**
   * 测试 switchoverTo 方法（带链式调用，仅使用 mchId）
   */
  @Test
  public void testSwitchoverToWithMchIdOnly() {
    WxPayService result = payService.switchoverTo(testMchId);
    assertNotNull(result);
    assertEquals(result, payService, "switchoverTo应该返回当前服务实例，支持链式调用");
    assertEquals(payService.getConfig().getMchId(), testMchId);
  }

  /**
   * 测试切换到不存在的商户号
   */
  @Test
  public void testSwitchoverToNonexistentMchId() {
    boolean success = payService.switchover("nonexistent_mch_id");
    assertFalse(success, "切换到不存在的商户号应该失败");
  }

  /**
   * 测试 switchoverTo 切换到不存在的商户号（应该抛出异常）
   */
  @Test(expectedExceptions = WxRuntimeException.class)
  public void testSwitchoverToNonexistentMchIdThrowsException() {
    payService.switchoverTo("nonexistent_mch_id");
  }

  /**
   * 测试切换到不存在的 mchId + appId 组合
   */
  @Test
  public void testSwitchoverToNonexistentAppId() {
    boolean success = payService.switchover(testMchId, "wx9999999999999999");
    assertFalse(success, "切换到不存在的appId应该失败");
  }

  /**
   * 测试添加配置后能够正常切换
   */
  @Test
  public void testAddConfigAndSwitchover() {
    String newAppId = "wx4444444444444444";

    // 动态添加一个新的配置
    WxPayConfig newConfig = new WxPayConfig();
    newConfig.setMchId(testMchId);
    newConfig.setAppId(newAppId);
    newConfig.setMchKey("test_key_4");

    payService.addConfig(testMchId, newAppId, newConfig);

    // 切换到新添加的配置
    boolean success = payService.switchover(testMchId, newAppId);
    assertTrue(success);
    assertEquals(payService.getConfig().getAppId(), newAppId);
    assertEquals(payService.getConfig().getMchKey(), "test_key_4");

    // 使用仅mchId切换也应该能够找到配置
    success = payService.switchover(testMchId);
    assertTrue(success);
    assertEquals(payService.getConfig().getMchId(), testMchId);
  }

  /**
   * 测试移除配置后切换
   */
  @Test
  public void testRemoveConfigAndSwitchover() {
    // 移除一个配置
    payService.removeConfig(testMchId, testAppId1);

    // 切换到已移除的配置应该失败
    boolean success = payService.switchover(testMchId, testAppId1);
    assertFalse(success);

    // 但仍然能够切换到其他配置
    success = payService.switchover(testMchId, testAppId2);
    assertTrue(success);

    // 使用仅mchId切换应该仍然有效（因为还有其他appId的配置）
    success = payService.switchover(testMchId);
    assertTrue(success);
  }

  /**
   * 测试单个配置的场景（确保向后兼容）
   */
  @Test
  public void testSingleConfig() {
    WxPayService singlePayService = new WxPayServiceImpl();
    WxPayConfig singleConfig = new WxPayConfig();
    singleConfig.setMchId("single_mch_id");
    singleConfig.setAppId("single_app_id");
    singleConfig.setMchKey("single_key");

    singlePayService.setConfig(singleConfig);

    // 直接获取配置应该成功
    assertEquals(singlePayService.getConfig().getMchId(), "single_mch_id");
    assertEquals(singlePayService.getConfig().getAppId(), "single_app_id");

    // 使用精确匹配切换
    boolean success = singlePayService.switchover("single_mch_id", "single_app_id");
    assertTrue(success);

    // 使用仅mchId切换
    success = singlePayService.switchover("single_mch_id");
    assertTrue(success);
  }

  /**
   * 测试空参数或null参数的处理
   */
  @Test
  public void testSwitchoverWithNullOrEmptyMchId() {
    // 测试 null 参数
    boolean success = payService.switchover(null);
    assertFalse(success, "使用null作为mchId应该返回false");

    // 测试空字符串
    success = payService.switchover("");
    assertFalse(success, "使用空字符串作为mchId应该返回false");

    // 测试空白字符串
    success = payService.switchover("   ");
    assertFalse(success, "使用空白字符串作为mchId应该返回false");
  }

  /**
   * 测试 switchoverTo 方法对空参数或null参数的处理
   */
  @Test(expectedExceptions = WxRuntimeException.class)
  public void testSwitchoverToWithNullMchId() {
    payService.switchoverTo((String) null);
  }

  @Test(expectedExceptions = WxRuntimeException.class)
  public void testSwitchoverToWithEmptyMchId() {
    payService.switchoverTo("");
  }

  @Test(expectedExceptions = WxRuntimeException.class)
  public void testSwitchoverToWithBlankMchId() {
    payService.switchoverTo("   ");
  }

  /**
   * 测试商户号存在包含关系的场景
   * 例如同时配置 "123" 和 "1234"，验证前缀匹配不会错误匹配
   */
  @Test
  public void testSwitchoverWithOverlappingMchIds() {
    WxPayService testService = new WxPayServiceImpl();

    // 配置两个有包含关系的商户号
    String mchId1 = "123";
    String mchId2 = "1234";
    String appId1 = "wx_app_123";
    String appId2 = "wx_app_1234";

    WxPayConfig config1 = new WxPayConfig();
    config1.setMchId(mchId1);
    config1.setAppId(appId1);
    config1.setMchKey("key_123");

    WxPayConfig config2 = new WxPayConfig();
    config2.setMchId(mchId2);
    config2.setAppId(appId2);
    config2.setMchKey("key_1234");

    Map<String, WxPayConfig> configMap = new HashMap<>();
    configMap.put(mchId1 + "_" + appId1, config1);
    configMap.put(mchId2 + "_" + appId2, config2);
    testService.setMultiConfig(configMap);

    // 切换到 "123"，应该只匹配 "123_wx_app_123"
    boolean success = testService.switchover(mchId1);
    assertTrue(success);
    assertEquals(testService.getConfig().getMchId(), mchId1);
    assertEquals(testService.getConfig().getAppId(), appId1);

    // 切换到 "1234"，应该只匹配 "1234_wx_app_1234"
    success = testService.switchover(mchId2);
    assertTrue(success);
    assertEquals(testService.getConfig().getMchId(), mchId2);
    assertEquals(testService.getConfig().getAppId(), appId2);

    // 精确切换验证
    success = testService.switchover(mchId1, appId1);
    assertTrue(success);
    assertEquals(testService.getConfig().getAppId(), appId1);

    success = testService.switchover(mchId2, appId2);
    assertTrue(success);
    assertEquals(testService.getConfig().getAppId(), appId2);
  }
}
