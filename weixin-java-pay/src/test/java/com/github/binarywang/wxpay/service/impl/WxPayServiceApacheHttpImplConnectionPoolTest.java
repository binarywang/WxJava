package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 测试WxPayServiceApacheHttpImpl的连接池功能
 */
public class WxPayServiceApacheHttpImplConnectionPoolTest {

  @Test
  public void testHttpClientConnectionPool() throws Exception {
    WxPayConfig config = new WxPayConfig();
    config.setAppId("test-app-id");
    config.setMchId("test-mch-id");
    config.setMchKey("test-mch-key");
    
    // 测试初始化连接池
    CloseableHttpClient httpClient1 = config.initHttpClient();
    Assert.assertNotNull(httpClient1, "HttpClient should not be null");
    
    // 再次获取，应该返回同一个实例
    CloseableHttpClient httpClient2 = config.getHttpClient();
    Assert.assertSame(httpClient1, httpClient2, "Should return the same HttpClient instance");
    
    // 验证连接池配置
    WxPayServiceApacheHttpImpl service = new WxPayServiceApacheHttpImpl();
    service.setConfig(config);
    
    // 测试不使用SSL的情况下应该使用连接池
    CloseableHttpClient clientForNonSSL = service.createHttpClient(false);
    Assert.assertSame(httpClient1, clientForNonSSL, "Should use pooled client for non-SSL requests");
  }
  
  @Test
  public void testSslHttpClientConnectionPool() throws Exception {
    WxPayConfig config = new WxPayConfig();
    config.setAppId("test-app-id");
    config.setMchId("test-mch-id");
    config.setMchKey("test-mch-key");
    
    // 为了测试SSL客户端，我们需要设置一些基本的SSL配置
    // 注意：在实际使用中需要提供真实的证书
    try {
      CloseableHttpClient sslClient1 = config.initSslHttpClient();
      Assert.assertNotNull(sslClient1, "SSL HttpClient should not be null");
      
      CloseableHttpClient sslClient2 = config.getSslHttpClient();
      Assert.assertSame(sslClient1, sslClient2, "Should return the same SSL HttpClient instance");
      
      WxPayServiceApacheHttpImpl service = new WxPayServiceApacheHttpImpl();
      service.setConfig(config);
      
      // 测试使用SSL的情况下应该使用SSL连接池
      CloseableHttpClient clientForSSL = service.createHttpClient(true);
      Assert.assertSame(sslClient1, clientForSSL, "Should use pooled SSL client for SSL requests");
      
    } catch (WxPayException e) {
      // SSL初始化失败是预期的，因为我们没有提供真实的证书
      // 这里主要是测试代码路径是否正确
      Assert.assertTrue(e.getMessage().contains("证书") || e.getMessage().contains("商户号"), 
          "Should fail with certificate or merchant ID related error");
    }
  }
  
  @Test
  public void testConnectionPoolConfiguration() throws Exception {
    WxPayConfig config = new WxPayConfig();
    config.setAppId("test-app-id");
    config.setMchId("test-mch-id");
    config.setMchKey("test-mch-key");
    config.setMaxConnTotal(50);
    config.setMaxConnPerRoute(20);
    
    CloseableHttpClient httpClient = config.initHttpClient();
    Assert.assertNotNull(httpClient, "HttpClient should not be null");
    
    // 验证配置值是否正确设置
    Assert.assertEquals(config.getMaxConnTotal(), 50, "Max total connections should be 50");
    Assert.assertEquals(config.getMaxConnPerRoute(), 20, "Max connections per route should be 20");
  }
}