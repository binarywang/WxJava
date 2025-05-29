package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 场景的渠道分析统计值
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDistributionSceneStat implements Serializable {

  private static final long serialVersionUID = 4261140121141859416L;

  /**
   * 场景类型 {@link me.chanjar.weixin.channel.enums.LiveDistributionSceneType}
   */
  @JsonProperty("scene_type")
  private Integer sceneType;

  /**
   * 该场景下的渠道分析统计值
   */
  @JsonProperty("dist_flow_type_stats")
  private List<LiveDistributionByFlowTypeStat> distFlowTypeStats;

  /**
   * 指标值总数
   */
  @JsonProperty("metric_value_total")
  private Long metricValueTotal;

  /**
   * GMV总值（单位：分）
   */
  @JsonProperty("gmv")
  private Long gmv;

  /**
   * UV总值
   */
  @JsonProperty("uv")
  private Long uv;

  /**
   * 千次看播成交（单位： 分）
   */
  @JsonProperty("gmv_per_uv")
  private Long gmvPerUv;

  /**
   * 指标值
   */
  @JsonProperty("metric_value")
  private Long metricValue;

  /**
   * 在该渠道下的统计值比率
   */
  @JsonProperty("metric_value_ratio")
  private Double metricValueRatio;

  /**
   * pv
   */
  @JsonProperty("pv")
  private Long pv;

}
