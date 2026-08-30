package com.binarywang.wxjava.store.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreService;
import com.binarywang.wxjava.store.enums.MessageType;
import com.binarywang.wxjava.store.message.rule.WxStoreMessageHandler;
import com.binarywang.wxjava.store.message.rule.WxStoreMessageInterceptor;
import com.binarywang.wxjava.store.message.rule.WxStoreMessageMatcher;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.XmlUtils;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@Accessors(chain = true)
@Slf4j
public class WxStoreMessageRouterRule<T extends WxStoreMessage> {
  /** 是否异步, 默认是true */
  private boolean async = true;
  /** 消息类型 */
  private String msgType;
  /** 事件类型 */
  private String event;
  /** 自定义匹配器 */
  private WxStoreMessageMatcher matcher;
  /** 进入下一个rule，默认是false */
  private boolean next = false;
  /** 消息处理器 */
  @Singular
  private List<WxStoreMessageHandler<T>> handlers = new ArrayList<>(4);
  /** 消息拦截器 */
  @Singular
  private List<WxStoreMessageInterceptor> interceptors = new ArrayList<>(4);
  /** 消息类型 */
  private Class<T> messageClass;

  public WxStoreMessageRouterRule() {
  }

  /**
   * 设置事件
   *
   * @param event 事件
   * @return this
   */
  public WxStoreMessageRouterRule<T> setEvent(String event) {
    this.msgType = MessageType.EVENT.getKey();
    this.event = event;
    return this;
  }

  /**
   * 测试消息是否匹配规则
   *
   * @param message 消息
   * @return 是否匹配
   */
  protected boolean isMatch(WxStoreMessage message) {
    String msgType = message.getMsgType() == null ? null : message.getMsgType().toLowerCase();
    String event = message.getEvent() == null ? null : message.getEvent().toLowerCase();

    boolean matchMsgType = this.msgType == null || this.msgType.toLowerCase().equals(msgType);
    boolean matchEvent = this.event == null || this.event.toLowerCase().equals(event);
    boolean matchMatcher = this.matcher == null || this.matcher.match(message);

    return matchMsgType && matchEvent && matchMatcher;
  }

  /**
   * 处理微信推送过来的消息
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param service        服务实例
   * @param sessionManager session管理器
   * @param exceptionHandler 异常处理器
   * @return 返回消息
   */
  protected Object process(WxStoreMessage message, String content, String appId, Map<String, Object> context,
    WxStoreService service, WxSessionManager sessionManager, WxErrorExceptionHandler exceptionHandler) {
    if (context == null) {
      context = new HashMap<>(16);
    }
    // 重新反序列化消息
    T tempMessage = deserialize(content, messageClass, service);
    if (tempMessage == null) {
      log.error("消息重新反序列化失败，请检查消息格式是否正确或者指定正确的messageClass");
      return null;
    }

    Object outMessage = null;
    try {
      // 如果拦截器不通过，返回null
      for (WxStoreMessageInterceptor interceptor : this.interceptors) {
        if (!interceptor.intercept(message, content, context, service, sessionManager)) {
          return null;
        }
      }

      // 交给handler处理
      for (WxStoreMessageHandler<T> handler : this.handlers) {
        // 返回最后handler的结果
        if (handler == null) {
          continue;
        }

        outMessage = handler.handle(tempMessage, content, appId, context, sessionManager);
      }
    } catch (WxErrorException e) {
      exceptionHandler.handle(e);
    }
    return outMessage;
  }

  /**
   * 重新反序列化消息
   *
   * @param content 消息内容
   * @param clazz 消息类型
   * @param service 服务实例
   * @return T
   */
  private T deserialize(String content, Class<T> clazz, WxStoreService service) {
    String msgFormat = service.getConfig().getMsgDataFormat();
    T t = deserialize(content, clazz, msgFormat);
    if (t != null) {
      return t;
    }
    // 如果指定的消息格式不正确，根据内容猜猜格式
    if (StringUtils.isNotBlank(content)) {
      if (content.startsWith("<xml>")) {
        t = deserialize(content, clazz, "XML");
      } else if (content.startsWith("{")){
        t = deserialize(content, clazz, "JSON");
      }
    }
    return t;
  }

  /**
   * 重新反序列化消息
   *
   * @param content 消息内容
   * @param clazz 消息类型
   * @param msgFormat 消息格式
   * @return T
   */
  private T deserialize(String content, Class<T> clazz, String msgFormat) {
    T message = null;
    // 重新反序列化原始消息
    if (msgFormat == null || msgFormat.equalsIgnoreCase("JSON")) {
      message = JsonUtils.decode(content, clazz);
    } else {
      message = XmlUtils.decode(content, clazz);
    }
    return message;
  }
}
