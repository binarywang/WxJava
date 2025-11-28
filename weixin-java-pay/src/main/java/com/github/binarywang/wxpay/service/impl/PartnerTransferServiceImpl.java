package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.FundBalanceResult;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.marketing.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PartnerTransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.InputStream;

/**
 * 批量转账到零钱（服务商）
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Slf4j
@RequiredArgsConstructor
public class PartnerTransferServiceImpl implements PartnerTransferService {

  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  /**
   * 发起批量转账API
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches
   * 请求方式：POST
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request 请求对象
   * @return 返回数据 {@link PartnerTransferResult}
   * @throws WxPayException the wx pay exception
   */
  @Override
  public PartnerTransferResult batchTransfer(PartnerTransferRequest request) throws WxPayException {
    request.getTransferDetailList().forEach(p -> {
      try {
        String userName = RsaCryptoUtil.encryptOAEP(p.getUserName(),
          this.payService.getConfig().getVerifier().getValidCertificate());
        p.setUserName(userName);

        if (StringUtil.isNotBlank(p.getUserIdCard())) {
          String userIdCard = RsaCryptoUtil.encryptOAEP(p.getUserIdCard(),
            this.payService.getConfig().getVerifier().getValidCertificate());
          p.setUserIdCard(userIdCard);
        }
      } catch (IllegalBlockSizeException e) {
        throw new RuntimeException("姓名或身份证转换异常!", e);
      }
    });
    String url = String.format("%s/v3/partner-transfer/batches", this.payService.getPayBaseUrl());
    String response = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(response, PartnerTransferResult.class);
  }

  /**
   * 微信支付批次单号查询批次单API
   * 接口说明
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/batch-id/{batch_id}
   * https://api.mch.weixin.qq.com/v3/partner-transfer/batches/batch-id/1030000071100999991182020050700019480001
   * ?need_query_detail=true&offset=1
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request 请求对象
   * @return 返回数据 {@link BatchNumberResult}
   * @throws WxPayException the wx pay exception
   */
  @Override
  public BatchNumberResult queryBatchByBatchId(BatchNumberRequest request) throws WxPayException {
    String url = String.format("%s/v3/partner-transfer/batches/batch-id/%s", this.payService.getPayBaseUrl(),
      request.getBatchId());
    if (request.getOffset() == null) {
      request.setOffset(0);
    }
    if (request.getLimit() == null || request.getLimit() <= 0) {
      request.setLimit(20);
    }
    String detailStatus = StringUtil.isNotBlank(request.getDetailStatus()) ? request.getDetailStatus() : "ALL";

    String query = String.format("?need_query_detail=%s&detail_status=%s&offset=%s&limit=%s",
      request.getNeedQueryDetail(), detailStatus, request.getOffset(), request.getLimit());
    String response = this.payService.getV3(url + query);
    return GSON.fromJson(response, BatchNumberResult.class);
  }

  /**
   * 商家批次单号查询批次单API
   * 接口说明
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/out-batch-no/{out_batch_no}
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request 请求对象
   * @return 返回数据 {@link BatchNumberResult}
   * @throws WxPayException the wx pay exception
   */
  @Override
  public BatchNumberResult queryBatchByOutBatchNo(MerchantBatchRequest request) throws WxPayException {
    String url = String.format("%s/v3/partner-transfer/batches/out-batch-no/%s", this.payService.getPayBaseUrl(),
      request.getOutBatchNo());
    if (request.getOffset() == null) {
      request.setOffset(0);
    }
    if (request.getLimit() == null || request.getLimit() <= 0) {
      request.setLimit(20);
    }
    String query = String.format("?need_query_detail=%s&offset=%s&limit=%s", request.getNeedQueryDetail(),
      request.getOffset(), request.getLimit());
    if (StringUtil.isNotBlank(request.getDetailStatus())) {
      query += "&detail_status=" + request.getDetailStatus();
    }
    String response = this.payService.getV3(url + query);
    return GSON.fromJson(response, BatchNumberResult.class);
  }

  /**
   * 微信支付明细单号查询明细单API
   * 接口说明
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/batch-id/{batch_id}/details/detail-id/{detail_id}
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param batchId  微信批次单号
   * @param detailId 微信明细单号
   * @return 返回数据 {@link BatchDetailsResult}
   * @throws WxPayException      the wx pay exception
   * @throws BadPaddingException the wx decrypt exception
   */
  @Override
  public BatchDetailsResult queryBatchDetailByWeChat(String batchId, String detailId) throws WxPayException,
    BadPaddingException {
    String url = String.format("%s/v3/partner-transfer/batches/batch-id/%s/details/detail-id/%s",
      this.payService.getPayBaseUrl(), batchId, detailId);
    String response = this.payService.getV3(url);
    BatchDetailsResult batchDetailsResult = GSON.fromJson(response, BatchDetailsResult.class);
    String userName = RsaCryptoUtil.decryptOAEP(batchDetailsResult.getUserName(),
      this.payService.getConfig().getPrivateKey());
    batchDetailsResult.setUserName(userName);
    return batchDetailsResult;
  }

  /**
   * 商家明细单号查询明细单API
   * 接口说明
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/out-batch-no/{out_batch_no}/details/out-detail
   * -no/{out_detail_no}
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param outBatchNo  商家明细单号
   * @param outDetailNo 商家批次单号
   * @return 返回数据 {@link BatchDetailsResult}
   * @throws WxPayException      the wx pay exception
   * @throws BadPaddingException the wx decrypt exception
   */
  @Override
  public BatchDetailsResult queryBatchDetailByMch(String outBatchNo, String outDetailNo) throws WxPayException,
    BadPaddingException {
    String url = String.format("%s/v3/partner-transfer/batches/out-batch-no/%s/details/out-detail-no/%s",
      this.payService.getPayBaseUrl(), outBatchNo, outDetailNo);
    String response = this.payService.getV3(url);
    BatchDetailsResult batchDetailsResult = GSON.fromJson(response, BatchDetailsResult.class);
    String userName = RsaCryptoUtil.decryptOAEP(batchDetailsResult.getUserName(),
      this.payService.getConfig().getPrivateKey());
    batchDetailsResult.setUserName(userName);
    return batchDetailsResult;
  }


  /**
   * 转账电子回单申请受理API
   * 接口说明
   * 适用对象：直连商户 服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter4_1.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/bill-receipt
   * 请求方式：POST
   *
   * @param request 商家批次单号
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  @Override
  public BillReceiptResult receiptBill(ReceiptBillRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/bill-receipt", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, BillReceiptResult.class);
  }


  /**
   * 查询转账电子回单API
   * 接口说明
   * 适用对象：直连商户 服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter4_2.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/bill-receipt/{out_batch_no}
   * 请求方式：GET
   *
   * @param outBatchNo 商家批次单号
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  @Override
  public BillReceiptResult queryBillReceipt(String outBatchNo) throws WxPayException {
    String url = String.format("%s/v3/transfer/bill-receipt/%s", this.payService.getPayBaseUrl(), outBatchNo);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, BillReceiptResult.class);
  }

  /**
   * 转账明细电子回单受理API
   * 接口说明
   * 适用对象：直连商户 服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter4_4.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer-detail/electronic-receipts
   * 请求方式：POST
   * 前置条件：只支持受理最近90天内的转账明细单
   *
   * @param request 请求对象
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  @Override
  public ElectronicReceiptsResult transferElectronic(ElectronicReceiptsRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer-detail/electronic-receipts", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ElectronicReceiptsResult.class);
  }


  /**
   * 查询转账明细电子回单受理结果API
   * 接口说明
   * 适用对象：直连商户 服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter4_5.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer-detail/electronic-receipts
   * 请求方式：GET
   * 前置条件：只支持查询最近90天内的转账明细单
   *
   * @param request 请求对象
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  @Override
  public ElectronicReceiptsResult queryTransferElectronicResult(ElectronicReceiptsRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer-detail/electronic-receipts", this.payService.getPayBaseUrl());
    String query = String.format("?accept_type=%s&out_batch_no=%s&out_detail_no=%s", request.getAcceptType(),
      request.getOutBatchNo(), request.getOutDetailNo());
    String response = this.payService.getV3(url + query);
    return GSON.fromJson(response, ElectronicReceiptsResult.class);
  }

  /**
   * 下载电子回单API
   * 接口说明
   * 适用对象：直连商户 服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter4_3.shtml
   * 请求URL：通过申请账单接口获取到“download_url”，URL有效期10min
   * 请求方式：GET
   * 前置条件：调用申请账单接口并获取到“download_url”
   *
   * @param url 微信返回的电子回单地址。
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  @Override
  public InputStream transferDownload(String url) throws WxPayException {
    InputStream response = this.payService.downloadV3(url);
    return response;
  }

  /**
   * <pre>
   * 查询账户实时余额API
   * 接口说明
   * 适用对象：直连商户 服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter5_1.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/balance/{account_type}
   * 请求方式：GET
   * </pre>
   *
   * @param accountType 服务商账户类型 {@link SpAccountTypeEnum}
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  @Override
  public FundBalanceResult fundBalance(SpAccountTypeEnum accountType) throws WxPayException {
    String url = String.format("%s/v3/merchant/fund/balance/%s", this.payService.getPayBaseUrl(), accountType);
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  /**
   * <pre>
   * 服务商账户日终余额
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter5_2.shtml
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/amount.shtml
   * </pre>
   *
   * @param accountType 服务商账户类型
   * @param date        查询日期 2020-09-11
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  @Override
  public FundBalanceResult spDayEndBalance(SpAccountTypeEnum accountType, String date) {
    try {
      return this.payService.getEcommerceService().spDayEndBalance(accountType, date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


}
