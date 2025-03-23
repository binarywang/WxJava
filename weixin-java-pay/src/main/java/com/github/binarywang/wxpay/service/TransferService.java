package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 商家转账到零钱
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
public interface TransferService {

  /**
   * <pre>
   *
   * 发起商家转账API
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_1.shtml">发起商家转账API</a>
   * </pre>
   *
   * @param request 转账请求参数
   * @return TransferBatchesResult 转账结果
   * @throws WxPayException .
   */
  TransferBatchesResult transferBatches(TransferBatchesRequest request) throws WxPayException;

  /**
   * 解析商家转账结果
   * 详见<a href="https://pay.weixin.qq.com/docs/merchant/apis/batch-transfer-to-balance/transfer-batch-callback-notice.html"></a>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return the wx transfer notify result
   * @throws WxPayException the wx pay exception
   */
  TransferNotifyResult parseTransferNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   *
   * 微信批次单号查询批次单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/batch-id/{batch_id}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_2.shtml">微信批次单号查询批次单API</a>
   * </pre>
   *
   * @param request 查询请求参数
   * @return TransferBatchesResult 查询结果
   * @throws WxPayException .
   */
  QueryTransferBatchesResult transferBatchesBatchId(QueryTransferBatchesRequest request) throws WxPayException;

  /**
   * <pre>
   *
   * 微信明细单号查询明细单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/batch-id/{batch_id}/details/detail-id/{detail_id}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_3.shtml">微信明细单号查询明细单API</a>
   * </pre>
   *
   * @param batchId  微信批次单号
   * @param detailId 微信明细单号
   * @return TransferBatchDetailResult 查询结果
   * @throws WxPayException .
   */
  TransferBatchDetailResult transferBatchesBatchIdDetail(String batchId, String detailId) throws WxPayException;

  /**
   * <pre>
   *
   * 商家批次单号查询批次单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/out-batch-no/{out_batch_no}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_5.shtml">商家批次单号查询批次单API</a>
   * </pre>
   *
   * @param request 查询请求参数
   * @return TransferBatchesResult 查询结果
   * @throws WxPayException .
   * @throws WxPayException .
   */
  QueryTransferBatchesResult transferBatchesOutBatchNo(QueryTransferBatchesRequest request) throws WxPayException;

  /**
   * <pre>
   *
   * 商家明细单号查询明细单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/out-batch-no/{out_batch_no}/details/out-detail-no/{out_detail_no}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_6.shtml">商家明细单号查询明细单API</a>
   * </pre>
   *
   * @param outBatchNo  商家明细单号
   * @param outDetailNo 商家批次单号
   * @return TransferBatchDetailResult 查询结果
   * @throws WxPayException .
   */
  TransferBatchDetailResult transferBatchesOutBatchNoDetail(String outBatchNo, String outDetailNo) throws WxPayException;

  /**
   * <pre>
   *
   * 2025.1.15 开始新接口 发起商家转账API
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012716434">发起商家转账API</a>
   * </pre>
   *
   * @param request 转账请求参数
   * @return TransferBillsResult 转账结果
   * @throws WxPayException .
   */
  TransferBillsResult transferBills(TransferBillsRequest request) throws WxPayException;

  /**
   * <pre>
   *
   * 2025.1.15 开始新接口 撤销转账API
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills/out-bill-no/{out_bill_no}/cancel">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012716458">商户撤销转账API</a>
   * </pre>
   *
   * @param outBillNo 【商户单号】 商户系统内部的商家单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   * @return TransformBillsGetResult 转账单
   * @throws WxPayException .
   */
  TransferBillsCancelResult transformBillsCancel(String outBillNo) throws WxPayException;

  /**
   * <pre>
   *
   * 2025.1.15 开始新接口 发起商家转账API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills/out-bill-no/{out_bill_no}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012716437">商户单号查询转账单API</a>
   * </pre>
   *
   * @param outBillNo 【商户单号】 商户系统内部的商家单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   * @return TransformBillsGetResult 转账单
   * @throws WxPayException .
   */
  TransferBillsGetResult getBillsByOutBillNo(String outBillNo) throws WxPayException;

  /**
   * <pre>
   *
   * 2025.1.15 开始新接口 微信单号查询转账单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills/transfer-bill-no/{transfer_bill_no}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012716437">商户单号查询转账单API</a>
   * </pre>
   *
   * @param transferBillNo 【微信转账单号】 微信转账单号，微信商家转账系统返回的唯一标识
   * @return TransformBillsGetResult 转账单
   * @throws WxPayException .
   */
  TransferBillsGetResult getBillsByTransferBillNo(String transferBillNo) throws WxPayException;

  /**
   * 2025.1.15 开始新接口 解析商家转账结果
   * 详见<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012712115"></a>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return the wx transfer notify result
   * @throws WxPayException the wx pay exception
   */
  TransferBillsNotifyResult parseTransferBillsNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;
}
