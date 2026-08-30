package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.ADD_SUB_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.BATCH_PRINT_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.CANCEL_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.CREATE_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.CREATE_TEMPLATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.DELETE_TEMPLATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.GET_ACCOUNT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.GET_DELIVERY_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.GET_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.GET_PRINT_CONTENT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.GET_TEMPLATE_BY_ID_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.GET_TEMPLATE_CONFIG_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.GET_TEMPLATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.PRE_CREATE_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.PRINT_ORDER_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Ewaybill.UPDATE_TEMPLATE_URL;

import java.util.List;
import com.binarywang.wxjava.store.api.WxStoreEwaybillService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.ewaybill.AccountInfoResponse;
import com.binarywang.wxjava.store.bean.ewaybill.AddSubOrderRequest;
import com.binarywang.wxjava.store.bean.ewaybill.CreateOrderRequest;
import com.binarywang.wxjava.store.bean.ewaybill.CreateOrderResponse;
import com.binarywang.wxjava.store.bean.ewaybill.DeliveryListResponse;
import com.binarywang.wxjava.store.bean.ewaybill.EwaybillOrderIdParam;
import com.binarywang.wxjava.store.bean.ewaybill.PrintOrderRequest;
import com.binarywang.wxjava.store.bean.ewaybill.BatchPrintOrderRequest;
import com.binarywang.wxjava.store.bean.ewaybill.OrderDetailResponse;
import com.binarywang.wxjava.store.bean.ewaybill.PreCreateRequest;
import com.binarywang.wxjava.store.bean.ewaybill.PreCreateResponse;
import com.binarywang.wxjava.store.bean.ewaybill.PrintContentResponse;
import com.binarywang.wxjava.store.bean.ewaybill.PrintContentParam;
import com.binarywang.wxjava.store.bean.ewaybill.TemplateCodeParam;
import com.binarywang.wxjava.store.bean.ewaybill.TemplateConfigResponse;
import com.binarywang.wxjava.store.bean.ewaybill.TemplateCreateRequest;
import com.binarywang.wxjava.store.bean.ewaybill.TemplateIdParam;
import com.binarywang.wxjava.store.bean.ewaybill.TemplateIdResponse;
import com.binarywang.wxjava.store.bean.ewaybill.TemplateInfoResponse;
import com.binarywang.wxjava.store.bean.ewaybill.TemplateUpdateRequest;
import com.binarywang.wxjava.store.bean.ewaybill.WaybillIdParam;
import com.binarywang.wxjava.store.bean.ewaybill.WaybillIdsParam;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;

/**
 * 微信小店电子面单服务实现。
 *
 * @author GitHub Copilot
 */
public class WxStoreEwaybillServiceImpl implements WxStoreEwaybillService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreEwaybillServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public TemplateConfigResponse getTemplateConfig() throws WxErrorException {
    String resJson = post(GET_TEMPLATE_CONFIG_URL, "{}");
    return ResponseUtils.decode(resJson, TemplateConfigResponse.class);
  }

  @Override
  public TemplateIdResponse createTemplate(TemplateCreateRequest req) throws WxErrorException {
    String resJson = post(CREATE_TEMPLATE_URL, req);
    return ResponseUtils.decode(resJson, TemplateIdResponse.class);
  }

  @Override
  public WxStoreBaseResponse deleteTemplate(String templateId) throws WxErrorException {
    String resJson = post(DELETE_TEMPLATE_URL, new TemplateIdParam(templateId));
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateTemplate(TemplateUpdateRequest req) throws WxErrorException {
    String resJson = post(UPDATE_TEMPLATE_URL, req);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public TemplateInfoResponse getTemplate(String templateCode) throws WxErrorException {
    String resJson = post(GET_TEMPLATE_URL, new TemplateCodeParam(templateCode));
    return ResponseUtils.decode(resJson, TemplateInfoResponse.class);
  }

  @Override
  public TemplateInfoResponse getTemplateById(String templateId) throws WxErrorException {
    String resJson = post(GET_TEMPLATE_BY_ID_URL, new TemplateIdParam(templateId));
    return ResponseUtils.decode(resJson, TemplateInfoResponse.class);
  }

  @Override
  public AccountInfoResponse getAccount() throws WxErrorException {
    String resJson = post(GET_ACCOUNT_URL, "{}");
    return ResponseUtils.decode(resJson, AccountInfoResponse.class);
  }

  @Override
  public DeliveryListResponse getDeliveryList() throws WxErrorException {
    String resJson = post(GET_DELIVERY_LIST_URL, "{}");
    return ResponseUtils.decode(resJson, DeliveryListResponse.class);
  }

  @Override
  public PreCreateResponse preCreateOrder(PreCreateRequest req) throws WxErrorException {
    String resJson = post(PRE_CREATE_ORDER_URL, req);
    return ResponseUtils.decode(resJson, PreCreateResponse.class);
  }

  @Override
  public CreateOrderResponse createOrder(CreateOrderRequest req) throws WxErrorException {
    String resJson = post(CREATE_ORDER_URL, req);
    return ResponseUtils.decode(resJson, CreateOrderResponse.class);
  }

  @Override
  public WxStoreBaseResponse addSubOrder(AddSubOrderRequest req) throws WxErrorException {
    String resJson = post(ADD_SUB_ORDER_URL, req);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse cancelOrder(PrintOrderRequest req) throws WxErrorException {
    String resJson = post(CANCEL_ORDER_URL, req);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public OrderDetailResponse getOrder(String ewaybillOrderId) throws WxErrorException {
    String resJson = post(GET_ORDER_URL, new EwaybillOrderIdParam(ewaybillOrderId));
    return ResponseUtils.decode(resJson, OrderDetailResponse.class);
  }

  @Override
  public PrintContentResponse getPrintContent(String ewaybillOrderId, String templateId)
      throws WxErrorException {
    String resJson = post(GET_PRINT_CONTENT_URL, new PrintContentParam(ewaybillOrderId, templateId));
    return ResponseUtils.decode(resJson, PrintContentResponse.class);
  }

  @Override
  public WxStoreBaseResponse printOrder(PrintOrderRequest req) throws WxErrorException {
    String resJson = post(PRINT_ORDER_URL, req);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse batchPrintOrder(BatchPrintOrderRequest req) throws WxErrorException {
    String resJson = post(BATCH_PRINT_ORDER_URL, req);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  private String post(String url, Object request) throws WxErrorException {
    return shopService.executeWithoutLog(
      SimplePostRequestExecutor.create(shopService), url, JsonUtils.encode(request));
  }
}
