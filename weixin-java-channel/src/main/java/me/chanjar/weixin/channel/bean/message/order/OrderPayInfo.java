package me.chanjar.weixin.channel.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单支付信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class OrderPayInfo extends OrderIdInfo {

  private static final long serialVersionUID = -3502786073769735831L;
  /** 支付时间，秒级时间戳 */
  @JsonProperty("pay_time")
  @JacksonXmlProperty(localName = "pay_time")
  private Long payTime;
}
