package me.chanjar.weixin.aispeech.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WxAispeechSignUtilTest {

  @Test
  public void testCalcDialogSign() {
    String sign = WxAispeechSignUtil.calcDialogSign("token123", 1711520394L, "abcdefghijklmn", "{\"env\":\"online\"}");
    Assert.assertEquals(sign, "dfa822f53a8de6bd41e0ea1b8d23f3be");
  }

  @Test
  public void testCalcKnowledgeSignature() {
    String signature = WxAispeechSignUtil.calcKnowledgeSignature("secret-key", 1677652288L,
      "nonce-abc", "request-1", "{\"a\":1}");
    Assert.assertEquals(signature, "5a525e13c491e312bb72a08ea5c3d2b7902bfe29121cbf42c2613f656ea94532");
  }

  @Test
  public void testAesEncryptAndDecrypt() {
    String aesKey = java.util.Base64.getEncoder().withoutPadding().encodeToString(new byte[32]);
    String source = "{\"query\":\"你好\"}";

    String encrypted = WxAispeechSignUtil.encryptAesCbcToBase64(source, aesKey);
    String decrypted = WxAispeechSignUtil.decryptAesCbcFromBase64(encrypted, aesKey);

    Assert.assertEquals(decrypted, source);
  }
}
