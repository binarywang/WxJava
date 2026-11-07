package me.chanjar.weixin.channel.bean.after;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.testng.annotations.Test;

public class AfterSaleContractTest {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Test
  public void shouldUseOfficialAfterSaleAndGuaranteeFieldNames() throws Exception {
    AfterSaleGenAfterSaleOrderParam afterSaleParam = new AfterSaleGenAfterSaleOrderParam();
    ExchangeSkuInfo exchangeSkuInfo = new ExchangeSkuInfo();
    exchangeSkuInfo.setNewSkuId("new-sku");
    afterSaleParam.setExchangeSkuInfo(exchangeSkuInfo);

    AfterSaleHandleFastExchangeReceiptParam receiptParam = new AfterSaleHandleFastExchangeReceiptParam();
    receiptParam.setRejectConfirmExchange(Collections.singletonList("media-1"));

    GuaranteeOrderIdParam guaranteeParam = new GuaranteeOrderIdParam("guarantee-1");
    assertTrue(OBJECT_MAPPER.writeValueAsString(afterSaleParam).contains("\"exchange_sku_info\":{\"new_sku_id\":\"new-sku\"}"));
    assertTrue(OBJECT_MAPPER.writeValueAsString(receiptParam).contains("\"reject_confirm_exchange\":[\"media-1\"]"));
    assertTrue(OBJECT_MAPPER.writeValueAsString(guaranteeParam).contains("\"guarantee_order_id\":\"guarantee-1\""));
  }

  @Test
  public void shouldDecodeOfficialResponseFields() throws Exception {
    AfterSaleCreateResponse createResponse = OBJECT_MAPPER.readValue(
        "{\"errcode\":0,\"after_sale_order_id\":\"after-1\"}", AfterSaleCreateResponse.class);
    GuaranteeOrderResponse guaranteeResponse = OBJECT_MAPPER.readValue(
        "{\"errcode\":0,\"guarantee_order\":{\"guarantee_order_id\":\"guarantee-1\"}}", GuaranteeOrderResponse.class);

    assertEquals(createResponse.getAfterSaleOrderId(), "after-1");
    assertEquals(guaranteeResponse.getGuaranteeOrder().get("guarantee_order_id").asText(), "guarantee-1");
  }
}
