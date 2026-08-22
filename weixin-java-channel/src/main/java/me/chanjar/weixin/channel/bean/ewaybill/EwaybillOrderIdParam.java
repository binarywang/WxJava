package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EwaybillOrderIdParam {
  @JsonProperty("ewaybill_order_id")
  private String ewaybillOrderId;
}
