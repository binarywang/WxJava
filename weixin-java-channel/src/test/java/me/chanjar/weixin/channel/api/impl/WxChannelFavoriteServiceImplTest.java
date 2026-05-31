package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelFavoriteService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.favorite.FavoriteCountResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 收藏管理接口 测试
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelFavoriteServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetFavoriteCount() throws WxErrorException {
    WxChannelFavoriteService favoriteService = channelService.getFavoriteService();
    FavoriteCountResponse response = favoriteService.getFavoriteCount();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }
}
