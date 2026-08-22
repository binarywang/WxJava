package me.chanjar.weixin.cp.util.crypto;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WxCpIntelligentRobotCryptUtilTest {
  private static final String TOKEN = "test-token";
  private static final String AES_KEY = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFA";
  private static final String AI_BOT_ID = "aibot-123";
  private static final String TIMESTAMP = "1710000000";
  private static final String NONCE = "test-nonce";

  @Test
  public void encryptShouldProduceDecryptableJsonEnvelope() {
    WxCpIntelligentRobotCryptUtil cryptUtil = new WxCpIntelligentRobotCryptUtil(TOKEN, AES_KEY, AI_BOT_ID);
    String plainJson = "{\"msgtype\":\"text\",\"text\":{\"content\":\"hello\"}}";

    JsonObject encrypted = GsonParser.parse(cryptUtil.encrypt(plainJson, TIMESTAMP, NONCE));

    assertEquals(encrypted.get("timestamp").getAsString(), TIMESTAMP);
    assertEquals(encrypted.get("nonce").getAsString(), NONCE);
    assertEquals(cryptUtil.decrypt(encrypted.get("msg_signature").getAsString(), TIMESTAMP, NONCE,
      encrypted.get("encrypt").getAsString()), plainJson);
  }

  @Test(expectedExceptions = WxRuntimeException.class)
  public void decryptShouldRejectMessageForAnotherRobot() {
    String plainJson = "{\"msgtype\":\"text\"}";
    WxCpIntelligentRobotCryptUtil source = new WxCpIntelligentRobotCryptUtil(TOKEN, AES_KEY, AI_BOT_ID);
    JsonObject encrypted = GsonParser.parse(source.encrypt(plainJson, TIMESTAMP, NONCE));
    WxCpIntelligentRobotCryptUtil otherRobot = new WxCpIntelligentRobotCryptUtil(TOKEN, AES_KEY, "aibot-456");

    otherRobot.decrypt(encrypted.get("msg_signature").getAsString(), TIMESTAMP, NONCE,
      encrypted.get("encrypt").getAsString());
  }
}
