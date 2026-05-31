package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商家协商保障单请求参数.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class GuaranteeModifyRequest extends GuaranteeOrderIdParam {

  private static final long serialVersionUID = -7142671437465099549L;

  /** 协商金额（单位：分）. */
  @JsonProperty("amount")
  private Integer amount;

  /** 协商描述. */
  @JsonProperty("desc")
  private String desc;

  public GuaranteeModifyRequest(String guaranteeOrderId, Integer amount, String desc) {
    super(guaranteeOrderId);
    this.amount = amount;
    this.desc = desc;
  }
}
