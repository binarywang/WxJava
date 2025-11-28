package com.github.binarywang.wxpay.util;

import org.testng.annotations.*;

import com.google.common.base.Splitter;

import static com.github.binarywang.wxpay.constant.WxPayConstants.SignType.HMAC_SHA256;
import static org.testng.Assert.*;

/**
 * <pre>
 * 测试中使用的测试数据参考的是官方文档，地址：
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
 *  Created by BinaryWang on 2017/9/2.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class SignUtilsTest {
  /**
   * Test create sign.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateSign() throws Exception {
    String signKey = "192006250b4c09247ec02edce69f6a2d";
    String message = "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
    assertEquals(SignUtils.createSign((Splitter.on("&").withKeyValueSeparator("=").split(message)), null, signKey, null),
      "9A0A8659F005D6984697E2CA0A9CF3B7");
  }

  /**
   * Test create sign hmacsha 256.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreateSign_HMACSHA256() throws Exception {
    String signKey = "192006250b4c09247ec02edce69f6a2d";
    final String message = "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
    String sign = SignUtils.createSign(Splitter.on("&").withKeyValueSeparator("=").split(message),
      HMAC_SHA256, signKey, null);
    assertEquals(sign, "6A9AE1657590FD6257D693A078E1C3E4BB6BA4DC30B23E0EE2496E54170DACD6");
  }

  /**
   * Test check sign.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCheckSign() throws Exception {
  }

}
