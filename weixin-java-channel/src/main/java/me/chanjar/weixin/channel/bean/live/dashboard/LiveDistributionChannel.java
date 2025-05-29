package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 电商渠道分布
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDistributionChannel implements Serializable {

  private static final long serialVersionUID = - 5898886208775573377L;

  /**
   * 客户数
   */
  @JsonProperty("audience_count")
  private Long audienceCount;

  /**
   * 总进入直播数
   */
  @JsonProperty("total_joinlive_count")
  private Long totalJoinliveCount;

  /**
   * 按场景划分的渠道分析统计值
   */
  @JsonProperty("live_dist_channel_source_by_scene_stats")
  private List<LiveDistributionSceneStat> liveDistChannelSourceBySceneStats;

  /**
   * 按照流量类型、渠道层级划分的渠道分析统计数据
   */
  @JsonProperty("live_dist_channel_source_stats")
  private List<LiveDistributionByFlowTypeStat> liveDistChannelSourceStats;

  /**
   * 数据版本（无实际意义）
   */
  @JsonProperty("data_key")
  private List<String> dataKey;

}
