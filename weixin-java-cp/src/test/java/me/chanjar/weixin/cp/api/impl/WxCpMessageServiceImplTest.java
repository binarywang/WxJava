package me.chanjar.weixin.cp.api.impl;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpLinkedCorpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendResult;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendStatistics;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static com.github.dreamhead.moco.Moco.file;
import static com.github.dreamhead.moco.MocoJsonRunner.jsonHttpServer;
import static me.chanjar.weixin.cp.api.ApiTestModuleWithMockServer.mockServerPort;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNotNull;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-08-30
 */
@Test
//@Guice(modules = ApiTestModuleWithMockServer.class)
@Guice(modules = ApiTestModule.class)
public class WxCpMessageServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  private Runner mockRunner;
  private ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  private WxCpMessageSendResult wxCpMessageSendResult;

  /**
   * Sets .
   */
  @BeforeTest
  public void setup() {
    HttpServer mockServer = jsonHttpServer(mockServerPort, file("src/test/resources/moco/message.json"));
    this.mockRunner = Runner.runner(mockServer);
    this.mockRunner.start();
    this.configStorage = (ApiTestModule.WxXmlCpInMemoryConfigStorage) this.wxService.getWxCpConfigStorage();
  }

  /**
   * Stop mock server.
   */
  @AfterTest
  public void stopMockServer() {
    this.mockRunner.stop();
  }

  /**
   * Test send message.
   *
   * @throws WxErrorException the wx error exception
   */
  public void testSendMessage() throws WxErrorException {
    WxCpMessage message = new WxCpMessage();
//    message.setAgentId(configStorage.getAgentId());
    message.setMsgType(WxConsts.KefuMsgType.TEXT);
    message.setToUser(configStorage.getUserId());
    message.setContent("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");

    WxCpMessageSendResult messageSendResult = this.wxService.getMessageService().send(message);
    assertNotNull(messageSendResult);
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
    System.out.println(messageSendResult.getUnlicensedUserList());
  }

  /**
   * Test send message 1.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendMessage1() throws WxErrorException {
    WxCpMessage message = WxCpMessage
      .TEXT()
//      .agentId(configStorage.getAgentId())
      .toUser(configStorage.getUserId())
      .content("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>")
      .build();

    WxCpMessageSendResult messageSendResult = this.wxService.getMessageService().send(message);
    assertNotNull(messageSendResult);
    wxCpMessageSendResult = messageSendResult;
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
  }

  /**
   * Test send message markdown.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendMessage_markdown() throws WxErrorException {
    WxCpMessage message = WxCpMessage
      .MARKDOWN()
      .toUser(configStorage.getUserId())
      .content("您的会议室已经预定，稍后会同步到`邮箱` \n" +
        "                >**事项详情** \n" +
        "                >事　项：<font color=\\\"info\\\">开会</font> \n" +
        "                >组织者：@miglioguan \n" +
        "                >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang \n" +
        "                > \n" +
        "                >会议室：<font color=\\\"info\\\">广州TIT 1楼 301</font> \n" +
        "                >日　期：<font color=\\\"warning\\\">2018年5月18日</font> \n" +
        "                >时　间：<font color=\\\"comment\\\">上午9:00-11:00</font> \n" +
        "                > \n" +
        "                >请准时参加会议。 \n" +
        "                > \n" +
        "                >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)")
      .build();

    WxCpMessageSendResult messageSendResult = this.wxService.getMessageService().send(message);
    assertNotNull(messageSendResult);
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
  }

  /**
   * Test send message text card.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendMessage_textCard() throws WxErrorException {
    WxCpMessage message = WxCpMessage
      .TEXTCARD()
      .toUser(configStorage.getUserId())
      .btnTxt("更多")
      .description("<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div " +
        "class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>")
      .url("URL")
      .title("领奖通知")
      .build();

    WxCpMessageSendResult messageSendResult = this.wxService.getMessageService().send(message);
    assertNotNull(messageSendResult);
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
  }

  /**
   * Test send message mini program notice.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendMessage_miniProgram_notice() throws WxErrorException {
    WxCpMessage message = WxCpMessage
      .newMiniProgramNoticeBuilder()
      .toUser(configStorage.getUserId())
      .appId("wx123123123123123")
      .page("pages/index?userid=zhangsan&orderid=123123123")
      .title("会议室预订成功通知")
      .description("4月27日 16:16")
      .emphasisFirstItem(true)
      .contentItems(ImmutableMap.of("会议室", "402",
        "会议地点", "广州TIT-402会议室",
        "会议时间", "2018年8月1日 09:00-09:30"))
      .build();

    WxCpMessageSendResult messageSendResult = this.wxService.getMessageService().send(message);
    assertNotNull(messageSendResult);
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
  }

  /**
   * Test send linked corp message.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendLinkedCorpMessage() throws WxErrorException {
    this.wxService.getMessageService().sendLinkedCorpMessage(WxCpLinkedCorpMessage.builder()
      .msgType(WxConsts.KefuMsgType.TEXT)
      .toUsers(new String[]{configStorage.getUserId()})
      .content("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>")
      .build());
  }

  /**
   * Test send.
   */
  @Test
  public void testSend() {
    // see other test methods
  }

  /**
   * Test get statistics.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetStatistics() throws WxErrorException {
    final WxCpMessageSendStatistics statistics = this.wxService.getMessageService().getStatistics(1);
    assertNotNull(statistics);
    assertThat(statistics.getStatistics()).isNotNull();
  }

  /**
   * Test message recall
   * @throws WxErrorException
   */
  @Test(dependsOnMethods = "testSendMessage1")
  public void testRecall() throws WxErrorException {
    this.wxService.getMessageService().recall(wxCpMessageSendResult.getMsgId());
  }

}
