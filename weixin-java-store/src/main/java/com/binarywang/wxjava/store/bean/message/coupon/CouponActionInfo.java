package com.binarywang.wxjava.store.bean.message.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券操作消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CouponActionInfo implements Serializable {

  private static final long serialVersionUID = -4456716511656569552L;
  /** 优惠券ID */
  @JsonProperty("coupon_id")
  @JacksonXmlProperty(localName = "coupon_id")
  private String couponId;

  /** 领券时间 */
  @JsonProperty("create_time")
  @JacksonXmlProperty(localName = "create_time")
  private String createTime;

  /** 删除时间 */
  @JsonProperty("delete_time")
  @JacksonXmlProperty(localName = "delete_time")
  private String deleteTime;

  /** 过期时间 */
  @JsonProperty("expire_time")
  @JacksonXmlProperty(localName = "expire_time")
  private String expireTime;

  /** 更新时间 */
  @JsonProperty("change_time")
  @JacksonXmlProperty(localName = "change_time")
  private String changeTime;

  /** 作废时间 */
  @JsonProperty("invalid_time")
  @JacksonXmlProperty(localName = "invalid_time")
  private String invalidTime;
}
