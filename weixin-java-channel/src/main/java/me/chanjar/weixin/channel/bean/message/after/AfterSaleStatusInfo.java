package me.chanjar.weixin.channel.bean.message.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.after.AfterSaleStatusInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class AfterSaleStatusInfo implements Serializable {

  private static final long serialVersionUID = -7309656340583314591L;
  /** 售后单号 */
  @JsonProperty("after_sale_order_id")
  @JacksonXmlProperty(localName = "after_sale_order_id")
  private String afterSaleOrderId;

  /** 售后单状态 */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private String status;

  /** 订单id */
  @JsonProperty("order_id")
  @JacksonXmlProperty(localName = "order_id")
  private String orderId;
}
