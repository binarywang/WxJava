package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.ewaybill.BatchPrintOrderRequest}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class BatchPrintOrderRequest {
  @JsonProperty("req_list") private List<PrintOrderRequest> reqList;
}
