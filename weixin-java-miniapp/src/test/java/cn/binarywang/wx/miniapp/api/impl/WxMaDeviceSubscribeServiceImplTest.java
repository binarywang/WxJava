package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.device.*;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

/**
 * 小程序设备订阅消息相关 测试类
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 * @since 2021-12-16 17:13:35
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaDeviceSubscribeServiceImplTest {

  @Inject
  protected WxMaService wxService;

  @Test
  public void testGetSnTicket() throws WxErrorException {
    WxMaDeviceTicketRequest wxMaDeviceTicketRequest = new WxMaDeviceTicketRequest();
    wxMaDeviceTicketRequest.setModelId("11111");
    wxMaDeviceTicketRequest.setSn("11111");
    String snTicket = this.wxService.getDeviceSubscribeService().getSnTicket(wxMaDeviceTicketRequest);
    System.out.println(snTicket);
  }

  @Test
  public void sendDeviceSubscribeMsg() throws WxErrorException {
    WxMaDeviceSubscribeMessageRequest wxMaDeviceSubscribeMessageRequest = new WxMaDeviceSubscribeMessageRequest();
    wxMaDeviceSubscribeMessageRequest.setToOpenidList(Lists.newArrayList("1", "2"));
    wxMaDeviceSubscribeMessageRequest.setPage("pages/index/index");
    wxMaDeviceSubscribeMessageRequest.setTemplateId("11111111");
    wxMaDeviceSubscribeMessageRequest.setSn("111111");
    JsonObject data = GsonParser.parse("{\n" +
      "\t\t\"thing2\": {\n" +
      "\t\t\t\"value\": \"阳台\"\n" +
      "\t\t},\n" +
      "\t\t\"time1\": {\n" +
      "\t\t\t\"value\": \"2021-09-30 13:32:44\"\n" +
      "\t\t},\n" +
      "\t\t\"thing3\": {\n" +
      "\t\t\t\"value\": \"洗衣已完成\"\n" +
      "\t\t}\n" +
      "\t}");
    wxMaDeviceSubscribeMessageRequest.setData(data);
    this.wxService.getDeviceSubscribeService().sendDeviceSubscribeMsg(wxMaDeviceSubscribeMessageRequest);
  }

  @Test
  public void testCreateIotGroupId() throws WxErrorException {
    WxMaCreateIotGroupIdRequest request = new WxMaCreateIotGroupIdRequest();
    request.setModelId("11111");
    request.setGroupName("测试设备组");
    String groupId = this.wxService.getDeviceSubscribeService().createIotGroupId(request);
    System.out.println(groupId);
  }

  @Test
  public void testGetIotGroupInfo() throws WxErrorException {
    WxMaGetIotGroupInfoRequest request = new WxMaGetIotGroupInfoRequest();
    request.setGroupId("12313123");
    WxMaIotGroupDeviceInfoResponse response = this.wxService.getDeviceSubscribeService().getIotGroupInfo(request);
    log.info("testGetIotGroupInfo = {}", response);
  }

  @Test
  public void testAddIotGroupDevice() throws WxErrorException {
    WxMaDeviceTicketRequest deviceTicketRequest = new WxMaDeviceTicketRequest();
    deviceTicketRequest.setSn("2222222");
    deviceTicketRequest.setModelId("sdfeweee");
    WxMaIotGroupDeviceRequest request = new WxMaIotGroupDeviceRequest();
    request.setGroupId("12313123");
    request.setDeviceList(Collections.singletonList(deviceTicketRequest));
    request.setForceAdd(true);
    List<WxMaDeviceTicketRequest> response = this.wxService.getDeviceSubscribeService().addIotGroupDevice(request);
    log.info("testAddIotGroupDevice = {}", response);
  }

  @Test
  public void testRemoveIotGroupDevice() throws WxErrorException {
    WxMaDeviceTicketRequest deviceTicketRequest = new WxMaDeviceTicketRequest();
    deviceTicketRequest.setSn("2222222");
    deviceTicketRequest.setModelId("sdfeweee");
    WxMaIotGroupDeviceRequest request = new WxMaIotGroupDeviceRequest();
    request.setGroupId("12313123");
    request.setDeviceList(Collections.singletonList(deviceTicketRequest));
    List<WxMaDeviceTicketRequest> response = this.wxService.getDeviceSubscribeService().removeIotGroupDevice(request);
    log.info("testRemoveIotGroupDevice = {}", response);
  }
}
