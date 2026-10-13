package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.service.WxPayService;
import org.testng.Assert;
import org.testng.annotations.Test;

/** 连锁品牌服务入口兼容性测试。 */
public class BrandServiceEntryTest {
  @Test
  public void shouldExposeBrandServicesOnAllHttpClients() throws Exception {
    String[] getters = {"getBrandApplymentService", "getBrandStoreService", "getBrandProfitSharingService"};
    WxPayService[] clients = {new WxPayServiceImpl(), new WxPayServiceApacheHttpImpl(),
      new WxPayServiceHttpComponentsImpl(), new WxPayServiceJoddHttpImpl()};
    for (String getter : getters) {
      Assert.assertTrue(WxPayService.class.getMethod(getter).isDefault());
      for (WxPayService client : clients) {
        Object service = WxPayService.class.getMethod(getter).invoke(client);
        Assert.assertNotNull(service);
        Assert.assertSame(WxPayService.class.getMethod(getter).invoke(client), service);
      }
    }
  }
}
