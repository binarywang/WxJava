package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 商品提审限额响应. */
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductAuditQuotaResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -6242837308752181147L;

  @JsonProperty("audit_quota")
  private AuditQuota auditQuota;

  @Data
  public static class AuditQuota implements Serializable {
    private static final long serialVersionUID = 6066821247844334714L;

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
    @JsonProperty("new_product_remaining")
    private Integer newProductRemaining;
  }
}
