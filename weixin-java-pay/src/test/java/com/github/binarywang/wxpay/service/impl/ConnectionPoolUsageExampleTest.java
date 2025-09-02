package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.config.WxPayConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 演示连接池功能的示例测试
 */
public class ConnectionPoolUsageExampleTest {

  @Test
  public void demonstrateConnectionPoolUsage() throws Exception {
    // 1. 创建配置并设置连接池参数
    WxPayConfig config = new WxPayConfig();
    config.setAppId("wx123456789");
    config.setMchId("1234567890");
    config.setMchKey("32位商户密钥32位商户密钥32位商户密钥");
    
    // 设置连接池参数（可选，有默认值）
    config.setMaxConnTotal(50);  // 最大连接数，默认20
    config.setMaxConnPerRoute(20); // 每个路由最大连接数，默认10
    
    // 2. 初始化连接池
    CloseableHttpClient pooledClient = config.initHttpClient();
    Assert.assertNotNull(pooledClient);
    
    // 3. 创建支付服务实例
    WxPayServiceApacheHttpImpl payService = new WxPayServiceApacheHttpImpl();
    payService.setConfig(config);
    
    // 4. 现在所有的HTTP请求都会使用连接池
    // 对于非SSL请求，会复用同一个HttpClient实例
    CloseableHttpClient client1 = payService.createHttpClient(false);
    CloseableHttpClient client2 = payService.createHttpClient(false);
    Assert.assertSame(client1, client2, "非SSL请求应该复用同一个客户端实例");
    
    // 对于SSL请求，也会复用同一个SSL HttpClient实例（需要配置证书后）
    System.out.println("连接池配置成功！");
    System.out.println("最大连接数：" + config.getMaxConnTotal());
    System.out.println("每路由最大连接数：" + config.getMaxConnPerRoute());
  }
  
  @Test
  public void demonstrateDefaultConfiguration() throws Exception {
    // 使用默认配置的示例
    WxPayConfig config = new WxPayConfig();
    config.setAppId("wx123456789");
    config.setMchId("1234567890");
    config.setMchKey("32位商户密钥32位商户密钥32位商户密钥");
    
    // 不设置连接池参数，使用默认值
    CloseableHttpClient client = config.initHttpClient();
    Assert.assertNotNull(client);
    
    // 验证默认配置
    Assert.assertEquals(config.getMaxConnTotal(), 20, "默认最大连接数应该是20");
    Assert.assertEquals(config.getMaxConnPerRoute(), 10, "默认每路由最大连接数应该是10");
  }
}