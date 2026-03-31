package com.binarywang.spring.starter.wxjava.pay;

import com.binarywang.spring.starter.wxjava.pay.config.WxPayMultiAutoConfiguration;
import com.binarywang.spring.starter.wxjava.pay.properties.WxPayMultiProperties;
import com.binarywang.spring.starter.wxjava.pay.properties.WxPaySingleProperties;
import com.binarywang.spring.starter.wxjava.pay.service.WxPayMultiServices;
import com.github.binarywang.wxpay.service.WxPayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 微信支付多公众号关联配置测试.
 *
 * @author Binary Wang
 */
@SpringBootTest(classes = {WxPayMultiAutoConfiguration.class, WxPayMultiServicesTest.TestApplication.class})
@TestPropertySource(properties = {
  "wx.pay.configs.app1.app-id=wx1111111111111111",
  "wx.pay.configs.app1.mch-id=1111111111",
  "wx.pay.configs.app1.mch-key=11111111111111111111111111111111",
  "wx.pay.configs.app1.notify-url=https://example.com/pay/notify",
  "wx.pay.configs.app2.app-id=wx2222222222222222",
  "wx.pay.configs.app2.mch-id=2222222222",
  "wx.pay.configs.app2.apiv3-key=22222222222222222222222222222222",
  "wx.pay.configs.app2.cert-serial-no=2222222222222222",
  "wx.pay.configs.app2.private-key-path=classpath:cert/apiclient_key.pem",
  "wx.pay.configs.app2.private-cert-path=classpath:cert/apiclient_cert.pem"
})
public class WxPayMultiServicesTest {

  @Autowired
  private WxPayMultiServices wxPayMultiServices;

  @Autowired
  private WxPayMultiProperties wxPayMultiProperties;

  @Test
  public void testConfiguration() {
    assertNotNull(wxPayMultiServices, "WxPayMultiServices should be autowired");
    assertNotNull(wxPayMultiProperties, "WxPayMultiProperties should be autowired");

    // 验证配置正确加载
    assertEquals(2, wxPayMultiProperties.getConfigs().size(), "Should have 2 configurations");

    WxPaySingleProperties app1Config = wxPayMultiProperties.getConfigs().get("app1");
    assertNotNull(app1Config, "app1 configuration should exist");
    assertEquals("wx1111111111111111", app1Config.getAppId());
    assertEquals("1111111111", app1Config.getMchId());
    assertEquals("11111111111111111111111111111111", app1Config.getMchKey());

    WxPaySingleProperties app2Config = wxPayMultiProperties.getConfigs().get("app2");
    assertNotNull(app2Config, "app2 configuration should exist");
    assertEquals("wx2222222222222222", app2Config.getAppId());
    assertEquals("2222222222", app2Config.getMchId());
    assertEquals("22222222222222222222222222222222", app2Config.getApiv3Key());
  }

  @Test
  public void testGetWxPayService() {
    WxPayService app1Service = wxPayMultiServices.getWxPayService("app1");
    assertNotNull(app1Service, "Should get WxPayService for app1");
    assertEquals("wx1111111111111111", app1Service.getConfig().getAppId());
    assertEquals("1111111111", app1Service.getConfig().getMchId());

    WxPayService app2Service = wxPayMultiServices.getWxPayService("app2");
    assertNotNull(app2Service, "Should get WxPayService for app2");
    assertEquals("wx2222222222222222", app2Service.getConfig().getAppId());
    assertEquals("2222222222", app2Service.getConfig().getMchId());

    // 测试相同key返回相同实例
    WxPayService app1ServiceAgain = wxPayMultiServices.getWxPayService("app1");
    assertSame(app1Service, app1ServiceAgain, "Should return the same instance for the same key");
  }

  @Test
  public void testGetWxPayServiceWithInvalidKey() {
    WxPayService service = wxPayMultiServices.getWxPayService("nonexistent");
    assertNull(service, "Should return null for non-existent key");
  }

  @Test
  public void testRemoveWxPayService() {
    // 首先获取一个服务实例
    WxPayService app1Service = wxPayMultiServices.getWxPayService("app1");
    assertNotNull(app1Service, "Should get WxPayService for app1");

    // 移除服务
    wxPayMultiServices.removeWxPayService("app1");

    // 再次获取时应该创建新实例
    WxPayService app1ServiceNew = wxPayMultiServices.getWxPayService("app1");
    assertNotNull(app1ServiceNew, "Should get new WxPayService for app1");
    assertNotSame(app1Service, app1ServiceNew, "Should return a new instance after removal");
  }

  @SpringBootApplication
  static class TestApplication {
  }
}
