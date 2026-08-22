package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import me.chanjar.weixin.channel.bean.after.GuaranteeModifyRequest;
import me.chanjar.weixin.channel.bean.after.GuaranteeOrderInfoResponse;
import me.chanjar.weixin.channel.bean.after.GuaranteeOrderListResponse;
import me.chanjar.weixin.channel.bean.after.GuaranteeProofRequest;
import me.chanjar.weixin.channel.bean.after.GuaranteeRefuseRequest;
import org.testng.annotations.Test;

/**
 * 保障单模型 JSON 映射测试。
 */
public class WxChannelAfterSaleServiceImplGuaranteeTest {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Test
  public void shouldSerializeGuaranteeOperationRequestsWithOfficialFieldNames() throws Exception {
    JsonNode modify = OBJECT_MAPPER.readTree(OBJECT_MAPPER.writeValueAsString(
      new GuaranteeModifyRequest("2000001077270153", 50, "协商说明")));
    assertEquals(modify.get("guarantee_order_id").asText(), "2000001077270153");
    assertEquals(modify.get("bad_level").asInt(), 50);
    assertEquals(modify.get("merchant_remark").asText(), "协商说明");

    JsonNode proof = OBJECT_MAPPER.readTree(OBJECT_MAPPER.writeValueAsString(
      new GuaranteeProofRequest("2000001077270153", "举证说明", Arrays.asList("media-1"))));
    assertEquals(proof.get("content").asText(), "举证说明");
    assertEquals(proof.get("pic_list").get(0).asText(), "media-1");

    JsonNode refuse = OBJECT_MAPPER.readTree(OBJECT_MAPPER.writeValueAsString(
      new GuaranteeRefuseRequest("2000001077270153", "拒绝原因", Arrays.asList("media-2"))));
    assertEquals(refuse.get("reason").asText(), "拒绝原因");
    assertEquals(refuse.get("pic_list").get(0).asText(), "media-2");
  }

  @Test
  public void shouldDeserializeGuaranteeOrderListAndDetail() throws Exception {
    String listJson = "{\"errcode\":0,\"total_num\":1,\"guarantee_order_list\":[{"
      + "\"guarantee_order_id\":\"2000001077270153\",\"apply_reason\":\"质量问题\","
      + "\"pay_amount\":100,\"product_info\":{\"product_id\":\"123\","
      + "\"sku_id\":\"456\",\"sku_name\":\"红色\"}}]}";
    GuaranteeOrderListResponse listResponse = OBJECT_MAPPER.readValue(
      listJson, GuaranteeOrderListResponse.class);
    assertEquals(listResponse.getTotalNum(), Integer.valueOf(1));
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getGuaranteeOrderId(), "2000001077270153");
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getApplyReason(), "质量问题");
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getPayAmount(), Integer.valueOf(100));
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getProductInfo().getProductId(), "123");
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getProductInfo().getSkuName(), "红色");

    String detailJson = "{\"errcode\":0,\"guarantee_order\":{"
      + "\"guarantee_order_id\":\"2000001077270153\",\"apply_reason\":\"质量问题\","
      + "\"pay_amount\":100,\"product_info\":{\"product_id\":\"123\","
      + "\"sku_id\":\"456\",\"sku_name\":\"红色\"}}}";
    GuaranteeOrderInfoResponse detailResponse = OBJECT_MAPPER.readValue(
      detailJson, GuaranteeOrderInfoResponse.class);
    assertEquals(detailResponse.getGuaranteeOrder().getGuaranteeOrderId(), "2000001077270153");
    assertEquals(detailResponse.getGuaranteeOrder().getProductInfo().getSkuId(), "456");
  }
}
