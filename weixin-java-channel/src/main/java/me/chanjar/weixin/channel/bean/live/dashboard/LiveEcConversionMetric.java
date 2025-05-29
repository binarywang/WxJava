package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电商转化力数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveEcConversionMetric implements Serializable {

  private static final long serialVersionUID = - 7332281175637902883L;

  /**
   * 近10分钟转化率数据
   */
  @JsonProperty("recent_10_min_conversion")
  private ConversionMetric recent10MinConversion;

  /**
   * 整场直播
   */
  @JsonProperty("whole_live_conversion")
  private ConversionMetric wholeLiveConversion;

}
