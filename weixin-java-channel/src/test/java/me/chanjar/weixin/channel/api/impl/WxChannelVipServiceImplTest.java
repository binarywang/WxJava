package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.vip.VipInfoResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelVipServiceImplTest {
  @Inject
  private WxChannelService channelService;

  @Test
  public void getVipInfo() throws WxErrorException {
    String openId = "";
    Boolean needPhoneNumber = false;
    VipInfoResponse response = channelService.getVipService().getVipInfo(openId, needPhoneNumber);
    System.out.println(JsonUtils.encode(response));
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
