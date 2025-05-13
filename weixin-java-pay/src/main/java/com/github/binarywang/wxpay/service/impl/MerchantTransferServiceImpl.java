package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.merchanttransfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MerchantTransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author glz
 * created on  2022/6/11
 */
@Slf4j
@RequiredArgsConstructor
public class MerchantTransferServiceImpl implements MerchantTransferService {
  private static final Gson GSON = (new GsonBuilder()).create();

  private final WxPayService wxPayService;


  @Override
  public TransferCreateResult createTransfer(TransferCreateRequest request) throws WxPayException {
    if (StringUtils.isEmpty(request.getAppid())) {
      request.setAppid(this.wxPayService.getConfig().getAppId());
    }

    String url = String.format("%s/v3/transfer/batches", this.wxPayService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.wxPayService.getConfig().getVerifier().getValidCertificate());

    String response = wxPayService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(response, TransferCreateResult.class);
  }

  @Override
  public BatchesQueryResult queryWxBatches(WxBatchesQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/batch-id/%s?need_query_detail=%b",
      this.wxPayService.getPayBaseUrl(), request.getBatchId(), request.getNeedQueryDetail());

    if (request.getOffset() != null) {
      url = String.format("%s&offset=%d", url, request.getOffset());
    }
    if (request.getLimit() != null) {
      url = String.format("%s&limit=%d", url, request.getLimit());
    }
    if (request.getDetailStatus() != null && !request.getDetailStatus().isEmpty()) {
      url = String.format("%s&detail_status=%s", url, request.getDetailStatus());
    }

    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BatchesQueryResult.class);
  }

  @Override
  public DetailsQueryResult queryWxDetails(WxDetailsQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/batch-id/%s/details/detail-id/%s",
      this.wxPayService.getPayBaseUrl(), request.getBatchId(), request.getDetailId());
    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, DetailsQueryResult.class);
  }

  @Override
  public BatchesQueryResult queryMerchantBatches(MerchantBatchesQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/out-batch-no/%s?need_query_detail=%b",
      this.wxPayService.getPayBaseUrl(), request.getOutBatchNo(), request.getNeedQueryDetail());

    if (request.getOffset() != null) {
      url = String.format("%s&offset=%d", url, request.getOffset());
    }
    if (request.getLimit() != null) {
      url = String.format("%s&limit=%d", url, request.getLimit());
    }
    if (request.getDetailStatus() != null && !request.getDetailStatus().isEmpty()) {
      url = String.format("%s&detail_status=%s", url, request.getDetailStatus());
    }

    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BatchesQueryResult.class);
  }

  @Override
  public DetailsQueryResult queryMerchantDetails(MerchantDetailsQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/out-batch-no/%s/details/out-detail-no/%s",
      this.wxPayService.getPayBaseUrl(), request.getOutBatchNo(), request.getOutDetailNo());
    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, DetailsQueryResult.class);
  }

  @Override
  public ElectronicBillResult applyElectronicBill(ElectronicBillApplyRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/bill-receipt", this.wxPayService.getPayBaseUrl());
    String response = wxPayService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ElectronicBillResult.class);
  }

  @Override
  public ElectronicBillResult queryElectronicBill(String outBatchNo) throws WxPayException {
    String url = String.format("%s/v3/transfer/bill-receipt/%s", this.wxPayService.getPayBaseUrl(), outBatchNo);
    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, ElectronicBillResult.class);
  }

  @Override
  public DetailElectronicBillResult applyDetailElectronicBill(DetailElectronicBillRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer-detail/electronic-receipts", this.wxPayService.getPayBaseUrl());
    String response = wxPayService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, DetailElectronicBillResult.class);
  }

  @Override
  public DetailElectronicBillResult queryDetailElectronicBill(DetailElectronicBillRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer-detail/electronic-receipts?accept_type=%s&out_detail_no=%s",
      this.wxPayService.getPayBaseUrl(), request.getAcceptType(), request.getOutDetailNo());

    if (StringUtils.isNotEmpty(request.getOutBatchNo())) {
      url = String.format("%s&out_batch_no=%s", url, request.getOutBatchNo());
    }

    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, DetailElectronicBillResult.class);
  }

}
