package com.binarywang.wxjava.store.bean.qic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InspectConfigResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 6463651966377955876L;

  @JsonProperty("inspect_config")
  private InspectConfig inspectConfig;

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class InspectConfig implements Serializable {
    private static final long serialVersionUID = 5829846300579243328L;

    @JsonProperty("warehouse_id")
    private String warehouseId;

    @JsonProperty("delivery_address")
    private Address deliveryAddress;

    @JsonProperty("return_address")
    private Address returnAddress;

    @JsonProperty("warehouse_name")
    private String warehouseName;

    @JsonProperty("warehouse_addr")
    private String warehouseAddr;
  }

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Address implements Serializable {
    private static final long serialVersionUID = -664266740472865991L;

    @JsonProperty("contact_name")
    private String contactName;

    @JsonProperty("contact_phone")
    private String contactPhone;

    private String province;

    private String city;

    private String county;

    private String detail;
  }
}
