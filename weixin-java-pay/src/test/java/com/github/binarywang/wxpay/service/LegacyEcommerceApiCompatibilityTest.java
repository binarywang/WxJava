package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.TransactionsResult;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Compile-time compatibility checks for the pre-#4014 e-commerce API.
 */
public class LegacyEcommerceApiCompatibilityTest {

  @Test
  public void shouldKeepLegacyTransactionResultAndTradeTypeAvailable() {
    TransactionsResult result = new TransactionsResult();

    Assert.assertNotNull(result);
    Assert.assertEquals(TradeTypeEnum.JSAPI.name(), "JSAPI");
  }
}
