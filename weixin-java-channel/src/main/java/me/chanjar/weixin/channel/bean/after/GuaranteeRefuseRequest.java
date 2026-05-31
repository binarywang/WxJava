package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商家拒绝保障单请求参数.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class GuaranteeRefuseRequest extends GuaranteeOrderIdParam {

  private static final long serialVersionUID = 6632720364917208597L;

  /** 拒绝原因. */
  @JsonProperty("reason")
  private String reason;

  public GuaranteeRefuseRequest(String guaranteeOrderId, String reason) {
    super(guaranteeOrderId);
    this.reason = reason;
  }
}
