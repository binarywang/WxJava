package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link PassengerTransportInvoiceRequest} 单元测试。
 */
public class PassengerTransportInvoiceRequestTest {

  @Test
  public void shouldSerializePassengerTransportInvoiceRequestUsingWechatPayFieldNames() {
    PassengerTransportInvoiceRequest request = new PassengerTransportInvoiceRequest();
    request.setSubMchid("1900000109");
    request.setFapiaoApplyId("apply_20260827_001");

    BuyerInformation buyer = new BuyerInformation();
    buyer.setType("INDIVIDUAL");
    buyer.setName("示例旅客");
    buyer.setPhone("encrypted-phone");
    buyer.setEmail("encrypted-email");
    buyer.setAmount(1000);
    request.setBuyerInformation(buyer);

    PassengerTransportInvoiceRequest.PassengerInformation passenger =
      new PassengerTransportInvoiceRequest.PassengerInformation();
    passenger.setName("张**");
    passenger.setCertificateType("IDENTITY_CARD");
    passenger.setCertificateNumber("encrypted-certificate-number");
    passenger.setDepartureDate("2026-08-27T10:00:00+08:00");
    passenger.setDeparturePlace("重庆市渝北区示例出发地");
    passenger.setDestination("重庆市两江新区示例目的地");
    passenger.setTransportationType("SHIP");
    passenger.setTransportationClasses("SHIP_FIRST_CLASS_CABIN");

    PassengerTransportInvoiceRequest.InvoiceItem item = new PassengerTransportInvoiceRequest.InvoiceItem();
    item.setTaxCode("3010101020100000000");
    item.setGoodsName("旅客运输服务");
    item.setQuantity(2200000000L);
    item.setTotalAmount(1000L);
    item.setTaxRate(300L);
    item.setDiscount(false);
    item.setPassengerInformation(passenger);

    PassengerTransportInvoiceRequest.TransactionInformation transaction =
      new PassengerTransportInvoiceRequest.TransactionInformation();
    transaction.setPayChannel("WECHAT_PAY");
    transaction.setOutTradeNo("order_20260827_001");
    transaction.setAmount(1000L);

    PassengerTransportInvoiceRequest.FapiaoInformation fapiao =
      new PassengerTransportInvoiceRequest.FapiaoInformation();
    fapiao.setFapiaoId("invoice_20260827_001");
    fapiao.setTotalAmount(1000L);
    fapiao.setItems(Collections.singletonList(item));
    fapiao.setBillingPersonId("billing_person_001");
    fapiao.setFapiaoBillType("COMM_FAPIAO");
    fapiao.setTransactionInformation(Collections.singletonList(transaction));
    request.setFapiaoInformation(fapiao);

    JsonObject json = new Gson().toJsonTree(request).getAsJsonObject();
    JsonObject passengerJson = json.getAsJsonObject("fapiao_information")
      .getAsJsonArray("items").get(0).getAsJsonObject()
      .getAsJsonObject("passenger_information");

    assertThat(json.get("sub_mchid").getAsString()).isEqualTo("1900000109");
    assertThat(json.get("fapiao_apply_id").getAsString()).isEqualTo("apply_20260827_001");
    assertThat(json.getAsJsonObject("buyer_information").get("phone").getAsString())
      .isEqualTo("encrypted-phone");
    assertThat(json.getAsJsonObject("fapiao_information").getAsJsonArray("items").get(0)
      .getAsJsonObject().get("quantity").getAsLong()).isEqualTo(2200000000L);
    assertThat(passengerJson.get("name").getAsString()).isEqualTo("张**");
    assertThat(passengerJson.get("certificate_type").getAsString()).isEqualTo("IDENTITY_CARD");
    assertThat(passengerJson.get("certificate_number").getAsString())
      .isEqualTo("encrypted-certificate-number");
    assertThat(passengerJson.get("departure_date").getAsString())
      .isEqualTo("2026-08-27T10:00:00+08:00");
    assertThat(passengerJson.get("departure_place").getAsString())
      .isEqualTo("重庆市渝北区示例出发地");
    assertThat(passengerJson.get("destination").getAsString())
      .isEqualTo("重庆市两江新区示例目的地");
    assertThat(passengerJson.get("transportation_type").getAsString()).isEqualTo("SHIP");
    assertThat(passengerJson.get("transportation_classes").getAsString())
      .isEqualTo("SHIP_FIRST_CLASS_CABIN");
    assertThat(json.getAsJsonObject("fapiao_information").getAsJsonArray("transaction_information").get(0)
      .getAsJsonObject().get("out_trade_no").getAsString()).isEqualTo("order_20260827_001");
  }
}
