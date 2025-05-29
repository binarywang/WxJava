package com.github.binarywang.wxpay.service.impl;
import com.google.common.collect.Lists;

import com.github.binarywang.wxpay.bean.ecommerce.*;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.RandomUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class EcommerceServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  @Test
  public void testNotifySign() {
    //通知报文主体
    String notifyData = "";
    //请求头  Wechatpay-Timestamp
    String timeStamp = "";
    //请求头  Wechatpay-Nonce
    String nonce = "";
    //请求头  Wechatpay-Signature
    String signed = "";
    //请求头  Wechatpay-Serial
    String serialNo = "";

    SignatureHeader header = new SignatureHeader();
    header.setNonce(nonce);
    header.setSerialNo(serialNo);
    header.setTimeStamp(timeStamp);
    header.setSigned(signed);

    String beforeSign = String.format("%s\n%s\n%s\n",
      header.getTimeStamp(),
      header.getNonce(),
      notifyData);
    boolean signResult = wxPayService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
    log.info("签名结果:{} \nheader:{} \ndata:{}", signResult, header, notifyData);
  }

  @Test
  public void testCombinePay() throws WxPayException {
    String outTradeNo = RandomUtils.getRandomStr();
    String notifyUrl = "https://api.qq.com/";
    System.out.println("outTradeNo = " + outTradeNo);
    CombineTransactionsRequest request = new CombineTransactionsRequest();
    request.setCombineAppid("");
    request.setCombineMchid("");
    request.setCombineOutTradeNo(outTradeNo);
    request.setNotifyUrl(notifyUrl);

    CombineTransactionsRequest.CombinePayerInfo payerInfo = new CombineTransactionsRequest.CombinePayerInfo();
    payerInfo.setOpenid("");
    request.setCombinePayerInfo(payerInfo);

    //构建金额信息
    CombineTransactionsRequest.Amount requestAmount = new CombineTransactionsRequest.Amount();
    //设置币种信息
    requestAmount.setCurrency("CNY");
    //设置金额
    requestAmount.setTotalAmount(1);

    CombineTransactionsRequest.SubOrders subOrder1 = new CombineTransactionsRequest.SubOrders();
    //设置 子单商户号  mchId 和 combine_mchId 取值一样
    subOrder1.setMchid("");
    String subOrderNo1 = outTradeNo + "1";
    subOrder1.setAttach(subOrderNo1);
    subOrder1.setOutTradeNo(subOrderNo1);
    subOrder1.setDescription("订单1");
    subOrder1.setAmount(requestAmount);

    CombineTransactionsRequest.SubOrders subOrder2 = new CombineTransactionsRequest.SubOrders();
    //设置 子单商户号  mchId 和 combine_mchId 取值一样
    subOrder2.setMchid("");
    String subOrderNo2 = outTradeNo + "2";
    subOrder2.setAttach(subOrderNo2);
    subOrder2.setOutTradeNo(subOrderNo2);
    subOrder2.setSubMchid("");
    subOrder2.setDescription("订单2");
    subOrder2.setAmount(requestAmount);

    request.setSubOrders(Arrays.asList(subOrder1, subOrder2));
    TransactionsResult result = wxPayService.getEcommerceService().combine(TradeTypeEnum.JSAPI, request);

    System.out.println("result = " + result);
  }

  @Test
  public void testQueryPartnerTransactions() throws WxPayException {
    PartnerTransactionsQueryRequest request = new PartnerTransactionsQueryRequest();
    //服务商商户号
    request.setSpMchid("");
    //二级商户号
    request.setSubMchid("");
    //商户订单号
    request.setOutTradeNo("");
    //微信订单号
    request.setTransactionId("");
    PartnerTransactionsResult result = wxPayService.getEcommerceService().queryPartnerTransactions(request);
    System.out.println("result = " + result);
  }

  @Test
  public void testSubNowBalance() throws WxPayException {
    String subMchid = "";
    wxPayService.getEcommerceService().subNowBalance(subMchid);
  }

  @Test
  public void testSubNowBalanceWithAccountType() throws WxPayException {
    String subMchid = "";
    wxPayService.getEcommerceService().subNowBalance(subMchid, SpAccountTypeEnum.BASIC);
  }

  @Test
  public void testAddReceivers() throws WxPayException {
    ProfitSharingReceiverRequest request = new ProfitSharingReceiverRequest();
    request.setAppid("wx8888888888888888");
    request.setType("MERCHANT_ID");
    request.setAccount("190001001");
    request.setName("张三网络公司");
    request.setRelationType("SUPPLIER");

    ProfitSharingReceiverResult result = wxPayService.getEcommerceService().addReceivers(request);
    System.out.println("result = " + result);
  }

  @Test
  public void testSubDayEndBalance() throws WxPayException {
    String subMchid = "";
    String date = "";
    wxPayService.getEcommerceService().subDayEndBalance(subMchid, date);
  }

  @Test
  public void testCreatedAccountCancelApplication() throws WxPayException {
    AccountCancelApplicationsRequest request = new AccountCancelApplicationsRequest();
    request.setSubMchid("");
    request.setOutApplyNo("");
    request.setApplicationInfo(Lists.newArrayList());

    AccountCancelApplicationsResult result = wxPayService.getEcommerceService().createdAccountCancelApplication(request);
    log.info("请求参数:{} 响应结果:{}", request, result);
  }

  @Test
  public void testGetAccountCancelApplication() throws WxPayException {
    String request = "申请单号";
    AccountCancelApplicationsResult result = wxPayService.getEcommerceService().getAccountCancelApplication(request);
    log.info("请求参数:{} 响应结果:{}", request, result);
  }

  @Test
  public void testUploadMediaAccountCancelApplication() throws WxPayException, IOException {
    AccountCancelApplicationsMediaResult result = wxPayService.getEcommerceService()
      .uploadMediaAccountCancelApplication(new File("src\\test\\resources\\mm.jpeg"));
    log.info("响应结果:{}", result);
  }
}
