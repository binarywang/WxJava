package me.chanjar.weixin.channel.api.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.chanjar.weixin.channel.api.WxChannelQicService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.qic.InspectCodeResponse;
import me.chanjar.weixin.channel.bean.qic.InspectConfigResponse;
import me.chanjar.weixin.channel.bean.qic.RegisterLogisticsRequest;
import me.chanjar.weixin.channel.bean.qic.SubmitConfigResponse;
import me.chanjar.weixin.channel.bean.qic.SubmitInspectRequest;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Qic.GET_INSPECT_CONFIG_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Qic.GET_SUBMIT_CONFIG_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Qic.PRINT_INSPECT_CODE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Qic.REGISTER_LOGISTICS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Qic.SUBMIT_INSPECT_INFO_URL;

/**
 * 视频号小店 质检管理服务实现.
 */
public class WxChannelQicServiceImpl implements WxChannelQicService {
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelQicServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public InspectConfigResponse getInspectConfig() throws WxErrorException {
    String respJson = shopService.get(GET_INSPECT_CONFIG_URL, null);
    return ResponseUtils.decode(respJson, InspectConfigResponse.class);
  }

  @Override
  public SubmitConfigResponse getSubmitConfig(String orderId) throws WxErrorException {
    String queryParam = StringUtils.isBlank(orderId) ? "" : "order_id=" + orderId;
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
  public WxChannelBaseResponse submitInspectInfo(SubmitInspectRequest request) throws WxErrorException {
    String respJson = shopService.post(SUBMIT_INSPECT_INFO_URL, request);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse registerLogistics(RegisterLogisticsRequest request) throws WxErrorException {
    String respJson = shopService.post(REGISTER_LOGISTICS_URL, request);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Data
  @AllArgsConstructor
  private static class PrintInspectCodeRequest {
    @JsonProperty("order_id")
    private String orderId;
  }
}
