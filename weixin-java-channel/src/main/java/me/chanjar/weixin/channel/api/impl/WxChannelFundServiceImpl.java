package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.CHECK_QRCODE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_BALANCE_FLOW_DETAIL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_BALANCE_FLOW_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_BALANCE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_BANK_ACCOUNT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_BANK_BY_NUM_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_BANK_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_CITY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_PROVINCE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_QRCODE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_SUB_BANK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_WITHDRAW_DETAIL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.GET_WITHDRAW_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.SET_BANK_ACCOUNT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Fund.WITHDRAW_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelFundService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.fund.AccountInfo;
import me.chanjar.weixin.channel.bean.fund.AccountInfoParam;
import me.chanjar.weixin.channel.bean.fund.AccountInfoResponse;
import me.chanjar.weixin.channel.bean.fund.BalanceInfoResponse;
import me.chanjar.weixin.channel.bean.fund.FlowListResponse;
import me.chanjar.weixin.channel.bean.fund.FundsFlowResponse;
import me.chanjar.weixin.channel.bean.fund.FundsListParam;
import me.chanjar.weixin.channel.bean.fund.WithdrawDetailResponse;
import me.chanjar.weixin.channel.bean.fund.WithdrawListParam;
import me.chanjar.weixin.channel.bean.fund.WithdrawListResponse;
import me.chanjar.weixin.channel.bean.fund.WithdrawSubmitParam;
import me.chanjar.weixin.channel.bean.fund.WithdrawSubmitResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankCityResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankInfoResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankListResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankProvinceResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankSearchParam;
import me.chanjar.weixin.channel.bean.fund.bank.BranchInfoResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BranchSearchParam;
import me.chanjar.weixin.channel.bean.fund.qrcode.QrCheckResponse;
import me.chanjar.weixin.channel.bean.fund.qrcode.QrCodeResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 资金服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelFundServiceImpl implements WxChannelFundService {


  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelFundServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
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
  public WxChannelBaseResponse setBankAccount(AccountInfo accountInfo) throws WxErrorException {
    AccountInfoParam param = new AccountInfoParam(accountInfo);
    String resJson = shopService.post(SET_BANK_ACCOUNT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
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
