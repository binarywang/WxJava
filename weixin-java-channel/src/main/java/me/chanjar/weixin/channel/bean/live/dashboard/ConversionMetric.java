package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 转化率
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class ConversionMetric implements Serializable {

  private static final long serialVersionUID = - 3411290344181494863L;

  /**
   * 商品曝光-点击转化率
   */
  @JsonProperty("product_view_click_conversion_ratio")
  private ItemConversionMetric productViewClickConversionRatio;

  /**
   * 气泡曝光-点击转化率
   */
  @JsonProperty("bubble_view_click_conversion_ratio")
  private ItemConversionMetric bubbleViewClickConversionRatio;

  /**
   * 成交转化率
   */
  @JsonProperty("pay_conversion_ratio")
  private ItemConversionMetric payConversionRatio;

  /**
   * 千次观看成交金额（单位：分）
   */
  @JsonProperty("k_view_pay_conversion_ratio")
  private ItemConversionMetric kViewPayConversionRatio;

  /**
   * 更新时间
   */
  @JsonProperty("update_time")
  private Long updateTime;

  /**
   * 购物袋商品点击率
   */
  @JsonProperty("product_list_click_conversion_ratio")
  private ItemConversionMetric productListClickConversionRatio;

  /**
   * 挂车时间（>0 则为挂车）
   */
  @JsonProperty("shelftime")
  private Long shelftime;
}
