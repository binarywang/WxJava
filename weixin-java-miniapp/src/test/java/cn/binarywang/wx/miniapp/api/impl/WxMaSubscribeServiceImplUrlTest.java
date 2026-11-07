package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaSubscribeService;
import cn.binarywang.wx.miniapp.bean.WxMaGetUserNotifyRequest;
import cn.binarywang.wx.miniapp.bean.WxMaServiceNotifyExtRequest;
import cn.binarywang.wx.miniapp.bean.WxMaServiceNotifyRequest;
import me.chanjar.weixin.common.error.WxErrorException;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WxMaSubscribeServiceImplUrlTest {

  @Test
  public void setUserNotifyUsesOfficialEndpoint() throws WxErrorException {
    WxMaService service = successService();

    subscribeService(service).setUserNotify(WxMaServiceNotifyRequest.builder().build());

    verify(service).post(eq("https://api.weixin.qq.com/wxa/set_user_notify"), anyString());
  }

  @Test
  public void setUserNotifyExtUsesOfficialEndpoint() throws WxErrorException {
    WxMaService service = successService();

    subscribeService(service).setUserNotifyExt(WxMaServiceNotifyExtRequest.builder().build());

    verify(service).post(eq("https://api.weixin.qq.com/wxa/set_user_notifyext"), anyString());
  }

  @Test
  public void getUserNotifyUsesOfficialEndpoint() throws WxErrorException {
    WxMaService service = successService();

    subscribeService(service).getUserNotify(WxMaGetUserNotifyRequest.builder().build());

    verify(service).post(eq("https://api.weixin.qq.com/wxa/get_user_notify"), anyString());
  }

  private WxMaSubscribeService subscribeService(WxMaService service) {
    return new WxMaSubscribeServiceImpl(service);
  }

  private WxMaService successService() throws WxErrorException {
    WxMaService service = Mockito.mock(WxMaService.class);
    when(service.post(anyString(), anyString())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    return service;
  }
}
