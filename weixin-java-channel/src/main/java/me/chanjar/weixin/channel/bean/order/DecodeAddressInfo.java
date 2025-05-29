package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 解码地址数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DecodeAddressInfo extends AddressInfo {

  /** 虚拟发货订单联系方式，在发货方式为无需快递(deliver_method=1)时返回 */
  @JsonProperty("virtual_order_tel_number")
  private String virtualOrderTelNumber;
}
