package com.binarywang.wxjava.store.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/** Contract tests for the store module public entry point. */
public class WxStoreServiceContractTest {

  @Test
  public void shouldExposeIndependentStoreServiceType() throws ClassNotFoundException {
    Class<?> serviceType = Class.forName("com.binarywang.wxjava.store.api.WxStoreService");

    Assert.assertEquals(serviceType.getName(), "com.binarywang.wxjava.store.api.WxStoreService");
    Assert.assertNull(serviceType.getSuperclass());
  }

  @Test
  public void shouldNotExposeChannelOnlyServices() {
    Assert.assertFalse(Arrays.stream(WxStoreService.class.getMethods())
      .anyMatch(method -> method.getName().equals("getLeagueProductService")
        || method.getName().equals("getFinderLiveService")
        || method.getName().equals("getLiveDashboardService")
        || method.getName().equals("getLeadComponentService")));
  }

  @Test
  public void shouldExposeStoreCommerceServices() {
    Assert.assertTrue(Arrays.stream(WxStoreService.class.getMethods())
      .anyMatch(method -> method.getName().equals("getProductService")));
    Assert.assertTrue(Arrays.stream(WxStoreService.class.getMethods())
      .anyMatch(method -> method.getName().equals("getOrderService")));
    Assert.assertTrue(Arrays.stream(WxStoreService.class.getMethods())
      .anyMatch(method -> method.getName().equals("getAfterSaleService")));
    Assert.assertTrue(Arrays.stream(WxStoreService.class.getMethods())
      .anyMatch(method -> method.getName().equals("getFundService")));
  }
}
