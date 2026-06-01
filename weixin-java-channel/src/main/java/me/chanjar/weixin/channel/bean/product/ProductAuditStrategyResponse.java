package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品上架策略响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductAuditStrategyResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1074784511408331849L;

  /** 上架策略. */
  @JsonProperty("audit_strategy")
  private ProductAuditStrategyInfo auditStrategy;
}
