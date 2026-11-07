package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.testng.*;
import org.testng.annotations.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Map;

/**
 * 测试消息路由器
 *
 * @author chanjarster
 */
@Test
public class WxMpMessageRouterTest {

  private WxMpService newTestService() {
    WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
    config.setAppId("test-app-id");
    WxMpService service = new WxMpServiceImpl();
    service.setWxMpConfigStorage(config);
    return service;
  }

  @Test(enabled = false)
  public void prepare(boolean async, StringBuffer sb, WxMpMessageRouter router) {
    router.setMessageDuplicateChecker(new WxMessageInMemoryDuplicateChecker());
    router
      .rule()
      .async(async)
      .msgType(WxConsts.XmlMsgType.TEXT).event(WxConsts.EventType.CLICK).eventKey("KEY_1").content("CONTENT_1")
      .handler(new WxEchoMpMessageHandler(sb, "COMBINE_4"))
      .end()
      .rule()
      .async(async)
      .msgType(WxConsts.XmlMsgType.TEXT).event(WxConsts.EventType.CLICK).eventKey("KEY_1")
      .handler(new WxEchoMpMessageHandler(sb, "COMBINE_3"))
      .end()
      .rule()
      .async(async)
      .msgType(WxConsts.XmlMsgType.TEXT).event(WxConsts.EventType.CLICK)
      .handler(new WxEchoMpMessageHandler(sb, "COMBINE_2"))
      .end()
      .rule().async(async).msgType(WxConsts.XmlMsgType.TEXT).handler(new WxEchoMpMessageHandler(sb, WxConsts.XmlMsgType.TEXT)).end()
      .rule().async(async).event(WxConsts.EventType.CLICK).handler(new WxEchoMpMessageHandler(sb, WxConsts.EventType.CLICK)).end()
      .rule().async(async).eventKey("KEY_1").handler(new WxEchoMpMessageHandler(sb, "KEY_1")).end()
      .rule().async(async).eventKeyRegex("KEY_1*").handler(new WxEchoMpMessageHandler(sb, "KEY_123")).end()
      .rule().async(async).content("CONTENT_1").handler(new WxEchoMpMessageHandler(sb, "CONTENT_1")).end()
      .rule().async(async).rContent(".*bc.*").handler(new WxEchoMpMessageHandler(sb, "abcd")).end()
      .rule().async(async).matcher(new WxMpMessageMatcher() {
      @Override
      public boolean match(WxMpXmlMessage message) {
        return "strangeformat".equals(message.getFormat());
      }
    }).handler(new WxEchoMpMessageHandler(sb, "matcher")).end()
      .rule().async(async).handler(new WxEchoMpMessageHandler(sb, "ALL")).end();
  }

  @Test(dataProvider = "messages-1")
  public void testSync(WxMpXmlMessage message, String expected) {
    StringBuffer sb = new StringBuffer();
    WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
    prepare(false, sb, router);
    router.route(message);
    Assert.assertEquals(sb.toString(), expected);
  }

  @Test(dataProvider = "messages-1")
  public void testAsync(WxMpXmlMessage message, String expected) throws InterruptedException {
    StringBuffer sb = new StringBuffer();
    WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
    prepare(true, sb, router);
    router.route(message);
    Thread.sleep(500);
    router.shutDownExecutorService();
    Assert.assertEquals(sb.toString(), expected);
  }

  @Test(dataProvider = "messages-1")
  public void testExternalExcutorService(WxMpXmlMessage message, String expected) throws InterruptedException {
    StringBuffer sb = new StringBuffer();
    ExecutorService executorService = Executors.newFixedThreadPool(100);
    WxMpMessageRouter router = new WxMpMessageRouter(newTestService(), executorService);
    prepare(true, sb, router);
    router.route(message);
    Thread.sleep(500);
    executorService.shutdown();
    Assert.assertEquals(sb.toString(), expected);
  }


  public void testConcurrency() throws InterruptedException {
    final WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
    router.rule().handler(new WxMpMessageHandler() {
      @Override
      public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                      WxSessionManager sessionManager) {
        return null;
      }
    }).end();

    final WxMpXmlMessage m = new WxMpXmlMessage();
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

  @DataProvider(name = "messages-1")
  public Object[][] messages2() {
    WxMpXmlMessage message1 = new WxMpXmlMessage();
    message1.setMsgType(WxConsts.XmlMsgType.TEXT);

    WxMpXmlMessage message2 = new WxMpXmlMessage();
    message2.setEvent(WxConsts.EventType.CLICK);

    WxMpXmlMessage message3 = new WxMpXmlMessage();
    message3.setEventKey("KEY_1");

    WxMpXmlMessage message4 = new WxMpXmlMessage();
    message4.setContent("CONTENT_1");

    WxMpXmlMessage message5 = new WxMpXmlMessage();
    message5.setContent("BLA");

    WxMpXmlMessage message6 = new WxMpXmlMessage();
    message6.setContent("abcd");

    WxMpXmlMessage message7 = new WxMpXmlMessage();
    message7.setFormat("strangeformat");

    WxMpXmlMessage c2 = new WxMpXmlMessage();
    c2.setMsgType(WxConsts.XmlMsgType.TEXT);
    c2.setEvent(WxConsts.EventType.CLICK);

    WxMpXmlMessage c3 = new WxMpXmlMessage();
    c3.setMsgType(WxConsts.XmlMsgType.TEXT);
    c3.setEvent(WxConsts.EventType.CLICK);
    c3.setEventKey("KEY_1");

    WxMpXmlMessage c4 = new WxMpXmlMessage();
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

  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean1(StandardSessionManager ism) throws InterruptedException {

    // 两个同步请求，看是否处理完毕后会被清理掉
    final WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
    router.setSessionManager(ism);
    router
      .rule().async(false).handler(new WxSessionMessageHandler()).next()
      .rule().async(false).handler(new WxSessionMessageHandler()).end();

    WxMpXmlMessage msg = new WxMpXmlMessage();
    msg.setFromUser("abc");
    router.route(msg);

    Thread.sleep(2000);
    Assert.assertEquals(ism.getActiveSessions(), 0);

  }

  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean2(StandardSessionManager ism) throws InterruptedException {

    // 1个同步,1个异步请求，看是否处理完毕后会被清理掉
    {
      final WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
      router.setSessionManager(ism);
      router
        .rule().async(false).handler(new WxSessionMessageHandler()).next()
        .rule().async(true).handler(new WxSessionMessageHandler()).end();

      WxMpXmlMessage msg = new WxMpXmlMessage();
      msg.setFromUser("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }
    {
      final WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
      router.setSessionManager(ism);
      router
        .rule().async(true).handler(new WxSessionMessageHandler()).next()
        .rule().async(false).handler(new WxSessionMessageHandler()).end();

      WxMpXmlMessage msg = new WxMpXmlMessage();
      msg.setFromUser("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }

  }

  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean3(StandardSessionManager ism) throws InterruptedException {

    // 2个异步请求，看是否处理完毕后会被清理掉
    final WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
    router.setSessionManager(ism);
    router
      .rule().async(true).handler(new WxSessionMessageHandler()).next()
      .rule().async(true).handler(new WxSessionMessageHandler()).end();

    WxMpXmlMessage msg = new WxMpXmlMessage();
    msg.setFromUser("abc");
    router.route(msg);

    Thread.sleep(2000);
    Assert.assertEquals(ism.getActiveSessions(), 0);

  }

  @Test(dataProvider = "standardSessionManager")
  public void testSessionClean4(StandardSessionManager ism) throws InterruptedException {

    // 一个同步请求，看是否处理完毕后会被清理掉
    {
      final WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
      router.setSessionManager(ism);
      router
        .rule().async(false).handler(new WxSessionMessageHandler()).end();

      WxMpXmlMessage msg = new WxMpXmlMessage();
      msg.setFromUser("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }

    {
      final WxMpMessageRouter router = new WxMpMessageRouter(newTestService());
      router.setSessionManager(ism);
      router
        .rule().async(true).handler(new WxSessionMessageHandler()).end();

      WxMpXmlMessage msg = new WxMpXmlMessage();
      msg.setFromUser("abc");
      router.route(msg);

      Thread.sleep(2000);
      Assert.assertEquals(ism.getActiveSessions(), 0);
    }
  }

  public static class WxEchoMpMessageHandler implements WxMpMessageHandler {

    private StringBuffer sb;
    private String echoStr;

    public WxEchoMpMessageHandler(StringBuffer sb, String echoStr) {
      this.sb = sb;
      this.echoStr = echoStr;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
      this.sb.append(this.echoStr).append(',');
      return null;
    }

  }

  public static class WxSessionMessageHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
      sessionManager.getSession(wxMessage.getFromUser());
      return null;
    }

  }

}
