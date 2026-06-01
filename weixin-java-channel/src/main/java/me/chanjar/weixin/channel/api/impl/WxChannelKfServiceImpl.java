package me.chanjar.weixin.channel.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelKfService;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfCosUploadResponse;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgParam;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Kf.COS_UPLOAD_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Kf.SEND_MSG_URL;

/**
 * 视频号小店 商家客服服务实现
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Slf4j
public class WxChannelKfServiceImpl implements WxChannelKfService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelKfServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
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
    String resJson = shopService.upload(COS_UPLOAD_URL, uploadParam);
    WxChannelKfCosUploadResponse response = ResponseUtils.decode(resJson, WxChannelKfCosUploadResponse.class);
    return response.getCosUrl();
  }

  @Override
  public WxChannelKfSendMsgResponse sendMessage(WxChannelKfSendMsgParam param) throws WxErrorException {
    String resJson = shopService.post(SEND_MSG_URL, param);
    return ResponseUtils.decode(resJson, WxChannelKfSendMsgResponse.class);
  }
}
