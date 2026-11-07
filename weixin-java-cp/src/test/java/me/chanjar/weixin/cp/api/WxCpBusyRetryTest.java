package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

/**
 * 企业微信系统繁忙重试测试。
 */
public class WxCpBusyRetryTest {
  @DataProvider(name = "getService")
  public Object[][] getService() {
    BusyService service = new BusyService();
    service.setMaxRetryTimes(3);
    service.setRetrySleepMillis(1);
    return new Object[][]{{service}};
  }

  /**
   * @param service 始终返回系统繁忙错误的服务
   */
  @Test(dataProvider = "getService")
  public void testRetry(WxCpService service) {
    assertRetryExhausted(service);
    assertEquals(((BusyService) service).calls.get(), 4);
  }

  /**
   * @param service 始终返回系统繁忙错误的服务
   * @throws InterruptedException 等待任务被中断
   * @throws ExecutionException 后台测试失败
   */
  @Test(dataProvider = "getService")
  public void testRetryInThreadPool(WxCpService service) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    try {
      Future<?> first = executorService.submit(() -> assertRetryExhausted(service));
      Future<?> second = executorService.submit(() -> assertRetryExhausted(service));
      first.get();
      second.get();
      assertEquals(((BusyService) service).calls.get(), 8);
    } finally {
      executorService.shutdownNow();
    }
  }

  private void assertRetryExhausted(WxCpService service) {
    WxRuntimeException failure = expectThrows(WxRuntimeException.class,
      () -> service.execute(null, (String) null, null));
    assertTrue(failure.getMessage().contains("超出重试次数"));
  }

  private static class BusyService extends WxCpServiceImpl {
    private final AtomicInteger calls = new AtomicInteger();

    @Override
    public synchronized <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data,
                                                boolean doNotAutoRefresh) throws WxErrorException {
      calls.incrementAndGet();
      throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("system busy").build());
    }
  }
}
