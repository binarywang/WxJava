package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.FundBalanceResult;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.marketing.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;

import javax.crypto.BadPaddingException;
import java.io.InputStream;

/**
 * 微信批量转账到零钱【V3接口】服务商API
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
public interface PartnerTransferService {

  /**
   * 发起批量转账API
   * 适用对象：服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter3_1.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches
   * 请求方式：POST
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request 请求对象
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  PartnerTransferResult batchTransfer(PartnerTransferRequest request) throws WxPayException;

  /**
   * 微信支付批次单号查询批次单API
   * 接口说明
   * 适用对象：服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter3_2.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/batch-id/{batch_id}
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request 请求对象
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  BatchNumberResult queryBatchByBatchId(BatchNumberRequest request) throws WxPayException;

  /**
   * 微信支付明细单号查询明细单API
   * 接口说明
   * 适用对象：服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter3_3.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/batch-id/{batch_id}/details/detail-id/{detail_id}
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param batchId  微信批次单号
   * @param detailId 微信明细单号
   * @return 返回数据 fund balance result
   * @throws WxPayException      the wx pay exception
   * @throws BadPaddingException the wx decrypt exception
   */
  BatchDetailsResult queryBatchDetailByWeChat(String batchId, String detailId) throws WxPayException, BadPaddingException;

  /**
   * 商家批次单号查询批次单API
   * 接口说明
   * 适用对象：服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter3_4.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/out-batch-no/{out_batch_no}
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request 请求对象
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  BatchNumberResult queryBatchByOutBatchNo(MerchantBatchRequest request) throws WxPayException;

  /**
   * 商家明细单号查询明细单API
   * 接口说明
   * 适用对象：服务商
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter3_5.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/out-batch-no/{out_batch_no}/details/out-detail-no/{out_detail_no}
   * 请求方式：GET
   * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param outBatchNo  商家明细单号
   * @param outDetailNo 商家批次单号
   * @return 返回数据 fund balance result
   * @throws WxPayException      the wx pay exception
   * @throws BadPaddingException the wx decrypt exception
   */
  BatchDetailsResult queryBatchDetailByMch(String outBatchNo, String outDetailNo) throws WxPayException, BadPaddingException;


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
  BillReceiptResult receiptBill(ReceiptBillRequest request) throws WxPayException;


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
  BillReceiptResult queryBillReceipt(String outBatchNo) throws WxPayException;

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
  ElectronicReceiptsResult transferElectronic(ElectronicReceiptsRequest request) throws WxPayException;

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
  ElectronicReceiptsResult queryTransferElectronicResult(ElectronicReceiptsRequest request) throws WxPayException;

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
  InputStream transferDownload(String url) throws WxPayException;

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
  FundBalanceResult fundBalance(SpAccountTypeEnum accountType) throws WxPayException;

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
  FundBalanceResult spDayEndBalance(SpAccountTypeEnum accountType, String date);
}
