package me.chanjar.weixin.channel.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 提现详情响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class WithdrawDetailResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1473346677401168323L;
  /** 金额 */
  @JsonProperty("amount")
  private Integer amount;

  /** 创建时间 */
  @JsonProperty("create_time")
  private Long createTime;

  /** 更新时间 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 失败原因 */
  @JsonProperty("reason")
  private String reason;

  /** 备注 */
  @JsonProperty("remark")
  private String remark;

  /** 银行附言 */
  @JsonProperty("bank_memo")
  private String bankMemo;

  /** 银行名称 */
  @JsonProperty("bank_name")
  private String bankName;

  /** 银行账户 */
  @JsonProperty("bank_num")
  private String bankNum;

  /** 提现状态 {@link me.chanjar.weixin.channel.enums.WithdrawStatus} */
  @JsonProperty("status")
  private String status;
}
