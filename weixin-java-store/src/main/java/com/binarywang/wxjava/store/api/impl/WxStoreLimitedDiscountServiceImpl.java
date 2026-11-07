package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.ADD_LIMIT_TASK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.DELETE_LIMIT_TASK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.LIST_LIMIT_TASK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.STOP_LIMIT_TASK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.UPDATE_LIMIT_TASK_URL;

import com.binarywang.wxjava.store.api.WxStoreLimitedDiscountService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.limit.LimitTaskAddResponse;
import com.binarywang.wxjava.store.bean.limit.LimitTaskListParam;
import com.binarywang.wxjava.store.bean.limit.LimitTaskListResponse;
import com.binarywang.wxjava.store.bean.limit.LimitTaskParam;
import com.binarywang.wxjava.store.bean.limit.LimitTaskUpdateParam;
import com.binarywang.wxjava.store.bean.limit.LimitTaskUpdateResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店限时抢购服务实现。
 */
public class WxStoreLimitedDiscountServiceImpl implements WxStoreLimitedDiscountService {

  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreLimitedDiscountServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
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
  public WxStoreBaseResponse stopLimitTask(String taskId) throws WxErrorException {
    String reqJson = "{\"task_id\": \"" + taskId + "\"}";
    String resJson = shopService.post(STOP_LIMIT_TASK_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse deleteLimitTask(String taskId) throws WxErrorException {
    String reqJson = "{\"task_id\": \"" + taskId + "\"}";
    String resJson = shopService.post(DELETE_LIMIT_TASK_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public LimitTaskUpdateResponse updateLimitTask(LimitTaskUpdateParam param) throws WxErrorException {
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(UPDATE_LIMIT_TASK_URL, reqJson);
    return ResponseUtils.decode(resJson, LimitTaskUpdateResponse.class);
  }
}
