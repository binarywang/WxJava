package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.TransactionsResult;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Verifies that source code written against the pre-#4014 e-commerce API can
 * still compile while the unified API remains the implementation path.
 */
public class LegacyEcommerceApiCompatibilityTest {

  @Test
  public void shouldKeepLegacyTransactionResultAndTradeTypeAvailable() {
    TransactionsResult result = new TransactionsResult();

    Assert.assertNotNull(result);
    Assert.assertEquals(TradeTypeEnum.JSAPI.name(), "JSAPI");
  }
}
