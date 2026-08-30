package com.binarywang.wxjava.store.api;


import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.fund.AccountInfo;
import com.binarywang.wxjava.store.bean.fund.AccountInfoResponse;
import com.binarywang.wxjava.store.bean.fund.BalanceInfoResponse;
import com.binarywang.wxjava.store.bean.fund.FlowListResponse;
import com.binarywang.wxjava.store.bean.fund.FundsFlowResponse;
import com.binarywang.wxjava.store.bean.fund.FundsListParam;
import com.binarywang.wxjava.store.bean.fund.WithdrawDetailResponse;
import com.binarywang.wxjava.store.bean.fund.WithdrawListResponse;
import com.binarywang.wxjava.store.bean.fund.WithdrawSubmitResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankCityResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankInfoResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankListResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankProvinceResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BranchInfoResponse;
import com.binarywang.wxjava.store.bean.fund.qrcode.QrCheckResponse;
import com.binarywang.wxjava.store.bean.fund.qrcode.QrCodeResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 资金相关服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreFundService {

  /**
   * 获取账户余额
   *
   * @return 账户余额
   *
   * @throws WxErrorException 异常
   */
  BalanceInfoResponse getBalance() throws WxErrorException;

  /**
   * 获取结算账户
   *
   * @return 结算账户
   *
   * @throws WxErrorException 异常
   */
  AccountInfoResponse getBankAccount() throws WxErrorException;

  /**
   * 获取资金流水详情
   *
   * @param flowId 资金流水号
   * @return 资金流水详情
   *
   * @throws WxErrorException 异常
   */
  FundsFlowResponse getFundsFlowDetail(String flowId) throws WxErrorException;

  /**
   * 获取资金流水列表
   *
   * @param param 资金流水列表参数
   * @return 资金流水列表
   *
   * @throws WxErrorException 异常
   */
  FlowListResponse listFundsFlow(FundsListParam param) throws WxErrorException;

  /**
   * 获取提现记录
   *
   * @param withdrawId 提现单号
   * @return 提现记录
   *
   * @throws WxErrorException 异常
   */
  WithdrawDetailResponse getWithdrawDetail(String withdrawId) throws WxErrorException;

  /**
   * 获取提现记录列表
   *
   * @param pageNum   页码
   * @param pageSize  每页大小
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @return 提现记录列表
   *
   * @throws WxErrorException 异常
   */
  WithdrawListResponse listWithdraw(Integer pageNum, Integer pageSize, Long startTime, Long endTime)
    throws WxErrorException;

  /**
   * 修改结算账户
   *
   * @param accountInfo 结算账户信息
   * @return 修改结果
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse setBankAccount(AccountInfo accountInfo) throws WxErrorException;

  /***
   * 商户提现
   *
   * @param amount 提现金额（单位：分）
   * @param remark 提现备注
   * @param bankMemo 银行附言
   * @return 提现结果
   * @throws WxErrorException 异常
   */
  WithdrawSubmitResponse submitWithdraw(Integer amount, String remark, String bankMemo) throws WxErrorException;

  /**
   * 根据卡号查银行信息
   *
   * @param accountNumber 卡号
   * @return 银行信息
   *
   * @throws WxErrorException 异常
   */
  BankInfoResponse getBankInfoByCardNo(String accountNumber) throws WxErrorException;

  /**
   * 搜索银行列表
   *
   * @param offset   偏移量
   * @param limit    每页数据大小
   * @param keywords 银行关键字
   * @param bankType 银行类型(1:对私银行,2:对公银行; 默认对公)
   * @return 银行列表
   *
   * @throws WxErrorException 异常
   */
  BankListResponse searchBankList(Integer offset, Integer limit, String keywords, Integer bankType)
    throws WxErrorException;

  /**
   * 查询城市列表
   *
   * @param provinceCode 省份编码
   * @return 城市列表
   *
   * @throws WxErrorException 异常
   */
  BankCityResponse searchCityList(String provinceCode) throws WxErrorException;

  /**
   * 查询大陆银行省份列表
   *
   * @return 省份列表
   *
   * @throws WxErrorException 异常
   */
  BankProvinceResponse getProvinceList() throws WxErrorException;

  /**
   * 查询支行列表
   *
   * @param bankCode 银行编码
   * @param cityCode 城市编码
   * @param offset   偏移量
   * @param limit    每页数据大小
   * @return 支行列表
   *
   * @throws WxErrorException 异常
   */
  BranchInfoResponse searchBranchList(String bankCode, String cityCode, Integer offset, Integer limit)
    throws WxErrorException;

  /**
   * 获取二维码
   *
   * @param qrcodeTicket 二维码ticket
   * @return 二维码响应
   *
   * @throws WxErrorException 异常
   */
  QrCodeResponse getQrCode(String qrcodeTicket) throws WxErrorException;

  /**
   * 查询扫码状态
   *
   * @param qrcodeTicket 二维码ticket
   * @return 扫码状态
   *
   * @throws WxErrorException 异常
   */
  QrCheckResponse checkQrStatus(String qrcodeTicket) throws WxErrorException;
}
