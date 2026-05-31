package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelBasicService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.shop.ShopH5UrlResponse;
import me.chanjar.weixin.channel.bean.shop.ShopQrCodeResponse;
import me.chanjar.weixin.channel.bean.shop.ShopTagLinkResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 店铺链接接口 测试
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelShopLinkServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetShopH5Url() throws WxErrorException {
    WxChannelBasicService basicService = channelService.getBasicService();
    ShopH5UrlResponse response = basicService.getShopH5Url();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testGetShopQrCode() throws WxErrorException {
    WxChannelBasicService basicService = channelService.getBasicService();
    // qrcodeType: 1=二维码, 2=标准物料, 3=送礼物物料
    ShopQrCodeResponse response = basicService.getShopQrCode(1);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testGetShopTagLink() throws WxErrorException {
    WxChannelBasicService basicService = channelService.getBasicService();
    ShopTagLinkResponse response = basicService.getShopTagLink();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }
}
