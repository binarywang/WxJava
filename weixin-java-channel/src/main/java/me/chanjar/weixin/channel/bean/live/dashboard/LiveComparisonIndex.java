package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 内容力数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveComparisonIndex implements Serializable {

  private static final long serialVersionUID = 525214144965479881L;

  /**
   * 是否正在直播
   */
  @JsonProperty("is_living")
  private Boolean isLiving;

  /**
   * 在播数据
   */
  @JsonProperty("on_air")
  private OnAir onAir;

  /**
   * 关播数据
   */
  @JsonProperty("ended")
  private Ended ended;

}
