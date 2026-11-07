package me.chanjar.weixin.channel.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单确认收货信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.order.OrderConfirmInfo}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class OrderConfirmInfo extends OrderIdInfo {

  private static final long serialVersionUID = -2569494642832261346L;
  /** 1:用户确认收货；2:超时自动确认收货 */
  @JsonProperty("confirm_type")
  @JacksonXmlProperty(localName = "confirm_type")
  private Integer confirmType;
}
