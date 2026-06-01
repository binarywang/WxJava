package me.chanjar.weixin.channel.api;

import java.util.List;
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
import me.chanjar.weixin.channel.bean.ewaybill.TemplateConfigResponse;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateCreateRequest;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateIdResponse;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateInfoResponse;
import me.chanjar.weixin.channel.bean.ewaybill.TemplateUpdateRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店电子面单服务接口
 *
 * @author GitHub Copilot
 */
public interface WxChannelEwaybillService {

  TemplateConfigResponse getTemplateConfig() throws WxErrorException;

  TemplateIdResponse createTemplate(TemplateCreateRequest req) throws WxErrorException;

  WxChannelBaseResponse deleteTemplate(String templateId) throws WxErrorException;

  WxChannelBaseResponse updateTemplate(TemplateUpdateRequest req) throws WxErrorException;

  TemplateInfoResponse getTemplate(String templateId) throws WxErrorException;

  TemplateInfoResponse getTemplateById(String templateId) throws WxErrorException;

  AccountInfoResponse getAccount() throws WxErrorException;

  DeliveryListResponse getDeliveryList() throws WxErrorException;

  PreCreateResponse preCreateOrder(PreCreateRequest req) throws WxErrorException;

  CreateOrderResponse createOrder(CreateOrderRequest req) throws WxErrorException;

  WxChannelBaseResponse addSubOrder(AddSubOrderRequest req) throws WxErrorException;

  WxChannelBaseResponse cancelOrder(String waybillId) throws WxErrorException;

  OrderDetailResponse getOrder(String waybillId) throws WxErrorException;

  PrintContentResponse getPrintContent(String waybillId) throws WxErrorException;

  WxChannelBaseResponse printOrder(String waybillId) throws WxErrorException;

  WxChannelBaseResponse batchPrintOrder(List<String> waybillIds) throws WxErrorException;
}

