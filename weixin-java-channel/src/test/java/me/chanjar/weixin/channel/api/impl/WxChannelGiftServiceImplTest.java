package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelGiftService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.gift.GiftGetResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductInfo;
import me.chanjar.weixin.channel.bean.gift.GiftProductListResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductUpdateInfo;
import me.chanjar.weixin.channel.bean.gift.GiftSetParam;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 视频号小店 赠品管理服务 测试
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelGiftServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testAddGiftProduct() throws WxErrorException {
    WxChannelGiftService giftService = channelService.getGiftService();
    GiftProductInfo info = new GiftProductInfo();
    info.setTitle("测试赠品");
    GiftProductResponse response = giftService.addGiftProduct(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateGiftProduct() throws WxErrorException {
    WxChannelGiftService giftService = channelService.getGiftService();
    GiftProductUpdateInfo info = new GiftProductUpdateInfo();
    info.setProductId("");
    GiftProductResponse response = giftService.updateGiftProduct(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSetProductAsGift() throws WxErrorException {
    WxChannelGiftService giftService = channelService.getGiftService();
    GiftSetParam param = new GiftSetParam();
    param.setProductId("");
    GiftProductListResponse response = giftService.setProductAsGift(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetGiftProduct() throws WxErrorException {
    WxChannelGiftService giftService = channelService.getGiftService();
    String productId = "";
    Integer dataType = 3;
    GiftGetResponse response = giftService.getGiftProduct(productId, dataType);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListGiftProduct() throws WxErrorException {
    WxChannelGiftService giftService = channelService.getGiftService();
    Integer pageSize = 10;
    String nextKey = null;
    Integer status = null;
    GiftProductListResponse response = giftService.listGiftProduct(pageSize, nextKey, status);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateGiftStock() throws WxErrorException {
    WxChannelGiftService giftService = channelService.getGiftService();
    String productId = "";
    String skuId = "";
    Integer diffType = 1;
    Integer num = 10;
    WxChannelBaseResponse response = giftService.updateGiftStock(productId, skuId, diffType, num);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
