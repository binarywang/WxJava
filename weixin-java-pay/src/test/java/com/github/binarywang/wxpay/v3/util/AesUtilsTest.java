package com.github.binarywang.wxpay.v3.util;

import me.chanjar.weixin.common.error.WxRuntimeException;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 测试 {@link AesUtils}.
 */
public class AesUtilsTest {

  @Test
  public void testHmacSha256() {
    assertThat(AesUtils.HMACSHA256("data", "key")).isNotEmpty();
  }

  @Test
  public void testHmacSha256ThrowsOnInvalidKey() {
    // 空密钥无法构造 SecretKeySpec，此前该场景会被静默吞掉并返回 null
    assertThatThrownBy(() -> AesUtils.HMACSHA256("data", ""))
      .isInstanceOf(WxRuntimeException.class);
  }
}
