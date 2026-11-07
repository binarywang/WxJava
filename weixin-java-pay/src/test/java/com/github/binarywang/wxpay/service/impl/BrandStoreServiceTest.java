package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.brandstore.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Proxy;

/** 品牌门店接口的离线契约测试。 */
public class BrandStoreServiceTest {
  private String method;
  private String url;
  private String body;
  private String response;
  private WxPayException error;

  private BrandStoreServiceImpl service(String response) {
    this.response = response;
    this.error = null;
    return new BrandStoreServiceImpl((WxPayService) Proxy.newProxyInstance(
      getClass().getClassLoader(), new Class[]{WxPayService.class}, (proxy, called, args) -> {
        if ("getPayBaseUrl".equals(called.getName())) {
          return "https://pay.example";
        }
        this.method = called.getName();
        this.url = (String) args[0];
        this.body = args.length > 1 && args[1] instanceof String ? (String) args[1] : null;
        if ("requestV3".equals(this.method)) {
          HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase) args[1];
          Assert.assertEquals(request.getURI().toString(), this.url);
          Assert.assertEquals(request.getFirstHeader("Accept").getValue(), "application/json");
          Assert.assertTrue(request.getEntity().getContentType().getValue().startsWith("application/json"));
          this.method = request.getMethod();
          this.body = EntityUtils.toString(request.getEntity(), "UTF-8");
        }
        if (this.error != null) {
          throw this.error;
        }
        return this.response;
      }));
  }

  @Test
  public void createSerializesNestedStoreAndParsesFullResponse() throws Exception {
    BrandStoreServiceImpl service = service("{\"brand_id\":\"brand\",\"store_id\":\"store\",\"store_state\":\"OPEN\",\"audit_state\":\"REJECTED\",\"review_reject_reason\":\"地址错误\",\"store_basics\":{\"branch_name\":\"深圳店\"},\"store_address\":{\"longitude\":\"112.63484\"},\"store_business\":{\"service_phone\":\"010-12345678\"},\"store_recipient\":[{\"mchid\":\"mch\",\"company_name\":\"公司\",\"recipient_state\":\"CONFIRMING\"}]}");
    BrandStoreRequest request = new BrandStoreRequest();
    request.setBrandId("brand");
    BrandStoreBasics basics = new BrandStoreBasics();
    basics.setStoreReferenceId("ref");
    basics.setBranchName("深圳店");
    request.setStoreBasics(basics);
    BrandStoreAddress address = new BrandStoreAddress();
    address.setAddressCode("440305");
    address.setAddressDetail("地址");
    address.setAddressComplements("地铁口");
    address.setLongitude("112.63484");
    address.setLatitude("37.75464");
    request.setStoreAddress(address);
    BrandStoreBusiness business = new BrandStoreBusiness();
    business.setBusinessHours("09:00-20:00");
    business.setServicePhone("010-12345678");
    request.setStoreBusiness(business);
    BrandStoreResult result = service.create(request);
    Assert.assertEquals(method, "postV3");
    Assert.assertEquals(url, "https://pay.example/v3/brand/partner/store/brandstores");
    JsonObject json = new JsonParser().parse(body).getAsJsonObject();
    Assert.assertEquals(json.getAsJsonObject("store_basics").get("store_reference_id").getAsString(), "ref");
    Assert.assertEquals(json.getAsJsonObject("store_address").get("address_complements").getAsString(), "地铁口");
    Assert.assertEquals(json.getAsJsonObject("store_business").get("business_hours").getAsString(), "09:00-20:00");
    Assert.assertEquals(result.getStoreId(), "store");
    Assert.assertEquals(result.getAuditState(), "REJECTED");
    Assert.assertEquals(result.getStoreState(), "OPEN");
    Assert.assertEquals(result.getReviewRejectReason(), "地址错误");
    Assert.assertEquals(result.getStoreRecipient().get(0).getRecipientState(), "CONFIRMING");
    Assert.assertEquals(result.getStoreBasics().getBranchName(), "深圳店");
    Assert.assertEquals(result.getStoreAddress().getLongitude(), "112.63484");
    Assert.assertEquals(result.getStoreBusiness().getServicePhone(), "010-12345678");
  }

  @Test
  public void queryEncodesPathAndQueryIdentifiers() throws Exception {
    service("{\"store_id\":\"store\"}").query("brand &+", "store /中");
    Assert.assertEquals(method, "getV3");
    Assert.assertEquals(url, "https://pay.example/v3/brand/partner/store/brandstores/store%20%2F%E4%B8%AD?brand_id=brand%20%26%2B");
  }

  @Test
  public void updateUsesPatchAndDeletePreservesRequiredJsonBody() throws Exception {
    BrandStoreServiceImpl service = service("{}");
    BrandStoreRequest request = new BrandStoreRequest();
    request.setBrandId("brand");
    service.update("store", request);
    Assert.assertEquals(method, "patchV3");
    Assert.assertEquals(url, "https://pay.example/v3/brand/partner/store/brandstores/store");
    Assert.assertEquals(body, "{\"brand_id\":\"brand\"}");
    service.delete("brand", "store");
    Assert.assertEquals(method, "DELETE");
    Assert.assertEquals(body, "{\"brand_id\":\"brand\"}");
  }

  @Test
  public void bindsAndUnbindsRecipientWithTypedResponses() throws Exception {
    BrandStoreServiceImpl service = service("{\"brand_id\":\"brand\",\"store_id\":\"store\",\"mchid\":\"mch\",\"company_name\":\"公司\",\"recipient_state\":\"CONFIRMED\"}");
    BrandStoreBindRecipientRequest request = new BrandStoreBindRecipientRequest();
    request.setBrandId("brand");
    request.setMchid("mch");
    request.setCompanyName("公司");
    Assert.assertEquals(service.bindRecipient("store", request).getRecipientState(), "CONFIRMED");
    Assert.assertEquals(method, "postV3");
    Assert.assertTrue(url.endsWith("/store/bindrecipient"));
    Assert.assertEquals(new JsonParser().parse(body).getAsJsonObject().get("company_name").getAsString(), "公司");
    this.response = "{\"failed_reason\":\"解绑失败\"}";
    BrandStoreUnbindRecipientRequest unbind = new BrandStoreUnbindRecipientRequest();
    unbind.setBrandId("brand");
    unbind.setMchid("mch");
    Assert.assertEquals(service.unbindRecipient("store", unbind).getFailedReason(), "解绑失败");
    Assert.assertTrue(url.endsWith("/store/unbindrecipient"));
    Assert.assertEquals(body, "{\"brand_id\":\"brand\",\"mchid\":\"mch\"}");
  }

  @Test
  public void propagatesWechatErrors() throws Exception {
    BrandStoreServiceImpl service = service("{}");
    this.error = new WxPayException("denied");
    try {
      service.query("brand", "store");
      Assert.fail("Expected WxPayException");
    } catch (WxPayException actual) {
      Assert.assertSame(actual, this.error);
    }
  }
}
