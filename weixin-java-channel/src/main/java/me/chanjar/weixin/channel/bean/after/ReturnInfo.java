package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户退货信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.ReturnInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class ReturnInfo implements Serializable {

  private static final long serialVersionUID = 1643844664701376892L;
  /** 快递单号 */
  @JsonProperty("waybill_id")
  private String waybillId;

  /** 物流公司id */
  @JsonProperty("delivery_id")
  private String deliveryId;

  /** 物流公司名称 */
  @JsonProperty("delivery_name")
  private String deliveryName;
}
