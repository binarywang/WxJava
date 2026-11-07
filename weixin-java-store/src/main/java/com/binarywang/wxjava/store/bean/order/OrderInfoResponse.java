package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 订单信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderInfoResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 935829924760021624L;
  /** 订单信息 */
  @JsonProperty("order")
  private OrderInfo order;
}
