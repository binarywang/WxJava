package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgParam;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店商家客服服务。
 *
 * @deprecated 请迁移至 {@code com.binarywang.wxjava.store.api.WxStoreKfService}。
 */
@Deprecated
public interface WxChannelKfService {

  /**
   * 上传多媒体资源。
   *
   * @param openId 用户 open_id
   * @param msgType 文件类型，仅支持 video、file、image
   * @param file 文件字节内容
   * @return COS 地址
   * @throws WxErrorException 微信异常
   */
  String uploadMedia(String openId, String msgType, byte[] file) throws WxErrorException;

  /**
   * 上传多媒体资源。
   *
   * @param openId 用户 open_id
   * @param msgType 文件类型，仅支持 video、file、image
   * @param fileName 文件名
   * @param file 文件字节内容
   * @return COS 地址
   * @throws WxErrorException 微信异常
   */
  String uploadMedia(String openId, String msgType, String fileName, byte[] file) throws WxErrorException;

  /**
   * 发送客服消息。
   *
   * @param param 请求参数
   * @return 发送结果
   * @throws WxErrorException 微信异常
   */
  WxChannelKfSendMsgResponse sendMessage(WxChannelKfSendMsgParam param) throws WxErrorException;
}
