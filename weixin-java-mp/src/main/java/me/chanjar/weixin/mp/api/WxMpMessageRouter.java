package me.chanjar.weixin.mp.api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateCheckerSingleton;
import me.chanjar.weixin.common.session.InternalSession;
import me.chanjar.weixin.common.session.InternalSessionManager;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.LogExceptionHandler;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link WxMpMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link WxMpMessageRouterRule#end()}或者{@link WxMpMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * WxMpMessageRouter router = new WxMpMessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将WxXmlMessage交给消息路由器
 * router.route(message);
 *
 * </pre>
 *
 * @author Daniel Qian
 */
@Slf4j
@AllArgsConstructor
public class WxMpMessageRouter {
  private static final int DEFAULT_THREAD_POOL_SIZE = 100;
  private final List<WxMpMessageRouterRule> rules = new ArrayList<>();

  private final WxMpService wxMpService;

  private ExecutorService executorService;

  private WxMessageDuplicateChecker messageDuplicateChecker;

  private WxSessionManager sessionManager;

  private WxErrorExceptionHandler exceptionHandler;

  /**
   * Instantiates a new Wx mp message router.
   *
   * @param wxMpService the wx mp service
   */
  public WxMpMessageRouter(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("WxMpMessageRouter-pool-%d").build();
    this.executorService = new ThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE, DEFAULT_THREAD_POOL_SIZE,
      0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    this.messageDuplicateChecker = WxMessageInMemoryDuplicateCheckerSingleton.getInstance();
    this.sessionManager = new StandardSessionManager();
    this.exceptionHandler = new LogExceptionHandler();
  }

  /**
   * 使用自定义的 {@link ExecutorService}.
   *
   * @param wxMpService     the wx mp service
   * @param executorService the executor service
   */
  public WxMpMessageRouter(WxMpService wxMpService, ExecutorService executorService) {
    this.wxMpService = wxMpService;
    this.executorService = executorService;
    this.messageDuplicateChecker = WxMessageInMemoryDuplicateCheckerSingleton.getInstance();
    this.sessionManager = new StandardSessionManager();
    this.exceptionHandler = new LogExceptionHandler();
  }

  /**
   * 系统退出前，应该调用该方法
   */
  public void shutDownExecutorService() {
    this.executorService.shutdown();
  }

  /**
   * 系统退出前，应该调用该方法，增加了超时时间检测
   *
   * @param second the second
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
   * 如果不调用该方法，默认使用 Executors.newFixedThreadPool(100)
   * </pre>
   *
   * @param executorService the executor service
   */
  public void setExecutorService(ExecutorService executorService) {
    this.executorService = executorService;
  }

  /**
   * <pre>
   * 设置自定义的 {@link me.chanjar.weixin.common.api.WxMessageDuplicateChecker}
   * 如果不调用该方法，默认使用 {@link me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker}
   * </pre>
   *
   * @param messageDuplicateChecker the message duplicate checker
   */
  public void setMessageDuplicateChecker(WxMessageDuplicateChecker messageDuplicateChecker) {
    this.messageDuplicateChecker = messageDuplicateChecker;
  }

  /**
   * <pre>
   * 设置自定义的{@link me.chanjar.weixin.common.session.WxSessionManager}
   * 如果不调用该方法，默认使用 {@link me.chanjar.weixin.common.session.StandardSessionManager}
   * </pre>
   *
   * @param sessionManager the session manager
   */
  public void setSessionManager(WxSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * <pre>
   * 设置自定义的{@link me.chanjar.weixin.common.api.WxErrorExceptionHandler}
   * 如果不调用该方法，默认使用 {@link me.chanjar.weixin.common.util.LogExceptionHandler}
   * </pre>
   *
   * @param exceptionHandler the exception handler
   */
  public void setExceptionHandler(WxErrorExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  /**
   * Gets rules.
   *
   * @return the rules
   */
  List<WxMpMessageRouterRule> getRules() {
    return this.rules;
  }

  /**
   * 开始一个新的Route规则.
   *
   * @return the wx mp message router rule
   */
  public WxMpMessageRouterRule rule() {
    return new WxMpMessageRouterRule(this);
  }

  /**
   * 处理微信消息.
   *
   * @param wxMessage the wx message
   * @param context   the context
   * @return the wx mp xml out message
   */
  public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage, final Map<String, Object> context) {
    return route(wxMessage, context, null);
  }

  /**
   * 处理不同appid微信消息
   *
   * @param appid     the appid
   * @param wxMessage the wx message
   * @param context   the context
   * @return the wx mp xml out message
   */
  public WxMpXmlOutMessage route(final String appid, final WxMpXmlMessage wxMessage,
                                 final Map<String, Object> context) {
    return route(wxMessage, context, this.wxMpService.switchoverTo(appid));
  }

  /**
   * 处理微信消息.
   *
   * @param wxMessage   the wx message
   * @param context     the context
   * @param wxMpService the wx mp service
   * @return the wx mp xml out message
   */
  public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage, final Map<String, Object> context,
                                 WxMpService wxMpService) {
    if (wxMpService == null) {
      wxMpService = this.wxMpService;
    }

    final WxMpService mpService = wxMpService;
    if (isMsgDuplicated(wxMessage)) {
      // 如果是重复消息，那么就不做处理
      return null;
    }

    final List<WxMpMessageRouterRule> matchRules = new ArrayList<>();
    // 收集匹配的规则
    for (final WxMpMessageRouterRule rule : this.rules) {
      if (rule.test(wxMessage)) {
        matchRules.add(rule);
        if (!rule.isReEnter()) {
          break;
        }
      }
    }

    if (matchRules.isEmpty()) {
      return null;
    }

    WxMpXmlOutMessage res = null;
    final List<Future<?>> futures = new ArrayList<>();

    for (final WxMpMessageRouterRule rule : matchRules) {
      // 返回最后一个非异步的rule的执行结果
      if (rule.isAsync()) {
        //获取当前线程使用的实际appId。兼容只有一个appId，且未显式设置当前使用的appId的情况
        String appId = mpService.getWxMpConfigStorage().getAppId();
        futures.add(
          this.executorService.submit(() -> {
            //传入父线程的appId
            mpService.switchoverTo(appId);
            rule.service(wxMessage, context, mpService, WxMpMessageRouter.this.sessionManager,
              WxMpMessageRouter.this.exceptionHandler);
            WxMpConfigStorageHolder.remove();
          })
        );
      } else {
        res = rule.service(wxMessage, context, mpService, this.sessionManager, this.exceptionHandler);
        // 在同步操作结束，session访问结束
        log.debug("End session access: async=false, sessionId={}", wxMessage.getFromUser());
        sessionEndAccess(wxMessage);
      }
    }

    if (futures.isEmpty()) {
      return res;
    }

    this.executorService.submit(() -> {
      for (Future<?> future : futures) {
        try {
          future.get();
          log.debug("End session access: async=true, sessionId={}", wxMessage.getFromUser());
          // 异步操作结束，session访问结束
          sessionEndAccess(wxMessage);
        } catch (InterruptedException e) {
          log.error("Error happened when wait task finish", e);
          Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
          log.error("Error happened when wait task finish", e);
        }
      }
    });
    return res;
  }

  /**
   * Route wx mp xml out message.
   *
   * @param wxMessage the wx message
   * @return the wx mp xml out message
   */
  public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage) {
    return this.route(wxMessage, new HashMap<>(2));
  }

  /**
   * Route wx mp xml out message.
   *
   * @param appid     the appid
   * @param wxMessage the wx message
   * @return the wx mp xml out message
   */
  public WxMpXmlOutMessage route(String appid, final WxMpXmlMessage wxMessage) {
    return this.route(appid, wxMessage, new HashMap<>(2));
  }

  protected boolean isMsgDuplicated(WxMpXmlMessage wxMessage) {
    StringBuilder messageId = new StringBuilder();
    if (wxMessage.getMsgId() == null) {
      messageId.append(wxMessage.getCreateTime())
        .append("-").append(wxMessage.getFromUser())
        .append("-").append(StringUtils.trimToEmpty(wxMessage.getEventKey()))
        .append("-").append(StringUtils.trimToEmpty(wxMessage.getEvent()));
    } else {
      messageId.append(wxMessage.getMsgId())
        .append("-").append(wxMessage.getCreateTime())
        .append("-").append(wxMessage.getFromUser());
    }

    if (StringUtils.isNotEmpty(wxMessage.getUserCardCode())) {
      messageId.append("-").append(wxMessage.getUserCardCode());
    }

    return this.messageDuplicateChecker.isDuplicate(messageId.toString());

  }

  /**
   * 对session的访问结束.
   */
  private void sessionEndAccess(WxMpXmlMessage wxMessage) {
    InternalSession session = ((InternalSessionManager) this.sessionManager).findSession(wxMessage.getFromUser());
    if (session != null) {
      session.endAccess();
    }
  }
}
