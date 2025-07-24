package me.chanjar.weixin.channel.api.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxFinderLiveService;
import me.chanjar.weixin.channel.bean.lead.component.request.GetFinderLiveDataListRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetFinderLiveLeadsDataRequest;
import me.chanjar.weixin.channel.bean.lead.component.response.FinderAttrResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetFinderLiveDataListResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetFinderLiveLeadsDataResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.FinderLive.GET_FINDER_ATTR_BY_APPID;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.FinderLive.GET_FINDER_LIVE_DATA_LIST;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.FinderLive.GET_FINDER_LIVE_LEADS_DATA;

/**
 * 视频号助手 留资服务的直播数据服务
 * @author imyzt
 * @date 2024/01/27
 */
@RequiredArgsConstructor
@Slf4j
public class WxFinderLiveServiceImpl implements WxFinderLiveService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;
  @Override
  public FinderAttrResponse getFinderAttrByAppid() throws WxErrorException {
    String resJson = shopService.post(GET_FINDER_ATTR_BY_APPID, "{}");
    return ResponseUtils.decode(resJson, FinderAttrResponse.class);
  }

  @Override
  public GetFinderLiveDataListResponse getFinderLiveDataList(GetFinderLiveDataListRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_FINDER_LIVE_DATA_LIST, req);
    return ResponseUtils.decode(resJson, GetFinderLiveDataListResponse.class);
  }

  @Override
  public GetFinderLiveLeadsDataResponse getFinderLiveLeadsData(GetFinderLiveLeadsDataRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_FINDER_LIVE_LEADS_DATA, req);
    return ResponseUtils.decode(resJson, GetFinderLiveLeadsDataResponse.class);
  }
}
