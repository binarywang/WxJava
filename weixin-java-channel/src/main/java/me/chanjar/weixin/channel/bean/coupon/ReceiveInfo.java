package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 领取信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.ReceiveInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class ReceiveInfo implements Serializable {

  private static final long serialVersionUID = 755956808504040633L;
  /** 优惠券领用结束时间 **/
  @JsonProperty("end_time")
  private Long endTime;

  /** 单人限领张数 **/
  @JsonProperty("limit_num_one_person")
  private Integer limitNumOnePerson;

  /** 优惠券领用开始时间 **/
  @JsonProperty("start_time")
  private Long startTime;

  /** 优惠券领用总数 **/
  @JsonProperty("total_num")
  private Integer totalNum;
}
