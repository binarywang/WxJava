package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 直播观众画像
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDashboardData2Portrait implements Serializable {

  private static final long serialVersionUID = - 5603781471063785276L;

  /**
   * 在线人数的画像
   */
  @JsonProperty("online_watch_uv")
  private List<Series> onlineWatchUv;

  /**
   * 观看人数的画像
   */
  @JsonProperty("new_watch_uv")
  private List<Series> newWatchUv;

}
