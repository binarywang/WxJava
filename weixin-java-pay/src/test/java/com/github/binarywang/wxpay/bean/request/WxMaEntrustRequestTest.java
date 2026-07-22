package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.util.SignUtils;
import com.github.binarywang.wxpay.util.XmlConfig;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link WxMaEntrustRequest}.
 */
public class WxMaEntrustRequestTest {

  @Test
  public void versionIsExcludedFromPayloadAndSignature() throws Exception {
    WxMaEntrustRequest request = WxMaEntrustRequest.newBuilder()
      .planId("plan-id")
      .contractCode("contract-code")
      .requestSerial(1L)
      .contractDisplayAccount("account")
      .notifyUrl("https://example.com/notify")
      .timestamp("1710000000")
      .version("1.0")
      .build();
    WxPayConfig config = new WxPayConfig();
    config.setAppId("wx-app-id");
    config.setMchId("mch-id");
    config.setMchKey("mch-key");

    request.checkAndSign(config);

    assertThat(request.toString()).doesNotContain("\"version\"");
    assertThat(request.toXML()).doesNotContain("<version>");
    boolean fastMode = XmlConfig.fastMode;
    try {
      XmlConfig.fastMode = true;
      assertThat(request.toXML()).doesNotContain("<version>");
    } finally {
      XmlConfig.fastMode = fastMode;
    }
    assertThat(request.getSign()).isEqualTo(SignUtils.createSign(
      request, WxPayConstants.SignType.MD5, config.getMchKey(), new String[]{"version"}));
  }
}
