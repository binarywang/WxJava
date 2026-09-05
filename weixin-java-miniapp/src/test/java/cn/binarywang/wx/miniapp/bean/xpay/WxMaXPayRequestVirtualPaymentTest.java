package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.api.impl.WxMaXPayServiceImpl;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * 验证个人主体虚拟支付调起 wx.requestVirtualPayment 所需参数。
 */
public class WxMaXPayRequestVirtualPaymentTest {

  @Test
  public void testCreatePayData() {
    WxMaXPayRequestVirtualPaymentRequest request = WxMaXPayRequestVirtualPaymentRequest.builder()
      .offerId("1450019686")
      .buyQuantity(1)
      .env(0)
      .currencyType(WxMaConstants.XPayCurrencyType.CNY)
      .productId("product_001")
      .goodsPrice(100)
      .outTradeNo("order12345")
      .attach("attach中文")
      .build();
    WxMaXPaySigParams sigParams = WxMaXPaySigParams.builder()
      .appKey("app_key_123")
      .sessionKey("session_key_123")
      .build();

    WxMaXPayRequestVirtualPaymentData payData = request.createPayData(sigParams);

    assertEquals(payData.getMode(), WxMaConstants.XPayPaymentMode.GOODS);
    assertEquals(payData.getSignData(),
      "{\"offerId\":\"1450019686\",\"buyQuantity\":1,\"env\":0,\"currencyType\":\"CNY\","
        + "\"productId\":\"product_001\",\"goodsPrice\":100,\"outTradeNo\":\"order12345\","
        + "\"attach\":\"attach中文\"}");
    assertEquals(payData.getPaySig(), "52b0abda3c933b0273d328b5c5102ee9ab9249309474ddcdf5e9ce0f80b23532");
    assertEquals(payData.getSignature(), "602f76d9cadf36c232f7f6c1faa5fe295b0f955d188049997d0df699c0619c86");

    JsonObject jsonObject = WxMaGsonBuilder.create().fromJson(payData.toJson(), JsonObject.class);
    assertEquals(jsonObject.get("mode").getAsString(), WxMaConstants.XPayPaymentMode.GOODS);
    assertEquals(jsonObject.get("paySig").getAsString(), payData.getPaySig());
    assertEquals(jsonObject.get("signature").getAsString(), payData.getSignature());
    assertEquals(jsonObject.get("signData").getAsString(), payData.getSignData());
  }

  @Test
  public void testCreatePayDataByService() {
    WxMaXPayRequestVirtualPaymentRequest request = WxMaXPayRequestVirtualPaymentRequest.builder()
      .offerId("1450019686")
      .buyQuantity(1)
      .env(0)
      .currencyType(WxMaConstants.XPayCurrencyType.CNY)
      .productId("product_001")
      .goodsPrice(100)
      .outTradeNo("order12345")
      .attach("attach中文")
      .build();
    WxMaXPaySigParams sigParams = WxMaXPaySigParams.builder()
      .appKey("app_key_123")
      .sessionKey("session_key_123")
      .build();

    WxMaXPayRequestVirtualPaymentData payData = new WxMaXPayServiceImpl(null)
      .createRequestVirtualPaymentData(request, sigParams);

    assertEquals(payData.getMode(), WxMaConstants.XPayPaymentMode.GOODS);
    assertEquals(payData.getPaySig(), "52b0abda3c933b0273d328b5c5102ee9ab9249309474ddcdf5e9ce0f80b23532");
    assertEquals(payData.getSignature(), "602f76d9cadf36c232f7f6c1faa5fe295b0f955d188049997d0df699c0619c86");
  }
}
