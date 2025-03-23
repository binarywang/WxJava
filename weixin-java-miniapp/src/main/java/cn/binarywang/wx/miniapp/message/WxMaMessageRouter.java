package cn.binarywang.wx.miniapp.message;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateCheckerSingleton;
import me.chanjar.weixin.common.session.InternalSession;
import me.chanjar.weixin.common.session.InternalSessionManager;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.LogExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxMaMessageRouter {
  private static final int DEFAULT_THREAD_POOL_SIZE = 100;
  private final Logger log = LoggerFactory.getLogger(WxMaMessageRouter.class);
  private final List<WxMaMessageRouterRule> rules = new ArrayList<>();

  private final WxMaService wxMaService;

  private ExecutorService executorService;

  private WxSessionManager sessionManager;

  private WxErrorExceptionHandler exceptionHandler;

  private WxMessageDuplicateChecker messageDuplicateChecker;

  public WxMaMessageRouter(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("WxMaMessageRouter-pool-%d").build();
    this.executorService = new ThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE, DEFAULT_THREAD_POOL_SIZE,
      0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    this.sessionManager = new StandardSessionManager();
    this.exceptionHandler = new LogExceptionHandler();
    this.messageDuplicateChecker = WxMessageInMemoryDuplicateCheckerSingleton.getInstance();
  }

  /**
   * 使用自定义的 {@link ExecutorService}.
   */
  public WxMaMessageRouter(WxMaService wxMaService, ExecutorService executorService) {
    this.wxMaService = wxMaService;
    this.executorService = executorService;
    this.sessionManager = new StandardSessionManager();
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
        if (!this.executorService.awaitTermination(second, TimeUnit.SECONDS))
          log.error("线程池未关闭！");
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
   * 开始一个新的Route规则.
   */
  public WxMaMessageRouterRule rule() {
    return new WxMaMessageRouterRule(this);
  }

  /**
   * 处理微信消息.
   */
  public WxMaXmlOutMessage route(final WxMaMessage wxMessage, final Map<String, Object> context) {
    if (isMsgDuplicated(wxMessage)) {
      // 如果是重复消息，那么就不做处理
      return null;
    }

    final List<WxMaMessageRouterRule> matchRules = new ArrayList<>();
    // 收集匹配的规则
    for (final WxMaMessageRouterRule rule : this.rules) {
      if (rule.test(wxMessage)) {
        matchRules.add(rule);
        if (!rule.isReEnter()) {
          break;
        }
      }
    }

    if (matchRules.size() == 0) {
      return null;
    }

    final List<Future<?>> futures = new ArrayList<>();
    WxMaXmlOutMessage result = null;
    for (final WxMaMessageRouterRule rule : matchRules) {
      // 返回最后一个非异步的rule的执行结果
      if (rule.isAsync()) {
        //获取当前线程使用的实际appId，兼容只有一个appId，且未显式设置当前使用的appId的情况
        String miniAppId = this.wxMaService.getWxMaConfig().getAppid();
        futures.add(
          this.executorService.submit(() -> {
            //子线程中设置实际的appId
            this.wxMaService.switchoverTo(miniAppId);
            rule.service(wxMessage, context, WxMaMessageRouter.this.wxMaService, WxMaMessageRouter.this.sessionManager, WxMaMessageRouter.this.exceptionHandler);
            WxMaConfigHolder.remove();
          })
        );
      } else {
        result = rule.service(wxMessage, context, this.wxMaService, this.sessionManager, this.exceptionHandler);
        // 在同步操作结束，session访问结束
        this.log.debug("End session access: async=false, sessionId={}", wxMessage.getFromUser());
        sessionEndAccess(wxMessage);
      }
    }

    if (futures.size() > 0) {
      this.executorService.submit(() -> {
        for (Future<?> future : futures) {
          try {
            future.get();
            WxMaMessageRouter.this.log.debug("End session access: async=true, sessionId={}", wxMessage.getFromUser());
            // 异步操作结束，session访问结束
            sessionEndAccess(wxMessage);
          } catch (InterruptedException | ExecutionException e) {
            WxMaMessageRouter.this.log.error("Error happened when wait task finish", e);
          }
        }
      });
    }
    return result;
  }

  public WxMaXmlOutMessage route(final WxMaMessage wxMessage) {
    return this.route(wxMessage, new HashMap<>(2));
  }

  protected boolean isMsgDuplicated(WxMaMessage wxMessage) {
    StringBuilder messageId = new StringBuilder();
    if (wxMessage.getMsgId() == null) {
      messageId.append(wxMessage.getCreateTime())
        .append("-").append(wxMessage.getFromUser())
        .append("-").append(StringUtils.trimToEmpty(wxMessage.getEvent()));
    } else {
      messageId.append(wxMessage.getMsgId())
        .append("-").append(wxMessage.getCreateTime())
        .append("-").append(wxMessage.getFromUser());
    }

    if (StringUtils.isNotEmpty(wxMessage.getToUser())) {
      messageId.append("-").append(wxMessage.getToUser());
    }

    if (StringUtils.isNotEmpty(wxMessage.getTraceId())) {
      messageId.append("-").append(wxMessage.getTraceId());
    }

    return this.messageDuplicateChecker.isDuplicate(messageId.toString());
  }

  /**
   * 对session的访问结束.
   */
  private void sessionEndAccess(WxMaMessage wxMessage) {
    InternalSession session = ((InternalSessionManager) this.sessionManager).findSession(wxMessage.getFromUser());
    if (session != null) {
      session.endAccess();
    }

  }
}
