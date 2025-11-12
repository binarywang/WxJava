package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.coupon.*;
import com.github.binarywang.wxpay.bean.notify.*;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.bean.request.WxPayDownloadFundFlowRequest.AccountType;
import com.github.binarywang.wxpay.constant.WxPayConstants.BillType;
import com.github.binarywang.wxpay.constant.WxPayConstants.SignType;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.github.binarywang.wxpay.testbase.XmlWxPayConfig;
import com.github.binarywang.wxpay.util.RequestUtils;
import com.github.binarywang.wxpay.util.XmlConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.RandomUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.github.binarywang.wxpay.constant.WxPayConstants.TarType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * 测试支付相关接口
 * Created by Binary Wang on 2016/7/28.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxPayServiceImplTest {

  @Inject
  private WxPayService payService;

  /**
   * Test method for {@link WxPayService#unifiedOrder(WxPayUnifiedOrderRequest)}.
   *
   * @throws WxPayException the wx pay exception
   */
  @Test
  public void testUnifiedOrder() throws WxPayException {
    WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
      .body("我去")
      .totalFee(1)
      .spbillCreateIp("11.1.11.1")
      .notifyUrl("111111")
      .tradeType(TradeType.JSAPI)
      .openid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid())
      .outTradeNo("111111826")
      .attach("#*#{\"pn\":\"粤B87965\",\"aid\":\"wx123\"}#*#")
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxPayUnifiedOrderResult result = this.payService.unifiedOrder(request);
    log.info(result.toString());
    log.warn(this.payService.getWxApiData().toString());
  }

  /**
   * Test create order.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateOrder() throws Exception {
    //see other tests with method name starting with 'testCreateOrder_'
  }

  /**
   * Test create order jsapi.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateOrder_jsapi() throws Exception {
    WxPayMpOrderResult result = this.payService
      .createOrder(WxPayUnifiedOrderRequest.newBuilder()
        .body("我去")
        .totalFee(1)
        .spbillCreateIp("11.1.11.1")
        .notifyUrl("111111")
        .tradeType(TradeType.JSAPI)
        .openid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid())
        .outTradeNo("1111112")
        .build());
    log.info(result.toString());
    log.warn(this.payService.getWxApiData().toString());
  }

  /**
   * Test create order app.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateOrder_app() throws Exception {
    WxPayAppOrderResult result = this.payService
      .createOrder(WxPayUnifiedOrderRequest.newBuilder()
        .body("我去")
        .totalFee(1)
        .spbillCreateIp("11.1.11.1")
        .notifyUrl("111111")
        .tradeType(TradeType.APP)
        .outTradeNo("1111112")
        .build());
    log.info(result.toString());
    log.warn(this.payService.getWxApiData().toString());
  }

  /**
   * Test create order native.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateOrder_native() throws Exception {
    WxPayNativeOrderResult result = this.payService
      .createOrder(WxPayUnifiedOrderRequest.newBuilder()
        .body("我去")
        .totalFee(1)
        .productId("aaa")
        .spbillCreateIp("11.1.11.1")
        .notifyUrl("111111")
        .tradeType(TradeType.NATIVE)
        .outTradeNo("111111290")
        .build());
    log.info(result.toString());
    log.warn(this.payService.getWxApiData().toString());
  }

  @Test
  public void testCreateOrderSpecific() throws Exception {
    // Won't compile
    // WxPayMpOrderResult result = payService.createOrder(TradeType.Specific.APP, new WxPayUnifiedOrderRequest());
    payService.createOrder(
        TradeType.Specific.JSAPI,
        WxPayUnifiedOrderRequest.newBuilder()
          .body("我去")
          .totalFee(1)
          .productId("aaa")
          .spbillCreateIp("11.1.11.1")
          .notifyUrl("111111")
          .outTradeNo("111111290")
          .build()
      )
      .getAppId();
  }

  /**
   * Test get pay info.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGetPayInfo() throws Exception {
    //please use createOrder instead
  }

  /**
   * Test method for {@link WxPayService#queryOrder(String, String)} .
   *
   * @throws WxPayException the wx pay exception
   */
  @Test
  public void testQueryOrder() throws WxPayException {
    log.info(this.payService.queryOrder("11212121", null).toString());
    log.info(this.payService.queryOrder(null, "11111").toString());
  }

  /**
   * Test method for {@link WxPayService#closeOrder(String)} .
   *
   * @throws WxPayException the wx pay exception
   */
  @Test
  public void testCloseOrder() throws WxPayException {
    log.info(this.payService.closeOrder("11212121").toString());
  }

  /**
   * Billing data object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] billingData() {
    return new Object[][]{
      {"20170831", BillType.ALL, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.RECHARGE_REFUND, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.REFUND, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.SUCCESS, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.ALL, null, "deviceInfo"},
      {"20170831", BillType.RECHARGE_REFUND, null, "deviceInfo"},
      {"20170831", BillType.REFUND, null, "deviceInfo"},
      {"20170831", BillType.SUCCESS, null, "deviceInfo"}
    };
  }

  /**
   * Test download bill.
   *
   * @param billDate   the bill date
   * @param billType   the bill type
   * @param tarType    the tar type
   * @param deviceInfo the device info
   * @throws Exception the exception
   */
  @Test(dataProvider = "billingData")
  public void testDownloadBill(String billDate, String billType,
                               String tarType, String deviceInfo) throws Exception {
    WxPayBillResult billResult = this.payService.downloadBill(billDate, billType, tarType, deviceInfo);
    assertThat(billResult).isNotNull();
    log.info(billResult.toString());
  }

  /**
   * Test download bill with no params.
   *
   * @throws Exception the exception
   */
  @Test(expectedExceptions = WxPayException.class)
  public void testDownloadBill_withNoParams() throws Exception {
    //必填字段为空时，抛出异常
    this.payService.downloadBill("", "", "", null);
  }

  /**
   * Fund flow data object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] fundFlowData() {
    return new Object[][]{
      {"20180819", AccountType.BASIC, TarType.GZIP},
      {"20180819", AccountType.OPERATION, TarType.GZIP},
      {"20180819", AccountType.FEES, TarType.GZIP},
      {"20180819", AccountType.BASIC, null},
      {"20180819", AccountType.OPERATION, null},
      {"20180819", AccountType.FEES, null}
    };
  }

  /**
   * Test download fund flow.
   *
   * @param billDate    the bill date
   * @param accountType the account type
   * @param tarType     the tar type
   * @throws Exception the exception
   */
  @Test(dataProvider = "fundFlowData")
  public void testDownloadFundFlow(String billDate, String accountType, String tarType) throws Exception {
    WxPayFundFlowResult fundFlowResult = this.payService.downloadFundFlow(billDate, accountType, tarType);
    assertThat(fundFlowResult).isNotNull();
    log.info(fundFlowResult.toString());
  }

  /**
   * Test download fund flow with no params.
   *
   * @throws Exception the exception
   */
  @Test(expectedExceptions = WxPayException.class)
  public void testDownloadFundFlow_withNoParams() throws Exception {
    //必填字段为空时，抛出异常
    this.payService.downloadFundFlow("", "", null);
  }

  /**
   * Test report.
   *
   * @throws Exception the exception
   */
  @Test
  public void testReport() throws Exception {
    WxPayReportRequest request = new WxPayReportRequest();
    request.setInterfaceUrl("hahahah");
    request.setSignType(SignType.HMAC_SHA256);//貌似接口未校验此字段
    request.setExecuteTime(1000);
    request.setReturnCode("aaa");
    request.setResultCode("aaa");
    request.setUserIp("8.8.8");
    this.payService.report(request);
  }

  /**
   * Test method for {@link WxPayService#refund(WxPayRefundRequest)} .
   *
   * @throws Exception the exception
   */
  @Test
  public void testRefund() throws Exception {
    WxPayRefundResult result = this.payService.refund(
      WxPayRefundRequest.newBuilder()
        .outRefundNo("aaa")
        .outTradeNo("1111")
        .totalFee(1222)
        .refundFee(111)
        .build());
    log.info(result.toString());
  }

  @Test
  public void testRefundV2() throws WxPayException {
    WxPayRefundResult result = this.payService.refundV2(
      WxPayRefundRequest.newBuilder()
        .outRefundNo("aaa")
        .outTradeNo("1111")
        .totalFee(1222)
        .refundFee(111)
        .build());
    log.info(result.toString());
  }

  /**
   * Test method for {@link WxPayService#refundQuery(String, String, String, String)} .
   *
   * @throws Exception the exception
   */
  @Test
  public void testRefundQuery() throws Exception {
    WxPayRefundQueryResult result;

    result = this.payService.refundQuery("1", "", "", "");
    log.info(result.toString());

    result = this.payService.refundQuery("", "2", "", "");
    log.info(result.toString());

    result = this.payService.refundQuery("", "", "3", "");
    log.info(result.toString());

    result = this.payService.refundQuery("", "", "", "4");
    log.info(result.toString());

    //测试四个参数都填的情况，应该报异常的
    result = this.payService.refundQuery("1", "2", "3", "4");
    log.info(result.toString());
  }

  @Test
  public void testRefundQueryV2() throws WxPayException {
    this.payService.refundQueryV2(WxPayRefundQueryRequest.newBuilder().outRefundNo("1").build());
  }

  /**
   * Test parse refund notify result.
   *
   * @throws Exception the exception
   */
  @Test
  public void testParseRefundNotifyResult() throws Exception {
    // 请参考com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResultTest里的单元测试
  }

  /**
   * Test create scan pay qrcode mode 1.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateScanPayQrcodeMode1() throws Exception {
    String productId = "abc";
    byte[] bytes = this.payService.createScanPayQrcodeMode1(productId, null, null);
    Path qrcodeFilePath = Files.createTempFile("qrcode_", ".jpg");
    Files.write(qrcodeFilePath, bytes);
    String qrcodeContent = QrcodeUtils.decodeQrcode(qrcodeFilePath.toFile());
    log.info(qrcodeContent);

    assertTrue(qrcodeContent.startsWith("weixin://wxpay/bizpayurl?"));
    assertTrue(qrcodeContent.contains("product_id=" + productId));
  }

  /**
   * Test create scan pay qrcode mode 2.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateScanPayQrcodeMode2() throws Exception {
    String qrcodeContent = "abc";
    byte[] bytes = this.payService.createScanPayQrcodeMode2(qrcodeContent, null, null);
    Path qrcodeFilePath = Files.createTempFile("qrcode_", ".jpg");
    Files.write(qrcodeFilePath, bytes);
    assertEquals(QrcodeUtils.decodeQrcode(qrcodeFilePath.toFile()), qrcodeContent);
  }

  /**
   * Test micropay.
   *
   * @throws Exception the exception
   */
  @Test
  public void testMicropay() throws Exception {
    WxPayMicropayResult result = this.payService.micropay(
      WxPayMicropayRequest
        .newBuilder()
        .body("body")
        .outTradeNo("aaaaa")
        .totalFee(123)
        .spbillCreateIp("127.0.0.1")
        .authCode("aaa")
        .build());
    log.info(result.toString());
  }

  /**
   * Test get config.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGetConfig() throws Exception {
    // no need to test
  }

  /**
   * Test set config.
   *
   * @throws Exception the exception
   */
  @Test
  public void testSetConfig() throws Exception {
    // no need to test
  }

  /**
   * Test reverse order.
   *
   * @throws Exception the exception
   */
  @Test
  public void testReverseOrder() throws Exception {
    WxPayOrderReverseResult result = this.payService.reverseOrder(
      WxPayOrderReverseRequest.newBuilder()
        .outTradeNo("1111")
        .build());
    assertNotNull(result);
    log.info(result.toString());
  }

  /**
   * Test shorturl.
   *
   * @throws Exception the exception
   */
  @Test
  public void testShorturl() throws Exception {
    String longUrl = "weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX";

    String result = this.payService.shorturl(new WxPayShorturlRequest(longUrl));
    assertNotNull(result);
    log.info(result);

    result = this.payService.shorturl(longUrl);
    assertNotNull(result);
    log.info(result);
  }

  /**
   * Test authcode 2 openid.
   *
   * @throws Exception the exception
   */
  @Test
  public void testAuthcode2Openid() throws Exception {
    String authCode = "11111";

    String result = this.payService.authcode2Openid(new WxPayAuthcode2OpenidRequest(authCode));
    assertNotNull(result);
    log.info(result);

    result = this.payService.authcode2Openid(authCode);
    assertNotNull(result);
    log.info(result);
  }

  /**
   * Test get sandbox sign key.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGetSandboxSignKey() throws Exception {
    final String signKey = this.payService.getSandboxSignKey();
    assertNotNull(signKey);
    log.info(signKey);
  }

  /**
   * Test send coupon.
   *
   * @throws Exception the exception
   */
  @Test
  public void testSendCoupon() throws Exception {
    WxPayCouponSendResult result = this.payService.sendCoupon(WxPayCouponSendRequest.newBuilder()
      .couponStockId("123")
      .openid("122")
      .partnerTradeNo("1212")
      .openidCount(1)
      .build());
    log.info(result.toString());
  }

  /**
   * Test query coupon stock.
   *
   * @throws Exception the exception
   */
  @Test
  public void testQueryCouponStock() throws Exception {
    WxPayCouponStockQueryResult result = this.payService.queryCouponStock(
      WxPayCouponStockQueryRequest.newBuilder()
        .couponStockId("123")
        .build());
    log.info(result.toString());
  }

  /**
   * Test query coupon info.
   *
   * @throws Exception the exception
   */
  @Test
  public void testQueryCouponInfo() throws Exception {
    WxPayCouponInfoQueryResult result = this.payService.queryCouponInfo(
      WxPayCouponInfoQueryRequest.newBuilder()
        .openid("ojOQA0y9o-Eb6Aep7uVTdbkJqrP4")
        .couponId("11")
        .stockId("1121")
        .build());
    log.info(result.toString());
  }

  /**
   * 只支持拉取90天内的评论数据
   *
   * @throws Exception the exception
   */
  @Test
  public void testQueryComment() throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    Date endDate = calendar.getTime();
    calendar.add(Calendar.DAY_OF_MONTH, -88);
    Date beginDate = calendar.getTime();
    String result = this.payService.queryComment(beginDate, endDate, 0, 1);
    log.info(result);
  }

  /**
   * Test parse order notify result.
   *
   * @throws Exception the exception
   * @see WxPayOrderNotifyResultTest#testFromXML() WxPayOrderNotifyResultTest#testFromXML()
   */
  @Test
  public void testParseOrderNotifyResult() throws Exception {
    // 请参考com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResultTest 里的单元测试

    String xmlString = "<xml>\n" +
      "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "  <attach><![CDATA[支付测试]]></attach>\n" +
      "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
      "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
      "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
      "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
      "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
      "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
      "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
      "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
      "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
      "  <total_fee>1</total_fee>\n" +
      "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
      "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
      "   <coupon_count>2</coupon_count>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <coupon_id_0>10000</coupon_id_0>\n" +
      "   <coupon_fee_0>100</coupon_fee_0>\n" +
      "   <coupon_type_1><![CDATA[NO_CASH]]></coupon_type_1>\n" +
      "   <coupon_id_1>10001</coupon_id_1>\n" +
      "   <coupon_fee_1>200</coupon_fee_1>\n" +
      "</xml>";

    XmlConfig.fastMode = true;
    WxPayOrderNotifyResult result;
    try {
      result = BaseWxPayResult.fromXML(xmlString, WxPayOrderNotifyResult.class);
      System.out.println(result);
    } finally {
      XmlConfig.fastMode = false;
    }

    result = this.payService.parseOrderNotifyResult(xmlString);
    System.out.println(result);

  }

  /**
   * Test get wx api data.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGetWxApiData() throws Exception {
    //see test in testUnifiedOrder()
  }

  @Test
  public void testDownloadRawBill() {
  }

  @Test
  public void testTestDownloadRawBill() {
  }

  @Test
  public void testGetWxPayFaceAuthInfo() throws WxPayException {
    XmlConfig.fastMode = true;
    final WxPayFaceAuthInfoRequest request = new WxPayFaceAuthInfoRequest()
      .setStoreId("1").setRawdata("111").setNow("111").setVersion("111").setStoreName("2222").setDeviceId("111");
    request.setSignType("MD5");
    this.payService.getWxPayFaceAuthInfo(request);
  }

  @Test
  public void testFacepay() throws WxPayException {
    final WxPayFacepayResult result = this.payService.facepay(WxPayFacepayRequest.newBuilder().build());
  }

  @Test
  public void testGetEntPayService() {
    // no need to test
  }

  @Test
  public void testGetProfitSharingService() {
    // no need to test
  }

  @Test
  public void testGetRedpackService() {
    // no need to test
  }

  @Test
  public void testSetEntPayService() {
    // no need to test
  }

  @Test
  public void testGetPayBaseUrl() {
    // no need to test
  }

  @Test
  public void testParseScanPayNotifyResult() {
  }

  @Test
  public void testSendMiniProgramRedpack() {
  }

  @Test
  public void testSendRedpack() {
  }

  @Test
  public void testQueryRedpack() {
  }

  @Test
  public void testTestQueryRedpack() {
  }

  @Test
  public void testGetPayScoreService() {
    // no need to test
  }

  @Test
  public void testQueryExchangeRate() throws WxPayException {
    final WxPayQueryExchangeRateResult result = this.payService.queryExchangeRate("USD", "20200425");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void testUnifiedOrderV3() throws WxPayException {
    String outTradeNo = RandomUtils.getRandomStr();
    String notifyUrl = "https://api.qq.com/";
    System.out.println("outTradeNo = " + outTradeNo);
    WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
    request.setOutTradeNo(outTradeNo);
    request.setNotifyUrl(notifyUrl);
    request.setDescription("test");

    WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
    payer.setOpenid("openid");
    request.setPayer(payer);

    //构建金额信息
    WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
    //设置币种信息
    amount.setCurrency(WxPayConstants.CurrencyType.CNY);
    //设置金额
    amount.setTotal(BaseWxPayRequest.yuan2Fen(BigDecimal.ONE));
    request.setAmount(amount);

    WxPayUnifiedOrderV3Result.JsapiResult result = this.payService.createOrderV3(TradeTypeEnum.JSAPI, request);

    System.out.println(GSON.toJson(result));
  }

  @Test
  public void testQueryOrderV3() throws WxPayException {
    WxPayOrderQueryV3Request request = new WxPayOrderQueryV3Request();
    request.setOutTradeNo("n1ZvYqjAg3D3LUBa");
    WxPayOrderQueryV3Result result = this.payService.queryOrderV3(request);
    System.out.println(GSON.toJson(result));
  }

  @Test
  public void testCloseOrderV3() throws WxPayException {
    WxPayOrderCloseV3Request request = new WxPayOrderCloseV3Request();
    request.setOutTradeNo("n1ZvYqjAg3D3LUBa");
    this.payService.closeOrderV3(request);
  }

  @Test
  public void testRefundV3() throws WxPayException {
    String outRefundNo = RandomUtils.getRandomStr();
    String notifyUrl = "https://api.qq.com/";
    System.out.println("outRefundNo = " + outRefundNo);
    WxPayRefundV3Request request = new WxPayRefundV3Request();
    request.setOutTradeNo("n1ZvYqjAg3D3LUBa");
    request.setOutRefundNo(outRefundNo);
    request.setNotifyUrl(notifyUrl);
    request.setAmount(new WxPayRefundV3Request.Amount().setRefund(100).setTotal(100).setCurrency("CNY"));
    WxPayRefundV3Result result = this.payService.refundV3(request);
    System.out.println(GSON.toJson(result));
  }

  /**
   * 测试V3支付成功回调
   * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_5.shtml
   *
   * @throws Exception the exception
   */
  @Test
  public String testParseOrderNotifyV3Result(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String timestamp = request.getHeader("Wechatpay-Timestamp");
    Optional.ofNullable(timestamp).orElseThrow(() -> new RuntimeException("时间戳不能为空"));

    String nonce = request.getHeader("Wechatpay-Nonce");
    Optional.ofNullable(nonce).orElseThrow(() -> new RuntimeException("nonce不能为空"));

    String serialNo = request.getHeader("Wechatpay-Serial");
    Optional.ofNullable(serialNo).orElseThrow(() -> new RuntimeException("serialNo不能为空"));

    String signature = request.getHeader("Wechatpay-Signature");
    Optional.ofNullable(signature).orElseThrow(() -> new RuntimeException("signature不能为空"));

    log.info("请求头参数为：timestamp:{} nonce:{} serialNo:{} signature:{}", timestamp, nonce, serialNo, signature);

    // V2版本请参考com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResultTest里的单元测试
    final WxPayNotifyV3Result wxPayOrderNotifyV3Result = this.payService.parseOrderNotifyV3Result(RequestUtils.readData(request),
      new SignatureHeader(timestamp, nonce, signature, serialNo));
    log.info(GSON.toJson(wxPayOrderNotifyV3Result));

    return WxPayNotifyV3Response.success("成功");
  }

  /**
   * 测试V3退款成功回调
   * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_11.shtml
   *
   * @throws Exception the exception
   */
  @Test
  public String testParseRefundNotifyV3Result(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String timestamp = request.getHeader("Wechatpay-Timestamp");
    Optional.ofNullable(timestamp).orElseThrow(() -> new RuntimeException("时间戳不能为空"));

    String nonce = request.getHeader("Wechatpay-Nonce");
    Optional.ofNullable(nonce).orElseThrow(() -> new RuntimeException("nonce不能为空"));

    String serialNo = request.getHeader("Wechatpay-Serial");
    Optional.ofNullable(serialNo).orElseThrow(() -> new RuntimeException("serialNo不能为空"));

    String signature = request.getHeader("Wechatpay-Signature");
    Optional.ofNullable(signature).orElseThrow(() -> new RuntimeException("signature不能为空"));

    log.info("支付请求头参数为：timestamp:{} nonce:{} serialNo:{} signature:{}", timestamp, nonce, serialNo, signature);

    final WxPayRefundNotifyV3Result wxPayRefundNotifyV3Result = this.payService.parseRefundNotifyV3Result(RequestUtils.readData(request),
      new SignatureHeader(timestamp, nonce, signature, serialNo));
    log.info(GSON.toJson(wxPayRefundNotifyV3Result));

    // 退款金额
    final WxPayRefundNotifyV3Result.DecryptNotifyResult result = wxPayRefundNotifyV3Result.getResult();
    final BigDecimal total = BaseWxPayRequest.fen2Yuan(BigDecimal.valueOf(result.getAmount().getTotal()));
    final BigDecimal payerRefund = BaseWxPayRequest.fen2Yuan(BigDecimal.valueOf(result.getAmount().getPayerRefund()));

    // 处理业务逻辑 ...

    return WxPayNotifyV3Response.success("成功");
  }

  /**
   * 商家转账批次回调通知
   * https://pay.weixin.qq.com/docs/merchant/apis/batch-transfer-to-balance/transfer-batch-callback-notice.html
   *
   * @return
   * @throws Exception
   */
  @Test
  public String parseTransferBatchesNotifyV3Result() throws Exception {

    String body = "{\n" +
      "    \"id\": \"1c8192d8-aba1-5898-a79c-7d3abb72eabe\",\n" +
      "    \"create_time\": \"2023-08-16T16:43:27+08:00\",\n" +
      "    \"resource_type\": \"encrypt-resource\",\n" +
      "    \"event_type\": \"MCHTRANSFER.BATCH.FINISHED\",\n" +
      "    \"summary\": \"商家转账批次完成通知\",\n" +
      "    \"resource\": {\n" +
      "        \"original_type\": \"mch_payment\",\n" +
      "        \"algorithm\": \"AEAD_AES_256_GCM\",\n" +
      "        \"ciphertext\": \"zTBf6DDPzZSoIBkoLFkC+ho97QrqnT6UU/ADM0tJP07ITaFPek4vofQjmclLUof78NqrPcJs5OIBl+gnKKJ4xCxcDmDnZZHvev5o1pk4gwtJIFIDxbq3piDr4Wq6cZpvGPPQTYC8YoVRTdVeeN+EcuklRrmaFzv8wCTSdI9wFJ9bsxtLedhq4gpkKqN5fbSguQg9JFsX3OJeT7KPfRd6SD1gu4Lpw5gwxthfOHcYsjM/eY5gaew8zzpN6mMUEJ1HqkNuQgOguHBxFnqFPiMz+Iadw7X38Yz+IgfUkOhN1iuvMhGYKbwKJ7rTiBVvGGpF6Wse1zFKgSiTLH2RnUAMkkHmxqk+JhbQKZpSWr6O8BfhHO1OKg7hpcHZtOJKNMjIF62WYDVf36w1h8h5fg==\",\n" +
      "        \"associated_data\": \"mch_payment\",\n" +
      "        \"nonce\": \"DdF3UJVNQaKT\"\n" +
      "    }\n" +
      "}";
    WxPayTransferBatchesNotifyV3Result transferBatchesNotifyV3Body = GSON.fromJson(body, WxPayTransferBatchesNotifyV3Result.class);
    log.info(GSON.toJson(transferBatchesNotifyV3Body));

    // 处理业务逻辑 ...

    return WxPayNotifyV3Response.success("成功");
  }

  // 测试
  public static void main(String[] args){
    String body = "{\n" +
      "    \"id\": \"1c8192d8-aba1-5898-a79c-7d3abb72eabe\",\n" +
      "    \"create_time\": \"2023-08-16T16:43:27+08:00\",\n" +
      "    \"resource_type\": \"encrypt-resource\",\n" +
      "    \"event_type\": \"MCHTRANSFER.BATCH.FINISHED\",\n" +
      "    \"summary\": \"商家转账批次完成通知\",\n" +
      "    \"resource\": {\n" +
      "        \"original_type\": \"mch_payment\",\n" +
      "        \"algorithm\": \"AEAD_AES_256_GCM\",\n" +
      "        \"ciphertext\": \"zTBf6DDPzZSoIBkoLFkC+ho97QrqnT6UU/ADM0tJP07ITaFPek4vofQjmclLUof78NqrPcJs5OIBl+gnKKJ4xCxcDmDnZZHvev5o1pk4gwtJIFIDxbq3piDr4Wq6cZpvGPPQTYC8YoVRTdVeeN+EcuklRrmaFzv8wCTSdI9wFJ9bsxtLedhq4gpkKqN5fbSguQg9JFsX3OJeT7KPfRd6SD1gu4Lpw5gwxthfOHcYsjM/eY5gaew8zzpN6mMUEJ1HqkNuQgOguHBxFnqFPiMz+Iadw7X38Yz+IgfUkOhN1iuvMhGYKbwKJ7rTiBVvGGpF6Wse1zFKgSiTLH2RnUAMkkHmxqk+JhbQKZpSWr6O8BfhHO1OKg7hpcHZtOJKNMjIF62WYDVf36w1h8h5fg==\",\n" +
      "        \"associated_data\": \"mch_payment\",\n" +
      "        \"nonce\": \"DdF3UJVNQaKT\"\n" +
      "    }\n" +
      "}";
    OriginNotifyResponse transferBatchesNotifyV3Body = GSON.fromJson(body, OriginNotifyResponse.class);
    log.info(GSON.toJson(transferBatchesNotifyV3Body));

    String decryptNotifyResult = "{\n" +
      "    \"out_batch_no\": \"bfatestnotify000033\",\n" +
      "    \"batch_id\": \"131000007026709999520922023081519403795655\",\n" +
      "    \"batch_status\": \"FINISHED\",\n" +
      "    \"total_num\": 2,\n" +
      "    \"total_amount\": 200,\n" +
      "    \"success_amount\": 100,\n" +
      "    \"success_num\": 1,\n" +
      "    \"fail_amount\": 100,\n" +
      "    \"fail_num\": 1,\n" +
      "    \"mchid\": \"2483775951\",\n" +
      "    \"update_time\": \"2023-08-15T20:33:22+08:00\"\n" +
      "}";
    WxPayTransferBatchesNotifyV3Result.DecryptNotifyResult notifyResult = GSON.fromJson(decryptNotifyResult, WxPayTransferBatchesNotifyV3Result.DecryptNotifyResult.class);
    log.info(GSON.toJson(notifyResult));

  }

  @Test
  public void testWxPayNotifyV3Response() {
    System.out.println(WxPayNotifyV3Response.success("success"));
    System.out.println(WxPayNotifyV3Response.fail("fail"));
  }

  @Test
  public void testRefundQueryV3() throws WxPayException {
    WxPayRefundQueryV3Request request = new WxPayRefundQueryV3Request();
//    request.setOutRefundNo("n1ZvYqjAg3D7LUBa");
    request.setOutRefundNo("123456789011");
    WxPayRefundQueryV3Result result = this.payService.refundQueryV3(request);
    System.out.println(GSON.toJson(result));
  }

  /**
   * 测试包含正向代理的测试
   *
   * @throws WxPayException
   */
  @Test
  public void testQueryOrderV3WithProxy() {
    try {
      WxPayOrderQueryV3Request request = new WxPayOrderQueryV3Request();
      request.setOutTradeNo("n1ZvYqjAg3D3LUBa");
      WxPayConfig config = this.payService.getConfig();
      config.setApiHostUrl("http://api.mch.weixin.qq.com");
      config.setHttpProxyHost("12.11.1.113");
      config.setHttpProxyPort(8015);
      WxPayOrderQueryV3Result result = this.payService.queryOrderV3(request);
      System.out.println(GSON.toJson(result));
    } catch (WxPayException e) {
    }

  }

  @Test
  public void testCreatePartnerOrderV3() throws WxPayException {
    WxPayConfig wxPayConfig = new WxPayConfig();
    //服务商的参数
    wxPayConfig.setMchId("xxx");
    wxPayConfig.setApiV3Key("xxx");
    wxPayConfig.setPrivateKeyPath("xxx");
    wxPayConfig.setPrivateCertPath("xxx");
    wxPayConfig.setKeyPath("xxx");
    wxPayConfig.setAppId("xxx");
    wxPayConfig.setKeyPath("xxx");
    //如果有子商户的appId则配置
//    wxPayConfig.setSubAppId("xxx");
    //创建支付服务
    WxPayService wxPayService = new WxPayServiceImpl();
    wxPayService.setConfig(wxPayConfig);
    //子商户的参数
    wxPayConfig.setSubMchId("xxx");

    //构建请求
    WxPayPartnerUnifiedOrderV3Request request = new WxPayPartnerUnifiedOrderV3Request();
    request.setAmount(new WxPayPartnerUnifiedOrderV3Request.Amount().setTotal(1));
    request.setPayer(new WxPayPartnerUnifiedOrderV3Request.Payer().setSpOpenid("xxx"));
    //如果有子商户的appId则配置
//    request.setPayer(new WxPayPartnerUnifiedOrderV3Request.Payer().setSubOpenid("xxx"));
    request.setOutTradeNo(UUID.randomUUID().toString());

    WxPayUnifiedOrderV3Result.JsapiResult result = payService.createPartnerOrderV3(TradeTypeEnum.JSAPI, request);
    System.out.println(result);
  }

  @Test
  public void test_certSerialNoExtractedFromPrivateCertContentOrPrivateCertString() throws Exception {
    WxPayConfig wxPayConfig = new WxPayConfig();
    //服务商的参数
    wxPayConfig.setMchId("xxx");
    wxPayConfig.setAppId("xxx");
    wxPayConfig.setApiV3Key("xxx");
    wxPayConfig.setPrivateKeyContent("xxx".getBytes(StandardCharsets.UTF_8));
    wxPayConfig.setPrivateCertContent("xxx".getBytes(StandardCharsets.UTF_8)
    );
    wxPayConfig.setPublicKeyId("xxx");
    wxPayConfig.setPublicKeyContent("xxx".getBytes(StandardCharsets.UTF_8));
    //创建支付服务
    WxPayService wxPayService = new WxPayServiceImpl();
    wxPayService.setConfig(wxPayConfig);

    String outTradeNo = RandomUtils.getRandomStr();
    String notifyUrl = "https://api.qq.com/";
    System.out.println("outTradeNo = " + outTradeNo);
    WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
    request.setOutTradeNo(outTradeNo);
    request.setNotifyUrl(notifyUrl);
    request.setDescription("test");

    WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
    payer.setOpenid("xxx");
    request.setPayer(payer);

    //构建金额信息
    WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
    //设置币种信息
    amount.setCurrency(WxPayConstants.CurrencyType.CNY);
    //设置金额
    amount.setTotal(BaseWxPayRequest.yuan2Fen(BigDecimal.ONE));
    request.setAmount(amount);

    wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);
  }

}
