package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 转化率环比数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class QuarterlyGrowthRate implements Serializable {

  private static final long serialVersionUID = 1683118806978367016L;

  /**
   * 环比（近10分钟转化率数据才有）
   */
  @JsonProperty("value")
  private Long value;

  /**
   * 环比是否是有效值（如果是false说明分母是0）
   */
  @JsonProperty("is_valid")
  private Boolean isValid;

}
