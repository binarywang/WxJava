package me.chanjar.weixin.channel.api.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxLeadComponentService;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadInfoByComponentRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentPromoteRecordRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsInfoByRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentPromoteRecordResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsRequestIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.LeadInfoResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.ObjectUtils;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_COMPONENT_ID;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_COMPONENT_PROMOTE_RECORD;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_INFO_BY_COMPONENT_ID;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_INFO_BY_REQUEST_ID;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_REQUEST_ID;

/**
 * 视频号助手 留资组件管理服务
 * @author imyzt
 * @date 2024/01/27
 */
@RequiredArgsConstructor
@Slf4j
public class WxLeadComponentServiceImpl implements WxLeadComponentService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Override
  public LeadInfoResponse getLeadsInfoByComponentId(GetLeadInfoByComponentRequest req) throws WxErrorException {
    req.setVersion(ObjectUtils.defaultIfNull(req.getVersion(), 1));
    String resJson = shopService.post(GET_LEADS_INFO_BY_COMPONENT_ID, req);
    return this.convertLeadInfoResponse(resJson);
  }

  @Override
  public LeadInfoResponse getLeadsInfoByRequestId(GetLeadsInfoByRequestIdRequest req) throws WxErrorException {
    req.setVersion(ObjectUtils.defaultIfNull(req.getVersion(), 1));
    String resJson = shopService.post(GET_LEADS_INFO_BY_REQUEST_ID, req);
    return this.convertLeadInfoResponse(resJson);
  }

  @Override
  public GetLeadsRequestIdResponse getLeadsRequestId(GetLeadsRequestIdRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_REQUEST_ID, req);
    return ResponseUtils.decode(resJson, GetLeadsRequestIdResponse.class);
  }

  @Override
  public GetLeadsComponentPromoteRecordResponse getLeadsComponentPromoteRecord(GetLeadsComponentPromoteRecordRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_COMPONENT_PROMOTE_RECORD, req);
    return ResponseUtils.decode(resJson, GetLeadsComponentPromoteRecordResponse.class);
  }

  @Override
  public GetLeadsComponentIdResponse getLeadsComponentId(GetLeadsComponentIdRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_COMPONENT_ID, req);
    return ResponseUtils.decode(resJson, GetLeadsComponentIdResponse.class);
  }

  /**
   * 微信返回的数据中, user_data和leads_data均为字符串包裹的非标准JSON结构, 为方便业务使用避免踩坑此处做好解析
   */
  private LeadInfoResponse convertLeadInfoResponse(String resJson) throws WxErrorException {
    try {
      ObjectNode rootNode = (ObjectNode) objectMapper.readTree(resJson);
      ArrayNode convertedUserDataArray = objectMapper.createArrayNode();
      for (JsonNode userDataEle : rootNode.get("user_data")) {
        ObjectNode userDataJsonNode = (ObjectNode) objectMapper.readTree(userDataEle.asText());
        ArrayNode leadsDataArray = (ArrayNode) objectMapper.readTree(userDataJsonNode.get("leads_data").asText());
        userDataJsonNode.set("leads_data", leadsDataArray);
        convertedUserDataArray.add(userDataJsonNode);
      }
      rootNode.set("user_data", convertedUserDataArray);
      String json = objectMapper.writeValueAsString(rootNode);
      return ResponseUtils.decode(json, LeadInfoResponse.class);
    } catch (JsonProcessingException e) {
      throw new WxErrorException(e);
    }
  }

}
