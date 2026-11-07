package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.SignUtils;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * {@link WxMaUserServiceImpl} 检查登录态接口的单元测试。
 */
public class WxMaUserServiceImplCheckSessionKeyTest {

  @Test
  public void shouldUseCheckSessionUrlAndSignEmptyStringWhenCheckingSessionKey() throws WxErrorException {
    WxMaService wxMaService = mock(WxMaService.class);
    when(wxMaService.get(anyString(), isNull())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    new WxMaUserServiceImpl(wxMaService).checkSessionKey("user-openid", "session-key");

    ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
    verify(wxMaService).get(url.capture(), isNull());
    assertEquals(url.getValue(),
      "https://api.weixin.qq.com/wxa/checksession?openid=user-openid&signature="
        + SignUtils.createHmacSha256Sign("", "session-key").toLowerCase()
        + "&sig_method=hmac_sha256");
  }
}
