package me.chanjar.weixin.channel.bean.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 属性
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class AttrInfo implements Serializable {

  private static final long serialVersionUID = -790859309885311785L;

  /** 销售属性key（自定义），字符类型，最长不超过40 */
  @JsonProperty("attr_key")
  private String key;

  /** 销售属性value（自定义），字符类型，最长不超过40，相同key下不能超过100个不同value */
  @JsonProperty("attr_value")
  private String value;
}
