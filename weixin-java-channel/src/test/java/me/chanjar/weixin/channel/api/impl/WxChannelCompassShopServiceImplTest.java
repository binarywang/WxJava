package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelCompassShopService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.compass.shop.FinderAuthListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderOverallResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderProductListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderProductOverallResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopLiveListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopOverallResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopProductDataResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopProductListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopSaleProfileDataResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelCompassShopServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetShopOverall() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "20240306";
    ShopOverallResponse response = service.getShopOverall(ds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFinderAuthorizationList() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    FinderAuthListResponse response = service.getFinderAuthorizationList();
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFinderList() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "20240306";
    FinderListResponse response = service.getFinderList(ds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFinderOverall() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "20240306";
    FinderOverallResponse response = service.getFinderOverall(ds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFinderProductList() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "";
    String finderId = "";
    FinderProductListResponse response = service.getFinderProductList(ds, finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFinderProductOverall() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "";
    String finderId = "";
    FinderProductOverallResponse response = service.getFinderProductOverall(ds, finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetShopLiveList() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "";
    String finderId = "";
    ShopLiveListResponse response = service.getShopLiveList(ds, finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetShopProductData() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "20240306";
    String productId = "";
    ShopProductDataResponse response = service.getShopProductData(ds, productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetShopProductList() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "20240306";
    ShopProductListResponse response = service.getShopProductList(ds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetShopSaleProfileData() throws WxErrorException {
    WxChannelCompassShopService service = channelService.getCompassShopService();
    String ds = "20240306";
    ShopSaleProfileDataResponse response = service.getShopSaleProfileData(ds, 3);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
