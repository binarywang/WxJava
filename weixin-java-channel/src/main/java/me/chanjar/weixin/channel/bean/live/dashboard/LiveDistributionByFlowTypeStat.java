package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 流量类型、渠道层级的渠道分析统计数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDistributionByFlowTypeStat implements Serializable {

  private static final long serialVersionUID = 5885014384803438677L;

  /**
   * 渠道流量类型 {@link me.chanjar.weixin.channel.enums.LiveDistributionFlowType}
   */
  @JsonProperty("live_dst_channel_type")
  private Integer liveDstChannelType;

  /**
   * 一级类目渠道来源指标划分
   */
  @JsonProperty("channel_source_stats")
  private List<LiveDistChannelSourceStats> channelSourceStats;

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
   * 千次看播成交（单位： 分）(GPV)
   */
  @JsonProperty("gmv_per_uv")
  private Long gmvPerUv;

  /**
   * PV总值
   */
  @JsonProperty("pv")
  private Long pv;

  /**
   * 当前层级pv占总pv的比例
   */
  @JsonProperty("pv_ratio")
  private Double pvRatio;

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

}
