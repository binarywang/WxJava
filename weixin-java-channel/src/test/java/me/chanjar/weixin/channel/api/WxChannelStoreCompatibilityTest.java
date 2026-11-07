package me.chanjar.weixin.channel.api;

import java.lang.reflect.Method;
import me.chanjar.weixin.channel.bean.address.AddressAddParam;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Verifies that the store split keeps the legacy channel API available for existing consumers.
 */
public class WxChannelStoreCompatibilityTest {

  @Test
  public void shouldKeepLegacyStoreApisAndModelsDeprecated() throws NoSuchMethodException {
    Method productService = WxChannelService.class.getMethod("getProductService");

    Assert.assertEquals(productService.getReturnType(), WxChannelProductService.class);
    Assert.assertTrue(productService.isAnnotationPresent(Deprecated.class));
    Assert.assertTrue(AddressAddParam.class.isAnnotationPresent(Deprecated.class));
  }

  @Test
  public void shouldKeepChannelOnlyApisAvailableWithoutDeprecation() throws NoSuchMethodException {
    Method finderLiveService = WxChannelService.class.getMethod("getFinderLiveService");

    Assert.assertEquals(finderLiveService.getReturnType(), WxFinderLiveService.class);
    Assert.assertFalse(finderLiveService.isAnnotationPresent(Deprecated.class));
  }
}
