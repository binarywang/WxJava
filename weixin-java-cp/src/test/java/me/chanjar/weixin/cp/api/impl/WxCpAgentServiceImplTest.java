package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpAgentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAgent;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;


/**
 * <pre>
 *  管理企业号应用-测试
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpAgentServiceImplTest {
  @Inject
  private WxCpService wxCpService;

  /**
   * Test get.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGet() throws Exception {
    final Integer agentId = this.wxCpService.getWxCpConfigStorage().getAgentId();
    WxCpAgent wxCpAgent = this.wxCpService.getAgentService().get(agentId);

    assertThat(wxCpAgent.getAgentId()).isEqualTo(agentId);

    assertThat(wxCpAgent.getAllowUserInfos().getUsers().toArray()).isNotEmpty();
    assertThat(wxCpAgent.getAllowParties().getPartyIds().toArray()).isNotEmpty();
    assertThat(wxCpAgent.getAllowTags().getTagIds().toArray()).isNotEmpty();
  }

  /**
   * Test set.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSet() throws WxErrorException {
    final Integer agentId = this.wxCpService.getWxCpConfigStorage().getAgentId();

    this.wxCpService.getAgentService().set(WxCpAgent.builder()
      .description("abcddd")
      .logoMediaId("aaaaaaaaaaaaaa")
      .agentId(agentId)
      .build());
  }

  /**
   * Test list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testList() throws WxErrorException {
    List<WxCpAgent> list = this.wxCpService.getAgentService().list();

    assertThat(list).isNotEmpty();

    assertThat(list.get(0).getAgentId()).isNotNull();
    assertThat(list.get(0).getName()).isNotEmpty();
    assertThat(list.get(0).getSquareLogoUrl()).isNotEmpty();
  }

  /**
   * Test get admin list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetAdminList() throws WxErrorException {
    final Integer agentId = this.wxCpService.getWxCpConfigStorage().getAgentId();
    me.chanjar.weixin.cp.bean.WxCpTpAdmin adminList = this.wxCpService.getAgentService().getAdminList(agentId);

    assertThat(adminList).isNotNull();
    assertThat(adminList.getErrCode()).isEqualTo(0);
  }

  /**
   * The type Mock test.
   */
  public static class MockTest {
    private final WxCpService wxService = mock(WxCpService.class);

    /**
     * Test get.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGet() throws Exception {
      String returnJson = "{\"errcode\": 0,\"errmsg\": \"ok\",\"agentid\": 9,\"name\": \"测试应用\",\"square_logo_url\": " +
        "\"http://wx.qlogo.cn/mmhead/alksjf;lasdjf;lasjfuodiuj3rj2o34j/0\",\"description\": \"这是一个企业号应用\"," +
        "\"allow_userinfos\": {\"user\": [{\"userid\": \"0009854\"}, {\"userid\": \"1723\"}, {\"userid\": " +
        "\"5625\"}]},\"allow_partys\": {\"partyid\": [42762742]},\"allow_tags\": {\"tagid\": [23, 22, 35, 19, 32, " +
        "125, 133, 46, 150, 38, 183, 9, 7]},\"close\": 0,\"redirect_domain\": \"weixin.com.cn\"," +
        "\"report_location_flag\": 0,\"isreportenter\": 0,\"home_url\": \"\"}";
      final WxCpConfigStorage configStorage = new WxCpDefaultConfigImpl();
      when(wxService.getWxCpConfigStorage()).thenReturn(configStorage);
      when(wxService.get(String.format(configStorage.getApiUrl(WxCpApiPathConsts.Agent.AGENT_GET), 9), null)).thenReturn(returnJson);
      when(wxService.getAgentService()).thenReturn(new WxCpAgentServiceImpl(wxService));

      WxCpAgentService wxAgentService = this.wxService.getAgentService();
      WxCpAgent wxCpAgent = wxAgentService.get(9);

      assertEquals(9, wxCpAgent.getAgentId().intValue());

      assertEquals(new Integer[]{42762742}, wxCpAgent.getAllowParties().getPartyIds().toArray());

      assertEquals(new Integer[]{23, 22, 35, 19, 32, 125, 133, 46, 150, 38, 183, 9, 7},
        wxCpAgent.getAllowTags().getTagIds().toArray());

    }

    /**
     * Test get admin list.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAdminList() throws Exception {
      String returnJson = "{\"errcode\": 0,\"errmsg\": \"ok\",\"admin\": [{\"userid\": \"zhangsan\"," +
        "\"open_userid\": \"woAJ2GCAAAXtWyujaWJHDDGi0mACH71w\",\"auth_type\": 1}," +
        "{\"userid\": \"lisi\",\"open_userid\": \"woAJ2GCAAAXtWyujaWJHDDGi0mACH72w\",\"auth_type\": 2}]}";
      final WxCpConfigStorage configStorage = new WxCpDefaultConfigImpl();
      when(wxService.getWxCpConfigStorage()).thenReturn(configStorage);
      when(wxService.post(configStorage.getApiUrl(WxCpApiPathConsts.Agent.AGENT_GET_ADMIN_LIST), "{\"agentid\":9}")).thenReturn(returnJson);
      when(wxService.getAgentService()).thenReturn(new WxCpAgentServiceImpl(wxService));

      WxCpAgentService wxAgentService = this.wxService.getAgentService();
      me.chanjar.weixin.cp.bean.WxCpTpAdmin adminList = wxAgentService.getAdminList(9);

      assertEquals(0, adminList.getErrCode().intValue());
      assertEquals(2, adminList.getAdmin().size());
      assertEquals("zhangsan", adminList.getAdmin().get(0).getUserId());
      assertEquals("woAJ2GCAAAXtWyujaWJHDDGi0mACH71w", adminList.getAdmin().get(0).getOpenUserId());
      assertEquals(1, adminList.getAdmin().get(0).getAuthType().intValue());
    }

  }

}
