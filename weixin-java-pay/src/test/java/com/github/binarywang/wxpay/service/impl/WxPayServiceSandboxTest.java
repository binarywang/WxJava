package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.config.WxPayConfig;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

public class WxPayServiceSandboxTest {

  @Test
  public void shouldUseV2SandboxUrlWhenV3KeyIsConfigured() {
    WxPayConfig config = new WxPayConfig();
    config.setApiHostUrl("https://api.mch.weixin.qq.com");
    config.setApiHostUrlPath("/payment-proxy");
    config.setApiV3Key("v3-key");
    config.setUseSandboxEnv(true);

    WxPayServiceImpl service = new WxPayServiceImpl();
    service.setConfig(config);

    assertEquals(service.getPayBaseUrl(), "https://api.mch.weixin.qq.com/payment-proxy/xdc/apiv2sandbox");
  }

  @Test
  public void shouldRejectV3RequestWhenSandboxIsEnabled() {
    WxPayConfig config = new WxPayConfig();
    config.setUseSandboxEnv(true);

    WxPayServiceImpl service = new WxPayServiceImpl();
    service.setConfig(config);

    WxRuntimeException exception = expectThrows(WxRuntimeException.class,
      () -> service.postV3("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi", "{}"));

    assertEquals(exception.getMessage(), "微信支付V3 目前不支持沙箱模式！");
  }
}
