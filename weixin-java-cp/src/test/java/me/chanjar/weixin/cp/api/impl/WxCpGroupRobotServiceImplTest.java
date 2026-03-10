package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpGroupRobotService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 微信群机器人消息发送api 单元测试
 *
 * @author yr  created on  2020-08-20
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpGroupRobotServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  private WxCpGroupRobotService robotService;

  /**
   * Sets .
   */
  @BeforeTest
  public void setup() {
    robotService = wxService.getGroupRobotService();
  }

  /**
   * Test send text.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendText() throws WxErrorException {
    robotService.sendText("Hello World", null, null);
  }

  /**
   * Test send mark down.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendMarkDown() throws WxErrorException {
    String content = "实时新增用户反馈<font color=\"warning\">132例</font>，请相关同事注意。\n" +
      ">类型:<font color=\"comment\">用户反馈</font> \n" +
      ">普通用户反馈:<font color=\"comment\">117例</font> \n" +
      ">VIP用户反馈:<font color=\"comment\">15例</font>";
    robotService.sendMarkdown(content);
  }

  /**
   * Test send mark down v2.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendMarkDownV2() throws WxErrorException {
    String content = "# 一、标题\n" +
      "## 二级标题\n" +
      "### 三级标题\n" +
      "# 二、字体\n" +
      "*斜体*\n" +
      "\n" +
      "**加粗**\n" +
      "# 三、列表 \n" +
      "- 无序列表 1 \n" +
      "- 无序列表 2\n" +
      "  - 无序列表 2.1\n" +
      "  - 无序列表 2.2\n" +
      "1. 有序列表 1\n" +
      "2. 有序列表 2\n" +
      "# 四、引用\n" +
      "> 一级引用\n" +
      ">>二级引用\n" +
      ">>>三级引用\n" +
      "# 五、链接\n" +
      "[这是一个链接](https://work.weixin.qq.com/api/doc)\n" +
      "![](https://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png)\n" +
      "# 六、分割线\n" +
      "\n" +
      "---\n" +
      "# 七、代码\n" +
      "`这是行内代码`\n" +
      "```\n" +
      "这是独立代码块\n" +
      "```\n" +
      "\n" +
      "# 八、表格\n" +
      "| 姓名 | 文化衫尺寸 | 收货地址 |\n" +
      "| :----- | :----: | -------: |\n" +
      "| 张三 | S | 广州 |\n" +
      "| 李四 | L | 深圳 |";
    robotService.sendMarkdownV2(content);
  }

  /**
   * Test send image.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendImage() throws WxErrorException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mm.jpeg");
    assert inputStream != null;
    String base64 = FileUtils.imageToBase64ByStream(inputStream);
    String md5 = "1cb2e787063d66e24f5f89e7fc267a4d";
    robotService.sendImage(base64, md5);
  }

  /**
   * Test send news.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendNews() throws WxErrorException {
    NewArticle article = new NewArticle("图文消息测试", "hello world", "http://www.baidu.com",
      "http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png", null, null, null);
    robotService.sendNews(Stream.of(article).collect(Collectors.toList()));
  }
}
