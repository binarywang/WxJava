package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentCancelRequest;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentRequest;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentQueryResult;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.impl.BrandApplymentServiceImpl;
import com.github.binarywang.wxpay.v3.auth.PublicCertificateVerifier;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Proxy;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/** 品牌入驻申请离线契约测试。 */
public class BrandApplymentServiceTest {
  private static final String BASE = "https://api.mch.weixin.qq.com";
  private static final Gson GSON = new Gson();

  @Test
  public void shouldEncryptAdminAndPreserveReusableRequest() throws Exception {
    Capture capture = new Capture();
    BrandApplymentService service = new BrandApplymentServiceImpl(capture.service());
    BrandApplymentRequest request = GSON.fromJson("{\"business_code\":\"brand_1\","
      + "\"admin_info\":{\"admin_name\":\"测试管理员\",\"id_doc_type\":\"IDENTIFICATION_TYPE_MAINLAND_ID_CARD\",\"id_card_number\":\"test-id\"},"
      + "\"subject_info\":{\"subject_type\":\"SUBJECT_TYPE_ENTERPRISE\",\"subject_name\":\"测试企业\",\"unified_social_credit_code\":\"test-code\"},"
      + "\"brand_basic_info\":{\"brand_name\":\"测试品牌\",\"brand_logo\":\"logo-media\"},"
      + "\"trademark\":{\"trademark_exists\":\"TRADEMARK_EXISTS\",\"trademark_registration_certificate\":{\"name\":\"商标\",\"certificate_list\":[\"cert-1\",\"cert-2\"],\"license_list\":[\"license-1\"]},"
      + "\"logo_trademark_registration_certificate\":{\"number\":\"logo-1\"},\"no_trademark_addition_prove_list\":[\"proof-1\"]}}", BrandApplymentRequest.class);
    BrandApplymentResult result = service.createApplyment(request);
    Assert.assertEquals(capture.method, "postV3WithWechatpaySerial");
    Assert.assertEquals(capture.url, BASE + "/v3/brand/applyments");
    JsonObject body = GSON.fromJson(capture.body, JsonObject.class);
    JsonObject admin = body.getAsJsonObject("admin_info");
    Assert.assertEquals(RsaCryptoUtil.decryptOAEP(admin.get("admin_name").getAsString(), capture.keys.getPrivate()), "测试管理员");
    Assert.assertEquals(RsaCryptoUtil.decryptOAEP(admin.get("id_card_number").getAsString(), capture.keys.getPrivate()), "test-id");
    Assert.assertEquals(admin.get("id_doc_type").getAsString(), "IDENTIFICATION_TYPE_MAINLAND_ID_CARD");
    Assert.assertEquals(body.getAsJsonObject("trademark").getAsJsonObject("trademark_registration_certificate").getAsJsonArray("certificate_list").size(), 2);
    Assert.assertEquals(result.getApplymentId(), "1234567890123456");
    Assert.assertEquals(request.getAdminInfo().getAdminName(), "测试管理员");
    service.createApplyment(request);
    admin = GSON.fromJson(capture.body, JsonObject.class).getAsJsonObject("admin_info");
    Assert.assertEquals(RsaCryptoUtil.decryptOAEP(admin.get("admin_name").getAsString(), capture.keys.getPrivate()), "测试管理员");
  }

  @Test
  public void shouldCancelByEitherIdentifier() throws Exception {
    Capture capture = new Capture();
    BrandApplymentService service = new BrandApplymentServiceImpl(capture.service());
    BrandApplymentCancelRequest request = new BrandApplymentCancelRequest();
    request.setBusinessCode("brand_1");
    Assert.assertEquals(service.cancelApplyment(request).getBusinessCode(), "brand_1");
    Assert.assertEquals(capture.method, "postV3");
    Assert.assertEquals(capture.url, BASE + "/v3/brand/applyments/cancel-applyment");
    Assert.assertEquals(GSON.fromJson(capture.body, JsonObject.class).get("business_code").getAsString(), "brand_1");
    request.setBusinessCode(null);
    request.setApplymentId("1234567890123456");
    service.cancelApplyment(request);
    Assert.assertFalse(GSON.fromJson(capture.body, JsonObject.class).has("business_code"));
  }

  @Test
  public void shouldQueryBothIdentifiersAndReadState() throws Exception {
    Capture capture = new Capture();
    capture.response = "{\"applyment_id\":\"1234567890123456\",\"business_code\":\"brand_1\",\"applyment_state\":\"APPLYMENT_STATE_FINISH\",\"applyment_state_desc\":\"入驻完成\",\"authorization_confirmation_qr_code\":\"https://example.com/auth\",\"reject_reason\":\"测试原因\",\"brand_id\":\"12345678901234567890\"}";
    BrandApplymentService service = new BrandApplymentServiceImpl(capture.service());
    BrandApplymentQueryResult result = service.queryApplymentByBusinessCode("brand_1");
    Assert.assertEquals(result.getApplymentState(), "APPLYMENT_STATE_FINISH");
    Assert.assertEquals(result.getApplymentStateDesc(), "入驻完成");
    Assert.assertEquals(result.getAuthorizationConfirmationQrCode(), "https://example.com/auth");
    Assert.assertEquals(result.getRejectReason(), "测试原因");
    Assert.assertEquals(result.getBrandId(), "12345678901234567890");
    Assert.assertEquals(capture.method, "getV3");
    Assert.assertEquals(capture.url, BASE + "/v3/brand/applyments/business-code/brand_1");
    Assert.assertEquals(service.queryApplymentByApplymentId("1234567890123456").getApplymentId(), "1234567890123456");
    Assert.assertEquals(capture.url, BASE + "/v3/brand/applyments/applyment-id/1234567890123456");
  }

  @Test
  public void shouldPropagateWechatErrors() throws Exception {
    Capture capture = new Capture();
    capture.error = new WxPayException("RESOURCE_NOT_EXISTS");
    BrandApplymentService service = new BrandApplymentServiceImpl(capture.service());
    try {
      service.queryApplymentByBusinessCode("brand_1");
      Assert.fail("Expected WeChat error");
    } catch (WxPayException e) {
      Assert.assertSame(e, capture.error);
    }
  }

  private static class Capture {
    final KeyPair keys;
    final WxPayConfig config = new WxPayConfig();
    String method;
    String url;
    String body;
    String response = "{\"applyment_id\":\"1234567890123456\",\"business_code\":\"brand_1\"}";
    WxPayException error;

    Capture() throws Exception {
      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(2048);
      keys = generator.generateKeyPair();
      config.setVerifier(new PublicCertificateVerifier(keys.getPublic(), "PUB_KEY_ID_TEST"));
    }

    WxPayService service() {
      return (WxPayService) Proxy.newProxyInstance(WxPayService.class.getClassLoader(), new Class<?>[]{WxPayService.class}, (proxy, called, args) -> {
        if (called.getName().equals("getPayBaseUrl")) {
          return BASE;
        }
        if (called.getName().equals("getConfig")) {
          return config;
        }
        if (called.getName().equals("getV3") || called.getName().equals("postV3") || called.getName().equals("postV3WithWechatpaySerial")) {
          method = called.getName();
          url = (String) args[0];
          body = args.length > 1 ? (String) args[1] : null;
          if (error != null) {
            throw error;
          }
          return response;
        }
        throw new UnsupportedOperationException(called.getName());
      });
    }
  }
}
