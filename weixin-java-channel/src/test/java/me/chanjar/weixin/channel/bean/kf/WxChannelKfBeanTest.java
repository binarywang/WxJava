package me.chanjar.weixin.channel.bean.kf;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import me.chanjar.weixin.channel.util.JsonUtils;
import org.testng.annotations.Test;

/** JSON serialization tests for channel customer service models. */
public class WxChannelKfBeanTest {

  @Test
  public void testSendMsgParamJson() {
    WxChannelKfSendMsgParam param = new WxChannelKfSendMsgParam();
    param.setRequestId("request-1");
    param.setOpenId("open-1");
    param.setMsgType("text");
    WxChannelKfSendMsgParam.Text text = new WxChannelKfSendMsgParam.Text();
    text.setContent("hello");
    param.setText(text);

    String json = JsonUtils.encode(param);
    assertNotNull(json);
    assertFalse(json.contains("requestId"));
    assertFalse(json.contains("openId"));
    assertFalse(json.contains("msgType"));
    WxChannelKfSendMsgParam decoded = JsonUtils.decode(json, WxChannelKfSendMsgParam.class);
    assertEquals(decoded.getRequestId(), "request-1");
    assertEquals(decoded.getOpenId(), "open-1");
    assertEquals(decoded.getMsgType(), "text");
    assertNotNull(decoded.getText());
    assertEquals(decoded.getText().getContent(), "hello");
  }

  @Test
  public void testCosUploadResponseJson() {
    WxChannelKfCosUploadResponse response = JsonUtils.decode(
      "{\"errcode\":0,\"errmsg\":\"ok\",\"cos_url\":\"https://example.test/media\"}",
      WxChannelKfCosUploadResponse.class);

    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getCosUrl(), "https://example.test/media");
    assertEquals(JsonUtils.decode(JsonUtils.encode(response), WxChannelKfCosUploadResponse.class)
      .getCosUrl(), "https://example.test/media");
  }

  @Test
  public void testSendMsgResponseJson() {
    WxChannelKfSendMsgResponse response = JsonUtils.decode(
      "{\"errcode\":0,\"errmsg\":\"ok\",\"msg_id\":\"msg-1\"}",
      WxChannelKfSendMsgResponse.class);

    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getMsgId(), "msg-1");
    assertEquals(JsonUtils.decode(JsonUtils.encode(response), WxChannelKfSendMsgResponse.class)
      .getMsgId(), "msg-1");
  }
}
