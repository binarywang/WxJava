package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 直播基础数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDashboardData2Summary implements Serializable {

  private static final long serialVersionUID = - 9029702302333930066L;

  /**
   * 观看人数
   */
  @JsonProperty("new_watch_uv")
  private Long newWatchUv;

  /**
   * 最大在线人数
   */
  @JsonProperty("max_online_watch_uv")
  private Long maxOnlineWatchUv;

  /**
   * 曝光人数
   */
  @JsonProperty("impression_uv")
  private Long impressionUv;

  /**
   * 平均观看时长（秒）
   */
  @JsonProperty("average_watch_seconds_per_audience")
  private Long averageWatchSecondsPerAudience;

  /**
   * 新增关注人数
   */
  @JsonProperty("new_follow_uv")
  private Long newFollowUv;

  /**
   * 新增粉丝团人数
   */
  @JsonProperty("new_fans_club_uv")
  private Long newFansClubUv;

  /**
   * 评论人数
   */
  @JsonProperty("comment_uv")
  private Long commentUv;

  /**
   * 打赏人数
   */
  @JsonProperty("reward_uv")
  private Long rewardUv;

  /**
   * 分享直播间人数
   */
  @JsonProperty("sharing_uv")
  private Long sharingUv;

  /**
   * 热度
   */
  @JsonProperty("hot_quota")
  private Long hotQuota;

}
