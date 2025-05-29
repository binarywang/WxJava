package me.chanjar.weixin.channel.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelCompassFinderService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.compass.finder.OverallResponse;
import me.chanjar.weixin.channel.bean.compass.finder.ProductDataResponse;
import me.chanjar.weixin.channel.bean.compass.finder.ProductListResponse;
import me.chanjar.weixin.channel.bean.compass.finder.SaleProfileDataResponse;
import me.chanjar.weixin.channel.enums.SaleProfileUserType;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelCompassFinderServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetOverAll() throws WxErrorException {
    WxChannelCompassFinderService compassFinderService = channelService.getCompassFinderService();
    String ds = "20240630";
    OverallResponse response = compassFinderService.getOverall(ds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductData() throws WxErrorException {
    WxChannelCompassFinderService compassFinderService = channelService.getCompassFinderService();
    String ds = "20240630";
    String productId = "10000017457793";
    ProductDataResponse response = compassFinderService.getProductData(ds, productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductList() throws WxErrorException {
    WxChannelCompassFinderService compassFinderService = channelService.getCompassFinderService();
    String ds = "20240630";
    ProductListResponse response = compassFinderService.getProductList(ds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetSaleProfileData() throws WxErrorException {
    WxChannelCompassFinderService compassFinderService = channelService.getCompassFinderService();
    String ds = "20240630";
    Integer type = SaleProfileUserType.PRODUCT_IMPRESSION_USER.getKey();
    SaleProfileDataResponse response = compassFinderService.getSaleProfileData(ds, type);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

}
