package com.binarywang.wxjava.store.bean.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单结算信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderSettleInfo extends OrderIdInfo {

  private static final long serialVersionUID = -1817955568383872053L;
  /** 结算时间 */
  @JsonProperty("settle_time")
  @JacksonXmlProperty(localName = "settle_time")
  private Long settleTime;
}
