package com.binarywang.wxjava.store.common;

import com.binarywang.wxjava.store.enums.WxStoreErrorMsgEnum;
import me.chanjar.weixin.common.error.WxError;

/**
 * 微信小店错误码
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请使用 {@link me.chanjar.weixin.common.error.WxError} 替代
 */
@Deprecated
public class StoreWxError extends WxError {

  private static final long serialVersionUID = -2638512715814977441L;

  public StoreWxError() {
  }

  public StoreWxError(int errorCode, String errorMsgEn) {
    super(errorCode, errorMsgEn);
    if (WxStoreErrorMsgEnum.findMsgByCode(errorCode) != null) {
      this.setErrorMsg(WxStoreErrorMsgEnum.findMsgByCode(errorCode));
    }
    this.setErrorMsgEn(errorMsgEn);
  }
}
