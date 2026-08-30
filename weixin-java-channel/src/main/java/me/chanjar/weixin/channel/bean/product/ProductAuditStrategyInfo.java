package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/** 商品上架策略信息. */
@Data
@Deprecated
public class ProductAuditStrategyInfo implements Serializable {
  private static final long serialVersionUID = -2747596416115475981L;

  @JsonProperty("hide_err_field_flag")
  private Integer hideErrFieldFlag;
  @JsonProperty("hit_duplicated_flag")
  private Integer hitDuplicatedFlag;
  @JsonProperty("hit_low_risk_rule_flag")
  private Integer hitLowRiskRuleFlag;
}
