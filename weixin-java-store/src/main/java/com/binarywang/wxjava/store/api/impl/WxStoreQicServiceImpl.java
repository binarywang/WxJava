package com.binarywang.wxjava.store.api.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.binarywang.wxjava.store.api.WxStoreQicService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.qic.InspectCodeResponse;
import com.binarywang.wxjava.store.bean.qic.InspectConfigResponse;
import com.binarywang.wxjava.store.bean.qic.RegisterLogisticsRequest;
import com.binarywang.wxjava.store.bean.qic.SubmitConfigResponse;
import com.binarywang.wxjava.store.bean.qic.SubmitInspectRequest;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Qic.GET_INSPECT_CONFIG_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Qic.GET_SUBMIT_CONFIG_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Qic.PRINT_INSPECT_CODE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Qic.REGISTER_LOGISTICS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Qic.SUBMIT_INSPECT_INFO_URL;

/**
 * 微信小店 质检管理服务实现.
 */
public class WxStoreQicServiceImpl implements WxStoreQicService {
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreQicServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public InspectConfigResponse getInspectConfig() throws WxErrorException {
    String respJson = shopService.get(GET_INSPECT_CONFIG_URL, null);
    return ResponseUtils.decode(respJson, InspectConfigResponse.class);
  }

  @Override
  public SubmitConfigResponse getSubmitConfig(String orderId) throws WxErrorException {
    String queryParam = StringUtils.isBlank(orderId) ? null : "order_id=" + orderId;
    String respJson = shopService.get(GET_SUBMIT_CONFIG_URL, queryParam);
    return ResponseUtils.decode(respJson, SubmitConfigResponse.class);
  }

  @Override
  public SubmitConfigResponse getSubmitConfig() throws WxErrorException {
    return getSubmitConfig(null);
  }

  @Override
  public InspectCodeResponse printInspectCode(String orderId) throws WxErrorException {
    String respJson = shopService.post(PRINT_INSPECT_CODE_URL, new PrintInspectCodeRequest(orderId));
    return ResponseUtils.decode(respJson, InspectCodeResponse.class);
  }

  @Override
  public WxStoreBaseResponse submitInspectInfo(SubmitInspectRequest request) throws WxErrorException {
    String respJson = shopService.post(SUBMIT_INSPECT_INFO_URL, request);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse registerLogistics(RegisterLogisticsRequest request) throws WxErrorException {
    String respJson = shopService.post(REGISTER_LOGISTICS_URL, request);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
  }

  @Data
  @AllArgsConstructor
  private static class PrintInspectCodeRequest {
    @JsonProperty("order_id")
    private String orderId;
  }
}
