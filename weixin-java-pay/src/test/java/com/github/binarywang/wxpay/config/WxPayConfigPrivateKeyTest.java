package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Test cases for private key format handling in WxPayConfig
 */
public class WxPayConfigPrivateKeyTest {

  @Test
  public void testPrivateKeyStringFormat_PemFormat() {
    WxPayConfig config = new WxPayConfig();
    
    // Set minimal required configuration 
    config.setMchId("1234567890");
    config.setApiV3Key("test-api-v3-key-32-characters-long");
    config.setCertSerialNo("test-serial-number");
    
    // Test with PEM format private key string that would previously fail
    String pemKey = "-----BEGIN PRIVATE KEY-----\n" +
                   "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2pK3buBufh8Vo\n" +
                   "X4sfYbZ5CcPeGMnVQTGmj0b6\n" +
                   "-----END PRIVATE KEY-----";
    
    config.setPrivateKeyString(pemKey);
    
    // This should not throw a "无效的密钥格式" exception immediately
    // The actual key validation will happen during HTTP client initialization
    // but at least the format parsing should not fail
    
    try {
      // Try to initialize API V3 HTTP client - this might fail for other reasons 
      // (like invalid key content) but should not fail due to format parsing
      config.initApiV3HttpClient();
      // If we get here without InvalidKeySpecException, the format detection worked
    } catch (WxPayException e) {
      // Check that it's not the specific "无效的密钥格式" error from PemUtils
      if (e.getCause() != null && 
          e.getCause().getMessage() != null && 
          e.getCause().getMessage().contains("无效的密钥格式")) {
        fail("Private key format detection failed - PEM format was not handled correctly: " + e.getMessage());
      }
      // Other exceptions are acceptable for this test since we're using a dummy key
    } catch (Exception e) {
      // Check for the specific InvalidKeySpecException that indicates format problems
      if (e.getCause() != null && 
          e.getCause().getMessage() != null && 
          e.getCause().getMessage().contains("无效的密钥格式")) {
        fail("Private key format detection failed - PEM format was not handled correctly: " + e.getMessage());
      }
      // Other exceptions are acceptable for this test since we're using a dummy key
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