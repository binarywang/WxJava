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
    if (request.getUserName() != null && request.getUserName().length() > 0) {
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
}
