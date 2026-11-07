package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.message.WxCpMessageHandler;
import me.chanjar.weixin.cp.message.WxCpMessageMatcher;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 测试消息路由器
 *
 * @author Daniel Qian
 */
@Test
public class WxCpMessageRouterTest {

  /**
   * Prepare.
   *
   * @param async  the async
   * @param sb     the sb
   * @param router the router
   */
  @Test(enabled = false)
  public void prepare(boolean async, StringBuffer sb, WxCpMessageRouter router) {
    router.setMessageDuplicateChecker(new me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker());
    router
      .rule()
      .async(async)
      .msgType(WxConsts.XmlMsgType.TEXT).event(WxConsts.EventType.CLICK).eventKey("KEY_1").content("CONTENT_1")
      .handler(new WxEchoCpMessageHandler(sb, "COMBINE_4"))
      .end()
      .rule()
      .async(async)
      .msgType(WxConsts.XmlMsgType.TEXT).event(WxConsts.EventType.CLICK).eventKey("KEY_1")
      .handler(new WxEchoCpMessageHandler(sb, "COMBINE_3"))
      .end()
      .rule()
      .async(async)
      .msgType(WxConsts.XmlMsgType.TEXT).event(WxConsts.EventType.CLICK)
      .handler(new WxEchoCpMessageHandler(sb, "COMBINE_2"))
      .end()
      .rule().async(async).msgType(WxConsts.XmlMsgType.TEXT).handler(new WxEchoCpMessageHandler(sb,
        WxConsts.XmlMsgType.TEXT)).end()
      .rule().async(async).event(WxConsts.EventType.CLICK).handler(new WxEchoCpMessageHandler(sb,
        WxConsts.EventType.CLICK)).end()
      .rule().async(async).eventKey("KEY_1").handler(new WxEchoCpMessageHandler(sb, "KEY_1")).end()
      .rule().async(async).content("CONTENT_1").handler(new WxEchoCpMessageHandler(sb, "CONTENT_1")).end()
      .rule().async(async).rContent(".*bc.*").handler(new WxEchoCpMessageHandler(sb, "abcd")).end()
      .rule().async(async).matcher(new WxCpMessageMatcher() {
        @Override
        public boolean match(WxCpXmlMessage message) {
          return "strangeformat".equals(message.getFormat());
        }
      }).handler(new WxEchoCpMessageHandler(sb, "matcher")).end()
      .rule().async(async).handler(new WxEchoCpMessageHandler(sb, "ALL")).end();
  }

  /**
   * Test sync.
   *
   * @param message  the message
   * @param expected the expected
   */
  @Test(dataProvider = "messages-1")
  public void testSync(WxCpXmlMessage message, String expected) {
    StringBuffer sb = new StringBuffer();
    WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
    prepare(false, sb, router);
    router.route(message);
    Assert.assertEquals(sb.toString(), expected);
  }

  /**
   * Test async.
   *
   * @param message  the message
   * @param expected the expected
   * @throws InterruptedException the interrupted exception
   */
  @Test(dataProvider = "messages-1")
  public void testAsync(WxCpXmlMessage message, String expected) throws InterruptedException {
    StringBuffer sb = new StringBuffer();
    WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
    prepare(true, sb, router);
    router.route(message);
    Thread.sleep(500);
    Assert.assertEquals(sb.toString(), expected);
  }

  /**
   * Test concurrency.
   *
   * @throws InterruptedException the interrupted exception
   */
  public void testConcurrency() throws InterruptedException {
    final WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
    router.rule().handler(new WxCpMessageHandler() {
      @Override
      public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService wxCpService,
                                      WxSessionManager sessionManager) {
        return null;
      }
    }).end();

    final WxCpXmlMessage m = new WxCpXmlMessage();
    Runnable r = () -> {
      router.route(m);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    };
    for (int i = 0; i < 10; i++) {
      new Thread(r).start();
    }

    Thread.sleep(2000);
  }

  /**
   * Messages 2 object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider(name = "messages-1")
  public Object[][] messages2() {
    WxCpXmlMessage message1 = new WxCpXmlMessage();
    message1.setMsgType(WxConsts.XmlMsgType.TEXT);

    WxCpXmlMessage message2 = new WxCpXmlMessage();
    message2.setEvent(WxConsts.EventType.CLICK);

    WxCpXmlMessage message3 = new WxCpXmlMessage();
    message3.setEventKey("KEY_1");

    WxCpXmlMessage message4 = new WxCpXmlMessage();
    message4.setContent("CONTENT_1");

    WxCpXmlMessage message5 = new WxCpXmlMessage();
    message5.setContent("BLA");

    WxCpXmlMessage message6 = new WxCpXmlMessage();
    message6.setContent("abcd");

    WxCpXmlMessage message7 = new WxCpXmlMessage();
    message7.setFormat("strangeformat");

    WxCpXmlMessage c2 = new WxCpXmlMessage();
    c2.setMsgType(WxConsts.XmlMsgType.TEXT);
    c2.setEvent(WxConsts.EventType.CLICK);

    WxCpXmlMessage c3 = new WxCpXmlMessage();
    c3.setMsgType(WxConsts.XmlMsgType.TEXT);
    c3.setEvent(WxConsts.EventType.CLICK);
    c3.setEventKey("KEY_1");

    WxCpXmlMessage c4 = new WxCpXmlMessage();
    c4.setMsgType(WxConsts.XmlMsgType.TEXT);
    c4.setEvent(WxConsts.EventType.CLICK);
    c4.setEventKey("KEY_1");
    c4.setContent("CONTENT_1");


    return new Object[][]{
      new Object[]{message1, WxConsts.XmlMsgType.TEXT + ","},
      new Object[]{message2, WxConsts.EventType.CLICK + ","},
      new Object[]{message3, "KEY_1,"},
      new Object[]{message4, "CONTENT_1,"},
      new Object[]{message5, "ALL,"},
      new Object[]{message6, "abcd,"},
      new Object[]{message7, "matcher,"},
      new Object[]{c2, "COMBINE_2,"},
      new Object[]{c3, "COMBINE_3,"},
      new Object[]{c4, "COMBINE_4,"}
    };

  }

  /**
   * Standard session manager object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] standardSessionManager() {

    // 故意把session存活时间变短，清理更频繁
    StandardSessionManager ism = new StandardSessionManager();
    ism.setMaxInactiveInterval(1);
    ism.setProcessExpiresFrequency(1);
    ism.setBackgroundProcessorDelay(1);

    return new Object[][]{
      new Object[]{ism}
    };

  }

  /**
   * Test session clean 1.
   *
   * @param ism the ism
   * @throws InterruptedException the interrupted exception
   */
  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean1(StandardSessionManager ism) throws InterruptedException {

    // 两个同步请求，看是否处理完毕后会被清理掉
    final WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
    router.setSessionManager(ism);
    router
      .rule().async(false).handler(new WxSessionMessageHandler()).next()
      .rule().async(false).handler(new WxSessionMessageHandler()).end();

    WxCpXmlMessage msg = new WxCpXmlMessage();
    msg.setFromUserName("abc");
    router.route(msg);

    Thread.sleep(2000);
    Assert.assertEquals(ism.getActiveSessions(), 0);

  }

  /**
   * Test session clean 2.
   *
   * @param ism the ism
   * @throws InterruptedException the interrupted exception
   */
  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean2(StandardSessionManager ism) throws InterruptedException {

    // 1个同步,1个异步请求，看是否处理完毕后会被清理掉
    {
      final WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
      router.setSessionManager(ism);
      router
        .rule().async(false).handler(new WxSessionMessageHandler()).next()
        .rule().async(true).handler(new WxSessionMessageHandler()).end();

      WxCpXmlMessage msg = new WxCpXmlMessage();
      msg.setFromUserName("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }
    {
      final WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
      router.setSessionManager(ism);
      router
        .rule().async(true).handler(new WxSessionMessageHandler()).next()
        .rule().async(false).handler(new WxSessionMessageHandler()).end();

      WxCpXmlMessage msg = new WxCpXmlMessage();
      msg.setFromUserName("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }

  }

  /**
   * Test session clean 3.
   *
   * @param ism the ism
   * @throws InterruptedException the interrupted exception
   */
  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean3(StandardSessionManager ism) throws InterruptedException {

    // 2个异步请求，看是否处理完毕后会被清理掉
    final WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
    router.setSessionManager(ism);
    router
      .rule().async(true).handler(new WxSessionMessageHandler()).next()
      .rule().async(true).handler(new WxSessionMessageHandler()).end();

    WxCpXmlMessage msg = new WxCpXmlMessage();
    msg.setFromUserName("abc");
    router.route(msg);

    Thread.sleep(2000);
    Assert.assertEquals(ism.getActiveSessions(), 0);

  }

  /**
   * Test session clean 4.
   *
   * @param ism the ism
   * @throws InterruptedException the interrupted exception
   */
  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean4(StandardSessionManager ism) throws InterruptedException {

    // 一个同步请求，看是否处理完毕后会被清理掉
    {
      final WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
      router.setSessionManager(ism);
      router
        .rule().async(false).handler(new WxSessionMessageHandler()).end();

      WxCpXmlMessage msg = new WxCpXmlMessage();
      msg.setFromUserName("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }

    {
      final WxCpMessageRouter router = new WxCpMessageRouter(new WxCpServiceImpl());
      router.setSessionManager(ism);
      router
        .rule().async(true).handler(new WxSessionMessageHandler()).end();

      WxCpXmlMessage msg = new WxCpXmlMessage();
      msg.setFromUserName("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }
  }

  /**
   * The type Wx echo cp message handler.
   */
  public static class WxEchoCpMessageHandler implements WxCpMessageHandler {

    private final StringBuffer sb;
    private final String echoStr;

    /**
     * Instantiates a new Wx echo cp message handler.
     *
     * @param sb      the sb
     * @param echoStr the echo str
     */
    public WxEchoCpMessageHandler(StringBuffer sb, String echoStr) {
      this.sb = sb;
      this.echoStr = echoStr;
    }

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService wxCpService,
                                    WxSessionManager sessionManager) {
      this.sb.append(this.echoStr).append(',');
      return null;
    }

  }

  /**
   * The type Wx session message handler.
   */
  public static class WxSessionMessageHandler implements WxCpMessageHandler {

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService wxCpService,
                                    WxSessionManager sessionManager) {
      sessionManager.getSession(wxMessage.getFromUserName());
      return null;
    }

  }

}
