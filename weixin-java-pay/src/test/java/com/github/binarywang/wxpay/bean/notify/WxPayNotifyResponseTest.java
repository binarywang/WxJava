package com.github.binarywang.wxpay.bean.notify;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WxPayNotifyResponse 测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-06-30
 */
@Slf4j
public class WxPayNotifyResponseTest {

  /**
   * V2版本
   */
  @Test
  public void testSuccess() {
    final String result = WxPayNotifyResponse.success("OK");
    assertThat(result).isEqualTo("<xml>" +
      "<return_code><![CDATA[SUCCESS]]></return_code>" +
      "<return_msg><![CDATA[OK]]></return_msg>" +
      "</xml>");
  }

  @Test
  public void testSuccessResp() {
    final String result = WxPayNotifyResponse.successResp("OK");
    assertThat(result).isEqualTo("<xml>" +
      "<return_code><![CDATA[SUCCESS]]></return_code>" +
      "<return_msg><![CDATA[OK]]></return_msg>" +
      "</xml>");
  }

  @Test
  public void testFailResp() {
    final String result = WxPayNotifyResponse.failResp("500");
    assertThat(result).isEqualTo("<xml>" +
      "<return_code><![CDATA[FAIL]]></return_code>" +
      "<return_msg><![CDATA[500]]></return_msg>" +
      "</xml>");
  }

  /**
   * V3版本
   * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_5.shtml
   */
  @Test
  public void testV3Fail() {
    final String result = WxPayNotifyV3Response.fail("失败");
    log.info(result);
    assertThat(result).isNotEmpty();
  }

  @Test
  public void testV3Success() {
    final String result = WxPayNotifyV3Response.success("成功");
    log.info(result);
    assertThat(result).isNotEmpty();
  }

}
