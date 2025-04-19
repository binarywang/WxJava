package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Cooperation.CANCEL_COOPERATION_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Cooperation.GENERATE_QRCODE_COOPERATION_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Cooperation.GET_COOPERATION_STATUS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Cooperation.LIST_COOPERATION_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Cooperation.UNBIND_COOPERATION_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxStoreCooperationService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.cooperation.CooperationListResponse;
import me.chanjar.weixin.channel.bean.cooperation.CooperationQrCodeResponse;
import me.chanjar.weixin.channel.bean.cooperation.CooperationSharerParam;
import me.chanjar.weixin.channel.bean.cooperation.CooperationStatusResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 合作账号相关接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreCooperationServiceImpl implements WxStoreCooperationService {

  /** 微信小店服务 */
  private final BaseWxChannelServiceImpl storeService;

  public WxStoreCooperationServiceImpl(BaseWxChannelServiceImpl storeService) {
    this.storeService = storeService;
  }

  @Override
  public CooperationListResponse listCooperation(Integer sharerType) throws WxErrorException {
    String paramJson = "{\"sharer_type\":" + sharerType + "}";
    String resJson = storeService.post(LIST_COOPERATION_URL, paramJson);
    return ResponseUtils.decode(resJson, CooperationListResponse.class);
  }

  @Override
  public CooperationStatusResponse getCooperationStatus(String sharerId, Integer sharerType) throws WxErrorException {
    CooperationSharerParam param = new CooperationSharerParam(sharerId, sharerType);
    String resJson = storeService.post(GET_COOPERATION_STATUS_URL, param);
    return ResponseUtils.decode(resJson, CooperationStatusResponse.class);
  }

  @Override
  public CooperationQrCodeResponse generateQrCode(String sharerId, Integer sharerType) throws WxErrorException {
    CooperationSharerParam param = new CooperationSharerParam(sharerId, sharerType);
    String resJson = storeService.post(GENERATE_QRCODE_COOPERATION_URL, param);
    return ResponseUtils.decode(resJson, CooperationQrCodeResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelInvitation(String sharerId, Integer sharerType) throws WxErrorException {
    CooperationSharerParam param = new CooperationSharerParam(sharerId, sharerType);
    String resJson = storeService.post(CANCEL_COOPERATION_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse unbind(String sharerId, Integer sharerType) throws WxErrorException {
    CooperationSharerParam param = new CooperationSharerParam(sharerId, sharerType);
    String resJson = storeService.post(UNBIND_COOPERATION_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
