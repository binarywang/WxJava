package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 售后单 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AfterSaleInfoResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -752661975153491902L;
  /** 售后单 */
  @JsonProperty("after_sale_order")
  private AfterSaleInfo info;
}
