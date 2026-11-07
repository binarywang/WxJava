package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商家协商保障单请求参数。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.GuaranteeModifyRequest}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
@Deprecated
public class GuaranteeModifyRequest extends GuaranteeOrderIdParam {

  private static final long serialVersionUID = 4268864541609439068L;

  /** 商品破损程度。 */
  @JsonProperty("bad_level")
  private Integer badLevel;

  /** 商家协商备注。 */
  @JsonProperty("merchant_remark")
  private String merchantRemark;

  public GuaranteeModifyRequest(String guaranteeOrderId, Integer badLevel, String merchantRemark) {
    super(guaranteeOrderId);
    this.badLevel = badLevel;
    this.merchantRemark = merchantRemark;
  }
}
