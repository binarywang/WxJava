package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址编码
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.address.AddressCode}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class AddressCode implements Serializable {

  private static final long serialVersionUID = -6782328785056142627L;

  /** 地址名称 */
  @JsonProperty("name")
  private String name;

  /** 地址行政编码 */
  @JsonProperty("code")
  private Integer code;

  /** 地址级别 1-省级 2-市级 3-区县级 4-街道 */
  @JsonProperty("level")
  private Integer level;
}
