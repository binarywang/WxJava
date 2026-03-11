package me.chanjar.weixin.cp.bean.message;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * The type Wx cp tp xml message test.
 */
public class WxCpTpXmlMessageTest {

  /**
   * Test user notify xml.
   */
  @Test
  public void testUserNotifyXML() {
    String xml = "<xml>\n" +
      "    <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>\n" +
      "    <AuthCorpId><![CDATA[wxf8b4f85f3axxxxxx]]></AuthCorpId>\n" +
      "    <InfoType><![CDATA[change_contact]]></InfoType>\n" +
      "    <TimeStamp>1403610513</TimeStamp>\n" +
      "    <ChangeType><![CDATA[create_user]]></ChangeType>\n" +
      "    <UserID><![CDATA[zhangsan]]></UserID>\n" +
      "    <Name><![CDATA[张三]]></Name>\n" +
      "    <Department><![CDATA[1,2,3]]></Department>\n" +
      "    <MainDepartment>1</MainDepartment>\n" +
      "    <IsLeaderInDept><![CDATA[1,0,0]]></IsLeaderInDept>\n" +
      "    <Mobile><![CDATA[11111111111]]></Mobile>\n" +
      "    <Position><![CDATA[产品经理]]></Position>\n" +
      "    <Gender>1</Gender>\n" +
      "    <Email><![CDATA[zhangsan@xxx.com]]></Email>\n" +
      "    <Avatar><![CDATA[http://wx.qlogo" +
      ".cn/mmopen/ajNVdqHZLLA3WJ6DSZUfiakYe37PKnQhBIeOQBO4czqrnZDS79FH5Wm5m4X69TBicnHFlhiafvDwklOpZeXYQQ2icg/0" +
      "]]></Avatar>\n" +
      "    <Alias><![CDATA[zhangsan]]></Alias>\n" +
      "    <Telephone><![CDATA[020-111111]]></Telephone>\n" +
      "    <ExtAttr>\n" +
      "        <Item>\n" +
      "        <Name><![CDATA[爱好]]></Name>\n" +
      "        <Type>0</Type>\n" +
      "        <Text>\n" +
      "            <Value><![CDATA[旅游]]></Value>\n" +
      "        </Text>\n" +
      "        </Item>\n" +
      "        <Item>\n" +
      "        <Name><![CDATA[卡号]]></Name>\n" +
      "        <Type>1</Type>\n" +
      "        <Web>\n" +
      "            <Title><![CDATA[企业微信]]></Title>\n" +
      "            <Url><![CDATA[https://work.weixin.qq.com]]></Url>\n" +
      "        </Web>\n" +
      "        </Item>\n" +
      "    </ExtAttr>\n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);
    assertEquals(wxXmlMessage.getSuiteId(), "ww4asffe99e54c0f4c");
    assertEquals(wxXmlMessage.getPosition(), "产品经理");
    assertEquals(wxXmlMessage.getGender(), Integer.valueOf(1));
    assertEquals(wxXmlMessage.getTelephone(), "020-111111");
  }


  /**
   * Test register corp.
   */
  @Test
  public void testRegisterCorp() {
    String xml = "<xml>\n" +
      "    <ServiceCorpId><![CDATA[wwddddccc7775555aab]]></ServiceCorpId>\n" +
      "    <InfoType><![CDATA[register_corp]]></InfoType>\n" +
      "    <TimeStamp>1502682173</TimeStamp>\n" +
      "    <RegisterCode><![CDATA[pIKi3wRPNWCGF-pyP-YU5KWjDDD]]></RegisterCode>\n" +
      "    <AuthCorpId><![CDATA[wwddddccc7775555aaa]]></AuthCorpId>\n" +
      "    <ContactSync>\n" +
      "        <AccessToken><![CDATA[accesstoken000001]]></AccessToken>\n" +
      "        <ExpiresIn>1800</ExpiresIn>\n" +
      "    </ContactSync>\n" +
      "    <AuthUserInfo>\n" +
      "        <UserId><![CDATA[zhangshan]]></UserId>\n" +
      "    </AuthUserInfo>\n" +
      "    <State><![CDATA[state1]]></State>\n" +
      "    <TemplateId><![CDATA[tpl1test]]></TemplateId>\n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);
    assertEquals(wxXmlMessage.getServiceCorpId(), "wwddddccc7775555aab");
    assertEquals(wxXmlMessage.getInfoType(), "register_corp");
    assertEquals(wxXmlMessage.getRegisterCode(), "pIKi3wRPNWCGF-pyP-YU5KWjDDD");
    assertNotNull(wxXmlMessage.getContactSync());
    assertEquals(wxXmlMessage.getContactSync().getAccessToken(), "accesstoken000001");
    assertEquals(wxXmlMessage.getContactSync().getExpiresIn(), Integer.valueOf(1800));
    assertNotNull(wxXmlMessage.getAuthUserInfo());
    assertEquals(wxXmlMessage.getAuthUserInfo().getUserId(), "zhangshan");
    assertEquals(wxXmlMessage.getTemplateId(), "tpl1test");
  }

  /**
   * Tag notify test.
   */
  @Test
  public void tagNotifyTest() {
    String xml = "<xml>\n" +
      "    <SuiteId><![CDATA[ww4asffe99e54cxxxx]]></SuiteId>\n" +
      "    <AuthCorpId><![CDATA[wxf8b4f85f3a79xxxx]]></AuthCorpId>\n" +
      "    <InfoType><![CDATA[change_contact]]></InfoType>\n" +
      "    <TimeStamp>1403610513</TimeStamp>\n" +
      "    <ChangeType><![CDATA[update_tag]]></ChangeType>\n" +
      "    <TagId>1</TagId>\n" +
      "    <AddUserItems><![CDATA[zhangsan,lisi]]></AddUserItems>\n" +
      "    <DelUserItems><![CDATA[zhangsan1,lisi1]]></DelUserItems>\n" +
      "    <AddPartyItems><![CDATA[1,2]]></AddPartyItems>\n" +
      "    <DelPartyItems><![CDATA[3,4]]></DelPartyItems>\n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);

    assertEquals(wxXmlMessage.getTagId(), Integer.valueOf(1));
    assertNotNull(wxXmlMessage.getAddUserItems());
    assertEquals(wxXmlMessage.getAddUserItems()[0], "zhangsan");
    assertEquals(wxXmlMessage.getAddUserItems()[1], "lisi");

    assertNotNull(wxXmlMessage.getDelUserItems());
    assertNotNull(wxXmlMessage.getDelUserItems()[0], "zhangsan1");
    assertNotNull(wxXmlMessage.getDelUserItems()[0], "lisi1");

    assertNotNull(wxXmlMessage.getAddPartyItems());
    assertEquals(wxXmlMessage.getAddPartyItems()[0], Integer.valueOf(1));
    assertEquals(wxXmlMessage.getAddPartyItems()[1], Integer.valueOf(2));

  }


  /**
   * Enter app test.
   */
  @Test
  public void enterAppTest() {
    String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "<FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
      "<CreateTime>1408091189</CreateTime>\n" +
      "<MsgType><![CDATA[event]]></MsgType>\n" +
      "<Event><![CDATA[enter_agent]]></Event>\n" +
      "<EventKey><![CDATA[]]></EventKey>\n" +
      "<AgentID>1</AgentID>\n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);
    assertEquals(wxXmlMessage.getToUserName(), "toUser");
    assertEquals(wxXmlMessage.getFromUserName(), "FromUser");
    assertEquals(wxXmlMessage.getCreateTime(), Long.valueOf(1408091189));
    assertEquals(wxXmlMessage.getEvent(), "enter_agent");
    assertEquals(wxXmlMessage.getEventKey(), "");
    assertEquals(wxXmlMessage.getAgentID(), 1);
  }

  /**
   * Text message test.
   */
  @Test
  public void textMessageTest() {
    String xml = "<xml>\n" +
      "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "   <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
      "   <CreateTime>1348831860</CreateTime>\n" +
      "   <MsgType><![CDATA[text]]></MsgType>\n" +
      "   <Content><![CDATA[this is a test]]></Content>\n" +
      "   <MsgId>1234567890123456</MsgId>\n" +
      "   <Id><![CDATA[etEsNADQAAaiB0cWCSDFiJ2qCap-ww9A]]></Id>" +
      "   <AgentID>1</AgentID>\n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);
    assertEquals(wxXmlMessage.getToUserName(), "toUser");
    assertEquals(wxXmlMessage.getFromUserName(), "fromUser");
    assertEquals(wxXmlMessage.getCreateTime(), Long.valueOf(1348831860));
    assertEquals(wxXmlMessage.getMsgType(), "text");
    assertEquals(wxXmlMessage.getMsgId(), "1234567890123456");
    assertEquals(wxXmlMessage.getId(), "etEsNADQAAaiB0cWCSDFiJ2qCap-ww9A");
  }

  /**
   * Approval info test.
   */
  @Test
  public void ApprovalInfoTest() {
    String xml = "<xml>\n" +
      " <ToUserName>wwddddccc7775555aaa</ToUserName>  \n" +
      "  <FromUserName>sys</FromUserName>  \n" +
      "  <CreateTime>1527838022</CreateTime>  \n" +
      "  <MsgType>event</MsgType>  \n" +
      "  <Event>open_approval_change</Event>\n" +
      "  <AgentID>1</AgentID>\n" +
      "  <ApprovalInfo> \n" +
      "    <ThirdNo>201806010001</ThirdNo>  \n" +
      "    <OpenSpName>付款</OpenSpName>  \n" +
      "    <OpenTemplateId>1234567890</OpenTemplateId> \n" +
      "    <OpenSpStatus>1</OpenSpStatus>  \n" +
      "    <ApplyTime>1527837645</ApplyTime>  \n" +
      "    <ApplyUserName>xiaoming</ApplyUserName>  \n" +
      "    <ApplyUserId>1</ApplyUserId>  \n" +
      "    <ApplyUserParty>产品部</ApplyUserParty>  \n" +
      "    <ApplyUserImage>http://www.qq.com/xxx.png</ApplyUserImage>  \n" +
      "    <ApprovalNodes> \n" +
      "      <ApprovalNode> \n" +
      "        <NodeStatus>1</NodeStatus>  \n" +
      "        <NodeAttr>1</NodeAttr> \n" +
      "        <NodeType>1</NodeType>  \n" +
      "        <Items> \n" +
      "          <Item> \n" +
      "            <ItemName>xiaohong</ItemName>  \n" +
      "            <ItemUserId>2</ItemUserId> \n" +
      "            <ItemImage>http://www.qq.com/xxx.png</ItemImage>  \n" +
      "            <ItemStatus>1</ItemStatus>  \n" +
      "            <ItemSpeech></ItemSpeech>  \n" +
      "            <ItemOpTime>0</ItemOpTime> \n" +
      "          </Item> \n" +
      "        </Items> \n" +
      "      </ApprovalNode> \n" +
      "    </ApprovalNodes>  \n" +
      "    <NotifyNodes> \n" +
      "      <NotifyNode> \n" +
      "        <ItemName>xiaogang</ItemName>  \n" +
      "        <ItemUserId>3</ItemUserId> \n" +
      "        <ItemImage>http://www.qq.com/xxx.png</ItemImage>  \n" +
      "      </NotifyNode> \n" +
      "    </NotifyNodes> \n" +
      "    <approverstep>0</approverstep>  \n" +
      "  </ApprovalInfo> \n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);
    assertEquals(wxXmlMessage.getToUserName(), "wwddddccc7775555aaa");
    assertEquals(wxXmlMessage.getFromUserName(), "sys");
    assertEquals(wxXmlMessage.getCreateTime(), Long.valueOf(1527838022));
    assertEquals(wxXmlMessage.getEvent(), "open_approval_change");

    assertNotNull(wxXmlMessage.getApprovalInfo());
    assertEquals(wxXmlMessage.getApprovalInfo().getThirdNo(), Long.valueOf(201806010001L));
    assertEquals(wxXmlMessage.getApprovalInfo().getOpenSpName(), "付款");
    assertEquals(wxXmlMessage.getApprovalInfo().getThirdNo(), Long.valueOf(201806010001L));
    assertEquals(wxXmlMessage.getApprovalInfo().getApplyTime(), Long.valueOf(1527837645));
    assertEquals(wxXmlMessage.getApprovalInfo().getApplyUserName(), "xiaoming");

    assertNotNull(wxXmlMessage.getApprovalInfo().getApprovalNodes());
    assertNotNull(wxXmlMessage.getApprovalInfo().getApprovalNodes().get(0));
    assertEquals(wxXmlMessage.getApprovalInfo().getApprovalNodes().get(0).getNodeAttr(), Integer.valueOf(1));
    assertEquals(wxXmlMessage.getApprovalInfo().getApprovalNodes().get(0).getNodeType(), Integer.valueOf(1));

    assertNotNull(wxXmlMessage.getApprovalInfo().getApprovalNodes().get(0).getItems());
    assertEquals(wxXmlMessage.getApprovalInfo().getApprovalNodes().get(0).getItems().get(0).getItemName(), "xiaohong");
    assertEquals(wxXmlMessage.getApprovalInfo().getApprovalNodes().get(0).getItems().get(0).getItemOpTime(),
      Long.valueOf(0));

    assertNotNull(wxXmlMessage.getApprovalInfo().getNotifyNodes().get(0));
    assertEquals(wxXmlMessage.getApprovalInfo().getNotifyNodes().get(0).getItemImage(), "http://www.qq.com/xxx.png");
    assertEquals(wxXmlMessage.getApprovalInfo().getNotifyNodes().get(0).getItemUserId(), Integer.valueOf(3));
  }

  /**
   * Test from xml.
   */
  @Test
  public void testFromXml() {
    String xml = "<xml>\n" +
      "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "   <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
      "   <CreateTime>1348831860</CreateTime>\n" +
      "   <MsgType><![CDATA[text]]></MsgType>\n" +
      "   <Content><![CDATA[this is a test]]></Content>\n" +
      "   <MsgId>1234567890123456</MsgId>\n" +
      "   <Id>2</Id>\n" +
      "   <AgentID>1</AgentID>\n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);
    assertEquals(wxXmlMessage.getToUserName(), "toUser");
    assertEquals(wxXmlMessage.getFromUserName(), "fromUser");
    assertEquals(wxXmlMessage.getCreateTime(), Long.valueOf(1348831860));
    assertEquals(wxXmlMessage.getMsgType(), "text");
    assertEquals(wxXmlMessage.getMsgId(), "1234567890123456");
    assertEquals(wxXmlMessage.getId(), "2");

  }
}
