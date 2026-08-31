package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.ewaybill.EwaybillOrderIdParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class EwaybillOrderIdParam {
  @JsonProperty("ewaybill_order_id")
  private String ewaybillOrderId;
}
