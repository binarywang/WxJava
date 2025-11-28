package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.TransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.security.cert.X509Certificate;
import java.util.List;

/**
 * 商家转账到零钱
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public TransferBatchesResult transferBatches(TransferBatchesRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches", this.payService.getPayBaseUrl());
    List<TransferBatchesRequest.TransferDetail> transferDetailList = request.getTransferDetailList();
    X509Certificate validCertificate = this.payService.getConfig().getVerifier().getValidCertificate();
    for (TransferBatchesRequest.TransferDetail detail : transferDetailList) {
      RsaCryptoUtil.encryptFields(detail, validCertificate);
    }
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, TransferBatchesResult.class);
  }

  @Override
  public TransferNotifyResult parseTransferNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    return this.payService.baseParseOrderNotifyV3Result(notifyData, header, TransferNotifyResult.class, TransferNotifyResult.DecryptNotifyResult.class);
  }

  @Override
  public QueryTransferBatchesResult transferBatchesBatchId(QueryTransferBatchesRequest request) throws WxPayException {
    String url;
    if (request.getNeedQueryDetail()) {
      url = String.format("%s/v3/transfer/batches/batch-id/%s?need_query_detail=true&offset=%s&limit=%s&detail_status=%s",
        this.payService.getPayBaseUrl(), request.getBatchId(), request.getOffset(), request.getLimit(), request.getDetailStatus());
    } else {
      url = String.format("%s/v3/transfer/batches/batch-id/%s?need_query_detail=false",
        this.payService.getPayBaseUrl(), request.getBatchId());
    }
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, QueryTransferBatchesResult.class);
  }

  @Override
  public TransferBatchDetailResult transferBatchesBatchIdDetail(String batchId, String detailId) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/batch-id/%s/details/detail-id/%s", this.payService.getPayBaseUrl(), batchId, detailId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, TransferBatchDetailResult.class);
  }

  @Override
  public QueryTransferBatchesResult transferBatchesOutBatchNo(QueryTransferBatchesRequest request) throws WxPayException {
    String url;
    if (request.getNeedQueryDetail()) {
      url = String.format("%s/v3/transfer/batches/out-batch-no/%s?need_query_detail=true&offset=%s&limit=%s&detail_status=%s",
        this.payService.getPayBaseUrl(), request.getOutBatchNo(), request.getOffset(), request.getLimit(), request.getDetailStatus());
    } else {
      url = String.format("%s/v3/transfer/batches/out-batch-no/%s?need_query_detail=false",
        this.payService.getPayBaseUrl(), request.getOutBatchNo());
    }
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, QueryTransferBatchesResult.class);
  }

  @Override
  public TransferBatchDetailResult transferBatchesOutBatchNoDetail(String outBatchNo, String outDetailNo) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/out-batch-no/%s/details/out-detail-no/%s", this.payService.getPayBaseUrl(), outBatchNo, outDetailNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, TransferBatchDetailResult.class);
  }

  @Override
  public TransferBillsResult transferBills(TransferBillsRequest request) throws WxPayException {
    String url = String.format("%s/v3/fund-app/mch-transfer/transfer-bills", this.payService.getPayBaseUrl());
    if (request.getUserName() != null && !request.getUserName().isEmpty()) {
      X509Certificate validCertificate = this.payService.getConfig().getVerifier().getValidCertificate();
      RsaCryptoUtil.encryptFields(request, validCertificate);
    }
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, TransferBillsResult.class);
  }

  @Override
  public TransferBillsCancelResult transformBillsCancel(String outBillNo) throws WxPayException {
    String url = String.format("%s/v3/fund-app/mch-transfer/transfer-bills/out-bill-no/%s/cancel",
      this.payService.getPayBaseUrl(), outBillNo);
    String result = this.payService.postV3(url, "");

    return GSON.fromJson(result, TransferBillsCancelResult.class);
  }

  @Override
  public TransferBillsGetResult getBillsByOutBillNo(String outBillNo) throws WxPayException {
    String url = String.format("%s/v3/fund-app/mch-transfer/transfer-bills/out-bill-no/%s",
      this.payService.getPayBaseUrl(), outBillNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, TransferBillsGetResult.class);
  }

  @Override
  public TransferBillsGetResult getBillsByTransferBillNo(String transferBillNo) throws WxPayException {
    String url = String.format("%s/v3/fund-app/mch-transfer/transfer-bills/transfer-bill-no/%s",
      this.payService.getPayBaseUrl(), transferBillNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, TransferBillsGetResult.class);
  }

  @Override
  public TransferBillsNotifyResult parseTransferBillsNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    return this.payService.baseParseOrderNotifyV3Result(notifyData, header, TransferBillsNotifyResult.class, TransferBillsNotifyResult.DecryptNotifyResult.class);
  }

  // ===================== 用户授权免确认模式相关接口实现 =====================

  @Override
  public UserAuthorizationStatusResult getUserAuthorizationStatus(String openid, String transferSceneId) throws WxPayException {
    String url = String.format("%s/v3/fund-app/mch-transfer/authorization/openid/%s?transfer_scene_id=%s",
      this.payService.getPayBaseUrl(), openid, transferSceneId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, UserAuthorizationStatusResult.class);
  }

  @Override
  public ReservationTransferBatchResult reservationTransferBatch(ReservationTransferBatchRequest request) throws WxPayException {
    String url = String.format("%s/v3/fund-app/mch-transfer/reservation/transfer-batches", this.payService.getPayBaseUrl());
    List<ReservationTransferBatchRequest.TransferDetail> transferDetailList = request.getTransferDetailList();
    if (transferDetailList != null && !transferDetailList.isEmpty()) {
      X509Certificate validCertificate = this.payService.getConfig().getVerifier().getValidCertificate();
      for (ReservationTransferBatchRequest.TransferDetail detail : transferDetailList) {
        if (detail.getUserName() != null && !detail.getUserName().isEmpty()) {
          RsaCryptoUtil.encryptFields(detail, validCertificate);
        }
      }
    }
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ReservationTransferBatchResult.class);
  }

  @Override
  public ReservationTransferBatchGetResult getReservationTransferBatchByOutBatchNo(String outBatchNo, Boolean needQueryDetail,
                                                                                   Integer offset, Integer limit, String detailState) throws WxPayException {
    String url = buildReservationBatchQueryUrl("out-batch-no", outBatchNo, needQueryDetail, offset, limit, detailState);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ReservationTransferBatchGetResult.class);
  }

  @Override
  public ReservationTransferBatchGetResult getReservationTransferBatchByReservationBatchNo(String reservationBatchNo, Boolean needQueryDetail,
                                                                                           Integer offset, Integer limit, String detailState) throws WxPayException {
    String url = buildReservationBatchQueryUrl("reservation-batch-no", reservationBatchNo, needQueryDetail, offset, limit, detailState);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ReservationTransferBatchGetResult.class);
  }

  private String buildReservationBatchQueryUrl(String batchNoType, String batchNo, Boolean needQueryDetail,
                                               Integer offset, Integer limit, String detailState) {
    StringBuilder url = new StringBuilder();
    url.append(this.payService.getPayBaseUrl())
      .append("/v3/fund-app/mch-transfer/reservation/transfer-batches/")
      .append(batchNoType).append("/").append(batchNo);

    boolean hasParams = false;
    if (needQueryDetail != null) {
      url.append("?need_query_detail=").append(needQueryDetail);
      hasParams = true;
    }
    if (offset != null) {
      url.append(hasParams ? "&" : "?").append("offset=").append(offset);
      hasParams = true;
    }
    if (limit != null) {
      url.append(hasParams ? "&" : "?").append("limit=").append(limit);
      hasParams = true;
    }
    if (detailState != null && !detailState.isEmpty()) {
      url.append(hasParams ? "&" : "?").append("detail_state=").append(detailState);
    }
    return url.toString();
  }

  @Override
  public ReservationTransferNotifyResult parseReservationTransferNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    return this.payService.baseParseOrderNotifyV3Result(notifyData, header, ReservationTransferNotifyResult.class,
      ReservationTransferNotifyResult.DecryptNotifyResult.class);
  }

  @Override
  public void closeReservationTransferBatch(String outBatchNo) throws WxPayException {
    String url = String.format("%s/v3/fund-app/mch-transfer/reservation/transfer-batches/out-batch-no/%s/close",
      this.payService.getPayBaseUrl(), outBatchNo);
    this.payService.postV3(url, "");
  }
}
