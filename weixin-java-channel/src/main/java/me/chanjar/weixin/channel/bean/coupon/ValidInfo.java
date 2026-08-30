package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券有效信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class ValidInfo implements Serializable {

  private static final long serialVersionUID = -4550516248380285635L;
  /** 优惠券有效期类型 {@link me.chanjar.weixin.channel.enums.CouponValidType} */
  @JsonProperty("valid_type")
  private Integer validType;

  /** 优惠券有效天数，valid_type=2时才有意义 */
  @JsonProperty("valid_day_num")
  private Integer validDayNum;

  /** 优惠券有效期开始时间，valid_type=1时才有意义 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 优惠券有效期结束时间，valid_type=1时才有意义 */
  @JsonProperty("end_time")
  private Long endTime;
}
