package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Answers.CALLS_REAL_METHODS;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import me.chanjar.weixin.channel.api.WxChannelAfterSaleService;
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
      + "\"guarantee_order_id\":\"2000001077270153\",\"status\":\"PENDING\","
      + "\"product_info\":[{\"product_id\":\"123\"}]}]}";
    GuaranteeOrderListResponse listResponse = OBJECT_MAPPER.readValue(
      listJson, GuaranteeOrderListResponse.class);
    assertEquals(listResponse.getTotalNum(), Integer.valueOf(1));
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getGuaranteeOrderId(), "2000001077270153");
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getStatus(), "PENDING");
    assertEquals(listResponse.getGuaranteeOrderList().get(0).getProductInfo().get(0).getProductId(), "123");

    String detailJson = "{\"errcode\":0,\"guarantee_order\":{"
      + "\"guarantee_order_id\":\"2000001077270153\",\"status\":\"PENDING\","
      + "\"product_info\":{\"product_id\":\"123\"}}}";
    GuaranteeOrderInfoResponse detailResponse = OBJECT_MAPPER.readValue(
      detailJson, GuaranteeOrderInfoResponse.class);
    assertEquals(detailResponse.getGuaranteeOrder().getGuaranteeOrderId(), "2000001077270153");
    assertEquals(detailResponse.getGuaranteeOrder().getStatus(), "PENDING");
    assertEquals(detailResponse.getGuaranteeOrder().getProductInfo().getProductId(), "123");
  }

  @Test
  public void shouldSerializeGuaranteeOrderListParamWithOfficialFieldNames() throws Exception {
    GuaranteeOrderListParam param = OBJECT_MAPPER.readValue(
      "{\"begin_create_time\":1,\"end_create_time\":2,\"begin_update_time\":3,"
        + "\"end_update_time\":4,\"next_key\":\"next\"}", GuaranteeOrderListParam.class);

    JsonNode json = OBJECT_MAPPER.readTree(OBJECT_MAPPER.writeValueAsString(param));
    assertEquals(json.get("begin_create_time").asLong(), 1L);
    assertEquals(json.get("end_create_time").asLong(), 2L);
    assertEquals(json.get("begin_update_time").asLong(), 3L);
    assertEquals(json.get("end_update_time").asLong(), 4L);
    assertEquals(json.get("next_key").asText(), "next");
  }

  @Test
  public void shouldExposeGuaranteeMethodsAsDefaultMethods() throws Exception {
    assertTrue(WxChannelAfterSaleService.class.getMethod("listGuaranteeOrder", GuaranteeOrderListParam.class)
      .isDefault());
    assertTrue(WxChannelAfterSaleService.class.getMethod("getGuaranteeOrder", String.class).isDefault());
    assertTrue(WxChannelAfterSaleService.class.getMethod("acceptGuarantee", String.class).isDefault());
    assertTrue(WxChannelAfterSaleService.class.getMethod("modifyGuarantee", GuaranteeModifyRequest.class).isDefault());
    assertTrue(WxChannelAfterSaleService.class.getMethod("proofGuarantee", GuaranteeProofRequest.class).isDefault());
    assertTrue(WxChannelAfterSaleService.class.getMethod("refuseGuarantee", GuaranteeRefuseRequest.class).isDefault());

    WxChannelAfterSaleService service = mock(WxChannelAfterSaleService.class, CALLS_REAL_METHODS);
    try {
      service.acceptGuarantee("guarantee-1");
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException ignored) {
      // Expected from the compatibility default method.
    }
  }

  @Test
  public void shouldDelegateGuaranteeOrderEndpointsAndDecodeResponses() throws Exception {
    BaseWxChannelServiceImpl shopService = mock(BaseWxChannelServiceImpl.class);
    WxChannelAfterSaleServiceImpl service = new WxChannelAfterSaleServiceImpl(shopService);
    GuaranteeOrderListParam listParam = new GuaranteeOrderListParam();
    listParam.setBeginCreateTime(1L);
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
      .thenReturn("{\"errcode\":40001,\"errmsg\":\"invalid credential\"}");

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
    assertFalse(refuseResponse.isSuccess());
    verify(shopService).post(GUARANTEE_ORDER_LIST_URL, listParam);
    verify(shopService).post(GUARANTEE_ORDER_GET_URL, new GuaranteeOrderIdParam("guarantee-1"));
    verify(shopService).post(GUARANTEE_ORDER_ACCEPT_URL, new GuaranteeOrderIdParam("guarantee-1"));
    verify(shopService).post(GUARANTEE_ORDER_MODIFY_URL, modifyRequest);
    verify(shopService).post(GUARANTEE_ORDER_PROOF_URL, proofRequest);
    verify(shopService).post(GUARANTEE_ORDER_REFUSE_URL, refuseRequest);
  }
}
