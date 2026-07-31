package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.hc.DefaultHttpComponentsClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;

/**
 * 验证默认情况下不会跳过服务器端证书校验，避免中间人攻击.
 */
public class ServerCertificateVerificationTest {

  @Test
  public void testApacheBuilderVerifiesCertificateByDefault() throws Exception {
    Constructor<DefaultApacheHttpClientBuilder> constructor =
      DefaultApacheHttpClientBuilder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    DefaultApacheHttpClientBuilder builder = constructor.newInstance();

    Assert.assertFalse(builder.isSkipServerCertificateVerification(),
      "默认应校验服务器端证书");

    builder.setSkipServerCertificateVerification(true);
    Assert.assertTrue(builder.isSkipServerCertificateVerification(),
      "特殊调试场景下应允许显式跳过证书校验");
  }

  @Test
  public void testHttpComponentsBuilderVerifiesCertificateByDefault() throws Exception {
    Constructor<DefaultHttpComponentsClientBuilder> constructor =
      DefaultHttpComponentsClientBuilder.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    DefaultHttpComponentsClientBuilder builder = constructor.newInstance();

    Assert.assertFalse(builder.isSkipServerCertificateVerification(),
      "默认应校验服务器端证书");

    builder.setSkipServerCertificateVerification(true);
    Assert.assertTrue(builder.isSkipServerCertificateVerification(),
      "特殊调试场景下应允许显式跳过证书校验");
  }
}
