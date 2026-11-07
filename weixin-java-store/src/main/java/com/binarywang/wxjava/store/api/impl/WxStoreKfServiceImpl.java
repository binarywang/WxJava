package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Kf.COS_UPLOAD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Kf.SEND_MSG_URL;

import com.binarywang.wxjava.store.api.WxStoreKfService;
import com.binarywang.wxjava.store.bean.kf.WxStoreKfCosUploadResponse;
import com.binarywang.wxjava.store.bean.kf.WxStoreKfSendMsgParam;
import com.binarywang.wxjava.store.bean.kf.WxStoreKfSendMsgResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;

/** 微信小店商家客服服务实现。 */
public class WxStoreKfServiceImpl implements WxStoreKfService {

  private final BaseWxStoreServiceImpl<?, ?> channelService;

  public WxStoreKfServiceImpl(BaseWxStoreServiceImpl<?, ?> channelService) {
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
    return ResponseUtils.decode(responseJson, WxStoreKfCosUploadResponse.class).getCosUrl();
  }

  @Override
  public WxStoreKfSendMsgResponse sendMessage(WxStoreKfSendMsgParam param) throws WxErrorException {
    String responseJson = channelService.executeWithoutLog(SimplePostRequestExecutor.create(channelService), SEND_MSG_URL,
      JsonUtils.encode(param));
    return ResponseUtils.decode(responseJson, WxStoreKfSendMsgResponse.class);
  }
}
