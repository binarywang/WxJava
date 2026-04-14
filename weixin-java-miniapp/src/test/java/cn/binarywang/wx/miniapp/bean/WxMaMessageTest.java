package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.bean.xpay.WxMaXPayTeamInfo;
import me.chanjar.weixin.common.api.WxConsts;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
public class WxMaMessageTest {

  public void testFromXml() {
    String xml = "<xml>\n" +
      "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "   <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
      "   <CreateTime>1482048670</CreateTime>\n" +
      "   <MsgType><![CDATA[text]]></MsgType>\n" +
      "   <Content><![CDATA[this is a test]]></Content>\n" +
      "   <MsgId>1234567890123456</MsgId>\n" +
      "   <PicUrl><![CDATA[this is a url]]></PicUrl>\n" +
      "   <MediaId><![CDATA[media_id]]></MediaId>\n" +
      "   <Title><![CDATA[Title]]></Title>\n" +
      "   <AppId><![CDATA[AppId]]></AppId>\n" +
      "   <PagePath><![CDATA[PagePath]]></PagePath>\n" +
      "   <ThumbUrl><![CDATA[ThumbUrl]]></ThumbUrl>\n" +
      "   <ThumbMediaId><![CDATA[ThumbMediaId]]></ThumbMediaId>\n" +
      "   <Event><![CDATA[user_enter_tempsession]]></Event>\n" +
      "   <SessionFrom><![CDATA[sessionFrom]]></SessionFrom>\n" +
      "</xml>";
    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "toUser");
    assertEquals(wxMessage.getFromUser(), "fromUser");
    assertEquals(wxMessage.getCreateTime(), new Integer(1482048670));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
    assertEquals(wxMessage.getPicUrl(), "this is a url");
    assertEquals(wxMessage.getMediaId(), "media_id");
    assertEquals(wxMessage.getTitle(), "Title");
    assertEquals(wxMessage.getPagePath(), "PagePath");
    assertEquals(wxMessage.getThumbUrl(), "ThumbUrl");
    assertEquals(wxMessage.getThumbMediaId(), "ThumbMediaId");
    assertEquals(wxMessage.getAppId(), "AppId");
    assertEquals(wxMessage.getEvent(), "user_enter_tempsession");
    assertEquals(wxMessage.getSessionFrom(), "sessionFrom");
  }

  public void testSubscribeMsgPopupEvent() {
    // xml 格式
    String xml = "<xml>" +
      "<ToUserName><![CDATA[gh_123456789abc]]></ToUserName>\n" +
      "<FromUserName><![CDATA[otFpruAK8D-E6EfStSYonYSBZ8_4]]></FromUserName>\n" +
      "<CreateTime>1610969440</CreateTime>\n" +
      "<MsgType><![CDATA[event]]></MsgType>\n" +
      "<Event><![CDATA[subscribe_msg_popup_event]]></Event>\n" +
      "<SubscribeMsgPopupEvent>\n" +
      " <List>\n" +
      "   <TemplateId><![CDATA[VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc]]></TemplateId>\n" +
      "   <SubscribeStatusString><![CDATA[accept]]></SubscribeStatusString>\n" +
      "   <PopupScene>0</PopupScene>\n" +
      " </List>\n" +
      "</SubscribeMsgPopupEvent>" +
      "</xml>";

    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    checkSubscribeMsgPopupEvent(wxMessage);

    // 订阅单个模板  json格式 (对象）
    String json = "{\n" +
      "  \"ToUserName\": \"gh_123456789abc\",\n" +
      "  \"FromUserName\": \"otFpruAK8D-E6EfStSYonYSBZ8_4\",\n" +
      "  \"CreateTime\": \"1610969440\",\n" +
      "  \"MsgType\": \"event\",\n" +
      "  \"Event\": \"subscribe_msg_popup_event\",\n" +
      "  \"List\": {\n" +
      "        \"TemplateId\": \"VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc\",\n" +
      "        \"SubscribeStatusString\": \"accept\",\n" +
      "        \"PopupScene\": \"0\"\n" +
      "    }\n" +
      " }";
    wxMessage = WxMaMessage.fromJson(json);
    checkSubscribeMsgPopupEvent(wxMessage);
    // 订阅多条模板的  json格式（数组）
    json = "{\n" +
      "  \"ToUserName\": \"gh_123456789abc\",\n" +
      "  \"FromUserName\": \"otFpruAK8D-E6EfStSYonYSBZ8_4\",\n" +
      "  \"CreateTime\": \"1610969440\",\n" +
      "  \"MsgType\": \"event\",\n" +
      "  \"Event\": \"subscribe_msg_popup_event\",\n" +
      "  \"List\": [{\n" +
      "        \"TemplateId\": \"VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc\",\n" +
      "        \"SubscribeStatusString\": \"accept\",\n" +
      "        \"PopupScene\": \"0\"\n" +
      "    }]\n" +
      " }";
    wxMessage = WxMaMessage.fromJson(json);
    checkSubscribeMsgPopupEvent(wxMessage);
  }

  private void checkSubscribeMsgPopupEvent(WxMaMessage wxMessage) {
    assertEquals(wxMessage.getToUser(), "gh_123456789abc");
    assertEquals(wxMessage.getFromUser(), "otFpruAK8D-E6EfStSYonYSBZ8_4");
    assertEquals(wxMessage.getCreateTime(), new Integer(1610969440));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "subscribe_msg_popup_event");
    assertEquals(wxMessage.getSubscribeMsgPopupEvent().getList().size(), 1);
    WxMaSubscribeMsgEvent.PopupEvent event = wxMessage.getSubscribeMsgPopupEvent().getList().get(0);
    assertEquals(event.getTemplateId(), "VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc");
    assertEquals(event.getSubscribeStatusString(), "accept");
    assertEquals(event.getPopupScene(), "0");
  }

  public void testSubscribeMsgChangeEvent() {
    // xml 格式
    String xml = "<xml>\n" +
      "    <ToUserName><![CDATA[gh_123456789abc]]></ToUserName>\n" +
      "    <FromUserName><![CDATA[o7esq5OI1Uej6Xixw1lA2H7XDVbc]]></FromUserName>\n" +
      "    <CreateTime>1610968440</CreateTime>\n" +
      "    <MsgType><![CDATA[event]]></MsgType>\n" +
      "    <Event><![CDATA[subscribe_msg_change_event]]></Event>\n" +
      "    <SubscribeMsgChangeEvent>\n" +
      "        <List>" +
      "             <TemplateId><![CDATA[BEwX0BOT3MqK3Uc5oTU3CGBqzjpndk2jzUf7VfExd8]]></TemplateId>\n" +
      "            <SubscribeStatusString><![CDATA[reject]]></SubscribeStatusString>\n" +
      "        </List>\n" +
      "    </SubscribeMsgChangeEvent>\n" +
      "</xml>";

    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    checkSubscribeMsgChangeEvent(wxMessage);

    // json格式 (对象）
    String json = "{\n" +
      "      \"ToUserName\": \"gh_123456789abc\",\n" +
      "      \"FromUserName\": \"o7esq5OI1Uej6Xixw1lA2H7XDVbc\",\n" +
      "      \"CreateTime\": \"1610968440\",\n" +
      "      \"MsgType\": \"event\",\n" +
      "      \"Event\": \"subscribe_msg_change_event\",\n" +
      "      \"List\": {\n" +
      "                \"TemplateId\":\"BEwX0BOT3MqK3Uc5oTU3CGBqzjpndk2jzUf7VfExd8\",\n" +
      "                \"SubscribeStatusString\": \"reject\"\n" +
      "      }\n" +
      "}\n";
    wxMessage = WxMaMessage.fromJson(json);
    checkSubscribeMsgChangeEvent(wxMessage);
    // json格式（数组）
    json = "{\n" +
      "      \"ToUserName\": \"gh_123456789abc\",\n" +
      "      \"FromUserName\": \"o7esq5OI1Uej6Xixw1lA2H7XDVbc\",\n" +
      "      \"CreateTime\": \"1610968440\",\n" +
      "      \"MsgType\": \"event\",\n" +
      "      \"Event\": \"subscribe_msg_change_event\",\n" +
      "      \"List\": [  {\n" +
      "                \"TemplateId\":\"BEwX0BOT3MqK3Uc5oTU3CGBqzjpndk2jzUf7VfExd8\",\n" +
      "                \"SubscribeStatusString\": \"reject\"\n" +
      "      }]" +
      "}";
    wxMessage = WxMaMessage.fromJson(json);
    checkSubscribeMsgChangeEvent(wxMessage);
  }

  private void checkSubscribeMsgChangeEvent(WxMaMessage wxMessage) {
    assertEquals(wxMessage.getToUser(), "gh_123456789abc");
    assertEquals(wxMessage.getFromUser(), "o7esq5OI1Uej6Xixw1lA2H7XDVbc");
    assertEquals(wxMessage.getCreateTime(), new Integer(1610968440));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "subscribe_msg_change_event");
    assertEquals(wxMessage.getSubscribeMsgChangeEvent().getList().size(), 1);
    WxMaSubscribeMsgEvent.ChangeEvent event = wxMessage.getSubscribeMsgChangeEvent().getList().get(0);
    assertEquals(event.getTemplateId(), "BEwX0BOT3MqK3Uc5oTU3CGBqzjpndk2jzUf7VfExd8");
    assertEquals(event.getSubscribeStatusString(), "reject");
  }

  public void testSubscribeMsgSentEvent() {
    // xml 格式
    String xml = "<xml>\n" +
      "    <ToUserName><![CDATA[gh_123456789abc]]></ToUserName>\n" +
      "    <FromUserName><![CDATA[o7esq5PHRGBQYmeNyfG064wEFVpQ]]></FromUserName>\n" +
      "    <CreateTime>1620963428</CreateTime>\n" +
      "    <MsgType><![CDATA[event]]></MsgType>\n" +
      "    <Event><![CDATA[subscribe_msg_sent_event]]></Event>\n" +
      "    <SubscribeMsgSentEvent>\n" +
      "        <List>" +
      "             <TemplateId><![CDATA[BEwX0BO-T3MqK3Uc5oTU3CGBqzjpndk2jzUf7VfExd8]]></TemplateId>\n" +
      "            <MsgID>1864323726461255680</MsgID>\n" +
      "            <ErrorCode>0</ErrorCode>\n" +
      "            <ErrorStatus><![CDATA[success]]></ErrorStatus>\n" +
      "        </List>\n" +
      "    </SubscribeMsgSentEvent>\n" +
      "</xml>";

    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    checkSubscribeMsgSentEvent(wxMessage);

    // json格式 (对象）
    String json = "{\n" +
      "    \"ToUserName\": \"gh_123456789abc\",\n" +
      "    \"FromUserName\": \"o7esq5PHRGBQYmeNyfG064wEFVpQ\",\n" +
      "    \"CreateTime\": \"1620963428\",\n" +
      "    \"MsgType\": \"event\",\n" +
      "    \"Event\": \"subscribe_msg_sent_event\",\n" +
      "    \"List\": {\n" +
      "        \"TemplateId\": \"BEwX0BO-T3MqK3Uc5oTU3CGBqzjpndk2jzUf7VfExd8\",\n" +
      "        \"MsgID\": \"1864323726461255680\",\n" +
      "        \"ErrorCode\": \"0\",\n" +
      "        \"ErrorStatus\": \"success\"\n" +
      "      }\n" +
      "}";
    wxMessage = WxMaMessage.fromJson(json);
    checkSubscribeMsgSentEvent(wxMessage);
  }

  private void checkSubscribeMsgSentEvent(WxMaMessage wxMessage) {
    assertEquals(wxMessage.getToUser(), "gh_123456789abc");
    assertEquals(wxMessage.getFromUser(), "o7esq5PHRGBQYmeNyfG064wEFVpQ");
    assertEquals(wxMessage.getCreateTime(), new Integer(1620963428));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "subscribe_msg_sent_event");
    assertNotNull(wxMessage.getSubscribeMsgSentEvent());
    WxMaSubscribeMsgEvent.SentEvent event = wxMessage.getSubscribeMsgSentEvent().getList();
    assertEquals(event.getTemplateId(), "BEwX0BO-T3MqK3Uc5oTU3CGBqzjpndk2jzUf7VfExd8");
    assertEquals(event.getMsgId(), "1864323726461255680");
    assertEquals(event.getErrorCode(), "0");
    assertEquals(event.getErrorStatus(), "success");
  }

  @Test
  public void testFromXmlForAllFieldsMap() {
    String xml = "<xml>\n" +
      "    <ToUserName><![CDATA[gh_3953b390c11d]]></ToUserName>\n" +
      "    <FromUserName><![CDATA[ofYMP5JFT4SD7EX1LQv3IWrciBSo]]></FromUserName>\n" +
      "    <CreateTime>1642658087</CreateTime>\n" +
      "    <MsgType><![CDATA[event]]></MsgType>\n" +
      "    <Event><![CDATA[add_express_path]]></Event>\n" +
      "    <DeliveryID><![CDATA[TEST]]></DeliveryID>\n" +
      "    <WayBillId><![CDATA[01234567894_waybill_id]]></WayBillId>\n" +
      "    <Version>16</Version>\n" +
      "    <Count>2</Count>\n" +
      "    <Actions>\n" +
      "        <ActionTime>1642605533</ActionTime>\n" +
      "        <ActionType>300001</ActionType>\n" +
      "        <ActionMsg><![CDATA[揽件阶段-揽件成功]]></ActionMsg>\n" +
      "        <Lat>0</Lat>\n" +
      "        <Lng>0</Lng>\n" +
      "    </Actions>\n" +
      "    <Actions>\n" +
      "        <ActionTime>1642605533</ActionTime>\n" +
      "        <ActionType>100001</ActionType>\n" +
      "        <ActionMsg><![CDATA[揽件阶段-揽件成功]]></ActionMsg>\n" +
      "        <Lat>0</Lat>\n" +
      "        <Lng>0</Lng>\n" +
      "    </Actions>\n" +
      "    <OrderId><![CDATA[01234567894]]></OrderId>\n" +
      "</xml>";

    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    Map<String, Object> allFieldsMap = wxMessage.getAllFieldsMap();
    assertThat(allFieldsMap).isNotEmpty()
      .containsEntry("ToUserName", "gh_3953b390c11d")
      .containsEntry("FromUserName", "ofYMP5JFT4SD7EX1LQv3IWrciBSo")
      .containsEntry("CreateTime", "1642658087")
      .containsEntry("MsgType", "event")
      .containsEntry("Event", "add_express_path")
      .containsEntry("DeliveryID", "TEST")
      .containsEntry("WayBillId", "01234567894_waybill_id")
      .containsEntry("Version", "16")
      .containsEntry("Count", "2")
      .containsEntry("OrderId", "01234567894");

    List<Map<String, Object>> actions = (List<Map<String, Object>>) allFieldsMap.get("Actions");
    assertThat(actions).isNotEmpty().hasSize(2);

    assertThat(actions.get(0))
      .containsEntry("ActionTime", "1642605533")
      .containsEntry("ActionType", "300001")
      .containsEntry("ActionMsg", "揽件阶段-揽件成功")
      .containsEntry("Lat", "0")
      .containsEntry("Lng", "0");

    assertThat(actions.get(1))
      .containsEntry("ActionTime", "1642605533")
      .containsEntry("ActionType", "100001")
      .containsEntry("ActionMsg", "揽件阶段-揽件成功")
      .containsEntry("Lat", "0")
      .containsEntry("Lng", "0");
  }

  /**
   * 虚拟支付退款推送事件 xpay_refund_notify 测试用例（XML格式，含TeamInfo）
   */
  @Test
  public void testXPayRefundNotifyFromXml() {
    String xml = "<xml>\n" +
      "  <ToUserName><![CDATA[gh_abcdefg]]></ToUserName>\n" +
      "  <FromUserName><![CDATA[oABCDEFG]]></FromUserName>\n" +
      "  <CreateTime>1700000000</CreateTime>\n" +
      "  <MsgType><![CDATA[event]]></MsgType>\n" +
      "  <Event><![CDATA[xpay_refund_notify]]></Event>\n" +
      "  <OpenId><![CDATA[oABCDEFG]]></OpenId>\n" +
      "  <WxRefundId><![CDATA[wx_refund_123]]></WxRefundId>\n" +
      "  <MchRefundId><![CDATA[mch_refund_456]]></MchRefundId>\n" +
      "  <WxOrderId><![CDATA[wx_order_789]]></WxOrderId>\n" +
      "  <MchOrderId><![CDATA[mch_order_101]]></MchOrderId>\n" +
      "  <RefundFee>100</RefundFee>\n" +
      "  <RetCode>0</RetCode>\n" +
      "  <RetMsg><![CDATA[success]]></RetMsg>\n" +
      "  <RefundStartTimestamp>1700000000</RefundStartTimestamp>\n" +
      "  <RefundSuccTimestamp>1700000010</RefundSuccTimestamp>\n" +
      "  <WxpayRefundTransactionId><![CDATA[wxpay_refund_tx_202]]></WxpayRefundTransactionId>\n" +
      "  <RetryTimes>0</RetryTimes>\n" +
      "  <TeamInfo>\n" +
      "    <ActivityId><![CDATA[act_001]]></ActivityId>\n" +
      "    <TeamId><![CDATA[team_002]]></TeamId>\n" +
      "    <TeamType>1</TeamType>\n" +
      "    <TeamAction>0</TeamAction>\n" +
      "  </TeamInfo>\n" +
      "</xml>";

    WxMaMessage msg = WxMaMessage.fromXml(xml);
    checkXPayRefundNotifyMessage(msg);
  }

  /**
   * 虚拟支付退款推送事件 xpay_refund_notify 测试用例（JSON格式，含TeamInfo）
   */
  @Test
  public void testXPayRefundNotifyFromJson() {
    String json = "{\n" +
      "  \"ToUserName\": \"gh_abcdefg\",\n" +
      "  \"FromUserName\": \"oABCDEFG\",\n" +
      "  \"CreateTime\": 1700000000,\n" +
      "  \"MsgType\": \"event\",\n" +
      "  \"Event\": \"xpay_refund_notify\",\n" +
      "  \"OpenId\": \"oABCDEFG\",\n" +
      "  \"WxRefundId\": \"wx_refund_123\",\n" +
      "  \"MchRefundId\": \"mch_refund_456\",\n" +
      "  \"WxOrderId\": \"wx_order_789\",\n" +
      "  \"MchOrderId\": \"mch_order_101\",\n" +
      "  \"RefundFee\": 100,\n" +
      "  \"RetCode\": 0,\n" +
      "  \"RetMsg\": \"success\",\n" +
      "  \"RefundStartTimestamp\": 1700000000,\n" +
      "  \"RefundSuccTimestamp\": 1700000010,\n" +
      "  \"WxpayRefundTransactionId\": \"wxpay_refund_tx_202\",\n" +
      "  \"RetryTimes\": 0,\n" +
      "  \"TeamInfo\": {\n" +
      "    \"ActivityId\": \"act_001\",\n" +
      "    \"TeamId\": \"team_002\",\n" +
      "    \"TeamType\": 1,\n" +
      "    \"TeamAction\": 0\n" +
      "  }\n" +
      "}";

    WxMaMessage msg = WxMaMessage.fromJson(json);
    checkXPayRefundNotifyMessage(msg);
  }

  private void checkXPayRefundNotifyMessage(WxMaMessage msg) {
    assertEquals(msg.getToUser(), "gh_abcdefg");
    assertEquals(msg.getFromUser(), "oABCDEFG");
    assertEquals(msg.getCreateTime(), new Integer(1700000000));
    assertEquals(msg.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(msg.getEvent(), "xpay_refund_notify");
    assertEquals(msg.getWxRefundId(), "wx_refund_123");
    assertEquals(msg.getMchRefundId(), "mch_refund_456");
    assertEquals(msg.getWxOrderId(), "wx_order_789");
    assertEquals(msg.getMchOrderId(), "mch_order_101");
    assertEquals(msg.getRefundFee(), new Integer(100));
    assertEquals(msg.getRetCode(), new Integer(0));
    assertEquals(msg.getRetMsg(), "success");
    assertEquals(msg.getRefundStartTimestamp(), new Long(1700000000L));
    assertEquals(msg.getRefundSuccTimestamp(), new Long(1700000010L));
    assertEquals(msg.getWxpayRefundTransactionId(), "wxpay_refund_tx_202");
    assertEquals(msg.getRetryTimes(), new Integer(0));
    WxMaXPayTeamInfo teamInfo = msg.getTeamInfo();
    assertNotNull(teamInfo);
    assertEquals(teamInfo.getActivityId(), "act_001");
    assertEquals(teamInfo.getTeamId(), "team_002");
    assertEquals(teamInfo.getTeamType(), new Integer(1));
    assertEquals(teamInfo.getTeamAction(), new Integer(0));
  }

  /**
   * 虚拟支付投诉推送事件 xpay_complaint_notify 测试用例（XML格式）
   */
  @Test
  public void testXPayComplaintNotifyFromXml() {
    String xml = "<xml>\n" +
      "  <ToUserName><![CDATA[gh_abcdefg]]></ToUserName>\n" +
      "  <FromUserName><![CDATA[official_openid]]></FromUserName>\n" +
      "  <CreateTime>1700000100</CreateTime>\n" +
      "  <MsgType><![CDATA[event]]></MsgType>\n" +
      "  <Event><![CDATA[xpay_complaint_notify]]></Event>\n" +
      "  <OpenId><![CDATA[user_openid_abc]]></OpenId>\n" +
      "  <WxOrderId><![CDATA[wx_order_cmp_001]]></WxOrderId>\n" +
      "  <MchOrderId><![CDATA[mch_order_cmp_002]]></MchOrderId>\n" +
      "  <TransactionId><![CDATA[transaction_cmp_003]]></TransactionId>\n" +
      "  <ComplaintId><![CDATA[complaint_004]]></ComplaintId>\n" +
      "  <ComplaintDetail><![CDATA[商品未收到]]></ComplaintDetail>\n" +
      "  <ComplaintTime>1700000050</ComplaintTime>\n" +
      "  <RetryTimes>0</RetryTimes>\n" +
      "  <RequestId><![CDATA[req_005]]></RequestId>\n" +
      "</xml>";

    WxMaMessage msg = WxMaMessage.fromXml(xml);
    checkXPayComplaintNotifyMessage(msg);
  }

  /**
   * 虚拟支付投诉推送事件 xpay_complaint_notify 测试用例（JSON格式）
   */
  @Test
  public void testXPayComplaintNotifyFromJson() {
    String json = "{\n" +
      "  \"ToUserName\": \"gh_abcdefg\",\n" +
      "  \"FromUserName\": \"official_openid\",\n" +
      "  \"CreateTime\": 1700000100,\n" +
      "  \"MsgType\": \"event\",\n" +
      "  \"Event\": \"xpay_complaint_notify\",\n" +
      "  \"OpenId\": \"user_openid_abc\",\n" +
      "  \"WxOrderId\": \"wx_order_cmp_001\",\n" +
      "  \"MchOrderId\": \"mch_order_cmp_002\",\n" +
      "  \"TransactionId\": \"transaction_cmp_003\",\n" +
      "  \"ComplaintId\": \"complaint_004\",\n" +
      "  \"ComplaintDetail\": \"商品未收到\",\n" +
      "  \"ComplaintTime\": 1700000050,\n" +
      "  \"RetryTimes\": 0,\n" +
      "  \"RequestId\": \"req_005\"\n" +
      "}";

    WxMaMessage msg = WxMaMessage.fromJson(json);
    checkXPayComplaintNotifyMessage(msg);
  }

  private void checkXPayComplaintNotifyMessage(WxMaMessage msg) {
    assertEquals(msg.getToUser(), "gh_abcdefg");
    assertEquals(msg.getFromUser(), "official_openid");
    assertEquals(msg.getCreateTime(), new Integer(1700000100));
    assertEquals(msg.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(msg.getEvent(), "xpay_complaint_notify");
    assertEquals(msg.getOpenId(), "user_openid_abc");
    assertEquals(msg.getWxOrderId(), "wx_order_cmp_001");
    assertEquals(msg.getMchOrderId(), "mch_order_cmp_002");
    assertEquals(msg.getComplaintTransactionId(), "transaction_cmp_003");
    assertEquals(msg.getComplaintId(), "complaint_004");
    assertEquals(msg.getComplaintDetail(), "商品未收到");
    assertEquals(msg.getComplaintTime(), new Long(1700000050L));
    assertEquals(msg.getRetryTimes(), new Integer(0));
    assertEquals(msg.getRequestId(), "req_005");
  }
}
