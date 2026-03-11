package com.github.binarywang.wxpay.bean.marketing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayV3Result;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询代金券详情结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorCouponsGetResult extends BaseWxPayV3Result {

  private static final long serialVersionUID = 1L;

  /**
   * 创建批次的商户号
   * <p>
   * 批次创建方商户号
   * 示例值：9800064
   */
  @SerializedName("stock_creator_mchid")
  private String stockCreatorMchid;

  /**
   * 批次号
   * <p>
   * 微信为每个代金券批次分配的唯一id。
   * 示例值：9865888
   */
  @SerializedName("stock_id")
  private String stockId;

  /**
   * 代金券id
   * <p>
   * 微信为代金券唯一分配的id。
   * 示例值：98674556
   */
  @SerializedName("coupon_id")
  private String couponId;

  /**
   * 单品优惠特定信息
   * <p>
   * 单品优惠特定信息
   */
  @SerializedName("cut_to_message")
  private CutToMessage cutToMessage;

  /**
   * 代金券名称
   * <p>
   * 代金券名称
   * 示例值：微信支付代金券
   */
  @SerializedName("coupon_name")
  private String couponName;

  /**
   * 代金券状态
   * <p>
   * 代金券状态：
   * SENDED：可用
   * USED：已实扣
   * EXPIRED：已过期
   * 示例值：EXPIRED
   */
  @SerializedName("status")
  private String status;

  /**
   * 使用说明
   * <p>
   * 代金券描述说明字段。
   * 示例值：微信支付营销
   */
  @SerializedName("description")
  private String description;

  /**
   * 领券时间
   * <p>
   * 领券时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值: 2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 券类型
   * <p>
   * 券类型：
   * NORMAL：满减券
   * CUT_TO：减至券
   * 示例值：CUT_TO
   */
  @SerializedName("coupon_type")
  private String couponType;

  /**
   * 是否无资金流
   * <p>
   * 枚举值：
   * true：是
   * false：否
   * 示例值：true
   */
  @SerializedName("no_cash")
  private Boolean noCash;

  /**
   * 可用开始时间
   * <p>
   * 可用开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值: 2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("available_begin_time")
  private String availableBeginTime;

  /**
   * 可用结束时间
   * <p>
   * 可用结束时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值: 2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("available_end_time")
  private String availableEndTime;

  /**
   * 是否单品优惠
   * <p>
   * 枚举值：
   * true：是
   * false：否
   * 示例值：true
   */
  @SerializedName("singleitem")
  private Boolean singleitem;

  /**
   * 商户单据号
   * <p>
   * 商户此次发放凭据号（格式：商户id+日期+流水号），可包含英文字母，数字，|，_，*，-等内容，不允许出现其他不合法符号，商户侧需保持唯一性。
   * 示例值: 89560002019101000121
   */
  @SerializedName("out_request_no")
  private String outRequestNo;

  /**
   * 满减券信息
   * <p>
   * 普通满减券面额、门槛信息。
   */
  @SerializedName("normal_coupon_information")
  private NormalCouponInformation normalCouponInformation;

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
  public static class NormalCouponInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 面额
     * <p>
     * 面额，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "coupon_amount")
    private Integer couponAmount;

    /**
     * 门槛
     * <p>
     * 使用券金额门槛，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "transaction_minimum")
    private Integer transactionMinimum;
  }
}
