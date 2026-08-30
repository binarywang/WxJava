package me.chanjar.weixin.channel.bean.qic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class SubmitInspectRequest implements Serializable {
  private static final long serialVersionUID = 6396115469552098613L;

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("inspect_info")
  private InspectInfo inspectInfo;

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class InspectInfo implements Serializable {
    private static final long serialVersionUID = -3502982646296821525L;

    @JsonProperty("delivery_id")
    private String deliveryId;

    @JsonProperty("backup_delivery_id")
    private String backupDeliveryId;

    @JsonProperty("express_insure")
    private Boolean expressInsure;

    @JsonProperty("express_insure_amount")
    private Long expressInsureAmount;

    @JsonProperty("express_merge")
    private Boolean expressMerge;

    @JsonProperty("inspect_org_id")
    private String inspectOrgId;

    @JsonProperty("refund_intercept")
    private Integer refundIntercept;

    @JsonProperty("inspect_org_name")
    private String inspectOrgName;

    @JsonProperty("warehouse_name")
    private String warehouseName;

    @JsonProperty("warehouse_addr")
    private String warehouseAddr;

    @JsonProperty("delivery_product_id")
    private Long deliveryProductId;

    @JsonProperty("delivery_insure_id")
    private String deliveryInsureId;

    @JsonProperty("backup_delivery_product_id")
    private Long backupDeliveryProductId;

    @JsonProperty("backup_delivery_insure_id")
    private String backupDeliveryInsureId;

    @JsonProperty("backup_express_insure")
    private Boolean backupExpressInsure;

    @JsonProperty("backup_express_insure_amount")
    private Long backupExpressInsureAmount;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("agarwood_inspect_org_id")
    private String agarwoodInspectOrgId;

    @JsonProperty("agarwood_inspect_org_name")
    private String agarwoodInspectOrgName;
  }
}
