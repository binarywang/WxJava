package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述时间序列的维度标签
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class Dimension implements Serializable {

  private static final long serialVersionUID = - 1879006149576217182L;

  /**
   * 维度的类型 {@link me.chanjar.weixin.channel.enums.DimensionType}
   */
  @JsonProperty("type")
  private Integer type;

  /**
   * 维度标签
   */
  @JsonProperty("ux_label")
  private String uxLabel;

  /**
   * 维度值
   */
  @JsonProperty("dimension_value")
  private Long dimensionValue;

}
