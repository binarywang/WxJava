package me.chanjar.weixin.channel.bean.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 视频号小店 基础响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse}。
 */
@Deprecated
public class WxChannelBaseResponse implements Serializable {

  private static final long serialVersionUID = 3141420881984171781L;

  /** 请求成功状态码 */
  public static final int SUCCESS_CODE = 0;
  public static final int INTERNAL_ERROR_CODE = -99;

  /**
   * 错误码
   */
  @JsonProperty("errcode")
  protected int errCode;

  /**
   * 错误消息
   */
  @JsonProperty("errmsg")
  protected String errMsg;

  /**
   * 错误代码 + 错误消息
   *
   * @return String
   */
  public String errorMessage() {
    return "errcode: " + errCode + ", errmsg: " + errMsg;
  }

  public boolean isSuccess() {
    return errCode == SUCCESS_CODE;
  }

  public int getErrCode() {
    return errCode;
  }

  public void setErrCode(int errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", WxChannelBaseResponse.class.getSimpleName() + "[", "]")
      .add("errCode=" + errCode)
      .add("errMsg='" + errMsg + "'")
      .toString();
  }
}
