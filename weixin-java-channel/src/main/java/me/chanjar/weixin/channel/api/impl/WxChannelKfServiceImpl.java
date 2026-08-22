package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Kf.COS_UPLOAD_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Kf.SEND_MSG_URL;

import me.chanjar.weixin.channel.api.WxChannelKfService;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfCosUploadResponse;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgParam;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxErrorException;

/** 视频号小店商家客服服务实现。 */
public class WxChannelKfServiceImpl implements WxChannelKfService {

  private final BaseWxChannelServiceImpl<?, ?> channelService;

  public WxChannelKfServiceImpl(BaseWxChannelServiceImpl<?, ?> channelService) {
    this.channelService = channelService;
  }

  @Override
  public String uploadMedia(String openId, String msgType, byte[] file) throws WxErrorException {
    return uploadMedia(openId, msgType, null, file);
  }

  @Override
  public String uploadMedia(String openId, String msgType, String fileName, byte[] file) throws WxErrorException {
    CommonUploadParam uploadParam = CommonUploadParam.fromBytes("file", fileName, file)
      .addFormField("open_id", openId)
      .addFormField("msg_type", msgType);
    String responseJson = channelService.upload(COS_UPLOAD_URL, uploadParam);
    return ResponseUtils.decode(responseJson, WxChannelKfCosUploadResponse.class).getCosUrl();
  }

  @Override
  public WxChannelKfSendMsgResponse sendMessage(WxChannelKfSendMsgParam param) throws WxErrorException {
    String responseJson = channelService.post(SEND_MSG_URL, param);
    return ResponseUtils.decode(responseJson, WxChannelKfSendMsgResponse.class);
  }
}
