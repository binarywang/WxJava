package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendResult;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpMessageService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Message.MESSAGE_RECALL;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Message.MESSAGE_SEND;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

/**
 * 企业微信第三方应用消息推送服务测试.
 *
 * @author GitHub Copilot
 */
public class WxCpTpMessageServiceImplTest {

  @Mock
  private WxCpTpServiceApacheHttpClientImpl wxCpTpService;

  @Mock
  private WxCpTpConfigStorage configStorage;

  private WxCpTpMessageService wxCpTpMessageService;

  private AutoCloseable mockitoAnnotations;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    mockitoAnnotations = MockitoAnnotations.openMocks(this);
    when(wxCpTpService.getWxCpTpConfigStorage()).thenReturn(configStorage);
    WxCpTpDefaultConfigImpl defaultConfig = new WxCpTpDefaultConfigImpl();
    when(configStorage.getApiUrl(anyString()))
      .thenAnswer(invocation -> defaultConfig.getApiUrl(invocation.getArgument(0)));
    wxCpTpMessageService = new WxCpTpMessageServiceImpl(wxCpTpService);
  }

  /**
   * Tear down.
   *
   * @throws Exception the exception
   */
  @AfterClass
  public void tearDown() throws Exception {
    mockitoAnnotations.close();
  }

  /**
   * 测试 send 方法：验证使用了 corpId 对应的 access_token，并以 withoutSuiteAccessToken=true 发起请求.
   *
   * @throws WxErrorException 微信错误异常
   */
  @Test
  public void testSendMessage() throws WxErrorException {
    String corpId = "test_corp_id";
    String accessToken = "test_access_token";
    String mockResponse = "{\"errcode\":0,\"errmsg\":\"ok\",\"msgid\":\"msg_001\"}";

    when(configStorage.getAccessToken(corpId)).thenReturn(accessToken);
    String expectedUrl = new WxCpTpDefaultConfigImpl().getApiUrl(MESSAGE_SEND)
      + "?access_token=" + accessToken;
    when(wxCpTpService.post(eq(expectedUrl), anyString(), eq(true))).thenReturn(mockResponse);

    WxCpMessage message = WxCpMessage.TEXT().toUser("zhangsan").content("hello").agentId(1).build();
    WxCpMessageSendResult result = wxCpTpMessageService.send(message, corpId);
    assertNotNull(result);

    // 验证调用时传入了 withoutSuiteAccessToken=true，确保不会附加 suite_access_token
    verify(wxCpTpService).post(eq(expectedUrl), anyString(), eq(true));
  }

  /**
   * 测试 recall 方法：验证使用了 corpId 对应的 access_token，并以 withoutSuiteAccessToken=true 发起请求.
   *
   * @throws WxErrorException 微信错误异常
   */
  @Test
  public void testRecallMessage() throws WxErrorException {
    String corpId = "test_corp_id";
    String accessToken = "test_access_token";
    String msgId = "test_msg_id";

    when(configStorage.getAccessToken(corpId)).thenReturn(accessToken);
    String expectedUrl = new WxCpTpDefaultConfigImpl().getApiUrl(MESSAGE_RECALL)
      + "?access_token=" + accessToken;
    when(wxCpTpService.post(eq(expectedUrl), contains(msgId), eq(true))).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    wxCpTpMessageService.recall(msgId, corpId);

    // 验证调用时传入了 withoutSuiteAccessToken=true，确保不会附加 suite_access_token
    verify(wxCpTpService).post(eq(expectedUrl), contains(msgId), eq(true));
  }

  /**
   * 测试 getWxCpTpMessageService 方法：验证 BaseWxCpTpServiceImpl 中正确初始化了消息服务.
   */
  @Test
  public void testGetWxCpTpMessageServiceFromBase() {
    WxCpTpServiceApacheHttpClientImpl tpService = new WxCpTpServiceApacheHttpClientImpl();
    assertNotNull(tpService.getWxCpTpMessageService());
  }
}
