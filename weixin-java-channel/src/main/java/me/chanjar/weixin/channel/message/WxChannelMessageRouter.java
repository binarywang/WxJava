package me.chanjar.weixin.channel.message;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateCheckerSingleton;
import me.chanjar.weixin.common.session.InternalSession;
import me.chanjar.weixin.common.session.InternalSessionManager;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.LogExceptionHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 消息路由器
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@Slf4j
public class WxChannelMessageRouter {
  /** 规则列表 */
  private final List<WxChannelMessageRouterRule<? extends WxChannelMessage>> rules = new ArrayList<>();
  /** 线程池 */
  private ExecutorService executorService;
  /** 异常处理器 */
  private WxErrorExceptionHandler exceptionHandler;
  /** 消息重复检查器 */
  private WxMessageDuplicateChecker messageDuplicateChecker;

  public WxChannelMessageRouter() {
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("WxChMsgRouter-pool-%d").build();
    this.executorService = new ThreadPoolExecutor(2, 100,
      0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    //this.sessionManager = new StandardSessionManager();
    this.exceptionHandler = new LogExceptionHandler();
    this.messageDuplicateChecker = WxMessageInMemoryDuplicateCheckerSingleton.getInstance();
  }

  /**
   * 使用自定义的 {@link ExecutorService}.
   */
  public WxChannelMessageRouter(ExecutorService executorService) {
    this.executorService = executorService;
    this.exceptionHandler = new LogExceptionHandler();
    this.messageDuplicateChecker = WxMessageInMemoryDuplicateCheckerSingleton.getInstance();
  }

  /**
   * 系统退出前，应该调用该方法
   */
  public void shutDownExecutorService() {
    this.executorService.shutdown();
  }

  /**
   * 系统退出前，应该调用该方法，增加了超时时间检测
   */
  public void shutDownExecutorService(Integer second) {
    this.executorService.shutdown();
    try {
      if (!this.executorService.awaitTermination(second, TimeUnit.SECONDS)) {
        this.executorService.shutdownNow();
        if (!this.executorService.awaitTermination(second, TimeUnit.SECONDS)) {
          log.error("线程池未关闭！");
        }
      }
    } catch (InterruptedException ie) {
      this.executorService.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }

  /**
   * <pre>
   * 设置自定义的 {@link ExecutorService}
   * 如果不调用该方法，默认使用内置的
   * </pre>
   */
  public void setExecutorService(ExecutorService executorService) {
    this.executorService = executorService;
  }

  /**
   * 消息路由入口
   *
   * @param message 消息
   * @param content 原始消息（解密之后的）
   * @param appId   appId
   * @param service 服务实例
   * @return 返回值
   */
  public Object route(final WxChannelMessage message, final String content, final String appId,
    final WxChannelService service) {
    return this.route(message, content, appId, new HashMap<>(2), service, new StandardSessionManager());
  }

  /**
   * 路由微信消息
   *
   * @param message 消息
   * @param content 消息原始内容
   * @param context 上下文
   * @return Object
   */
  public Object route(final WxChannelMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxChannelService service, final WxSessionManager sessionManager) {
    // 如果是重复消息，那么就不做处理
    if (isMsgDuplicated(message)) {
      log.info("收到重复消息，{}", content);
      return null;
    }

    final List<WxChannelMessageRouterRule<? extends WxChannelMessage>> matchRules = new ArrayList<>();

    // 收集匹配的规则
    for (final WxChannelMessageRouterRule<? extends WxChannelMessage> rule : this.rules) {
      if (rule.isMatch(message)) {
        matchRules.add(rule);
        if (!rule.isNext()) {
          break;
        }
      }
    }

    if (matchRules.size() == 0) {
      return null;
    }
    final List<Future<?>> futures = new ArrayList<>();
    Object result = null;
    for (final WxChannelMessageRouterRule<? extends WxChannelMessage> rule : matchRules) {
      // 返回最后一个非异步的rule的执行结果
      if (rule.isAsync()) {
        futures.add(
          this.executorService.submit(() -> {
            rule.process(message, content, appId, context, service, sessionManager, exceptionHandler);
          })
        );
      } else {
        result = rule.process(message, content, appId, context, service, sessionManager, exceptionHandler);
        // 在同步操作结束，session访问结束
        sessionEndAccess(sessionManager, message, false);
      }
    }

    if (futures.size() > 0) {
      this.executorService.submit(() -> {
        for (Future<?> future : futures) {
          try {
            future.get();
            // 异步操作结束，session访问结束
            sessionEndAccess(sessionManager, message, true);
          } catch (InterruptedException | ExecutionException e) {
            log.error("Error happened when wait task finish", e);
          }
        }
      });
    }
    return result;
  }

  /**
   * 判断消息是否重复
   *
   * @param wxMessage 消息
   * @return 是否重复
   */
  protected boolean isMsgDuplicated(WxChannelMessage wxMessage) {
    String messageId = this.generateMessageId(wxMessage);
    return this.messageDuplicateChecker.isDuplicate(messageId);
  }

  /**
   * 生成消息id
   *
   * @return 消息id
   */
  protected String generateMessageId(WxChannelMessage wxMessage) {
    StringBuilder sb = new StringBuilder();
    if (wxMessage.getMsgId() == null) {
      sb.append(wxMessage.getCreateTime())
        .append("-").append(wxMessage.getFromUser())
        .append("-").append(StringUtils.trimToEmpty(wxMessage.getEvent()));
    } else {
      sb.append(wxMessage.getMsgId())
        .append("-").append(wxMessage.getCreateTime())
        .append("-").append(wxMessage.getFromUser());
    }

    if (StringUtils.isNotEmpty(wxMessage.getToUser())) {
      sb.append("-").append(wxMessage.getToUser());
    }
    return sb.toString();
  }

  /**
   * 对session的访问结束
   *
   * @param sessionManager session管理器
   * @param message 消息
   * @param async 是否异步 打印log用
   */
  private void sessionEndAccess(WxSessionManager sessionManager, WxChannelMessage message, boolean async) {
    log.debug("End session access: async={}, sessionId={}", async, message.getFromUser());
    InternalSession session = ((InternalSessionManager) sessionManager).findSession(message.getFromUser());
    if (session != null) {
      session.endAccess();
    }
  }


}
