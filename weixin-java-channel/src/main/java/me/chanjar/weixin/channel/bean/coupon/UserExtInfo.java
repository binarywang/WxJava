package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户优惠券附加信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.UserExtInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class UserExtInfo implements Serializable {

  private static final long serialVersionUID = 8304922825230343409L;
  /** 优惠券核销时间 */
  @JsonProperty("use_time")
  private Long useTime;
}
