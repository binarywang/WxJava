package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WxPayRefundNotifyV3ResultTest {

  private static final Gson GSON = new Gson();

  @Test
  public void shouldDeserializeOfficialAmountFields() {
    String json = "{"
      + "\"mchid\":\"1900000100\","
      + "\"out_trade_no\":\"1217752501201407033233368018\","
      + "\"transaction_id\":\"4200000000000000000000000000\","
      + "\"out_refund_no\":\"1217752501201407033233368019\","
      + "\"refund_id\":\"50000000382019052709732678859\","
      + "\"refund_status\":\"SUCCESS\","
      + "\"success_time\":\"2020-12-01T12:00:00+08:00\","
      + "\"user_received_account\":\"支付用户零钱\","
      + "\"amount\":{"
      + "\"refund_fee\":10,"
      + "\"settlement_refund\":9,"
      + "\"total\":100,"
      + "\"currency\":\"CNY\","
      + "\"payer_total\":90,"
      + "\"payer_refund\":10,"
      + "\"settlement_total\":90,"
      + "\"discount_refund\":1,"
      + "\"from\":[{\"account\":\"AVAILABLE\",\"amount\":10}]"
      + "}"
      + "}";

    WxPayRefundNotifyV3Result.DecryptNotifyResult result =
      GSON.fromJson(json, WxPayRefundNotifyV3Result.DecryptNotifyResult.class);

    assertThat(result.getAmount().getRefundFee()).isEqualTo(10);
    assertThat(result.getAmount().getRefund()).isEqualTo(10);
    assertThat(result.getAmount().getSettlementRefund()).isEqualTo(9);
    assertThat(result.getAmount().getTotal()).isEqualTo(100);
    assertThat(result.getAmount().getCurrency()).isEqualTo("CNY");
    assertThat(result.getAmount().getPayerTotal()).isEqualTo(90);
    assertThat(result.getAmount().getPayerRefund()).isEqualTo(10);
    assertThat(result.getAmount().getSettlementTotal()).isEqualTo(90);
    assertThat(result.getAmount().getDiscountRefund()).isEqualTo(1);
    assertThat(result.getAmount().getFrom()).hasSize(1);
    assertThat(result.getAmount().getFrom().get(0).getAccount()).isEqualTo("AVAILABLE");
    assertThat(result.getAmount().getFrom().get(0).getAmount()).isEqualTo(10);
  }

  @Test
  public void shouldKeepBackwardCompatibilityForRefundAlias() {
    WxPayRefundNotifyV3Result.Amount amount =
      GSON.fromJson("{\"refund\":88}", WxPayRefundNotifyV3Result.Amount.class);

    assertThat(amount.getRefundFee()).isEqualTo(88);
    assertThat(amount.getRefund()).isEqualTo(88);
  }
}
