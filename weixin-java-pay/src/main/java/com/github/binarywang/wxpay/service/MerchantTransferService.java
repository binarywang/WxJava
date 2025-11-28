package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.merchanttransfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 商家转账到零钱（直联商户）
 *
 * @author glz
 * created on  2022-6-11
 */
public interface MerchantTransferService {

  /**
   * 发起商家转账API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_1.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/batches
   * 请求方式：POST
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   * 是否需要证书：是
   *
   * @param request the request
   * @return transfer create result
   * @throws WxPayException the wx pay exception
   */
  TransferCreateResult createTransfer(TransferCreateRequest request) throws WxPayException;

  /**
   * 微信批次单号查询批次单API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_2.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/batches/batch-id/{batch_id}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return batches query result
   * @throws WxPayException the wx pay exception
   */
  BatchesQueryResult queryWxBatches(WxBatchesQueryRequest request) throws WxPayException;

  /**
   * 微信明细单号查询明细单API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_3.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/batches/batch-id/{batch_id}/details/detail-id/{detail_id}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return details query result
   * @throws WxPayException the wx pay exception
   */
  DetailsQueryResult queryWxDetails(WxDetailsQueryRequest request) throws WxPayException;

  /**
   * 商家批次单号查询批次单API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_5.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/batches/out-batch-no/{out_batch_no}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return batches query result
   * @throws WxPayException the wx pay exception
   */
  BatchesQueryResult queryMerchantBatches(MerchantBatchesQueryRequest request) throws WxPayException;

  /**
   * 商家明细单号查询明细单API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_6.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/batches/out-batch-no/{out_batch_no}/details/out-detail-no/{out_detail_no}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return details query result
   * @throws WxPayException the wx pay exception
   */
  DetailsQueryResult queryMerchantDetails(MerchantDetailsQueryRequest request) throws WxPayException;

  /**
   * 转账电子回单申请受理API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_7.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/bill-receipt
   * 请求方式：POST
   * 接口限频： 单个商户 20QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return electronic bill result
   * @throws WxPayException the wx pay exception
   */
  ElectronicBillResult applyElectronicBill(ElectronicBillApplyRequest request) throws WxPayException;

  /**
   * 查询转账电子回单API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_8.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/bill-receipt/{out_batch_no}
   * 请求方式：GET
   *
   * @param outBatchNo the out batch no
   * @return electronic bill result
   * @throws WxPayException the wx pay exception
   */
  ElectronicBillResult queryElectronicBill(String outBatchNo) throws WxPayException;

  /**
   * 转账明细电子回单受理API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_9.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer-detail/electronic-receipts
   * 请求方式：POST
   * 接口限频： 单个商户 20QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   * 前置条件：只支持受理最近30天内的转账明细单
   *
   * @param request the request
   * @return detail electronic bill result
   * @throws WxPayException the wx pay exception
   */
  DetailElectronicBillResult applyDetailElectronicBill(DetailElectronicBillRequest request) throws WxPayException;


  /**
   * 查询转账明细电子回单受理结果API
   * <p>
   * 适用对象：直连商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_10.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/transfer-detail/electronic-receipts
   * 请求方式：GET
   * 前置条件：只支持查询最近90天内的转账明细单
   *
   * @param request the request
   * @return detail electronic bill result
   * @throws WxPayException the wx pay exception
   */
  DetailElectronicBillResult queryDetailElectronicBill(DetailElectronicBillRequest request) throws WxPayException;
}
