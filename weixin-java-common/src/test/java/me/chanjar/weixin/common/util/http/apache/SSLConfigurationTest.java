package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 测试SSL配置，特别是TLS协议版本配置
 * Test SSL configuration, especially TLS protocol version configuration
 */
public class SSLConfigurationTest {

  @Test
  public void testDefaultTLSProtocols() throws Exception {
    // Create a new instance to check the default configuration
    Class<?> builderClass = DefaultApacheHttpClientBuilder.class;
    Object builder = builderClass.getDeclaredMethod("get").invoke(null);
    
    // 验证默认支持的TLS协议版本包含现代版本
    Field supportedProtocolsField = builderClass.getDeclaredField("supportedProtocols");
    supportedProtocolsField.setAccessible(true);
    String[] supportedProtocols = (String[]) supportedProtocolsField.get(builder);
    
    List<String> protocolList = Arrays.asList(supportedProtocols);
    
    System.out.println("Default supported TLS protocols: " + Arrays.toString(supportedProtocols));
    
    // 主要验证：应该支持TLS 1.2和/或1.3 (现代安全版本)
    // Main validation: Should support TLS 1.2 and/or 1.3 (modern secure versions)
    Assert.assertTrue(protocolList.contains("TLSv1.2"), "Should support TLS 1.2");
    Assert.assertTrue(protocolList.contains("TLSv1.3"), "Should support TLS 1.3");
    
    // 验证不再是只有TLS 1.0 (这是导致原问题的根本原因)
    // Verify it's no longer just TLS 1.0 (which was the root cause of the original issue)
    Assert.assertTrue(protocolList.size() > 0, "Should support at least one TLS version");
    boolean hasModernTLS = protocolList.contains("TLSv1.2") || protocolList.contains("TLSv1.3");
    Assert.assertTrue(hasModernTLS, "Should support at least one modern TLS version (1.2 or 1.3)");
    
    // 验证不是原来的老旧配置 (只有 "TLSv1")
    // Verify it's not the old configuration (only "TLSv1")
    boolean isOldConfig = protocolList.size() == 1 && protocolList.contains("TLSv1");
    Assert.assertFalse(isOldConfig, "Should not be the old configuration that only supported TLS 1.0");
  }

  @Test
  public void testCustomTLSProtocols() throws Exception {
    // Test that we can set custom TLS protocols
    String[] customProtocols = {"TLSv1.2", "TLSv1.3"};
    
    // Create a new builder instance using reflection to avoid singleton issues in testing
    Class<?> builderClass = DefaultApacheHttpClientBuilder.class;
    Constructor<?> constructor = builderClass.getDeclaredConstructor();
    constructor.setAccessible(true);
    Object builder = constructor.newInstance();
    
    // Set custom protocols
    builderClass.getMethod("supportedProtocols", String[].class).invoke(builder, (Object) customProtocols);
    
    Field supportedProtocolsField = builderClass.getDeclaredField("supportedProtocols");
    supportedProtocolsField.setAccessible(true);
    String[] actualProtocols = (String[]) supportedProtocolsField.get(builder);
    
    Assert.assertEquals(actualProtocols, customProtocols, "Custom protocols should be set correctly");
    
    System.out.println("Custom supported TLS protocols: " + Arrays.toString(actualProtocols));
  }

  @Test
  public void testSSLContextCreation() throws Exception {
    DefaultApacheHttpClientBuilder builder = DefaultApacheHttpClientBuilder.get();
    
    // 构建HTTP客户端以验证SSL工厂是否正确创建
    CloseableHttpClient client = builder.build();
    Assert.assertNotNull(client, "HTTP client should be created successfully");
    
    // 验证SSL上下文支持现代TLS协议
    SSLContext sslContext = SSLContext.getDefault();
    SSLSocketFactory socketFactory = sslContext.getSocketFactory();
    
    // 创建一个SSL socket来检查支持的协议
    try (SSLSocket socket = (SSLSocket) socketFactory.createSocket()) {
      String[] supportedProtocols = socket.getSupportedProtocols();
      List<String> supportedList = Arrays.asList(supportedProtocols);
      
      // JVM应该支持TLS 1.2（在JDK 8+中默认可用）
      Assert.assertTrue(supportedList.contains("TLSv1.2"), 
          "JVM should support TLS 1.2. Supported protocols: " + Arrays.toString(supportedProtocols));
      
      System.out.println("JVM supported TLS protocols: " + Arrays.toString(supportedProtocols));
    }
    
    client.close();
  }

  @Test
  public void testBuilderChaining() {
    DefaultApacheHttpClientBuilder builder = DefaultApacheHttpClientBuilder.get();
    
    // 测试方法链调用
    ApacheHttpClientBuilder result = builder
        .supportedProtocols(new String[]{"TLSv1.2", "TLSv1.3"})
        .httpProxyHost("proxy.example.com")
        .httpProxyPort(8080);
    
    Assert.assertSame(result, builder, "Builder methods should return the same instance for method chaining");
  }
}