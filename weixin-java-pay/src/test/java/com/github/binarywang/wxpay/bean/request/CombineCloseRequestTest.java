package com.github.binarywang.wxpay.bean.request;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2024-12-19
 */
public class CombineCloseRequestTest {

  @Test
  public void testSerialization() {
    CombineCloseRequest request = new CombineCloseRequest();
    request.setCombineAppid("wxd678efh567hg6787");
    request.setCombineOutTradeNo("P20150806125346");

    CombineCloseRequest.SubOrders subOrder = new CombineCloseRequest.SubOrders();
    subOrder.setMchid("1900000109");
    subOrder.setOutTradeNo("20150806125346");
    subOrder.setSubMchid("1230000109");
    subOrder.setSubAppid("wxd678efh567hg6999");

    request.setSubOrders(Arrays.asList(subOrder));

    Gson gson = new Gson();
    String json = gson.toJson(request);

    // Verify that the JSON contains the new fields
    assertThat(json).contains("\"sub_mchid\":\"1230000109\"");
    assertThat(json).contains("\"sub_appid\":\"wxd678efh567hg6999\"");
    assertThat(json).contains("\"combine_appid\":\"wxd678efh567hg6787\"");
    assertThat(json).contains("\"mchid\":\"1900000109\"");
    assertThat(json).contains("\"out_trade_no\":\"20150806125346\"");

    // Verify deserialization works
    CombineCloseRequest deserializedRequest = gson.fromJson(json, CombineCloseRequest.class);
    assertThat(deserializedRequest.getCombineAppid()).isEqualTo("wxd678efh567hg6787");
    assertThat(deserializedRequest.getSubOrders()).hasSize(1);
    assertThat(deserializedRequest.getSubOrders().get(0).getSubMchid()).isEqualTo("1230000109");
    assertThat(deserializedRequest.getSubOrders().get(0).getSubAppid()).isEqualTo("wxd678efh567hg6999");
  }
}