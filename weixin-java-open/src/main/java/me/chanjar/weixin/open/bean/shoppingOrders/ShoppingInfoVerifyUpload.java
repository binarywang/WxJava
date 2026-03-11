package me.chanjar.weixin.open.bean.shoppingOrders;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingInfoVerifyUpload implements Serializable {

  private static final long serialVersionUID = 4295431037060277496L;
  /**
   * 必填
   * 订单，需要上传购物详情的订单，根据订单类型二选一
   */
  @SerializedName("order_key")
  private OrderKeyBean orderKey;

  /**
   * 必填
   * 支付者，支付者信息
   */
  @SerializedName("payer")
  private PayerBean payer;
}
