package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AfterSaleGenAfterSaleOrderParam extends AfterSaleRefundPriceDiffParam {
  private static final long serialVersionUID = -6873909673739068936L;

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("type")
  private String type;

  @JsonProperty("address_id")
  private String addressId;

  @JsonProperty("exchange_sku_info")
  private ExchangeSkuInfo exchangeSkuInfo;
}
