package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据点
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class Point implements Serializable {

  private static final long serialVersionUID = 3332256418933163389L;

  /**
   * 时间戳
   */
  @JsonProperty("ts")
  private Long ts;

  /**
   * 指标值
   */
  @JsonProperty("value")
  private Long value;

}
