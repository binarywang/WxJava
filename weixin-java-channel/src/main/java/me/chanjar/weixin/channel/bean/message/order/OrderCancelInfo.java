package me.chanjar.weixin.channel.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单取消信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.order.OrderCancelInfo}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class OrderCancelInfo extends OrderIdInfo {

  private static final long serialVersionUID = -8022876997578127873L;
  /** 1:用户取消；2:超时取消；3:全部商品售后完成,订单取消；4:超卖商家取消订单 */
  @JsonProperty("cancel_type")
  @JacksonXmlProperty(localName = "cancel_type")
  private Integer cancelType;
}
