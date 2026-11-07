package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Cooperation.CANCEL_COOPERATION_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Cooperation.GENERATE_QRCODE_COOPERATION_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Cooperation.GET_COOPERATION_STATUS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Cooperation.LIST_COOPERATION_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Cooperation.UNBIND_COOPERATION_URL;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreCooperationService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.cooperation.CooperationListResponse;
import com.binarywang.wxjava.store.bean.cooperation.CooperationQrCodeResponse;
import com.binarywang.wxjava.store.bean.cooperation.CooperationSharerParam;
import com.binarywang.wxjava.store.bean.cooperation.CooperationStatusResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 合作账号相关接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreCooperationServiceImpl implements WxStoreCooperationService {

  /** 微信小店服务 */
  private final BaseWxStoreServiceImpl<?, ?> storeService;

  public WxStoreCooperationServiceImpl(BaseWxStoreServiceImpl<?, ?> storeService) {
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
  public WxStoreBaseResponse cancelInvitation(String sharerId, Integer sharerType) throws WxErrorException {
    CooperationSharerParam param = new CooperationSharerParam(sharerId, sharerType);
    String resJson = storeService.post(CANCEL_COOPERATION_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse unbind(String sharerId, Integer sharerType) throws WxErrorException {
    CooperationSharerParam param = new CooperationSharerParam(sharerId, sharerType);
    String resJson = storeService.post(UNBIND_COOPERATION_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }
}
