package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Test;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.User.GET_PHONE_NUMBER_URL;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link WxMaUserServiceImpl} 获取手机号接口的单元测试。
 */
public class WxMaUserServiceImplPhoneNumberTest {

  @Test
  public void shouldSendOpenidWhenGettingPhoneNumber() throws WxErrorException {
    WxMaService wxMaService = mock(WxMaService.class);
    when(wxMaService.post(anyString(), anyString())).thenReturn("{\"phone_info\":{}}");

    new WxMaUserServiceImpl(wxMaService).getPhoneNumber("phone-code", "user-openid");

    verify(wxMaService).post(GET_PHONE_NUMBER_URL, "{\"code\":\"phone-code\",\"openid\":\"user-openid\"}");
  }
}
