package me.chanjar.weixin.channel.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

/**
 * {@link WxChCryptUtils} 单元测试
 */
public class WxChCryptUtilsTest {

  private static final String APP_ID = "wx0000000000000002";
  private static final String TOKEN = "test_channel_token";
  /** 43 位 EncodingAESKey 占位值，非真实密钥 */
  private static final String AES_KEY = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY";

  private static final Pattern ENCRYPT_PATTERN = Pattern.compile("<Encrypt><!\\[CDATA\\[(.*?)]]></Encrypt>");
  private static final Pattern SIGNATURE_PATTERN =
    Pattern.compile("<MsgSignature><!\\[CDATA\\[(.*?)]]></MsgSignature>");
  private static final Pattern TIMESTAMP_PATTERN = Pattern.compile("<TimeStamp>(.*?)</TimeStamp>");
  private static final Pattern NONCE_PATTERN = Pattern.compile("<Nonce><!\\[CDATA\\[(.*?)]]></Nonce>");

  private WxChCryptUtils cryptUtils() {
    WxChannelDefaultConfigImpl config = new WxChannelDefaultConfigImpl();
    config.setAppid(APP_ID);
    config.setToken(TOKEN);
    config.setAesKey(AES_KEY);
    return new WxChCryptUtils(config);
  }

  private String group(Pattern pattern, String text) {
    Matcher matcher = pattern.matcher(text);
    assertTrue(matcher.find(), "未匹配到期望的节点：" + pattern.pattern());
    return matcher.group(1);
  }

  @Test
  public void testEncryptThenDecrypt() {
    WxChCryptUtils cryptUtils = cryptUtils();
    String plainText = "<xml><ToUserName><![CDATA[" + APP_ID + "]]></ToUserName>"
      + "<MsgType><![CDATA[event]]></MsgType></xml>";

    String encryptedXml = cryptUtils.encrypt(plainText);
    assertNotNull(encryptedXml);

    String encrypt = group(ENCRYPT_PATTERN, encryptedXml);
    String signature = group(SIGNATURE_PATTERN, encryptedXml);
    String timestamp = group(TIMESTAMP_PATTERN, encryptedXml);
    String nonce = group(NONCE_PATTERN, encryptedXml);

    assertEquals(signature, SHA1.gen(TOKEN, timestamp, nonce, encrypt));
    assertEquals(cryptUtils.decryptXml(signature, timestamp, nonce, encryptedXml), plainText);
  }

  @Test
  public void testDecryptXmlWithWrongSignature() {
    WxChCryptUtils cryptUtils = cryptUtils();
    String encryptedXml = cryptUtils.encrypt("<xml><MsgType><![CDATA[event]]></MsgType></xml>");
    String timestamp = group(TIMESTAMP_PATTERN, encryptedXml);
    String nonce = group(NONCE_PATTERN, encryptedXml);

    expectThrows(WxRuntimeException.class,
      () -> cryptUtils.decryptXml("wrong_signature", timestamp, nonce, encryptedXml));
  }

  @Test
  public void testDecryptWithSessionKey() throws Exception {
    byte[] keyBytes = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);
    byte[] ivBytes = "fedcba9876543210".getBytes(StandardCharsets.UTF_8);
    String plainText = "{\"openid\":\"o0000000000000000000\"}";

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(ivBytes));
    String encryptedData = Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));

    String decrypted = WxChCryptUtils.decrypt(Base64.encodeBase64String(keyBytes), encryptedData,
      Base64.encodeBase64String(ivBytes));
    assertEquals(decrypted, plainText);
  }

  @Test
  public void testDecryptWithInvalidSessionKey() {
    expectThrows(RuntimeException.class,
      () -> WxChCryptUtils.decrypt(Base64.encodeBase64String("short_key".getBytes(StandardCharsets.UTF_8)),
        "invalid", Base64.encodeBase64String("fedcba9876543210".getBytes(StandardCharsets.UTF_8))));
  }
}
