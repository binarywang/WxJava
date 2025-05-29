package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 直播大屏数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDashboardData implements Serializable {

  private static final long serialVersionUID = 7917049411269553153L;

  /**
   * 直播大屏数据实体
   */
  @JsonProperty("live_dashboard_data")
  private LiveDashboardData2 liveDashboardData;

  /**
   * 直播时长
   */
  @JsonProperty("live_duration")
  private Long liveDuration;

  /**
   * 直播开始时间
   */
  @JsonProperty("start_time")
  private Long startTime;

}
