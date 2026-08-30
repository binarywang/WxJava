package me.chanjar.weixin.channel.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单发货信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class OrderDeliveryInfo extends OrderIdInfo {

  private static final long serialVersionUID = 117962754344887556L;
  /** 0:尚未全部发货；1:全部商品发货完成 */
  @JsonProperty("finish_delivery")
  @JacksonXmlProperty(localName = "finish_delivery")
  private Integer finishDelivery;

}
