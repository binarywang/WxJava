package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.TransactionsResult;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

/**
 * Compatibility checks for the pre-#4014 e-commerce API.
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
  public void shouldKeepLegacySignatureHeaderConstructorAndBuilderAbi() throws Exception {
    com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader legacyHeader =
      com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.builder()
        .timeStamp("timestamp")
        .nonce("nonce")
        .signed("signed")
        .serialNo("serial-no")
        .build();

    Assert.assertNotNull(com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.class.getConstructor(
      String.class, String.class, String.class, String.class));
    Assert.assertEquals(com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.SignatureHeaderBuilder.class,
      com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.SignatureHeaderBuilder.class
        .getMethod("timeStamp", String.class).getReturnType());
    Assert.assertEquals(com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.SignatureHeaderBuilder.class,
      com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader.SignatureHeaderBuilder.class
        .getMethod("nonce", String.class).getReturnType());
    Assert.assertTrue(com.github.binarywang.wxpay.bean.notify.SignatureHeader.class
      .isAssignableFrom(legacyHeader.getClass()));
    com.github.binarywang.wxpay.bean.notify.SignatureHeader unifiedHeader =
      EcommerceService.toUnifiedSignatureHeader(legacyHeader);
    Assert.assertEquals(unifiedHeader.getTimeStamp(), "timestamp");
    Assert.assertEquals(unifiedHeader.getNonce(), "nonce");
    Assert.assertEquals(unifiedHeader.getSignature(), "signed");
    Assert.assertEquals(unifiedHeader.getSerial(), "serial-no");
  }

  @Test
  public void shouldIncludeTimestampAndNonceInLegacyHeaderEquality() {
    com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader first =
      new com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader("timestamp-1", "nonce", "signed", "serial-no");
    com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader second =
      new com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader("timestamp-2", "nonce", "signed", "serial-no");

    Assert.assertNotEquals(first, second);
  }

  @Test
  public void shouldReadLegacySerializedHeaderFields() throws Exception {
    String legacySerializedHeader = "rO0ABXNyADpjb20uZ2l0aHViLmJpbmFyeXdhbmcud3hwYXkuYmVhbi5lY29tbWVyY2UuU2lnbmF0dXJlSGVhZGVyn3ApxLekv9MCAARMAAVub25jZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wACHNlcmlhbE5vcQB+AAFMAAZzaWduZWRxAH4AAUwACXRpbWVTdGFtcHEAfgABeHB0AAVub25jZXQACXNlcmlhbC1ub3QABnNpZ25lZHQACXRpbWVzdGFtcA==";
    ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(
      Base64.getDecoder().decode(legacySerializedHeader)));
    com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader header =
      (com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader) input.readObject();

    Assert.assertEquals(header.getTimeStamp(), "timestamp");
    Assert.assertEquals(header.getNonce(), "nonce");
    Assert.assertEquals(header.getSigned(), "signed");
    Assert.assertEquals(header.getSerialNo(), "serial-no");
  }

  private void shouldCompileNullNotificationHeaderCalls(EcommerceService ecommerceService) throws Exception {
    ecommerceService.parseRefundNotifyResult("notify-data", null);
    ecommerceService.parseWithdrawNotifyResult("notify-data", null);
  }
}
