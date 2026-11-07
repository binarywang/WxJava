package me.chanjar.weixin.channel.bean.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品售后信息
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.AfterSaleInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class AfterSaleInfo implements Serializable {


  /**
   * 商品的售后地址id，可使用获取地址详情
   */
  @JsonProperty("after_sale_address_id")
  private Long afterSaleAddressId;
}
