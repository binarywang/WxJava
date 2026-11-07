package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Test cases for private key format handling in WxPayConfig
 */
public class WxPayConfigPrivateKeyTest {

  @Test
  public void testPrivateKeyStringFormat_PemFormat() throws Exception {
    java.security.KeyPairGenerator generator = java.security.KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    java.security.KeyPair pair = generator.generateKeyPair();
    java.util.Base64.Encoder encoder = java.util.Base64.getMimeEncoder(64, new byte[]{'\n'});
    WxPayConfig config = new WxPayConfig();
    config.setMchId("1234567890");
    config.setApiV3Key("test-api-v3-key-32-characters-long");
    config.setCertSerialNo("test-serial-number");
    config.setPrivateKeyString("-----BEGIN PRIVATE KEY-----\n"
      + encoder.encodeToString(pair.getPrivate().getEncoded()) + "\n-----END PRIVATE KEY-----");
    config.setFullPublicKeyModel(true);
    config.setPublicKeyId("PUB_KEY_ID_TEST");
    config.setPublicKeyString("-----BEGIN PUBLIC KEY-----\n"
      + encoder.encodeToString(pair.getPublic().getEncoded()) + "\n-----END PUBLIC KEY-----");
    try (org.apache.http.impl.client.CloseableHttpClient client = config.initApiV3HttpClient()) {
      assertNotNull(client);
    }
  }

  @Test 
  public void testPrivateKeyStringFormat_EmptyString() {
    WxPayConfig config = new WxPayConfig();
    
    // Test with empty string - should not cause format errors
    config.setPrivateKeyString("");
    
    // This should handle empty strings gracefully
    // No assertion needed, just ensuring no exceptions during object creation
    assertNotNull(config);
  }

  @Test
  public void testPrivateKeyStringFormat_NullString() {
    WxPayConfig config = new WxPayConfig();
    
    // Test with null string - should not cause format errors
    config.setPrivateKeyString(null);
    
    // This should handle null strings gracefully
    assertNotNull(config);
  }

  @Test
  public void testPrivateCertStringFormat_PemFormat() {
    WxPayConfig config = new WxPayConfig();
    
    // Set minimal required configuration 
    config.setMchId("1234567890");
    config.setApiV3Key("test-api-v3-key-32-characters-long");
    
    // Test with PEM format certificate string that would previously fail
    String pemCert = "-----BEGIN CERTIFICATE-----\n" +
                    "MIICdTCCAd4CAQAwDQYJKoZIhvcNAQEFBQAwRTELMAkGA1UEBhMCQVUxEzARBgNV\n" +
                    "BAsKClRlc3QgQ2VydCBEYXRhMRswGQYDVQQDDBJUZXN0IENlcnRpZmljYXRlQ0Ew\n" +
                    "-----END CERTIFICATE-----";
    
    config.setPrivateCertString(pemCert);
    
    // This should not throw a format parsing exception immediately
    // The actual certificate validation will happen during HTTP client initialization
    // but at least the format parsing should not fail
    
    try {
      // Try to initialize API V3 HTTP client - this might fail for other reasons 
      // (like invalid cert content) but should not fail due to format parsing
      config.initApiV3HttpClient();
      // If we get here without Base64 decoding issues, the format detection worked
    } catch (Exception e) {
      // Check that it's not the specific Base64 decoding error
      if (e.getCause() != null && 
          e.getCause().getMessage() != null && 
          e.getCause().getMessage().contains("Illegal base64 character")) {
        fail("Certificate format detection failed - PEM format was not handled correctly: " + e.getMessage());
      }
      // Other exceptions are acceptable for this test since we're using a dummy cert
    }
  }
}
