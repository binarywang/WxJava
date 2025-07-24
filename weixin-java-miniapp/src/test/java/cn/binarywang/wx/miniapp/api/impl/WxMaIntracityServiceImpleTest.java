package cn.binarywang.wx.miniapp.api.impl;

import static org.testng.AssertJUnit.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.intractiy.*;
import cn.binarywang.wx.miniapp.bean.openapi.WxMiniGetApiQuotaResult;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.test.TestConfig;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaIntracityServiceImpleTest {
  private static final Logger logger = LoggerFactory.getLogger(WxMaIntracityServiceImpleTest.class);

  @Inject private WxMaService wxService;

  @Test
  public void testApiSignature() throws Exception {
    WxMiniGetApiQuotaResult result =
        wxService
            .getWxMaOpenApiService()
            .getApiQuota(
                WxMaApiUrlConstants.Intracity.APPLY_URL.substring(
                    "https://api.weixin.qq.com".length()));
    logger.info("apply 额度剩余 :{}", result.getQuota());
  }

  @Test
  public void testApiGetPostNullData() throws Exception {
    try {
      wxService.get(WxMaApiUrlConstants.Analysis.GET_USER_PORTRAIT_URL, null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 get(url, null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
    // 走加密路径url
    try {
      wxService.post(WxMaApiUrlConstants.OpenApi.CLEAR_QUOTA, (Object) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, Object null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
    try {
      wxService.post(WxMaApiUrlConstants.OpenApi.CLEAR_QUOTA, (String) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, String null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
    try {
      wxService.post(WxMaApiUrlConstants.OpenApi.CLEAR_QUOTA, (JsonObject) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, JsonObject null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
    try {
      wxService.post(WxMaApiUrlConstants.OpenApi.CLEAR_QUOTA, (ToJson) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, ToJson null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }

    // 不走加密路径URL
    try {
      wxService.post(WxMaApiUrlConstants.Intracity.APPLY_URL, (Object) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, Object null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
    try {
      wxService.post(WxMaApiUrlConstants.Intracity.APPLY_URL, (String) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, String null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
    try {
      wxService.post(WxMaApiUrlConstants.Intracity.APPLY_URL, (JsonObject) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, JsonObject null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
    try {
      wxService.post(WxMaApiUrlConstants.Intracity.APPLY_URL, (ToJson) null);
    } catch (NullPointerException npe) {
      logger.error("NullPointerException", npe);
      fail("遇到空指针 post(url, ToJson null)");
    } catch (WxErrorException wxErrorException) {
      // 这个是正常的，因为这里的调用没按照接口规则
    }
  }

  @Test
  public void testApply() throws Exception {
    logger.debug("testApply");
    try {
      wxService.getIntracityService().apply();
    } catch (WxErrorException wxEx) {
      if (wxEx.getError().getErrorCode() == 45009) {
        // 调用分钟频率受限
      } else {
        throw wxEx;
      }
    }
  }

  @Test
  public void testStoreRelatedApis() throws Exception {
    WxMaStore store = new WxMaStore();
    store.setStoreName("南京东路店");
    store.setOutStoreId("njdl-001");
    WxMaStore.AddressInfo addr = new WxMaStore.AddressInfo();
    addr.setProvince("上海市");
    addr.setCity("上海市");
    addr.setArea("黄浦区");
    addr.setStreet("");
    addr.setHouse("南京东路690号");
    addr.setLat(31.235318);
    addr.setLng(121.477284);
    addr.setPhone("021-23456789");
    store.setAddressInfo(addr);
    String wxStoreId;
    List<WxMaStore> result =
        wxService.getIntracityService().queryStoreByOutStoreId(store.getOutStoreId());
    if (result.isEmpty()) {
      wxStoreId = wxService.getIntracityService().createStore(store);
      logger.debug("create store result:{}", wxStoreId);
    } else {
      wxStoreId = result.get(0).getWxStoreId();
    }
    store.setWxStoreId(wxStoreId);
    addr.setPhone("021-23450000");
    store.setStoreName(null);
    wxService.getIntracityService().updateStore(store);
    List<WxMaStore> stores = wxService.getIntracityService().listAllStores();
    logger.info("listAllStores 查询到 {} 个门店 {}", stores.size(), stores);
    if (stores.size() > 0) {
      WxMaStore s =
          wxService.getIntracityService().queryStoreByWxStoreId(stores.get(0).getWxStoreId());
      assertNotNull(s);
      List<WxMaStore> list =
          wxService.getIntracityService().queryStoreByOutStoreId(stores.get(0).getOutStoreId());
      logger.info("queryStoreByOutStoreId 查询到 {} 个门店 {}", list.size(), list);
    }
  }

  @Test
  public void testStoreChargeRelated() throws Exception {
    List<WxMaStore> stores = wxService.getIntracityService().listAllStores();
    if (stores.isEmpty()) {
      logger.warn("没有门店，无法测试");
      return;
    }
    WxMaStore store = stores.get(0);

    WxMaGetPayModeResponse resp = wxService.getIntracityService().getPayMode();
    logger.debug("查询付费主体 {}", resp);
    PayMode currentPayMode = resp.getPayMode();
    // 只能用当前付费模式充值；否则微信接口会返回 错误代码：934025, 错误信息：pay_mode not match
    WxMaStoreChargeRequest request = new WxMaStoreChargeRequest();
    request.setPayMode(currentPayMode);
    request.setWxStoreId(store.getWxStoreId());
    request.setServiceTransId("DADA");
    request.setAmount(5000);
    String payUrl = wxService.getIntracityService().storeCharge(request);
    logger.debug("充值URL:{}", payUrl);

    // 查询余额
    WxMaStoreBalance balance =
        wxService.getIntracityService().balanceQuery(store.getWxStoreId(), null, PayMode.STORE);
    logger.debug("余额 {}", balance);

    // 退款
    WxMaStoreRefundRequest rr = new WxMaStoreRefundRequest();
    rr.setPayMode(PayMode.STORE);
    rr.setWxStoreId(store.getWxStoreId());
    rr.setServiceTransId("DADA");
    int refundAmount = wxService.getIntracityService().storeRefund(rr);
    logger.debug("退款:{}", refundAmount);

    // 查询流水
    WxMaQueryFlowRequest qfr = new WxMaQueryFlowRequest();
    qfr.setWxStoreId(store.getWxStoreId());
    WxMaStoreFlowResponse flowResponse = wxService.getIntracityService().queryFlow(qfr);
    logger.debug("查询流水 {}", flowResponse);
  }

  @Test
  public void testPayMode() throws Exception {
    WxMaGetPayModeResponse resp = wxService.getIntracityService().getPayMode();
    logger.debug("查询付费主体 {}", resp);
    PayMode newMode = resp.getPayMode() == PayMode.APP ? PayMode.STORE : PayMode.APP;
    logger.debug("set pay mode to {}", newMode);
    wxService.getIntracityService().setPayMode(newMode);
    WxMaGetPayModeResponse resp2 = wxService.getIntracityService().getPayMode();
    logger.debug("查询付费主体 {}", resp2);
  }

  @Test
  public void testGetCity() throws Exception {
    List<WxMaTransCity> list = wxService.getIntracityService().getCity(null);
    logger.debug("支持的城市 {}", list);
    List<WxMaTransCity> list2 = wxService.getIntracityService().getCity("SFTC");
    logger.debug("SFTC支持的城市有{}个", list2.get(0).getCityList().size());
  }

  @Test
  public void testOrderRelatived() throws Exception {
    List<WxMaStore> stores = wxService.getIntracityService().listAllStores();
    if (stores.isEmpty()) {
      logger.warn("没有门店，无法测试");
      return;
    }
    String wxStoreId = stores.get(0).getWxStoreId();
    {
      WxMaPreAddOrderRequest request = new WxMaPreAddOrderRequest();
      request.setWxStoreId(wxStoreId);
      request.setUseSandbox(1);
      request.setUserName("顺丰同城");
      request.setUserPhone("13800000138");
      request.setUserAddress("北京市海淀区学清嘉创大厦A座15层");
      request.setUserLat(40.01496);
      request.setUserLng(116.353093);
      WxMaPreAddOrderRequest.Cargo cargo = new WxMaPreAddOrderRequest.Cargo();
      cargo.setCargoName("蛋糕");
      cargo.setCargoType(13);
      cargo.setCargoNum(1);
      cargo.setCargoPrice(10000);
      cargo.setCargoWeight(1000);
      request.setCargo(cargo);
      WxMaPreAddOrderResponse response = wxService.getIntracityService().preAddOrder(request);
      logger.debug("查询运费返回 {}， 预估运费{}元", response, response.getEstFee() / 100.0);
    }
    String wxOrderId = null;
    {
      TestConfig config = (TestConfig) this.wxService.getWxMaConfig();
      WxMaAddOrderRequest request = new WxMaAddOrderRequest();
      request.setWxStoreId(wxStoreId);
      request.setStoreOrderId("store-order-" + System.currentTimeMillis());
      request.setOrderSeq("0001");
      request.setUserOpenid(config.getOpenid());
      request.setUseSandbox(1);
      request.setUserName("顺丰同城");
      request.setUserPhone("13800000138");
      request.setUserAddress("北京市海淀区学清嘉创大厦A座15层");
      request.setUserLat(40.01496);
      request.setUserLng(116.353093);
      request.setOrderDetailPath("/pages/user-center/order/detail/detail?id=xxx");
      WxMaAddOrderRequest.Cargo cargo = new WxMaAddOrderRequest.Cargo();
      cargo.setCargoName("蛋糕");
      cargo.setCargoType(13);
      cargo.setCargoNum(1);
      cargo.setCargoPrice(10000);
      cargo.setCargoWeight(1000);
      WxMaAddOrderRequest.ItemDetail detail = new WxMaAddOrderRequest.ItemDetail();
      detail.setItemName("蛋糕A");
      detail.setItemPicUrl("https://www.somehost.com/aaa.jpg");
      detail.setCount(1);
      List<WxMaAddOrderRequest.ItemDetail> itemList = new ArrayList<>();
      itemList.add(detail);
      cargo.setItemList(itemList);
      request.setCargo(cargo);
      WxMaAddOrderResponse response = wxService.getIntracityService().addOrder(request);
      wxOrderId = response.getWxOrderId();
      logger.debug("创建订单返回 {}， wxOrderId:{}", response, wxOrderId);
    }
    WxMaOrder order = wxService.getIntracityService().queryOrderByWxOrderId(wxOrderId);
    logger.debug("查询订单返回 {}， storeOrderId:{} ", order, order.getStoreOrderId());
    WxMaOrder order2 =
        wxService
            .getIntracityService()
            .queryOrderByStoreOrderId(wxStoreId, order.getStoreOrderId());
    logger.debug("查询订单返回 {}，  ", order);
    assertEquals(order2.getWxOrderId(), wxOrderId);

    WxMaCancelOrderResponse cancelOrderResp =
        wxService.getIntracityService().cancelOrderByWxOrderId(wxOrderId, 1, "不再需要");
    logger.debug("取消订单返回 {}， 扣费：{} ", cancelOrderResp, cancelOrderResp.getDeductfee());

    try {
      wxService
          .getIntracityService()
          .cancelOrderByStoreOrderId(wxStoreId, order.getStoreOrderId(), 1, "不再需要");
      fail("重复取消未抛异常，疑似第一次取消未成功");
    } catch (WxErrorException wxErrorException) {
      // 订单已经被取消了，重复取消会报错，这里才正常
    }
  }
}
