package com.binarywang.wxjava.store.api.impl;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreVipService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.vip.*;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Vip.*;

/**
 * 微信小店 会员功能接口
 *
 * @author <a href="https://github.com/asushiye">aushiye</a>
 * @link <a href="https://developers.weixin.qq.com/doc/channels/API/vip/access_guide.html">会员功能接口文档</a>
 */

@Slf4j
public class WxStoreVipServiceImpl implements WxStoreVipService {
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreVipServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
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
  public WxStoreBaseResponse increaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException {
    VipScoreParam param = new VipScoreParam(openId, score, remark, requestId);
    String respJson = shopService.post(SCORE_INCREASE_URL, param);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse decreaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException {
    VipScoreParam param = new VipScoreParam(openId, score, remark, requestId);
    String respJson = shopService.post(SCORE_DECREASE_URL, param);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateVipGrade(String openId, Integer score) throws WxErrorException {
    VipGradeParam param = new VipGradeParam(openId, score);
    String respJson = shopService.post(GRADE_UPDATE_URL, param);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
  }
}
