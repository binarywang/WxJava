package me.chanjar.weixin.channel.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelVipService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.vip.*;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Vip.*;

/**
 * 视频号小店 会员功能接口
 *
 * @author <a href="https://github.com/asushiye">aushiye</a>
 * @link <a href="https://developers.weixin.qq.com/doc/channels/API/vip/access_guide.html">会员功能接口文档</a>
 */

@Slf4j
public class WxChannelVipServiceImpl implements WxChannelVipService {
  private BaseWxChannelServiceImpl shopService;

  public WxChannelVipServiceImpl(BaseWxChannelServiceImpl shopService) {
    this.shopService = shopService;
  }

  @Override
  public VipInfoResponse getVipInfo(String openId, Boolean needPhoneNumber) throws WxErrorException {
    VipInfoParam param = new VipInfoParam(openId, needPhoneNumber);
    String respJson = shopService.post(VIP_USER_INFO_URL, param);
    return ResponseUtils.decode(respJson, VipInfoResponse.class);
  }

  @Override
  public VipListResponse getVipList(Boolean needPhoneNumber, Integer pageNum, Integer pageSize) throws WxErrorException {
    VipListParam param = new VipListParam(needPhoneNumber, pageNum, pageSize);
    String respJson = shopService.post(VIP_USER_LIST_URL, param);
    return ResponseUtils.decode(respJson, VipListResponse.class);
  }

  @Override
  public VipScoreResponse getVipScore(String openId) throws WxErrorException {
    VipOpenIdParam param = new VipOpenIdParam(openId);
    String respJson = shopService.post(VIP_SCORE_URL, param);
    return ResponseUtils.decode(respJson, VipScoreResponse.class);
  }

  @Override
  public WxChannelBaseResponse increaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException {
    VipScoreParam param = new VipScoreParam(openId, score, remark, requestId);
    String respJson = shopService.post(SCORE_INCREASE_URL, param);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse decreaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException {
    VipScoreParam param = new VipScoreParam(openId, score, remark, requestId);
    String respJson = shopService.post(SCORE_DECREASE_URL, param);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateVipGrade(String openId, Integer score) throws WxErrorException {
    VipGradeParam param = new VipGradeParam(openId, score);
    String respJson = shopService.post(GRADE_UPDATE_URL, param);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }
}
