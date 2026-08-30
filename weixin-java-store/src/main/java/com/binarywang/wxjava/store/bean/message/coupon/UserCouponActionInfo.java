package com.binarywang.wxjava.store.bean.message.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户优惠券操作消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class UserCouponActionInfo implements Serializable {

  private static final long serialVersionUID = -5948836918972669529L;
  /** 优惠券ID */
  @JsonProperty("coupon_id")
  @JacksonXmlProperty(localName = "coupon_id")
  private String couponId;

  /** 用户券ID */
  @JsonProperty("user_coupon_id")
  @JacksonXmlProperty(localName = "user_coupon_id")
  private String userCouponId;

  /** 过期时间 */
  @JsonProperty("expire_time")
  @JacksonXmlProperty(localName = "expire_time")
  private String expireTime;

  /** 使用时间 */
  @JsonProperty("use_time")
  @JacksonXmlProperty(localName = "use_time")
  private String useTime;

  /** 返还时间 */
  @JsonProperty("unuse_time")
  @JacksonXmlProperty(localName = "unuse_time")
  private String unuseTime;


}
