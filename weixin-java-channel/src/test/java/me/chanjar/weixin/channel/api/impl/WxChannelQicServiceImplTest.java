package me.chanjar.weixin.channel.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelQicService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.qic.InspectCodeResponse;
import me.chanjar.weixin.channel.bean.qic.InspectConfigResponse;
import me.chanjar.weixin.channel.bean.qic.RegisterLogisticsRequest;
import me.chanjar.weixin.channel.bean.qic.SubmitConfigResponse;
import me.chanjar.weixin.channel.bean.qic.SubmitInspectRequest;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@Guice(modules = ApiTestModule.class)
public class WxChannelQicServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetInspectConfig() throws WxErrorException {
    WxChannelQicService qicService = channelService.getQicService();
    InspectConfigResponse response = qicService.getInspectConfig();
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetSubmitConfig() throws WxErrorException {
    WxChannelQicService qicService = channelService.getQicService();
    SubmitConfigResponse response = qicService.getSubmitConfig("123456");
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testPrintInspectCode() throws WxErrorException {
    WxChannelQicService qicService = channelService.getQicService();
    InspectCodeResponse response = qicService.printInspectCode("123456");
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSubmitInspectInfo() throws WxErrorException {
    WxChannelQicService qicService = channelService.getQicService();
    SubmitInspectRequest request = new SubmitInspectRequest();
    request.setOrderId("123456");
    SubmitInspectRequest.InspectInfo inspectInfo = new SubmitInspectRequest.InspectInfo();
    inspectInfo.setDeliveryId("YTO");
    inspectInfo.setBackupDeliveryId("SF");
    inspectInfo.setExpressInsure(Boolean.FALSE);
    inspectInfo.setExpressMerge(Boolean.FALSE);
    inspectInfo.setInspectOrgId("ORG_1");
    inspectInfo.setRefundIntercept(0);
    inspectInfo.setInspectOrgName("机构A");
    inspectInfo.setWarehouseName("质检仓");
    inspectInfo.setWarehouseAddr("质检仓地址");
    inspectInfo.setDeliveryProductId(1L);
    inspectInfo.setBackupDeliveryProductId(2L);
    request.setInspectInfo(inspectInfo);

    WxChannelBaseResponse response = qicService.submitInspectInfo(request);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testRegisterLogistics() throws WxErrorException {
    WxChannelQicService qicService = channelService.getQicService();
    RegisterLogisticsRequest request = new RegisterLogisticsRequest();
    request.setOrderIdList(Collections.singletonList("123456"));

    RegisterLogisticsRequest.LogisticsInfo logisticsInfo = new RegisterLogisticsRequest.LogisticsInfo();
    logisticsInfo.setWaybillId("YT1234567890");
    logisticsInfo.setDeliveryId("YTO");
    logisticsInfo.setDeliveryName("圆通速递");
    logisticsInfo.setDeliveryType(1);
    request.setLogisticsInfo(logisticsInfo);

    WxChannelBaseResponse response = qicService.registerLogistics(request);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
