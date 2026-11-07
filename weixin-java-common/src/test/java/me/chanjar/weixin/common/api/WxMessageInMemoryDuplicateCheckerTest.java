package me.chanjar.weixin.common.api;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class WxMessageInMemoryDuplicateCheckerTest {
  public void test() throws InterruptedException {
    WxMessageInMemoryDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker(2000L, 1000L);
    Long[] msgIds = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};

    // 第一次检查
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      assertFalse(result);
    }

    // 未过期时应识别为重复消息
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      assertTrue(result);
    }

    // 过期记录由后台线程定期清理，不能假定 TTL 到达后立即被删除。
    long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10);
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      while (result && System.nanoTime() < deadline) {
        TimeUnit.MILLISECONDS.sleep(20L);
        result = checker.isDuplicate(String.valueOf(msgId));
      }
      assertFalse(result, "Expired message was not cleared: " + msgId);
      assertTrue(checker.isDuplicate(String.valueOf(msgId)));
    }

  }

}
