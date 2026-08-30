package com.binarywang.wxjava.store.bean.qic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmitConfigResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 2456553692263326158L;

  @JsonProperty("submit_config")
  private SubmitConfig submitConfig;

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class SubmitConfig implements Serializable {
    private static final long serialVersionUID = 3286213539172123945L;

    @JsonProperty("delivery_list")
    private List<Delivery> deliveryList;

    @JsonProperty("inspect_org_list")
    private List<InspectOrg> inspectOrgList;

    @JsonProperty("charge_url")
    private String chargeUrl;
  }

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Delivery implements Serializable {
    private static final long serialVersionUID = -9209694824619490683L;

    private String id;

    private String name;

    @JsonProperty("delivery_products")
    private List<DeliveryProduct> deliveryProducts;
  }

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class DeliveryProduct implements Serializable {
    private static final long serialVersionUID = 6527277159948670769L;

    private Long id;

    private String name;

    @JsonProperty("enable_insure")
    private Integer enableInsure;

    @JsonProperty("insure_type_list")
    private List<InsureType> insureTypeList;
  }

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class InsureType implements Serializable {
    private static final long serialVersionUID = -7788541278375899098L;

    private String id;

    private String name;

    @JsonProperty("upper_limit_type")
    private Integer upperLimitType;

    @JsonProperty("upper_limit_amount")
    private Long upperLimitAmount;
  }

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class InspectOrg implements Serializable {
    private static final long serialVersionUID = 1723422231048685194L;

    private String id;

    private String name;

    @JsonProperty("org_category")
    private Integer orgCategory;
  }
}
