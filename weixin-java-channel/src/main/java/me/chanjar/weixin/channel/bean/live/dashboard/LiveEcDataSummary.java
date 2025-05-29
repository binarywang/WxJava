package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电商数据概要数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveEcDataSummary implements Serializable {

  private static final long serialVersionUID = - 6634047980552575196L;

  /**
   * 成交金额（单位：分）
   */
  @JsonProperty("total_gmv")
  private Long totalGmv;

  /**
   * 成交次数
   */
  @JsonProperty("total_pay_pv")
  private Long totalPayPv;

  /**
   * 成交人数
   */
  @JsonProperty("total_pay_uv")
  private Long totalPayUv;

  /**
   * 订单创建次数
   */
  @JsonProperty("total_create_pv")
  private Long totalCreatePv;

  /**
   * 订单创建人数
   */
  @JsonProperty("total_create_uv")
  private Long totalCreateUv;

  /**
   * 总点击次数
   */
  @JsonProperty("total_clk_pv")
  private Long totalClkPv;

  /**
   * 总点击人数
   */
  @JsonProperty("total_clk_uv")
  private Long totalClkUv;

  /**
   * 总曝光次数
   */
  @JsonProperty("total_exp_pv")
  private Long totalExpPv;

  /**
   * 总曝光人数
   */
  @JsonProperty("total_exp_uv")
  private Long totalExpUv;

  /**
   * 在线观众数
   */
  @JsonProperty("online_audience_count")
  private Long onlineAudienceCount;

  /**
   * 累计观众数
   */
  @JsonProperty("cumulative_audience_count")
  private Long cumulativeAudienceCount;

  /**
   * 新增观众数
   */
  @JsonProperty("new_audience_count")
  private Long newAudienceCount;

  /**
   * 剩余观众数
   */
  @JsonProperty("leaved_audience_count")
  private Long leavedAudienceCount;

  /**
   * 观众平均观看秒数
   */
  @JsonProperty("average_watch_seconds_per_audience")
  private Long averageWatchSecondsPerAudience;

  /**
   * 新增关注数
   */
  @JsonProperty("new_follow_count")
  private Long newFollowCount;

  /**
   * 新增评论数
   */
  @JsonProperty("new_comment_count")
  private Long newCommentCount;

  /**
   * 分享直播观众数
   */
  @JsonProperty("share_live_audience_count")
  private Long shareLiveAudienceCount;

  /**
   * 新粉丝俱乐部数
   */
  @JsonProperty("new_fans_club_count")
  private Long newFansClubCount;

  /**
   * 退费次数
   */
  @JsonProperty("refund_pv")
  private Long refundPv;

  /**
   * 退费人数
   */
  @JsonProperty("refund_uv")
  private Long refundUv;

  /**
   * 退费率
   */
  @JsonProperty("refund_rate")
  private Double refundRate;

  /**
   * 退款金额（单位：分）
   */
  @JsonProperty("refund_amount")
  private Long refundAmount;

  /**
   * 退费商品件数
   */
  @JsonProperty("refund_product_cnt")
  private Long refundProductCnt;

  /**
   * 广告累计观众数
   */
  @JsonProperty("ads_cumulative_audience_count")
  private Long adsCumulativeAudienceCount;

  /**
   * 广告累计观看数
   */
  @JsonProperty("ads_cumulative_watch_count")
  private Long adsCumulativeWatchCount;

  /**
   * 促销累计观看数
   */
  @JsonProperty("promotion_cumulative_watch_count")
  private Long promotionCumulativeWatchCount;

  /**
   * 千次看播成交总额
   */
  @JsonProperty("gmv_per_thousand_cumulative_watch_pv")
  private Double gmvPerThousandCumulativeWatchPv;

  /**
   * 观众成交率
   */
  @JsonProperty("audience_pay_ratio")
  private Double audiencePayRatio;

  /**
   * 点击成交率
   */
  @JsonProperty("clk_pay_ratio")
  private Double clkPayRatio;

  /**
   * 新买家人数
   */
  @JsonProperty("new_buyer_uv")
  private Long newBuyerUv;

  /**
   * 老买家人数
   */
  @JsonProperty("old_buyer_uv")
  private Long oldBuyerUv;

  /**
   * 客户价格
   */
  @JsonProperty("customer_price")
  private Long customerPrice;

}
