package me.chanjar.weixin.mp.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.testng.*;
import org.testng.annotations.*;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.result.WxMpCurrentAutoReplyInfo;
import me.chanjar.weixin.common.enums.TicketType;

import static org.testng.Assert.*;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMpServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testGetCurrentAutoReplyInfo() throws WxErrorException {
    WxMpCurrentAutoReplyInfo autoReplyInfo = this.wxService.getCurrentAutoReplyInfo();

    assertNotNull(autoReplyInfo);
    System.out.println(autoReplyInfo);
  }

  @Test
  public void testClearQuota() throws WxErrorException {
    this.wxService.clearQuota(wxService.getWxMpConfigStorage().getAppId());
  }

  @Test
  public void testBuildQrConnectUrl() {
    String qrconnectRedirectUrl = ((TestConfigStorage) this.wxService.getWxMpConfigStorage()).getQrconnectRedirectUrl();
    String qrConnectUrl = this.wxService.buildQrConnectUrl(qrconnectRedirectUrl,
      WxConsts.QrConnectScope.SNSAPI_LOGIN, null);
    Assert.assertNotNull(qrConnectUrl);
    System.out.println(qrConnectUrl);
  }

  @Test
  public void testBuildQrConnectRedirectUrl() {
    String qrConnectRedirectUrl = this.wxService.getWxMpConfigStorage().getQrConnectRedirectUrl();
    String qrConnectUrl = this.wxService.buildQrConnectUrl(qrConnectRedirectUrl,
      WxConsts.QrConnectScope.SNSAPI_LOGIN, null);
    Assert.assertNotNull(qrConnectUrl);
    System.out.println(qrConnectUrl);
  }

  public void testGetTicket() throws WxErrorException {
    String ticket = this.wxService.getTicket(TicketType.SDK, false);
    System.out.println(ticket);
    Assert.assertNotNull(ticket);
  }

  public void testRefreshAccessToken() throws WxErrorException {
    WxMpConfigStorage configStorage = this.wxService.getWxMpConfigStorage();
    String before = configStorage.getAccessToken();
    this.wxService.getAccessToken(false);

    String after = configStorage.getAccessToken();
    Assert.assertNotEquals(before, after);
    Assert.assertTrue(StringUtils.isNotBlank(after));
  }

  public void testStableRefreshAccessToken() throws WxErrorException {
    WxMpConfigStorage configStorage = this.wxService.getWxMpConfigStorage();
    configStorage.useStableAccessToken(true);
    String before = configStorage.getAccessToken();
    this.wxService.getAccessToken(false);

    String after = configStorage.getAccessToken();
    Assert.assertNotEquals(before, after);
    Assert.assertTrue(StringUtils.isNotBlank(after));
  }
}
