package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 查询退款结果
 * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_2.shtml
 */
@Data
@NoArgsConstructor
public class RefundQueryResult implements Serializable {

  /**
   * <pre>
   * 字段名：微信退款单号
   * 变量名：refund_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信退款单号
   * 示例值： 1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "refund_id")
  private String refundId;

  /**
   * <pre>
   * 字段名：商户退款单号
   * 变量名：out_refund_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户退款单号
   * 示例值： 1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_refund_no")
  private String outRefundNo;

  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付订单号
   * 示例值： 1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  返回的商户订单号
   * 示例值： 1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 字段名：退款渠道
   * 变量名：channel
   * 是否必填：否
   * 类型：string（16）
   * 描述：
   *  ORIGINAL：原路退款
   *  BALANCE：退回到余额
   *  OTHER_BALANCE：原账户异常退到其他余额账户
   *  OTHER_BANKCARD：原银行卡异常退到其他银行卡
   * 示例值： ORIGINAL
   * </pre>
   */
  @SerializedName(value = "channel")
  private String channel;

  /**
   * <pre>
   * 字段名：退款入账账户
   * 变量名：user_received_account
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  取当前退款单的退款入账方。
   *    退回银行卡：{银行名称}{卡类型}{卡尾号}
   *    退回支付用户零钱: 支付用户零钱
   *    退还商户: 商户基本账户、商户结算银行账户
   *    退回支付用户零钱通：支付用户零钱通
   * 示例值：招商银行信用卡0403
   * </pre>
   */
  @SerializedName(value = "user_received_account")
  private String userReceivedAccount;

  /**
   * <pre>
   * 字段名：退款成功时间
   * 变量名：success_time
   * 是否必填：否
   * 类型：string（64）
   * 描述：
   *  1、退款成功时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，
   *  表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
   * 2、当退款状态为退款成功时返回此参数。
   * 示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName(value = "success_time")
  private String successTime;

  /**
   * <pre>
   * 字段名：退款创建时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  1、退款受理时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，
   *  表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
   * 2、当退款状态为退款成功时返回此字段。
   * 示例值：2018-06-08T10:34:56+08:00
   * </pre>
   */
  @SerializedName(value = "create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：退款状态
   * 变量名：status
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  退款状态，枚举值：
   *    SUCCESS：退款成功
   *    REFUNDCLOSE：退款关闭
   *    PROCESSING：退款处理中
   *    ABNORMAL：退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往【服务商平台—>交易中心】，手动处理此笔退款
   * 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;

  /**
   * <pre>
   * 字段名：金额信息
   * 变量名：amount
   * 是否必填：是
   * 类型：object
   * 描述：
   *  金额信息
   * </pre>
   */
  @SerializedName(value = "amount")
  private Amount amount;

  /**
   * <pre>
   * 字段名：营销详情
   * 变量名：promotion_detail
   * 是否必填：否
   * 类型：array
   * 描述：
   *  优惠退款信息
   * </pre>
   */
  public List<PromotionDetail> promotionDetails;


  /**
   * <pre>
   * 字段名：退款出资商户
   * 变量名：refund_account
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *   电商平台垫资退款专用参数。需先确认已开通此功能后，才能使用。若需要开通，请联系微信支付客服。
   *   枚举值：
   *   REFUND_SOURCE_PARTNER_ADVANCE : 电商平台垫付，需要向微信支付申请开通
   *   REFUND_SOURCE_SUB_MERCHANT : 二级商户，默认值
   *   注意：
   *   若传入REFUND_SOURCE_PARTNER_ADVANCE，仅代表可以使用垫付退款，实际出款账户需以退款申请受理结果或查单结果为准。
   *   示例值：REFUND_SOURCE_SUB_MERCHANT
   * </pre>
   */
  @SerializedName(value = "refund_account")
  private String refundAccount;

  /**
   * <pre>
   * 字段名：资金账户
   * 变量名：funds_account
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *   若订单处于待分账状态，且未指定垫资退款（即refund_account未指定为REFUND_SOURCE_PARTNER_ADVANCE），
   *   可以传入此参数，指定退款资金来源账户。当该字段不存在时，默认使用订单交易资金所在账户出款，
   *   即待分账时使用不可用余额的资金进行退款，已分账或无分账时使用可用余额的资金进行退款。 AVAILABLE：可用余额
   * 示例值：AVAILABLE
   * </pre>
   */
  @SerializedName(value = "funds_account")
  private String fundsAccount;



  @Data
  @NoArgsConstructor
  public static class Amount implements Serializable {

    /**
     * <pre>
     * 字段名：退款金额
     * 变量名：refund
     * 是否必填：是
     * 类型：int
     * 描述：
     *  退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额。
     * 示例值：888
     * </pre>
     */
    @SerializedName(value = "refund")
    private String refund;

    /**
     * <pre>
     * 字段名：用户退款金额
     * 变量名：payer_refund
     * 是否必填：是
     * 类型：int
     * 描述：
     *  退款给用户的金额，不包含所有优惠券金额。
     * 示例值：888
     * </pre>
     */
    @SerializedName(value = "payer_refund")
    private String payerRefund;

    /**
     * <pre>
     * 字段名：优惠退款金额
     * 变量名：discount_refund
     * 是否必填：否
     * 类型：int
     * 描述：
     *  优惠券的退款金额，原支付单的优惠按比例退款。
     * 示例值：888
     * </pre>
     */
    @SerializedName(value = "discount_refund")
    private Integer discountRefund;


    /**
     * <pre>
     * 字段名：退款币种
     * 变量名：currency
     * 是否必填：否
     * 类型：string(18)
     * 描述：
     *  符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY 。
     * 示例值： CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;

  }

  @Data
  @NoArgsConstructor
  public static class PromotionDetail implements Serializable {

    /**
     * <pre>
     * 字段名：券ID
     * 变量名：promotion_id
     * 是否必填：是
     * 类型：string（32）
     * 描述：券或者立减优惠id 。
     * 示例值：109519
     * </pre>
     */
    @SerializedName(value = "promotion_id")
    private String promotionId;

    /**
     * <pre>
     * 字段名：优惠范围
     * 变量名：scope
     * 是否必填：是
     * 类型：string（32）
     * 描述： 优惠范围
     * 枚举值：
     *    GLOBAL：全场代金券
     *    SINGLE：单品优惠
     * 示例值：GLOBAL
     * </pre>
     */
    @SerializedName(value = "scope")
    private String scope;

    /**
     * <pre>
     * 字段名：优惠类型
     * 变量名：type
     * 是否必填：是
     * 类型：string（32）
     * 描述：
     *   枚举值：
     *    COUPON：充值型代金券，商户需要预先充值营销经费
     *    DISCOUNT：免充值型优惠券，商户不需要预先充值营销经费
     * 示例值：DISCOUNT
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
     * 描述： 用户享受优惠的金额（优惠券面额=微信出资金额+商家出资金额+其他出资方金额 ）。
     * 示例值：5
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
     * 描述： 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见《代金券或立减优惠》。
     * 示例值：100
     * </pre>
     */
    @SerializedName(value = "refund_amount")
    private Integer refundAmount;

  }
}
