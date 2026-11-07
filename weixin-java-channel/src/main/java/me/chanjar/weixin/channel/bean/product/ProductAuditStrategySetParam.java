package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/** 设置商品上架策略请求参数.
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.ProductAuditStrategySetParam}。
*/
@Data
@Deprecated
public class ProductAuditStrategySetParam implements Serializable {
  private static final long serialVersionUID = 7542738744842032508L;

  @JsonProperty("audit_strategy")
  private ProductAuditStrategyInfo auditStrategy;
}
