package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 微信支付-申请退款请求参数
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxPayRefundV3Request implements Serializable {
  private static final long serialVersionUID = -1L;
  /**
   * <pre>
   * 字段名：微信支付订单号
   * 变量名：transaction_id
   * 是否必填：与out_order_no二选一
   * 类型：string[1, 32]
   * 描述：
   *  原支付交易对应的微信订单号。
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;
  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：与transaction_id二选一
   * 类型：string[1, 32]
   * 描述：
   *  原支付交易对应的商户订单号。
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;
  /**
   * <pre>
   * 字段名：商户退款单号
   * 变量名：out_refund_no
   * 是否必填：是
   * 类型：string[1, 64]
   * 描述：
   *   商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_refund_no")
  private String outRefundNo;
  /**
   * <pre>
   * 字段名：退款原因
   * 变量名：reason
   * 是否必填：否
   * 类型：string[1, 80]
   * 描述：
   *  若商户传入，会在下发给用户的退款消息中体现退款原因。
   *  示例值：商品已售完
   * </pre>
   */
  @SerializedName(value = "reason")
  private String reason;
  /**
   * <pre>
   * 字段名：退款结果回调url
   * 变量名：notify_url
   * 是否必填：否
   * 类型：string[8, 256]
   * 描述：
   *  异步接收微信支付退款结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的这个地址。
   *  示例值：https://weixin.qq.com
   * </pre>
   */
  @SerializedName(value = "notify_url")
  private String notifyUrl;
  /**
   * <pre>
   * 字段名：退款资金来源
   * 变量名：funds_account
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  若传递此参数则使用对应的资金账户退款，否则默认使用未结算资金退款（仅对老资金流商户适用）
   *  示例值：
   *    UNSETTLED : 未结算资金
   *    AVAILABLE : 可用余额
   *    UNAVAILABLE : 不可用余额
   *    OPERATION : 运营户
   *    BASIC : 基本账户（含可用余额和不可用余额）
   * </pre>
   */
  @SerializedName(value = "funds_account")
  private String fundsAccount;
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
   * 字段名：退款商品
   * 变量名：goods_detail
   * 是否必填：否
   * 类型：array
   * 描述：
   *  指定商品退款需要传此参数，其他场景无需传递。
   * </pre>
   */
  @SerializedName(value = "goods_detail")
  private List<GoodsDetail> goodsDetails;

  @Data
  @NoArgsConstructor
  public static class Amount implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 类型：int
     * 描述：
     *  原支付交易的订单总金额，币种的最小单位，只能为整数。
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "total")
    private Integer total;
    /**
     * <pre>
     * 字段名：币类型
     * 变量名：currency
     * 是否必填：是
     * 类型：string[1, 16]
     * 描述：
     *  符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;
    /**
     * <pre>
     * 字段名：退款出资账户及金额
     * 变量名：from
     * 是否必填：否
     * 类型：array
     * 描述：
     *  退款出资的账户类型及金额信息
     * </pre>
     */
    @SerializedName(value = "from")
    private List<From> from;
  }

  @Data
  @NoArgsConstructor
  public static class From implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：出资账户类型
     * 变量名：account
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  下面枚举值多选一。
     *  枚举值：
     *  AVAILABLE : 可用余额
     *  UNAVAILABLE : 不可用余额
     *  示例值：AVAILABLE
     * </pre>
     */
    @SerializedName(value = "account")
    private String account;
    /**
     * <pre>
     * 字段名：出资金额
     * 变量名：amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  对应账户出资金额
     *  示例值：444
     * </pre>
     */
    @SerializedName(value = "amount")
    private Integer amount;
  }

  @Data
  @NoArgsConstructor
  public static class GoodsDetail implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * <pre>
     * 字段名：商户侧商品编码
     * 变量名：merchant_goods_id
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @SerializedName(value = "merchant_goods_id")
    private String merchantGoodsId;
    /**
     * <pre>
     * 字段名：微信侧商品编码
     * 变量名：wechatpay_goods_id
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  微信支付定义的统一商品编号（没有可不传）。
     *  示例值：1001
     * </pre>
     */
    @SerializedName(value = "wechatpay_goods_id")
    private String wechatpayGoodsId;
    /**
     * <pre>
     * 字段名：商品名称
     * 变量名：goods_name
     * 是否必填：否
     * 类型：string[1,256]
     * 描述：
     *  商品的实际名称。
     *  示例值：iPhone6s 16G
     * </pre>
     */
    @SerializedName(value = "goods_name")
    private String goodsName;
    /**
     * <pre>
     * 字段名：商品单价
     * 变量名：unit_price
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商品单价金额，单位为分。
     *  示例值：528800
     * </pre>
     */
    @SerializedName(value = "unit_price")
    private Integer unitPrice;
    /**
     * <pre>
     * 字段名：商品退款金额
     * 变量名：refund_amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商品退款金额，单位为分。
     *  示例值：528800
     * </pre>
     */
    @SerializedName(value = "refund_amount")
    private Integer refundAmount;
    /**
     * <pre>
     * 字段名：商品退货数量
     * 变量名：refund_quantity
     * 是否必填：是
     * 类型：int
     * 描述：
     *  单品的退款数量。
     *  示例值：1
     * </pre>
     */
    @SerializedName(value = "refund_quantity")
    private Integer refundQuantity;
  }

  /**
   * <pre>
   * 字段名：子商户的商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  子商户商户号，由微信支付生成并下发。
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;
}
