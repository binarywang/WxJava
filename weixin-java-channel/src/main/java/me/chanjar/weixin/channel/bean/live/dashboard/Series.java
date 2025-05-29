package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 维度标签的时间序列（与指标的类型无关）
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class Series implements Serializable {

  private static final long serialVersionUID = 507937573085880287L;

  /**
   * 数据点
   */
  @JsonProperty("points")
  private List<Point> points;

  /**
   * 描述时间序列的维度标签
   */
  @JsonProperty("dimensions")
  private List<Dimension> dimensions;

  /**
   * 每个数据点描述的时间长度（秒）
   */
  @JsonProperty("step")
  private Long step;

  /**
   * 该时间序列的起始时间戳
   */
  @JsonProperty("begin_ts")
  private Long beginTs;

  /**
   * 该时间序列的结束时间戳
   */
  @JsonProperty("end_ts")
  private Long endTs;

}
