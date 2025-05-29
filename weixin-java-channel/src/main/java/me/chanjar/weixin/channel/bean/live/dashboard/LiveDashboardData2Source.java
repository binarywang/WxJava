package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 直播流量渠道
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDashboardData2Source implements Serializable {

  private static final long serialVersionUID = 7347276250944913612L;

  /**
   * 观看人数的渠道分布
   */
  @JsonProperty("new_watch_uv")
  private List<Series> newWatchUv;

}
