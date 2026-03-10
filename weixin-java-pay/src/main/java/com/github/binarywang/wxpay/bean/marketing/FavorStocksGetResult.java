package com.github.binarywang.wxpay.bean.marketing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayV3Result;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 查询批次详情结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorStocksGetResult extends BaseWxPayV3Result {

  private static final long serialVersionUID = 1L;

  /**
   * 批次号
   * <p>
   * 微信为每个代金券批次分配的唯一id。
   * 示例值：9836588
   */
  @SerializedName("stock_id")
  private String stockId;

  /**
   * 创建批次的商户号
   * <p>
   * 批次创建方商户号。
   * 示例值：123456
   */
  @SerializedName("stock_creator_mchid")
  private String stockCreatorMchId;

  /**
   * 批次名称
   * <p>
   * 批次名称
   * 示例值：微信支付批次
   */
  @SerializedName("stock_name")
  private String stockName;

  /**
   * 批次状态
   * <p>
   * 批次状态
   * 枚举值：
   * unactivated：未激活
   * audit：审核中
   * running：运行中
   * stoped：已停止
   * paused：暂停发放
   * 示例值：paused
   */
  @SerializedName("status")
  private String status;

  /**
   * 创建时间
   * <p>
   * 批次创建时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 使用说明
   * <p>
   * 批次描述信息
   * 示例值：微信支付营销
   */
  @SerializedName("description")
  private String description;

  /**
   * 满减券批次使用规则
   * <p>
   * 普通发券批次特定信息。
   * 示例值：1900000109
   */
  @SerializedName("stock_use_rule")
  private StockUseRule stockUseRule;

  /**
   * 可用开始时间
   * <p>
   * 可用开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("available_begin_time")
  private String availableBeginTime;

  /**
   * 可用结束时间
   * <p>
   * 可用结束时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("available_end_time")
  private String availableEndTime;

  /**
   * 已发券数量
   * <p>
   * 已发券数量
   * 示例值：100
   */
  @SerializedName("distributed_coupons")
  private Long distributedCoupons;

  /**
   * 是否无资金流
   * <p>
   * 是否无资金流。
   * ture：是
   * false：否
   * 示例值：true
   */
  @SerializedName("no_cash")
  private Boolean noCash;

  /**
   * 激活批次的时间
   * <p>
   * 批次激活开启时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("start_time")
  private String startTime;

  /**
   * 终止批次的时间
   * <p>
   * 批次永久停止时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("stop_time")
  private String stopTime;

  /**
   * 单品优惠特定信息
   * <p>
   * 单品优惠特定信息
   */
  @SerializedName("cut_to_message")
  private CutToMessage cutToMessage;

  /**
   * 是否单品优惠
   * <p>
   * 枚举值：
   * true：是
   * false：否
   * 示例值：true
   */
  @SerializedName("singleitem")
  private Boolean singleItem;

  /**
   * 批次类型
   * <p>
   * 批次类型， 枚举值：
   * NORMAL：代金券批次
   * DISCOUNT_CUT：立减与折扣
   * OTHER：其他
   * 示例值：NORMAL
   */
  @SerializedName("stock_type")
  private String stockType;

  @Data
  @NoArgsConstructor
  public static class CutToMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 可用优惠的商品最高单价
     * <p>
     * 可用优惠的商品最高单价，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "single_price_max")
    private Long singlePriceMax;

    /**
     * 减至后的优惠单价
     * <p>
     * 减至后的优惠单价，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "cut_to_price")
    private Long cutToPrice;
  }

  @Data
  @NoArgsConstructor
  public static class StockUseRule implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 发放总上限
     * <p>
     * 最大发券数
     * 示例值：100
     */
    @SerializedName(value = "max_coupons")
    private Long maxCoupons;

    /**
     * 总预算
     * <p>
     * 总消耗金额，单位：分。
     * 示例值：5000
     */
    @SerializedName(value = "max_amount")
    private Long maxAmount;

    /**
     * 单天发放上限金额
     * <p>
     * 单天最高消耗金额，单位：分。
     * 示例值：400
     */
    @SerializedName(value = "max_amount_by_day")
    private Long maxAmountByDay;

    /**
     * 固定面额批次特定信息
     * <p>
     * 固定面额发券批次特定信息。
     */
    @SerializedName(value = "fixed_normal_coupon")
    private FixedNormalCoupon fixedNormalCoupon;

    /**
     * 单个用户可领个数
     * <p>
     * 单个用户可领个数，每个用户最多100张券
     * 示例值：3
     */
    @SerializedName(value = "max_coupons_per_user")
    private Long maxCouponsPerUser;

    /**
     * 券类型
     * <p>
     * 枚举值：
     * NORMAL：满减券
     * CUT_TO：减至券
     * 示例值：NORMAL
     */
    @SerializedName(value = "coupon_type")
    private String couponType;

    /**
     * 订单优惠标记
     * <p>
     * 订单优惠标记
     * 特殊规则：单个优惠标记的字符长度为【1，128】,条目个数限制为【1，50】。
     * 示例值：{'123456','23456'}
     */
    @SerializedName(value = "goods_tag")
    private List<String> goodsTag;

    /**
     * 支付方式
     * <p>
     * 默认不限制
     * 枚举值：
     * MICROAPP：小程序支付
     * APPPAY：APP支付
     * PPAY：免密支付
     * CARD：付款码支付
     * FACE：人脸支付
     * OTHER：（公众号、扫码等）
     * 示例值：MICROAPP
     */
    @SerializedName(value = "trade_type")
    private List<String> tradeType;

    /**
     * 是否可叠加其他优惠
     * <p>
     * 枚举值：
     * true：是
     * false：否
     * 示例值：true
     */
    @SerializedName(value = "combine_use")
    private Boolean combineUse;
  }

  @Data
  @NoArgsConstructor
  public static class FixedNormalCoupon implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 面额
     * <p>
     * 面额，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "coupon_amount")
    private Long couponAmount;

    /**
     * 门槛
     * <p>
     * 使用券金额门槛，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "transaction_minimum")
    private Long transactionMinimum;
  }
}
