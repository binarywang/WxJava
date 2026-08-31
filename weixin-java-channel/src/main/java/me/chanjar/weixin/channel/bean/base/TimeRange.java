package me.chanjar.weixin.channel.bean.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 时间范围
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.base.TimeRange}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class TimeRange implements Serializable {

  private static final long serialVersionUID = -8149679871789511479L;

  /** 开始时间 秒级时间戳 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 结束时间 秒级时间戳 */
  @JsonProperty("end_time")
  private Long endTime;
}
