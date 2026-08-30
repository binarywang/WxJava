package me.chanjar.weixin.channel.bean.fund.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 二维码校验响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class QrCheckResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -3860756719827268969L;
  /** 扫码状态 {@link me.chanjar.weixin.channel.enums.QrCheckStatus} */
  @JsonProperty("status")
  private Integer status;

  /** 业务返回错误码 */
  @JsonProperty("self_check_err_code")
  private Integer selfCheckErrCode;

  /** 业务返回错误信息 */
  @JsonProperty("self_check_err_msg")
  private String selfCheckErrMsg;

  /** 扫码者身份 0非管理员 1管理员 2次管理员 */
  @JsonProperty("scan_user_type")
  private Integer scanUserType;

}
