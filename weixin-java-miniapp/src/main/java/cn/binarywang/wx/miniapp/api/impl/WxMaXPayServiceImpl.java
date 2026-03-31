package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaXPayService;
import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.bean.xpay.*;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.XPay.*;


@RequiredArgsConstructor
@Slf4j
public class WxMaXPayServiceImpl implements WxMaXPayService {

  private final WxMaService service;

  @Override
  public WxMaXPayQueryUserBalanceResponse queryUserBalance(WxMaXPayQueryUserBalanceRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithBoth(QUERY_USER_BALANCE_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryUserBalanceResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryUserBalanceResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayCurrencyPayResponse currencyPay(WxMaXPayCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithBoth(CURRENCY_PAY_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayCurrencyPayResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayCurrencyPayResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryOrderResponse queryOrder(WxMaXPayQueryOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayCancelCurrencyPayResponse cancelCurrencyPay(WxMaXPayCancelCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithBoth(CANCEL_CURRENCY_PAY_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayCancelCurrencyPayResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayCancelCurrencyPayResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public boolean notifyProvideGoods(WxMaXPayNotifyProvideGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(NOTIFY_PROVIDE_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return true;
  }

  @Override
  public WxMaXPayPresentCurrencyResponse presentCurrency(WxMaXPayPresentCurrencyRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(PRESENT_CURRENCY_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayPresentCurrencyResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayPresentCurrencyResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayPresentGoodsResponse presentGoods(WxMaXPayPresentGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(PRESENT_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayPresentGoodsResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayPresentGoodsResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayDownloadBillResponse downloadBill(WxMaXPayDownloadBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(DOWNLOAD_BILL_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayDownloadBillResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayDownloadBillResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayRefundOrderResponse refundOrder(WxMaXPayRefundOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(REFUND_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayRefundOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayRefundOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayCreateWithdrawOrderResponse createWithdrawOrder(WxMaXPayCreateWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(CREATE_WITHDRAW_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayCreateWithdrawOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayCreateWithdrawOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryWithdrawOrderResponse queryWithdrawOrder(WxMaXPayQueryWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_WITHDRAW_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryWithdrawOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryWithdrawOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public boolean startUploadGoods(WxMaXPayStartUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(START_UPLOAD_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return true;
  }

  @Override
  public WxMaXPayQueryUploadGoodsResponse queryUploadGoods(WxMaXPayQueryUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_UPLOAD_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryUploadGoodsResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryUploadGoodsResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public boolean startPublishGoods(WxMaXPayStartPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(START_PUBLISH_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return true;
  }

  @Override
  public WxMaXPayQueryPublishGoodsResponse queryPublishGoods(WxMaXPayQueryPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_PUBLISH_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryPublishGoodsResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryPublishGoodsResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryBizBalanceResponse queryBizBalance(WxMaXPayQueryBizBalanceRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_BIZ_BALANCE_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryBizBalanceResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryBizBalanceResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryTransferAccountResponse queryTransferAccount(WxMaXPayQueryTransferAccountRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_TRANSFER_ACCOUNT_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryTransferAccountResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryTransferAccountResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryAdverFundsResponse queryAdverFunds(WxMaXPayQueryAdverFundsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_ADVER_FUNDS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryAdverFundsResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryAdverFundsResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayCreateFundsBillResponse createFundsBill(WxMaXPayCreateFundsBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(CREATE_FUNDS_BILL_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayCreateFundsBillResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayCreateFundsBillResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaBaseResponse bindTransferAccount(WxMaXPayBindTransferAccountRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(BIND_TRANSFER_ACCOUNT_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryFundsBillResponse queryFundsBill(WxMaXPayQueryFundsBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_FUNDS_BILL_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryFundsBillResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryFundsBillResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryRecoverBillResponse queryRecoverBill(WxMaXPayQueryRecoverBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_RECOVER_BILL_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryRecoverBillResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryRecoverBillResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }


  @Override
  public WxMaXPayGetComplaintListResponse getComplaintList(WxMaXPayGetComplaintListRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(GET_COMPLAINT_LIST_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayGetComplaintListResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayGetComplaintListResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayGetComplaintDetailResponse getComplaintDetail(WxMaXPayGetComplaintDetailRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(GET_COMPLAINT_DETAIL_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayGetComplaintDetailResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayGetComplaintDetailResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayGetNegotiationHistoryResponse getNegotiationHistory(WxMaXPayGetNegotiationHistoryRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(GET_NEGOTIATION_HISTORY_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayGetNegotiationHistoryResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayGetNegotiationHistoryResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaBaseResponse responseComplaint(WxMaXPayResponseComplaintRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(RESPONSE_COMPLAINT_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaBaseResponse completeComplaint(WxMaXPayCompleteComplaintRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(COMPLETE_COMPLAINT_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayUploadVpFileResponse uploadVpFile(WxMaXPayUploadVpFileRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(UPLOAD_VP_FILE_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayUploadVpFileResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayUploadVpFileResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayGetUploadFileSignResponse getUploadFileSign(WxMaXPayGetUploadFileSignRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(GET_UPLOAD_FILE_SIGN_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayGetUploadFileSignResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayGetUploadFileSignResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayDownloadAdverfundsOrderResponse downloadAdverfundsOrder(WxMaXPayDownloadAdverfundsOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(DOWNLOAD_ADVERFUNDS_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayDownloadAdverfundsOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayDownloadAdverfundsOrderResponse.class);
    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }
    return getDetailResponse;
  }
}
