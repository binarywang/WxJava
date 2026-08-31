package me.chanjar.weixin.channel.bean.qic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.qic.RegisterLogisticsRequest}。
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class RegisterLogisticsRequest implements Serializable {
  private static final long serialVersionUID = 4346443649534209624L;

  @JsonProperty("order_id_list")
  private List<String> orderIdList;

  @JsonProperty("logistics_info")
  private LogisticsInfo logisticsInfo;

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class LogisticsInfo implements Serializable {
    private static final long serialVersionUID = 8677143207727485993L;

    @JsonProperty("waybill_id")
    private String waybillId;

    @JsonProperty("delivery_id")
    private String deliveryId;

    @JsonProperty("delivery_name")
    private String deliveryName;

    @JsonProperty("delivery_type")
    private Integer deliveryType;
  }
}
