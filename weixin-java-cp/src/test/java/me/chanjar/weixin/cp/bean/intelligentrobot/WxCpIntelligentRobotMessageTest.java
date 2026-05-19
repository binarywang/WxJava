package me.chanjar.weixin.cp.bean.intelligentrobot;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * 智能机器人回调消息测试.
 */
public class WxCpIntelligentRobotMessageTest {

  @Test
  public void testFromJsonWithTextMessage() {
    String json = "{"
      + "\"msgid\":\"msg_1\","
      + "\"aibotid\":\"bot_1\","
      + "\"chatid\":\"chat_1\","
      + "\"chattype\":\"group\","
      + "\"from\":{\"userid\":\"zhangsan\"},"
      + "\"response_url\":\"https://example.com/reply\","
      + "\"msgtype\":\"text\","
      + "\"text\":{\"content\":\"@robot hello\"}"
      + "}";

    WxCpIntelligentRobotMessage message = WxCpIntelligentRobotMessage.fromJson(json);
    assertEquals(message.getMsgId(), "msg_1");
    assertEquals(message.getAiBotId(), "bot_1");
    assertEquals(message.getChatId(), "chat_1");
    assertEquals(message.getChatType(), "group");
    assertNotNull(message.getFrom());
    assertEquals(message.getFrom().getUserid(), "zhangsan");
    assertEquals(message.getResponseUrl(), "https://example.com/reply");
    assertEquals(message.getMsgType(), "text");
    assertNotNull(message.getText());
    assertEquals(message.getText().getContent(), "@robot hello");
    assertNull(message.getMixed());
    assertNull(message.getStream());
  }

  @Test
  public void testFromJsonWithMixedAndQuote() {
    String json = "{"
      + "\"msgid\":\"msg_2\","
      + "\"aibotid\":\"bot_2\","
      + "\"chattype\":\"single\","
      + "\"from\":{\"userid\":\"lisi\"},"
      + "\"msgtype\":\"mixed\","
      + "\"mixed\":{\"msg_item\":["
      + "{\"msgtype\":\"text\",\"text\":{\"content\":\"hello\"}},"
      + "{\"msgtype\":\"image\",\"image\":{\"url\":\"https://example.com/1.png\"}}"
      + "]},"
      + "\"quote\":{\"msgtype\":\"text\",\"text\":{\"content\":\"quoted\"}}"
      + "}";

    WxCpIntelligentRobotMessage message = WxCpIntelligentRobotMessage.fromJson(json);
    assertEquals(message.getMsgType(), "mixed");
    assertNotNull(message.getMixed());
    assertNotNull(message.getMixed().getMsgItem());
    assertEquals(message.getMixed().getMsgItem().size(), 2);
    assertEquals(message.getMixed().getMsgItem().get(0).getMsgType(), "text");
    assertEquals(message.getMixed().getMsgItem().get(0).getText().getContent(), "hello");
    assertEquals(message.getMixed().getMsgItem().get(1).getMsgType(), "image");
    assertEquals(message.getMixed().getMsgItem().get(1).getImage().getUrl(), "https://example.com/1.png");
    assertNotNull(message.getQuote());
    assertEquals(message.getQuote().getMsgType(), "text");
    assertEquals(message.getQuote().getText().getContent(), "quoted");

    String serialized = message.toJson();
    WxCpIntelligentRobotMessage deserialized = WxCpIntelligentRobotMessage.fromJson(serialized);
    assertEquals(deserialized.getAiBotId(), "bot_2");
    assertEquals(deserialized.getFrom().getUserid(), "lisi");
    assertEquals(deserialized.getMixed().getMsgItem().size(), 2);
  }
}
