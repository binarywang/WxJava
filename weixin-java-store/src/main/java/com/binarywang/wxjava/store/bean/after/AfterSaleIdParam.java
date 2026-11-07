package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后单id信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AfterSaleIdParam implements Serializable {

  private static final long serialVersionUID = 4974332291476116540L;
  /** 售后单号 */
  @JsonProperty("after_sale_order_id")
  private String afterSaleOrderId;
}
