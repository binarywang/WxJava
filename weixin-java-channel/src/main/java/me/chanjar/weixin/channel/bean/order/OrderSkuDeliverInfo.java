package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品发货信息
 *
 * @author <a href="https://github.com/qrpcode">北鹤M</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.OrderSkuDeliverInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class OrderSkuDeliverInfo implements Serializable {

  private static final long serialVersionUID = 4075897806362929800L;

  /** 商品发货类型：0：现货，1：全款预售 */
  @JsonProperty("stock_type")
  private Integer stockType;

  /** 预计发货时间(stock_type=1时返回该字段) */
  @JsonProperty("predict_delivery_time")
  private String predictDeliveryTime;

}
