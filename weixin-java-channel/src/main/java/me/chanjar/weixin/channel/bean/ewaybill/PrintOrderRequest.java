package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Deprecated
public class PrintOrderRequest extends EwaybillOrderIdParam {
  @JsonProperty("delivery_id") private String deliveryId;
  @JsonProperty("waybill_id") private String waybillId;
  @JsonProperty("re_print") private Boolean rePrint;
}
