package me.chanjar.weixin.channel.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelLiveDashboardService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.live.dashboard.LiveDataResponse;
import me.chanjar.weixin.channel.bean.live.dashboard.LiveListResponse;
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
public class WxChannelLiveDashboardServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetLiveList() throws WxErrorException {
    WxChannelLiveDashboardService liveDashboardService = channelService.getLiveDashboardService();
    // yyyyMMdd
    Long ds = 20240630L;
    LiveListResponse response = liveDashboardService.getLiveList(ds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetLiveData() throws WxErrorException {
    WxChannelLiveDashboardService liveDashboardService = channelService.getLiveDashboardService();
    String exportId = "export/UzFf*****************************************************************************************64V";
    LiveDataResponse response = liveDashboardService.getLiveData(exportId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

}
