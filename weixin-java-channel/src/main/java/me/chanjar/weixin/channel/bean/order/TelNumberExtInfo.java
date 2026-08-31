package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 联系方式信息
 *
 * @author <a href="https://github.com/imyzt">imyzt</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.TelNumberExtInfo}。
 */
@Data
@Deprecated
public class TelNumberExtInfo {

  /**
   * 脱敏手机号
   */
  @JsonProperty("real_tel_number")
  private String realTelNumber;

  /**
   * 完整的虚拟号码
   */
  @JsonProperty("virtual_tel_number")
  private String virtualTelNumber;

  /**
   * 主动兑换的虚拟号码过期时间
   */
  @JsonProperty("virtual_tel_expire_time")
  private Long virtualTelExpireTime;

  /**
   * 主动兑换虚拟号码次数
   */
  @JsonProperty("get_virtual_tel_cnt")
  private Long getVirtualTelCnt;
}
