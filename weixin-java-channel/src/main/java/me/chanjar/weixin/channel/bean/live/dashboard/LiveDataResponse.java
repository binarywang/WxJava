package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取直播大屏数据响应
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LiveDataResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = - 8416743234527598719L;

  /**
   * 追踪ID，报bug带
   */
  @JsonProperty("trace_id")
  private String traceId;

  /**
   * 直播大屏基础数据
   */
  @JsonProperty("live_dashboard_data")
  private LiveDashboardData liveDashboardData;

  /**
   * 内容力数据
   */
  @JsonProperty("live_comparison_index")
  private LiveComparisonIndex liveComparisonIndex;

  /**
   * 电商数据概要数据
   */
  @JsonProperty("live_ec_data_summary")
  private LiveEcDataSummary liveEcDataSummary;

  /**
   * 电商转化力数据
   */
  @JsonProperty("live_ec_conversion_metric")
  private LiveEcConversionMetric liveEcConversionMetric;

  /**
   * 电商画像数据
   */
  @JsonProperty("live_ec_profile")
  private LiveEcProfile liveEcProfile;

  /**
   * 电商渠道分布
   */
  @JsonProperty("live_distribution_channel")
  private LiveDistributionChannel liveDistributionChannel;

  /**
   * 电商商品数据
   */
  @JsonProperty("single_live_ec_spu_data_page_v2")
  private SingleLiveEcSpuDataPageV2 singleLiveEcSpuDataPageV2;

}
