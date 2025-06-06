package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 虚拟号码信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AfterSaleVirtualNumberInfo implements Serializable {
  private static final long serialVersionUID = -5756618937333859985L;

  /** 虚拟号码 */
  @JsonProperty("virtual_tel_number")
  private String virtualTelNumber;

  /** 虚拟号码过期时间 */
  @JsonProperty("virtual_tel_expire_time")
  private Long virtualTelExpireTime;

}
