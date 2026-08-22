package me.chanjar.weixin.aispeech.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WxAispeechSignUtilTest {

  @Test
  public void testCalcDialogSign() {
    String sign = WxAispeechSignUtil.calcDialogSign("token123", 1711520394L, "abcdefghijklmn", "{\"env\":\"online\"}");
    Assert.assertEquals(sign, "db3f57ece7f56fef3ac512f97ef1f624");
  }

  @Test
  public void testCalcKnowledgeSignature() {
    String signature = WxAispeechSignUtil.calcKnowledgeSignature("secret-key", 1677652288L,
      "nonce-abc", "request-1", "{\"a\":1}");
    Assert.assertEquals(signature, "bf31b89ef3008e1ef91f7057d8819d4c8d5f9e435f5657097cb8f7fbf69d4e73");
  }

  @Test
  public void testAesEncryptAndDecrypt() {
    String aesKey = "q1Os1ZMe0nG28KUEx9lg3HjK7V5QyXvi212fzsgDqgz";
    String source = "{\"query\":\"你好\"}";

    String encrypted = WxAispeechSignUtil.encryptAesCbcToBase64(source, aesKey);
    String decrypted = WxAispeechSignUtil.decryptAesCbcFromBase64(encrypted, aesKey);

    Assert.assertEquals(decrypted, source);
  }
}
