package com.github.binarywang.wxpay.bean.request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link WxPayPartnerRefundV3Request} 单元测试
 *
 */
public class WxPayPartnerRefundV3RequestTest {

  @Test
  public void testSpAppidAndSubAppidSerialization() {
    WxPayPartnerRefundV3Request request = new WxPayPartnerRefundV3Request();
    request.setSpAppid("wx8888888888888888");
    request.setSubAppid("wxd678efh567hg6999");
    request.setSubMchid("1230000109");
    request.setOutRefundNo("1217752501201407033233368018");
    request.setFundsAccount("AVAILABLE");

    Gson gson = new Gson();
    String json = gson.toJson(request);
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

    assertThat(jsonObject.has("sp_appid")).isTrue();
    assertThat(jsonObject.get("sp_appid").getAsString()).isEqualTo("wx8888888888888888");
    assertThat(jsonObject.has("sub_appid")).isTrue();
    assertThat(jsonObject.get("sub_appid").getAsString()).isEqualTo("wxd678efh567hg6999");
    assertThat(jsonObject.has("sub_mchid")).isTrue();
    assertThat(jsonObject.get("sub_mchid").getAsString()).isEqualTo("1230000109");
    assertThat(jsonObject.has("out_refund_no")).isTrue();
    assertThat(jsonObject.get("out_refund_no").getAsString()).isEqualTo("1217752501201407033233368018");
    assertThat(jsonObject.has("funds_account")).isTrue();
    assertThat(jsonObject.get("funds_account").getAsString()).isEqualTo("AVAILABLE");
  }

  @Test
  public void testSubAppidIsOptional() {
    WxPayPartnerRefundV3Request request = new WxPayPartnerRefundV3Request();
    request.setSpAppid("wx8888888888888888");
    request.setSubMchid("1230000109");
    request.setOutRefundNo("1217752501201407033233368018");

    Gson gson = new Gson();
    String json = gson.toJson(request);
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

    assertThat(jsonObject.has("sp_appid")).isTrue();
    assertThat(jsonObject.get("sp_appid").getAsString()).isEqualTo("wx8888888888888888");
    assertThat(jsonObject.has("sub_appid")).isFalse();
  }
}
