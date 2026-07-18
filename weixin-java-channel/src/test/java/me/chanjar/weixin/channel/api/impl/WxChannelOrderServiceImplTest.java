package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelOrderService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.AddressInfo;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.delivery.DeliveryCompanyResponse;
import me.chanjar.weixin.channel.bean.delivery.DeliveryInfo;
import me.chanjar.weixin.channel.bean.delivery.PackageAuditInfo;
import me.chanjar.weixin.channel.bean.order.ChangeOrderInfo;
import me.chanjar.weixin.channel.bean.order.DecodeSensitiveInfoResponse;
import me.chanjar.weixin.channel.bean.order.DeliveryUpdateParam;
import me.chanjar.weixin.channel.bean.order.OrderAddressInfo;
import me.chanjar.weixin.channel.bean.order.OrderCompensationDeliveryParam;
import me.chanjar.weixin.channel.bean.order.OrderInfoResponse;
import me.chanjar.weixin.channel.bean.order.OrderListParam;
import me.chanjar.weixin.channel.bean.order.OrderListResponse;
import me.chanjar.weixin.channel.bean.order.OrderSearchCondition;
import me.chanjar.weixin.channel.bean.order.OrderSearchParam;
import me.chanjar.weixin.channel.bean.order.PreShipmentChangeSkuResponse;
import me.chanjar.weixin.channel.bean.order.PresentSubOrderResponse;
import me.chanjar.weixin.channel.bean.order.PrivateNumberGetPhoneResponse;
import me.chanjar.weixin.channel.bean.order.RealNumberViewAuditResponse;
import me.chanjar.weixin.channel.bean.order.VirtualTelNumberResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelOrderServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetOrder() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    OrderInfoResponse response = orderService.getOrder(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetOrder2() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    boolean encodeSensitiveInfo = true;
    OrderInfoResponse response = orderService.getOrder(orderId, encodeSensitiveInfo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetOrders() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    OrderListParam param = new OrderListParam();
    OrderListResponse response = orderService.getOrders(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSearchOrder() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    OrderSearchParam param = new OrderSearchParam();
    param.setPageSize(100);
    OrderSearchCondition searchCondition = new OrderSearchCondition();
    searchCondition.setTitle("");
    param.setSearchCondition(searchCondition);
    OrderListResponse response = orderService.searchOrder(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdatePrice() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    Integer expressFee = 0;
    List<ChangeOrderInfo> changeOrderInfos = new ArrayList<>(4);
    ChangeOrderInfo changeOrderInfo = new ChangeOrderInfo();
    changeOrderInfos.add(changeOrderInfo);
    WxChannelBaseResponse response = orderService.updatePrice(orderId, expressFee, changeOrderInfos);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateRemark() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    String merchantNotes = "";
    WxChannelBaseResponse response = orderService.updateRemark(orderId, merchantNotes);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateAddress() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    AddressInfo addressInfo = new OrderAddressInfo();
    WxChannelBaseResponse response = orderService.updateAddress(orderId, addressInfo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateDelivery() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    DeliveryUpdateParam param = new DeliveryUpdateParam();
    WxChannelBaseResponse response = orderService.updateDelivery(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAcceptAddressModify() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    WxChannelBaseResponse response = orderService.acceptAddressModify(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testRejectAddressModify() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    WxChannelBaseResponse response = orderService.rejectAddressModify(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCloseOrder() {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    WxChannelBaseResponse response = orderService.closeOrder(orderId);
    assertNotNull(response);
    //assertTrue(response.isSuccess());
  }

  @Test
  public void testListDeliveryCompany() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    DeliveryCompanyResponse response = orderService.listDeliveryCompany(false);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeliveryOrder() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    List<DeliveryInfo> deliveryList = new ArrayList<>(4);
    DeliveryInfo deliveryInfo = new DeliveryInfo();
    deliveryList.add(deliveryInfo);
    WxChannelBaseResponse response = orderService.deliveryOrder(orderId, deliveryList);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUploadFreshInspect() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "123";
    List<PackageAuditInfo> items = new ArrayList<>();
    items.add(new PackageAuditInfo("product_express_pic_url", "https://store.mp.video.tencent-cloud.com/x"));
    items.add(new PackageAuditInfo("product_packaging_box_panoramic_video_url", "https://store.mp.video.tencent-cloud.com/y"));
    items.add(new PackageAuditInfo("product_unboxing_panoramic_video_url", "https://store.mp.video.tencent-cloud.com/z"));
    items.add(new PackageAuditInfo("single_product_detail_panoramic_video_url", "https://store.mp.video.tencent-cloud.com/a"));
    WxChannelBaseResponse response = orderService.uploadFreshInspect(orderId, items);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetVirtualTelNumber() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "123";
    VirtualTelNumberResponse response = orderService.getVirtualTelNumber(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDecodeSensitiveInfo() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "123";
    DecodeSensitiveInfoResponse response = orderService.decodeSensitiveInfo(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddPresentNote() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    String note = "测试备注";
    WxChannelBaseResponse response = orderService.addPresentNote(orderId, note);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetPresentSubOrders() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    PresentSubOrderResponse response = orderService.getPresentSubOrders(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetPreShipmentChangeSku() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    PreShipmentChangeSkuResponse response = orderService.getPreShipmentChangeSku(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testApprovePreShipmentChangeSku() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    WxChannelBaseResponse response = orderService.approvePreShipmentChangeSku(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testRejectPreShipmentChangeSku() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    String rejectReason = "库存不足";
    WxChannelBaseResponse response = orderService.rejectPreShipmentChangeSku(orderId, rejectReason);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testApplyRealNumber() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    WxChannelBaseResponse response = orderService.applyRealNumber(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetRealNumberViewAudit() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    RealNumberViewAuditResponse response = orderService.getRealNumberViewAudit(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testApplyVirtualNumberAgain() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    WxChannelBaseResponse response = orderService.applyVirtualNumberAgain(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDelayVirtualNumber() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String orderId = "";
    WxChannelBaseResponse response = orderService.delayVirtualNumber(orderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddPrivatePhone() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String phone = "13800138000";
    WxChannelBaseResponse response = orderService.addPrivatePhone(phone);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSendPrivatePhoneVerifyCode() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    String phone = "13800138000";
    WxChannelBaseResponse response = orderService.sendPrivatePhoneVerifyCode(phone);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetPrivatePhone() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    PrivateNumberGetPhoneResponse response = orderService.getPrivatePhone();
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCompensationDelivery() throws WxErrorException {
    WxChannelOrderService orderService = channelService.getOrderService();
    OrderCompensationDeliveryParam param = new OrderCompensationDeliveryParam();
    param.setOrderId("");
    WxChannelBaseResponse response = orderService.compensationDelivery(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
