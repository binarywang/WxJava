package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 查看订单真实号审核状态 响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class RealNumberViewAuditResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1L;

  /**
   * 审核状态：1-审核中，2-审核通过，3-审核拒绝
   */
  @JsonProperty("audit_status")
  private Integer auditStatus;

  /** 真实号码（审核通过后返回）*/
  @JsonProperty("real_number")
  private String realNumber;

}
