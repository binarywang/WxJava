package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaExpressService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.express.*;
import cn.binarywang.wx.miniapp.bean.express.request.*;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressOrderInfoResult;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
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

  @Test
  public void testBindAccount() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressBindAccountRequest request = WxMaExpressBindAccountRequest.builder()
      .deliveryId("YUNDA")
      .bizId("******")
      .password("*********")
      .remarkContent("####")
      .type(WxMaConstants.BindAccountType.BIND)
      .build();
    service.bindAccount(request);
  }

  @Test
  public void testGetQuota() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressBindAccountRequest request = WxMaExpressBindAccountRequest.builder()
      .deliveryId("YUNDA")
      .bizId("******")
      .build();
    Integer quotaNum = service.getQuota(request);
    System.out.println(quotaNum);
  }



  @Test
  public void testUpdatePrinter() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressPrinterUpdateRequest request = WxMaExpressPrinterUpdateRequest.builder()
      .openid("*************")
      .updateType(WxMaConstants.BindAccountType.UNBIND)
      .build();
    service.updatePrinter(request);
  }

  @Test
  public void testGetPrinter() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressPrinter printer = service.getPrinter();
    System.out.println(WxMaGsonBuilder.create().toJson(printer));
  }

  @Test
  public void testAddOrder() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();

    WxMaExpressOrderPerson sender = new WxMaExpressOrderPerson();
    sender.setName("张三");
    sender.setMobile("177****9809");
    sender.setProvince("北京市");
    sender.setCity("朝阳区");
    sender.setArea("朝阳区");
    sender.setAddress("西坝河****C-102");

    WxMaExpressOrderPerson receiver = new WxMaExpressOrderPerson();
    receiver.setName("李四");
    receiver.setMobile("180****8772");
    receiver.setProvince("北京市");
    receiver.setCity("朝阳区");
    receiver.setArea("朝阳区");
    receiver.setAddress("西坝河北里****101");


    WxMaExpressOrderCargo cargo = new WxMaExpressOrderCargo();
    List<WxMaExpressOrderCargoDetail> detailList = new ArrayList<>();
    List<String> shopNames = new ArrayList<>();
    Integer goodsCount = 0;
    for (int i = 0; i < 4; i++) {
      WxMaExpressOrderCargoDetail detail = new WxMaExpressOrderCargoDetail();
      String shopName = "商品_"+i;
      detail.setName(shopName);
      detail.setCount(1);
      detailList.add(detail);
      shopNames.add(shopName);
      goodsCount ++;
    }
    cargo.setCount(detailList.size());
    cargo.setWeight(1.2);
    cargo.setSpaceHeight(10.0);
    cargo.setSpaceLength(20.0);
    cargo.setSpaceWidth(15.0);
    cargo.setDetailList(detailList);


    WxMaExpressOrderShop shop = new WxMaExpressOrderShop();
    shop.setWxaPath("/index/index?from=waybill&id=01234567890123456789");
    shop.setGoodsName(StringUtils.join(shopNames,"&"));
    shop.setGoodsCount(goodsCount);
    shop.setImgUrl("https://mmbiz.qpic.cn/mmbiz_png/OiaFLUqewuIDNQnTiaCInIG8ibdosYHhQHPbXJUrqYSNIcBL60vo4LIjlcoNG1QPkeH5GWWEB41Ny895CokeAah8A/640");

    WxMaExpressDelivery.ServiceType serviceType = new WxMaExpressDelivery.ServiceType();
    serviceType.setServiceName("test_service_name");
    serviceType.setServiceType(1);

    WxMaExpressAddOrderRequest request = WxMaExpressAddOrderRequest.builder()
      .addSource(WxMaConstants.OrderAddSource.MINI_PROGRAM)
      .orderId("test201911271429004")
      .openid("oAg_-0PDUPuLbQw9V9kXE9OkU-Vo")
      .deliveryId("TEST")
      .bizId("test_biz_id")
      .customRemark("")
      .expectTime(0L)
      .sender(sender)
      .receiver(receiver)
      .cargo(cargo)
      .shop(shop)
      .insured(WxMaExpressOrderInsured.builder().build())
      .service(serviceType)
      .build();

    WxMaExpressOrderInfoResult result = service.addOrder(request);
    System.out.println(WxMaGsonBuilder.create().toJson(result));
  }

  @Test
  public void testBatchGetOrder() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    List<WxMaExpressGetOrderRequest> requests = new ArrayList<>();

    List<String> orderIds = new ArrayList<>();
    orderIds.add("test201911271429000");

    List<String> waybillIds = new ArrayList<>();
    waybillIds.add("test201911271429000_1574836404_waybill_id");
    for (int i = 0; i < orderIds.size(); i++) {
      WxMaExpressGetOrderRequest request = WxMaExpressGetOrderRequest.builder()
        .orderId(orderIds.get(i))
        .deliveryId("TEST")
        .waybillId(waybillIds.get(i))
        .build();
      requests.add(request);
    }

    List<WxMaExpressOrderInfoResult> results = service.batchGetOrder(requests);
    System.out.println(WxMaGsonBuilder.create().toJson(results));
  }

  @Test
  public void testCancelOrder() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressGetOrderRequest request = WxMaExpressGetOrderRequest.builder()
      .orderId("test201911271429000")
      .deliveryId("TEST")
      .waybillId("test201911271429000_1574836404_waybill_id")
      .openid("oAg_-0PDUPuLbQw9V9kXE9OkU-Vo")
      .build();
    service.cancelOrder(request);
  }


  @Test
  public void testGetOrder() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressGetOrderRequest request = WxMaExpressGetOrderRequest.builder()
      .orderId("test201911271429000")
      .deliveryId("TEST")
      .waybillId("test201911271429000_1574836404_waybill_id")
      .openid("oAg_-0PDUPuLbQw9V9kXE9OkU-Vo")
      .build();
    WxMaExpressOrderInfoResult result = service.getOrder(request);
    System.out.println(WxMaGsonBuilder.create().toJson(result));
  }


  @Test
  public void testGetPath() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressGetOrderRequest request = WxMaExpressGetOrderRequest.builder()
      .orderId("test201911271429000")
      .deliveryId("TEST")
      .waybillId("test201911271429000_1574836404_waybill_id")
      .openid("oAg_-0PDUPuLbQw9V9kXE9OkU-Vo")
      .build();
    WxMaExpressPath path = service.getPath(request);
    System.out.println(WxMaGsonBuilder.create().toJson(path));
  }

  @Test
  public void testTestUpdateOrder() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressTestUpdateOrderRequest request = WxMaExpressTestUpdateOrderRequest.builder()
      .orderId("test201911271429000")
      .waybillId("test201911271429000_1574836404_waybill_id")
      .actionTime(1574850455L)
      .actionType(300002)
      .actionMsg("开始派送")
      .build();
    service.testUpdateOrder(request);
  }
}
