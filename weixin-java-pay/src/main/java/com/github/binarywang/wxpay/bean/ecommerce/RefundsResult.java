package com.github.binarywang.wxpay.bean.ecommerce;

/**
 * @author f00lish
 * created on  2020/09/17
 */

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 退款结果
 * * <pre>
 *  *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_1.shtml
 *  * </pre>
 *
 * @author f00lish
 * created on  2020/09/14
 */
@Data
@NoArgsConstructor
public class RefundsResult implements Serializable {
  private static final long serialVersionUID = -3186851559004865784L;

  /**
   * <pre>
   * 字段名：微信退款单号
   * 变量名：refund_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付退款订单号。
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "refund_id")
  private String refundId;

  /**
   * <pre>
   * 字段名：商户退款单号
   * 变量名：out_refund_no
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *   商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔。
   * 示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_refund_no")
  private String outRefundNo;

  /**
   * <pre>
   * 字段名：退款创建时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *   退款受理时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
   *   示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName(value = "create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：订单金额
   * 变量名：amount
   * 是否必填：是
   * 类型：object
   * 描述：
   *  订单金额信息
   * </pre>
   */
  @SerializedName(value = "amount")
  private Amount amount;

  /**
   * <pre>
   * 字段名：优惠退款详情
   * 变量名：promotion_detail
   * 是否必填：否
   * 类型：array
   * 描述：
   *   优惠退款功能信息
   * </pre>
   */
  @SerializedName(value = "promotion_detail")
  private PromotionDetail[] promotionDetail;

  @Data
  @NoArgsConstructor
  public static class Amount implements Serializable {

    private static final long serialVersionUID = 7383027142329410399L;

    /**
     * <pre>
     * 字段名：退款金额
     * 变量名：refund
     * 是否必填：是
     * 类型：int
     * 描述：
     *  退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额。
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "refund")
    private Integer refund;

    /**
     * <pre>
     * 字段名：用户退款金额
     * 变量名：payer_refund
     * 是否必填：是
     * 类型：int64
     * 描述：
     *  退款给用户的金额，不包含所有优惠券金额。
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "payer_refund")
    private Integer payerRefund;

    /**
     * <pre>
     * 字段名：优惠退款金额
     * 变量名：discount_refund
     * 是否必填：否
     * 类型：int64
     * 描述：
     *  优惠券的退款金额，原支付单的优惠按比例退款。
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "discount_refund")
    private Integer discountRefund;

    /**
     * <pre>
     * 字段名：币类型
     * 变量名：currency
     * 是否必填：否
     * 类型：string(18)
     * 描述：
     *  符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;

  }

  @Data
  @NoArgsConstructor
  public static class PromotionDetail implements Serializable {

    private static final long serialVersionUID = 7383027142329410399L;

    /**
     * <pre>
     * 字段名：券ID
     * 变量名：promotion_id
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  券或者立减优惠id。
     *  示例值：109519
     * </pre>
     */
    @SerializedName(value = "promotion_id")
    private String promotionId;

    /**
     * <pre>
     * 字段名：优惠范围
     * 变量名：scope
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  枚举值：
     *  GLOBAL：全场代金券
     *  SINGLE：单品优惠
     *  示例值：SINGLE
     * </pre>
     */
    @SerializedName(value = "scope")
    private String scope;

    /**
     * <pre>
     * 字段名：优惠类型
     * 变量名：type
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  枚举值：
     *  COUPON：充值型代金券，商户需要预先充值营销经费
     *  DISCOUNT：免充值型优惠券，商户不需要预先充值营销经费
     *  示例值：DISCOUNT
     * </pre>
     */
    @SerializedName(value = "type")
    private String type;

    /**
     * <pre>
     * 字段名：优惠券面额
     * 变量名：amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  用户享受优惠的金额（优惠券面额=微信出资金额+商家出资金额+其他出资方金额 ）。
     *  示例值：5
     * </pre>
     */
    @SerializedName(value = "amount")
    private Integer amount;

    /**
     * <pre>
     * 字段名：优惠退款金额
     * 变量名：refund_amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见《代金券或立减优惠》https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_1 。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "refund_amount")
    private Integer refundAmount;

  }


  /**
   * <pre>
   * 字段名：退款资金来源
   * 变量名：refund_account
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *   枚举值：
   * REFUND_SOURCE_PARTNER_ADVANCE : 电商平台垫付
   * REFUND_SOURCE_SUB_MERCHANT : 二级商户，默认值
   * 示例值：REFUND_SOURCE_SUB_MERCHANT
   * </pre>
   */
  @SerializedName(value = "refund_account")
  private String refundAccount;


}
