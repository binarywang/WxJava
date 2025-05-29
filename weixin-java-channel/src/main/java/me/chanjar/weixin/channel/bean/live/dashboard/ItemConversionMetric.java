package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 转化率数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class ItemConversionMetric implements Serializable {

  private static final long serialVersionUID = - 8317027740221390754L;

  /**
   * 指标值
   */
  @JsonProperty("metric_value")
  private Double metricValue;

  /**
   * 较近7天中位数
   */
  @JsonProperty("median_to_recent_7_days")
  private Double medianToRecent7Days;

  /**
   * 同行对比
   */
  @JsonProperty("within_industry_percentage")
  private Double withinIndustryPercentage;

  /**
   * 环比数据
   */
  @JsonProperty("quarterly_growth_rate")
  private QuarterlyGrowthRate quarterlyGrowthRate;

}
