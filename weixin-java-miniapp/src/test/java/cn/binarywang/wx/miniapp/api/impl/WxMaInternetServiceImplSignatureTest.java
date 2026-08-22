package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 服务端网络相关接口签名测试.
 */
public class WxMaInternetServiceImplSignatureTest {
  @Test
  public void testGetUserEncryptKeySignatureUsesRawSessionKey() throws Exception {
    WxMaService wxMaService = mock(WxMaService.class);
    when(wxMaService.post(anyString(), eq(""))).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    String openid = "ogu-84hVFTbTt-myGisQESoDJ6BM";
    String sessionKey = "9ny8n3t0KULoi0deF7T9pw==";
    WxMaInternetServiceImpl service = new WxMaInternetServiceImpl(wxMaService);

    service.getUserEncryptKey(openid, sessionKey);

    ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
    verify(wxMaService).post(urlCaptor.capture(), eq(""));
    assertThat(urlCaptor.getValue())
      .contains("openid=" + openid)
      .contains("signature=A6782009C273FDF75CAD421C9D3EAF6CA8715C1B15CB1E130EF40FE7901A9772");
  }
}
