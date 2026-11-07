package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.AfterSaleOrderInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class AfterSaleOrderInfo implements Serializable {

  private static final long serialVersionUID = 3938545222231426455L;

  /** 售后单ID */
  @JsonProperty("aftersale_order_id")
  private String afterSaleOrderId;

  public String getAfterSaleOrderId() {
    return afterSaleOrderId;
  }

  public void setAfterSaleOrderId(String afterSaleOrderId) {
    this.afterSaleOrderId = afterSaleOrderId;
  }
}
