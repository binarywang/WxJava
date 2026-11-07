package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskAddResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskListResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.bean.limit.LimitTaskUpdateParam;
import me.chanjar.weixin.channel.bean.limit.LimitTaskUpdateResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店限时抢购服务。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreLimitedDiscountService}。
 */
@Deprecated
public interface WxChannelLimitedDiscountService {

  /**
   * 添加限时抢购任务。
   *
   * @param param 限时抢购任务
   * @return 添加任务响应
   * @throws WxErrorException 异常
   */
  LimitTaskAddResponse addLimitTask(LimitTaskParam param) throws WxErrorException;

  /**
   * 拉取限时抢购任务列表。
   *
   * @param pageSize 每页数量
   * @param nextKey 翻页上下文
   * @param status 抢购活动状态
   * @return 任务列表响应
   * @throws WxErrorException 异常
   */
  LimitTaskListResponse listLimitTask(Integer pageSize, String nextKey, Integer status) throws WxErrorException;

  /**
   * 停止限时抢购任务。
   *
   * @param taskId 限时抢购任务ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse stopLimitTask(String taskId) throws WxErrorException;

  /**
   * 删除限时抢购任务。
   *
   * @param taskId 限时抢购任务ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse deleteLimitTask(String taskId) throws WxErrorException;

  /**
   * 更新限时抢购任务。
   *
   * @param param 更新任务参数
   * @return 更新任务响应
   * @throws WxErrorException 异常
   */
  LimitTaskUpdateResponse updateLimitTask(LimitTaskUpdateParam param) throws WxErrorException;
}
