package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 线下配送地址类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class OfflineAddressType implements Serializable {

  private static final long serialVersionUID = 636850757572901377L;

  /** 1表示同城配送 */
  @JsonProperty("same_city")
  private Integer sameCity;

  /** 1表示用户自提 */
  @JsonProperty("pickup")
  private Integer pickup;
}
