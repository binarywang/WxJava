package me.chanjar.weixin.channel.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 提现提交响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class WithdrawSubmitResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -8269579250564427758L;
  /** 二维码ticket,可用于获取二维码和查询二维码状态 */
  @JsonProperty("qrcode_ticket")
  private String qrcodeTicket;
}
