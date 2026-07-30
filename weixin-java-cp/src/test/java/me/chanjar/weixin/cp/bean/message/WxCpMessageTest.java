package me.chanjar.weixin.cp.bean.message;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.taskcard.TaskCardButton;
import me.chanjar.weixin.cp.bean.templatecard.*;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Wx cp message test.
 */
@Test
public class WxCpMessageTest {

  /**
   * Test text build.
   */
  public void testTextBuild() {
    WxCpMessage reply = WxCpMessage.TEXT().toUser("OPENID").content("sfsfdsdf").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"},\"safe\":\"0\"}");
  }

  /**
   * Test text card build.
   */
  public void testTextCardBuild() {
    WxCpMessage reply = WxCpMessage.TEXTCARD().toUser("OPENID")
      .title("领奖通知")
      .description("<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，" +
        "领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>")
      .url("http://www.qq.com")
      .btnTxt("更多")
      .build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"textcard\",\"textcard\":{\"title\":\"领奖通知\"," +
        "\"description\":\"<div class=\\\"gray\\\">2016年9月26日</div> <div class=\\\"normal\\\">" +
        "恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\\\"highlight\\\">请于2016年10月10日前联系行政同事领取</div>\"," +
        "\"url\":\"http://www.qq.com\",\"btntxt\":\"更多\"},\"safe\":\"0\"}");
  }

  /**
   * Test image build.
   */
  public void testImageBuild() {
    WxCpMessage reply = WxCpMessage.IMAGE().toUser("OPENID").mediaId("MEDIA_ID").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"},\"safe\":\"0\"}");
  }

  /**
   * Test voice build.
   */
  public void testVoiceBuild() {
    WxCpMessage reply = WxCpMessage.VOICE().toUser("OPENID").mediaId("MEDIA_ID").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"},\"safe\":\"0\"}");
  }

  /**
   * Test video build.
   */
  public void testVideoBuild() {
    WxCpMessage reply = WxCpMessage.VIDEO().toUser("OPENID").title("TITLE").mediaId("MEDIA_ID").thumbMediaId("MEDIA_ID")
      .description("DESCRIPTION").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\"," +
        "\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"},\"safe\":\"0\"}");
  }

  /**
   * Test news build.
   */
  public void testNewsBuild() {
    NewArticle article1 = new NewArticle();
    article1.setUrl("URL");
    article1.setPicUrl("PIC_URL");
    article1.setDescription("Is Really A Happy Day");
    article1.setTitle("Happy Day");

    NewArticle article2 = new NewArticle();
    article2.setUrl("URL");
    article2.setPicUrl("PIC_URL");
    article2.setDescription("Is Really A Happy Day");
    article2.setTitle("Happy Day");

    WxCpMessage reply = WxCpMessage.NEWS().toUser("OPENID").addArticle(article1).addArticle(article2).build();

    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":" +
        "[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}," +
        "{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\"," +
        "\"picurl\":\"PIC_URL\"}]}," +
        "\"safe\":\"0\"}");
  }

  /**
   * Test mpnews build with articles.
   */
  public void testMpnewsBuild_with_articles() {
    MpnewsArticle article1 = MpnewsArticle.newBuilder()
      .title("Happy Day")
      .author("aaaaaa")
      .content("hahaha")
      .contentSourceUrl("nice url")
      .digest("digest")
      .showCoverPic("heihei")
      .thumbMediaId("thumb")
      .build();

    MpnewsArticle article2 = MpnewsArticle.newBuilder()
      .title("Happy Day")
      .author("aaaaaa")
      .content("hahaha")
      .contentSourceUrl("nice url")
      .digest("digest")
      .showCoverPic("heihei")
      .thumbMediaId("thumb")
      .build();

    WxCpMessage reply = WxCpMessage.MPNEWS().toUser("OPENID").addArticle(article1, article2).build();

    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"mpnews\",\"mpnews\":{\"articles\":" +
        "[{\"title\":\"Happy Day\",\"thumb_media_id\":\"thumb\",\"author\":\"aaaaaa\"," +
        "\"content_source_url\":\"nice url\",\"content\":\"hahaha\",\"digest\":\"digest\"," +
        "\"show_cover_pic\":\"heihei\"}" +
        ",{\"title\":\"Happy Day\",\"thumb_media_id\":\"thumb\",\"author\":\"aaaaaa\"," +
        "\"content_source_url\":\"nice url\",\"content\":\"hahaha\",\"digest\":\"digest\"," +
        "\"show_cover_pic\":\"heihei\"}]}," +
        "\"safe\":\"0\"}");
  }

  /**
   * Test mpnews build with media id.
   */
  public void testMpnewsBuild_with_media_id() {
    WxCpMessage reply = WxCpMessage.MPNEWS().toUser("OPENID").mediaId("mmm").build();

    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"mpnews\",\"mpnews\":{\"media_id\":\"mmm\"},\"safe\":\"0\"}");
  }

  /**
   * Test task card builder.
   */
  public void testTaskCardBuilder() {
    TaskCardButton button1 = TaskCardButton.builder()
      .key("yes")
      .name("批准")
      .replaceName("已批准")
      .color("blue")
      .bold(true)
      .build();
    TaskCardButton button2 = TaskCardButton.builder()
      .key("yes")
      .name("拒绝")
      .replaceName("已拒绝")
      .color("red")
      .bold(false)
      .build();
    WxCpMessage reply = WxCpMessage.TASKCARD().toUser("OPENID")
      .title("任务卡片")
      .description("有一条待处理任务")
      .url("http://www.qq.com")
      .taskId("task_123")
      .buttons(Arrays.asList(button1, button2))
      .build();
    assertThat(reply.toJson())
      .isEqualTo(
        "{\"touser\":\"OPENID\",\"msgtype\":\"taskcard\",\"taskcard\":{\"title\":\"任务卡片\"," +
          "\"description\":\"有一条待处理任务\",\"url\":\"http://www.qq.com\",\"task_id\":\"task_123\"," +
          "\"btn\":[{\"key\":\"yes\",\"name\":\"批准\",\"replace_name\":\"已批准\",\"color\":\"blue\",\"is_bold\":true}," +
          "{\"key\":\"yes\",\"name\":\"拒绝\",\"replace_name\":\"已拒绝\",\"color\":\"red\",\"is_bold\":false}]}}");
  }

  /**
   * 测试模板卡片消息
   * 文本通知型
   */
  public void TestTemplateCardBuilder_text_notice() {

    HorizontalContent hContent1 = HorizontalContent.builder()
      .keyname("邀请人")
      .value("张三")
      .build();
    HorizontalContent hContent2 = HorizontalContent.builder()
      .type(1)
      .keyname("企业微信官网")
      .value("点击访问")
      .url("https://work.weixin.qq.com")
      .build();
    HorizontalContent hContent3 = HorizontalContent.builder()
      .type(2)
      .keyname("企业微信下载")
      .value("企业微信.apk")
      .media_id("文件的media_id")
      .build();
    HorizontalContent hContent4 = HorizontalContent.builder()
      .type(3)
      .keyname("员工信息")
      .value("点击查看")
      .userid("zhangsan")
      .build();

    TemplateCardJump jump1 = TemplateCardJump.builder()
      .type(1)
      .title("企业微信官网")
      .url("https://work.weixin.qq.com")
      .build();
    TemplateCardJump jump2 = TemplateCardJump.builder()
      .type(2)
      .title("跳转小程序")
      .appid("小程序的appid")
      .pagepath("/index.html")
      .build();
    QuoteArea quoteArea = QuoteArea.builder()
      .type(1)
      .title("引用文献标题")
      .appid("小程序的appid")
      .pagepath("/index.html")
      .url("https://work.weixin.qq.com")
      .quoteText("引用文献样式的引用文案")
      .build();
    ActionMenuItem action1 = ActionMenuItem.builder()
      .text("接受推送")
      .key("A")
      .build();
    ActionMenuItem action2 = ActionMenuItem.builder()
      .text("不再推送")
      .key("B")
      .build();
    WxCpMessage reply = WxCpMessage.TEMPLATECARD().toUser("OPENID")
      .toParty("PartyID1 | PartyID2")
      .toTag("TagID1 | TagID2")
      .agentId(1000002L)
      .cardType(WxConsts.TemplateCardType.TEXT_NOTICE)
      .taskId("task_id")
      .sourceIconUrl("图片的url")
      .sourceDesc("企业微信")
      .sourceDescColor(1)
      .actionMenuDesc("卡片副交互辅助文本说明")
      .actionMenuActionList(Arrays.asList(action1, action2))
      .mainTitleTitle("欢迎使用企业微信")
      .mainTitleDesc("您的好友正在邀请您加入企业微信")
      .emphasisContentTitle("100")
      .emphasisContentDesc("核心数据")
      .subTitleText("下载企业微信还能抢红包！")
      .horizontalContents(Arrays.asList(hContent1, hContent2, hContent3, hContent4))
      .jumps(Arrays.asList(jump1, jump2))
      .cardActionType(2)
      .cardActionAppid("小程序的appid")
      .cardActionUrl("https://work.weixin.qq.com")
      .cardActionPagepath("/index.html")
      .quoteArea(quoteArea)
      .build();
    reply.setEnableIdTrans(false);
    reply.setEnableDuplicateCheck(false);
    reply.setDuplicateCheckInterval(1800);
//    System.out.println(reply.toJson());
    assertThat(reply.toJson())
      .isEqualTo(
        "{\"agentid\":1000002,\"touser\":\"OPENID\",\"msgtype\":\"template_card\",\"toparty\":\"PartyID1 | " +
          "PartyID2\",\"totag\":\"TagID1 | TagID2\",\"duplicate_check_interval\":1800," +
          "\"template_card\":{\"card_type\":\"text_notice\",\"source\":{\"icon_url\":\"图片的url\",\"desc\":\"企业微信\"," +
          "\"desc_color\":1},\"action_menu\":{\"desc\":\"卡片副交互辅助文本说明\",\"action_list\":[{\"text\":\"接受推送\"," +
          "\"key\":\"A\"},{\"text\":\"不再推送\",\"key\":\"B\"}]},\"main_title\":{\"title\":\"欢迎使用企业微信\"," +
          "\"desc\":\"您的好友正在邀请您加入企业微信\"},\"emphasis_content\":{\"title\":\"100\",\"desc\":\"核心数据\"}," +
          "\"sub_title_text\":\"下载企业微信还能抢红包！\",\"task_id\":\"task_id\"," +
          "\"horizontal_content_list\":[{\"keyname\":\"邀请人\",\"value\":\"张三\"},{\"type\":1,\"keyname\":\"企业微信官网\"," +
          "\"value\":\"点击访问\",\"url\":\"https://work.weixin.qq.com\"},{\"type\":2,\"keyname\":\"企业微信下载\"," +
          "\"value\":\"企业微信.apk\",\"media_id\":\"文件的media_id\"},{\"type\":3,\"keyname\":\"员工信息\",\"value\":\"点击查看\"," +
          "\"userid\":\"zhangsan\"}],\"jump_list\":[{\"type\":1,\"title\":\"企业微信官网\",\"url\":\"https://work.weixin.qq" +
          ".com\"},{\"type\":2,\"title\":\"跳转小程序\",\"appid\":\"小程序的appid\",\"pagepath\":\"/index.html\"}]," +
          "\"card_action\":{\"type\":2,\"url\":\"https://work.weixin.qq.com\",\"appid\":\"小程序的appid\"," +
          "\"pagepath\":\"/index.html\"},\"quote_area\":{\"type\":1,\"url\":\"https://work.weixin.qq.com\"," +
          "\"appid\":\"小程序的appid\",\"pagepath\":\"/index.html\",\"title\":\"引用文献标题\"," +
          "\"quote_text\":\"引用文献样式的引用文案\"}}}");
  }

  /**
   * 测试模板卡片消息
   * 图文展示型
   */
  public void TestTemplateCardBuilder_news_notice() {

    VerticalContent vContent1 = VerticalContent.builder()
      .title("惊喜红包等你来拿")
      .desc("下载企业微信还能抢红包！")
      .build();
    VerticalContent vContent2 = VerticalContent.builder()
      .title("二级垂直内容")
      .desc("二级垂直内容！")
      .build();

    HorizontalContent hContent1 = HorizontalContent.builder()
      .keyname("邀请人")
      .value("张三")
      .build();
    HorizontalContent hContent2 = HorizontalContent.builder()
      .type(1)
      .keyname("企业微信官网")
      .value("点击访问")
      .url("https://work.weixin.qq.com")
      .build();
    HorizontalContent hContent3 = HorizontalContent.builder()
      .type(2)
      .keyname("企业微信下载")
      .value("企业微信.apk")
      .media_id("文件的media_id")
      .build();

    TemplateCardJump jump1 = TemplateCardJump.builder()
      .type(1)
      .title("企业微信官网")
      .url("https://work.weixin.qq.com")
      .build();
    TemplateCardJump jump2 = TemplateCardJump.builder()
      .type(2)
      .title("跳转小程序")
      .appid("小程序的appid")
      .pagepath("/index.html")
      .build();

    WxCpMessage reply = WxCpMessage.TEMPLATECARD().toUser("OPENID")
      .agentId(1000002L)
      .cardType(WxConsts.TemplateCardType.NEWS_NOTICE)
      .sourceIconUrl("图片的url")
      .sourceDesc("企业微信")
      .mainTitleTitle("欢迎使用企业微信")
      .mainTitleDesc("您的好友正在邀请您加入企业微信")
      .verticalContents(Arrays.asList(vContent1, vContent2))
      .horizontalContents(Arrays.asList(hContent1, hContent2, hContent3))
      .jumps(Arrays.asList(jump1, jump2))
      .cardActionType(2)
      .cardActionAppid("小程序的appid")
      .cardActionUrl("https://work.weixin.qq.com")
      .cardActionPagepath("/index.html")
      .build();
    reply.setEnableIdTrans(false);
    reply.setEnableDuplicateCheck(false);
    reply.setDuplicateCheckInterval(1800);
    System.out.println(reply.toJson());
    assertThat(reply.toJson())
      .isEqualTo(
        "{\"agentid\":1000002,\"touser\":\"OPENID\",\"msgtype\":\"template_card\",\"duplicate_check_interval\":1800," +
          "\"template_card\":{\"card_type\":\"news_notice\",\"source\":{\"icon_url\":\"图片的url\",\"desc\":\"企业微信\"}," +
          "\"main_title\":{\"title\":\"欢迎使用企业微信\",\"desc\":\"您的好友正在邀请您加入企业微信\"}," +
          "\"vertical_content_list\":[{\"title\":\"惊喜红包等你来拿\",\"desc\":\"下载企业微信还能抢红包！\"},{\"title\":\"二级垂直内容\"," +
          "\"desc\":\"二级垂直内容！\"}],\"horizontal_content_list\":[{\"keyname\":\"邀请人\",\"value\":\"张三\"},{\"type\":1," +
          "\"keyname\":\"企业微信官网\",\"value\":\"点击访问\",\"url\":\"https://work.weixin.qq.com\"},{\"type\":2," +
          "\"keyname\":\"企业微信下载\",\"value\":\"企业微信.apk\",\"media_id\":\"文件的media_id\"}],\"jump_list\":[{\"type\":1," +
          "\"title\":\"企业微信官网\",\"url\":\"https://work.weixin.qq.com\"},{\"type\":2,\"title\":\"跳转小程序\"," +
          "\"appid\":\"小程序的appid\",\"pagepath\":\"/index.html\"}],\"card_action\":{\"type\":2,\"url\":\"https://work" +
          ".weixin.qq.com\",\"appid\":\"小程序的appid\",\"pagepath\":\"/index.html\"}}}");
  }

  /**
   * 测试模板卡片消息
   * 按钮交互型
   */
  public void TestTemplateCardBuilder_button_interaction() {

    TemplateCardButton tButton1 = TemplateCardButton.builder()
      .text("按钮1")
      .style(1)
      .key("button_key_1")
      .build();
    TemplateCardButton tButton2 = TemplateCardButton.builder()
      .text("按钮2")
      .style(2)
      .key("button_key_2")
      .build();

    HorizontalContent hContent1 = HorizontalContent.builder()
      .keyname("邀请人")
      .value("张三")
      .build();
    HorizontalContent hContent2 = HorizontalContent.builder()
      .type(1)
      .keyname("企业微信官网")
      .value("点击访问")
      .url("https://work.weixin.qq.com")
      .build();
    HorizontalContent hContent3 = HorizontalContent.builder()
      .type(2)
      .keyname("企业微信下载")
      .value("企业微信.apk")
      .media_id("文件的media_id")
      .build();

    WxCpMessage reply = WxCpMessage.TEMPLATECARD().toUser("OPENID")
      .agentId(1000002L)
      .cardType(WxConsts.TemplateCardType.BUTTON_INTERACTION)
      .sourceIconUrl("图片的url")
      .sourceDesc("企业微信")
      .mainTitleTitle("欢迎使用企业微信")
      .mainTitleDesc("您的好友正在邀请您加入企业微信")
      .subTitleText("下载企业微信还能抢红包！")
      .horizontalContents(Arrays.asList(hContent1, hContent2, hContent3))
      .cardActionType(2)
      .cardActionAppid("小程序的appid")
      .cardActionUrl("https://work.weixin.qq.com")
      .cardActionPagepath("/index.html")
      .taskId("task_id")
      .buttons(Arrays.asList(tButton1, tButton2))
      .build();
    reply.setEnableIdTrans(false);
    reply.setEnableDuplicateCheck(false);
    reply.setDuplicateCheckInterval(1800);
    System.out.println(reply.toJson());
    assertThat(reply.toJson())
      .isEqualTo(
        "{\"agentid\":1000002,\"touser\":\"OPENID\",\"msgtype\":\"template_card\",\"duplicate_check_interval\":1800," +
          "\"template_card\":{\"card_type\":\"button_interaction\",\"source\":{\"icon_url\":\"图片的url\"," +
          "\"desc\":\"企业微信\"},\"main_title\":{\"title\":\"欢迎使用企业微信\",\"desc\":\"您的好友正在邀请您加入企业微信\"}," +
          "\"sub_title_text\":\"下载企业微信还能抢红包！\",\"task_id\":\"task_id\"," +
          "\"horizontal_content_list\":[{\"keyname\":\"邀请人\",\"value\":\"张三\"},{\"type\":1,\"keyname\":\"企业微信官网\"," +
          "\"value\":\"点击访问\",\"url\":\"https://work.weixin.qq.com\"},{\"type\":2,\"keyname\":\"企业微信下载\"," +
          "\"value\":\"企业微信.apk\",\"media_id\":\"文件的media_id\"}],\"card_action\":{\"type\":2,\"url\":\"https://work" +
          ".weixin.qq.com\",\"appid\":\"小程序的appid\",\"pagepath\":\"/index.html\"},\"button_list\":[{\"text\":\"按钮1\"," +
          "\"style\":1,\"key\":\"button_key_1\"},{\"text\":\"按钮2\",\"style\":2,\"key\":\"button_key_2\"}]}}");
  }

  /**
   * 测试模板卡片消息
   * 投票选择型
   */
  public void TestTemplateCardBuilder_vote_interaction() {
    CheckboxOption option1 = CheckboxOption.builder()
      .id("option_id1")
      .text("选择题选项1")
      .is_checked(true)
      .build();
    CheckboxOption option2 = CheckboxOption.builder()
      .id("option_id2")
      .text("选择题选项2")
      .is_checked(false)
      .build();

    WxCpMessage reply = WxCpMessage.TEMPLATECARD().toUser("OPENID")
      .agentId(1000002L)
      .cardType(WxConsts.TemplateCardType.VOTE_INTERACTION)
      .sourceIconUrl("图片的url")
      .sourceDesc("企业微信")
      .mainTitleTitle("欢迎使用企业微信")
      .mainTitleDesc("您的好友正在邀请您加入企业微信")
      .taskId("task_id")
      .checkboxQuestionKey("question_key1")
      .checkboxMode(1)
      .options(Arrays.asList(option1, option2))
      .submitButtonKey("key")
      .submitButtonText("提交")
      .build();

    reply.setEnableIdTrans(false);
    reply.setEnableDuplicateCheck(false);
    reply.setDuplicateCheckInterval(1800);
    System.out.println(reply.toJson());

    assertThat(reply.toJson())
      .isEqualTo(
        "{\"agentid\":1000002,\"touser\":\"OPENID\",\"msgtype\":\"template_card\",\"duplicate_check_interval\":1800," +
          "\"template_card\":{\"card_type\":\"vote_interaction\",\"source\":{\"icon_url\":\"图片的url\"," +
          "\"desc\":\"企业微信\"},\"main_title\":{\"title\":\"欢迎使用企业微信\",\"desc\":\"您的好友正在邀请您加入企业微信\"}," +
          "\"task_id\":\"task_id\",\"checkbox\":{\"question_key\":\"question_key1\",\"mode\":1," +
          "\"option_list\":[{\"id\":\"option_id1\",\"text\":\"选择题选项1\",\"is_checked\":true},{\"id\":\"option_id2\"," +
          "\"text\":\"选择题选项2\",\"is_checked\":false}]},\"submit_button\":{\"text\":\"提交\",\"key\":\"key\"}}}");
  }

  /**
   * 测试模板卡片消息
   * 投票选择型
   */
  public void TestTemplateCardBuilder_multiple_interaction() {
    CheckboxOption option1 = CheckboxOption.builder()
      .id("selection_id1")
      .text("选择器选项1")
      .build();
    CheckboxOption option2 = CheckboxOption.builder()
      .id("selection_id2")
      .text("选择题选项2")
      .build();
    CheckboxOption option3 = CheckboxOption.builder()
      .id("selection_id3")
      .text("选择器选项3")
      .build();
    CheckboxOption option4 = CheckboxOption.builder()
      .id("selection_id4")
      .text("选择题选项4")
      .build();

    MultipleSelect mSelect1 = MultipleSelect.builder()
      .question_key("question_key1")
      .title("选择器标签1")
      .selected_id("selection_id1")
      .options(Arrays.asList(option1, option2))
      .build();
    MultipleSelect mSelect2 = MultipleSelect.builder()
      .question_key("question_key2")
      .title("选择器标签2")
      .selected_id("selection_id3")
      .options(Arrays.asList(option3, option4))
      .build();


    WxCpMessage reply = WxCpMessage.TEMPLATECARD().toUser("OPENID")
      .agentId(1000002L)
      .cardType(WxConsts.TemplateCardType.MULTIPLE_INTERACTION)
      .sourceIconUrl("图片的url")
      .sourceDesc("企业微信")
      .mainTitleTitle("欢迎使用企业微信")
      .mainTitleDesc("您的好友正在邀请您加入企业微信")
      .taskId("task_id")
      .selects(Arrays.asList(mSelect1, mSelect2))
      .submitButtonKey("key")
      .submitButtonText("提交")
      .build();
    reply.setEnableIdTrans(false);
    reply.setEnableDuplicateCheck(false);
    reply.setDuplicateCheckInterval(1800);
    System.out.println(reply.toJson());
    assertThat(reply.toJson())
      .isEqualTo("{\"agentid\":1000002,\"touser\":\"OPENID\",\"msgtype\":\"template_card\"," +
        "\"duplicate_check_interval\":1800,\"template_card\":{\"card_type\":\"multiple_interaction\"," +
        "\"source\":{\"icon_url\":\"图片的url\",\"desc\":\"企业微信\"},\"main_title\":{\"title\":\"欢迎使用企业微信\"," +
        "\"desc\":\"您的好友正在邀请您加入企业微信\"},\"task_id\":\"task_id\",\"submit_button\":{\"text\":\"提交\",\"key\":\"key\"}," +
        "\"select_list\":[{\"question_key\":\"question_key1\",\"title\":\"选择器标签1\",\"selected_id\":\"selection_id1\"," +
        "\"option_list\":[{\"id\":\"selection_id1\",\"text\":\"选择器选项1\"},{\"id\":\"selection_id2\"," +
        "\"text\":\"选择题选项2\"}]},{\"question_key\":\"question_key2\",\"title\":\"选择器标签2\"," +
        "\"selected_id\":\"selection_id3\",\"option_list\":[{\"id\":\"selection_id3\",\"text\":\"选择器选项3\"}," +
        "{\"id\":\"selection_id4\",\"text\":\"选择题选项4\"}]}]}}");
  }

}
