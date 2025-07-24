package me.chanjar.weixin.cp.api.impl;

import static org.testng.Assert.assertNotNull;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactBatchInfo;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactInfo;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactListInfo;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.bean.external.msg.AttachmentBuilder;
import me.chanjar.weixin.cp.bean.external.msg.Image;
import me.chanjar.weixin.cp.bean.external.msg.Video;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

/**
 * The type Wx cp external contact service impl test.
 */
@Guice(modules = ApiTestModule.class)
public class WxCpExternalContactServiceImplTest {
  @Inject
  private WxCpService wxCpService;
  /**
   * The Config storage.
   */
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;
  private final String userId = "someone" + System.currentTimeMillis();

  /**
   * Test get external contact.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetExternalContact() throws WxErrorException {
    String externalUserId = this.configStorage.getExternalUserId();
    WxCpExternalContactInfo result = this.wxCpService.getExternalContactService().getExternalContact(externalUserId);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test add contact way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testAddContactWay() throws WxErrorException {

    final String concatUserId = "HuangXiaoMing";

    WxCpContactWayInfo.ContactWay wayInfo = new WxCpContactWayInfo.ContactWay();
    wayInfo.setType(WxCpContactWayInfo.TYPE.SINGLE);
    wayInfo.setScene(WxCpContactWayInfo.SCENE.QRCODE);
    wayInfo.setUsers(Lists.newArrayList(concatUserId));
    wayInfo.setRemark("CreateDate:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));

    WxCpContactWayInfo info = new WxCpContactWayInfo();
    info.setContactWay(wayInfo);
    this.wxCpService.getExternalContactService().addContactWay(info);
  }

  /**
   * Test get contact way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetContactWay() throws WxErrorException {
    final String configId = "39fea3d93e30faaa8c7a9edd4cfe4d08";
    WxCpContactWayInfo contactWayInfo = this.wxCpService.getExternalContactService().getContactWay(configId);
    System.out.println(contactWayInfo.toJson());
    assertNotNull(contactWayInfo);
  }

  /**
   * Test list contact way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListContactWay() throws WxErrorException {
    long startTime = LocalDateTime.now().minusDays(1).toEpochSecond(ZoneOffset.of("+8"));
    long endTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    WxCpContactWayList wxCpContactWayList = this.wxCpService.getExternalContactService().listContactWay(startTime, endTime, null, 100L);
    System.out.println(wxCpContactWayList.toJson());
    assertNotNull(wxCpContactWayList);
  }

  /**
   * Test update contact way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateContactWay() throws WxErrorException {
    final String configId = "2d7a68c657663afbd1d90db19a4b5ee9";
    final String concatUserId = "符合要求的userId";
    WxCpContactWayInfo.ContactWay wayInfo = new WxCpContactWayInfo.ContactWay();
    wayInfo.setConfigId(configId);
    wayInfo.setUsers(Lists.newArrayList(concatUserId));
    wayInfo.setRemark("CreateDate:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));
    WxCpContactWayInfo info = new WxCpContactWayInfo();
    info.setContactWay(wayInfo);
    WxCpBaseResp resp = this.wxCpService.getExternalContactService().updateContactWay(info);
    System.out.println(resp);
    assertNotNull(resp);
  }

  /**
   * Test del contact way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testDelContactWay() throws WxErrorException {
    final String configId = "2d7a68c657663afbd1d90db19a4b5ee9";
    WxCpBaseResp resp = this.wxCpService.getExternalContactService().deleteContactWay(configId);
    System.out.println(resp);
    assertNotNull(resp);
  }

  /**
   * Test close temp chat.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testCloseTempChat() throws WxErrorException {
    final String externalUserId = "externalUserId";
    WxCpBaseResp resp = this.wxCpService.getExternalContactService().closeTempChat(userId, externalUserId);
    System.out.println(resp);
  }

  /**
   * Test list external contacts.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListExternalContacts() throws WxErrorException {
    String userId = this.configStorage.getUserId();
    List<String> ret = this.wxCpService.getExternalContactService().listExternalContacts(userId);
    System.out.println(ret);
    assertNotNull(ret);
  }

  /**
   * Test list external with permission.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListExternalWithPermission() throws WxErrorException {
    List<String> ret = this.wxCpService.getExternalContactService().listFollowers();
    System.out.println(ret);
    assertNotNull(ret);
  }

  /**
   * Test get contact detail.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetContactDetail() throws WxErrorException {
    String externalUserId = this.configStorage.getExternalUserId();
    WxCpExternalContactInfo result = this.wxCpService.getExternalContactService().getContactDetail(externalUserId,
      null);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test get contact detail batch.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetContactDetailBatch() throws WxErrorException {
    String userId = this.configStorage.getUserId();
    WxCpExternalContactBatchInfo result =
      this.wxCpService.getExternalContactService().getContactDetailBatch(new String[]{userId}, "", 100);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test get contact list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetContactList() throws WxErrorException {
    WxCpExternalContactListInfo result =
      this.wxCpService.getExternalContactService().getContactList("", 100);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test get corp tag list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCorpTagList() throws WxErrorException {
    String[] tag = {};
    WxCpUserExternalTagGroupList result = this.wxCpService.getExternalContactService().getCorpTagList(null);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test add corp tag.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testAddCorpTag() throws WxErrorException {

    List<WxCpUserExternalTagGroupInfo.Tag> list = new ArrayList<>();
    WxCpUserExternalTagGroupInfo.Tag tag = new WxCpUserExternalTagGroupInfo.Tag();
    tag.setName("测试标签20");
    tag.setOrder(1L);
    list.add(tag);

    WxCpUserExternalTagGroupInfo tagGroupInfo = new WxCpUserExternalTagGroupInfo();
    WxCpUserExternalTagGroupInfo.TagGroup tagGroup = new WxCpUserExternalTagGroupInfo.TagGroup();
    tagGroup.setGroupName("其他");
    tagGroup.setOrder(1L);
    tagGroup.setTag(list);
    tagGroupInfo.setTagGroup(tagGroup);

    WxCpUserExternalTagGroupInfo result = this.wxCpService.getExternalContactService().addCorpTag(tagGroupInfo);

    System.out.println(result.toJson());
    assertNotNull(result);
  }

  /**
   * Test edit corp tag.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testEditCorpTag() throws WxErrorException {

    WxCpBaseResp result = this.wxCpService.getExternalContactService().editCorpTag("et2omCCwAA6PtGsfeEOQMENl3Ub1FA6A"
      , "未知6", 2);

    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test del corp tag.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testDelCorpTag() throws WxErrorException {

    String[] tagId = {};
    String[] groupId = {"et2omCCwAAM3WzL00QpK9xARab3HGkAg"};

    WxCpBaseResp result = this.wxCpService.getExternalContactService().delCorpTag(tagId, groupId);

    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test mark tag.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testMarkTag() throws WxErrorException {

    String userid = "HuangXiaoMing";
    String externalUserid = "wo2omCCwAAzR0Rt1omz-90o_XJkPGXIQ";
    String[] addTag = {"et2omCCwAAzdcSK-RV80YS9sbpCXlNlQ"};
    String[] removeTag = {};

    WxCpBaseResp result = this.wxCpService.getExternalContactService().markTag(userid, externalUserid, addTag,
      removeTag);

    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test delete contact way.
   */
  @Test
  public void testDeleteContactWay() {
  }

  /**
   * Test list followers.
   */
  @Test
  public void testListFollowers() {
  }

  /**
   * Test list unassigned list.
   */
  @Test
  public void testListUnassignedList() {
  }

  /**
   * Test transfer external contact.
   */
  @Test
  public void testTransferExternalContact() {
  }

  /**
   * Test transfer customer.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testTransferCustomer() throws WxErrorException {
    WxCpUserTransferCustomerReq req = new WxCpUserTransferCustomerReq();
    req.setExternalUserid(Collections.emptyList());
    req.setHandOverUserid("123");
    req.setTakeOverUserid("234");
    WxCpBaseResp result = this.wxCpService.getExternalContactService().transferCustomer(req);

    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test trnsfer result.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testTrnsferResult() throws WxErrorException {
    WxCpUserTransferResultResp result = this.wxCpService.getExternalContactService().transferResult("123", "234", "");
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Testresigned transfer customer.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testresignedTransferCustomer() throws WxErrorException {
    WxCpUserTransferCustomerReq req = new WxCpUserTransferCustomerReq();
    req.setExternalUserid(Collections.emptyList());
    req.setHandOverUserid("123");
    req.setTakeOverUserid("234");
    WxCpBaseResp result = this.wxCpService.getExternalContactService().resignedTransferCustomer(req);

    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Testresigned trnsfer result.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testresignedTrnsferResult() throws WxErrorException {
    WxCpUserTransferResultResp result = this.wxCpService.getExternalContactService().resignedTransferResult("123",
      "234", "");
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test list group chat.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListGroupChat() throws WxErrorException {
    WxCpUserExternalGroupChatList result = this.wxCpService.getExternalContactService().listGroupChat(0, 100, 0,
      new String[1], new String[1]);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test list group chat v 3.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListGroupChatV3() throws WxErrorException {
    WxCpUserExternalGroupChatList result = this.wxCpService.getExternalContactService().listGroupChat(100, "", 0,
      new String[1]);
    System.out.println(result);
    assertNotNull(result);
  }


  /**
   * Test get group chat.
   */
  @Test
  public void testGetGroupChat() throws WxErrorException {
    final WxCpUserExternalGroupChatInfo result = this.wxCpService.getExternalContactService().getGroupChat("wrOgQhDgAAMYQiS5ol9G7gK9JVAAAA", 1);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test transfer group chat.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testTransferGroupChat() throws WxErrorException {
    String[] str = {"wri1_QEAAATfnZl_VJ4hlQda0e4Mgf1A"};
    WxCpUserExternalGroupChatTransferResp result = this.wxCpService.getExternalContactService().transferGroupChat(str
      , "123");
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test onjob transfer group chat.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testOnjobTransferGroupChat() throws WxErrorException {
    String[] str = {"wrHlLKQAAAFbfB99-BO97YZlcywznGZg", "error_group_id"};
    WxCpUserExternalGroupChatTransferResp result = this.wxCpService.getExternalContactService().onjobTransferGroupChat(str
      , "x");
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test get user behavior statistic.
   */
  @Test
  public void testGetUserBehaviorStatistic() {
  }

  /**
   * Test get group chat statistic.
   */
  @Test
  public void testGetGroupChatStatistic() {
  }

  /**
   * Test add msg template.
   */
  @Test
  public void testAddMsgTemplate() {
  }

  /**
   * Test send welcome msg.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendWelcomeMsg() throws WxErrorException {
    Image image = new Image();
    image.setMediaId("123123");
    Attachment attachment = new Attachment();
    attachment.setImage(image);

    Video video = new Video();
    video.setMediaId("video_media_id");
    Attachment attachment2 = new Attachment();
    attachment2.setVideo(video);

    List<Attachment> attachments = new ArrayList<>();
    attachments.add(attachment);
    attachments.add(attachment2);
    this.wxCpService.getExternalContactService().sendWelcomeMsg(WxCpWelcomeMsg.builder()
      .welcomeCode("abc")
      .attachments(attachments)
      .build());
  }

  /**
   * Test send welcome msg. use AttachmentBuilder
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendWelcomeMsg2() throws WxErrorException {

    Attachment imageAttachment = AttachmentBuilder.imageBuilder().mediaId("123123").build();
    Attachment videoAttachment = AttachmentBuilder.videoBuilder().mediaId("video_media_id").build();
    Attachment miniProgramAttachment = AttachmentBuilder.miniProgramBuilder()
      .title("title")
      .picMediaId("123123123")
      .appId("wxcxxxxxxxxxxx")
      .page("https://")
      .build();

    List<Attachment> attachments = new ArrayList<>();
    attachments.add(imageAttachment);
    attachments.add(videoAttachment);
    attachments.add(miniProgramAttachment);
    this.wxCpService.getExternalContactService().sendWelcomeMsg(WxCpWelcomeMsg.builder()
      .welcomeCode("abc")
      .attachments(attachments)
      .build());
  }

  /**
   * Test update remark.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateRemark() throws WxErrorException {
    this.wxCpService.getExternalContactService().updateRemark(WxCpUpdateRemarkRequest.builder()
      .description("abc")
      .userId("aaa")
      .externalUserId("aaa")
      .remark("aa")
      .remarkCompany("aaa")
      .remarkMobiles(new String[]{"111", "222"})
      .remarkPicMediaId("aaa")
      .build());
  }

  /**
   * Test get product list album.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetProductListAlbum() throws WxErrorException {
    WxCpProductAlbumListResult result = this.wxCpService.getExternalContactService()
      .getProductAlbumList(100, null);
    System.out.println(result);
    assertNotNull(result);
    if (CollectionUtils.hasElements(result.getProductList())) {
      WxCpProductAlbumResult result1 =
        this.wxCpService.getExternalContactService().getProductAlbum(result.getProductList().get(0).getProductId());
      System.out.println(result1);
      assertNotNull(result1);
    }
  }

  /**
   * Test get moment list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetMomentList() throws WxErrorException {
    WxCpGetMomentList result = this.wxCpService.getExternalContactService()
      .getMomentList(1636732800L, 1636991999L, null, null, null, null);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test add join way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testAddJoinWay() throws WxErrorException {


    WxCpGroupJoinWayInfo.JoinWay joinWay = new WxCpGroupJoinWayInfo.JoinWay();
    joinWay.setChatIdList(Collections.singletonList("wrfpBaCwAAxR-iIqIUa5vvbpZQcAexJA"));
    joinWay.setScene(2);
    joinWay.setAutoCreateRoom(1);
    joinWay.setRemark("CreateDate:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));

    WxCpGroupJoinWayInfo info = new WxCpGroupJoinWayInfo();
    info.setJoinWay(joinWay);
    this.wxCpService.getExternalContactService().addJoinWay(info);
  }

  /**
   * Test update join way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateJoinWay() throws WxErrorException {

    final String configId = "";

    WxCpGroupJoinWayInfo.JoinWay joinWay = new WxCpGroupJoinWayInfo.JoinWay();
    joinWay.setConfigId(configId);
    joinWay.setChatIdList(Collections.singletonList("wrfpBaCwAAxR-iIqIUa5vvbpZQcAexJA"));
    joinWay.setScene(2);
    joinWay.setAutoCreateRoom(1);
    joinWay.setRemark("CreateDate:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));

    WxCpGroupJoinWayInfo info = new WxCpGroupJoinWayInfo();
    info.setJoinWay(joinWay);
    this.wxCpService.getExternalContactService().updateJoinWay(info);
  }

  /**
   * Test del join way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testDelJoinWay() throws WxErrorException {

    final String configId = "";

    this.wxCpService.getExternalContactService().delJoinWay(configId);
  }

  /**
   * Test get join way.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetJoinWay() throws WxErrorException {

    final String configId = "";

    this.wxCpService.getExternalContactService().getJoinWay(configId);
  }

  /**
   * 提醒成员群发
   *
   * @throws WxErrorException
   */
  @Test
  public void testRemindGroupMsgSend() throws WxErrorException {
    this.wxCpService.getExternalContactService()
      .remindGroupMsgSend("msgGCAAAXtWyujaWJHDDGi0mACAAAA");
  }

  /**
   * 测试取消提醒成员群发
   *
   * @throws WxErrorException
   */
  @Test
  public void testCancelGroupMsgSend() throws WxErrorException {
    this.wxCpService.getExternalContactService()
      .cancelGroupMsgSend("msgGCAAAXtWyujaWJHDDGi0mACAAAA");
  }

  /**
   * 获客助手事件通知
   * https://developer.work.weixin.qq.com/document/path/97299
   *
   * @throws WxErrorException
   */
  @Test
  public void testEvent() throws WxErrorException {

    /**
     * 获客额度即将耗尽事件
     */
    String xml1 = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[customer_acquisition]]></Event>\n" +
      "\t<ChangeType><![CDATA[balance_low]]></ChangeType>\n" +
      "</xml>";

    WxCpXmlMessage msg1 = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml1);
    msg1.setAllFieldsMap(XmlUtils.xml2Map(xml1));
    System.out.println("获客额度即将耗尽事件：" + WxCpGsonBuilder.create().toJson(msg1));

    /**
     * 使用量已经耗尽事件
     */
    String xml2 = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[customer_acquisition]]></Event>\n" +
      "\t<ChangeType><![CDATA[balance_exhausted]]></ChangeType>\n" +
      "</xml>";

    WxCpXmlMessage msg2 = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml2);
    msg2.setAllFieldsMap(XmlUtils.xml2Map(xml2));
    System.out.println("使用量已经耗尽事件：" + WxCpGsonBuilder.create().toJson(msg2));

    /**
     * 获客链接不可用事件
     */
    String xml3 = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[customer_acquisition]]></Event>\n" +
      "\t<ChangeType><![CDATA[link_unavailable]]></ChangeType>\n" +
      "\t<LinkId><![CDATA[cawcdea7783d7330b4]]></LinkId>\n" +
      "</xml>";

    WxCpXmlMessage msg3 = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml3);
    msg3.setAllFieldsMap(XmlUtils.xml2Map(xml3));
    System.out.println("获客链接不可用事件：" + WxCpGsonBuilder.create().toJson(msg3));

    /**
     * 微信客户发起会话事件
     */
    String xml4 = "<xml>\n" +
      "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "<CreateTime>1403610513</CreateTime>\n" +
      "<MsgType><![CDATA[event]]></MsgType>\n" +
      "<Event><![CDATA[customer_acquisition]]></Event>\n" +
      "<ChangeType><![CDATA[customer_start_chat]]></ChangeType>\n" +
      "<UserID><![CDATA[zhangsan]]></UserID>\n" +
      "<ExternalUserID><![CDATA[woAJ2GCAAAXtWyujaWJHDDGi0mAAAA]]></ExternalUserID>\n" +
      "</xml>";

    WxCpXmlMessage msg4 = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml4);
    msg4.setAllFieldsMap(XmlUtils.xml2Map(xml4));
    System.out.println("微信客户发起会话事件：" + WxCpGsonBuilder.create().toJson(msg4));

    /**
     * 删除获客链接事件
     */
    String xml5 = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[customer_acquisition]]></Event>\n" +
      "\t<ChangeType><![CDATA[delete_link]]></ChangeType>\n" +
      "\t<LinkId><![CDATA[cawcdea7783d7330b4]]></LinkId>\n" +
      "</xml>";

    WxCpXmlMessage msg5 = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml5);
    msg5.setAllFieldsMap(XmlUtils.xml2Map(xml5));
    System.out.println("删除获客链接事件：" + WxCpGsonBuilder.create().toJson(msg5));

    /**
     * 通过获客链接申请好友事件
     */
    String xml6 = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1689171577</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[customer_acquisition]]></Event>\n" +
      "\t<ChangeType><![CDATA[friend_request]]></ChangeType>\n" +
      "\t<LinkId><![CDATA[cawcdea7783d7330b4]]></LinkId>\n" +
      "\t<State><![CDATA[STATE]]></State>\n" +
      "</xml>";

    WxCpXmlMessage msg6 = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml6);
    msg6.setAllFieldsMap(XmlUtils.xml2Map(xml6));
    System.out.println("通过获客链接申请好友事件：" + WxCpGsonBuilder.create().toJson(msg6));


    /**
     * 获客助手事件通知ChangeType
     * @see me.chanjar.weixin.cp.constant.WxCpConsts.CustomerAcquisitionChangeType.CUSTOMER_START_CHAT
     */

  }

}
