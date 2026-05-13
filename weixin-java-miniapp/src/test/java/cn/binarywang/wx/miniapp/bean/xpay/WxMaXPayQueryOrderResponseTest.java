package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * 验证 {@link WxMaXPayQueryOrderResponse} Gson 反序列化正确性的单元测试。
 *
 * <p>修复说明：{@link WxMaXPayQueryOrderResponse.OrderInfo} 中 {@code leftFee} 和 {@code wxOrderId}
 * 字段的 {@code @SerializedName} 使用了驼峰命名（{@code "leftFee"}、{@code "wxOrderId"}），
 * 而微信小程序接口实际返回的 JSON key 为下划线分割形式（{@code "left_fee"}、{@code "wx_order_id"}），
 * 导致这两个字段无法正确反序列化。
 *
 * @author GitHub Copilot
 */
public class WxMaXPayQueryOrderResponseTest {

  private static final String JSON_WITH_SNAKE_CASE_KEYS =
    "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"order\":{"
      + "\"order_id\":\"order123\","
      + "\"status\":1,"
      + "\"left_fee\":500,"
      + "\"wx_order_id\":\"wx_inner_order_456\","
      + "\"sett_state\":4"
      + "}"
      + "}";

  /**
   * 验证 left_fee（下划线形式）能正确反序列化到 leftFee 字段。
   */
  @Test
  public void testLeftFeeDeserializedFromSnakeCaseKey() {
    WxMaXPayQueryOrderResponse response = WxMaGsonBuilder.create()
      .fromJson(JSON_WITH_SNAKE_CASE_KEYS, WxMaXPayQueryOrderResponse.class);

    assertNotNull(response.getOrder(), "order 字段不应为 null");
    assertEquals(response.getOrder().getLeftFee(), Long.valueOf(500L),
      "left_fee 应正确反序列化为 leftFee 字段");
  }

  /**
   * 验证 wx_order_id（下划线形式）能正确反序列化到 wxOrderId 字段。
   */
  @Test
  public void testWxOrderIdDeserializedFromSnakeCaseKey() {
    WxMaXPayQueryOrderResponse response = WxMaGsonBuilder.create()
      .fromJson(JSON_WITH_SNAKE_CASE_KEYS, WxMaXPayQueryOrderResponse.class);

    assertNotNull(response.getOrder(), "order 字段不应为 null");
    assertEquals(response.getOrder().getWxOrderId(), "wx_inner_order_456",
      "wx_order_id 应正确反序列化为 wxOrderId 字段");
  }

  /**
   * 验证 sett_state = 4（苹果iOS订单）能正确反序列化。
   */
  @Test
  public void testSettStateAppleOrderValue() {
    WxMaXPayQueryOrderResponse response = WxMaGsonBuilder.create()
      .fromJson(JSON_WITH_SNAKE_CASE_KEYS, WxMaXPayQueryOrderResponse.class);

    assertNotNull(response.getOrder(), "order 字段不应为 null");
    assertEquals(response.getOrder().getSettState(), Integer.valueOf(4),
      "sett_state = 4 表示苹果iOS订单，Apple公司结算中");
  }
}
