package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.intelligentrobot.WxCpIntelligentRobotMessage;
import me.chanjar.weixin.cp.util.crypto.WxCpIntelligentRobotCryptUtil;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class WxCpIntelligentRobotApiModeServiceTest {
  private static final String TOKEN = "test-token";
  private static final String AES_KEY = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFA";
  private static final String AI_BOT_ID = "bot_1";
  private static final String TIMESTAMP = "1710000000";
  private static final String NONCE = "test-nonce";

  @Test
  public void shouldParseEncryptedCallbackMessage() {
    String callbackJson = "{\"msgid\":\"msg_1\",\"aibotid\":\"bot_1\",\"msgtype\":\"text\","
      + "\"from\":{\"userid\":\"user_1\"},\"text\":{\"content\":\"hello\"}}";
    WxCpIntelligentRobotCryptUtil cryptUtil = new WxCpIntelligentRobotCryptUtil(TOKEN, AES_KEY, AI_BOT_ID);
    JsonObject encrypted = GsonParser.parse(cryptUtil.encrypt(callbackJson, TIMESTAMP, NONCE));
    WxCpIntelligentRobotServiceImpl service = new WxCpIntelligentRobotServiceImpl(mock(WxCpService.class));

    WxCpIntelligentRobotMessage message = service.parseEncryptedCallbackMessage(
      encrypted.get("msg_signature").getAsString(), TIMESTAMP, NONCE, encrypted.get("encrypt").getAsString(),
      TOKEN, AES_KEY, AI_BOT_ID);

    assertEquals(message.getMsgId(), "msg_1");
    assertEquals(message.getText().getContent(), "hello");
  }

  @Test
  public void shouldReplyThroughResponseUrlWithoutAccessToken() throws Exception {
    WxCpService cpService = mock(WxCpService.class);
    when(cpService.postWithoutToken(anyString(), anyString())).thenReturn("ok");
    WxCpIntelligentRobotServiceImpl service = new WxCpIntelligentRobotServiceImpl(cpService);
    String responseUrl = "https://example.com/response";
    String plainJson = "{\"msgtype\":\"text\"}";

    assertEquals(service.replyMessage(responseUrl, plainJson, TOKEN, AES_KEY, AI_BOT_ID, TIMESTAMP, NONCE), "ok");

    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).postWithoutToken(org.mockito.ArgumentMatchers.eq(responseUrl), bodyCaptor.capture());
    JsonObject encrypted = GsonParser.parse(bodyCaptor.getValue());
    WxCpIntelligentRobotCryptUtil cryptUtil = new WxCpIntelligentRobotCryptUtil(TOKEN, AES_KEY, AI_BOT_ID);
    assertEquals(cryptUtil.decrypt(encrypted.get("msg_signature").getAsString(), TIMESTAMP, NONCE,
      encrypted.get("encrypt").getAsString()), plainJson);
  }
}
