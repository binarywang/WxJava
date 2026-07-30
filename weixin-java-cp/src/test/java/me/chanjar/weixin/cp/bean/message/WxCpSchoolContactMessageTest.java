package me.chanjar.weixin.cp.bean.message;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.school.user.WxCpAllowScope;
import me.chanjar.weixin.cp.bean.school.user.WxCpListParentResult;
import me.chanjar.weixin.cp.bean.school.user.WxCpUserListResult;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 发送「学校通知」消息测试类
 * https://developer.work.weixin.qq.com/document/path/92321
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on  2022-06-29
 */
@Slf4j
public class WxCpSchoolContactMessageTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;


  /**
   * 发送「学校通知」
   * 学校可以通过此接口来给家长发送不同类型的学校通知，来满足多种场景下的学校通知需求。目前支持的消息类型为文本、图片、语音、视频、文件、图文。
   * <p>
   * https://developer.work.weixin.qq.com/document/path/92321
   * <p>
   * 消息体类型请参考测试类
   * WxCpSchoolContactMessageTest
   * {@link WxCpSchoolContactMessageTest}
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendSchoolContactMessage() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);

    // 获取可使用的家长范围 返回的数据
    WxCpAllowScope allowScope = cpService.getSchoolUserService().getAllowScope(1000002);

    WxCpUserListResult userList = cpService.getSchoolUserService().getUserList(1, 1);

    // 测试发送给家长 [学校通知]
    WxCpListParentResult userListParent = cpService.getSchoolUserService().getUserListParent(1);
    List<WxCpListParentResult.Parent> collect = userListParent.getParents()
      .stream()
      .filter(parent -> parent.getMobile().equals("13079226621"))
      .collect(Collectors.toList());

    String[] parentsId = {"ab0b1691d0204d4900f6b7a7e5a6aa8f", collect.get(0).getParentUserId()};

    WxCpSchoolContactMessageSendResult sendResult = cpService.getMessageService().sendSchoolContactMessage(

      WxCpSchoolContactMessage.builder()
        .recvScope(0)
        .msgType(WxConsts.SchoolContactMsgType.NEWS)
        .toParentUserId(parentsId)
//        .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
//        .toParty(new String[]{"partyid1", "partyid2"})
        .toAll(false)
        .agentId(cpService.getWxCpConfigStorage().getAgentId())
        .articles(Lists.newArrayList(NewArticle.builder()
          .title("这是接口测试标题")
          .description("今年中秋节公司有豪礼相送哦")
          .url("https://www.baidu.com/")
          .picUrl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png")
          .build()))
        .build()

    );

    log.info("sendResult: {}", sendResult.toJson());

  }

  /**
   * Test to json text.
   */
//    WxCpConsts.SchoolContactChangeType
  @Test
  public void testToJson_text() {

    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.TEXT)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .content("你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。")
      .enableIdTrans(false)
      .enableDuplicateCheck(false)
      .duplicateCheckInterval(1800)
      .build();

    WxCpSchoolContactMessage schoolContactMessage1 = new WxCpSchoolContactMessage();
    schoolContactMessage1.setMsgType(WxConsts.SchoolContactMsgType.TEXT);
    schoolContactMessage1.setRecvScope(0);
    schoolContactMessage1.setToParentUserId(new String[]{"parent_userid1", "parent_userid2"});
    schoolContactMessage1.setToStudentUserId(new String[]{"student_userid1", "student_userid2"});
    schoolContactMessage1.setToParty(new String[]{"partyid1", "partyid2"});
    schoolContactMessage1.setToAll(false);
    schoolContactMessage1.setAgentId(1L);
    schoolContactMessage1.setContent("你的快递已到，请携带工卡前往邮件中心领取");
    schoolContactMessage1.setEnableIdTrans(false);
    schoolContactMessage1.setEnableDuplicateCheck(false);
    schoolContactMessage1.setDuplicateCheckInterval(1800);
    final String jsonMsg = schoolContactMessage1.toJson();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "\t\"recv_scope\" : 0,\n" +
      "\t\"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "\t\"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "\t\"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "\t\"toall\" : 0,\n" +
      "\t\"msgtype\" : \"text\",\n" +
      "\t\"agentid\" : 1,\n" +
      "\t\"text\" : {\n" +
      "\t\t\"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq" +
      ".com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
      "\t},\n" +
      "\t\"enable_id_trans\": 0,\n" +
      "\t\"enable_duplicate_check\": 0,\n" +
      "\t\"duplicate_check_interval\": 1800\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json image.
   */
  @Test
  public void testToJson_image() {
    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.IMAGE)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .mediaId("MEDIA_ID")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "\t\"recv_scope\" : 0,\n" +
      "\t\"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "\t\"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "\t\"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "\t\"toall\" : 0,\n" +
      "\t\"msgtype\" : \"image\",\n" +
      "\t\"agentid\" : 1,\n" +
      "\t\"image\" : {\n" +
      "\t\t\"media_id\" : \"MEDIA_ID\"\n" +
      "\t},\n" +
      "\t\"enable_duplicate_check\": 0,\n" +
      "\t\"duplicate_check_interval\": 1800\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json voice.
   */
  @Test
  public void testToJson_voice() {
    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.VOICE)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .mediaId("MEDIA_ID")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "\t\"recv_scope\" : 0,\n" +
      "\t\"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "\t\"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "\t\"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "\t\"toall\" : 0,\n" +
      "\t\"msgtype\" : \"voice\",\n" +
      "\t\"agentid\" : 1,\n" +
      "\t\"voice\" : {\n" +
      "\t\t\"media_id\" : \"MEDIA_ID\"\n" +
      "\t},\n" +
      "\t\"enable_duplicate_check\": 0,\n" +
      "\t\"duplicate_check_interval\": 1800\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json video.
   */
  @Test
  public void testToJson_video() {
    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.VIDEO)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .mediaId("MEDIA_ID")
      .title("Title")
      .description("Description")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "\t\"recv_scope\" : 0,\n" +
      "\t\"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "\t\"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "\t\"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "\t\"toall\" : 0,\n" +
      "\t\"msgtype\" : \"video\",\n" +
      "\t\"agentid\" : 1,\n" +
      "\t\"video\" : {\n" +
      "        \"media_id\" : \"MEDIA_ID\",\n" +
      "        \"title\" : \"Title\",\n" +
      "       \"description\" : \"Description\"\n" +
      "\t},\n" +
      "\t\"enable_duplicate_check\": 0,\n" +
      "\t\"duplicate_check_interval\": 1800\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json file.
   */
  @Test
  public void testToJson_file() {
    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.FILE)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .mediaId("1Yv-zXfHjSjU-7LH-GwtYqDGS-zz6w22KmWAT5COgP7o")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "\t\"recv_scope\" : 0,\n" +
      "\t\"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "\t\"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "\t\"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "\t\"toall\" : 0,\n" +
      "\t\"msgtype\" : \"file\",\n" +
      "\t\"agentid\" : 1,\n" +
      "\t\"file\" : {\n" +
      "        \"media_id\" : \"1Yv-zXfHjSjU-7LH-GwtYqDGS-zz6w22KmWAT5COgP7o\"\n" +
      "\t},\n" +
      "\t\"enable_duplicate_check\": 0,\n" +
      "\t\"duplicate_check_interval\": 1800\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json news.
   */
  @Test
  public void testToJson_news() {
    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.NEWS)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .articles(Lists.newArrayList(NewArticle.builder()
        .title("中秋节礼品领取")
        .description("今年中秋节公司有豪礼相送")
        .url("URL")
        .picUrl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png")
        .build()))
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "   \"recv_scope\" : 0,\n" +
      "   \"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "   \"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "   \"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "   \"toall\" : 0,\n" +
      "   \"msgtype\" : \"news\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"news\" : {\n" +
      "       \"articles\" : [\n" +
      "           {\n" +
      "               \"title\" : \"中秋节礼品领取\",\n" +
      "               \"description\" : \"今年中秋节公司有豪礼相送\",\n" +
      "               \"url\" : \"URL\",\n" +
      "               \"picurl\" : \"http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1" +
      ".png\"\n" +
      "           }\n" +
      "\t\t]\n" +
      "   },\n" +
      "   \"enable_id_trans\": 0,\n" +
      "   \"enable_duplicate_check\": 0,\n" +
      "   \"duplicate_check_interval\": 1800\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }


  /**
   * Test to json mpnews.
   */
  @Test
  public void testToJson_mpnews() {
    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.MPNEWS)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .mpNewsArticles(Lists.newArrayList(MpnewsArticle.newBuilder()
        .title("Title")
        .thumbMediaId("MEDIA_ID")
        .author("Author")
        .contentSourceUrl("URL")
        .content("Content")
        .digest("Digest description")
        .build()))
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "   \"recv_scope\" : 0,\n" +
      "   \"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "   \"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "   \"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "   \"toall\" : 0,\n" +
      "   \"msgtype\" : \"mpnews\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"mpnews\" : {\n" +
      "       \"articles\":[\n" +
      "           {\n" +
      "               \"title\": \"Title\", \n" +
      "               \"thumb_media_id\": \"MEDIA_ID\",\n" +
      "               \"author\": \"Author\",\n" +
      "               \"content_source_url\": \"URL\",\n" +
      "               \"content\": \"Content\",\n" +
      "               \"digest\": \"Digest description\"\n" +
      "            }\n" +
      "       ]\n" +
      "   },\n" +
      "   \"enable_id_trans\": 0,\n" +
      "   \"enable_duplicate_check\": 0,\n" +
      "   \"duplicate_check_interval\": 1800\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json mini program.
   */
  @Test
  public void testToJson_miniProgram() {
    WxCpSchoolContactMessage message = WxCpSchoolContactMessage.builder()
      .recvScope(0)
      .msgType(WxConsts.SchoolContactMsgType.MINIPROGRAM)
      .toParentUserId(new String[]{"parent_userid1", "parent_userid2"})
      .toStudentUserId(new String[]{"student_userid1", "student_userid2"})
      .toParty(new String[]{"partyid1", "partyid2"})
      .toAll(false)
      .agentId(1L)
      .appId("APPID")
      .title("欢迎报名夏令营")
      .thumbMediaId("MEDIA_ID")
      .pagePath("PAGE_PATH")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "   \"recv_scope\" : 0,\n" +
      "   \"to_parent_userid\": [\"parent_userid1\", \"parent_userid2\"],\n" +
      "   \"to_student_userid\": [\"student_userid1\", \"student_userid2\"],\n" +
      "   \"to_party\": [\"partyid1\", \"partyid2\"],\n" +
      "   \"toall\" : 0,\n" +
      "   \"agentid\" : 1,\n" +
      "   \"msgtype\" : \"miniprogram\",\n" +
      "   \"miniprogram\" : {\n" +
      "       \"appid\": \"APPID\",\n" +
      "       \"title\": \"欢迎报名夏令营\",\n" +
      "       \"thumb_media_id\": \"MEDIA_ID\",\n" +
      "       \"pagepath\": \"PAGE_PATH\"\n" +
      "   },\n" +
      "   \"enable_id_trans\": 0,\n" +
      "   \"enable_duplicate_check\": 0,\n" +
      "   \"duplicate_check_interval\": 1800\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

}
