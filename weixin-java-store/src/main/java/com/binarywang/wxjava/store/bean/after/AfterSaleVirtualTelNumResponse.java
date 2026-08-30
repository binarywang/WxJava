package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

@Data
@EqualsAndHashCode(callSuper = true)
public class AfterSaleVirtualTelNumResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = -2715343569103426942L;

  @JsonProperty("virtual_tel_number")
  private String virtualTelNumber;

  @JsonProperty("virtual_tel_expire_time")
  private Long virtualTelExpireTime;
}
