package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 地址信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class OrderAddressInfo extends AddressInfo {

  private static final long serialVersionUID = 3062707865189774795L;
  /** 虚拟发货订单联系方式(deliver_method=1时返回) */
  @JsonProperty("virtual_order_tel_number")
  private String virtualOrderTelNumber;

  /**
   * 额外的联系方式信息（虚拟号码相关），具体结构请参考TelNumberExtInfo结构体
   */
  @JsonProperty("tel_number_ext_info")
  private TelNumberExtInfo telNumberExtInfo;

  /**
   * 0：不使用虚拟号码，1：使用虚拟号码
   */
  @JsonProperty("use_tel_number")
  private Integer useTelNumber;

  /**
   * 标识当前店铺下一个唯一的用户收货地址
   */
  @JsonProperty("hash_code")
  private String hashCode;
}
