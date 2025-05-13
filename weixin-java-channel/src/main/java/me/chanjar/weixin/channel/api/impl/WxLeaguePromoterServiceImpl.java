package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.ADD_PROMOTER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.DELETE_PROMOTER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.EDIT_PROMOTER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_PROMOTER_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_PROMOTER_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxLeaguePromoterService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.promoter.PromoterInfoResponse;
import me.chanjar.weixin.channel.bean.league.promoter.PromoterListParam;
import me.chanjar.weixin.channel.bean.league.promoter.PromoterListResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 达人服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxLeaguePromoterServiceImpl implements WxLeaguePromoterService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxLeaguePromoterServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public WxChannelBaseResponse addPromoter(String finderId) throws WxErrorException {
    String reqJson = "{\"finder_id\":\"" + finderId + "\"}";
    String resJson = shopService.post(ADD_PROMOTER_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse updatePromoter(String finderId, int type) throws WxErrorException {
    String reqJson = "{\"finder_id\":\"" + finderId + "\",\"type\":" + type + "}";
    String resJson = shopService.post(EDIT_PROMOTER_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse deletePromoter(String finderId) throws WxErrorException {
    String reqJson = "{\"finder_id\":\"" + finderId + "\"}";
    String resJson = shopService.post(DELETE_PROMOTER_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public PromoterInfoResponse getPromoterInfo(String finderId) throws WxErrorException {
    String reqJson = "{\"finder_id\":\"" + finderId + "\"}";
    String resJson = shopService.post(GET_PROMOTER_URL, reqJson);
    return ResponseUtils.decode(resJson, PromoterInfoResponse.class);
  }

  @Override
  public PromoterListResponse listPromoter(Integer pageIndex, Integer pageSize, Integer status)
    throws WxErrorException {
    PromoterListParam param = new PromoterListParam(pageIndex, pageSize, status);
    String resJson = shopService.post(GET_PROMOTER_LIST_URL, param);
    return ResponseUtils.decode(resJson, PromoterListResponse.class);

  }
}
