package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.AddressInfo;

/**
 * 订单地址参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderAddressParam implements Serializable {

  private static final long serialVersionUID = 2277618297276466650L;

  /** 订单id */
  @JsonProperty("order_id")
  private String orderId;

  /** 地址信息 */
  @JsonProperty("user_address")
  private AddressInfo userAddress;
}
