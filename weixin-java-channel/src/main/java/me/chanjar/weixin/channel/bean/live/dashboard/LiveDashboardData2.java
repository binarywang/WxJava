package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 直播大屏实体
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveDashboardData2 implements Serializable {

  private static final long serialVersionUID = 3657714024563123097L;

  /**
   * 直播基础数据
   */
  @JsonProperty("summary")
  private LiveDashboardData2Summary summary;

  /**
   * 直播流量渠道
   */
  @JsonProperty("source")
  private LiveDashboardData2Source source;

  /**
   * 直播观众画像
   */
  @JsonProperty("portrait")
  private LiveDashboardData2Portrait portrait;

}
