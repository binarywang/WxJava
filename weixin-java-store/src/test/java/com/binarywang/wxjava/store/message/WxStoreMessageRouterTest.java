package com.binarywang.wxjava.store.message;

import java.util.concurrent.ThreadPoolExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Tests the default router backpressure policy. */
public class WxStoreMessageRouterTest {

  @Test
  public void shouldUseBoundedQueueAndCallerRunsPolicy() {
    WxStoreMessageRouter router = new WxStoreMessageRouter();
    ThreadPoolExecutor executor = (ThreadPoolExecutor) router.getExecutorService();

    Assert.assertTrue(executor.getQueue().remainingCapacity() > 0);
    Assert.assertTrue(executor.getRejectedExecutionHandler() instanceof ThreadPoolExecutor.CallerRunsPolicy);
    router.shutDownExecutorService();
  }
}
