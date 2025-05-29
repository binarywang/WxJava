package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.config.impl.WxMpMapConfigImpl;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * 测试 ConcurrentHashMap 保存配置信息
 * @author jimmyjimmy-sw
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpMapConfigImplTest {

  @Inject
  private WxMpService wxService;

  /**
   * 测试多租户保存 WxMpMapConfigImpl 到 WxMpService，切换之后能获取到租户各自AppId对应的token
   * @throws WxErrorException
   */
  @Test
  public void testAppidSwitch() throws WxErrorException {
    // 保存租户A的配置信息，并获取token
    WxMpMapConfigImpl configAppA = new WxMpMapConfigImpl();
    String appidA = "APPID_A";
    configAppA.setAppId(appidA);
    configAppA.setSecret("APP_SECRET_A");
    configAppA.useStableAccessToken(true);
    String tokenA = "TOKEN_A";
    configAppA.updateAccessToken(tokenA,60 * 60 * 1);
    wxService.addConfigStorage(appidA, configAppA);
    WxMpConfigStorageHolder.set(appidA);
    assertEquals(this.wxService.getAccessToken(),tokenA);

    // 保存租户B的配置信息，并获取token
    WxMpMapConfigImpl configAppB = new WxMpMapConfigImpl();
    String appidB = "APPID_B";
    configAppB.setAppId(appidB);
    configAppB.setSecret("APP_SECRET_B");
    configAppB.useStableAccessToken(true);
    String tokenB = "TOKEN_B";
    configAppB.updateAccessToken(tokenB,60 * 60 * 1);
    wxService.addConfigStorage(appidB, configAppB);
    WxMpConfigStorageHolder.set(appidB);
    assertEquals(this.wxService.getAccessToken(),tokenB);

    // 上下文切换到租户A 获取租户A的token
    WxMpConfigStorageHolder.set(appidA);
    assertEquals(this.wxService.getAccessToken(),tokenA);
  }
}
