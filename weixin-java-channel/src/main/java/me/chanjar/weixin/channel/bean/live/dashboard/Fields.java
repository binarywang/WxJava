package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统计数值维度指标数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class Fields implements Serializable {

  private static final long serialVersionUID = 228387216076265877L;

  /**
   * 维度值
   */
  @JsonProperty("dim_key")
  private String dimKey;

  /**
   * 指标值
   */
  @JsonProperty("dim_val")
  private String dimVal;

  /**
   * 指标值比例
   */
  @JsonProperty("dim_val_ratio")
  private String dimValRatio;

}
