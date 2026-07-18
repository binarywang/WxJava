package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link GeneralInvoiceRequest} 单元测试。
 */
public class GeneralInvoiceRequestTest {

  @Test
  public void shouldSerializeGeneralInvoiceRequestUsingWechatPayFieldNames() {
    GeneralInvoiceRequest request = new GeneralInvoiceRequest();
    request.setSubMchid("1900000109");
    request.setFapiaoApplyId("apply_20260718_001");

    BuyerInformation buyer = new BuyerInformation();
    buyer.setType("ORGANIZATION");
    buyer.setName("示例企业");
    buyer.setTaxpayerId("91440300TEST00001");
    request.setBuyerInformation(buyer);

    GeneralInvoiceRequest.InvoiceItem item = new GeneralInvoiceRequest.InvoiceItem();
    item.setTaxCode("3010101020203000000");
    item.setGoodsName("技术服务");
    item.setTotalAmount(100);
    item.setTaxRate(600);
    item.setDiscount(false);

    GeneralInvoiceRequest.FapiaoInformation invoice = new GeneralInvoiceRequest.FapiaoInformation();
    invoice.setFapiaoId("invoice_20260718_001");
    invoice.setTotalAmount(100);
    invoice.setItems(Collections.singletonList(item));
    invoice.setBillingPersonId("billing_person_001");
    invoice.setFapiaoBillType("COMM_FAPIAO");

    GeneralInvoiceRequest.TransactionInformation transaction = new GeneralInvoiceRequest.TransactionInformation();
    transaction.setPayChannel("WECHAT_PAY");
    transaction.setOutTradeNo("order_20260718_001");
    transaction.setAmount(100);
    invoice.setTransactionInformation(Collections.singletonList(transaction));
    request.setFapiaoInformation(invoice);

    JsonObject json = new Gson().toJsonTree(request).getAsJsonObject();
    assertThat(json.get("sub_mchid").getAsString()).isEqualTo("1900000109");
    assertThat(json.get("fapiao_apply_id").getAsString()).isEqualTo("apply_20260718_001");
    assertThat(json.getAsJsonObject("buyer_information").get("taxpayer_id").getAsString())
      .isEqualTo("91440300TEST00001");
    assertThat(json.getAsJsonObject("fapiao_information").getAsJsonArray("items").get(0)
      .getAsJsonObject().get("tax_code").getAsString()).isEqualTo("3010101020203000000");
    assertThat(json.getAsJsonObject("fapiao_information").getAsJsonArray("transaction_information").get(0)
      .getAsJsonObject().get("out_trade_no").getAsString()).isEqualTo("order_20260718_001");
  }
}
