package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatchPrintOrderRequest {
  @JsonProperty("req_list") private List<PrintOrderRequest> reqList;
}
