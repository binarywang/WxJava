package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.open.api.WxOpenService;

import java.util.HashMap;
import java.util.Map;

public class WxOpenMessageRouter extends WxMpMessageRouter {
  private WxOpenService wxOpenService;

  public WxOpenMessageRouter(WxOpenService wxOpenService) {
    super(null);
    this.wxOpenService = wxOpenService;
  }

  public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage, String appId) {
    return route(wxMessage, new HashMap<>(), appId);
  }

  public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage, final Map<String, Object> context, String appId) {
    return route(wxMessage, context, wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId));
  }

  /**
   * 路由微信消息到小程序服务 (Route WeChat message to Mini App service)
   *
   * @param wxMessage the wx message
   * @param appId     the app id
   * @return the wx mp xml out message
   */
  public WxMpXmlOutMessage routeForMa(final WxMpXmlMessage wxMessage, String appId) {
    return routeForMa(wxMessage, new HashMap<>(), appId);
  }

  /**
   * 路由微信消息到小程序服务 (Route WeChat message to Mini App service)
   *
   * @param wxMessage the wx message
   * @param context   the context
   * @param appId     the app id
   * @return the wx mp xml out message
   */
  public WxMpXmlOutMessage routeForMa(final WxMpXmlMessage wxMessage, final Map<String, Object> context, String appId) {
    // 将小程序服务放入上下文中，以便处理器可以访问 (Put Mini App service in context so handlers can access it)
    context.put("wxOpenMaService", wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId));
    return route(wxMessage, context, wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId));
  }
}
