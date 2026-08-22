package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.qic.InspectCodeResponse;
import me.chanjar.weixin.channel.bean.qic.InspectConfigResponse;
import me.chanjar.weixin.channel.bean.qic.RegisterLogisticsRequest;
import me.chanjar.weixin.channel.bean.qic.SubmitConfigResponse;
import me.chanjar.weixin.channel.bean.qic.SubmitInspectRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 质检管理接口.
 */
public interface WxChannelQicService {

  /**
   * 查询质检仓配置.
   *
   * @return 质检仓配置
   * @throws WxErrorException 异常
   */
  InspectConfigResponse getInspectConfig() throws WxErrorException;

  /**
   * 查询送检配置模板信息.
   *
   * @param orderId 订单号（可选）
   * @return 送检配置模板信息
   * @throws WxErrorException 异常
   */
  SubmitConfigResponse getSubmitConfig(String orderId) throws WxErrorException;

  /**
   * 查询送检配置模板信息.
   *
   * @return 送检配置模板信息
   * @throws WxErrorException 异常
   */
  SubmitConfigResponse getSubmitConfig() throws WxErrorException;

  /**
   * 打印质检码.
   *
   * @param orderId 订单号
   * @return 质检码详情
   * @throws WxErrorException 异常
   */
  InspectCodeResponse printInspectCode(String orderId) throws WxErrorException;

  /**
   * 绑定送检信息.
   *
   * @param request 送检信息请求
   * @return 基础响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse submitInspectInfo(SubmitInspectRequest request) throws WxErrorException;

  /**
   * 自寄快递送检.
   *
   * @param request 自寄快递请求
   * @return 基础响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse registerLogistics(RegisterLogisticsRequest request) throws WxErrorException;
}
