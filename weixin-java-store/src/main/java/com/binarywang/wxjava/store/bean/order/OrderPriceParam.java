package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 订单价格参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@JsonInclude(Include.NON_NULL)
public class OrderPriceParam implements Serializable {

  private static final long serialVersionUID = -7925819981481556218L;
  /** 订单id */
  @JsonProperty("order_id")
  private String orderId;

  /** 是否修改运费 */
  @JsonProperty("change_express")
  private Boolean changeExpress;

  /** 修改后的运费价格（change_express=true时必填），以分为单位 */
  @JsonProperty("express_fee")
  private Integer expressFee;

  /** 改价列表 */
  @JsonProperty("change_order_infos")
  private List<ChangeOrderInfo> changeOrderInfos;

  public OrderPriceParam() {
  }

  public OrderPriceParam(String orderId, Integer expressFee, List<ChangeOrderInfo> changeOrderInfos) {
    this.orderId = orderId;
    // expressFee不为空时，表示修改运费
    this.changeExpress = (expressFee != null);
    this.expressFee = expressFee;
    this.changeOrderInfos = changeOrderInfos;
  }
}
