package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 换货类型的发货物流信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AfterSaleExchangeDeliveryInfo implements Serializable {

  private static final long serialVersionUID = 3039216368034112038L;

  /** 快递单号 */
  @JsonProperty("waybill_id")
  private String waybillId;

  /** 物流公司id */
  @JsonProperty("delivery_id")
  private String deliveryId;

  /** 物流公司名称 */
  @JsonProperty("delivery_name")
  private String deliveryName;

  /** 地址信息 */
  @JsonProperty("address_info")
  private AddressInfo addressInfo;
}
