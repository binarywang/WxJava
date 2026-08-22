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

  /** 获取可用的标准面单模板。 @return 模板配置 @throws WxErrorException 微信接口调用失败 */
  TemplateConfigResponse getTemplateConfig() throws WxErrorException;

  /** 创建商家面单模板。 @param req 官方模板创建字段 @return 新模板 ID @throws WxErrorException 调用失败 */
  TemplateIdResponse createTemplate(TemplateCreateRequest req) throws WxErrorException;

  /** 删除商家面单模板。 @param templateId 模板 ID @return 操作结果 @throws WxErrorException 调用失败 */
  WxChannelBaseResponse deleteTemplate(String templateId) throws WxErrorException;

  /** 更新商家面单模板。 @param req 官方模板更新字段 @return 操作结果 @throws WxErrorException 调用失败 */
  WxChannelBaseResponse updateTemplate(TemplateUpdateRequest req) throws WxErrorException;

  /** 查询标准模板信息。 @param templateCode 标准模板编码 @return 模板详情 @throws WxErrorException 调用失败 */
  TemplateInfoResponse getTemplate(String templateCode) throws WxErrorException;

  /** 按模板 ID 查询商家模板。 @param templateId 模板 ID @return 模板详情 @throws WxErrorException 调用失败 */
  TemplateInfoResponse getTemplateById(String templateId) throws WxErrorException;

  /** 查询已开通电子面单的网点和账号。 @return 账号信息 @throws WxErrorException 调用失败 */
  AccountInfoResponse getAccount() throws WxErrorException;

  /** 查询已开通电子面单的快递公司。 @return 快递公司列表 @throws WxErrorException 调用失败 */
  DeliveryListResponse getDeliveryList() throws WxErrorException;

  /** 预取电子面单号。 @param req 官方预取号字段 @return 预取号结果 @throws WxErrorException 调用失败 */
  PreCreateResponse preCreateOrder(PreCreateRequest req) throws WxErrorException;

  /** 获取电子面单号。 @param req 官方取号字段，含收寄件信息 @return 面单号结果 @throws WxErrorException 调用失败 */
  CreateOrderResponse createOrder(CreateOrderRequest req) throws WxErrorException;

  /** 追加电子面单子件。 @param req 官方子件字段 @return 操作结果 @throws WxErrorException 调用失败 */
  WxChannelBaseResponse addSubOrder(AddSubOrderRequest req) throws WxErrorException;

  /** 取消电子面单下单。 @param waybillId 运单 ID @return 操作结果 @throws WxErrorException 调用失败 */
  WxChannelBaseResponse cancelOrder(String waybillId) throws WxErrorException;

  /** 查询电子面单详情。 @param waybillId 运单 ID @return 面单详情 @throws WxErrorException 调用失败 */
  OrderDetailResponse getOrder(String waybillId) throws WxErrorException;

  /** 获取打印报文。 @param waybillIds 运单 ID 列表 @param templateId 可选模板 ID @return 打印内容 @throws WxErrorException 调用失败 */
  PrintContentResponse getPrintContent(List<String> waybillIds, String templateId)
      throws WxErrorException;

  /** 通知单个运单打印成功。 @param waybillId 运单 ID @return 操作结果 @throws WxErrorException 调用失败 */
  WxChannelBaseResponse printOrder(String waybillId) throws WxErrorException;

  /** 批量通知运单打印成功。 @param waybillIds 运单 ID 列表 @return 操作结果 @throws WxErrorException 调用失败 */
  WxChannelBaseResponse batchPrintOrder(List<String> waybillIds) throws WxErrorException;
}
