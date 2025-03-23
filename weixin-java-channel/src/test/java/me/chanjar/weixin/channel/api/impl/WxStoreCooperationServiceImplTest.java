package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxStoreCooperationService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxStoreCooperationServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testListCooperation() throws WxErrorException {
    WxStoreCooperationService service = channelService.getCooperationService();
    Integer sharerType = 3;
    WxChannelBaseResponse response = service.listCooperation(sharerType);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testGetCooperationStatus() throws WxErrorException {
    WxStoreCooperationService service = channelService.getCooperationService();
    WxChannelBaseResponse response = service.getCooperationStatus("sph3FZbOEY64mWQ", 2);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGenerateQrCode() throws WxErrorException {
    WxStoreCooperationService service = channelService.getCooperationService();
    WxChannelBaseResponse response = service.generateQrCode("sph3FZbOEY64mWQ", 2);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCancelInvitation() throws WxErrorException {
    WxStoreCooperationService service = channelService.getCooperationService();
    WxChannelBaseResponse response = service.cancelInvitation("sph3FZbOEY64mWQ", 2);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUnbind() throws WxErrorException {
    WxStoreCooperationService service = channelService.getCooperationService();
    WxChannelBaseResponse response = service.unbind("sph3FZbOEY64mWQ", 2);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
