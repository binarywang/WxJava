package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.transfer.BusinessOperationTransferRequest;
import com.github.binarywang.wxpay.bean.transfer.BusinessOperationTransferResult;
import com.github.binarywang.wxpay.bean.transfer.BusinessOperationTransferQueryRequest;
import com.github.binarywang.wxpay.bean.transfer.BusinessOperationTransferQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 运营工具-商家转账API
 * <p>
 * 微信支付为商户提供的运营工具转账能力，用于商户的日常运营活动中进行转账操作
 * 
 * @author WxJava Team
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
 */
public interface BusinessOperationTransferService {

  /**
   * <pre>
   * 发起运营工具商家转账
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
   * </pre>
   *
   * @param request 运营工具转账请求参数
   * @return BusinessOperationTransferResult 转账结果
   * @throws WxPayException 微信支付异常
   */
  BusinessOperationTransferResult createOperationTransfer(BusinessOperationTransferRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询运营工具转账结果
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills/out-bill-no/{out_bill_no}
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
   * </pre>
   *
   * @param request 查询请求参数
   * @return BusinessOperationTransferQueryResult 查询结果
   * @throws WxPayException 微信支付异常
   */
  BusinessOperationTransferQueryResult queryOperationTransfer(BusinessOperationTransferQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 通过商户单号查询运营工具转账结果
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills/out-bill-no/{out_bill_no}
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
   * </pre>
   *
   * @param outBillNo 商户单号
   * @return BusinessOperationTransferQueryResult 查询结果
   * @throws WxPayException 微信支付异常
   */
  BusinessOperationTransferQueryResult queryOperationTransferByOutBillNo(String outBillNo) throws WxPayException;

  /**
   * <pre>
   * 通过微信转账单号查询运营工具转账结果
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：https://api.mch.weixin.qq.com/v3/fund-app/mch-transfer/transfer-bills/transfer-bill-no/{transfer_bill_no}
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
   * </pre>
   *
   * @param transferBillNo 微信转账单号
   * @return BusinessOperationTransferQueryResult 查询结果
   * @throws WxPayException 微信支付异常
   */
  BusinessOperationTransferQueryResult queryOperationTransferByTransferBillNo(String transferBillNo) throws WxPayException;
}