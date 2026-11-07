package com.binarywang.wxjava.store.api.impl;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreAfterSaleService;
import com.binarywang.wxjava.store.bean.after.*;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.complaint.ComplaintOrderResponse;
import com.binarywang.wxjava.store.bean.complaint.ComplaintParam;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.AfterSale.*;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Complaint.*;

/**
 * 微信小店 售后服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreAfterSaleServiceImpl implements WxStoreAfterSaleService {

  /**
   * 微信商店服务
   */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreAfterSaleServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
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
  public WxStoreBaseResponse accept(String afterSaleOrderId, String addressId, Integer acceptType) throws WxErrorException {
    AfterSaleAcceptParam param = new AfterSaleAcceptParam(afterSaleOrderId, addressId, acceptType);
    String resJson = shopService.post(AFTER_SALE_ACCEPT_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse reject(String afterSaleOrderId, String rejectReason, Integer rejectReasonType) throws WxErrorException {
    return reject(afterSaleOrderId, rejectReason, rejectReasonType, null);
  }

  @Override
  public WxStoreBaseResponse reject(String afterSaleOrderId, String rejectReason, Integer rejectReasonType,
    List<String> rejectCertificates) throws WxErrorException {
    AfterSaleRejectParam param = new AfterSaleRejectParam(afterSaleOrderId, rejectReason, rejectReasonType, rejectCertificates);
    String resJson = shopService.post(AFTER_SALE_REJECT_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse uploadRefundEvidence(String afterSaleOrderId, String desc, List<String> certificates)
    throws WxErrorException {
    RefundEvidenceParam param = new RefundEvidenceParam(afterSaleOrderId, desc, certificates);
    String resJson = shopService.post(AFTER_SALE_UPLOAD_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse addComplaintMaterial(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException {
    ComplaintParam param = new ComplaintParam(complaintId, content, mediaIds);
    String resJson = shopService.post(ADD_COMPLAINT_MATERIAL_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);

  }

  @Override
  public WxStoreBaseResponse addComplaintEvidence(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException {
    ComplaintParam param = new ComplaintParam(complaintId, content, mediaIds);
    String resJson = shopService.post(ADD_COMPLAINT_PROOF_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
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
  public WxStoreBaseResponse acceptExchangeReship(String afterSaleOrderId, String waybillId, String deliveryId) throws WxErrorException {
    AfterSaleAcceptExchangeReshipParam param = new AfterSaleAcceptExchangeReshipParam(afterSaleOrderId, waybillId, deliveryId);
    String resJson = shopService.post(AFTER_SALE_ACCEPT_EXCHANGE_RESHIP_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse rejectExchangeReship(String afterSaleOrderId, String rejectReason, Integer rejectReasonType, List<String> rejectCertificates) throws WxErrorException {
    AfterSaleRejectExchangeReshipParam param = new AfterSaleRejectExchangeReshipParam(afterSaleOrderId, rejectReason, rejectReasonType, rejectCertificates);
    String resJson = shopService.post(AFTER_SALE_REJECT_EXCHANGE_RESHIP_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse merchantUpdateAfterSale(AfterSaleMerchantUpdateParam param) throws WxErrorException {
    String resJson = shopService.post(AFTER_SALE_MERCHANT_UPDATE_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public GuaranteeOrderListResponse listGuaranteeOrder(GuaranteeOrderListParam param) throws WxErrorException {
    String resJson = shopService.post(GUARANTEE_ORDER_LIST_URL, param);
    return ResponseUtils.decode(resJson, GuaranteeOrderListResponse.class);
  }

  @Override
  public GuaranteeOrderInfoResponse getGuaranteeOrder(String guaranteeOrderId) throws WxErrorException {
    GuaranteeOrderIdParam param = new GuaranteeOrderIdParam(guaranteeOrderId);
    String resJson = shopService.post(GUARANTEE_ORDER_GET_URL, param);
    return ResponseUtils.decode(resJson, GuaranteeOrderInfoResponse.class);
  }

  @Override
  public WxStoreBaseResponse acceptGuarantee(String guaranteeOrderId) throws WxErrorException {
    GuaranteeOrderIdParam param = new GuaranteeOrderIdParam(guaranteeOrderId);
    String resJson = shopService.post(GUARANTEE_ORDER_ACCEPT_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse modifyGuarantee(GuaranteeModifyRequest request) throws WxErrorException {
    String resJson = shopService.post(GUARANTEE_ORDER_MODIFY_URL, request);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse proofGuarantee(GuaranteeProofRequest request) throws WxErrorException {
    String resJson = shopService.post(GUARANTEE_ORDER_PROOF_URL, request);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse refuseGuarantee(GuaranteeRefuseRequest request) throws WxErrorException {
    String resJson = shopService.post(GUARANTEE_ORDER_REFUSE_URL, request);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

}
