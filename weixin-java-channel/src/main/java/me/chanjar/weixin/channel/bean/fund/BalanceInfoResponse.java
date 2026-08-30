package me.chanjar.weixin.channel.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 账户余额信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class BalanceInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4480496860612566921L;
  /** 可提现余额 */
  @JsonProperty("available_amount")
  private Integer availableAmount;

  /** 待结算余额 */
  @JsonProperty("pending_amount")
  private Integer pendingAmount;

  /** 二级商户号 */
  @JsonProperty("sub_mchid")
  private String subMchid;

}
