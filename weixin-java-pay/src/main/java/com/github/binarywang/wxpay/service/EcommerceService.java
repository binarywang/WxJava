package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.*;
import com.github.binarywang.wxpay.bean.ecommerce.enums.FundBillTypeEnum;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 *  电商收付通相关服务类.
 *  接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_1_8.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e-combine.shtml
   * </pre>
   *
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 微信合单支付返回 transactions result
   * @throws WxPayException the wx pay exception
   */
  TransactionsResult combine(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 合单支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e-combine.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e-combine.shtml
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 combine transactions notify result
   * @throws WxPayException the wx pay exception
   */
  CombineTransactionsNotifyResult parseCombineNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 合单查询订单API
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_3.shtml
   * </pre>
   *
   * @param outTradeNo 合单商户订单号
   * @return 支付订单信息
   * @throws WxPayException the wx pay exception
   */
  CombineTransactionsResult queryCombineTransactions(String outTradeNo) throws WxPayException;

  /**
   * <pre>
   *  服务商模式普通支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   *  请求URL：https://api.mch.weixin.qq.com/v3/pay/partner/transactions/jsapi
   *  文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/transactions_sl.shtml
   *  </pre>
   *
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 调起支付需要的参数 transactions result
   * @throws WxPayException the wx pay exception
   */
  TransactionsResult partner(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   *  服务商模式普通支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   *  请求URL：https://api.mch.weixin.qq.com/v3/pay/partner/transactions/jsapi
   *  文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/transactions_sl.shtml
   *  </pre>
   *
   * @param <T>       the type parameter
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 调起支付需要的参数 t
   * @throws WxPayException the wx pay exception
   */
  <T> T partnerTransactions(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 普通支付通知回调数据处理
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e_transactions.shtml
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 partner transactions notify result
   * @throws WxPayException the wx pay exception
   */
  PartnerTransactionsNotifyResult parsePartnerNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 普通查询订单API
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/e_transactions/chapter3_5.shtml
   * </pre>
   *
   * @param request 商户订单信息
   * @return 支付订单信息
   * @throws WxPayException the wx pay exception
   */
  PartnerTransactionsResult queryPartnerTransactions(PartnerTransactionsQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 关闭普通订单API
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/e_transactions/chapter3_6.shtml
   * </pre>
   *
   * @param request 关闭普通订单请求
   * @throws WxPayException the wx pay exception
   * @return
   */
  String closePartnerTransactions(PartnerTransactionsCloseRequest request) throws WxPayException;

  /**
   * <pre>
   * 服务商账户实时余额
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/amount.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/amount.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/amount.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_3_11.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/amount.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_1.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_4_9.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_7.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_8.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_3.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_3.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_5.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_1.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_6_4.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_6_5.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_3.shtml
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
   * 二级商户账户余额提现API
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_3.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_6.shtml
   * </pre>
   *
   * @param outRequestNo 商户提现单号
   * @return 返回数据 return sp withdraw status result
   * @throws WxPayException the wx pay exception
   */
  SpWithdrawStatusResult querySpWithdrawByOutRequestNo(String outRequestNo) throws WxPayException;

  /**
   * <pre>
   * 修改结算账号API
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_4.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/bill.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/bill/chapter3_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/bill.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_5_1.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_5_2.shtml
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
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_5_3.shtml
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
   * 文档地址: https://pay.weixin.qq.com/docs/partner/apis/ecommerce-cancel/cancel-applications/create-cancel-application.html
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
   * 文档地址: https://pay.weixin.qq.com/docs/partner/apis/ecommerce-cancel/cancel-applications/get-cancel-application.html
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
   * 文档地址: https://pay.weixin.qq.com/docs/partner/apis/ecommerce-cancel/media/upload-media.html
   * </pre>
   *
   * @param imageFile 图片
   * @return 返回数据 return AccountCancelApplicationsResult
   * @throws WxPayException the wx pay exception
   */
  AccountCancelApplicationsMediaResult uploadMediaAccountCancelApplication(File imageFile) throws WxPayException, IOException;;
}
