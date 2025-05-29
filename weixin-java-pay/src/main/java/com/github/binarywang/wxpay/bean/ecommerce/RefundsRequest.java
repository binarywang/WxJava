package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * 退款申请
 * * <pre>
 *  *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/refunds/chapter3_1.shtml
 *  * </pre>
 *
 * @author f00lish
 * created on  2020/09/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundsRequest implements Serializable {
  private static final long serialVersionUID = -3186851559004865784L;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付分配二级商户的商户号。
   *  示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：电商平台APPID
   * 变量名：sp_appid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  电商平台在微信公众平台申请服务号对应的APPID，申请商户功能的时候微信支付会配置绑定关系。
   *  示例值：wx8888888888888888
   * </pre>
   */
  @SerializedName(value = "sp_appid")
  private String spAppid;

  /**
   * <pre>
   * 字段名：二级商户APPID
   * 变量名：sub_appid
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  二级商户在微信申请公众号成功后分配的帐号ID，需要电商平台侧配置绑定关系才能传参。
   *  示例值：wxd678efh567hg6999
   * </pre>
   */
  @SerializedName(value = "sub_appid")
  private String subAppid;

  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：与out_order_no二选一
   * 类型：string（32）
   * 描述：
   *  微信支付订单号。
   *  示例值：4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：与transaction_id二选一
   * 类型：string（64）
   * 描述：
   *   原支付交易对应的商户订单号。
   *   示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 字段名：商户退款单号
   * 变量名：out_refund_no
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *   商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@，同一退款单号多次请求只退一笔。
   *   示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_refund_no")
  private String outRefundNo;

  /**
   * <pre>
   * 字段名：退款原因
   * 变量名：reason
   * 是否必填：是
   * 类型：string（80）
   * 描述：
   *   若商户传入，会在下发给用户的退款消息中体现退款原因。
   *   注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
   *   示例值：商品已售完
   * </pre>
   */
  @SerializedName(value = "reason")
  private String reason;

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
   * 字段名：退款结果回调url
   * 变量名：notify_url
   * 是否必填：是
   * 类型：string（256）
   * 描述：
   *   异步接收微信支付退款结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的地址。
   *   示例值：https://weixin.qq.com
   * </pre>
   */
  @SerializedName(value = "notify_url")
  private String notifyUrl;

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
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
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
     * 字段名：原订单金额
     * 变量名：total
     * 是否必填：是
     * 类型：int64
     * 描述：
     *  订单总金额，单位为分。
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "total")
    private Integer total;

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

}
