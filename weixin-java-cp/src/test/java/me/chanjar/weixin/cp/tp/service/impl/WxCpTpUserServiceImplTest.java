package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpUserService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.User.USER_SIMPLE_LIST;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

/**
 * 企业微信-第三方开发-用户管理相关测试.
 *
 * @author GitHub Copilot
 */
public class WxCpTpUserServiceImplTest {

  @Mock
  private WxCpTpServiceApacheHttpClientImpl wxCpTpService;

  @Mock
  private WxCpTpConfigStorage configStorage;

  private WxCpTpUserService wxCpTpUserService;

  private AutoCloseable mockitoAnnotations;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    mockitoAnnotations = MockitoAnnotations.openMocks(this);
    when(wxCpTpService.getWxCpTpConfigStorage()).thenReturn(configStorage);
    WxCpTpDefaultConfigImpl defaultConfig = new WxCpTpDefaultConfigImpl();
    when(configStorage.getApiUrl(contains(USER_SIMPLE_LIST)))
      .thenAnswer(invocation -> defaultConfig.getApiUrl(invocation.getArgument(0)));
    wxCpTpUserService = new WxCpTpUserServiceImpl(wxCpTpService);
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
   * 测试使用 corpId 的 listSimpleByDepartment 方法，验证请求使用了 access_token 而非 suite_access_token.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListSimpleByDepartmentWithCorpId() throws WxErrorException {
    Long departId = 1L;
    String corpId = "test_corp_id";
    String accessToken = "test_access_token";
    String result = "{\"errcode\":0,\"errmsg\":\"ok\",\"userlist\":[{\"userid\":\"zhangsan\",\"name\":\"张三\"}]}";

    when(configStorage.getAccessToken(corpId)).thenReturn(accessToken);
    String url = new WxCpTpDefaultConfigImpl().getApiUrl(USER_SIMPLE_LIST + departId);
    when(wxCpTpService.get(eq(url), contains("access_token=" + accessToken), eq(true))).thenReturn(result);

    List<WxCpUser> users = wxCpTpUserService.listSimpleByDepartment(departId, true, 0, corpId);
    assertNotNull(users);

    // 验证调用时传入了 withoutSuiteAccessToken=true，确保不会附加 suite_access_token
    verify(wxCpTpService).get(eq(url), contains("access_token=" + accessToken), eq(true));
  }
}
