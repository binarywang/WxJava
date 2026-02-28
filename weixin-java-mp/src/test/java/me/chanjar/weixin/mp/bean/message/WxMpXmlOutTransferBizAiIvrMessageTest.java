package me.chanjar.weixin.mp.bean.message;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 转接AI回复消息测试.
 *
 * @author copilot
 */
public class WxMpXmlOutTransferBizAiIvrMessageTest {

  @Test
  public void test() {
    WxMpXmlOutTransferBizAiIvrMessage m = new WxMpXmlOutTransferBizAiIvrMessage();
    m.setCreateTime(1399197672L);
    m.setFromUserName("fromuser");
    m.setToUserName("touser");

    String expected = "<xml>" +
      "<ToUserName><![CDATA[touser]]></ToUserName>" +
      "<FromUserName><![CDATA[fromuser]]></FromUserName>" +
      "<CreateTime>1399197672</CreateTime>" +
      "<MsgType><![CDATA[transfer_biz_ai_ivr]]></MsgType>" +
      "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  @Test
  public void testBuild() {
    WxMpXmlOutTransferBizAiIvrMessage m = WxMpXmlOutMessage.TRANSFER_BIZ_AI_IVR()
      .fromUser("fromuser").toUser("touser").build();
    m.setCreateTime(1399197672L);
    String expected = "<xml>" +
      "<ToUserName><![CDATA[touser]]></ToUserName>" +
      "<FromUserName><![CDATA[fromuser]]></FromUserName>" +
      "<CreateTime>1399197672</CreateTime>" +
      "<MsgType><![CDATA[transfer_biz_ai_ivr]]></MsgType>" +
      "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }
}
