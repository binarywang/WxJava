package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.marketing.payroll.*;
import com.github.binarywang.wxpay.bean.marketing.transfer.PartnerTransferRequest;
import com.github.binarywang.wxpay.bean.marketing.transfer.PartnerTransferResult;
import com.github.binarywang.wxpay.bean.result.WxPayApplyBillV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * 微工卡（服务商）
 *
 * @author xiaoqiang
 * created on  2021/12/9
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class PayrollServiceImplTest {


  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void payrollCardTokens() throws WxPayException {
    TokensRequest request = new TokensRequest();
    request.setOpenid("onqOjjmo8wmTOOtSKwXtGjg9Gb58");
    request.setAppid("wxa1111111");
    request.setSubMchid("1111111");
    request.setSubAppid("wxa1111111");
    request.setUserName("LP7bT4hQXUsOZCEvK2YrSiqFsnP0oRMfeoLN0vBg");
    request.setIdCardNumber("7FzH5XksJG3a8HLLsaaUV6K54y1OnPMY5");
    request.setEmploymentType("LONG_TERM_EMPLOYMENT");
    TokensResult tokensResult = wxPayService.getPayrollService().payrollCardTokens(request);
    log.info(tokensResult.toString());

  }

  @Test
  public void payrollCardRelations() throws WxPayException {
    RelationsRequest request = new RelationsRequest();
    request.setOpenid("onqOjjmo8wmTOOtSKwXtGjg9Gb58");
    request.setSubMchid("1111111");
    request.setAppid("wxa1111111");
    request.setSubAppid("wxa1111111");
    RelationsResult relationsResult = wxPayService.getPayrollService().payrollCardRelations(request);
    log.info(relationsResult.toString());

  }


  @Test
  public void payrollCardPreOrder() throws WxPayException {
    PreOrderRequest request = new PreOrderRequest();
    request.setOpenid("onqOjjmo8wmTOOtSKwXtGjg9Gb58");
    request.setSubMchid("1111111");
    request.setAppid("wxa1111111");
    request.setSubAppid("wxa1111111");
    request.setAuthenticateNumber("mcdhehfgisdhfjghed39384564i83");
    request.setProjectName("某项目");
    request.setEmployerName("某单位名称");
    PreOrderResult preOrderResult = wxPayService.getPayrollService().payrollCardPreOrder(request);
    log.info(preOrderResult.toString());

  }

  @Test
  public void payrollCardAuthenticationsNumber() throws WxPayException {
    String subMchid = "1111111";
    String authenticateNumber = "mcdhehfgisdhfjghed39384564i83";
    AuthenticationsResult authenticationsResult = wxPayService.getPayrollService().payrollCardAuthenticationsNumber(subMchid, authenticateNumber);
    log.info(authenticationsResult.toString());

  }

  @Test
  public void payrollCardAuthentications() throws WxPayException {
    AuthRecordRequest request = new AuthRecordRequest();
    request.setOpenid("onqOjjmo8wmTOOtSKwXtGjg9Gb58");
    request.setSubMchid("1111111");
    request.setAppid("wxa1111111");
    request.setSubAppid("wxa1111111");
    request.setAuthenticateDate("2020-12-25");
    request.setAuthenticateState("AUTHENTICATE_SUCCESS");
    request.setOffset(0);
    request.setLimit(10);
    AuthRecordResult authRecordResult = wxPayService.getPayrollService().payrollCardAuthentications(request);
    log.info(authRecordResult.toString());

  }

  @Test
  public void payrollCardPreOrderWithAuth() throws WxPayException {
    PreOrderWithAuthRequest request = new PreOrderWithAuthRequest();
    request.setOpenid("onqOjjmo8wmTOOtSKwXtGjg9Gb58");
    request.setSubMchid("1111111");
    request.setAppid("wxa1111111");
    request.setSubAppid("wxa1111111");
    request.setAuthenticateNumber("mcdhehfgisdhfjghed39384564i83");
    request.setEmployerName("某用工企业");
    request.setEmploymentType("LONG_TERM_EMPLOYMENT");
    request.setIdCardNumber("7FzH5XksJG3a8HLLsaaUV6K54y1OnPMY5");
    request.setProjectName("某项目");
    request.setUserName("LP7bT4hQXUsOZCEvK2YrSiqFsnP0oRMfeoLN0vBg");
    PreOrderWithAuthResult preOrderWithAuthResult = wxPayService.getPayrollService().payrollCardPreOrderWithAuth(request);
    log.info(preOrderWithAuthResult.toString());

  }

  @Test
  public void merchantFundWithdrawBillType() throws WxPayException {
    String billType = "NO_SUCC";
    String billDate = "2019-08-17";
    WxPayApplyBillV3Result result = wxPayService.getPayrollService().merchantFundWithdrawBillType(billType, billDate, null);
    log.info(result.toString());
  }

  @Test
  public void payrollCardTransferBatches() throws WxPayException {
    PayrollTransferBatchesRequest request = PayrollTransferBatchesRequest.builder()
      .appid("wxa1111111")
      .subMchid("1111111")
      .subAppid("wxa1111111")
      .outBatchNo("plfk2020042013" + System.currentTimeMillis())
      .batchName("2019年1月深圳分部报销单")
      .batchRemark("2019年1月深圳分部报销单")
      .totalAmount(200000L)
      .totalNum(1)
      .transferDetailList(Collections.singletonList(
        PayrollTransferBatchesRequest.TransferDetail.builder()
          .outDetailNo("x23zy545Bd5436" + System.currentTimeMillis())
          .transferAmount(200000L)
          .transferRemark("2020年4月报销")
          .openid("o-MYE42l80oelYMDE34nYD456Xoy")
          .userName("张三")
          .build()
      ))
      .build();
    PayrollTransferBatchesResult result = wxPayService.getPayrollService().payrollCardTransferBatches(request);
    log.info(result.toString());
  }

}
