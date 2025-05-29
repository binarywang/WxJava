package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 流量来源子渠道指标数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class SubLiveDistChannelSourceStats implements Serializable {

  private static final long serialVersionUID = - 5279814435684116105L;

  /**
   * 渠道层级
   */
  @JsonProperty("level")
  private Integer level;

  /**
   * 来源渠道ID
   */
  @JsonProperty("source_channel_id")
  private Long sourceChannelId;

  /**
   * 在该渠道下的统计值
   */
  @JsonProperty("metric_value")
  private Long metricValue;

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
   * gmv占比
   */
  @JsonProperty("gmv_ratio")
  private Double gmvRatio;

  /**
   * uv占比
   */
  @JsonProperty("uv_ratio")
  private Double uvRatio;

  /**
   * 在该渠道下的统计值比率
   */
  @JsonProperty("metric_value_ratio")
  private Double metricValueRatio;

  /**
   * 渠道名称
   */
  @JsonProperty("source_channel_name")
  private String sourceChannelName;

  /**
   * pv
   */
  @JsonProperty("pv")
  private Long pv;

  /**
   * 当前层级pv占总pv的比例
   */
  @JsonProperty("pv_ratio")
  private Double pvRatio;

}
