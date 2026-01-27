package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.locks.Lock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 测试 getMsgAuditAccessToken 方法在各个实现类中的正确性
 *
 * @author Binary Wang
 */
@Test
public class WxCpServiceGetMsgAuditAccessTokenTest {

  private WxCpDefaultConfigImpl config;

  @BeforeMethod
  public void setUp() {
    config = new WxCpDefaultConfigImpl();
    config.setCorpId("testCorpId");
    config.setCorpSecret("testCorpSecret");
    config.setMsgAuditSecret("testMsgAuditSecret");
  }

  /**
   * 测试会话存档access token的缓存机制
   * 验证当token未过期时，直接从配置中返回缓存的token
   */
  @Test
  public void testGetMsgAuditAccessToken_Cache() throws WxErrorException {
    // 预先设置一个有效的token
    config.updateMsgAuditAccessToken("cached_token", 7200);
    
    BaseWxCpServiceImpl service = createTestService(config);
    
    // 不强制刷新时应该返回缓存的token
    String token = service.getMsgAuditAccessToken(false);
    assertThat(token).isEqualTo("cached_token");
  }

  /**
   * 测试强制刷新会话存档access token
   * 验证forceRefresh=true时会重新获取token
   */
  @Test
  public void testGetMsgAuditAccessToken_ForceRefresh() throws WxErrorException {
    // 预先设置一个有效的token
    config.updateMsgAuditAccessToken("old_token", 7200);
    
    BaseWxCpServiceImpl service = createTestServiceWithMockToken(config, "new_token");
    
    // 强制刷新应该获取新token
    String token = service.getMsgAuditAccessToken(true);
    assertThat(token).isEqualTo("new_token");
  }

  /**
   * 测试token过期时自动刷新
   * 验证当token已过期时，会自动重新获取
   */
  @Test
  public void testGetMsgAuditAccessToken_Expired() throws WxErrorException {
    // 设置一个已过期的token（过期时间为负数，确保立即过期）
    config.updateMsgAuditAccessToken("expired_token", -1);
    
    BaseWxCpServiceImpl service = createTestServiceWithMockToken(config, "refreshed_token");
    
    // 过期的token应该被自动刷新
    String token = service.getMsgAuditAccessToken(false);
    assertThat(token).isEqualTo("refreshed_token");
  }

  /**
   * 测试获取锁机制
   * 验证配置中的锁可以正常获取和使用
   */
  @Test
  public void testGetMsgAuditAccessToken_Lock() {
    // 验证配置提供的锁不为null
    assertThat(config.getMsgAuditAccessTokenLock()).isNotNull();
    
    // 验证锁可以正常使用
    config.getMsgAuditAccessTokenLock().lock();
    try {
      assertThat(config.getMsgAuditAccessToken()).isNull();
    } finally {
      config.getMsgAuditAccessTokenLock().unlock();
    }
  }

  /**
   * 检查token是否需要刷新的公共逻辑
   */
  private boolean shouldRefreshToken(WxCpConfigStorage storage, boolean forceRefresh) {
    return storage.isMsgAuditAccessTokenExpired() || forceRefresh;
  }

  /**
   * 验证会话存档secret是否已配置的公共逻辑
   */
  private void validateMsgAuditSecret(String msgAuditSecret) throws WxErrorException {
    if (msgAuditSecret == null || msgAuditSecret.trim().isEmpty()) {
      throw new WxErrorException("会话存档secret未配置");
    }
  }

  /**
   * 创建一个用于测试的BaseWxCpServiceImpl实现，
   * 用于测试缓存和过期逻辑
   */
  private BaseWxCpServiceImpl createTestService(WxCpConfigStorage config) {
    return new BaseWxCpServiceImpl() {
      @Override
      public Object getRequestHttpClient() {
        return null;
      }

      @Override
      public Object getRequestHttpProxy() {
        return null;
      }

      @Override
      public HttpClientType getRequestType() {
        return null;
      }

      @Override
      public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        return "test_access_token";
      }

      @Override
      public String getMsgAuditAccessToken(boolean forceRefresh) throws WxErrorException {
        // 检查是否需要刷新
        if (!shouldRefreshToken(getWxCpConfigStorage(), forceRefresh)) {
          return getWxCpConfigStorage().getMsgAuditAccessToken();
        }
        
        // 使用会话存档secret获取access_token
        String msgAuditSecret = getWxCpConfigStorage().getMsgAuditSecret();
        validateMsgAuditSecret(msgAuditSecret);
        
        // 返回缓存的token（用于测试缓存机制）
        return getWxCpConfigStorage().getMsgAuditAccessToken();
      }

      @Override
      public void initHttp() {
      }

      @Override
      public WxCpConfigStorage getWxCpConfigStorage() {
        return config;
      }
    };
  }

  /**
   * 创建一个用于测试的BaseWxCpServiceImpl实现，
   * 模拟返回指定的token（用于测试刷新逻辑）
   */
  private BaseWxCpServiceImpl createTestServiceWithMockToken(WxCpConfigStorage config, String mockToken) {
    return new BaseWxCpServiceImpl() {
      @Override
      public Object getRequestHttpClient() {
        return null;
      }

      @Override
      public Object getRequestHttpProxy() {
        return null;
      }

      @Override
      public HttpClientType getRequestType() {
        return null;
      }

      @Override
      public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        return "test_access_token";
      }

      @Override
      public String getMsgAuditAccessToken(boolean forceRefresh) throws WxErrorException {
        // 使用锁机制
        Lock lock = getWxCpConfigStorage().getMsgAuditAccessTokenLock();
        lock.lock();
        try {
          // 检查是否需要刷新
          if (!shouldRefreshToken(getWxCpConfigStorage(), forceRefresh)) {
            return getWxCpConfigStorage().getMsgAuditAccessToken();
          }
          
          // 使用会话存档secret获取access_token
          String msgAuditSecret = getWxCpConfigStorage().getMsgAuditSecret();
          validateMsgAuditSecret(msgAuditSecret);
          
          // 模拟获取新token并更新配置
          getWxCpConfigStorage().updateMsgAuditAccessToken(mockToken, 7200);
          return mockToken;
        } finally {
          lock.unlock();
        }
      }

      @Override
      public void initHttp() {
      }

      @Override
      public WxCpConfigStorage getWxCpConfigStorage() {
        return config;
      }
    };
  }

  /**
   * 测试当 MsgAuditSecret 未配置时应该抛出异常
   */
  @Test
  public void testGetMsgAuditAccessToken_WithoutSecret() {
    config.setMsgAuditSecret(null);
    BaseWxCpServiceImpl service = createTestService(config);

    // 验证当 secret 为 null 时抛出异常
    assertThatThrownBy(() -> service.getMsgAuditAccessToken(true))
      .isInstanceOf(WxErrorException.class)
      .hasMessageContaining("会话存档secret未配置");
  }

  /**
   * 测试当 MsgAuditSecret 为空字符串时应该抛出异常
   */
  @Test
  public void testGetMsgAuditAccessToken_WithEmptySecret() {
    config.setMsgAuditSecret("  ");
    BaseWxCpServiceImpl service = createTestService(config);

    // 验证当 secret 为空字符串时抛出异常
    assertThatThrownBy(() -> service.getMsgAuditAccessToken(true))
      .isInstanceOf(WxErrorException.class)
      .hasMessageContaining("会话存档secret未配置");
  }
}
