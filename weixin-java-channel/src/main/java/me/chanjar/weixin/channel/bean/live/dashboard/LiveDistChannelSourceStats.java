package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 流量来源渠道指标数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDistChannelSourceStats implements Serializable {

  private static final long serialVersionUID = - 6802106934852140579L;

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
   * 流量来源子渠道指标数据统计值
   */
  @JsonProperty("sub_channel_source_stats")
  private List<SubLiveDistChannelSourceStats> subChannelSourceStats;

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
   * 渠道名称
   */
  @JsonProperty("source_channel_name")
  private String sourceChannelName;

  /**
   * 当前层级pv占总pv的比例
   */
  @JsonProperty("pv_ratio")
  private Double pvRatio;

}
