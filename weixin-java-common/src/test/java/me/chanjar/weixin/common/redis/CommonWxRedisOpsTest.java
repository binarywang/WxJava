package me.chanjar.weixin.common.redis;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CommonWxRedisOpsTest {

  protected WxRedisOps wxRedisOps;
  private String key = "access_token";
  private String value = String.valueOf(System.currentTimeMillis());

  @Test
  public void testGetValue() {
    wxRedisOps.setValue(key, value, 3, TimeUnit.SECONDS);
    Assert.assertEquals(wxRedisOps.getValue(key), value);
  }

  @Test
  public void testSetValue() {
    String key = "access_token", value = String.valueOf(System.currentTimeMillis());
    wxRedisOps.setValue(key, value, -1, TimeUnit.SECONDS);
    wxRedisOps.setValue(key, value, 0, TimeUnit.SECONDS);
    wxRedisOps.setValue(key, value, 1, TimeUnit.SECONDS);
  }

  @Test
  public void testGetExpire() {
    String key = "access_token", value = String.valueOf(System.currentTimeMillis());
    wxRedisOps.setValue(key, value, -1, TimeUnit.SECONDS);
    Assert.assertTrue(wxRedisOps.getExpire(key) < 0);
    wxRedisOps.setValue(key, value, 4, TimeUnit.SECONDS);
    Long expireSeconds = wxRedisOps.getExpire(key);
    Assert.assertTrue(expireSeconds <= 4 && expireSeconds >= 0);
  }

  @Test
  public void testGetExpireForNonExistentKey() {
    String nonExistentKey = "non_existent_key_" + System.currentTimeMillis();
    Long expire = wxRedisOps.getExpire(nonExistentKey);
    // For non-existent keys, getExpire should return a negative value
    // In Spring Data Redis 2.x: may return null (but our implementation should handle this)
    // In Spring Data Redis 3.x: returns -2
    Assert.assertTrue(expire == null || expire < 0, "Non-existent key should have null or negative expiration");
  }

  @Test
  public void testExpire() {
    String key = "access_token", value = String.valueOf(System.currentTimeMillis());
    wxRedisOps.setValue(key, value, -1, TimeUnit.SECONDS);
    wxRedisOps.expire(key, 4, TimeUnit.SECONDS);
    Long expireSeconds = wxRedisOps.getExpire(key);
    Assert.assertTrue(expireSeconds <= 4 && expireSeconds >= 0);
  }

  @Test
  public void testGetLock() {
    Assert.assertNotNull(wxRedisOps.getLock("access_token_lock"));
  }
}
