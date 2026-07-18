package me.chanjar.weixin.cp.config.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

/**
 * 测试 WxCpDefaultConfigImpl 中会话存档 SDK 引用计数的正确性
 * 验证修复：SDK 在引用计数降为 0 但尚未过期时，不应被销毁
 *
 * @author GitHub Copilot
 */
public class WxCpDefaultConfigImplMsgAuditSdkTest {

  /**
   * 用于测试的未过期时间偏移量（毫秒），模拟 SDK 有效状态
   */
  private static final long VALID_EXPIRATION_TIME_OFFSET = 7_000_000L;

  private WxCpDefaultConfigImpl config;

  @BeforeMethod
  public void setUp() {
    config = new WxCpDefaultConfigImpl();
  }

  /**
   * 通过反射设置内部字段
   */
  private void setField(String fieldName, Object value) throws Exception {
    Field field = WxCpDefaultConfigImpl.class.getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(config, value);
  }

  /**
   * 通过反射获取内部字段值
   */
  private Object getField(String fieldName) throws Exception {
    Field field = WxCpDefaultConfigImpl.class.getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.get(config);
  }

  /**
   * 验证 acquireMsgAuditSdk 在 SDK 有效时能正确返回 SDK 并增加引用计数
   */
  @Test
  public void testAcquireMsgAuditSdkWhenSdkValid() throws Exception {
    long fakeSdk = 12345L;
    // 设置一个有效的（未过期的）SDK
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkExpiresTime", System.currentTimeMillis() + VALID_EXPIRATION_TIME_OFFSET);
    setField("msgAuditSdkRefCount", 0);

    long acquired = config.acquireMsgAuditSdk();

    Assert.assertEquals(acquired, fakeSdk, "应返回已缓存的有效 SDK");
    int refCount = (int) getField("msgAuditSdkRefCount");
    Assert.assertEquals(refCount, 1, "引用计数应增加到 1");
  }

  /**
   * 验证 acquireMsgAuditSdk 在 SDK 已过期时返回 0
   */
  @Test
  public void testAcquireMsgAuditSdkWhenSdkExpired() throws Exception {
    long fakeSdk = 12345L;
    // 设置已过期的 SDK
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkExpiresTime", System.currentTimeMillis() - 1000L);
    setField("msgAuditSdkRefCount", 0);

    long acquired = config.acquireMsgAuditSdk();

    Assert.assertEquals(acquired, 0L, "SDK 已过期，应返回 0");
    int refCount = (int) getField("msgAuditSdkRefCount");
    Assert.assertEquals(refCount, 0, "引用计数不应改变");
  }

  /**
   * 核心测试：验证当引用计数降为 0 但 SDK 尚未过期时，SDK 不会被销毁
   * 这是修复 issue 的关键验证：避免每次 API 调用后频繁销毁和重新初始化 SDK
   */
  @Test
  public void testReleaseMsgAuditSdkShouldNotDestroyWhenNotExpired() throws Exception {
    long fakeSdk = 12345L;
    // 设置一个有效的（未过期的）SDK，引用计数为 1
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkExpiresTime", System.currentTimeMillis() + VALID_EXPIRATION_TIME_OFFSET);
    setField("msgAuditSdkRefCount", 1);

    // 释放引用，引用计数应降为 0，但 SDK 尚未过期，不应被销毁
    config.releaseMsgAuditSdk(fakeSdk);

    long sdkAfterRelease = (long) getField("msgAuditSdk");
    int refCountAfterRelease = (int) getField("msgAuditSdkRefCount");

    Assert.assertEquals(sdkAfterRelease, fakeSdk, "SDK 尚未过期，引用计数归零后不应被销毁，应继续缓存");
    Assert.assertEquals(refCountAfterRelease, 0, "引用计数应为 0");
  }

  /**
   * 验证：SDK 在未过期、引用计数为 0 时，下次调用 acquireMsgAuditSdk 应直接复用，无需重新初始化
   * 这是修复后的核心行为：避免频繁初始化
   */
  @Test
  public void testSdkReuseAfterReleaseWhenNotExpired() throws Exception {
    long fakeSdk = 99999L;
    // 模拟：SDK 有效，引用计数为 1（正在被使用）
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkExpiresTime", System.currentTimeMillis() + VALID_EXPIRATION_TIME_OFFSET);
    setField("msgAuditSdkRefCount", 1);

    // 模拟方法调用结束，释放引用
    config.releaseMsgAuditSdk(fakeSdk);

    // 模拟下一次方法调用，应该直接复用缓存的 SDK
    long reacquired = config.acquireMsgAuditSdk();

    Assert.assertEquals(reacquired, fakeSdk, "SDK 应被复用，而不是返回 0（需要重新初始化）");
    int refCount = (int) getField("msgAuditSdkRefCount");
    Assert.assertEquals(refCount, 1, "复用后引用计数应为 1");
  }

  /**
   * 验证：多次 acquire/release 的引用计数正确性（串行验证）
   */
  @Test
  public void testMultipleAcquireAndReleaseSequential() throws Exception {
    long fakeSdk = 77777L;
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkExpiresTime", System.currentTimeMillis() + VALID_EXPIRATION_TIME_OFFSET);
    setField("msgAuditSdkRefCount", 0);

    // 三次 acquire，引用计数依次递增
    long sdk1 = config.acquireMsgAuditSdk();
    long sdk2 = config.acquireMsgAuditSdk();
    long sdk3 = config.acquireMsgAuditSdk();

    Assert.assertEquals(sdk1, fakeSdk);
    Assert.assertEquals(sdk2, fakeSdk);
    Assert.assertEquals(sdk3, fakeSdk);
    Assert.assertEquals((int) getField("msgAuditSdkRefCount"), 3, "应有 3 个引用");

    // 逐一释放，SDK 未过期，不应被销毁
    config.releaseMsgAuditSdk(fakeSdk);
    Assert.assertEquals((int) getField("msgAuditSdkRefCount"), 2, "释放一个后应有 2 个引用");
    Assert.assertEquals((long) getField("msgAuditSdk"), fakeSdk, "SDK 仍有引用，不应被销毁");

    config.releaseMsgAuditSdk(fakeSdk);
    Assert.assertEquals((int) getField("msgAuditSdkRefCount"), 1, "释放两个后应有 1 个引用");

    config.releaseMsgAuditSdk(fakeSdk);
    Assert.assertEquals((int) getField("msgAuditSdkRefCount"), 0, "全部释放后引用计数应为 0");
    // SDK 未过期，不应被销毁
    Assert.assertEquals((long) getField("msgAuditSdk"), fakeSdk, "SDK 未过期，全部引用释放后不应被销毁");
  }

  /**
   * 验证 incrementMsgAuditSdkRefCount 在 SDK 匹配时正确增加引用计数
   */
  @Test
  public void testIncrementRefCount() throws Exception {
    long fakeSdk = 11111L;
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkRefCount", 2);

    int result = config.incrementMsgAuditSdkRefCount(fakeSdk);

    Assert.assertEquals(result, 3, "引用计数应增加到 3");
  }

  /**
   * 验证 incrementMsgAuditSdkRefCount 在 SDK 不匹配时返回 -1
   */
  @Test
  public void testIncrementRefCountWithWrongSdk() throws Exception {
    setField("msgAuditSdk", 11111L);
    setField("msgAuditSdkRefCount", 2);

    int result = config.incrementMsgAuditSdkRefCount(99999L);

    Assert.assertEquals(result, -1, "SDK 不匹配时应返回 -1");
  }

  /**
   * 验证 getMsgAuditSdkRefCount 的正确性
   */
  @Test
  public void testGetMsgAuditSdkRefCount() throws Exception {
    long fakeSdk = 55555L;
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkRefCount", 5);

    int count = config.getMsgAuditSdkRefCount(fakeSdk);
    Assert.assertEquals(count, 5, "应返回正确的引用计数");

    int wrongCount = config.getMsgAuditSdkRefCount(99L);
    Assert.assertEquals(wrongCount, -1, "SDK 不匹配时应返回 -1");
  }

  /**
   * 验证：引用计数归零且 SDK 已过期时，releaseMsgAuditSdk 应尝试销毁 SDK
   * 由于 Finance.DestroySdk 是原生方法，测试环境中不加载原生库时会抛出 UnsatisfiedLinkError，
   * 但引用计数已在 Finance 调用前递减（可验证代码路径已进入销毁分支）。
   * 当原生库可用时，应进一步断言 msgAuditSdk 和 msgAuditSdkExpiresTime 均被清零。
   */
  @Test
  public void testReleaseMsgAuditSdkShouldDestroyWhenExpired() throws Exception {
    long fakeSdk = 22222L;
    // 设置已过期的 SDK，引用计数为 1
    setField("msgAuditSdk", fakeSdk);
    setField("msgAuditSdkExpiresTime", System.currentTimeMillis() - 1000L); // 已过期
    setField("msgAuditSdkRefCount", 1);

    try {
      config.releaseMsgAuditSdk(fakeSdk);
      // 原生库可用：断言字段已清零
      Assert.assertEquals((long) getField("msgAuditSdk"), 0L, "过期且引用归零后 msgAuditSdk 应被清零");
      Assert.assertEquals((long) getField("msgAuditSdkExpiresTime"), 0L, "过期时间应被清零");
    } catch (UnsatisfiedLinkError e) {
      // 测试环境未加载原生库：Finance.DestroySdk 被调用但抛出 UnsatisfiedLinkError
      // 这证明代码路径正确进入了"过期时销毁 SDK"的分支，与"未过期时跳过销毁"的分支形成对比
      Assert.assertEquals((int) getField("msgAuditSdkRefCount"), 0, "引用计数应已递减到 0（Finance 调用前完成）");
    }
  }
}
