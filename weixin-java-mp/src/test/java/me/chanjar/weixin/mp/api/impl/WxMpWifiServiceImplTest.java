package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.wifi.WxMpWifiShopDataResult;
import me.chanjar.weixin.mp.bean.wifi.WxMpWifiShopListResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Wifi.BIZWIFI_SHOP_GET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpWifiServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testListShop() throws WxErrorException {
    final WxMpWifiShopListResult result = this.wxService.getWifiService().listShop(1, 2);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testGetShopWifiInfo() throws WxErrorException {
    final WxMpWifiShopDataResult wifiInfo = this.wxService.getWifiService().getShopWifiInfo(123);
    assertThat(wifiInfo).isNotNull();
    System.out.println(wifiInfo);
  }

  @Test
  public void testUpdateShopWifiInfo() throws WxErrorException {
    final boolean result = this.wxService.getWifiService()
      .updateShopWifiInfo(123, "123", "345", null);
    assertThat(result).isTrue();
  }

  public static class MockTest {
    private WxMpService wxService = mock(WxMpService.class);

    @Test
    public void testGetShopWifiInfo() throws Exception {
      String returnJson = "{\n" +
        "  \"errcode\": 0,\n" +
        "  \"data\": {\n" +
        "    \"shop_name\": \"南山店\",\n" +
        "    \"ssid\": \" WX123\",\n" +
        "    \"ssid_list\": [\n" +
        "      \"WX123\",\n" +
        "      \"WX456\"\n" +
        "    ],\n" +
        "    \"ssid_password_list\": [\n" +
        "      {\n" +
        "        \"ssid\": \"WX123\",\n" +
        "        \"password\": \"123456789\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"ssid\": \"WX456\",\n" +
        "        \"password\": \"21332465dge\"\n" +
        "      }\n" +
        "    ],\n" +
        "    \"password\": \"123456789\",\n" +
        "    \"protocol_type\": 4,\n" +
        "    \"ap_count\": 2,\n" +
        "    \"template_id\": 1,\n" +
        "    \"homepage_url\": \"http://www.weixin.qq.com/\",\n" +
        "    \"bar_type\": 1,\n" +
        "    \"sid\":\"\",\n" +
        "    \"poi_id\":\"285633617\"\n" +
        "  }\n" +
        "}";

      when(wxService.post(eq(BIZWIFI_SHOP_GET), anyString())).thenReturn(returnJson);
      when(wxService.getWifiService()).thenReturn(new WxMpWifiServiceImpl(wxService));

      final WxMpWifiShopDataResult wifiInfo = this.wxService.getWifiService().getShopWifiInfo(123);
      assertThat(wifiInfo).isNotNull();
      System.out.println(wifiInfo);

    }
  }
}
