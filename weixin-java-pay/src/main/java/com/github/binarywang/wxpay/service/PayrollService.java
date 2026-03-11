package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.marketing.payroll.*;
import com.github.binarywang.wxpay.bean.result.WxPayApplyBillV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 微工卡-对接微信api
 *
 * @author xiaoqiang
 * created on  2021/12/7 14:26
 */
public interface PayrollService {
  /**
   * 生成授权token
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/tokens
   * 请求方式：POST
   *
   * @param request 请求参数
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  TokensResult payrollCardTokens(TokensRequest request) throws WxPayException;

  /**
   * 查询微工卡授权关系API
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/relations/{openid}
   * 请求方式：GET
   *
   * @param request 请求参数
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  RelationsResult payrollCardRelations(RelationsRequest request) throws WxPayException;

  /**
   * 微工卡核身预下单API
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order
   * 请求方式：POST
   *
   * @param request 请求参数
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  PreOrderResult payrollCardPreOrder(PreOrderRequest request) throws WxPayException;

  /**
   * 获取核身结果API
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/{authenticate_number}
   * 请求方式：GET
   *
   * @param subMchid           子商户号
   * @param authenticateNumber 商家核身单号
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  AuthenticationsResult payrollCardAuthenticationsNumber(String subMchid, String authenticateNumber) throws WxPayException;

  /**
   * 查询核身记录API
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications
   * 请求方式：GET
   *
   * @param request 请求参数
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  AuthRecordResult payrollCardAuthentications(AuthRecordRequest request) throws WxPayException;

  /**
   * 微工卡核身预下单（流程中完成授权）
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order-with-auth
   * 请求方式：POST
   *
   * @param request 请求参数
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  PreOrderWithAuthResult payrollCardPreOrderWithAuth(PreOrderWithAuthRequest request) throws WxPayException;

  /**
   * 按日下载提现异常文件API
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw/bill-type/{bill_type}
   * 请求方式：GET
   *
   * @param billType 账单类型
   *                 NO_SUCC：提现异常账单，包括提现失败和提现退票账单。
   *                 示例值：NO_SUCC
   * @param billDate 账单日期 表示所在日期的提现账单，格式为YYYY-MM-DD。
   *                 例如：2008-01-01日发起的提现，2008-01-03日银行返回提现失败，则该提现数据将出现在bill_date为2008-01-03日的账单中。
   *                 示例值：2019-08-17
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  WxPayApplyBillV3Result merchantFundWithdrawBillType(String billType, String billDate, String tarType) throws WxPayException;

  /**
   * 微工卡批量转账API
   * 适用对象：服务商
   * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/transfer-batches
   * 请求方式：POST
   *
   * @param request 请求参数
   * @return 返回数据
   * @throws WxPayException the wx pay exception
   */
  PayrollTransferBatchesResult payrollCardTransferBatches(PayrollTransferBatchesRequest request) throws WxPayException;

}
