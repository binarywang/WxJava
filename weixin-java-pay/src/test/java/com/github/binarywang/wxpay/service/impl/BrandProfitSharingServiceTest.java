package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.brandprofitsharing.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BrandProfitSharingService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.auth.PublicCertificateVerifier;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Proxy;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/** 连锁品牌分账接口契约测试，无需商户凭据。 */
public class BrandProfitSharingServiceTest {
  private static final Gson GSON = new Gson();
  private static final String BASE = "https://example.com/v3/brand/profitsharing";

  @Test
  public void shouldUseBrandQueryRouteAndEncodeOrderNumber() throws Exception {
    Capture capture = new Capture();
    capture.response = "{\"status\":\"FINISHED\",\"finish_amount\":123,\"finish_description\":\"完成\","
      + "\"receivers\":[{\"detail_id\":\"detail\",\"result\":\"SUCCESS\",\"finish_time\":\"2026-10-13T10:00:00+08:00\"}]}";
    BrandProfitSharingResult result = capture.service().query(BrandProfitSharingQueryRequest.newBuilder()
      .subMchId("store").transactionId("transaction").outOrderNo("order|*@").build());
    Assert.assertEquals(capture.url, BASE + "/orders?sub_mchid=store&transaction_id=transaction&out_order_no=order%7C%2A%40");
    Assert.assertEquals(capture.method, "getV3");
    Assert.assertEquals(result.getFinishAmount(), Integer.valueOf(123));
    Assert.assertEquals(result.getFinishDescription(), "完成");
    Assert.assertEquals(result.getReceivers().get(0).getDetailId(), "detail");
  }

  @Test
  public void shouldRouteReturnAndFinishAndReadResults() throws Exception {
    Capture capture = new Capture();
    BrandProfitSharingService service = capture.service();
    capture.response = "{\"return_no\":\"wechat-return\",\"result\":\"SUCCESS\",\"amount\":10}";
    BrandProfitSharingReturnResult result = service.returnOrder(BrandProfitSharingReturnRequest.newBuilder()
      .subMchId("store").outOrderNo("order").outReturnNo("return").returnMchId("receiver")
      .amount(10).description("退款").build());
    Assert.assertEquals(capture.url, BASE + "/returnorders");
    Assert.assertEquals(capture.method, "postV3");
    Assert.assertEquals(capture.json().get("return_mchid").getAsString(), "receiver");
    Assert.assertEquals(capture.json().get("amount").getAsInt(), 10);
    Assert.assertEquals(result.getReturnNo(), "wechat-return");
    Assert.assertEquals(result.getResult(), "SUCCESS");
    service.queryReturn(BrandProfitSharingReturnQueryRequest.newBuilder()
      .subMchId("store").outReturnNo("return").outOrderNo("order|*@").build());
    Assert.assertEquals(capture.url, BASE + "/returnorders?sub_mchid=store&out_return_no=return&out_order_no=order%7C%2A%40");
    service.queryReturn(BrandProfitSharingReturnQueryRequest.newBuilder()
      .subMchId("store").outReturnNo("return").orderId("wechat-order").build());
    Assert.assertEquals(capture.url, BASE + "/returnorders?sub_mchid=store&out_return_no=return&order_id=wechat-order");
    capture.response = "{\"order_id\":\"finish-order\",\"sub_mchid\":\"store\"}";
    Assert.assertEquals(service.finish(BrandProfitSharingFinishRequest.newBuilder().subMchId("store")
      .transactionId("transaction").outOrderNo("finish").description("完成").build()).getOrderId(), "finish-order");
    Assert.assertEquals(capture.url, BASE + "/finish-order");
    Assert.assertEquals(capture.json().get("sub_mchid").getAsString(), "store");
  }

  @Test
  public void shouldRouteRemainingAmountAndBrandRatio() throws Exception {
    Capture capture = new Capture();
    capture.response = "{\"transaction_id\":\"transaction\",\"unsplit_amount\":99}";
    Assert.assertEquals(capture.service().queryRemainingAmount("transaction").getUnsplitAmount(), Integer.valueOf(99));
    Assert.assertEquals(capture.url, BASE + "/orders/transaction/amounts");
    capture.response = "{\"brand_mchid\":\"brand\",\"max_ratio\":3000}";
    Assert.assertEquals(capture.service().queryMaxRatio("brand").getMaxRatio(), Integer.valueOf(3000));
    Assert.assertEquals(capture.url, BASE + "/brand-configs/brand");
  }

  @Test
  public void shouldTransmitReceiverRegistrationNameAsPlaintextPerContract() throws Exception {
    Capture capture = new Capture();
    BrandProfitSharingService service = capture.service();
    service.addReceiver(BrandProfitSharingReceiverRequest.newBuilder().brandMchId("brand")
      .appid("app").subAppid("brand-app").type("MERCHANT_ID").account("receiver")
      .name("测试名称").relationType("SUPPLIER").build());
    Assert.assertEquals(capture.url, BASE + "/receivers/add");
    Assert.assertEquals(capture.method, "postV3");
    Assert.assertEquals(capture.json().get("brand_mchid").getAsString(), "brand");
    Assert.assertEquals(capture.json().get("name").getAsString(), "测试名称");
    Assert.assertFalse(capture.json().has("sub_mchid"));
    service.deleteReceiver(BrandProfitSharingReceiverDeleteRequest.newBuilder().brandMchId("brand")
      .appid("app").subAppid("brand-app").type("MERCHANT_ID").account("receiver").build());
    Assert.assertEquals(capture.url, BASE + "/receivers/delete");
    Assert.assertEquals(capture.method, "postV3");
    Assert.assertFalse(capture.json().has("name"));
  }

  @Test
  public void shouldEncryptReceiverNameWithoutMutatingReusableRequest() throws Exception {
    Capture capture = new Capture();
    BrandProfitSharingRequest request = GSON.fromJson("{\"brand_mchid\":\"brand\",\"sub_mchid\":\"store\","
      + "\"appid\":\"app\",\"sub_appid\":\"brand-app\",\"finish\":true,"
      + "\"receivers\":[{\"type\":\"MERCHANT_ID\",\"account\":\"receiver\",\"amount\":10,\"name\":\"测试名称\"}]}", BrandProfitSharingRequest.class);
    BrandProfitSharingService service = capture.service();
    for (int n = 0; n < 2; n++) {
      service.profitSharing(request);
      Assert.assertEquals(capture.url, BASE + "/orders");
      Assert.assertEquals(capture.method, "postV3WithWechatpaySerial");
      String encrypted = capture.json().getAsJsonArray("receivers").get(0).getAsJsonObject().get("name").getAsString();
      Assert.assertEquals(RsaCryptoUtil.decryptOAEP(encrypted, capture.keys.getPrivate()), "测试名称");
      Assert.assertEquals(capture.json().get("brand_mchid").getAsString(), "brand");
      Assert.assertEquals(capture.json().get("sub_mchid").getAsString(), "store");
      Assert.assertTrue(capture.json().get("finish").getAsBoolean());
      Assert.assertEquals(request.getReceivers().get(0).getName(), "测试名称");
    }
  }

  @Test
  public void shouldPropagateWechatErrors() throws Exception {
    Capture capture = new Capture();
    capture.error = new WxPayException("RESOURCE_NOT_EXISTS");
    try {
      capture.service().queryMaxRatio("brand");
      Assert.fail("Expected WeChat error");
    } catch (WxPayException e) {
      Assert.assertSame(e, capture.error);
    }
  }

  @Test
  public void shouldVerifyAndDecryptBrandNotification() throws Exception {
    Capture capture = new Capture();
    String key = "0123456789abcdef0123456789abcdef";
    capture.config.setApiV3Key(key);
    String nonce = "0123456789ab";
    String associated = "profitsharing";
    String plaintext = "{\"sp_mchid\":\"provider\",\"sub_mchid\":\"store\",\"transaction_id\":\"transaction\","
      + "\"order_id\":\"order\",\"out_order_no\":\"out-order\",\"success_time\":\"2026-10-13T10:00:00+08:00\","
      + "\"receiver\":{\"type\":\"MERCHANT_ID\",\"account\":\"receiver\",\"amount\":10,\"description\":\"分账\"}}";
    javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding");
    cipher.init(javax.crypto.Cipher.ENCRYPT_MODE,
      new javax.crypto.spec.SecretKeySpec(key.getBytes(java.nio.charset.StandardCharsets.UTF_8), "AES"),
      new javax.crypto.spec.GCMParameterSpec(128, nonce.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
    cipher.updateAAD(associated.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    String encrypted = java.util.Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
    String data = "{\"resource\":{\"algorithm\":\"AEAD_AES_256_GCM\",\"nonce\":\"" + nonce
      + "\",\"associated_data\":\"" + associated + "\",\"ciphertext\":\"" + encrypted + "\"}}";
    com.github.binarywang.wxpay.bean.notify.SignatureHeader header = new com.github.binarywang.wxpay.bean.notify.SignatureHeader();
    header.setTimeStamp("123456");
    header.setNonce("signature-nonce");
    header.setSerial("PUB_KEY_ID_TEST");
    java.security.Signature signature = java.security.Signature.getInstance("SHA256withRSA");
    signature.initSign(capture.keys.getPrivate());
    signature.update((header.getTimeStamp() + "\n" + header.getNonce() + "\n" + data + "\n").getBytes(java.nio.charset.StandardCharsets.UTF_8));
    header.setSignature(java.util.Base64.getEncoder().encodeToString(signature.sign()));
    com.github.binarywang.wxpay.bean.profitsharing.notify.ProfitSharingNotifyV3Result result = capture.service().parseNotifyResult(data, header);
    Assert.assertEquals(result.getSpMchid(), "provider");
    Assert.assertEquals(result.getSubMchid(), "store");
    Assert.assertEquals(result.getReceiver().getAmount(), Integer.valueOf(10));
    try {
      capture.service().parseNotifyResult(data + " ", header);
      Assert.fail("Expected signature rejection");
    } catch (WxPayException e) {
      Assert.assertTrue(e.getMessage().contains("验证失败"));
    }
  }

  @Test(expectedExceptions = WxPayException.class, expectedExceptionsMessageRegExp = ".*签名头.*")
  public void shouldRequireNotificationSignatureHeader() throws Exception {
    new Capture().service().parseNotifyResult("{}", null);
  }

  private static class Capture {
    final KeyPair keys;
    final WxPayConfig config = new WxPayConfig();
    String method;
    String url;
    String body;
    String response = "{}";
    WxPayException error;

    Capture() throws Exception {
      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(2048);
      keys = generator.generateKeyPair();
      config.setVerifier(new PublicCertificateVerifier(keys.getPublic(), "PUB_KEY_ID_TEST"));
    }

    JsonObject json() {
      return GSON.fromJson(body, JsonObject.class);
    }

    BrandProfitSharingService service() {
      WxPayService pay = (WxPayService) Proxy.newProxyInstance(WxPayService.class.getClassLoader(),
        new Class<?>[]{WxPayService.class}, (proxy, called, args) -> {
          if (called.getName().equals("getPayBaseUrl")) return "https://example.com";
          if (called.getName().equals("getConfig")) return config;
          method = called.getName();
          url = (String) args[0];
          body = args.length > 1 ? (String) args[1] : null;
          if (error != null) throw error;
          return response;
        });
      return new BrandProfitSharingServiceImpl(pay);
    }
  }
}
