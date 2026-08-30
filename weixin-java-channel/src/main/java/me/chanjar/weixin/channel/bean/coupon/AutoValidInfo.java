package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自动生效信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class AutoValidInfo implements Serializable {

  private static final long serialVersionUID = 1702505613539861103L;
  /** 优惠券开启自动生效类型 0不启用自动生效 1启用自动生效，按领券开始时间（自动生效时间为 receive_info.start_time） */
  @JsonProperty("auto_valid_type")
  private Integer autoValidType;
}
