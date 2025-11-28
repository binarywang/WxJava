package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaKefuService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfList;
import cn.binarywang.wx.miniapp.bean.kefu.request.WxMaKfAccountRequest;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 小程序客服管理服务测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaKefuServiceImplTest {

  @Test
  public void testKfList() throws WxErrorException {
    WxMaService service = mock(WxMaService.class);
    when(service.get(anyString(), any())).thenReturn("{\"kf_list\":[]}");

    WxMaKefuService kefuService = new WxMaKefuServiceImpl(service);
    WxMaKfList result = kefuService.kfList();

    Assert.assertNotNull(result);
    Assert.assertNotNull(result.getKfList());
    Assert.assertEquals(result.getKfList().size(), 0);
  }

  @Test
  public void testKfAccountAdd() throws WxErrorException {
    WxMaService service = mock(WxMaService.class);
    when(service.post(anyString(), anyString())).thenReturn("{\"errcode\":0}");

    WxMaKefuService kefuService = new WxMaKefuServiceImpl(service);
    WxMaKfAccountRequest request = WxMaKfAccountRequest.builder()
        .kfAccount("test@kfaccount")
        .kfNick("测试客服")
        .kfPwd("password")
        .build();

    boolean result = kefuService.kfAccountAdd(request);
    Assert.assertTrue(result);
  }

  @Test
  public void testKfSessionCreate() throws WxErrorException {
    WxMaService service = mock(WxMaService.class);
    when(service.post(anyString(), anyString())).thenReturn("{\"errcode\":0}");

    WxMaKefuService kefuService = new WxMaKefuServiceImpl(service);
    boolean result = kefuService.kfSessionCreate("test_openid", "test@kfaccount");
    Assert.assertTrue(result);
  }
}