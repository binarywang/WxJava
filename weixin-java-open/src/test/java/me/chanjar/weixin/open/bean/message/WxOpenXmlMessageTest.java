package me.chanjar.weixin.open.bean.message;

import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.util.WxOpenCryptUtil;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * {@link WxOpenXmlMessage} 单元测试
 */
public class WxOpenXmlMessageTest {

  private static final String COMPONENT_APP_ID = "wx0000000000000001";
  private static final String COMPONENT_TOKEN = "test_component_token";
  /** 43 位 EncodingAESKey 占位值，非真实密钥 */
  private static final String COMPONENT_AES_KEY = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY";

  private static final Pattern ENCRYPT_PATTERN = Pattern.compile("<Encrypt><!\\[CDATA\\[(.*?)]]></Encrypt>");
  private static final Pattern TIMESTAMP_PATTERN = Pattern.compile("<TimeStamp>(.*?)</TimeStamp>");
  private static final Pattern NONCE_PATTERN = Pattern.compile("<Nonce><!\\[CDATA\\[(.*?)]]></Nonce>");

  private static final String VERIFY_TICKET_XML = "<xml>\n"
    + "  <AppId><![CDATA[" + COMPONENT_APP_ID + "]]></AppId>\n"
    + "  <CreateTime>1413192605</CreateTime>\n"
    + "  <InfoType><![CDATA[component_verify_ticket]]></InfoType>\n"
    + "  <ComponentVerifyTicket><![CDATA[ticket@@@abcdefg]]></ComponentVerifyTicket>\n"
    + "</xml>";

  private WxOpenInMemoryConfigStorage config() {
    WxOpenInMemoryConfigStorage config = new WxOpenInMemoryConfigStorage();
    config.setComponentAppId(COMPONENT_APP_ID);
    config.setComponentToken(COMPONENT_TOKEN);
    config.setComponentAesKey(COMPONENT_AES_KEY);
    return config;
  }

  private String group(Pattern pattern, String text) {
    Matcher matcher = pattern.matcher(text);
    assertTrue(matcher.find(), "未匹配到期望的节点：" + pattern.pattern());
    return matcher.group(1);
  }

  @Test
  public void testFromXmlComponentVerifyTicket() {
    WxOpenXmlMessage message = WxOpenXmlMessage.fromXml(VERIFY_TICKET_XML);
    assertNotNull(message);
    assertEquals(message.getAppId(), COMPONENT_APP_ID);
    assertEquals(message.getCreateTime(), Long.valueOf(1413192605L));
    assertEquals(message.getInfoType(), "component_verify_ticket");
    assertEquals(message.getComponentVerifyTicket(), "ticket@@@abcdefg");
  }

  @Test
  public void testFromXmlAuthorized() {
    String xml = "<xml>\n"
      + "  <AppId><![CDATA[" + COMPONENT_APP_ID + "]]></AppId>\n"
      + "  <CreateTime>1413192760</CreateTime>\n"
      + "  <InfoType><![CDATA[authorized]]></InfoType>\n"
      + "  <AuthorizerAppid><![CDATA[wx0000000000000002]]></AuthorizerAppid>\n"
      + "  <AuthorizationCode><![CDATA[auth_code_value]]></AuthorizationCode>\n"
      + "  <AuthorizationCodeExpiredTime>600</AuthorizationCodeExpiredTime>\n"
      + "  <PreAuthCode><![CDATA[pre_auth_code_value]]></PreAuthCode>\n"
      + "</xml>";

    WxOpenXmlMessage message = WxOpenXmlMessage.fromXml(xml);
    assertNotNull(message);
    assertEquals(message.getInfoType(), "authorized");
    assertEquals(message.getAuthorizerAppid(), "wx0000000000000002");
    assertEquals(message.getAuthorizationCode(), "auth_code_value");
    assertEquals(message.getAuthorizationCodeExpiredTime(), Long.valueOf(600L));
    assertEquals(message.getPreAuthCode(), "pre_auth_code_value");
  }

  @Test
  public void testFromXmlFastRegisterWeApp() {
    String xml = "<xml>\n"
      + "  <AppId><![CDATA[" + COMPONENT_APP_ID + "]]></AppId>\n"
      + "  <CreateTime>1535442403</CreateTime>\n"
      + "  <InfoType><![CDATA[notify_third_fasteregister]]></InfoType>\n"
      + "  <appid>wx0000000000000003</appid>\n"
      + "  <status>0</status>\n"
      + "  <auth_code>auth_code_value</auth_code>\n"
      + "  <msg><![CDATA[OK]]></msg>\n"
      + "  <info>\n"
      + "    <name><![CDATA[тест]]></name>\n"
      + "  </info>\n"
      + "</xml>";

    WxOpenXmlMessage message = WxOpenXmlMessage.fromXml(xml);
    assertNotNull(message);
    assertEquals(message.getSubAppId(), "wx0000000000000003");
    assertEquals(message.getRegistAppId(), "wx0000000000000003");
    assertEquals(message.getStatus(), 0);
    assertEquals(message.getAuthCode(), "auth_code_value");
    assertEquals(message.getMsg(), "OK");
    assertNotNull(message.getInfo());
    assertEquals(message.getInfo().getName(), "тест");
  }

  @Test
  public void testFromXmlInputStream() {
    InputStream is = new ByteArrayInputStream(VERIFY_TICKET_XML.getBytes(StandardCharsets.UTF_8));
    WxOpenXmlMessage message = WxOpenXmlMessage.fromXml(is);
    assertNotNull(message);
    assertEquals(message.getComponentVerifyTicket(), "ticket@@@abcdefg");
  }

  @Test
  public void testFromEncryptedXml() {
    WxOpenInMemoryConfigStorage config = config();
    String encryptedXml = new WxOpenCryptUtil(config).encrypt(VERIFY_TICKET_XML);

    String encrypt = group(ENCRYPT_PATTERN, encryptedXml);
    String timestamp = group(TIMESTAMP_PATTERN, encryptedXml);
    String nonce = group(NONCE_PATTERN, encryptedXml);
    String signature = SHA1.gen(COMPONENT_TOKEN, timestamp, nonce, encrypt);

    WxOpenXmlMessage message = WxOpenXmlMessage.fromEncryptedXml(encryptedXml, config, timestamp, nonce, signature);
    assertNotNull(message);
    assertEquals(message.getComponentVerifyTicket(), "ticket@@@abcdefg");
    assertEquals(message.getContext(), VERIFY_TICKET_XML);

    InputStream is = new ByteArrayInputStream(encryptedXml.getBytes(StandardCharsets.UTF_8));
    WxOpenXmlMessage fromStream = WxOpenXmlMessage.fromEncryptedXml(is, config, timestamp, nonce, signature);
    assertNotNull(fromStream);
    assertEquals(fromStream.getInfoType(), "component_verify_ticket");
  }
}
