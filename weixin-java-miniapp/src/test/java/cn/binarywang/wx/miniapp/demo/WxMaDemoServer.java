package cn.binarywang.wx.miniapp.demo;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import cn.binarywang.wx.miniapp.message.WxMaOutMessage;
import cn.binarywang.wx.miniapp.message.WxMaXmlOutMessage;
import cn.binarywang.wx.miniapp.test.TestConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaDemoServer {

  private static final WxMaMessageHandler logHandler = new WxMaMessageHandler() {
    @Override
    public WxMaOutMessage handle(WxMaMessage wxMessage, Map<String, Object> context,
                                 WxMaService service, WxSessionManager sessionManager) throws WxErrorException {
      System.out.println("收到消息：" + wxMessage.toString());
      service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMessage.toJson())
        .toUser(wxMessage.getFromUser()).build());
      return null;
    }
  };

  private static final WxMaMessageHandler textHandler = new WxMaMessageHandler() {
    @Override
    public WxMaOutMessage handle(WxMaMessage wxMessage, Map<String, Object> context,
                                 WxMaService service, WxSessionManager sessionManager)
      throws WxErrorException {
      service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("回复文本消息")
        .toUser(wxMessage.getFromUser()).build());
      return null;
    }

  };

  private static final WxMaMessageHandler picHandler = new WxMaMessageHandler() {
    @Override
    public WxMaOutMessage handle(WxMaMessage wxMessage, Map<String, Object> context,
                                 WxMaService service, WxSessionManager sessionManager) throws WxErrorException {
      try {
        WxMediaUploadResult uploadResult = service.getMediaService()
          .uploadMedia(WxMaConstants.MediaType.IMAGE, "png",
            ClassLoader.getSystemResourceAsStream("tmp.png"));
        service.getMsgService().sendKefuMsg(
          WxMaKefuMessage
            .newImageBuilder()
            .mediaId(uploadResult.getMediaId())
            .toUser(wxMessage.getFromUser())
            .build());
      } catch (WxErrorException e) {
        e.printStackTrace();
      }
      return null;
    }
  };

  private static final WxMaMessageHandler qrcodeHandler = new WxMaMessageHandler() {
    @Override
    public WxMaOutMessage handle(WxMaMessage wxMessage, Map<String, Object> context,
                                 WxMaService service, WxSessionManager sessionManager) throws WxErrorException {
      try {
        final File file = service.getQrcodeService().createQrcode("123", 430);
        WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia(WxMaConstants.MediaType.IMAGE, file);
        service.getMsgService().sendKefuMsg(
          WxMaKefuMessage
            .newImageBuilder()
            .mediaId(uploadResult.getMediaId())
            .toUser(wxMessage.getFromUser())
            .build());
      } catch (WxErrorException e) {
        e.printStackTrace();
      }
      return null;
    }
  };

  private static final WxMaMessageHandler customerServiceMessageHandler = new WxMaMessageHandler() {
    @Override
    public WxMaOutMessage handle(WxMaMessage message, Map<String, Object> context, WxMaService service, WxSessionManager sessionManager) {
      return new WxMaXmlOutMessage()
        .setMsgType(WxConsts.XmlMsgType.TRANSFER_CUSTOMER_SERVICE)
        .setFromUserName(message.getToUser())
        .setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000)
        .setToUserName(message.getFromUser());
    }
  };

  private static WxMaConfig config;
  private static WxMaService service;
  private static WxMaMessageRouter router;
  private static String templateId;

  public static void main(String[] args) throws Exception {
    init();

    Server server = new Server(8080);

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    ServletHolder endpointServletHolder = new ServletHolder(new WxMaPortalServlet(config, service, router));
    servletHandler.addServletWithMapping(endpointServletHolder, "/*");

    server.start();
    server.join();
  }

  private static void init() {
    try (InputStream is1 = ClassLoader.getSystemResourceAsStream("test-config.xml")) {
      TestConfig config = TestConfig.fromXml(is1);
      config.setAccessTokenLock(new ReentrantLock());
      templateId = config.getTemplateId();

      WxMaDemoServer.config = config;
      service = new WxMaServiceImpl();
      service.setWxMaConfig(config);

      router = new WxMaMessageRouter(service);

      router.rule().handler(logHandler).next()
        .rule().async(false).content("文本").handler(textHandler).end()
        .rule().async(false).content("图片").handler(picHandler).end()
        .rule().async(false).content("二维码").handler(qrcodeHandler).end()
        .rule().async(false).content("转发客服").handler(customerServiceMessageHandler).end();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
