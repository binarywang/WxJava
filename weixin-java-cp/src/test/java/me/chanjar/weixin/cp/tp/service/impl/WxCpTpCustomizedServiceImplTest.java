package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpCustomizedAppDetail;
import me.chanjar.weixin.cp.bean.WxCpTpTemplateList;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpCustomizedService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_CUSTOMIZED_APP_DETAIL;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_TEMPLATE_LIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * 代开发相关接口测试
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2026-01-14
 */
public class WxCpTpCustomizedServiceImplTest {

  @Mock
  private WxCpTpServiceApacheHttpClientImpl wxCpTpService;

  private WxCpTpConfigStorage configStorage;

  private WxCpTpCustomizedService wxCpTpCustomizedService;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    configStorage = new WxCpTpDefaultConfigImpl();
    when(wxCpTpService.getWxCpTpConfigStorage()).thenReturn(configStorage);
    wxCpTpCustomizedService = new WxCpTpCustomizedServiceImpl(wxCpTpService);
  }

  /**
   * 测试获取应用模板列表
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetTemplateList() throws WxErrorException {
    String result = "{\n" +
      "  \"errcode\": 0,\n" +
      "  \"errmsg\": \"ok\",\n" +
      "  \"template_list\": [\n" +
      "    {\n" +
      "      \"template_id\": \"tpl001\",\n" +
      "      \"template_type\": 1,\n" +
      "      \"app_name\": \"测试应用\",\n" +
      "      \"logo_url\": \"https://example.com/logo.png\",\n" +
      "      \"app_desc\": \"这是一个测试应用\",\n" +
      "      \"status\": 1\n" +
      "    }\n" +
      "  ]\n" +
      "}";

    String url = configStorage.getApiUrl(GET_TEMPLATE_LIST);
    when(wxCpTpService.getWxCpProviderToken()).thenReturn("mock_provider_token");
    when(wxCpTpService.get(eq(url + "?provider_access_token=mock_provider_token"), eq(null), eq(true)))
      .thenReturn(result);

    final WxCpTpTemplateList templateList = wxCpTpCustomizedService.getTemplateList();

    assertNotNull(templateList);
    assertEquals(templateList.getErrcode(), Long.valueOf(0));
    assertNotNull(templateList.getTemplateList());
    assertEquals(templateList.getTemplateList().size(), 1);
    assertEquals(templateList.getTemplateList().get(0).getTemplateId(), "tpl001");
    assertEquals(templateList.getTemplateList().get(0).getAppName(), "测试应用");
  }

  /**
   * 测试获取代开发应用详情
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCustomizedAppDetail() throws WxErrorException {
    String authCorpId = "ww1234567890abcdef";
    Integer agentId = 1000001;

    String result = "{\n" +
      "  \"errcode\": 0,\n" +
      "  \"errmsg\": \"ok\",\n" +
      "  \"auth_corpid\": \"ww1234567890abcdef\",\n" +
      "  \"auth_corp_name\": \"测试企业\",\n" +
      "  \"auth_corp_square_logo_url\": \"https://example.com/square_logo.png\",\n" +
      "  \"auth_corp_round_logo_url\": \"https://example.com/round_logo.png\",\n" +
      "  \"auth_corp_type\": 1,\n" +
      "  \"auth_corp_qrcode_url\": \"https://example.com/qrcode.png\",\n" +
      "  \"auth_corp_user_limit\": 200,\n" +
      "  \"auth_corp_full_name\": \"测试企业有限公司\",\n" +
      "  \"auth_corp_verified_type\": 2,\n" +
      "  \"auth_corp_industry\": \"互联网\",\n" +
      "  \"auth_corp_sub_industry\": \"软件服务\",\n" +
      "  \"auth_corp_location\": \"广东省深圳市\",\n" +
      "  \"customized_app_list\": [\n" +
      "    {\n" +
      "      \"agentid\": 1000001,\n" +
      "      \"template_id\": \"tpl001\",\n" +
      "      \"name\": \"测试应用\",\n" +
      "      \"description\": \"这是一个测试应用\",\n" +
      "      \"logo_url\": \"https://example.com/logo.png\",\n" +
      "      \"allow_userinfos\": {\n" +
      "        \"user\": [\n" +
      "          {\"userid\": \"zhangsan\"}\n" +
      "        ],\n" +
      "        \"department\": [\n" +
      "          {\"id\": 1}\n" +
      "        ]\n" +
      "      },\n" +
      "      \"close\": 0,\n" +
      "      \"home_url\": \"https://example.com/home\",\n" +
      "      \"app_type\": 0\n" +
      "    }\n" +
      "  ]\n" +
      "}";

    String url = configStorage.getApiUrl(GET_CUSTOMIZED_APP_DETAIL);
    when(wxCpTpService.getWxCpProviderToken()).thenReturn("mock_provider_token");
    when(wxCpTpService.post(eq(url + "?provider_access_token=mock_provider_token"), any(String.class), eq(true)))
      .thenReturn(result);

    final WxCpTpCustomizedAppDetail appDetail = wxCpTpCustomizedService.getCustomizedAppDetail(authCorpId, agentId);

    assertNotNull(appDetail);
    assertEquals(appDetail.getErrcode(), Long.valueOf(0));
    assertEquals(appDetail.getAuthCorpId(), authCorpId);
    assertEquals(appDetail.getAuthCorpName(), "测试企业");
    assertNotNull(appDetail.getCustomizedAppList());
    assertEquals(appDetail.getCustomizedAppList().size(), 1);
    assertEquals(appDetail.getCustomizedAppList().get(0).getAgentId(), agentId);
    assertEquals(appDetail.getCustomizedAppList().get(0).getTemplateId(), "tpl001");
    assertEquals(appDetail.getCustomizedAppList().get(0).getName(), "测试应用");
  }

  /**
   * 测试获取代开发应用详情（不指定agentId）
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCustomizedAppDetailWithoutAgentId() throws WxErrorException {
    String authCorpId = "ww1234567890abcdef";

    String result = "{\n" +
      "  \"errcode\": 0,\n" +
      "  \"errmsg\": \"ok\",\n" +
      "  \"auth_corpid\": \"ww1234567890abcdef\",\n" +
      "  \"auth_corp_name\": \"测试企业\",\n" +
      "  \"customized_app_list\": []\n" +
      "}";

    String url = configStorage.getApiUrl(GET_CUSTOMIZED_APP_DETAIL);
    when(wxCpTpService.getWxCpProviderToken()).thenReturn("mock_provider_token");
    when(wxCpTpService.post(eq(url + "?provider_access_token=mock_provider_token"), any(String.class), eq(true)))
      .thenReturn(result);

    final WxCpTpCustomizedAppDetail appDetail = wxCpTpCustomizedService.getCustomizedAppDetail(authCorpId, null);

    assertNotNull(appDetail);
    assertEquals(appDetail.getErrcode(), Long.valueOf(0));
    assertEquals(appDetail.getAuthCorpId(), authCorpId);
  }
}
