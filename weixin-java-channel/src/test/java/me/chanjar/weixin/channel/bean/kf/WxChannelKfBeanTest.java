package me.chanjar.weixin.channel.bean.kf;

import me.chanjar.weixin.channel.util.JsonUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
public class WxChannelKfBeanTest {

  @Test
  public void testSendMsgParamEncode() {
    WxChannelKfSendMsgParam param = new WxChannelKfSendMsgParam();
    param.setRequestId("63abd34b-656b-4082-b364-5f74226e1a20");
    param.setOpenId("o7eep4jVQelr2eyoDSmE1xxxxxx");
    param.setMsgType("text");
    param.setText(new WxChannelKfSendMsgParam.TextMessage("测试消息123"));

    String json = JsonUtils.encode(param);
    assertNotNull(json);
    assertTrue(json.contains("\"request_id\":\"63abd34b-656b-4082-b364-5f74226e1a20\""));
    assertTrue(json.contains("\"open_id\":\"o7eep4jVQelr2eyoDSmE1xxxxxx\""));
    assertTrue(json.contains("\"msg_type\":\"text\""));
    assertTrue(json.contains("\"text\":{\"content\":\"测试消息123\"}"));
  }

  @Test
  public void testSendMsgResponseDecode() {
    String json = "{\"msg_id\":\"3886839959369302016\",\"errmsg\":\"ok\",\"errcode\":0}";
    WxChannelKfSendMsgResponse response = JsonUtils.decode(json, WxChannelKfSendMsgResponse.class);
    assertNotNull(response);
    assertEquals(response.getMsgId(), "3886839959369302016");
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getErrMsg(), "ok");
  }

  @Test
  public void testCosUploadResponseDecode() {
    String json = "{\"cos_url\":\"https://channels.weixin.qq.com/shop/commkf/downloadmedia?encrypted_param=xxxxx&timestamp=xxxxx&openid=xxxxxx&msg_type=7\",\"errmsg\":\"ok\",\"errcode\":0}";
    WxChannelKfCosUploadResponse response = JsonUtils.decode(json, WxChannelKfCosUploadResponse.class);
    assertNotNull(response);
    assertTrue(response.getCosUrl().contains("channels.weixin.qq.com/shop/commkf/downloadmedia"));
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getErrMsg(), "ok");
  }
}
