package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 关播内容力指标数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class EndedIndexItem implements Serializable {

  private static final long serialVersionUID = 7529336638744298238L;

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

  /**
   * 该指标 7 天中位数
   */
  @JsonProperty("median_7_days")
  private Long median7Days;

}
