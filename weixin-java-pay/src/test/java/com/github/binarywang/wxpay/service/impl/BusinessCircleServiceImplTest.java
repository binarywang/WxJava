package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.businesscircle.BusinessCircleNotifyData;
import com.github.binarywang.wxpay.bean.businesscircle.PaidResult;
import com.github.binarywang.wxpay.bean.businesscircle.PointsNotifyRequest;
import com.github.binarywang.wxpay.bean.businesscircle.RefundResult;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * <pre>
 *  智慧商圈测试类
 * </pre>
 *
 * @author thinsstar
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class BusinessCircleServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void testNotifyPointsV3() throws WxPayException {
    PointsNotifyRequest request = new PointsNotifyRequest();
    String subMchid = "商圈商户ID";
    String transactionId = "微信订单号";
    String appId = "公众号id";
    String openId = "微信openid";
    request.setSubMchid(subMchid);
    request.setTransactionId(transactionId);
    request.setAppid(appId);
    request.setOpenid(openId);
    request.setEarnPoints(true);
    request.setIncreasedPoints(10);
    request.setPointsUpdateTime("2021-03-03T13:29:35.120+08:00");
    wxPayService.getBusinessCircleService().notifyPoints(request);
  }

  @Test
  public void testDecryptPaidNotifyDataResource() throws WxPayException {
    SignatureHeader header = new SignatureHeader();
    header.setSerial("Wechatpay-Serial");
    header.setTimeStamp("Wechatpay-Timestamp");
    header.setNonce("Wechatpay-Nonce");
    header.setSignature("Wechatpay-Signature");
    String data = "body";
    BusinessCircleNotifyData notifyData = wxPayService.getBusinessCircleService().parseNotifyData(data, header);
    PaidResult result = wxPayService.getBusinessCircleService().decryptPaidNotifyDataResource(notifyData);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testDecryptRefundNotifyDataResource() throws WxPayException {
    SignatureHeader header = new SignatureHeader();
    header.setSerial("Wechatpay-Serial");
    header.setTimeStamp("Wechatpay-Timestamp");
    header.setNonce("Wechatpay-Nonce");
    header.setSignature("Wechatpay-Signature");
    String data = "body";
    BusinessCircleNotifyData notifyData = wxPayService.getBusinessCircleService().parseNotifyData(data, header);
    RefundResult result = wxPayService.getBusinessCircleService().decryptRefundNotifyDataResource(notifyData);

    log.info("result: {}", GSON.toJson(result));
  }
}
