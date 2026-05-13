package com.github.binarywang.wxpay.config;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayConfigTest {
  private final WxPayConfig payConfig = new WxPayConfig();

  @Test
  public void testInitSSLContext_classpath() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyPath("classpath:/dlt.p12");
    payConfig.initSSLContext();
  }

  @Test
  public void testInitSSLContext_http() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyPath("https://www.baidu.com");
    payConfig.initSSLContext();
  }

  @Test
  public void testInitSSLContext() throws Exception {
    this.testInitSSLContext_classpath();
    this.testInitSSLContext_http();
  }

  @Test
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public void testHashCode() {
    payConfig.hashCode();
  }

  @Test
  public void testApiHostUrlPath() {
    payConfig.setApiHostUrl("http://10.0.0.1:3128/");
    payConfig.setApiHostUrlPath("api-weixin/");
    assertEquals(payConfig.getApiHostUrl(), "http://10.0.0.1:3128");
    assertEquals(payConfig.getApiHostUrlPath(), "/api-weixin");
    assertEquals(payConfig.getApiHostWithPathPrefix(), "http://10.0.0.1:3128/api-weixin");
  }

  @Test
  public void testInitSSLContext_base64() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyString("MIIKmgIBAzCCCmQGCS...");
    payConfig.initSSLContext();
  }

}
