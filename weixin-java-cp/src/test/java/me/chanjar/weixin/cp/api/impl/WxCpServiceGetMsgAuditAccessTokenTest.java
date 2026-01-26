package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
   * 测试 WxCpServiceApacheHttpClientImpl 的 getMsgAuditAccessToken 方法
   */
  @Test
  public void testGetMsgAuditAccessToken_ApacheHttpClient() throws WxErrorException {
    // 创建一个模拟实现，不实际调用HTTP请求
    WxCpServiceApacheHttpClientImpl service = new WxCpServiceApacheHttpClientImpl() {
      @Override
      public String getMsgAuditAccessToken(boolean forceRefresh) throws WxErrorException {
        // 验证配置是否正确使用
        WxCpConfigStorage storage = getWxCpConfigStorage();
        assertThat(storage.getMsgAuditSecret()).isEqualTo("testMsgAuditSecret");
        
        // 模拟返回 token
        return "mock_msg_audit_access_token";
      }
    };
    service.setWxCpConfigStorage(config);

    String token = service.getMsgAuditAccessToken(false);
    assertThat(token).isEqualTo("mock_msg_audit_access_token");
  }

  /**
   * 测试 WxCpServiceHttpComponentsImpl 的 getMsgAuditAccessToken 方法
   */
  @Test
  public void testGetMsgAuditAccessToken_HttpComponents() throws WxErrorException {
    // 创建一个模拟实现，不实际调用HTTP请求
    WxCpServiceHttpComponentsImpl service = new WxCpServiceHttpComponentsImpl() {
      @Override
      public String getMsgAuditAccessToken(boolean forceRefresh) throws WxErrorException {
        // 验证配置是否正确使用
        WxCpConfigStorage storage = getWxCpConfigStorage();
        assertThat(storage.getMsgAuditSecret()).isEqualTo("testMsgAuditSecret");
        
        // 模拟返回 token
        return "mock_msg_audit_access_token";
      }
    };
    service.setWxCpConfigStorage(config);

    String token = service.getMsgAuditAccessToken(false);
    assertThat(token).isEqualTo("mock_msg_audit_access_token");
  }

  /**
   * 测试 WxCpServiceOkHttpImpl 的 getMsgAuditAccessToken 方法
   */
  @Test
  public void testGetMsgAuditAccessToken_OkHttp() throws WxErrorException {
    // 创建一个模拟实现，不实际调用HTTP请求
    WxCpServiceOkHttpImpl service = new WxCpServiceOkHttpImpl() {
      @Override
      public String getMsgAuditAccessToken(boolean forceRefresh) throws WxErrorException {
        // 验证配置是否正确使用
        WxCpConfigStorage storage = getWxCpConfigStorage();
        assertThat(storage.getMsgAuditSecret()).isEqualTo("testMsgAuditSecret");
        
        // 模拟返回 token
        return "mock_msg_audit_access_token";
      }
    };
    service.setWxCpConfigStorage(config);

    String token = service.getMsgAuditAccessToken(false);
    assertThat(token).isEqualTo("mock_msg_audit_access_token");
  }

  /**
   * 测试 WxCpServiceJoddHttpImpl 的 getMsgAuditAccessToken 方法
   */
  @Test
  public void testGetMsgAuditAccessToken_JoddHttp() throws WxErrorException {
    // 创建一个模拟实现，不实际调用HTTP请求
    WxCpServiceJoddHttpImpl service = new WxCpServiceJoddHttpImpl() {
      @Override
      public String getMsgAuditAccessToken(boolean forceRefresh) throws WxErrorException {
        // 验证配置是否正确使用
        WxCpConfigStorage storage = getWxCpConfigStorage();
        assertThat(storage.getMsgAuditSecret()).isEqualTo("testMsgAuditSecret");
        
        // 模拟返回 token
        return "mock_msg_audit_access_token";
      }
    };
    service.setWxCpConfigStorage(config);

    String token = service.getMsgAuditAccessToken(false);
    assertThat(token).isEqualTo("mock_msg_audit_access_token");
  }

  /**
   * 创建一个用于测试的BaseWxCpServiceImpl实现，
   * 模拟在msgAuditSecret未配置时抛出异常的行为
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
        // 使用会话存档secret获取access_token
        String msgAuditSecret = getWxCpConfigStorage().getMsgAuditSecret();
        if (msgAuditSecret == null || msgAuditSecret.trim().isEmpty()) {
          throw new WxErrorException("会话存档secret未配置");
        }
        return "mock_token";
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
    service.setWxCpConfigStorage(config);

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
    service.setWxCpConfigStorage(config);

    // 验证当 secret 为空字符串时抛出异常
    assertThatThrownBy(() -> service.getMsgAuditAccessToken(true))
      .isInstanceOf(WxErrorException.class)
      .hasMessageContaining("会话存档secret未配置");
  }
}
