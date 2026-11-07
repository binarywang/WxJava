package me.chanjar.weixin.qidian.api;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.qidian.api.impl.WxQidianServiceHttpClientImpl;
import org.testng.annotations.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Test
@Slf4j
public class WxMpBusyRetryTest {

  @DataProvider(name = "getService")
  public Object[][] getService() {
    WxQidianService service = new WxQidianServiceHttpClientImpl() {

      @Override
      public synchronized <T, E> T executeInternal(
        RequestExecutor<T, E> executor, String uri, E data)
        throws WxErrorException {
        log.info("Executed");
        throw new WxErrorException(me.chanjar.weixin.common.error.WxError.builder()
          .errorCode(-1).errorMsg("system busy").build());
      }
    };

    service.setMaxRetryTimes(3);
    service.setRetrySleepMillis(1);
    return new Object[][]{{service}};
  }

  @Test(dataProvider = "getService", expectedExceptions = RuntimeException.class)
  public void testRetry(WxQidianService service) throws WxErrorException {
    service.execute(null, (String)null, null);
  }

  @Test(dataProvider = "getService")
  public void testRetryInThreadPool(final WxQidianService service) throws InterruptedException, ExecutionException {
    // 当线程池中的线程复用的时候，还是能保证相同的重试次数
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Runnable runnable = () -> {
      try {
        System.out.println("=====================");
        System.out.println(Thread.currentThread().getName() + ": testRetry");
        service.execute(null, (String)null, null);
      } catch (WxErrorException e) {
        throw new WxRuntimeException(e);
      } catch (RuntimeException e) {
        org.testng.Assert.assertTrue(e.getMessage().contains("超出重试次数"));
      }
    };
    Future<?> submit1 = executorService.submit(runnable);
    Future<?> submit2 = executorService.submit(runnable);

    submit1.get();
    submit2.get();
  }

}
