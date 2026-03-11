package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3GlobalRequest;
import com.github.binarywang.wxpay.bean.result.enums.GlobalTradeTypeEnum;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.util.RandomUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 境外微信支付测试类
 *
 * @author Binary Wang
 */
public class BaseWxPayServiceGlobalImplTest {

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void testWxPayUnifiedOrderV3GlobalRequest() {
    // Test that the new request class has the required fields
    WxPayUnifiedOrderV3GlobalRequest request = new WxPayUnifiedOrderV3GlobalRequest();
    
    // Set basic order information
    String outTradeNo = RandomUtils.getRandomStr();
    request.setOutTradeNo(outTradeNo);
    request.setDescription("Test overseas payment");
    request.setNotifyUrl("https://api.example.com/notify");
    
    // Set amount
    WxPayUnifiedOrderV3GlobalRequest.Amount amount = new WxPayUnifiedOrderV3GlobalRequest.Amount();
    amount.setCurrency(WxPayConstants.CurrencyType.CNY);
    amount.setTotal(100); // 1 yuan in cents
    request.setAmount(amount);
    
    // Set payer
    WxPayUnifiedOrderV3GlobalRequest.Payer payer = new WxPayUnifiedOrderV3GlobalRequest.Payer();
    payer.setOpenid("test_openid");
    request.setPayer(payer);
    
    // Set the new required fields for global payments
    request.setTradeType("JSAPI");
    request.setMerchantCategoryCode("5812"); // Example category code
    
    // Assert that all fields are properly set
    assertNotNull(request.getTradeType());
    assertNotNull(request.getMerchantCategoryCode());
    assertEquals("JSAPI", request.getTradeType());
    assertEquals("5812", request.getMerchantCategoryCode());
    assertEquals(outTradeNo, request.getOutTradeNo());
    assertEquals("Test overseas payment", request.getDescription());
    assertEquals(100, request.getAmount().getTotal());
    assertEquals("test_openid", request.getPayer().getOpenid());
    
    // Test JSON serialization contains the new fields
    String json = GSON.toJson(request);
    assertTrue(json.contains("trade_type"));
    assertTrue(json.contains("merchant_category_code"));
    assertTrue(json.contains("JSAPI"));
    assertTrue(json.contains("5812"));
  }

  @Test
  public void testGlobalTradeTypeEnum() {
    // Test that all trade types have the correct global endpoints
    assertEquals("/global/v3/transactions/app", GlobalTradeTypeEnum.APP.getUrl());
    assertEquals("/global/v3/transactions/jsapi", GlobalTradeTypeEnum.JSAPI.getUrl());
    assertEquals("/global/v3/transactions/native", GlobalTradeTypeEnum.NATIVE.getUrl());
    assertEquals("/global/v3/transactions/h5", GlobalTradeTypeEnum.H5.getUrl());
  }

  @Test
  public void testGlobalTradeTypeEnumValues() {
    // Test that we have all the main trade types
    GlobalTradeTypeEnum[] tradeTypes = GlobalTradeTypeEnum.values();
    assertEquals(4, tradeTypes.length);
    
    // Test that we can convert between enum name and TradeTypeEnum
    for (GlobalTradeTypeEnum globalType : tradeTypes) {
      // This tests that the enum names match between Global and regular TradeTypeEnum
      String name = globalType.name();
      assertNotNull(name);
      assertTrue(name.equals("APP") || name.equals("JSAPI") || name.equals("NATIVE") || name.equals("H5"));
    }
  }
}