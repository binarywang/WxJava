package me.chanjar.weixin.channel.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单其他信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class OrderExtInfo extends OrderIdInfo {

  private static final long serialVersionUID = 4723533858047219828L;
  /** 类型 1:联盟佣金信息 */
  @JsonProperty("type")
  @JacksonXmlProperty(localName = "type")
  private Integer type;
}
