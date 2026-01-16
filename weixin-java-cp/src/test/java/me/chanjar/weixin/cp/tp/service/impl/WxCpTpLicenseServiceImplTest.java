package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.*;
import me.chanjar.weixin.cp.bean.license.account.*;
import me.chanjar.weixin.cp.bean.license.order.*;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpLicenseService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.License.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * 许可证账号服务测试
 *
 * @author Totoro  created on  2022/6/27 16:34
 */
public class WxCpTpLicenseServiceImplTest {

  @Mock
  private WxCpTpServiceApacheHttpClientImpl wxCpTpService;

  private WxCpTpConfigStorage configStorage;

  private WxCpTpLicenseService wxCpTpLicenseService;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    configStorage = new WxCpTpDefaultConfigImpl();
    when(wxCpTpService.getWxCpTpConfigStorage()).thenReturn(configStorage);
    wxCpTpLicenseService = new WxCpTpLicenseServiceImpl(wxCpTpService);
  }


  /**
   * Test crate new order.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testCrateNewOrder() throws WxErrorException {
    String orderId = "OASFNAISFASFA252462";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"order_id\": \"OASFNAISFASFA252462\"\n" +
      "}";
    String url =
      configStorage.getApiUrl(CREATE_NEW_ORDER) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseNewOrderRequest orderRequest = WxCpTpLicenseNewOrderRequest.builder()
      .accountCount(WxCpTpLicenseAccountCount.builder().baseCount(5).externalContactCount(6).build())
      .buyerUserId("test")
      .corpId("test")
      .accountDuration(WxCpTpLicenseAccountDuration.builder().months(5).build())
      .build();
    final WxCpTpLicenseCreateOrderResp newOrder = wxCpTpLicenseService.createNewOrder(orderRequest);
    assertNotNull(newOrder);
    assertEquals(newOrder.getOrderId(), orderId);
  }


  /**
   * Test create renew order job.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testCreateRenewOrderJob() throws WxErrorException {
    String jobId = "test123456";
    String result = "{\n" +
      "    \"errcode\":0,\n" +
      "    \"errmsg\":\"ok\",\n" +
      "    \"jobid\":\"test123456\",\n" +
      "    \"invalid_account_list\":[\n" +
      "        {\n" +
      "            \"errcode\":1,\n" +
      "            \"errmsg\":\"error\",\n" +
      "            \"userid\":\"userid1\",\n" +
      "            \"type\":1\n" +
      "        },\n" +
      "        {\n" +
      "            \"errcode\":0,\n" +
      "            \"errmsg\":\"ok\",\n" +
      "            \"userid\":\"userid2\",\n" +
      "            \"type\":1\n" +
      "        }\n" +
      "    ]\n" +
      "}";
    String url =
      configStorage.getApiUrl(CREATE_RENEW_ORDER_JOB) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    List<WxCpTpLicenseBaseAccount> accountList = new ArrayList<>();
    accountList.add(WxCpTpLicenseBaseAccount.builder().type(1).userid("userid1").build());
    accountList.add(WxCpTpLicenseBaseAccount.builder().type(1).userid("userid2").build());
    WxCpTpLicenseRenewOrderJobRequest orderJobRequest = WxCpTpLicenseRenewOrderJobRequest.builder()
      .jobId("test123456")
      .accountList(accountList).build();
    final WxCpTpLicenseRenewOrderJobResp renewOrderJob = wxCpTpLicenseService.createRenewOrderJob(orderJobRequest);
    assertNotNull(renewOrderJob);

    assertEquals(renewOrderJob.getJobId(), jobId);

    assertEquals(renewOrderJob.getInvalidAccountList().size(), accountList.size());
  }

  /**
   * Test submit renew order job.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSubmitRenewOrderJob() throws WxErrorException {
    String orderId = "test5915231";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"order_id\": \"test5915231\"\n" +
      "}";
    String url =
      configStorage.getApiUrl(SUBMIT_ORDER_JOB) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseRenewOrderRequest renewOrderRequest = WxCpTpLicenseRenewOrderRequest.builder()
      .jobId("test123456")
      .accountDuration(WxCpTpLicenseAccountDuration.builder().months(5).build())
      .buyerUserId("test")
      .build();
    WxCpTpLicenseCreateOrderResp createOrderResp = wxCpTpLicenseService.submitRenewOrder(renewOrderRequest);
    assertNotNull(createOrderResp);

    assertEquals(createOrderResp.getOrderId(), orderId);
  }

  /**
   * Test get order list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetOrderList() throws WxErrorException {
    String nextCursor = "DSGAKAFA4524";
    String orderId = "test123";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"next_cursor\":\"DSGAKAFA4524\",\n" +
      "\t\"has_more\":1,\n" +
      "\t\"order_list\":[\n" +
      "\t\t{\n" +
      "\t\t\t\"order_id\":\"test123\",\n" +
      "\t\t\t\"order_type\":1\n" +
      "\t\t}\n" +
      "\t]\n" +
      "}";

    String url = configStorage.getApiUrl(LIST_ORDER) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseOrderListResp orderList = wxCpTpLicenseService.getOrderList("test", new Date(), new Date(), null, 10);
    assertNotNull(orderList);

    assertEquals(orderList.getNextCursor(), nextCursor);

    assertEquals(orderList.getOrderList().get(0).getOrderId(), orderId);

  }

  /**
   * Test get order.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetOrder() throws WxErrorException {
    String corpId = "ASFASF4254";
    String orderId = "FASASIFJ9W125234";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"order\":{\n" +
      "\t\t\"order_id\":\"FASASIFJ9W125234\",\n" +
      "\t\t\"order_type\":1,\n" +
      "\t\t\"order_status\":1,\n" +
      "\t\t\"corpid\":\"ASFASF4254\",\n" +
      "\t\t\"price\":10000,\n" +
      "\t\t\"account_count\":{\n" +
      "\t  \t   \"base_count\":100,\n" +
      "      \t   \"external_contact_count\":100\n" +
      "\t     },\n" +
      "\t\t  \"account_duration\":\n" +
      " \t\t   {\n" +
      "\t   \t  \t\"months\":2\n" +
      "   \t \t \t},\n" +
      "\t\t\"create_time\":150000000,\n" +
      "\t    \"pay_time\":1550000000\n" +
      "\t}\n" +
      "}";
    String url = configStorage.getApiUrl(GET_ORDER) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseOrderInfoResp orderInfo = wxCpTpLicenseService.getOrderInfo(orderId);
    assertNotNull(orderInfo);

    assertNotNull(orderInfo.getOrder());

    assertEquals(orderInfo.getOrder().getOrderId(), orderId);

    assertEquals(orderInfo.getOrder().getCorpId(), corpId);


  }


  /**
   * Test get order account.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetOrderAccount() throws WxErrorException {
    String orderId = "ASFASF4254";
    String activeCode = "FASASIFJ9W125234";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"next_cursor\": \"ASFASF4254\",\n" +
      "\t\"has_more\":1,\n" +
      "\t\"account_list\":[\n" +
      "\t\t{\n" +
      "\t\t\t\"active_code\": \"FASASIFJ9W125234\",\n" +
      "\t\t\t\"userid\":\"XXX\",\n" +
      "\t\t\t\"type\": 1\n" +
      "\t\t},\n" +
      "\t\t{\n" +
      "\t\t\t\"active_code\": \"code2\",\n" +
      "\t\t\t\"userid\":\"XXX\",\n" +
      "\t\t\t\"type\": 2\n" +
      "\t\t}\n" +
      "\t]\n" +
      "}";

    String url =
      configStorage.getApiUrl(LIST_ORDER_ACCOUNT) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseOrderAccountListResp orderAccountList = wxCpTpLicenseService.getOrderAccountList(orderId, 10, null);
    assertNotNull(orderAccountList);

    assertNotNull(orderAccountList.getAccountList());

    assertEquals(orderAccountList.getAccountList().get(0).getActiveCode(), activeCode);


  }


  /**
   * Test active account.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testActiveAccount() throws WxErrorException {
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\"\n" +
      "}";

    String url =
      configStorage.getApiUrl(ACTIVE_ACCOUNT) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpBaseResp wxCpBaseResp = wxCpTpLicenseService.activeCode("123456", "123456", "123456");
    assertNotNull(wxCpBaseResp);
  }

  /**
   * Test batch active account.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testBatchActiveAccount() throws WxErrorException {
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"active_result\":[\n" +
      "\t{\n" +
      "\t\t\"active_code\" : \"aASFINAJOFASF\",\n" +
      "\t\t\"userid\": \"SAGASGSD\",\n" +
      "\t\t\"errcode\":0\n" +
      "\t},\n" +
      "\t{\n" +
      "\t\t\"active_code\" : \"ASDEGAFAd\",\n" +
      "\t\t\"userid\": \"dsfafD\",\n" +
      "\t\t\"errcode\":0\n" +
      "\t}]\n" +
      "}";
    String url =
      configStorage.getApiUrl(BATCH_ACTIVE_ACCOUNT) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    List<WxCpTpLicenseActiveAccount> accountList = new ArrayList<>();
    accountList.add(WxCpTpLicenseActiveAccount.builder().userid("SAGASGSD").activeCode("aASFINAJOFASF").build());
    accountList.add(WxCpTpLicenseActiveAccount.builder().userid("dsfafD").activeCode("ASDEGAFAd").build());
    WxCpTpLicenseBatchActiveResultResp wxCpTpLicenseBatchActiveResultResp = wxCpTpLicenseService.batchActiveCode(
      "123456", accountList);
    assertNotNull(wxCpTpLicenseBatchActiveResultResp);

    assertEquals(wxCpTpLicenseBatchActiveResultResp.getActiveResults().size(), accountList.size());

    assertEquals(wxCpTpLicenseBatchActiveResultResp.getActiveResults().get(0).getActiveCode(), "aASFINAJOFASF");
  }


  /**
   * Test get active info by code.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetActiveInfoByCode() throws WxErrorException {
    String activeCode = "asgasfasfa";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"active_info\": {\n" +
      "\t\t\"active_code\": \"asgasfasfa\",\n" +
      "\t\t\"type\": 1,\n" +
      "\t\t\"status\": 1,\n" +
      "\t\t\"userid\": \"asfasgasg\",\n" +
      "\t\t\"create_time\":1640966400,\n" +
      "\t\t\"active_time\": 1640966400,\n" +
      "\t\t\"expire_time\":1640966400\n" +
      "\t}\n" +
      "}";

    String url =
      configStorage.getApiUrl(GET_ACTIVE_INFO_BY_CODE) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseCodeInfoResp activeInfoByCode = wxCpTpLicenseService.getActiveInfoByCode("123456", "safasg");
    assertNotNull(activeInfoByCode);

    assertEquals(activeInfoByCode.getActiveCodeInfo().getActiveCode(), activeCode);


  }


  /**
   * Test get active info by user.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetActiveInfoByUser() throws WxErrorException {
    String activeCode = "asfaisfhiuaw";
    String userid = "asfasgasga";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"active_status\": 1,\n" +
      "\t\"active_info_list\": \n" +
      "\t[\n" +
      "\t\t  {\n" +
      "\t\t\t\"active_code\": \"asfaisfhiuaw\",\n" +
      "\t\t\t\"type\": 1,\n" +
      "\t\t\t\"userid\": \"asfasgasga\",\n" +
      "\t\t\t\"active_time\": 1640966400,\n" +
      "\t\t\t\"expire_time\":1640966400\n" +
      " \t \t  },\n" +
      "          {\n" +
      "\t\t\t\"active_code\": \"gasdawsd\",\n" +
      "\t\t\t\"type\": 2,\n" +
      "\t\t\t\"userid\": \"asdfasfasf\",\n" +
      "\t\t\t\"active_time\":1640966400,\n" +
      "\t\t\t\"expire_time\":1640966400\n" +
      "\t\t  }\n" +
      "       ]\n" +
      "}";

    String url =
      configStorage.getApiUrl(GET_ACTIVE_INFO_BY_USER) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseActiveInfoByUserResp activeInfoByUser = wxCpTpLicenseService.getActiveInfoByUser("123456", userid);
    assertNotNull(activeInfoByUser);

    assertEquals(activeInfoByUser.getActiveStatus().intValue(), 1);

    assertEquals(activeInfoByUser.getActiveInfoList().get(0).getActiveCode(), activeCode);
  }

  /**
   * Test batch get active info by user.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testBatchGetActiveInfoByUser() throws WxErrorException {
    String activeCode = "asgasgasgas";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"active_info_list\": [\n" +
      "\t\t{\n" +
      "\t\t\t\"active_code\": \"asgasgasgas\",\n" +
      "\t\t\t\"type\": 1,\n" +
      "\t\t\t\"status\": 1,\n" +
      "\t\t\t\"userid\": \"gadfFDF\",\n" +
      "\t\t\t\"create_time\":1640966400,\n" +
      "\t\t\t\"active_time\": 1640966400,\n" +
      "\t\t\t\"expire_time\":1640966400\n" +
      "\t\t},\n" +
      "\t\t{\n" +
      "\t\t\t\"active_code\": \"awsgdgasdasd\",\n" +
      "\t\t\t\"type\": 2,\n" +
      "\t\t\t\"status\": 1,\n" +
      "\t\t\t\"userid\": \"SGASRDASGAQ\",\n" +
      "\t\t\t\"create_time\":1640966400,\n" +
      "\t\t\t\"active_time\": 1640966400,\n" +
      "\t\t\t\"expire_time\":1640966400\n" +
      "\t\t}\n" +
      "\t],\n" +
      "\t\"invalid_active_code_list\":[\"fasgasga\"]\n" +
      "}";


    String url =
      configStorage.getApiUrl(BATCH_GET_ACTIVE_INFO_BY_CODE) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    Set<String> codes = new HashSet<>();
    codes.add("asgasgasgas");
    codes.add("awsgdgasdasd");
    codes.add("fasgasga");
    WxCpTpLicenseBatchCodeInfoResp codeInfoResp = wxCpTpLicenseService.batchGetActiveInfoByCode(codes, "asfasfas");
    assertNotNull(codeInfoResp);

    assertEquals(codeInfoResp.getActiveCodeInfoList().size(), codes.size() - 1);

    assertNotNull(codeInfoResp.getInvalidActiveCodeList());


  }


  /**
   * Test get corp account list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCorpAccountList() throws WxErrorException {
    String nextCursor = "asfasdfas";
    String userid = "asdasdasd";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"next_cursor\":\"asfasdfas\",\n" +
      "\t\"has_more\":1,\n" +
      "\t\"account_list\":[\n" +
      "\t\t{\n" +
      "\t\t\t\"userid\": \"asdasdasd\",\n" +
      "\t\t\t\"type\": 1,\n" +
      "\t\t\t\"expire_time\":1500000000,\n" +
      "\t\t\t\"active_time\":1500000000\n" +
      "\t\t},\n" +
      "\t\t{\n" +
      "\t\t\t\"userid\": \"asgasgasdasd\",\n" +
      "\t\t\t\"type\": 1,\n" +
      "\t\t\t\"expire_time\":1500000000,\n" +
      "\t\t\t\"active_time\":1500000000\n" +
      "\t\t}\n" +
      "\t]\n" +
      "}";

    String url =
      configStorage.getApiUrl(LIST_ACTIVED_ACCOUNT) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    WxCpTpLicenseCorpAccountListResp accountList = wxCpTpLicenseService.getCorpAccountList("123456", 10, null);
    assertNotNull(accountList);

    assertNotNull(accountList.getOrderList());

    assertEquals(accountList.getNextCursor(), nextCursor);

    assertEquals(accountList.getOrderList().get(0).getUserid(), userid);
  }


  /**
   * Test batch transfer license.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testBatchTransferLicense() throws WxErrorException {
    String handoverUserid = "dazdasfasf";
    String takeoverUserid = "asfasfasf";
    String result = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"transfer_result\":[\n" +
      "\t{\n" +
      "\t\t\"handover_userid\":\"dazdasfasf\",\n" +
      "\t\t\"takeover_userid\":\"asfasfasf\",\n" +
      "\t\t\"errcode\":0\n" +
      "\t}]\n" +
      "}";

    String url =
      configStorage.getApiUrl(BATCH_TRANSFER_LICENSE) + "?provider_access_token=" + wxCpTpService.getWxCpProviderToken();
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);
    List<WxCpTpLicenseTransfer> transferList = new ArrayList<>();
    transferList.add(WxCpTpLicenseTransfer.builder().handoverUserId(handoverUserid).takeoverUserId(takeoverUserid).build());
    WxCpTpLicenseBatchTransferResp licenseBatchTransferResp = wxCpTpLicenseService.batchTransferLicense("123456",
      transferList);
    assertNotNull(licenseBatchTransferResp);

    assertNotNull(licenseBatchTransferResp.getTransferResult());

    assertEquals(licenseBatchTransferResp.getTransferResult().size(), transferList.size());

  }


}
