package me.chanjar.weixin.cp.corpgroup.service.impl;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.bean.WxCpAgent;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorpGetTokenReq;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpMaTransferSession;
import me.chanjar.weixin.cp.config.WxCpCorpGroupConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpCorpGroupDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpCorpGroupRedissonConfigImpl;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import me.chanjar.weixin.cp.corpgroup.service.WxCpCgService;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.CorpGroup.MA_TRANSFER_SESSION;
import static org.testng.Assert.assertNotNull;

/**
 * @author libo
 */
@Guice(modules = ApiTestModule.class)
public class WxCpCgServiceApacheHttpClientImplTest {
  private final WxCpCgService cgService = Mockito.spy(new WxCpCgServiceApacheHttpClientImpl());
  WxCpCorpGroupConfigStorage wxCpCorpGroupConfigStorage;
  @Inject
  WxCpService wxCpService;

  //下游企业的corpId
  String corpId = "";
  //下游企业的agentId
  int agentId = 0;
  int businessType = 0;
  String userId = "";
  WxCpCorpGroupCorpGetTokenReq wxCpCorpGroupCorpGetTokenReq;

  @BeforeMethod
  public void setUp() {
    cgService.setWxCpCorpGroupConfigStorage(wxCpCorpGroupConfigStorage());
    cgService.setWxCpService(wxCpService);

    wxCpCorpGroupCorpGetTokenReq = new WxCpCorpGroupCorpGetTokenReq();
    wxCpCorpGroupCorpGetTokenReq.setCorpId(corpId);
    wxCpCorpGroupCorpGetTokenReq.setAgentId(agentId);
    wxCpCorpGroupCorpGetTokenReq.setBusinessType(businessType);
  }

  public WxCpCorpGroupConfigStorage wxCpCorpGroupConfigStorage() {
    wxCpCorpGroupConfigStorage = new WxCpCorpGroupDefaultConfigImpl();
    wxCpCorpGroupConfigStorage.setCorpId(wxCpService.getWxCpConfigStorage().getCorpId());
    wxCpCorpGroupConfigStorage.setAgentId(wxCpService.getWxCpConfigStorage().getAgentId().intValue());
    return wxCpCorpGroupConfigStorage;
  }

  @Test
  public void testGetCorpAccessToken() throws Exception {
    String corpAccessToken = cgService.getCorpAccessToken(corpId, agentId, businessType);
    assertNotNull(corpAccessToken);
  }

  /**
   * 通讯录-读取成员
   *
   * @throws Exception
   */
  @Test
  public void testGetCorpUser() throws Exception {
    String result = cgService.get(wxCpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.User.USER_GET + userId), null, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(result);
    WxCpUser wxCpUser = WxCpUser.fromJson(result);
    assertNotNull(wxCpUser.getUserId());
  }

  /**
   * 应用管理-获取指定的应用详情
   *
   * @throws Exception
   */
  @Test
  public void testGetAgent() throws Exception {

    String result = cgService.get(wxCpService.getWxCpConfigStorage().getApiUrl(String.format(WxCpApiPathConsts.Agent.AGENT_GET, agentId)), null, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(result);
    WxCpAgent wxCpAgent = WxCpAgent.fromJson(result);
    assertNotNull(wxCpAgent.getAgentId());
  }

  /**
   * 获取下级/下游企业小程序session
   *
   * @throws Exception
   */
  @Test
  public void testGetTransferSession() throws Exception {
    String sessionKey = "";

    WxCpMaTransferSession wxCpMaTransferSession = cgService.getCorpTransferSession(userId, sessionKey, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(wxCpMaTransferSession);
  }
}
