package me.chanjar.weixin.cp.corpgroup.service.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorpGetTokenReq;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpAgentPerm;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpDepartment;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpUser;
import me.chanjar.weixin.cp.config.WxCpCorpGroupConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpCorpGroupDefaultConfigImpl;
import me.chanjar.weixin.cp.corpgroup.service.WxCpCgService;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * @author libo
 */
@Guice(modules = ApiTestModule.class)
public class WxCpLinkedCorpServiceImplTest {
  WxCpCorpGroupConfigStorage wxCpCorpGroupConfigStorage;
  @Inject
  WxCpService wxCpService;

  WxCpCgService cgService;

  //下游企业的corpId
  String corpId = "";
  //下游企业的agentId
  int agentId = 0;
  int businessType = 0;
  //CorpID/UserID
  String corpUserId = "";
  String departmentId = "";
  WxCpCorpGroupCorpGetTokenReq wxCpCorpGroupCorpGetTokenReq;

  @BeforeMethod
  public void setUp() {
    cgService = new WxCpCgServiceApacheHttpClientImpl();
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
    wxCpCorpGroupConfigStorage.setAgentId(wxCpService.getWxCpConfigStorage().getAgentId());
    return wxCpCorpGroupConfigStorage;
  }

  @Test
  public void testGetLinkedCorpAgentPerm() throws WxErrorException {
    WxCpLinkedCorpAgentPerm resp = cgService.getLinkedCorpService().getLinkedCorpAgentPerm(wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpUser() throws WxErrorException {
    WxCpLinkedCorpUser resp = cgService.getLinkedCorpService().getLinkedCorpUser(corpUserId, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpSimpleUserList() throws WxErrorException {
    List<WxCpLinkedCorpUser> resp = cgService.getLinkedCorpService().getLinkedCorpSimpleUserList(departmentId, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpUserList() throws WxErrorException {
    List<WxCpLinkedCorpUser> resp = cgService.getLinkedCorpService().getLinkedCorpUserList(departmentId, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpDepartmentList() throws WxErrorException {
    List<WxCpLinkedCorpDepartment> resp = cgService.getLinkedCorpService().getLinkedCorpDepartmentList(departmentId, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(resp);
  }

}
