package com.binarywang.wxjava.store.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 额外信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ExtInfo implements Serializable {

  private static final long serialVersionUID = 9053035437087423233L;
  /** 商品折扣券领取后跳转的商品id **/
  @JsonProperty("jump_product_id")
  private String jumpProductId;

  /** 备注信息 **/
  @JsonProperty("notes")
  private String notes;

  /** 优惠券有效时间 **/
  @JsonProperty("valid_time")
  private Long validTime;

  /** 优惠券失效时间戳 **/
  @JsonProperty("invalid_time")
  private Long invalidTime;
}
