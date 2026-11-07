package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后信息详情
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AfterSaleDetail implements Serializable {

  private static final long serialVersionUID = -3786573982841041144L;

  /** 正在售后流程的售后单数 */
  @JsonProperty("on_aftersale_order_cnt")
  private Integer onAfterSaleOrderCnt;

  /** 售后单列表 */
  @JsonProperty("aftersale_order_list")
  private List<AfterSaleOrderInfo> afterSaleOrderList;
}
