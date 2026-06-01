package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品提审限额响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductAuditQuotaResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -6242837308752181147L;

  /** 提审配额信息. */
  @JsonProperty("audit_quota")
  private AuditQuota auditQuota;

  @Data
  @NoArgsConstructor
  public static class AuditQuota {
    @JsonProperty("block_status")
    private Integer blockStatus;

    @JsonProperty("avail_quota")
    private Integer availQuota;

    @JsonProperty("total_quota")
    private Integer totalQuota;

    @JsonProperty("unlimited_type")
    private Integer unlimitedType;

    @JsonProperty("audit_total_quota")
    private Integer auditTotalQuota;

    @JsonProperty("audit_total_remaining")
    private Integer auditTotalRemaining;

    @JsonProperty("new_product_total_quota")
    private Integer newProductTotalQuota;

    @JsonProperty("new_product_total_remaining")
    private Integer newProductTotalRemaining;
  }
}
