package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaExpressService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaExpressAccount;
import cn.binarywang.wx.miniapp.bean.WxMaExpressDelivery;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;


@Guice(modules = ApiTestModule.class)
public class WxMaExpressServiceImplTest {

  @Inject
  private WxMaService wxMaService;

  @Test
  public void testGetAllDelivery() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    List<WxMaExpressDelivery> list = service.getAllDelivery();
    System.out.println(WxMaGsonBuilder.create().toJson(list));
  }

  @Test
  public void testGetAllAccount() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    List<WxMaExpressAccount> list = service.getAllAccount();
    System.out.println(WxMaGsonBuilder.create().toJson(list));
  }
}
