package com.binarywang.wxjava.store.message;

import java.util.concurrent.ThreadPoolExecutor;
import com.binarywang.wxjava.store.executor.StoreMediaDownloadRequestExecutor;
import java.io.IOException;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.ResponseHandler;
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

  @Test
  public void shouldExtractOnlyTheFilenameFromContentDisposition() {
    TestStoreMediaDownloadRequestExecutor executor = new TestStoreMediaDownloadRequestExecutor();

    Assert.assertEquals(executor.extract("attachment; filename=\"image.jpg\"; size=123"), "image.jpg");
  }

  private static class TestStoreMediaDownloadRequestExecutor extends StoreMediaDownloadRequestExecutor<Object, Object> {
    private TestStoreMediaDownloadRequestExecutor() {
      super(null, null);
    }

    @Override
    public com.binarywang.wxjava.store.bean.image.StoreImageResponse execute(String uri, String data, WxType wxType)
      throws WxErrorException, IOException {
      return null;
    }

    @Override
    public void execute(String uri, String data, ResponseHandler<com.binarywang.wxjava.store.bean.image.StoreImageResponse> handler,
      WxType wxType) throws WxErrorException, IOException {
    }

    private String extract(String contentDisposition) {
      return extractFileNameFromContentString(contentDisposition);
    }
  }
}
