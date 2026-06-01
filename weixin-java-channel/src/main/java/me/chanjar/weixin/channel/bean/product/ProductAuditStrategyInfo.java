package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品上架策略信息.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductAuditStrategyInfo implements Serializable {

  private static final long serialVersionUID = -2747596416115475981L;

  /** 隐藏商品信息上架策略标识. */
  @JsonProperty("hide_err_field_flag")
  private Integer hideErrFieldFlag;

  /** 相似品策略标识. */
  @JsonProperty("hit_duplicated_flag")
  private Integer hitDuplicatedFlag;

  /** 低风险规则策略标识. */
  @JsonProperty("hit_low_risk_rule_flag")
  private Integer hitLowRiskRuleFlag;
}
