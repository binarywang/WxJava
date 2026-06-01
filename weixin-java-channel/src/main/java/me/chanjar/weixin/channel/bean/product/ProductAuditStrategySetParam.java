package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设置商品上架策略请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductAuditStrategySetParam implements Serializable {

  private static final long serialVersionUID = 7542738744842032508L;

  /** 上架策略. */
  @JsonProperty("audit_strategy")
  private ProductAuditStrategyInfo auditStrategy;
}
