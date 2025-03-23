package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 尺码表
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SpuSizeChartItem implements Serializable {

  private static final long serialVersionUID = -3757716378584654974L;

  /** 尺码属性名称 */
  @JsonProperty("name")
  private String name;

  /** 尺码属性值的单位 */
  @JsonProperty("unit")
  private String unit;

  /** 尺码属性值是否为区间 */
  @JsonProperty("is_range")
  private Boolean range;

  /** 尺码值与尺码属性值的映射列表 */
  @JsonProperty("value_list")
  private List<ValueRange> valueList;

  @Data
  @NoArgsConstructor
  public static class ValueRange implements Serializable {
    /** 尺码值 */
    @JsonProperty("key")
    private String key;

    /** 尺码属性值；尺码属性值为非区间时返回 */
    @JsonProperty("value")
    private String value;

    /** 尺码属性值的左边界；尺码属性值为区间时返回 */
    @JsonProperty("left")
    private String left;

    /** 尺码属性值的右边界；尺码属性值为区间时返回 */
    @JsonProperty("right")
    private String right;
  }
}
