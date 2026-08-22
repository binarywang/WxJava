package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import me.chanjar.weixin.channel.bean.after.GuaranteeModifyRequest;
import me.chanjar.weixin.channel.bean.after.GuaranteeOrderIdParam;
import me.chanjar.weixin.channel.bean.after.GuaranteeOrderInfoResponse;
import me.chanjar.weixin.channel.bean.after.GuaranteeOrderListParam;
import me.chanjar.weixin.channel.bean.after.GuaranteeOrderListResponse;
import me.chanjar.weixin.channel.bean.after.GuaranteeProofRequest;
import me.chanjar.weixin.channel.bean.after.GuaranteeRefuseRequest;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import org.testng.annotations.Test;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.AfterSale.GUARANTEE_ORDER_ACCEPT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.AfterSale.GUARANTEE_ORDER_GET_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.AfterSale.GUARANTEE_ORDER_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.AfterSale.GUARANTEE_ORDER_MODIFY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.AfterSale.GUARANTEE_ORDER_PROOF_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.AfterSale.GUARANTEE_ORDER_REFUSE_URL;

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

  @Test
  public void shouldDelegateGuaranteeOrderEndpointsAndDecodeResponses() throws Exception {
    BaseWxChannelServiceImpl shopService = mock(BaseWxChannelServiceImpl.class);
    WxChannelAfterSaleServiceImpl service = new WxChannelAfterSaleServiceImpl(shopService);
    GuaranteeOrderListParam listParam = new GuaranteeOrderListParam();
    listParam.setOrderId("order-1");
    GuaranteeModifyRequest modifyRequest = new GuaranteeModifyRequest("guarantee-1", 50, "协商说明");
    GuaranteeProofRequest proofRequest = new GuaranteeProofRequest("guarantee-1", "举证说明",
      Arrays.asList("media-1"));
    GuaranteeRefuseRequest refuseRequest = new GuaranteeRefuseRequest("guarantee-1", "拒绝原因",
      Arrays.asList("media-2"));

    when(shopService.post(eq(GUARANTEE_ORDER_LIST_URL), eq(listParam))).thenReturn(
      "{\"errcode\":0,\"total_num\":1,\"guarantee_order_list\":[{\"guarantee_order_id\":\"guarantee-1\"}]}" );
    when(shopService.post(eq(GUARANTEE_ORDER_GET_URL), eq(new GuaranteeOrderIdParam("guarantee-1")))).thenReturn(
      "{\"errcode\":0,\"guarantee_order\":{\"guarantee_order_id\":\"guarantee-1\"}}" );
    when(shopService.post(eq(GUARANTEE_ORDER_ACCEPT_URL), eq(new GuaranteeOrderIdParam("guarantee-1"))))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(shopService.post(eq(GUARANTEE_ORDER_MODIFY_URL), eq(modifyRequest)))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(shopService.post(eq(GUARANTEE_ORDER_PROOF_URL), eq(proofRequest)))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(shopService.post(eq(GUARANTEE_ORDER_REFUSE_URL), eq(refuseRequest)))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    GuaranteeOrderListResponse listResponse = service.listGuaranteeOrder(listParam);
    GuaranteeOrderInfoResponse detailResponse = service.getGuaranteeOrder("guarantee-1");
    WxChannelBaseResponse acceptResponse = service.acceptGuarantee("guarantee-1");
    WxChannelBaseResponse modifyResponse = service.modifyGuarantee(modifyRequest);
    WxChannelBaseResponse proofResponse = service.proofGuarantee(proofRequest);
    WxChannelBaseResponse refuseResponse = service.refuseGuarantee(refuseRequest);

    assertEquals(listResponse.getGuaranteeOrderList().get(0).getGuaranteeOrderId(), "guarantee-1");
    assertEquals(detailResponse.getGuaranteeOrder().getGuaranteeOrderId(), "guarantee-1");
    assertTrue(acceptResponse.isSuccess());
    assertTrue(modifyResponse.isSuccess());
    assertTrue(proofResponse.isSuccess());
    assertTrue(refuseResponse.isSuccess());
    verify(shopService).post(GUARANTEE_ORDER_LIST_URL, listParam);
    verify(shopService).post(GUARANTEE_ORDER_GET_URL, new GuaranteeOrderIdParam("guarantee-1"));
    verify(shopService).post(GUARANTEE_ORDER_ACCEPT_URL, new GuaranteeOrderIdParam("guarantee-1"));
    verify(shopService).post(GUARANTEE_ORDER_MODIFY_URL, modifyRequest);
    verify(shopService).post(GUARANTEE_ORDER_PROOF_URL, proofRequest);
    verify(shopService).post(GUARANTEE_ORDER_REFUSE_URL, refuseRequest);
  }
}
