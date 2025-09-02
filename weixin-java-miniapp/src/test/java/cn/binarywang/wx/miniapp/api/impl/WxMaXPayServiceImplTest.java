package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.bean.xpay.*;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaXPayServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testQueryUserBalance() throws Exception {
    WxMaXPayQueryUserBalanceRequest request = WxMaXPayQueryUserBalanceRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .userIp("127.0.0.1")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryUserBalanceResponse response = this.wxService.getWxMaXPayService().queryUserBalance(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCurrencyPay() throws Exception {
    WxMaXPayCurrencyPayRequest request = WxMaXPayCurrencyPayRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .userIp("127.0.0.1")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayCurrencyPayResponse response = this.wxService.getWxMaXPayService().currencyPay(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryOrder() throws Exception {
    WxMaXPayQueryOrderRequest request = WxMaXPayQueryOrderRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryOrderResponse response = this.wxService.getWxMaXPayService().queryOrder(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCancelCurrencyPay() throws Exception {
    WxMaXPayCancelCurrencyPayRequest request = WxMaXPayCancelCurrencyPayRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .userIp("127.0.0.1")
      .orderId("")
      .payOrderId("")
      .amount(1000L)
      .deviceType(WxMaConstants.XPayDeviceType.ANDROID)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayCancelCurrencyPayResponse response = this.wxService.getWxMaXPayService().cancelCurrencyPay(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testNotifyProvideGoods() throws Exception {
    WxMaXPayNotifyProvideGoodsRequest request = WxMaXPayNotifyProvideGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    boolean response = this.wxService.getWxMaXPayService().notifyProvideGoods(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testPresentCurrency() throws Exception {
    WxMaXPayPresentCurrencyRequest request = WxMaXPayPresentCurrencyRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("").deviceType(WxMaConstants.XPayDeviceType.ANDROID)
      .amount(100L)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayPresentCurrencyResponse response = this.wxService.getWxMaXPayService().presentCurrency(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testDownloadBill() throws Exception {
    WxMaXPayDownloadBillRequest request = WxMaXPayDownloadBillRequest.builder()
      .beginDs(20230801)
      .endDs(20230810)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayDownloadBillResponse response = this.wxService.getWxMaXPayService().downloadBill(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testRefundOrder() throws Exception {
    WxMaXPayRefundOrderRequest request = WxMaXPayRefundOrderRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("")
      .refundOrderId("")
      .leftFee(100L)
      .refundFee(10L)
      .bizMeta("").refundReason("").reqFrom("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayRefundOrderResponse response = this.wxService.getWxMaXPayService().refundOrder(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCreateWithdrawOrder() throws Exception {
    WxMaXPayCreateWithdrawOrderRequest request = WxMaXPayCreateWithdrawOrderRequest.builder()
      .withdrawNo("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .withdrawAmount("0.01")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayCreateWithdrawOrderResponse response = this.wxService.getWxMaXPayService().createWithdrawOrder(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testQueryWithdrawOrder() throws Exception {
    WxMaXPayQueryWithdrawOrderRequest request = WxMaXPayQueryWithdrawOrderRequest.builder()
      .withdrawNo("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryWithdrawOrderResponse response = this.wxService.getWxMaXPayService().queryWithdrawOrder(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testStartUploadGoods() throws Exception {
    WxMaXPayStartUploadGoodsRequest request = WxMaXPayStartUploadGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .uploadItem(new ArrayList<>())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    boolean response = this.wxService.getWxMaXPayService().startUploadGoods(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryUploadGoods() throws Exception {
    WxMaXPayQueryUploadGoodsRequest request = WxMaXPayQueryUploadGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryUploadGoodsResponse response = this.wxService.getWxMaXPayService().queryUploadGoods(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testStartPublishGoods() throws Exception {
    WxMaXPayStartPublishGoodsRequest request = WxMaXPayStartPublishGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .publishItem(new ArrayList<>())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    boolean response = this.wxService.getWxMaXPayService().startPublishGoods(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryPublishGoods() throws Exception {
    WxMaXPayQueryPublishGoodsRequest request = WxMaXPayQueryPublishGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryPublishGoodsResponse response = this.wxService.getWxMaXPayService().queryPublishGoods(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryBizBalance() throws Exception {
    WxMaXPayQueryBizBalanceRequest request = WxMaXPayQueryBizBalanceRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayQueryBizBalanceResponse response = this.wxService.getWxMaXPayService().queryBizBalance(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryTransferAccount() throws Exception {
    WxMaXPayQueryTransferAccountRequest request = WxMaXPayQueryTransferAccountRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayQueryTransferAccountResponse response = this.wxService.getWxMaXPayService().queryTransferAccount(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryAdverFunds() throws Exception {
    WxMaXPayQueryAdverFundsRequest request = WxMaXPayQueryAdverFundsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .page(0)
      .pageSize(10)
      .filter(new WxMaXPayQueryAdverFundsRequest.Filter())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayQueryAdverFundsResponse response = this.wxService.getWxMaXPayService().queryAdverFunds(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCreateFundsBill() throws Exception {
    WxMaXPayCreateFundsBillRequest request = WxMaXPayCreateFundsBillRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .transferAmount(0)
      .transferAccountUid(0L)
      .transferAccountName("")
      .transferAccountAgencyId(0)
      .requestId("")
      .settleBegin(0L)
      .settleEnd(0L)
      .authorizeAdvertise(0)
      .fundType(0)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayCreateFundsBillResponse response = this.wxService.getWxMaXPayService().createFundsBill(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testBindTransferAccount() throws Exception {
    WxMaXPayBindTransferAccountRequest request = WxMaXPayBindTransferAccountRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .transferAccountOrgName("")
      .transferAccountUid(0L)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaBaseResponse response = this.wxService.getWxMaXPayService().bindTransferAccount(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryFundsBill() throws Exception {
    WxMaXPayQueryFundsBillRequest request = WxMaXPayQueryFundsBillRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .page(0)
      .pageSize(10)
      .filter(new WxMaXPayQueryFundsBillRequest.Filter())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayQueryFundsBillResponse response = this.wxService.getWxMaXPayService().queryFundsBill(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testQueryRecoverBill() throws Exception {
    WxMaXPayQueryRecoverBillRequest request = WxMaXPayQueryRecoverBillRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .page(0)
      .pageSize(10)
      .filter(new WxMaXPayQueryRecoverBillRequest.Filter())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayQueryRecoverBillResponse response = this.wxService.getWxMaXPayService().queryRecoverBill(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testGetComplaintList() throws Exception {
    WxMaXPayGetComplaintListRequest request = WxMaXPayGetComplaintListRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .beginDate("")
      .endDate("")
      .offset(0)
      .limit(10)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayGetComplaintListResponse response = this.wxService.getWxMaXPayService().getComplaintList(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testGetComplaintDetail() throws Exception {
    WxMaXPayGetComplaintDetailRequest request = WxMaXPayGetComplaintDetailRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .complaintId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayGetComplaintDetailResponse response = this.wxService.getWxMaXPayService().getComplaintDetail(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testGetNegotiationHistory() throws Exception {
    WxMaXPayGetNegotiationHistoryRequest request = WxMaXPayGetNegotiationHistoryRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .limit(10)
      .offset(0)
      .complaintId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayGetNegotiationHistoryResponse response = this.wxService.getWxMaXPayService().getNegotiationHistory(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testResponseComplaint() throws Exception {
    WxMaXPayResponseComplaintRequest request = WxMaXPayResponseComplaintRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .complaintId("")
      .responseContent("")
      .responseImages(new ArrayList<>())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaBaseResponse response = this.wxService.getWxMaXPayService().responseComplaint(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCompleteComplaint() throws Exception {
    WxMaXPayCompleteComplaintRequest request = WxMaXPayCompleteComplaintRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .complaintId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaBaseResponse response = this.wxService.getWxMaXPayService().completeComplaint(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testUploadVpFile() throws Exception {
    WxMaXPayUploadVpFileRequest request = WxMaXPayUploadVpFileRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .base64Img("")
      .fileName("")
      .imgUrl("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayUploadVpFileResponse response = this.wxService.getWxMaXPayService().uploadVpFile(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testGetUploadFileSign() throws Exception {
    WxMaXPayGetUploadFileSignRequest request = WxMaXPayGetUploadFileSignRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .wxpayUrl("")
      .complaintId("")
      .convertCos(true)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("");
    WxMaXPayGetUploadFileSignResponse response = this.wxService.getWxMaXPayService().getUploadFileSign(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testDownloadAdverfundsOrder() throws Exception {
    WxMaXPayDownloadAdverfundsOrderRequest request = WxMaXPayDownloadAdverfundsOrderRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .fundId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setAppKey("123");
    WxMaXPayDownloadAdverfundsOrderResponse response = this.wxService.getWxMaXPayService().downloadAdverfundsOrder(request, sigParams);
    assertNotNull(response);
  }

}
