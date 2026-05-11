package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.marketing.transfer.BillReceiptResult;
import com.github.binarywang.wxpay.bean.marketing.transfer.ReceiptBillRequest;
import com.github.binarywang.wxpay.bean.merchanttransfer.ElectronicBillApplyRequest;
import com.github.binarywang.wxpay.bean.merchanttransfer.ElectronicBillResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Test
public class TransferReceiptApiCompatibilityTest {

  private static final String BASE_URL = "https://api.mch.weixin.qq.com";

  /**
   * 验证直连商户电子回单接口已切换到新版fund-app路径。
   */
  public void shouldUseNewMerchantTransferElecsignApiPath() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    MerchantTransferServiceImpl merchantTransferService = new MerchantTransferServiceImpl(wxPayService);

    merchantTransferService.applyElectronicBill(new ElectronicBillApplyRequest().setOutBatchNo("plfk2020042013"));
    Assert.assertEquals(handler.lastPostUrl, BASE_URL + "/v3/fund-app/mch-transfer/elecsign/out-bill-no");
    Assert.assertTrue(handler.lastPostBody.contains("\"out_bill_no\""));
    Assert.assertFalse(handler.lastPostBody.contains("\"out_batch_no\""));

    merchantTransferService.queryElectronicBill("plfk2020042013");
    Assert.assertEquals(handler.lastGetUrl, BASE_URL + "/v3/fund-app/mch-transfer/elecsign/out-bill-no/plfk2020042013");
  }

  /**
   * 验证服务商电子回单接口已切换到新版fund-app路径。
   */
  public void shouldUseNewPartnerTransferElecsignApiPath() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    PartnerTransferServiceImpl partnerTransferService = new PartnerTransferServiceImpl(wxPayService);

    ReceiptBillRequest request = new ReceiptBillRequest();
    request.setOutBatchNo("plfk2020042013");
    partnerTransferService.receiptBill(request);
    Assert.assertEquals(handler.lastPostUrl, BASE_URL + "/v3/fund-app/mch-transfer/elecsign/out-bill-no");
    Assert.assertTrue(handler.lastPostBody.contains("\"out_bill_no\""));
    Assert.assertFalse(handler.lastPostBody.contains("\"out_batch_no\""));

    partnerTransferService.queryBillReceipt("plfk2020042013");
    Assert.assertEquals(handler.lastGetUrl, BASE_URL + "/v3/fund-app/mch-transfer/elecsign/out-bill-no/plfk2020042013");
  }

  /**
   * 验证新版字段名能够正确反序列化到现有结果对象。
   */
  public void shouldDeserializeNewResponseFieldNames() {
    Gson gson = new Gson();
    BillReceiptResult billReceiptResult =
      gson.fromJson("{\"out_bill_no\":\"plfk2020042013\",\"state\":\"FINISHED\"}", BillReceiptResult.class);
    Assert.assertEquals(billReceiptResult.getOutBatchNo(), "plfk2020042013");
    Assert.assertEquals(billReceiptResult.getSignatureStatus(), "FINISHED");

    ElectronicBillResult electronicBillResult =
      gson.fromJson("{\"out_bill_no\":\"plfk2020042013\",\"state\":\"FINISHED\"}", ElectronicBillResult.class);
    Assert.assertEquals(electronicBillResult.getOutBatchNo(), "plfk2020042013");
    Assert.assertEquals(electronicBillResult.getSignatureStatus(), "FINISHED");
  }

  /**
   * 通过动态代理拦截WxPayService请求并记录URL/请求体，便于断言接口路径和参数。
   */
  private static class RequestCaptureHandler implements InvocationHandler {
    private String lastPostUrl;
    private String lastPostBody;
    private String lastGetUrl;

    private WxPayService createWxPayService() {
      return (WxPayService) Proxy.newProxyInstance(
        WxPayService.class.getClassLoader(),
        new Class<?>[]{WxPayService.class},
        this
      );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
      if ("getPayBaseUrl".equals(method.getName())) {
        return BASE_URL;
      }
      if ("postV3".equals(method.getName())) {
        this.lastPostUrl = (String) args[0];
        this.lastPostBody = (String) args[1];
        return "{}";
      }
      if ("getV3".equals(method.getName())) {
        this.lastGetUrl = (String) args[0];
        return "{}";
      }
      if ("toString".equals(method.getName())) {
        return "MockWxPayService";
      }
      Class<?> returnType = method.getReturnType();
      if (boolean.class.equals(returnType)) {
        return false;
      }
      if (int.class.equals(returnType)) {
        return 0;
      }
      if (long.class.equals(returnType)) {
        return 0L;
      }
      if (double.class.equals(returnType)) {
        return 0D;
      }
      if (float.class.equals(returnType)) {
        return 0F;
      }
      if (short.class.equals(returnType)) {
        return (short) 0;
      }
      if (byte.class.equals(returnType)) {
        return (byte) 0;
      }
      if (char.class.equals(returnType)) {
        return (char) 0;
      }
      return null;
    }
  }
}
