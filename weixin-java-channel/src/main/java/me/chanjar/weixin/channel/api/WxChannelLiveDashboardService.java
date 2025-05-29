package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.live.dashboard.LiveDataResponse;
import me.chanjar.weixin.channel.bean.live.dashboard.LiveListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号助手 直播大屏数据服务
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
public interface WxChannelLiveDashboardService {

  /**
   * 获取直播大屏直播列表
   *
   * @param ds 日期，格式 yyyyMMdd
   * @return 播大屏直播列表
   *
   * @throws WxErrorException 异常
   */
  LiveListResponse getLiveList(Long ds) throws WxErrorException;

  /**
   * 获取直播大屏数据
   *
   * @param exportId 直播唯一ID
   * @return 播大屏数据
   *
   * @throws WxErrorException 异常
   */
  LiveDataResponse getLiveData(String exportId) throws WxErrorException;

}
