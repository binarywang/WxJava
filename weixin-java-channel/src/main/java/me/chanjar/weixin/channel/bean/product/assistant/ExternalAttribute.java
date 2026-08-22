package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品属性键值对。
 */
@Data
@NoArgsConstructor
public class ExternalAttribute implements Serializable {

  private static final long serialVersionUID = -8639178782951125101L;

  /** 属性名。 */
  @JsonProperty("key")
  private String key;

  /** 属性值。 */
  @JsonProperty("value")
  private String value;
}
