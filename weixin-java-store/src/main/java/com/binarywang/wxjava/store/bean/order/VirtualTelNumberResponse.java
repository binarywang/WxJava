package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 兑换虚拟号 返回结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VirtualTelNumberResponse extends WxStoreBaseResponse {

  /** 虚拟号码 */
  @JsonProperty("virtual_tel_number")
  private String virtualTelNumber;

  /** 虚拟号码过期时间 */
  @JsonProperty("virtual_tel_expire_time")
  private Long virtualTelExpireTime;

  /** 兑换虚拟号码次数 */
  @JsonProperty("get_virtual_tel_cnt")
  private Integer getVirtualTelCnt;
}
