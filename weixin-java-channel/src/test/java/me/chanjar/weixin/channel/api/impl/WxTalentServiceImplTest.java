package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxTalentService;
import me.chanjar.weixin.channel.bean.talent.TalentOrderDetailParam;
import me.chanjar.weixin.channel.bean.talent.TalentOrderDetailResponse;
import me.chanjar.weixin.channel.bean.talent.TalentOrderListParam;
import me.chanjar.weixin.channel.bean.talent.TalentOrderListResponse;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductDetailParam;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductDetailResponse;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductListParam;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductListResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 带货助手服务测试
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxTalentServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetOrderList() throws WxErrorException {
    WxTalentService talentService = channelService.getTalentService();
    TalentOrderListParam param = new TalentOrderListParam();
    TalentOrderListResponse response = talentService.getOrderList(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetOrderDetail() throws WxErrorException {
    WxTalentService talentService = channelService.getTalentService();
    TalentOrderDetailParam param = new TalentOrderDetailParam();
    param.setOrderId("");
    TalentOrderDetailResponse response = talentService.getOrderDetail(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWindowProductList() throws WxErrorException {
    WxTalentService talentService = channelService.getTalentService();
    TalentWindowProductListParam param = new TalentWindowProductListParam();
    TalentWindowProductListResponse response = talentService.getWindowProductList(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWindowProductDetail() throws WxErrorException {
    WxTalentService talentService = channelService.getTalentService();
    TalentWindowProductDetailParam param = new TalentWindowProductDetailParam();
    param.setProductId("");
    TalentWindowProductDetailResponse response = talentService.getWindowProductDetail(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
