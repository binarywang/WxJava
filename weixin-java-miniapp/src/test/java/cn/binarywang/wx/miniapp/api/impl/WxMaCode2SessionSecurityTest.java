package cn.binarywang.wx.miniapp.api.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.sun.net.httpserver.HttpServer;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class WxMaCode2SessionSecurityTest {
  private static final String SECRET = "FAKE_APP_SECRET";
  private static final String TOKEN = "FAKE_ACCESS_TOKEN";
  private static final String SESSION = "FAKE_SESSION_KEY";
  private static final String RESULT = "{\"openid\":\"FAKE_OPENID\",\"session_key\":\"" + SESSION + "\"}";

  @Test
  public void allHttpClientsSendEncodedValuesAndPreserveSession() throws Exception {
    AtomicReference<String> query = new AtomicReference<>();
    AtomicReference<String> response = new AtomicReference<>(RESULT);
    HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
    server.createContext("/sns/jscode2session", exchange -> {
      query.set(exchange.getRequestURI().getRawQuery());
      byte[] body = response.get().getBytes(StandardCharsets.UTF_8);
      exchange.sendResponseHeaders(200, body.length);
      try (java.io.OutputStream out = exchange.getResponseBody()) {
        out.write(body);
      }
    });
    server.createContext("/custom", exchange -> {
      query.set(exchange.getRequestURI().getRawQuery());
      String result = exchange.getRequestMethod();
      if ("POST".equals(result)) {
        java.io.ByteArrayOutputStream body = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[128];
        int count;
        while ((count = exchange.getRequestBody().read(buffer)) != -1) {
          body.write(buffer, 0, count);
        }
        result += ":" + new String(body.toByteArray(), StandardCharsets.UTF_8);
      }
      byte[] body = ("{\"result\":\"" + result + "\"}").getBytes(StandardCharsets.UTF_8);
      exchange.sendResponseHeaders(200, body.length);
      try (java.io.OutputStream out = exchange.getResponseBody()) {
        out.write(body);
      }
    });
    server.start();
    try (LogCapture logs = new LogCapture()) {
      BaseWxMaServiceImpl<?, ?>[] services = {new WxMaServiceImpl(), new WxMaServiceHttpComponentsImpl(),
        new WxMaServiceOkHttpImpl(), new WxMaServiceJoddHttpImpl()};
      for (BaseWxMaServiceImpl<?, ?> service : services) {
        WxMaDefaultConfigImpl config = config();
        config.setAppid("FAKE_APP_ID+&=中文");
        config.setSecret(SECRET + "+&=中文\uD83D\uDE00");
        config.setApiHostUrl("http://127.0.0.1:" + server.getAddress().getPort());
        service.setWxMaConfig(config);
        try {
          config.expireAccessToken();
          assertEquals(service.getWithoutToken("https://api.weixin.qq.com/custom", "value=a%2Fb"), "{\"result\":\"GET\"}");
          assertEquals(decode(query.get()).get("value"), "a/b");
          assertFalse(query.get().contains("access_token="));
          assertEquals(service.postWithoutToken("https://api.weixin.qq.com/custom?value=1", "payload"),
            "{\"result\":\"POST:payload\"}");
          assertEquals(query.get(), "value=1");
          response.set(RESULT);
          for (String code : new String[]{"NORMAL_CODE", "a b\n", "a+&=#?%2F", "中文\uD83D\uDE00", ""}) {
            assertEquals(service.jsCode2SessionInfo(code).getSessionKey(), SESSION);
            Map<String, String> params = decode(query.get());
            assertEquals(params.size(), 4);
            assertEquals(params.get("js_code"), code);
            assertEquals(params.get("secret"), config.getSecret());
            assertEquals(params.get("appid"), config.getAppid());
            assertNull(params.get("access_token"));
          }
          response.set("{\"errcode\":40029,\"errmsg\":\"" + SECRET + TOKEN + "\"}");
          try {
            service.jsCode2SessionInfo("FAKE_CODE");
            fail("Expected WeChat error");
          } catch (WxErrorException e) {
            assertEquals(e.getError().getErrorCode(), 40029);
            assertSafe(e);
          }
        } finally {
          Object client = service.getRequestHttpClient();
          if (client instanceof Closeable) {
            ((Closeable) client).close();
          } else if (client instanceof okhttp3.OkHttpClient) {
            ((okhttp3.OkHttpClient) client).connectionPool().evictAll();
            ((okhttp3.OkHttpClient) client).dispatcher().executorService().shutdown();
          }
        }
      }
      logs.assertSafe();
    } finally {
      server.stop(0);
    }
  }

  @Test
  public void errorsAndRetryLogsDoNotContainCredentials() throws Exception {
    try (LogCapture logs = new LogCapture()) {
      TestService service = service();
      service.getWxMaConfig().setApiHostUrl("http://proxy.invalid");
      when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp)))
        .thenThrow(error(-1)).thenReturn(RESULT);
      assertEquals(service.jsCode2SessionInfo("FAKE_CODE").getSessionKey(), SESSION);
      assertEquals(service.tokenRequests, 0);
      verify(service.executor, times(2)).execute(startsWith("http://proxy.invalid/"), anyString(), eq(WxType.MiniApp));

      for (Exception failure : new Exception[]{error(40029), new IOException(SECRET + TOKEN),
        new IllegalArgumentException(SECRET + TOKEN), new IllegalStateException(SECRET + TOKEN)}) {
        reset(service.executor);
        when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp))).thenThrow(failure);
        try {
          service.jsCode2SessionInfo("FAKE_CODE");
          fail("Expected failure");
        } catch (WxErrorException e) {
          assertEquals(e.getError().getErrorCode(), 40029);
          assertNull(e.getError().getJson());
          assertSafe(e);
        } catch (RuntimeException e) {
          assertSafe(e);
        }
      }
      logs.assertSafe();
    }
  }

  @Test
  public void loginDoesNotRequireTokenAndParsingFailuresRemainProtected() throws Exception {
    try (LogCapture logs = new LogCapture()) {
      TestService service = service();
      service.getWxMaConfig().expireAccessToken();
      when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp))).thenReturn(RESULT);
      assertEquals(service.jsCode2SessionInfo("FAKE_CODE").getSessionKey(), SESSION);
      assertEquals(service.tokenRequests, 0);
      when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp)))
        .thenReturn("{\"session_key\":\"" + SESSION + "\",\"expires_in\":");
      try {
        service.jsCode2SessionInfo("FAKE_CODE");
        fail("Expected malformed response failure");
      } catch (WxRuntimeException e) {
        assertSafe(e);
      }
      try {
        service.jsCode2SessionInfo(null);
        fail("Expected null input failure");
      } catch (NullPointerException e) {
        assertSafe(e);
      }
      service.getWxMaConfig().expireAccessToken();
      service.failToken = true;
      when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp))).thenReturn(RESULT);
      assertEquals(service.jsCode2SessionInfo("FAKE_CODE").getSessionKey(), SESSION);
      assertEquals(service.tokenRequests, 0);
      logs.assertSafe();
    }
  }

  @Test
  public void tokenFreeExecutionRetriesBusyButDoesNotRefreshInvalidToken() throws Exception {
    TestService service = service();
    service.getWxMaConfig().setApiHostUrl("http://proxy.invalid");
    when(service.executor.execute(anyString(), eq("payload"), eq(WxType.MiniApp)))
      .thenThrow(error(-1)).thenReturn("ok");
    assertEquals(service.executeWithoutToken(service.executor,
      "https://api.weixin.qq.com/custom", "payload"), "ok");
    verify(service.executor, times(2)).execute("http://proxy.invalid/custom", "payload", WxType.MiniApp);
    assertEquals(service.tokenRequests, 0);

    reset(service.executor);
    when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp))).thenThrow(error(40001));
    try {
      service.executeWithoutToken(service.executor, "https://api.weixin.qq.com/custom", "payload");
      fail("Expected invalid token error");
    } catch (WxErrorException e) {
      assertEquals(e.getError().getErrorCode(), 40001);
    }
    verify(service.executor).execute(anyString(), anyString(), eq(WxType.MiniApp));
    assertEquals(service.getWxMaConfig().getAccessToken(), TOKEN);
    assertFalse(service.getWxMaConfig().isAccessTokenExpired());
    assertEquals(service.tokenRequests, 0);
  }

  @Test
  public void tokenFreeExecutionStopsAtRetryLimit() throws Exception {
    TestService service = service();
    service.setMaxRetryTimes(2);
    when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp))).thenThrow(error(-1));
    try {
      service.executeWithoutToken(service.executor, "https://api.weixin.qq.com/custom", "payload");
      fail("Expected exhausted retries");
    } catch (WxErrorException e) {
      assertEquals(e.getError().getErrorCode(), -1);
    }
    verify(service.executor, times(3)).execute(anyString(), anyString(), eq(WxType.MiniApp));
    assertEquals(service.tokenRequests, 0);
  }

  @Test
  public void customServiceDefaultExecutionPreservesTransportContract() throws Exception {
    cn.binarywang.wx.miniapp.api.WxMaService service =
      mock(cn.binarywang.wx.miniapp.api.WxMaService.class, CALLS_REAL_METHODS);
    RequestExecutor<String, String> executor = mock(RequestExecutor.class);
    when(executor.execute("https://example.invalid/custom", "payload", WxType.MiniApp)).thenReturn("ok");
    assertEquals(service.executeWithoutToken(executor, "https://example.invalid/custom", "payload"), "ok");
    IOException failure = new IOException("transport failure");
    when(executor.execute(anyString(), anyString(), eq(WxType.MiniApp))).thenThrow(failure);
    try {
      service.executeWithoutToken(executor, "https://example.invalid/custom", "payload");
      fail("Expected transport failure");
    } catch (WxRuntimeException e) {
      assertSame(e.getCause(), failure);
    }
    verify(service, never()).getAccessToken(anyBoolean());
  }

  @Test
  public void ordinaryExecutionStillAddsAndRefreshesToken() throws Exception {
    TestService service = service();
    when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp)))
      .thenThrow(error(40001)).thenReturn("ok");
    assertEquals(service.get("https://api.weixin.qq.com/custom", "query=1"), "ok");
    verify(service.executor, times(2)).execute(
      "https://api.weixin.qq.com/custom?access_token=" + TOKEN, "query=1", WxType.MiniApp);
    assertEquals(service.tokenRequests, 1);
  }

  @Test
  public void ordinaryGetKeepsSerializedQueryAndExceptionContract() throws Exception {
    TestService service = service();
    IllegalArgumentException original = new IllegalArgumentException("ordinary failure");
    when(service.executor.execute(anyString(), eq("value=a%2Fb"), eq(WxType.MiniApp))).thenThrow(original);
    try {
      service.get("https://api.weixin.qq.com/ordinary", "value=a%2Fb");
      fail("Expected failure");
    } catch (IllegalArgumentException e) {
      assertSame(e, original);
    }
  }

  @Test
  public void accountSwitchKeepsCredentialsWithTheirAccount() throws Exception {
    TestService service = service();
    WxMaDefaultConfigImpl first = config();
    WxMaDefaultConfigImpl second = config();
    second.setAppid("SECOND_APP_ID");
    second.setSecret("SECOND_SECRET");
    second.updateAccessToken("SECOND_TOKEN", 7200);
    Map<String, cn.binarywang.wx.miniapp.config.WxMaConfig> configs = new HashMap<>();
    configs.put(first.getAppid(), first);
    configs.put(second.getAppid(), second);
    service.setMultiConfigs(configs, first.getAppid());
    when(service.executor.execute(anyString(), anyString(), eq(WxType.MiniApp))).thenAnswer(invocation -> {
      Map<String, String> params = decode(invocation.getArgument(1));
      String uri = invocation.getArgument(0);
      if ("SECOND_APP_ID".equals(params.get("appid"))) {
        assertEquals(params.get("secret"), "SECOND_SECRET");
        assertFalse(uri.contains("access_token="));
      } else {
        assertEquals(params.get("secret"), SECRET);
        assertFalse(uri.contains("access_token="));
      }
      return RESULT;
    });
    assertEquals(service.jsCode2SessionInfo("FAKE_CODE").getSessionKey(), SESSION);
    assertTrue(service.switchover(second.getAppid()));
    assertEquals(service.jsCode2SessionInfo("FAKE_CODE").getSessionKey(), SESSION);
    assertTrue(service.switchover(first.getAppid()));
    assertEquals(service.jsCode2SessionInfo("FAKE_CODE").getSessionKey(), SESSION);
  }

  private static WxMaDefaultConfigImpl config() {
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid("FAKE_APP_ID");
    config.setSecret(SECRET);
    config.updateAccessToken(TOKEN, 7200);
    return config;
  }

  private TestService service() {
    TestService service = new TestService();
    service.setWxMaConfig(config());
    service.setRetrySleepMillis(0);
    return service;
  }

  private WxErrorException error(int code) {
    return new WxErrorException(WxError.builder().errorCode(code).errorMsg(SECRET + TOKEN)
      .errorMsgEn(SECRET).json(SESSION).build(), new RuntimeException(SECRET));
  }

  private static Map<String, String> decode(String query) throws Exception {
    Map<String, String> result = new HashMap<>();
    for (String pair : query.split("&")) {
      String[] parts = pair.split("=", 2);
      result.put(parts[0], URLDecoder.decode(parts[1], "UTF-8"));
    }
    return result;
  }

  private static void assertSafe(Throwable e) {
    StringWriter trace = new StringWriter();
    e.printStackTrace(new PrintWriter(trace));
    assertNoSecrets(trace.toString());
    assertNull(e.getCause());
    assertEquals(e.getSuppressed().length, 0);
  }

  private static void assertNoSecrets(String text) {
    for (String secret : new String[]{SECRET, TOKEN, SESSION, "FAKE_CODE", "FAKE_OPENID"}) {
      assertFalse(text.contains(secret), "Credentials must not appear in diagnostics");
    }
  }

  private static class LogCapture implements AutoCloseable {
    private final Logger logger = (Logger) LoggerFactory.getLogger(BaseWxMaServiceImpl.class);
    private final Level previous = logger.getLevel();
    private final ListAppender<ILoggingEvent> appender = new ListAppender<>();

    LogCapture() {
      logger.setLevel(Level.DEBUG);
      appender.start();
      logger.addAppender(appender);
    }

    void assertSafe() {
      assertFalse(appender.list.isEmpty());
      for (ILoggingEvent event : appender.list) {
        assertNoSecrets(event.getFormattedMessage());
      }
    }

    @Override
    public void close() {
      logger.detachAppender(appender);
      logger.setLevel(previous);
      appender.stop();
    }
  }

  private static class TestService extends WxMaServiceImpl {
    @SuppressWarnings("unchecked")
    private final RequestExecutor<String, String> executor = mock(RequestExecutor.class);
    private int tokenRequests;
    private boolean failToken;

    @Override
    public void initHttp() {
      // The inherited execution/retry pipeline is exercised with a controlled transport.
    }

    @Override
    public String get(String url, String query) throws WxErrorException {
      return (String) super.execute(executor, url, query);
    }

    @Override
    public String getWithoutToken(String url, String query) throws WxErrorException {
      return (String) super.executeWithoutToken(executor, url, query);
    }

    @Override
    protected String doGetAccessTokenRequest() throws IOException {
      tokenRequests++;
      if (failToken) {
        throw new IOException(SECRET + TOKEN);
      }
      return "{\"access_token\":\"" + TOKEN + "\",\"expires_in\":7200}";
    }
  }
}
