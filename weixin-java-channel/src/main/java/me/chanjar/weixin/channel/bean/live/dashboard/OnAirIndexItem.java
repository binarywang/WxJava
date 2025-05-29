package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 在播内容力指标数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class OnAirIndexItem implements Serializable {

  private static final long serialVersionUID = - 2988342521964183666L;

  /**
   * 描述最近多少分钟的指标值
   */
  @JsonProperty("n")
  private Integer n;

  /**
   * 最近 n 分钟该指标的值
   */
  @JsonProperty("last_n_mins_value")
  private Integer lastNMinsValue;

  /**
   * 最近 2n 到 n 分钟该指标的值（用于环比）
   */
  @JsonProperty("last_2n_to_n_mins_value")
  private Integer last2nToNMinsValue;

  /**
   * 最近 n 分钟该指标值打败了 xx% 的在播主播
   */
  @JsonProperty("last_n_mins_percentile")
  private Integer lastNMinsPercentile;

  /**
   * 整场直播的指标值
   */
  @JsonProperty("value")
  private Long value;

  /**
   * 整场直播该指标值打败了 xx% 的主播
   */
  @JsonProperty("percentile")
  private Integer percentile;

}
