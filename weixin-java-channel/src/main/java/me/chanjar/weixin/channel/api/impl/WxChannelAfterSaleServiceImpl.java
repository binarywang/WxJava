package me.chanjar.weixin.channel.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelAfterSaleService;
import me.chanjar.weixin.channel.bean.after.*;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.complaint.ComplaintOrderResponse;
import me.chanjar.weixin.channel.bean.complaint.ComplaintParam;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.AfterSale.*;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Complaint.*;

/**
 * 视频号小店 售后服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelAfterSaleServiceImpl implements WxChannelAfterSaleService {

  /**
   * 微信商店服务
   */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelAfterSaleServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public AfterSaleListResponse listIds(Long beginCreateTime, Long endCreateTime, String nextKey)
    throws WxErrorException {
    AfterSaleListParam param = new AfterSaleListParam(beginCreateTime, endCreateTime, null, null, nextKey);
    String resJson = shopService.post(AFTER_SALE_LIST_URL, param);
    return ResponseUtils.decode(resJson, AfterSaleListResponse.class);
  }

  @Override
  public AfterSaleListResponse listIds(AfterSaleListParam param) throws WxErrorException {
    String resJson = shopService.post(AFTER_SALE_LIST_URL, param);
    return ResponseUtils.decode(resJson, AfterSaleListResponse.class);
  }

  @Override
  public AfterSaleInfoResponse get(String afterSaleOrderId) throws WxErrorException {
    AfterSaleIdParam param = new AfterSaleIdParam(afterSaleOrderId);
    String resJson = shopService.post(AFTER_SALE_GET_URL, param);
    return ResponseUtils.decode(resJson, AfterSaleInfoResponse.class);
  }

  @Override
  public WxChannelBaseResponse accept(String afterSaleOrderId, String addressId, Integer acceptType) throws WxErrorException {
    AfterSaleAcceptParam param = new AfterSaleAcceptParam(afterSaleOrderId, addressId, acceptType);
    String resJson = shopService.post(AFTER_SALE_ACCEPT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse reject(String afterSaleOrderId, String rejectReason, Integer rejectReasonType) throws WxErrorException {
    AfterSaleRejectParam param = new AfterSaleRejectParam(afterSaleOrderId, rejectReason, rejectReasonType);
    String resJson = shopService.post(AFTER_SALE_REJECT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse uploadRefundEvidence(String afterSaleOrderId, String desc, List<String> certificates)
    throws WxErrorException {
    RefundEvidenceParam param = new RefundEvidenceParam(afterSaleOrderId, desc, certificates);
    String resJson = shopService.post(AFTER_SALE_UPLOAD_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse addComplaintMaterial(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException {
    ComplaintParam param = new ComplaintParam(complaintId, content, mediaIds);
    String resJson = shopService.post(ADD_COMPLAINT_MATERIAL_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);

  }

  @Override
  public WxChannelBaseResponse addComplaintEvidence(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException {
    ComplaintParam param = new ComplaintParam(complaintId, content, mediaIds);
    String resJson = shopService.post(ADD_COMPLAINT_PROOF_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public ComplaintOrderResponse getComplaint(String complaintId) throws WxErrorException {
    String reqJson = "{\"complaint_id\":\"" + complaintId + "\"}";
    String resJson = shopService.post(GET_COMPLAINT_ORDER_URL, reqJson);
    return ResponseUtils.decode(resJson, ComplaintOrderResponse.class);
  }

  @Override
  public AfterSaleReasonResponse getAllReason() throws WxErrorException {
    String resJson = shopService.post(AFTER_SALE_REASON_GET_URL, "{}");
    return ResponseUtils.decode(resJson, AfterSaleReasonResponse.class);
  }

  @Override
  public AfterSaleRejectReasonResponse getRejectReason() throws WxErrorException {
    String resJson = shopService.post(AFTER_SALE_REJECT_REASON_GET_URL, "{}");
    return ResponseUtils.decode(resJson, AfterSaleRejectReasonResponse.class);
  }

  @Override
  public WxChannelBaseResponse acceptExchangeReship(String afterSaleOrderId, String waybillId, String deliveryId) throws WxErrorException {
    AfterSaleAcceptExchangeReshipParam param = new AfterSaleAcceptExchangeReshipParam(afterSaleOrderId, waybillId, deliveryId);
    String resJson = shopService.post(AFTER_SALE_ACCEPT_EXCHANGE_RESHIP_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse rejectExchangeReship(String afterSaleOrderId, String rejectReason, Integer rejectReasonType, List<String> rejectCertificates) throws WxErrorException {
    AfterSaleRejectExchangeReshipParam param = new AfterSaleRejectExchangeReshipParam(afterSaleOrderId, rejectReason, rejectReasonType, rejectCertificates);
    String resJson = shopService.post(AFTER_SALE_REJECT_EXCHANGE_RESHIP_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse merchantUpdateAfterSale(AfterSaleMerchantUpdateParam param) throws WxErrorException {
    String resJson = shopService.post(AFTER_SALE_MERCHANT_UPDATE_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

}
