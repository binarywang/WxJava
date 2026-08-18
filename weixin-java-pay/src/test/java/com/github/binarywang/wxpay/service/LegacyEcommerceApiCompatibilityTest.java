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

  @Test
  public void shouldKeepLegacyRefundAndWithdrawNotificationSignatures() throws Exception {
    Class<?> legacyHeader = com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.class;

    Assert.assertNotNull(EcommerceService.class.getMethod("parseRefundNotifyResult", String.class, legacyHeader));
    Assert.assertNotNull(EcommerceService.class.getMethod("parseWithdrawNotifyResult", String.class, legacyHeader));
  }

  @Test
  public void shouldMakeLegacyHeaderMoreSpecificAndPreserveSignatureFields() {
    com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader legacyHeader =
      com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.builder()
        .timeStamp("timestamp")
        .nonce("nonce")
        .signed("signed")
        .serialNo("serial-no")
        .build();

    Assert.assertTrue(com.github.binarywang.wxpay.bean.notify.SignatureHeader.class
      .isAssignableFrom(legacyHeader.getClass()));
    com.github.binarywang.wxpay.bean.notify.SignatureHeader unifiedHeader =
      EcommerceService.toUnifiedSignatureHeader(legacyHeader);
    Assert.assertEquals(unifiedHeader.getTimeStamp(), "timestamp");
    Assert.assertEquals(unifiedHeader.getNonce(), "nonce");
    Assert.assertEquals(unifiedHeader.getSignature(), "signed");
    Assert.assertEquals(unifiedHeader.getSerial(), "serial-no");
  }
}
