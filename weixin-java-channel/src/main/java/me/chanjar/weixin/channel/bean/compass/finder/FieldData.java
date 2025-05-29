package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 维度指标数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class FieldData implements Serializable {

  private static final long serialVersionUID = - 4022953139259283599L;

  /**
   * 维度指标名
   */
  @JsonProperty("dim_key")
  private String dimKey;

  /**
   * 维度指标值
   */
  @JsonProperty("dim_value")
  private String dimValue;

}
