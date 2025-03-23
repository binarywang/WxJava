package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Delivery.DELIVERY_SEND_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Delivery.GET_DELIVERY_COMPANY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Order.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelOrderService;
import me.chanjar.weixin.channel.bean.base.AddressInfo;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.delivery.PackageAuditInfo;
import me.chanjar.weixin.channel.bean.delivery.DeliveryCompanyResponse;
import me.chanjar.weixin.channel.bean.delivery.DeliveryInfo;
import me.chanjar.weixin.channel.bean.delivery.DeliverySendParam;
import me.chanjar.weixin.channel.bean.delivery.FreshInspectParam;
import me.chanjar.weixin.channel.bean.order.ChangeOrderInfo;
import me.chanjar.weixin.channel.bean.order.DecodeSensitiveInfoResponse;
import me.chanjar.weixin.channel.bean.order.DeliveryUpdateParam;
import me.chanjar.weixin.channel.bean.order.OrderAddressParam;
import me.chanjar.weixin.channel.bean.order.OrderIdParam;
import me.chanjar.weixin.channel.bean.order.OrderInfoResponse;
import me.chanjar.weixin.channel.bean.order.OrderListParam;
import me.chanjar.weixin.channel.bean.order.OrderListResponse;
import me.chanjar.weixin.channel.bean.order.OrderPriceParam;
import me.chanjar.weixin.channel.bean.order.OrderRemarkParam;
import me.chanjar.weixin.channel.bean.order.OrderSearchParam;
import me.chanjar.weixin.channel.bean.order.VirtualTelNumberResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * 视频号小店订单服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelOrderServiceImpl implements WxChannelOrderService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl shopService;

  public WxChannelOrderServiceImpl(BaseWxChannelServiceImpl shopService) {
    this.shopService = shopService;
  }

  @Override
  public OrderInfoResponse getOrder(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(ORDER_GET_URL, param);
    return ResponseUtils.decode(resJson, OrderInfoResponse.class);
  }

  @Override
  public OrderListResponse getOrders(OrderListParam param) throws WxErrorException {
    String resJson = shopService.post(ORDER_LIST_URL, param);
    return ResponseUtils.decode(resJson, OrderListResponse.class);
  }

  @Override
  public OrderListResponse searchOrder(OrderSearchParam param) throws WxErrorException {
    String resJson = shopService.post(ORDER_SEARCH_URL, param);
    return ResponseUtils.decode(resJson, OrderListResponse.class);
  }

  @Override
  public WxChannelBaseResponse updatePrice(String orderId, Integer expressFee, List<ChangeOrderInfo> changeOrderInfos)
    throws WxErrorException {
    OrderPriceParam param = new OrderPriceParam(orderId, expressFee, changeOrderInfos);
    String resJson = shopService.post(UPDATE_PRICE_URL, param);
    ;
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateRemark(String orderId, String merchantNotes) throws WxErrorException {
    OrderRemarkParam param = new OrderRemarkParam(orderId, merchantNotes);
    String resJson = shopService.post(UPDATE_REMARK_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateAddress(String orderId, AddressInfo userAddress) throws WxErrorException {
    OrderAddressParam param = new OrderAddressParam(orderId, userAddress);
    String resJson = shopService.post(UPDATE_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateDelivery(DeliveryUpdateParam param) throws WxErrorException {
    String resJson = shopService.post(UPDATE_EXPRESS_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse acceptAddressModify(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(ACCEPT_ADDRESS_MODIFY_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse rejectAddressModify(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(REJECT_ADDRESS_MODIFY_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse closeOrder(String orderId) {
    // 暂不支持
    return ResponseUtils.internalError(WxChannelBaseResponse.class);
  }

  @Override
  public DeliveryCompanyResponse listDeliveryCompany() throws WxErrorException {
    String resJson = shopService.post(GET_DELIVERY_COMPANY_URL, "{}");
    return ResponseUtils.decode(resJson, DeliveryCompanyResponse.class);
  }

  @Override
  public WxChannelBaseResponse deliveryOrder(String orderId, List<DeliveryInfo> deliveryList)
    throws WxErrorException {
    DeliverySendParam param = new DeliverySendParam(orderId, deliveryList);
    String resJson = shopService.post(DELIVERY_SEND_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse uploadFreshInspect(String orderId, List<PackageAuditInfo> items)
    throws WxErrorException {
    FreshInspectParam param = new FreshInspectParam(orderId, items);
    String resJson = shopService.post(UPLOAD_FRESH_INSPECT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public VirtualTelNumberResponse getVirtualTelNumber(String orderId) throws WxErrorException {
    String reqJson = "{\"order_id\":\"" + orderId + "\"}";
    String resJson = shopService.post(VIRTUAL_TEL_NUMBER_URL, reqJson);
    return ResponseUtils.decode(resJson, VirtualTelNumberResponse.class);
  }

  @Override
  public DecodeSensitiveInfoResponse decodeSensitiveInfo(String orderId) throws WxErrorException {
    String reqJson = "{\"order_id\":\"" + orderId + "\"}";
    String resJson = shopService.post(DECODE_SENSITIVE_INFO_URL, reqJson);
    return ResponseUtils.decode(resJson, DecodeSensitiveInfoResponse.class);
  }
}
