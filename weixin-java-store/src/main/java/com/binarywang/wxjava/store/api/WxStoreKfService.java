package com.binarywang.wxjava.store.api;

import com.binarywang.wxjava.store.bean.kf.WxStoreKfSendMsgParam;
import com.binarywang.wxjava.store.bean.kf.WxStoreKfSendMsgResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/** 微信小店商家客服服务。 */
public interface WxStoreKfService {

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
  WxStoreKfSendMsgResponse sendMessage(WxStoreKfSendMsgParam param) throws WxErrorException;
}
