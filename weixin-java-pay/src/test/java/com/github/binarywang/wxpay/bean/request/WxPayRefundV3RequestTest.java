package com.github.binarywang.wxpay.bean.request;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link WxPayRefundV3Request} 单元测试
 *
 */
public class WxPayRefundV3RequestTest {

  @Test
  public void testFundsAccountSerialization() {
    WxPayRefundV3Request request = new WxPayRefundV3Request();
    request.setOutRefundNo("1217752501201407033233368018");
    request.setFundsAccount("AVAILABLE");

    Gson gson = new Gson();
    String json = gson.toJson(request);
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

    assertThat(jsonObject.has("funds_account")).isTrue();
    assertThat(jsonObject.get("funds_account").getAsString()).isEqualTo("AVAILABLE");
  }

  @Test
  public void testAmountFromSerialization() {
    WxPayRefundV3Request.From from = new WxPayRefundV3Request.From();
    from.setAccount("AVAILABLE");
    from.setAmount(444);

    WxPayRefundV3Request.Amount amount = new WxPayRefundV3Request.Amount();
    amount.setRefund(888);
    amount.setTotal(888);
    amount.setCurrency("CNY");
    amount.setFrom(Collections.singletonList(from));

    WxPayRefundV3Request request = new WxPayRefundV3Request();
    request.setAmount(amount);

    Gson gson = new Gson();
    String json = gson.toJson(request);
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
    JsonArray fromJson = jsonObject.getAsJsonObject("amount").getAsJsonArray("from");

    assertThat(fromJson).hasSize(1);
    assertThat(fromJson.get(0).getAsJsonObject().get("account").getAsString()).isEqualTo("AVAILABLE");
    assertThat(fromJson.get(0).getAsJsonObject().get("amount").getAsInt()).isEqualTo(444);
  }
}
