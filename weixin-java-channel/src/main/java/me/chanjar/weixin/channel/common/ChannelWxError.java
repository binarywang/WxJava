package me.chanjar.weixin.channel.common;

import me.chanjar.weixin.channel.enums.WxChannelErrorMsgEnum;
import me.chanjar.weixin.common.error.WxError;

/**
 * 微信视频号错误码
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请使用 {@link me.chanjar.weixin.common.error.WxError} 替代
 */
@Deprecated
public class ChannelWxError extends WxError {

  private static final long serialVersionUID = -2638512715814977441L;

  public ChannelWxError() {
  }

  public ChannelWxError(int errorCode, String errorMsgEn) {
    super(errorCode, errorMsgEn);
    if (WxChannelErrorMsgEnum.findMsgByCode(errorCode) != null) {
      this.setErrorMsg(WxChannelErrorMsgEnum.findMsgByCode(errorCode));
    }
    this.setErrorMsgEn(errorMsgEn);
  }
}
