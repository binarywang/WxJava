package com.github.binarywang.wxpay.config;

import static org.testng.Assert.assertEquals;

import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryV3Result;
import com.github.binarywang.wxpay.constant.WxPayErrorCode;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.CustomizedApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/ifcute">dagewang</a>
 */
@Slf4j
@Test
@Guice(modules = CustomizedApiTestModule.class)
public class CustomizedWxPayConfigTest {

  @Inject
  private WxPayService wxPayService;

  public void testCustomizerHttpClient() {
    try {
      wxPayService.queryOrder("a", null);
    } catch (WxPayException e) {
      // ignore
      e.printStackTrace();
    }
  }

  public void testCustomizerV3HttpClient() {
    try {
      WxPayOrderQueryV3Result result = wxPayService.queryOrderV3("a", null);
    } catch (WxPayException e) {
      assertEquals(e.getErrCode(), WxPayErrorCode.RefundQuery.PARAM_ERROR);
    }
  }
}
