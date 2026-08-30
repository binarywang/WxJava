package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class StockInfo implements Serializable {

  private static final long serialVersionUID = -6078383881065929862L;
  /** 优惠券剩余量 */
  @JsonProperty("issued_num")
  private Integer issuedNum;

  /** 优惠券领用量 */
  @JsonProperty("receive_num")
  private Integer receiveNum;

  /** 优惠券已用量 */
  @JsonProperty("used_num")
  private Integer usedNum;
}
