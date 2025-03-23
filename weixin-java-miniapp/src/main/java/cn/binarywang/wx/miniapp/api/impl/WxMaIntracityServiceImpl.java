package cn.binarywang.wx.miniapp.api.impl;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Intracity;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

import cn.binarywang.wx.miniapp.api.WxMaIntracityService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.intractiy.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Slf4j
public class WxMaIntracityServiceImpl implements WxMaIntracityService {
  private final WxMaService wxMaService;
  private static final Logger logger = LoggerFactory.getLogger(WxMaIntracityServiceImpl.class);

  private final Gson gson =
      new GsonBuilder()
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
          .create();

  private void checkStringResponse(String response) throws WxErrorException {
    JsonObject respObj = GsonParser.parse(response);
    if (respObj.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
  }

  @Override
  public void apply() throws WxErrorException {
    String response = this.wxMaService.post(Intracity.APPLY_URL, "{}");
    checkStringResponse(response);
  }

  @Override
  public String createStore(WxMaStore store) throws WxErrorException {
    if (store.getOutStoreId() == null) {
      throw new IllegalArgumentException("创建门店时outStoreId不能为空");
    }
    if (store.getWxStoreId() != null) {
      throw new IllegalArgumentException("创建门店时wxStoreId只能是null");
    }
    String response = this.wxMaService.postWithSignature(Intracity.CREATE_STORE_URL, store);
    Map<?, ?> map = gson.fromJson(response, Map.class);
    return (String) map.get("wx_store_id");
  }

  @Override
  public void updateStore(WxMaStore store) throws WxErrorException {
    if (store.getWxStoreId() == null && store.getOutStoreId() == null) {
      throw new IllegalArgumentException("更新门店时wxStoreId 或 outStoreId 至少要有一个不为null");
    }
    JsonObject request = new JsonObject();
    Map<String, String> keys = new HashMap<>();
    if (store.getWxStoreId() != null) {
      keys.put("wx_store_id", store.getWxStoreId());
    } else {
      keys.put("out_store_id", store.getOutStoreId());
    }
    request.add("keys", gson.toJsonTree(keys));
    Map<String, Object> updateContent = new HashMap<>();
    if (store.getStoreName() != null) {
      updateContent.put("store_name", store.getStoreName());
    }
    if (store.getOrderPattern() == 1 || store.getOrderPattern() == 2) {
      updateContent.put("order_pattern", store.getOrderPattern());
    }
    if (store.getServiceTransPrefer() != null) {
      updateContent.put("service_trans_prefer", store.getServiceTransPrefer());
    }
    if (store.getAddressInfo() != null) {
      updateContent.put("address_info", store.getAddressInfo());
    }
    request.add("content", gson.toJsonTree(updateContent));
    String response = this.wxMaService.postWithSignature(Intracity.UPDATE_STORE_URL, request);
    checkStringResponse(response);
  }

  @Override
  public List<WxMaStore> listAllStores() throws WxErrorException {
    return queryStore(null, null);
  }

  @Override
  public WxMaStore queryStoreByWxStoreId(String wxStoreId) throws WxErrorException {
    List<WxMaStore> list = queryStore(wxStoreId, null);
    return list.isEmpty() ? null : list.get(0);
  }

  @Override
  public List<WxMaStore> queryStoreByOutStoreId(String outStoreId) throws WxErrorException {
    return queryStore(null, outStoreId);
  }

  private List<WxMaStore> queryStore(String wxStoreId, String outStoreId) throws WxErrorException {
    Map<String, String> map = new HashMap<>();
    if (wxStoreId != null) {
      map.put("wx_store_id", wxStoreId);
    } else if (outStoreId != null) {
      map.put("out_store_id", outStoreId);
    }
    String response = this.wxMaService.postWithSignature(Intracity.QUERY_STORE_URL, map);
    JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
    Type listType = new TypeToken<List<WxMaStore>>() {}.getType();
    return gson.fromJson(jsonObject.getAsJsonArray("store_list"), listType);
  }

  @Override
  public String storeCharge(WxMaStoreChargeRequest request) throws WxErrorException {
    String response = this.wxMaService.postWithSignature(Intracity.STORE_CHARGE, request);
    Map<?, ?> map = gson.fromJson(response, Map.class);
    return (String) map.get("payurl");
  }

  @Override
  public int storeRefund(WxMaStoreRefundRequest request) throws WxErrorException {
    String response = this.wxMaService.postWithSignature(Intracity.STORE_REFUND, request);
    Map<?, ?> map = gson.fromJson(response, Map.class);
    return ((Number) map.get("refund_amount")).intValue();
  }

  @Override
  public WxMaStoreFlowResponse<? extends WxMaStoreFlowResponse.BasicFlowRecord> queryFlow(
      WxMaQueryFlowRequest request) throws WxErrorException {
    if (request == null || request.getWxStoreId() == null) {
      throw new IllegalArgumentException("查询请求 wxStoreId 不可为空");
    }
    WxMaStoreFlowResponse<? extends WxMaStoreFlowResponse.BasicFlowRecord> inst =
        getFlowInstanceByType(request.getFlowType());
    if (inst == null) {
      throw new IllegalArgumentException("查询请求 flowType 不正确，只能是1、2、3之一");
    }

    String response = this.wxMaService.postWithSignature(Intracity.QUERY_FLOW, request);

    WxMaStoreFlowResponse<? extends WxMaStoreFlowResponse.BasicFlowRecord> flowResponse;
    flowResponse =
        (WxMaStoreFlowResponse<? extends WxMaStoreFlowResponse.BasicFlowRecord>)
            gson.fromJson(response, inst.getClass());
    logger.debug("queryFlow: {}", flowResponse);
    return flowResponse;
  }

  private WxMaStoreFlowResponse<? extends WxMaStoreFlowResponse.BasicFlowRecord>
      getFlowInstanceByType(int flowType) {
    WxMaStoreFlowResponse<? extends WxMaStoreFlowResponse.BasicFlowRecord> inst;
    if (flowType == 1) {
      inst = new WxMaStoreFlowResponse<WxMaStoreFlowResponse.ChargeFlowRecord>();
    } else if (flowType == 2) {
      inst = new WxMaStoreFlowResponse<WxMaStoreFlowResponse.RefundFlowRecord>();
    } else if (flowType == 3) {
      inst = new WxMaStoreFlowResponse<WxMaStoreFlowResponse.ConsumeFlowRecord>();
    } else {
      return null;
    }
    return inst;
  }

  @Override
  public WxMaStoreBalance balanceQuery(String wxStoreId, String serviceTransId, PayMode payMode)
      throws WxErrorException {
    if (wxStoreId == null && (payMode != null && payMode != PayMode.STORE)) {
      throw new IllegalArgumentException("payMode是PAY_MODE_STORE或null时，必须传递wxStoreId");
    }
    Map<String, Object> request = new HashMap<>();
    if (wxStoreId != null) {
      request.put("wx_store_id", wxStoreId);
    }
    if (serviceTransId != null) {
      request.put("service_trans_id", serviceTransId);
    }
    if (payMode != null) {
      request.put("pay_mode", payMode);
    }
    String response = this.wxMaService.postWithSignature(Intracity.BALANCE_QUERY, request);
    WxMaStoreBalance balance = gson.fromJson(response, WxMaStoreBalance.class);
    logger.debug("balance: {}", balance);
    return balance;
  }

  public void setPayMode(PayMode payMode) throws WxErrorException {
    Map<String, Object> request = new HashMap<>();
    request.put("pay_mode", payMode);
    request.put("appid", wxMaService.getWxMaConfig().getAppid());
    String response = this.wxMaService.postWithSignature(Intracity.SET_PAY_MODE, request);
    checkStringResponse(response);
  }

  public WxMaGetPayModeResponse getPayMode() throws WxErrorException {
    Map<String, Object> request = new HashMap<>();
    request.put("appid", wxMaService.getWxMaConfig().getAppid());
    String response = this.wxMaService.postWithSignature(Intracity.GET_PAY_MODE, request);
    return gson.fromJson(response, WxMaGetPayModeResponse.class);
  }

  @Override
  public WxMaPreAddOrderResponse preAddOrder(WxMaPreAddOrderRequest request)
      throws WxErrorException {
    String response = this.wxMaService.postWithSignature(Intracity.PRE_ADD_ORDER, request);
    return gson.fromJson(response, WxMaPreAddOrderResponse.class);
  }

  @Override
  public WxMaAddOrderResponse addOrder(WxMaAddOrderRequest request) throws WxErrorException {
    String response = this.wxMaService.postWithSignature(Intracity.ADD_ORDER, request);
    return gson.fromJson(response, WxMaAddOrderResponse.class);
  }

  @Override
  public WxMaOrder queryOrderByWxOrderId(String wxOrderId) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("wx_order_id", wxOrderId);
    String response = this.wxMaService.postWithSignature(Intracity.QUERY_ORDER, map);
    return gson.fromJson(response, WxMaOrder.class);
  }

  @Override
  public WxMaOrder queryOrderByStoreOrderId(String wxStoreId, String storeOrderId)
      throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("wx_store_id", wxStoreId);
    map.put("store_order_id", storeOrderId);
    String response = this.wxMaService.postWithSignature(Intracity.QUERY_ORDER, map);
    return gson.fromJson(response, WxMaOrder.class);
  }

  @Override
  public WxMaCancelOrderResponse cancelOrderByWxOrderId(
      String wxOrderId, int cancelReasonId, String cancelReason) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("wx_order_id", wxOrderId);
    map.put("cancel_reason_id", cancelReasonId);
    if (cancelReason != null) {
      map.put("cancel_reason", cancelReason);
    }
    String response = this.wxMaService.postWithSignature(Intracity.CANCEL_ORDER, map);
    return gson.fromJson(response, WxMaCancelOrderResponse.class);
  }

  @Override
  public WxMaCancelOrderResponse cancelOrderByStoreOrderId(
      String wxStoreId, String storeOrderId, int cancelReasonId, String cancelReason)
      throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("wx_store_id", wxStoreId);
    map.put("store_order_id", storeOrderId);
    map.put("cancel_reason_id", cancelReasonId);
    if (cancelReason != null) {
      map.put("cancel_reason", cancelReason);
    }
    String response = this.wxMaService.postWithSignature(Intracity.CANCEL_ORDER, map);
    return gson.fromJson(response, WxMaCancelOrderResponse.class);
  }

  @Override
  public List<WxMaTransCity> getCity(String serviceTransId) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    if (serviceTransId != null) {
      map.put("service_trans_id", serviceTransId);
    }
    String response = this.wxMaService.postWithSignature(Intracity.GET_CITY, map);
    JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
    Type listType = new TypeToken<List<WxMaTransCity>>() {}.getType();
    return gson.fromJson(jsonObject.getAsJsonArray("support_list"), listType);
  }
}
