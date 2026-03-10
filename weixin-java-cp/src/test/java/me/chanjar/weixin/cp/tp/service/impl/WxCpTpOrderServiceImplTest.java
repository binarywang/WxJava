package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.order.WxCpTpOrderDetails;
import me.chanjar.weixin.cp.bean.order.WxCpTpOrderListGetResult;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpOrderService;
import org.apache.commons.lang3.time.DateUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_ORDER;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_ORDER_LIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * 应用版本付费订单相关接口测试
 */
public class WxCpTpOrderServiceImplTest {

  @Mock
  private WxCpTpServiceApacheHttpClientImpl wxCpTpService;

  private WxCpTpConfigStorage configStorage;

  private WxCpTpOrderService wxCpTpOrderService;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    configStorage = new WxCpTpDefaultConfigImpl();
    when(wxCpTpService.getWxCpTpConfigStorage()).thenReturn(configStorage);
    wxCpTpOrderService = new WxCpTpOrderServiceImpl(wxCpTpService);
  }


  /**
   * 获取订单详情
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetOrder() throws WxErrorException {
    String orderId = "2018091822ks1sd3s";

    String result = "" +
      "{\n" +
      "    \"errcode\" : 0,\n" +
      "    \"errmsg\" : \"ok\",\n" +
      "    \"orderid\" : \"2018091822ks1sd3s\",\n" +
      "    \"order_status\" : 1,\n" +
      "    \"order_type\" : 1,\n" +
      "    \"paid_corpid\" : \"wwfedd7e5291d63aaa\",\n" +
      "    \"operator_id\" : \"zhangsan\",\n" +
      "    \"suiteid\" : \"wx67cce113441ccaaa\",\n" +
      "    \"appid\" : 1,\n" +
      "    \"edition_id\" : \"RLS65535\",\n" +
      "    \"edition_name\" : \"协同版\",\n" +
      "    \"price\" : 100,\n" +
      "    \"user_count\" : 1000,\n" +
      "    \"order_period\": 365,\n" +
      "    \"order_time\" : 1533702999,\n" +
      "    \"paid_time\" : 1533702910,\n" +
      "    \"begin_time\" : 1533702910,\n" +
      "    \"end_time\" : 1553515904,\n" +
      "    \"order_from\" : 1,\n" +
      "    \"operator_corpid\" : \"wwfedd7e5292d63aaa\",\n" +
      "    \"service_share_amount\" : 60,\n" +
      "    \"platform_share_amount\" : 10,\n" +
      "    \"dealer_share_amount\" : 30,\n" +
      "    \"dealer_corp_info\":\n" +
      "    {\n" +
      "      \"corpid\": \"xxxx\",\n" +
      "      \"corp_name\": \"name\"\n" +
      "    }\n" +
      "  }";
    String url = configStorage.getApiUrl(GET_ORDER);
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);

    final WxCpTpOrderDetails orderDetails = wxCpTpOrderService.getOrder(orderId);

    assertNotNull(orderDetails);

    assertEquals(orderDetails.getOrderId(), orderId);

  }

  /**
   * 获取订单列表
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetOrderList() throws WxErrorException {
    String orderId = "2018091822ks1sd3s";
    Date startTime = new Date();
    Date endTime = DateUtils.addDays(startTime, 5);
    Integer testMode = 0;

    String result = "" +
      "  {\n" +
      "    \"errcode\" : 0,\n" +
      "    \"errmsg\" : \"ok\",\n" +
      "    \"order_list\": [\n" +
      "    {\n" +
      "      \"orderid\" : \"2018091822ks1sd3s\",\n" +
      "      \"order_status\" : 1,\n" +
      "      \"order_type\" : 1,\n" +
      "      \"paid_corpid\" : \"wwfedd7e5292d63aaa\",\n" +
      "      \"operator_id\" : \"zhangsan\",\n" +
      "      \"suiteid\" : \"wx67cce113441cc7a6\",\n" +
      "      \"appid\" : 1,\n" +
      "      \"edition_id\" : \"RLS65535\",\n" +
      "      \"edition_name\" : \"协同版\",\n" +
      "      \"price\" : 100,\n" +
      "      \"user_count\" : 1000,\n" +
      "      \"order_period\": 365,\n" +
      "      \"order_time\" : 1533702999,\n" +
      "      \"paid_time\" : 1533702910,\n" +
      "      \"begin_time\" : 1533702910,\n" +
      "      \"end_time\" : 1553515904,\n" +
      "      \"order_from\" : 1,\n" +
      "      \"operator_corpid\" : \"wwfedd7e5292d63aaa\",\n" +
      "      \"service_share_amount\" : 60,\n" +
      "      \"platform_share_amount\" : 10,\n" +
      "      \"dealer_share_amount\" : 30,\n" +
      "      \"dealer_corp_info\":\n" +
      "      {\n" +
      "        \"corpid\": \"xxxx\",\n" +
      "        \"corp_name\": \"name\"\n" +
      "      }\n" +
      "    }]\n" +
      "  }";

    String url = configStorage.getApiUrl(GET_ORDER_LIST);
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);

    final WxCpTpOrderListGetResult orderList = wxCpTpOrderService.getOrderList(startTime, endTime, testMode);

    assertNotNull(orderList);

    final List<WxCpTpOrderDetails> detailsList = orderList.getOrderList();

    assertTrue(Objects.nonNull(detailsList) && !detailsList.isEmpty());

    assertEquals(detailsList.get(0).getOrderId(), orderId);
  }


}
