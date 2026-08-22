package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.ADD_LIMIT_TASK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.DELETE_LIMIT_TASK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.LIST_LIMIT_TASK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.STOP_LIMIT_TASK_URL;

import me.chanjar.weixin.channel.api.WxChannelLimitedDiscountService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskAddResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskListParam;
import me.chanjar.weixin.channel.bean.limit.LimitTaskListResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店限时抢购服务实现。
 */
public class WxChannelLimitedDiscountServiceImpl implements WxChannelLimitedDiscountService {

  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelLimitedDiscountServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public LimitTaskAddResponse addLimitTask(LimitTaskParam param) throws WxErrorException {
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(ADD_LIMIT_TASK_URL, reqJson);
    return ResponseUtils.decode(resJson, LimitTaskAddResponse.class);
  }

  @Override
  public LimitTaskListResponse listLimitTask(Integer pageSize, String nextKey, Integer status)
    throws WxErrorException {
    LimitTaskListParam param = new LimitTaskListParam(pageSize, nextKey, status);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(LIST_LIMIT_TASK_URL, reqJson);
    return ResponseUtils.decode(resJson, LimitTaskListResponse.class);
  }

  @Override
  public WxChannelBaseResponse stopLimitTask(String taskId) throws WxErrorException {
    String reqJson = "{\"task_id\": \"" + taskId + "\"}";
    String resJson = shopService.post(STOP_LIMIT_TASK_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteLimitTask(String taskId) throws WxErrorException {
    String reqJson = "{\"task_id\": \"" + taskId + "\"}";
    String resJson = shopService.post(DELETE_LIMIT_TASK_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
