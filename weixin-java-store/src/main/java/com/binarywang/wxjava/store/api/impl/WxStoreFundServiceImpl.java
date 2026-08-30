package com.binarywang.wxjava.store.api.impl;


import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.CHECK_QRCODE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_BALANCE_FLOW_DETAIL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_BALANCE_FLOW_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_BALANCE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_BANK_ACCOUNT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_BANK_BY_NUM_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_BANK_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_CITY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_PROVINCE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_QRCODE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_SUB_BANK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_WITHDRAW_DETAIL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.GET_WITHDRAW_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.SET_BANK_ACCOUNT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Fund.WITHDRAW_URL;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreFundService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.fund.AccountInfo;
import com.binarywang.wxjava.store.bean.fund.AccountInfoParam;
import com.binarywang.wxjava.store.bean.fund.AccountInfoResponse;
import com.binarywang.wxjava.store.bean.fund.BalanceInfoResponse;
import com.binarywang.wxjava.store.bean.fund.FlowListResponse;
import com.binarywang.wxjava.store.bean.fund.FundsFlowResponse;
import com.binarywang.wxjava.store.bean.fund.FundsListParam;
import com.binarywang.wxjava.store.bean.fund.WithdrawDetailResponse;
import com.binarywang.wxjava.store.bean.fund.WithdrawListParam;
import com.binarywang.wxjava.store.bean.fund.WithdrawListResponse;
import com.binarywang.wxjava.store.bean.fund.WithdrawSubmitParam;
import com.binarywang.wxjava.store.bean.fund.WithdrawSubmitResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankCityResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankInfoResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankListResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankProvinceResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BankSearchParam;
import com.binarywang.wxjava.store.bean.fund.bank.BranchInfoResponse;
import com.binarywang.wxjava.store.bean.fund.bank.BranchSearchParam;
import com.binarywang.wxjava.store.bean.fund.qrcode.QrCheckResponse;
import com.binarywang.wxjava.store.bean.fund.qrcode.QrCodeResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 资金服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreFundServiceImpl implements WxStoreFundService {


  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreFundServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public BalanceInfoResponse getBalance() throws WxErrorException {
    String resJson = shopService.post(GET_BALANCE_URL, "{}");
    return ResponseUtils.decode(resJson, BalanceInfoResponse.class);
  }

  @Override
  public AccountInfoResponse getBankAccount() throws WxErrorException {
    String resJson = shopService.post(GET_BANK_ACCOUNT_URL, "{}");
    return ResponseUtils.decode(resJson, AccountInfoResponse.class);
  }

  @Override
  public FundsFlowResponse getFundsFlowDetail(String flowId) throws WxErrorException {
    String reqJson = "{\"flow_id\":\"" + flowId + "\"}";
    String resJson = shopService.post(GET_BALANCE_FLOW_DETAIL_URL, reqJson);
    return ResponseUtils.decode(resJson, FundsFlowResponse.class);
  }

  @Override
  public FlowListResponse listFundsFlow(FundsListParam param) throws WxErrorException {
    String resJson = shopService.post(GET_BALANCE_FLOW_LIST_URL, param);
    return ResponseUtils.decode(resJson, FlowListResponse.class);
  }

  @Override
  public WithdrawDetailResponse getWithdrawDetail(String withdrawId) throws WxErrorException {
    String reqJson = "{\"withdraw_id\":\"" + withdrawId + "\"}";
    String resJson = shopService.post(GET_WITHDRAW_DETAIL_URL, reqJson);
    return ResponseUtils.decode(resJson, WithdrawDetailResponse.class);
  }

  @Override
  public WithdrawListResponse listWithdraw(Integer pageNum, Integer pageSize, Long startTime, Long endTime)
    throws WxErrorException {
    WithdrawListParam param = new WithdrawListParam(pageNum, pageSize, startTime, endTime);
    String resJson = shopService.post(GET_WITHDRAW_LIST_URL, param);
    return ResponseUtils.decode(resJson, WithdrawListResponse.class);
  }

  @Override
  public WxStoreBaseResponse setBankAccount(AccountInfo accountInfo) throws WxErrorException {
    AccountInfoParam param = new AccountInfoParam(accountInfo);
    String resJson = shopService.post(SET_BANK_ACCOUNT_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WithdrawSubmitResponse submitWithdraw(Integer amount, String remark, String bankMemo)
    throws WxErrorException {
    WithdrawSubmitParam param = new WithdrawSubmitParam(amount, remark, bankMemo);
    String resJson = shopService.post(WITHDRAW_URL, param);
    return ResponseUtils.decode(resJson, WithdrawSubmitResponse.class);
  }

  @Override
  public BankInfoResponse getBankInfoByCardNo(String accountNumber) throws WxErrorException {
    String reqJson = "{\"account_number\":\"" + accountNumber + "\"}";
    String resJson = shopService.post(GET_BANK_BY_NUM_URL, reqJson);
    return ResponseUtils.decode(resJson, BankInfoResponse.class);
  }

  @Override
  public BankListResponse searchBankList(Integer offset, Integer limit, String keywords, Integer bankType)
    throws WxErrorException {
    BankSearchParam param = new BankSearchParam(offset, limit, keywords, bankType);
    String resJson = shopService.post(GET_BANK_LIST_URL, param);
    return ResponseUtils.decode(resJson, BankListResponse.class);
  }

  @Override
  public BankCityResponse searchCityList(String provinceCode) throws WxErrorException {
    String reqJson = "{\"province_code\":\"" + provinceCode + "\"}";
    String resJson = shopService.post(GET_CITY_URL, reqJson);
    return ResponseUtils.decode(resJson, BankCityResponse.class);
  }

  @Override
  public BankProvinceResponse getProvinceList() throws WxErrorException {
    String resJson = shopService.post(GET_PROVINCE_URL, "{}");
    return ResponseUtils.decode(resJson, BankProvinceResponse.class);
  }

  @Override
  public BranchInfoResponse searchBranchList(String bankCode, String cityCode, Integer offset, Integer limit)
    throws WxErrorException {
    BranchSearchParam param = new BranchSearchParam(bankCode, cityCode, offset, limit);
    String resJson = shopService.post(GET_SUB_BANK_URL, param);
    return ResponseUtils.decode(resJson, BranchInfoResponse.class);
  }

  @Override
  public QrCodeResponse getQrCode(String qrcodeTicket) throws WxErrorException {
    String reqJson = "{\"qrcode_ticket\":\"" + qrcodeTicket + "\"}";
    String resJson = shopService.post(GET_QRCODE_URL, reqJson);
    return ResponseUtils.decode(resJson, QrCodeResponse.class);
  }

  @Override
  public QrCheckResponse checkQrStatus(String qrcodeTicket) throws WxErrorException {
    String reqJson = "{\"qrcode_ticket\":\"" + qrcodeTicket + "\"}";
    String resJson = shopService.post(CHECK_QRCODE_URL, reqJson);
    return ResponseUtils.decode(resJson, QrCheckResponse.class);
  }
}
