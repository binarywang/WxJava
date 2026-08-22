package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.ADD_SUB_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.BATCH_PRINT_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.CANCEL_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.CREATE_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.CREATE_TEMPLATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.DELETE_TEMPLATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.GET_ACCOUNT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.GET_DELIVERY_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.GET_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.GET_PRINT_CONTENT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.GET_TEMPLATE_BY_ID_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.GET_TEMPLATE_CONFIG_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.GET_TEMPLATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.PRE_CREATE_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.PRINT_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Ewaybill.UPDATE_TEMPLATE_URL;

import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelEwaybillService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.ewaybill.AccountInfoResponse;
import me.chanjar.weixin.channel.bean.ewaybill.AddSubOrderRequest;
import me.chanjar.weixin.channel.bean.ewaybill.CreateOrderRequest;
import me.chanjar.weixin.channel.bean.ewaybill.CreateOrderResponse;
import me.chanjar.weixin.channel.bean.ewaybill.DeliveryListResponse;
import me.chanjar.weixin.channel.bean.ewaybill.OrderDetailResponse;
import me.chanjar.weixin.channel.bean.ewaybill.PreCreateRequest;
import me.chanjar.weixin.channel.bean.ewaybill.PreCreateResponse;
import me.chanjar.weixin.channel.bean.ewaybill.PrintContentResponse;
import me.chanjar.weixin.channel.bean.ewaybill.PrintContentParam;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateCodeParam;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateConfigResponse;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateCreateRequest;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateIdParam;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateIdResponse;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateInfoResponse;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateUpdateRequest;
import me.chanjar.weixin.channel.bean.ewaybill.WaybillIdParam;
import me.chanjar.weixin.channel.bean.ewaybill.WaybillIdsParam;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店电子面单服务实现。
 *
 * @author GitHub Copilot
 */
public class WxChannelEwaybillServiceImpl implements WxChannelEwaybillService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelEwaybillServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public TemplateConfigResponse getTemplateConfig() throws WxErrorException {
    String resJson = shopService.post(GET_TEMPLATE_CONFIG_URL, "{}");
    return ResponseUtils.decode(resJson, TemplateConfigResponse.class);
  }

  @Override
  public TemplateIdResponse createTemplate(TemplateCreateRequest req) throws WxErrorException {
    String resJson = shopService.post(CREATE_TEMPLATE_URL, req);
    return ResponseUtils.decode(resJson, TemplateIdResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteTemplate(String templateId) throws WxErrorException {
    String resJson = shopService.post(DELETE_TEMPLATE_URL, new TemplateIdParam(templateId));
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateTemplate(TemplateUpdateRequest req) throws WxErrorException {
    String resJson = shopService.post(UPDATE_TEMPLATE_URL, req);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public TemplateInfoResponse getTemplate(String templateCode) throws WxErrorException {
    String resJson = shopService.post(GET_TEMPLATE_URL, new TemplateCodeParam(templateCode));
    return ResponseUtils.decode(resJson, TemplateInfoResponse.class);
  }

  @Override
  public TemplateInfoResponse getTemplateById(String templateId) throws WxErrorException {
    String resJson = shopService.post(GET_TEMPLATE_BY_ID_URL, new TemplateIdParam(templateId));
    return ResponseUtils.decode(resJson, TemplateInfoResponse.class);
  }

  @Override
  public AccountInfoResponse getAccount() throws WxErrorException {
    String resJson = shopService.post(GET_ACCOUNT_URL, "{}");
    return ResponseUtils.decode(resJson, AccountInfoResponse.class);
  }

  @Override
  public DeliveryListResponse getDeliveryList() throws WxErrorException {
    String resJson = shopService.post(GET_DELIVERY_LIST_URL, "{}");
    return ResponseUtils.decode(resJson, DeliveryListResponse.class);
  }

  @Override
  public PreCreateResponse preCreateOrder(PreCreateRequest req) throws WxErrorException {
    String resJson = shopService.post(PRE_CREATE_ORDER_URL, req);
    return ResponseUtils.decode(resJson, PreCreateResponse.class);
  }

  @Override
  public CreateOrderResponse createOrder(CreateOrderRequest req) throws WxErrorException {
    String resJson = shopService.post(CREATE_ORDER_URL, req);
    return ResponseUtils.decode(resJson, CreateOrderResponse.class);
  }

  @Override
  public WxChannelBaseResponse addSubOrder(AddSubOrderRequest req) throws WxErrorException {
    String resJson = shopService.post(ADD_SUB_ORDER_URL, req);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelOrder(String waybillId) throws WxErrorException {
    String resJson = shopService.post(CANCEL_ORDER_URL, new WaybillIdParam(waybillId));
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public OrderDetailResponse getOrder(String waybillId) throws WxErrorException {
    String resJson = shopService.post(GET_ORDER_URL, new WaybillIdParam(waybillId));
    return ResponseUtils.decode(resJson, OrderDetailResponse.class);
  }

  @Override
  public PrintContentResponse getPrintContent(List<String> waybillIds, String templateId)
      throws WxErrorException {
    String resJson = shopService.post(GET_PRINT_CONTENT_URL, new PrintContentParam(waybillIds, templateId));
    return ResponseUtils.decode(resJson, PrintContentResponse.class);
  }

  @Override
  public WxChannelBaseResponse printOrder(String waybillId) throws WxErrorException {
    String resJson = shopService.post(PRINT_ORDER_URL, new WaybillIdParam(waybillId));
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse batchPrintOrder(List<String> waybillIds) throws WxErrorException {
    String resJson = shopService.post(BATCH_PRINT_ORDER_URL, new WaybillIdsParam(waybillIds));
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
