package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单备注
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRemarkParam implements Serializable {

  private static final long serialVersionUID = 2285714780419948468L;
  /** 订单id */
  @JsonProperty("order_id")
  private String orderId;

  /** 备注内容 */
  @JsonProperty("merchant_notes")
  private String merchantNotes;

}
