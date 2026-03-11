package com.github.binarywang.wxpay.bean.entpay;

import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * .
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-08-18
 */
public class EntPayRequestTest {

  @Test
  public void testToString() {
    System.out.println(EntPayRequest.newBuilder().mchId("123").build().toString());
  }

  /**
   * 测试 brandId 为 null 时，getSignParams() 不抛出 NullPointerException.
   */
  @Test
  public void testGetSignParamsWithNullBrandId() {
    EntPayRequest request = EntPayRequest.newBuilder()
      .mchId("123")
      .amount(100)
      .brandId(null)
      .build();
    Map<String, String> params = request.getSignParams();
    assertThat(params).doesNotContainKey("brand_id");
  }

  /**
   * 测试 brandId 不为 null 时，getSignParams() 正确包含 brand_id.
   */
  @Test
  public void testGetSignParamsWithNonNullBrandId() {
    EntPayRequest request = EntPayRequest.newBuilder()
      .mchId("123")
      .amount(100)
      .brandId(1234)
      .build();
    Map<String, String> params = request.getSignParams();
    assertThat(params).containsEntry("brand_id", "1234");
  }
}
