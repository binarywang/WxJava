package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.brandmerchanttransfer.request.*;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandBatchesQueryResult;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandDetailsQueryResult;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandTransferBatchesResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BrandMerchantTransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 品牌商户发放红包商家转账到零钱（直联商户）实现
 *
 * @author moran
 */
@Slf4j
@RequiredArgsConstructor
public class BrandMerchantTransferServiceImpl implements BrandMerchantTransferService {
  private static final Gson GSON = (new GsonBuilder()).create();

  private final WxPayService wxPayService;


  @Override
  public BrandTransferBatchesResult createBrandTransfer(BrandTransferBatchesRequest request) throws WxPayException {

    String url = String.format("%s/v3/fund-app/brand-redpacket/brand-merchant-batches", this.wxPayService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.wxPayService.getConfig().getVerifier().getValidCertificate());

    String response = wxPayService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(response, BrandTransferBatchesResult.class);
  }

  @Override
  public BrandBatchesQueryResult queryBrandWxBatches(BrandWxBatchesQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/fund-app/brand-redpacket/brand-merchant-batches/%s",
      this.wxPayService.getPayBaseUrl(), request.getBatchNo());

    if (request.getNeedQueryDetail() != null) {
      url = String.format("%s?need_query_detail=%b", url, request.getNeedQueryDetail());
    }
    if (request.getDetailState() != null && !request.getDetailState().isEmpty()) {
      url = String.format("%s&detail_state=%s", url, request.getDetailState());
    }

    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BrandBatchesQueryResult.class);
  }

  @Override
  public BrandDetailsQueryResult queryBrandWxDetails(BrandWxDetailsQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/fund-app/brand-redpacket/brand-merchant-batches/%s/details/%s",
      this.wxPayService.getPayBaseUrl(), request.getBatchNo(), request.getDetailNo());
    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BrandDetailsQueryResult.class);
  }

  @Override
  public BrandBatchesQueryResult queryBrandMerchantBatches(BrandMerchantBatchesQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/fund-app/brand-redpacket/brand-merchant-out-batches/%s",
      this.wxPayService.getPayBaseUrl(), request.getOutBatchNo());

    if (request.getNeedQueryDetail() != null) {
      url = String.format("%s?need_query_detail=%b", url, request.getNeedQueryDetail());
    }
    if (request.getDetailState() != null && !request.getDetailState().isEmpty()) {
      url = String.format("%s&detail_state=%s", url, request.getDetailState());
    }

    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BrandBatchesQueryResult.class);
  }

  @Override
  public BrandDetailsQueryResult queryBrandMerchantDetails(BrandMerchantDetailsQueryRequest request) throws WxPayException {
    String url = String.format("%s/v3/fund-app/brand-redpacket/brand-merchant-out-batches/%s/out-details/%s",
      this.wxPayService.getPayBaseUrl(), request.getOutBatchNo(), request.getOutDetailNo());
    String response = wxPayService.getV3(url);
    return GSON.fromJson(response, BrandDetailsQueryResult.class);
  }

}
