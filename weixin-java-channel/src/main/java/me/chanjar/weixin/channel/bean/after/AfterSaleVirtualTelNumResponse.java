package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.AfterSaleVirtualTelNumResponse}。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AfterSaleVirtualTelNumResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -2715343569103426942L;

  @JsonProperty("virtual_tel_number")
  private String virtualTelNumber;

  @JsonProperty("virtual_tel_expire_time")
  private Long virtualTelExpireTime;
}
