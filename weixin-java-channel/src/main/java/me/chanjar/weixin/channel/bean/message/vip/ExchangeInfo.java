package me.chanjar.weixin.channel.bean.message.vip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 积分兑换
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Deprecated
public class ExchangeInfo implements Serializable {

  private static final long serialVersionUID = -5692646625631036694L;
  /** 入会时间 **/
  @JsonProperty("pay_score")
  @JacksonXmlProperty(localName = "pay_score")
  private Long pay_score;

  /** 兑换类型 1.优惠券 2商品 **/
  @JsonProperty("score_item_type")
  @JacksonXmlProperty(localName = "score_item_type")
  private Long score_item_type;

  /** 优惠券信息 **/
  @JsonProperty("coupon_info")
  @JacksonXmlProperty(localName = "coupon_info")
  private CouponInfo couponInfo;

  /** 商品信息 **/
  @JsonProperty("product_info")
  @JacksonXmlProperty(localName = "product_info")
  private ProductInfo productInfo;
}
