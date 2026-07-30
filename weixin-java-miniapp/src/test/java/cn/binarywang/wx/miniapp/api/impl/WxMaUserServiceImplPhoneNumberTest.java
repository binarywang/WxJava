package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.User.GET_PHONE_NUMBER_URL;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * {@link WxMaUserServiceImpl} 获取手机号接口的单元测试。
 */
public class WxMaUserServiceImplPhoneNumberTest {

  @Test
  public void shouldSendOpenidWhenGettingPhoneNumber() throws WxErrorException {
    WxMaService wxMaService = mock(WxMaService.class);
    when(wxMaService.post(anyString(), anyString())).thenReturn("{\"phone_info\":{}}");

    new WxMaUserServiceImpl(wxMaService).getPhoneNumber("phone-code", "user-openid");

    ArgumentCaptor<String> requestBody = ArgumentCaptor.forClass(String.class);
    verify(wxMaService).post(eq(GET_PHONE_NUMBER_URL), requestBody.capture());
    JsonObject request = GsonParser.parse(requestBody.getValue());
    assertEquals(request.get("code").getAsString(), "phone-code");
    assertEquals(request.get("openid").getAsString(), "user-openid");
  }

  @Test
  public void shouldIgnoreBlankOpenidWhenGettingPhoneNumber() throws WxErrorException {
    WxMaService wxMaService = mock(WxMaService.class);
    when(wxMaService.post(anyString(), anyString())).thenReturn("{\"phone_info\":{}}");

    new WxMaUserServiceImpl(wxMaService).getPhoneNumber("phone-code", " ");

    ArgumentCaptor<String> requestBody = ArgumentCaptor.forClass(String.class);
    verify(wxMaService).post(eq(GET_PHONE_NUMBER_URL), requestBody.capture());
    JsonObject request = GsonParser.parse(requestBody.getValue());
    assertFalse(request.has("openid"));
  }
}
