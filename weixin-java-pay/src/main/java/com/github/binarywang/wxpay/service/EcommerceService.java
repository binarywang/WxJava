package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.*;
import com.github.binarywang.wxpay.bean.ecommerce.enums.FundBillTypeEnum;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.notify.CombineNotifyResult;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayPartnerNotifyV3Result;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.CombineQueryResult;
import com.github.binarywang.wxpay.bean.result.CombineTransactionsResult;
import com.github.binarywang.wxpay.bean.result.WxPayPartnerOrderQueryV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 *  电商收付通相关服务类.
 *  <a href="https://pay.weixin.qq.com/doc/v3/partner/4012086891">产品介绍</a>
 * </pre>
 *
 * @author cloudX
 * created on  2020 /08/17
 */
public interface EcommerceService {
  /**
   * <pre>
   * 二级商户进件API
   * 接口地址: https://api.mch.weixin.qq.com/v3/ecommerce/applyments/
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012713017">接口文档</a>
   *
   * </pre>
   *
   * @param request 请求对象
   * @return . applyments result
   * @throws WxPayException the wx pay exception
   */
  ApplymentsResult createApply(ApplymentsRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询申请状态API
   * 请求URL: https://api.mch.weixin.qq.com/v3/ecommerce/applyments/{applyment_id}
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012691469">接口文档</a>
   * </pre>
   *
   * @param applymentId 申请单ID
   * @return . applyments status result
   * @throws WxPayException the wx pay exception
   */
  ApplymentsStatusResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException;

  /**
   * <pre>
   * 查询申请状态API
   * 请求URL: https://api.mch.weixin.qq.com/v3/ecommerce/applyments/out-request-no/{out_request_no}
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012691376">接口文档</a>
   * </pre>
   *
   * @param outRequestNo 业务申请编号
   * @return . applyments status result
   * @throws WxPayException the wx pay exception
   */
  ApplymentsStatusResult queryApplyStatusByOutRequestNo(String outRequestNo) throws WxPayException;

  /**
   * <pre>
   * 合单支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012089542">接口文档</a>
   * </pre>
   *
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 微信合单支付返回 CombineTransactionsResult
   * @throws WxPayException the wx pay exception
   */
  CombineTransactionsResult combine(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 合单支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012089542">接口文档</a>
   * </pre>
   *
   * @param <T>       the type parameter
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 调起支付需要的参数 t
   * @throws WxPayException the wx pay exception
   */
  <T> T combineTransactions(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 合单支付通知回调数据处理
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012237246">接口文档</a>
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 CombineNotifyResult
   * @throws WxPayException the wx pay exception
   */
  CombineNotifyResult parseCombineNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 合单查询订单API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012761049">接口文档</a>
   * </pre>
   *
   * @param combineOutTradeNo 合单商户订单号
   * @return 支付订单信息
   * @throws WxPayException the wx pay exception
   */
  CombineQueryResult queryCombine(String combineOutTradeNo) throws WxPayException;

  /**
   * <pre>
   * 合单关闭订单API
   * 请求URL: https://api.mch.weixin.qq.com/v3/combine-transactions/out-trade-no/{combine_out_trade_no}/close
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012761093">接口文档</a>
   * </pre>
   *
   * @param request 请求对象
   * @throws WxPayException the wx pay exception
   */
  void closeCombine(CombineCloseRequest request) throws WxPayException;

  /**
   * <pre>
   *  服务商模式普通支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   *  请求URL：https://api.mch.weixin.qq.com/v3/pay/partner/transactions/jsapi
   *  <a href="https://pay.weixin.qq.com/doc/v3/partner/4012088031">接口文档</a>
   *  </pre>
   *
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 调起支付需要的参数 WxPayUnifiedOrderV3Result
   * @throws WxPayException the wx pay exception
   */
  WxPayUnifiedOrderV3Result unifiedPartnerOrder(TradeTypeEnum tradeType, WxPayPartnerUnifiedOrderV3Request request) throws WxPayException;

  /**
   * <pre>
   *  服务商模式普通支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   *  请求URL：https://api.mch.weixin.qq.com/v3/pay/partner/transactions/jsapi
   *  <a href="https://pay.weixin.qq.com/doc/v3/partner/4012088031">接口文档</a>
   *  </pre>
   *
   * @param <T>       the type parameter
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 调起支付需要的参数 t
   * @throws WxPayException the wx pay exception
   */
  <T> T createPartnerOrder(TradeTypeEnum tradeType, WxPayPartnerUnifiedOrderV3Request request) throws WxPayException;

  /**
   * <pre>
   * 普通支付通知回调数据处理
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012090195">接口文档</a>
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 WxPayPartnerNotifyV3Result
   * @throws WxPayException the wx pay exception
   */
  WxPayPartnerNotifyV3Result parsePartnerNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 普通查询订单API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012760565">接口文档</a>
   * </pre>
   *
   * @param request 商户订单信息
   * @return 支付订单信息
   * @throws WxPayException the wx pay exception
   */
  WxPayPartnerOrderQueryV3Result queryPartnerOrder(WxPayPartnerOrderQueryV3Request request) throws WxPayException;

  /**
   * <pre>
   * 关闭普通订单API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012760574">接口文档</a>
   * </pre>
   *
   * @param request 请求对象
   * @throws WxPayException the wx pay exception
   */
  void closePartnerOrder(WxPayPartnerOrderCloseV3Request request) throws WxPayException;

  /**
   * <pre>
   * 服务商账户实时余额
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476700">接口文档</a>
   * </pre>
   *
   * @param accountType 服务商账户类型
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  FundBalanceResult spNowBalance(SpAccountTypeEnum accountType) throws WxPayException;

  /**
   * <pre>
   * 服务商账户日终余额
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476702">接口文档</a>
   * </pre>
   *
   * @param accountType 服务商账户类型
   * @param date        查询日期 2020-09-11
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  FundBalanceResult spDayEndBalance(SpAccountTypeEnum accountType, String date) throws WxPayException;

  /**
   * <pre>
   * 二级商户号账户实时余额
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476690">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  FundBalanceResult subNowBalance(String subMchid) throws WxPayException;

  /**
   * <pre>
   * 二级商户号账户实时余额
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476690">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param accountType 账户类型
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  FundBalanceResult subNowBalance(String subMchid, SpAccountTypeEnum accountType) throws WxPayException;

  /**
   * <pre>
   * 二级商户号账户日终余额
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476693">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param date     查询日期 2020-09-11
   * @return 返回数据 fund balance result
   * @throws WxPayException the wx pay exception
   */
  FundBalanceResult subDayEndBalance(String subMchid, String date) throws WxPayException;

  /**
   * <pre>
   * 请求分账API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012691594">接口文档</a>
   * </pre>
   *
   * @param request 分账请求
   * @return 返回数据 profit sharing result
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询分账结果API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477734">接口文档</a>
   * </pre>
   *
   * @param request 查询分账请求
   * @return 返回数据 profit sharing result
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult queryProfitSharing(ProfitSharingQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询订单剩余待分金额API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477751">接口文档</a>
   * </pre>
   *
   * @param request 查询订单剩余待分金额请求
   * @return 返回数据 profit sharing UnSplitAmount result
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingOrdersUnSplitAmountResult queryProfitSharingOrdersUnsplitAmount(ProfitSharingOrdersUnSplitAmountRequest request) throws WxPayException;

  /**
   * <pre>
   * 添加分账接收方API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477758">接口文档</a>
   * </pre>
   *
   * @param request 添加分账接收方
   * @return 返回数据 profit sharing result
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingReceiverResult addReceivers(ProfitSharingReceiverRequest request) throws WxPayException;

  /**
   * <pre>
   * 删除分账接收方API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477759">接口文档</a>
   * </pre>
   *
   * @param request 删除分账接收方
   * @return 返回数据 profit sharing result
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingReceiverResult deleteReceivers(ProfitSharingReceiverRequest request) throws WxPayException;

  /**
   * <pre>
   * 请求分账回退API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477737">接口文档</a>
   * </pre>
   *
   * @param request 分账回退请求
   * @return 返回数据 return orders result
   * @throws WxPayException the wx pay exception
   */
  ReturnOrdersResult returnOrders(ReturnOrdersRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询分账回退API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477740">接口文档</a>
   * </pre>
   *
   * @param request 查询分账回退请求
   * @return 返回数据 return orders result
   * @throws WxPayException the wx pay exception
   */
  ReturnOrdersResult queryReturnOrders(ReturnOrdersQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 完结分账API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477745">接口文档</a>
   * </pre>
   *
   * @param request 完结分账请求
   * @return 返回数据 return orders result
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult finishOrder(FinishOrderRequest request) throws WxPayException;

  /**
   * <pre>
   * 退款申请API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476892">接口文档</a>
   * </pre>
   *
   * @param request 退款请求
   * @return 返回数据 return refunds result
   * @throws WxPayException the wx pay exception
   */
  RefundsResult refunds(RefundsRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询退款API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476908">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param refundId 微信退款单号
   * @return 返回数据 return refunds result
   * @throws WxPayException the wx pay exception
   */
  RefundQueryResult queryRefundByRefundId(String subMchid, String refundId) throws WxPayException;


  /**
   * <pre>
   * 垫付退款回补API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476927">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param refundId 微信退款单号
   * @return 返回数据 return refunds result
   * @throws WxPayException the wx pay exception
   */
  ReturnAdvanceResult refundsReturnAdvance(String subMchid, String refundId) throws WxPayException;


  /**
   * <pre>
   * 查询垫付回补结果API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476916">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param refundId 微信退款单号
   * @return 返回数据 return refunds result
   * @throws WxPayException the wx pay exception
   */
  ReturnAdvanceResult queryRefundsReturnAdvance(String subMchid, String refundId) throws WxPayException;
  /**
   * <pre>
   * 查询退款API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476911">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param outRefundNo 商户退款单号
   * @return 返回数据 return refunds result
   * @throws WxPayException the wx pay exception
   */
  RefundQueryResult queryRefundByOutRefundNo(String subMchid, String outRefundNo) throws WxPayException;

  /**
   * <pre>
   * 退款通知回调数据处理
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012124635">接口文档</a>
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 partner refund notify result
   * @throws WxPayException the wx pay exception
   */
  RefundNotifyResult parseRefundNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 提现状态变更通知回调数据处理
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4013049135">接口文档</a>
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 withdraw notify result
   * @throws WxPayException the wx pay exception
   */
  WithdrawNotifyResult parseWithdrawNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 二级商户账户余额提现API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476652">接口文档</a>
   * </pre>
   *
   * @param request 提现请求
   * @return 返回数据 return withdraw result
   * @throws WxPayException the wx pay exception
   */
  SubWithdrawResult subWithdraw(SubWithdrawRequest request) throws WxPayException;

  /**
   * <pre>
   * 电商平台提现API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476670">接口文档</a>
   * </pre>
   *
   * @param request 提现请求
   * @return 返回数据 return withdraw result
   * @throws WxPayException the wx pay exception
   */
  SpWithdrawResult spWithdraw(SpWithdrawRequest request) throws WxPayException;

  /**
   * <pre>
   * 二级商户查询提现状态API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476656">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param outRequestNo 商户提现单号
   * @return 返回数据 return sub withdraw status result
   * @throws WxPayException the wx pay exception
   */
  SubWithdrawStatusResult querySubWithdrawByOutRequestNo(String subMchid, String outRequestNo) throws WxPayException;

  /**
   * <pre>
   * 电商平台查询提现状态API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476672">接口文档</a>
   * </pre>
   *
   * @param outRequestNo 商户提现单号
   * @return 返回数据 return sp withdraw status result
   * @throws WxPayException the wx pay exception
   */
  SpWithdrawStatusResult querySpWithdrawByOutRequestNo(String outRequestNo) throws WxPayException;

  /**
   * <pre>
   * 平台查询预约提现状态（根据微信支付预约提现单号查询）
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476674">接口文档</a>
   * </pre>
   *
   * @param withdrawId 微信支付提现单号
   * @return 返回数据 return sp withdraw status result
   * @throws WxPayException the wx pay exception
   */
  SpWithdrawStatusResult querySpWithdrawByWithdrawId(String withdrawId) throws WxPayException;

  /**
   * <pre>
   * 二级商户按日终余额预约提现
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4013328143">接口文档</a>
   * </pre>
   *
   * @param request 提现请求
   * @return 返回数据 return day-end balance withdraw result
   * @throws WxPayException the wx pay exception
   */
  SubDayEndBalanceWithdrawResult subDayEndBalanceWithdraw(SubDayEndBalanceWithdrawRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询二级商户按日终余额预约提现状态
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4013328163">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号
   * @param outRequestNo 商户提现单号
   * @return 返回数据 return day-end balance withdraw status result
   * @throws WxPayException the wx pay exception
   */
  SubDayEndBalanceWithdrawStatusResult querySubDayEndBalanceWithdraw(String subMchid, String outRequestNo) throws WxPayException;

  /**
   * <pre>
   * 修改结算账号API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012761138">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号。
   * @param request 结算账号
   * @throws WxPayException the wx pay exception
   */
  void modifySettlement(String subMchid, SettlementRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询结算账户API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012761142">接口文档</a>
   * </pre>
   *
   * @param subMchid 二级商户号。
   * @return 返回数据 return settlement result
   * @throws WxPayException the wx pay exception
   */
  SettlementResult querySettlement(String subMchid) throws WxPayException;

  /**
   * <pre>
   * 请求账单API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012760667">接口文档</a>
   * </pre>
   *
   * @param request 请求信息。
   * @return 返回数据 return trade bill result
   * @throws WxPayException the wx pay exception
   */
  TradeBillResult applyBill(TradeBillRequest request) throws WxPayException;

  /**
   * <pre>
   * 申请资金账单API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012760672">接口文档</a>
   * </pre>
   *
   * @param billType 账单类型。
   * @param request 请求信息。
   * @return 返回数据 return fund bill result
   * @throws WxPayException the wx pay exception
   */
  FundBillResult applyFundBill(FundBillTypeEnum billType, FundBillRequest request) throws WxPayException;

  /**
   * <pre>
   * 下载账单API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012124894">接口文档</a>
   * </pre>
   *
   * @param url 微信返回的账单地址。
   * @return 返回数据 return inputStream
   * @throws WxPayException the wx pay exception
   */
  InputStream downloadBill(String url) throws WxPayException;


  /**
   * <pre>
   * 请求补差API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477631">接口文档</a>
   * </pre>
   *
   * @param subsidiesCreateRequest 请求补差。
   * @return 返回数据 return SubsidiesCreateResult
   * @throws WxPayException the wx pay exception
   */
  SubsidiesCreateResult subsidiesCreate(SubsidiesCreateRequest subsidiesCreateRequest) throws WxPayException;

  /**
   * <pre>
   * 请求补差回退API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477636">接口文档</a>
   * </pre>
   *
   * @param subsidiesReturnRequest 请求补差。
   * @return 返回数据 return SubsidiesReturnResult
   * @throws WxPayException the wx pay exception
   */
  SubsidiesReturnResult subsidiesReturn(SubsidiesReturnRequest subsidiesReturnRequest) throws WxPayException;

  /**
   * <pre>
   * 取消补差API
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012477639">接口文档</a>
   * </pre>
   *
   * @param subsidiesCancelRequest 请求补差。
   * @return 返回数据 return SubsidiesCancelResult
   * @throws WxPayException the wx pay exception
   */
  SubsidiesCancelResult subsidiesCancel(SubsidiesCancelRequest subsidiesCancelRequest) throws WxPayException;

  /**
   * <pre>
   * 提交注销申请单
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476217">接口文档</a>
   * </pre>
   *
   * @param accountCancelApplicationsRequest 提交注销申请单
   * @return 返回数据 return AccountCancelApplicationsResult
   * @throws WxPayException the wx pay exception
   */
  AccountCancelApplicationsResult createdAccountCancelApplication(AccountCancelApplicationsRequest accountCancelApplicationsRequest) throws WxPayException;

  /**
   * <pre>
   * 查询注销单状态
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012476223">接口文档</a>
   * </pre>
   *
   * @param outApplyNo 注销申请单号
   * @return 返回数据 return AccountCancelApplicationsResult
   * @throws WxPayException the wx pay exception
   */
  AccountCancelApplicationsResult getAccountCancelApplication(String outApplyNo) throws WxPayException;

  /**
   * <pre>
   * 注销单资料图片上传
   * <a href="https://pay.weixin.qq.com/doc/v3/partner/4012691710">接口文档</a>
   * </pre>
   *
   * @param imageFile 图片
   * @return 返回数据 return AccountCancelApplicationsResult
   * @throws WxPayException the wx pay exception
   */
  AccountCancelApplicationsMediaResult uploadMediaAccountCancelApplication(File imageFile) throws WxPayException, IOException;;
}
