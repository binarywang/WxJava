package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor

@Deprecated
public class CouponDetailInfo implements Serializable {

  private static final long serialVersionUID = 5994815232349181577L;
  /** 优惠券名称 **/
  @JsonProperty("name")
  private String name;

  /** 优惠券有效信息 **/
  @JsonProperty("valid_info")
  private ValidInfo validInfo;

  /** 推广信息 **/
  @JsonProperty("promote_info")
  private PromoteInfo promoteInfo;

  /** 优惠信息 **/
  @JsonProperty("discount_info")
  private DiscountInfo discountInfo;

  /** 额外信息 **/
  @JsonProperty("ext_info")
  private ExtInfo extInfo;

  /** 领取信息 **/
  @JsonProperty("receive_info")
  private ReceiveInfo receiveInfo;

}
