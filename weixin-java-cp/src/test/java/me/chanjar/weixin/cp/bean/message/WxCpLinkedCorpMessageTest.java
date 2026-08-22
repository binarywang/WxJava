package me.chanjar.weixin.cp.bean.message;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.testng.annotations.Test;

import static me.chanjar.weixin.common.api.WxConsts.KefuMsgType;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试用例中的json参考 https://work.weixin.qq.com/api/doc/90000/90135/90250
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-08-30
 */
public class WxCpLinkedCorpMessageTest {

  /**
   * Test to json text.
   */
  @Test
  public void testToJson_text() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.TEXT)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .isSafe(false)
      .content("你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "    \"toall\" : 0,\n" +
      "    \"msgtype\" : \"text\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"text\" : {\n" +
      "       \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq" +
      ".com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
      "   },\n" +
      "   \"safe\":0\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json image.
   */
  @Test
  public void testToJson_image() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.IMAGE)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .isSafe(false)
      .mediaId("MEDIA_ID")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "    \"toall\" : 0,\n" +
      "   \"msgtype\" : \"image\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"image\" : {\n" +
      "        \"media_id\" : \"MEDIA_ID\"\n" +
      "   },\n" +
      "   \"safe\":0\n" +
      "}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json video.
   */
  @Test
  public void testToJson_video() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.VIDEO)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .isSafe(false)
      .mediaId("MEDIA_ID")
      .title("Title")
      .description("Description")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "    \"toall\" : 0,\n" +
      "   \"msgtype\" : \"video\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"video\" : {\n" +
      "        \"media_id\" : \"MEDIA_ID\",\n" +
      "        \"title\" : \"Title\",\n" +
      "       \"description\" : \"Description\"\n" +
      "   },\n" +
      "   \"safe\":0\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json file.
   */
  @Test
  public void testToJson_file() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.FILE)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .isSafe(false)
      .mediaId("1Yv-zXfHjSjU-7LH-GwtYqDGS-zz6w22KmWAT5COgP7o")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "    \"toall\" : 0,\n" +
      "   \"msgtype\" : \"file\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"file\" : {\n" +
      "        \"media_id\" : \"1Yv-zXfHjSjU-7LH-GwtYqDGS-zz6w22KmWAT5COgP7o\"\n" +
      "   },\n" +
      "   \"safe\":0\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json text card.
   */
  @Test
  public void testToJson_textCard() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.TEXTCARD)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .title("领奖通知")
      .description("<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div " +
        "class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>")
      .url("URL")
      .btnTxt("更多")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "    \"toall\" : 0,\n" +
      "   \"msgtype\" : \"textcard\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"textcard\" : {\n" +
      "            \"title\" : \"领奖通知\",\n" +
      "            \"description\" : \"<div class=\\\"gray\\\">2016年9月26日</div> <div class=\\\"normal\\\">恭喜你抽中iPhone" +
      " 7一台，领奖码：xxxx</div><div class=\\\"highlight\\\">请于2016年10月10日前联系行政同事领取</div>\",\n" +
      "            \"url\" : \"URL\",\n" +
      "            \"btntxt\":\"更多\"\n" +
      "   }\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json news.
   */
  @Test
  public void testToJson_news() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.NEWS)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .articles(Lists.newArrayList(NewArticle.builder()
        .title("中秋节礼品领取")
        .description("今年中秋节公司有豪礼相送")
        .url("URL")
        .picUrl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png")
        .btnText("更多")
        .build()))
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "    \"toall\" : 0,\n" +
      "   \"msgtype\" : \"news\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"news\" : {\n" +
      "       \"articles\" : [\n" +
      "           {\n" +
      "               \"title\" : \"中秋节礼品领取\",\n" +
      "               \"description\" : \"今年中秋节公司有豪礼相送\",\n" +
      "               \"url\" : \"URL\",\n" +
      "               \"picurl\" : \"http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1" +
      ".png\",\n" +
      "               \"btntxt\":\"更多\"\n" +
      "           }\n" +
      "        ]\n" +
      "   }\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }


  /**
   * Test to json mpnews.
   */
  @Test
  public void testToJson_mpnews() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.MPNEWS)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .isSafe(false)
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
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "    \"toall\" : 0,\n" +
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
      "   \"safe\":0\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json markdown.
   */
  @Test
  public void testToJson_markdown() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.MARKDOWN)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .agentId(1L)
      .isToAll(false)
      .content("您的会议室已经预定，稍后会同步到`邮箱`\n" +
        "                >**事项详情**\n" +
        "                >事　项：<font color=\"info\">开会</font>\n" +
        "                >组织者：@miglioguan\n" +
        "                >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang\n" +
        "                >\n" +
        "                >会议室：<font color=\"info\">广州TIT 1楼 301</font>\n" +
        "                >日　期：<font color=\"warning\">2018年5月18日</font>\n" +
        "                >时　间：<font color=\"comment\">上午9:00-11:00</font>\n" +
        "                >\n" +
        "                >请准时参加会议。\n" +
        "                >\n" +
        "                >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)")
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "   \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "   \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "   \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "   \"toall\" : 0,\n" +
      "   \"msgtype\" : \"markdown\",\n" +
      "   \"agentid\" : 1,\n" +
      "   \"markdown\": {\n" +
      "        \"content\": \"您的会议室已经预定，稍后会同步到`邮箱`\n" +
      "                >**事项详情**\n" +
      "                >事　项：<font color=\\\"info\\\">开会</font>\n" +
      "                >组织者：@miglioguan\n" +
      "                >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang\n" +
      "                >\n" +
      "                >会议室：<font color=\\\"info\\\">广州TIT 1楼 301</font>\n" +
      "                >日　期：<font color=\\\"warning\\\">2018年5月18日</font>\n" +
      "                >时　间：<font color=\\\"comment\\\">上午9:00-11:00</font>\n" +
      "                >\n" +
      "                >请准时参加会议。\n" +
      "                >\n" +
      "                >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)\"\n" +
      "   }\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

  /**
   * Test to json mini program notice.
   */
  @Test
  public void testToJson_miniProgramNotice() {
    WxCpLinkedCorpMessage message = WxCpLinkedCorpMessage.builder()
      .msgType(KefuMsgType.MINIPROGRAM_NOTICE)
      .toUsers(new String[]{"userid1", "userid2", "CorpId1/userid1", "CorpId2/userid2"})
      .toParties(new String[]{"partyid1", "partyid2", "LinkedId1/partyid1", "LinkedId2/partyid2"})
      .toTags(new String[]{"tagid1", "tagid2"})
      .emphasisFirstItem(true)
      .description("4月27日 16:16")
      .title("会议室预订成功通知")
      .appId("wx123123123123123")
      .page("pages/index?userid=zhangsan&orderid=123123123")
      .contentItems(ImmutableMap.of("会议室", "402",
        "会议地点", "广州TIT-402会议室",
        "会议时间", "2018年8月1日 09:00-09:30",
        "参与人员", "周剑轩"))
      .build();

    final String json = message.toJson();
    String expectedJson = "{\n" +
      "    \"touser\" : [\"userid1\",\"userid2\",\"CorpId1/userid1\",\"CorpId2/userid2\"],\n" +
      "    \"toparty\" : [\"partyid1\",\"partyid2\",\"LinkedId1/partyid1\",\"LinkedId2/partyid2\"],\n" +
      "    \"totag\" : [\"tagid1\",\"tagid2\"],\n" +
      "   \"msgtype\" : \"miniprogram_notice\",\n" +
      "   \"miniprogram_notice\" : {\n" +
      "        \"appid\": \"wx123123123123123\",\n" +
      "        \"page\": \"pages/index?userid=zhangsan&orderid=123123123\",\n" +
      "        \"title\": \"会议室预订成功通知\",\n" +
      "        \"description\": \"4月27日 16:16\",\n" +
      "        \"emphasis_first_item\": true,\n" +
      "        \"content_item\": [\n" +
      "            {\n" +
      "                \"key\": \"会议室\",\n" +
      "                \"value\": \"402\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"key\": \"会议地点\",\n" +
      "                \"value\": \"广州TIT-402会议室\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"key\": \"会议时间\",\n" +
      "                \"value\": \"2018年8月1日 09:00-09:30\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"key\": \"参与人员\",\n" +
      "                \"value\": \"周剑轩\"\n" +
      "            }\n" +
      "        ]\n" +
      "    }\n" +
      "}\n";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }
}
