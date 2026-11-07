package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Delivery.DELIVERY_SEND_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Delivery.GET_DELIVERY_COMPANY_NEW_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Delivery.GET_DELIVERY_COMPANY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.ACCEPT_ADDRESS_MODIFY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.DECODE_SENSITIVE_INFO_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.DELIVERY_COMPENSATION_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.ORDER_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.ORDER_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.ORDER_SEARCH_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.PRE_SHIPMENT_CHANGE_SKU_APPROVE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.PRE_SHIPMENT_CHANGE_SKU_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.PRE_SHIPMENT_CHANGE_SKU_REJECT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.PRESENT_NOTE_ADD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.PRESENT_SUB_ORDER_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.REAL_NUMBER_APPLY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.REAL_NUMBER_VIEW_AUDIT_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.REJECT_ADDRESS_MODIFY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.UPDATE_ADDRESS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.UPDATE_EXPRESS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.UPDATE_PRICE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.UPDATE_REMARK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.UPLOAD_FRESH_INSPECT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.VIRTUAL_NUMBER_APPLY_AGAIN_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.VIRTUAL_NUMBER_DELAY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Order.VIRTUAL_TEL_NUMBER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.PrivateNumber.ADD_PHONE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.PrivateNumber.GET_PHONE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.PrivateNumber.SEND_VERIFY_CODE_URL;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreOrderService;
import com.binarywang.wxjava.store.bean.base.AddressInfo;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.delivery.DeliveryCompanyResponse;
import com.binarywang.wxjava.store.bean.delivery.DeliveryInfo;
import com.binarywang.wxjava.store.bean.delivery.DeliverySendParam;
import com.binarywang.wxjava.store.bean.delivery.FreshInspectParam;
import com.binarywang.wxjava.store.bean.delivery.PackageAuditInfo;
import com.binarywang.wxjava.store.bean.order.ChangeOrderInfo;
import com.binarywang.wxjava.store.bean.order.DecodeSensitiveInfoResponse;
import com.binarywang.wxjava.store.bean.order.DeliveryUpdateParam;
import com.binarywang.wxjava.store.bean.order.OrderAddressParam;
import com.binarywang.wxjava.store.bean.order.OrderCompensationDeliveryParam;
import com.binarywang.wxjava.store.bean.order.OrderIdParam;
import com.binarywang.wxjava.store.bean.order.OrderInfoParam;
import com.binarywang.wxjava.store.bean.order.OrderInfoResponse;
import com.binarywang.wxjava.store.bean.order.OrderListParam;
import com.binarywang.wxjava.store.bean.order.OrderListResponse;
import com.binarywang.wxjava.store.bean.order.OrderPriceParam;
import com.binarywang.wxjava.store.bean.order.OrderRemarkParam;
import com.binarywang.wxjava.store.bean.order.OrderSearchParam;
import com.binarywang.wxjava.store.bean.order.PreShipmentChangeSkuRejectParam;
import com.binarywang.wxjava.store.bean.order.PreShipmentChangeSkuResponse;
import com.binarywang.wxjava.store.bean.order.PresentNoteAddParam;
import com.binarywang.wxjava.store.bean.order.PresentSubOrderResponse;
import com.binarywang.wxjava.store.bean.order.PrivateNumberAddPhoneParam;
import com.binarywang.wxjava.store.bean.order.PrivateNumberGetPhoneResponse;
import com.binarywang.wxjava.store.bean.order.PrivateNumberSendVerifyCodeParam;
import com.binarywang.wxjava.store.bean.order.RealNumberViewAuditResponse;
import com.binarywang.wxjava.store.bean.order.VirtualTelNumberResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * 微信小店订单服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreOrderServiceImpl implements WxStoreOrderService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreOrderServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public OrderInfoResponse getOrder(String orderId) throws WxErrorException {
    OrderInfoParam param = new OrderInfoParam(orderId, null);
    String resJson = shopService.post(ORDER_GET_URL, param);
    return ResponseUtils.decode(resJson, OrderInfoResponse.class);
  }

  @Override
  public OrderInfoResponse getOrder(String orderId, Boolean encodeSensitiveInfo) throws WxErrorException {
    OrderInfoParam param = new OrderInfoParam(orderId, encodeSensitiveInfo);
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
  public WxStoreBaseResponse updatePrice(String orderId, Integer expressFee, List<ChangeOrderInfo> changeOrderInfos)
    throws WxErrorException {
    OrderPriceParam param = new OrderPriceParam(orderId, expressFee, changeOrderInfos);
    String resJson = shopService.post(UPDATE_PRICE_URL, param);
    ;
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateRemark(String orderId, String merchantNotes) throws WxErrorException {
    OrderRemarkParam param = new OrderRemarkParam(orderId, merchantNotes);
    String resJson = shopService.post(UPDATE_REMARK_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateAddress(String orderId, AddressInfo userAddress) throws WxErrorException {
    OrderAddressParam param = new OrderAddressParam(orderId, userAddress);
    String resJson = shopService.post(UPDATE_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateDelivery(DeliveryUpdateParam param) throws WxErrorException {
    String resJson = shopService.post(UPDATE_EXPRESS_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse acceptAddressModify(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(ACCEPT_ADDRESS_MODIFY_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse rejectAddressModify(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(REJECT_ADDRESS_MODIFY_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse closeOrder(String orderId) {
    // 暂不支持
    return ResponseUtils.internalError(WxStoreBaseResponse.class);
  }

  @Override
  public DeliveryCompanyResponse listDeliveryCompany() throws WxErrorException {
    String resJson = shopService.post(GET_DELIVERY_COMPANY_URL, "{}");
    return ResponseUtils.decode(resJson, DeliveryCompanyResponse.class);
  }

  @Override
  public DeliveryCompanyResponse listDeliveryCompany(Boolean ewaybillOnly) throws WxErrorException {
    String reqJson = "{}";
    if (ewaybillOnly != null) {
      reqJson = "{\"ewaybill_only\":" + ewaybillOnly + "}";
    }
    String resJson = shopService.post(GET_DELIVERY_COMPANY_NEW_URL, reqJson);
    return ResponseUtils.decode(resJson, DeliveryCompanyResponse.class);
  }

  @Override
  public WxStoreBaseResponse deliveryOrder(String orderId, List<DeliveryInfo> deliveryList)
    throws WxErrorException {
    DeliverySendParam param = new DeliverySendParam(orderId, deliveryList);
    String resJson = shopService.post(DELIVERY_SEND_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse uploadFreshInspect(String orderId, List<PackageAuditInfo> items)
    throws WxErrorException {
    FreshInspectParam param = new FreshInspectParam(orderId, items);
    String resJson = shopService.post(UPLOAD_FRESH_INSPECT_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
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

  @Override
  public WxStoreBaseResponse addPresentNote(String orderId, String note) throws WxErrorException {
    PresentNoteAddParam param = new PresentNoteAddParam(orderId, note);
    String resJson = shopService.post(PRESENT_NOTE_ADD_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public PresentSubOrderResponse getPresentSubOrders(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(PRESENT_SUB_ORDER_GET_URL, param);
    return ResponseUtils.decode(resJson, PresentSubOrderResponse.class);
  }

  @Override
  public PreShipmentChangeSkuResponse getPreShipmentChangeSku(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(PRE_SHIPMENT_CHANGE_SKU_GET_URL, param);
    return ResponseUtils.decode(resJson, PreShipmentChangeSkuResponse.class);
  }

  @Override
  public WxStoreBaseResponse approvePreShipmentChangeSku(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(PRE_SHIPMENT_CHANGE_SKU_APPROVE_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse rejectPreShipmentChangeSku(String orderId, String rejectReason)
    throws WxErrorException {
    PreShipmentChangeSkuRejectParam param = new PreShipmentChangeSkuRejectParam(orderId, rejectReason);
    String resJson = shopService.post(PRE_SHIPMENT_CHANGE_SKU_REJECT_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse applyRealNumber(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(REAL_NUMBER_APPLY_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public RealNumberViewAuditResponse getRealNumberViewAudit(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(REAL_NUMBER_VIEW_AUDIT_GET_URL, param);
    return ResponseUtils.decode(resJson, RealNumberViewAuditResponse.class);
  }

  @Override
  public WxStoreBaseResponse applyVirtualNumberAgain(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(VIRTUAL_NUMBER_APPLY_AGAIN_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse delayVirtualNumber(String orderId) throws WxErrorException {
    OrderIdParam param = new OrderIdParam(orderId);
    String resJson = shopService.post(VIRTUAL_NUMBER_DELAY_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse addPrivatePhone(String phone) throws WxErrorException {
    PrivateNumberAddPhoneParam param = new PrivateNumberAddPhoneParam(phone);
    String resJson = shopService.post(ADD_PHONE_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse sendPrivatePhoneVerifyCode(String phone) throws WxErrorException {
    PrivateNumberSendVerifyCodeParam param = new PrivateNumberSendVerifyCodeParam(phone);
    String resJson = shopService.post(SEND_VERIFY_CODE_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public PrivateNumberGetPhoneResponse getPrivatePhone() throws WxErrorException {
    String resJson = shopService.post(GET_PHONE_URL, "{}");
    return ResponseUtils.decode(resJson, PrivateNumberGetPhoneResponse.class);
  }

  @Override
  public WxStoreBaseResponse compensationDelivery(OrderCompensationDeliveryParam param)
    throws WxErrorException {
    String resJson = shopService.post(DELIVERY_COMPENSATION_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }
}
