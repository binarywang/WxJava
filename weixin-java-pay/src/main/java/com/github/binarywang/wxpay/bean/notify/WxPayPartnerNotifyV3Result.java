package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 微信支付服务商下单回调，文档：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_5.shtml">文档</a>
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/3/2
 */
@Data
@NoArgsConstructor
public class WxPayPartnerNotifyV3Result implements Serializable, WxPayBaseNotifyV3Result<WxPayPartnerNotifyV3Result.DecryptNotifyResult> {
  private static final long serialVersionUID = -1L;
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：服务商应用ID
     * 变量名：spAppid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  由微信生成的应用ID，全局唯一。请求统一下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID
     *  示例值：wxd678efh567hg6787
     * </pre>
     */
    @SerializedName(value = "sp_appid")
    protected String spAppid;
    /**
     * <pre>
     * 字段名：服务商商户号
     * 变量名：spMchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  服务商商户号，由微信支付生成并下发。
     *  示例值：1230000109
     * </pre>
     */
    @SerializedName(value = "sp_mchid")
    protected String spMchid;
    /**
     * <pre>
     * 字段名：子商户应用ID
     * 变量名：subAppid
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  由微信生成的应用ID，全局唯一。请求统一下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID
     *  示例值：wxd678efh567hg6787
     * </pre>
     */
    @SerializedName(value = "sub_appid")
    protected String subAppid;
    /**
     * <pre>
     * 字段名：子商户商户号
     * 变量名：subMchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  子商户商户号，由微信支付生成并下发。
     *  示例值：1230000109
     * </pre>
     */
    @SerializedName(value = "sub_mchid")
    protected String subMchid;
    /**
     * <pre>
     * 字段名：商户订单号
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string[6,32]
     * 描述：
     *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
     *  特殊规则：最小字符长度为6
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @SerializedName(value = "out_trade_no")
    private String outTradeNo;
    /**
     * <pre>
     * 字段名：微信支付订单号
     * 变量名：transaction_id
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  微信支付系统生成的订单号。
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @SerializedName(value = "transaction_id")
    private String transactionId;
    /**
     * <pre>
     * 字段名：交易类型
     * 变量名：trade_type
     * 是否必填：是
     * 类型：string[1,16]
     * 描述：
     *  交易类型，枚举值：
     *  JSAPI：公众号支付
     *  NATIVE：扫码支付
     *  APP：APP支付
     *  MICROPAY：付款码支付
     *  MWEB：H5支付
     *  FACEPAY：刷脸支付
     *  示例值：MICROPAY
     * </pre>
     */
    @SerializedName(value = "trade_type")
    private String tradeType;
    /**
     * <pre>
     * 字段名：交易状态
     * 变量名：trade_state
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  交易状态，枚举值：
     *  SUCCESS：支付成功
     *  REFUND：转入退款
     *  NOTPAY：未支付
     *  CLOSED：已关闭
     *  REVOKED：已撤销（付款码支付）
     *  USERPAYING：用户支付中（付款码支付）
     *  PAYERROR：支付失败(其他原因，如银行返回失败)
     *  示例值：SUCCESS
     * </pre>
     */
    @SerializedName(value = "trade_state")
    private String tradeState;
    /**
     * <pre>
     * 字段名：交易状态描述
     * 变量名：trade_state_desc
     * 是否必填：是
     * 类型：string[1,256]
     * 描述：
     *  交易状态描述
     *  示例值：支付成功
     * </pre>
     */
    @SerializedName(value = "trade_state_desc")
    private String tradeStateDesc;
    /**
     * <pre>
     * 字段名：付款银行
     * 变量名：bank_type
     * 是否必填：是
     * 类型：string[1,16]
     * 描述：
     *  银行类型，采用字符串类型的银行标识。银行标识请参考《银行类型对照表》https://pay.weixin.qq.com/wiki/doc/apiv3/terms_definition/chapter1_1_3.shtml#part-6
     *  示例值：CMC
     * </pre>
     */
    @SerializedName(value = "bank_type")
    private String bankType;
    /**
     * <pre>
     * 字段名：附加数据
     * 变量名：attach
     * 是否必填：否
     * 类型：string[1,128]
     * 描述：
     *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     *  示例值：自定义数据
     * </pre>
     */
    @SerializedName(value = "attach")
    private String attach;
    /**
     * <pre>
     * 字段名：支付完成时间
     * 变量名：success_time
     * 是否必填：是
     * 类型：string[1,64]
     * 描述：
     *  支付完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     *  示例值：2018-06-08T10:34:56+08:00
     * </pre>
     */
    @SerializedName(value = "success_time")
    private String successTime;
    /**
     * <pre>
     * 字段名：支付者
     * 变量名：payer
     * 是否必填：是
     * 类型：object
     * 描述：
     *  支付者信息
     * </pre>
     */
    private Payer payer;
    /**
     * <pre>
     * 字段名：订单金额
     * 变量名：amount
     * 是否必填：否
     * 类型：object
     * 描述：
     *  订单金额信息
     * </pre>
     */
    @SerializedName(value = "amount")
    private Amount amount;
    /**
     * <pre>
     * 字段名：场景信息
     * 变量名：scene_info
     * 是否必填：否
     * 类型：object
     * 描述：
     *  支付场景信息描述
     * </pre>
     */
    @SerializedName(value = "scene_info")
    private SceneInfo sceneInfo;
    /**
     * <pre>
     * 字段名：优惠功能
     * 变量名：promotion_detail
     * 是否必填：否
     * 类型：array
     * 描述：
     *  优惠功能，享受优惠时返回该字段。
     * </pre>
     */
    @SerializedName(value = "promotion_detail")
    private List<PromotionDetail> promotionDetails;
  }

  @Data
  @NoArgsConstructor
  public static class Payer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：用户标识
     * 变量名：openid
     * 是否必填：是
     * 类型：string[1,128]
     * 描述：
     *  用户在直连商户appid下的唯一标识。
     *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * </pre>
     */
    @SerializedName(value = "openid")
    private String openid;

    /**
     * <pre>
     * 字段名：用户服务标识
     * 变量名：sp_openid
     * 是否必填：是
     * 类型：string[1,128]
     * 描述：
     *  用户在服务商appid下的唯一标识。
     *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * </pre>
     */
    @SerializedName(value = "sp_openid")
    private String spOpenid;

    /**
     * <pre>
     * 字段名：用户子标识
     * 变量名：sub_openid
     * 是否必填：否
     * 类型：string[1,128]
     * 描述：
     * 用户在子商户appid下的唯一标识。
     *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * </pre>
     */
    @SerializedName(value = "sub_openid")
    private String subOpenid;
  }

  @Data
  @NoArgsConstructor
  public static class Amount implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：总金额
     * 变量名：total
     * 是否必填：否
     * 类型：int
     * 描述：
     *  订单总金额，单位为分。
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "total")
    private Integer total;
    /**
     * <pre>
     * 字段名：用户支付金额
     * 变量名：payer_total
     * 是否必填：否
     * 类型：int
     * 描述：
     *  用户支付金额，单位为分。
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "payer_total")
    private Integer payerTotal;
    /**
     * <pre>
     * 字段名：货币类型
     * 变量名：currency
     * 是否必填：否
     * 类型：string[1,16]
     * 描述：
     *  CNY：人民币，境内商户号仅支持人民币。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;
    /**
     * <pre>
     * 字段名：用户支付币种
     * 变量名：payer_currency
     * 是否必填：否
     * 类型：string[1,16]
     * 描述：
     *  用户支付币种
     *  示例值： CNY
     * </pre>
     */
    @SerializedName(value = "payer_currency")
    private String payerCurrency;
  }

  @Data
  @NoArgsConstructor
  public static class SceneInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：商户端设备号
     * 变量名：device_id
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  终端设备号（门店号或收银设备ID）。
     *  示例值：013467007045764
     * </pre>
     */
    @SerializedName(value = "device_id")
    private String deviceId;
  }

  @Data
  @NoArgsConstructor
  public static class PromotionDetail implements Serializable {
    /**
     * <pre>
     * 字段名：券ID
     * 变量名：coupon_id
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  券ID
     *  示例值：109519
     * </pre>
     */
    @SerializedName(value = "coupon_id")
    private String couponId;
    /**
     * <pre>
     * 字段名：优惠名称
     * 变量名：name
     * 是否必填：否
     * 类型：string[1,64]
     * 描述：
     *  优惠名称
     *  示例值：单品惠-6
     * </pre>
     */
    @SerializedName(value = "name")
    private String name;
    /**
     * <pre>
     * 字段名：优惠范围
     * 变量名：scope
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  GLOBAL：全场代金券
     *  SINGLE：单品优惠
     *  示例值：GLOBAL
     * </pre>
     */
    @SerializedName(value = "scope")
    private String scope;
    /**
     * <pre>
     * 字段名：优惠类型
     * 变量名：type
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  CASH：充值
     *  NOCASH：预充值
     *  示例值：CASH
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
     *  优惠券面额
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "amount")
    private Integer amount;
    /**
     * <pre>
     * 字段名：活动ID
     * 变量名：stock_id
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  活动ID
     *  示例值：931386
     * </pre>
     */
    @SerializedName(value = "stock_id")
    private String stockId;
    /**
     * <pre>
     * 字段名：微信出资
     * 变量名：wechatpay_contribute
     * 是否必填：否
     * 类型：int
     * 描述：
     *  微信出资，单位为分
     *  示例值：0
     * </pre>
     */
    @SerializedName(value = "wechatpay_contribute")
    private Integer wechatpayContribute;
    /**
     * <pre>
     * 字段名：商户出资
     * 变量名：merchant_contribute
     * 是否必填：否
     * 类型：int
     * 描述：
     *  商户出资，单位为分
     *  示例值：0
     * </pre>
     */
    @SerializedName(value = "merchant_contribute")
    private Integer merchantContribute;
    /**
     * <pre>
     * 字段名：其他出资
     * 变量名：other_contribute
     * 是否必填：否
     * 类型：int
     * 描述：
     *  其他出资，单位为分
     *  示例值：0
     * </pre>
     */
    @SerializedName(value = "other_contribute")
    private Integer otherContribute;
    /**
     * <pre>
     * 字段名：优惠币种
     * 变量名：currency
     * 是否必填：否
     * 类型：string[1,16]
     * 描述：
     *  CNY：人民币，境内商户号仅支持人民币。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;
    /**
     * <pre>
     * 字段名：单品列表
     * 变量名：goods_detail
     * 是否必填：否
     * 类型：array
     * 描述：
     *  单品列表信息
     * </pre>
     */
    @SerializedName(value = "goods_detail")
    private List<GoodsDetail> goodsDetails;
  }

  @Data
  @NoArgsConstructor
  public static class GoodsDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：商品编码
     * 变量名：goods_id
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  商品编码
     *  示例值：M1006
     * </pre>
     */
    @SerializedName(value = "goods_id")
    private String goodsId;
    /**
     * <pre>
     * 字段名：商品数量
     * 变量名：quantity
     * 是否必填：是
     * 类型：int
     * 描述：
     *  用户购买的数量
     *  示例值：1
     * </pre>
     */
    @SerializedName(value = "quantity")
    private Integer quantity;
    /**
     * <pre>
     * 字段名：商品单价
     * 变量名：unit_price
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商品单价，单位为分
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "unit_price")
    private Integer unitPrice;
    /**
     * <pre>
     * 字段名：商品优惠金额
     * 变量名：discount_amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商品优惠金额
     *  示例值：0
     * </pre>
     */
    @SerializedName(value = "discount_amount")
    private Integer discountAmount;
    /**
     * <pre>
     * 字段名：商品备注
     * 变量名：goods_remark
     * 是否必填：否
     * 类型：string[1,128]
     * 描述：
     *  商品备注信息
     *  示例值：商品备注信息
     * </pre>
     */
    @SerializedName(value = "goods_remark")
    private String goodsRemark;
  }
}
