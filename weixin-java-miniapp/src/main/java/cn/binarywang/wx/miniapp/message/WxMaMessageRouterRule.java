package cn.binarywang.wx.miniapp.message;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaMessageRouterRule {
  private final WxMaMessageRouter routerBuilder;

  private boolean async = true;

  private String fromUser;

  private String msgType;

  private String event;

  private String eventKey;

  private String content;

  private String rContent;

  private String title;

  private WxMaMessageMatcher matcher;

  private boolean reEnter = false;

  private List<WxMaMessageHandler> handlers = new ArrayList<>();

  private List<WxMaMessageInterceptor> interceptors = new ArrayList<>();

  public WxMaMessageRouterRule(WxMaMessageRouter routerBuilder) {
    this.routerBuilder = routerBuilder;
  }

  /**
   * 设置是否异步执行，默认是true.
   */
  public WxMaMessageRouterRule async(boolean async) {
    this.async = async;
    return this;
  }

  /**
   * 如果msgType等于某值.
   */
  public WxMaMessageRouterRule msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

  /**
   * 标题,发送小程序页卡时有效
   * @param title
   * @return
   */
  public WxMaMessageRouterRule title(String title){
    this.title = title;
    return this;
  }

  /**
   * 如果event等于某值.
   */
  public WxMaMessageRouterRule event(String event) {
    this.event = event;
    return this;
  }

  /**
   * 如果eventKey等于某值.
   */
  public WxMaMessageRouterRule eventKey(String eventKey) {
    this.eventKey = eventKey;
    return this;
  }

  /**
   * 如果content等于某值.
   */
  public WxMaMessageRouterRule content(String content) {
    this.content = content;
    return this;
  }

  /**
   * 如果content匹配该正则表达式.
   */
  public WxMaMessageRouterRule rContent(String regex) {
    this.rContent = regex;
    return this;
  }

  /**
   * 如果fromUser等于某值.
   */
  public WxMaMessageRouterRule fromUser(String fromUser) {
    this.fromUser = fromUser;
    return this;
  }



  /**
   * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候.
   */
  public WxMaMessageRouterRule matcher(WxMaMessageMatcher matcher) {
    this.matcher = matcher;
    return this;
  }

  /**
   * 设置微信消息拦截器.
   */
  public WxMaMessageRouterRule interceptor(WxMaMessageInterceptor interceptor) {
    return interceptor(interceptor, (WxMaMessageInterceptor[]) null);
  }

  /**
   * 设置微信消息拦截器.
   */
  public WxMaMessageRouterRule interceptor(WxMaMessageInterceptor interceptor, WxMaMessageInterceptor... otherInterceptors) {
    this.interceptors.add(interceptor);
    if (otherInterceptors != null && otherInterceptors.length > 0) {
      Collections.addAll(this.interceptors, otherInterceptors);
    }
    return this;
  }

  /**
   * 设置微信消息处理器.
   */
  public WxMaMessageRouterRule handler(WxMaMessageHandler handler) {
    return handler(handler, (WxMaMessageHandler[]) null);
  }

  /**
   * 设置微信消息处理器.
   */
  public WxMaMessageRouterRule handler(WxMaMessageHandler handler, WxMaMessageHandler... otherHandlers) {
    this.handlers.add(handler);
    if (otherHandlers != null && otherHandlers.length > 0) {
      Collections.addAll(this.handlers, otherHandlers);
    }
    return this;
  }

  /**
   * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则.
   */
  public WxMaMessageRouter end() {
    this.routerBuilder.getRules().add(this);
    return this.routerBuilder;
  }

  /**
   * 规则结束，但是消息还会进入其他规则.
   */
  public WxMaMessageRouter next() {
    this.reEnter = true;
    return end();
  }



  /**
   * 将微信自定义的事件修正为不区分大小写.
   * 比如框架定义的事件常量为click，但微信传递过来的却是CLICK
   */
  protected boolean test(WxMaMessage wxMessage) {
    return
      (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUser()))
        &&
        (this.msgType == null || this.msgType.toLowerCase().equals((wxMessage.getMsgType() == null ? null : wxMessage.getMsgType().toLowerCase())))
        &&
        (this.event == null || this.event.toLowerCase().equals((wxMessage.getEvent() == null ? null : wxMessage.getEvent().toLowerCase())))
        &&
        (this.content == null || this.content
          .equals(wxMessage.getContent() == null ? null : wxMessage.getContent().trim()))
        &&
        (this.rContent == null || Pattern
          .matches(this.rContent, wxMessage.getContent() == null ? "" : wxMessage.getContent().trim()))
        &&
        (this.matcher == null || this.matcher.match(wxMessage))
        &&
        (this.title == null || this.title
          .equals(wxMessage.getTitle() == null ? null : wxMessage.getTitle().trim()))
      ;
  }

  /**
   * 处理微信推送过来的消息.
   */
  protected WxMaOutMessage service(WxMaMessage wxMessage,
                         Map<String, Object> context,
                         WxMaService wxMaService,
                         WxSessionManager sessionManager,
                         WxErrorExceptionHandler exceptionHandler) {
    if (context == null) {
      context = new HashMap<>(16);
    }

    WxMaOutMessage outMessage = null;
    try {
      // 如果拦截器不通过
      for (WxMaMessageInterceptor interceptor : this.interceptors) {
        if (!interceptor.intercept(wxMessage, context, wxMaService, sessionManager)) {
          return null;
        }
      }

      // 交给handler处理
      for (WxMaMessageHandler handler : this.handlers) {
        // 返回最后handler的结果
        if (handler == null) {
          continue;
        }
        outMessage = handler.handle(wxMessage, context, wxMaService, sessionManager);
      }
    } catch (WxErrorException e) {
      exceptionHandler.handle(e);
    }

    return outMessage;
  }

  public WxMaMessageRouter getRouterBuilder() {
    return this.routerBuilder;
  }

  public boolean isAsync() {
    return this.async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }

  public String getFromUser() {
    return this.fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public String getMsgType() {
    return this.msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getEvent() {
    return this.event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getEventKey() {
    return this.eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getrContent() {
    return this.rContent;
  }

  public void setrContent(String rContent) {
    this.rContent = rContent;
  }

  public WxMaMessageMatcher getMatcher() {
    return this.matcher;
  }

  public void setMatcher(WxMaMessageMatcher matcher) {
    this.matcher = matcher;
  }

  public boolean isReEnter() {
    return this.reEnter;
  }

  public void setReEnter(boolean reEnter) {
    this.reEnter = reEnter;
  }

  public List<WxMaMessageHandler> getHandlers() {
    return this.handlers;
  }

  public void setHandlers(List<WxMaMessageHandler> handlers) {
    this.handlers = handlers;
  }

  public List<WxMaMessageInterceptor> getInterceptors() {
    return this.interceptors;
  }

  public void setInterceptors(List<WxMaMessageInterceptor> interceptors) {
    this.interceptors = interceptors;
  }
}
