package me.chanjar.weixin.open.util;

import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

/**
 * {@link WxOpenCryptUtil} 单元测试
 */
public class WxOpenCryptUtilTest {

  private static final String COMPONENT_APP_ID = "wx0000000000000001";
  private static final String COMPONENT_TOKEN = "test_component_token";
  /** 43 位 EncodingAESKey 占位值，非真实密钥 */
  private static final String COMPONENT_AES_KEY = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY";

  private static final Pattern ENCRYPT_PATTERN = Pattern.compile("<Encrypt><!\\[CDATA\\[(.*?)]]></Encrypt>");
  private static final Pattern SIGNATURE_PATTERN =
    Pattern.compile("<MsgSignature><!\\[CDATA\\[(.*?)]]></MsgSignature>");
  private static final Pattern TIMESTAMP_PATTERN = Pattern.compile("<TimeStamp>(.*?)</TimeStamp>");
  private static final Pattern NONCE_PATTERN = Pattern.compile("<Nonce><!\\[CDATA\\[(.*?)]]></Nonce>");

  private WxOpenInMemoryConfigStorage config(String aesKey) {
    WxOpenInMemoryConfigStorage config = new WxOpenInMemoryConfigStorage();
    config.setComponentAppId(COMPONENT_APP_ID);
    config.setComponentToken(COMPONENT_TOKEN);
    config.setComponentAesKey(aesKey);
    return config;
  }

  private String group(Pattern pattern, String text) {
    Matcher matcher = pattern.matcher(text);
    assertTrue(matcher.find(), "未匹配到期望的节点：" + pattern.pattern());
    return matcher.group(1);
  }

  @Test
  public void testEncryptThenDecrypt() {
    WxOpenCryptUtil cryptUtil = new WxOpenCryptUtil(config(COMPONENT_AES_KEY));
    String plainText = "<xml><AppId><![CDATA[" + COMPONENT_APP_ID + "]]></AppId>"
      + "<InfoType><![CDATA[component_verify_ticket]]></InfoType></xml>";

    String encryptedXml = cryptUtil.encrypt(plainText);
    assertNotNull(encryptedXml);

    String encrypt = group(ENCRYPT_PATTERN, encryptedXml);
    String signature = group(SIGNATURE_PATTERN, encryptedXml);
    String timestamp = group(TIMESTAMP_PATTERN, encryptedXml);
    String nonce = group(NONCE_PATTERN, encryptedXml);

    assertEquals(signature, SHA1.gen(COMPONENT_TOKEN, timestamp, nonce, encrypt));
    assertEquals(cryptUtil.decryptXml(signature, timestamp, nonce, encryptedXml), plainText);
    assertEquals(cryptUtil.decryptContent(signature, timestamp, nonce, encrypt), plainText);
  }

  @Test
  public void testDecryptWithWrongSignature() {
    WxOpenCryptUtil cryptUtil = new WxOpenCryptUtil(config(COMPONENT_AES_KEY));
    String encryptedXml = cryptUtil.encrypt("<xml><InfoType><![CDATA[unauthorized]]></InfoType></xml>");

    String timestamp = group(TIMESTAMP_PATTERN, encryptedXml);
    String nonce = group(NONCE_PATTERN, encryptedXml);

    expectThrows(WxRuntimeException.class,
      () -> cryptUtil.decryptXml("wrong_signature", timestamp, nonce, encryptedXml));
  }

  @Test
  public void testAesKeyWithSpaces() {
    String plainText = "<xml><InfoType><![CDATA[authorized]]></InfoType></xml>";
    WxOpenCryptUtil cryptUtil = new WxOpenCryptUtil(config(COMPONENT_AES_KEY));
    WxOpenCryptUtil cryptUtilWithSpaces = new WxOpenCryptUtil(
      config(" " + COMPONENT_AES_KEY.substring(0, 10) + " " + COMPONENT_AES_KEY.substring(10) + " "));

    String randomStr = "1234567890123456";
    assertEquals(cryptUtilWithSpaces.encrypt(randomStr, plainText), cryptUtil.encrypt(randomStr, plainText));
  }
}
