package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * V3付款码支付请求对象类
 * @author <a href="https://github.com/xxm1995">DaxPay</a>
 * @date 2024/7/29
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxPayCodepayRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：应用ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  由微信生成的应用ID，全局唯一。请求统一下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "appid")
  protected String appid;
  /**
   * <pre>
   * 字段名：直连商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  直连商户的商户号，由微信支付生成并下发。
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "mchid")
  protected String mchid;
  /**
   * <pre>
   * 字段名：服务商应用ID
   * 变量名：sp_appid
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  服务商模式下使用，由微信生成的应用ID，全局唯一。
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "sp_appid")
  protected String spAppid;
  /**
   * <pre>
   * 字段名：服务商商户号
   * 变量名：sp_mchid
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  服务商模式下使用，服务商商户号，由微信支付生成并下发。
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "sp_mchid")
  protected String spMchid;
  /**
   * <pre>
   * 字段名：子商户应用ID
   * 变量名：sub_appid
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  服务商模式下使用，由微信生成的应用ID，全局唯一。
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "sub_appid")
  protected String subAppid;
  /**
   * <pre>
   * 字段名：子商户商户号
   * 变量名：sub_mchid
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  服务商模式下使用，子商户商户号，由微信支付生成并下发。
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  protected String subMchid;
  /**
   * <pre>
   * 字段名：商品描述
   * 变量名：description
   * 是否必填：是
   * 类型：string[1,127]
   * 描述：
   *  商品描述
   *  示例值：Image形象店-深圳腾大-QQ公仔
   * </pre>
   */
  @SerializedName(value = "description")
  protected String description;
  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string[6,32]
   * 描述：
   *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  protected String outTradeNo;

  /**
   * <pre>
   * 字段名：微信支付返回的订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：1000320306201511078440737890
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;
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
  protected String attach;

  /**
   * <pre>
   * 字段名：订单优惠标记
   * 变量名：goods_tag
   * 是否必填：否
   * 类型：string[1,256]
   * 描述：
   *  订单优惠标记
   *  示例值：WXG
   * </pre>
   */
  @SerializedName(value = "goods_tag")
  private String goodsTag;
  /**
   * <pre>
   * 字段名：电子发票入口开放标识
   * 变量名：support_fapiao
   * 是否必填：否
   * 类型：boolean
   * 描述：传入true时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效。
   * </pre>
   */
  @SerializedName(value = "support_fapiao")
  private Boolean supportFapiao;
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
  @SerializedName(value = "payer")
  private Payer payer;
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
   * 字段名：场景信息
   * 变量名：scene_info
   * 是否必填：否
   * 类型：object
   * 描述：
   *  支付场景描述
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

  /**
   * <pre>
   * 字段名：结算信息
   * 变量名：settle_info
   * 是否必填：否
   * 类型：Object
   * 描述：结算信息
   * </pre>
   */
  @SerializedName(value = "settle_info")
  private SettleInfo settleInfo;


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
  public static class Payer implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * <pre>
     * 字段名：用户标识
     * 变量名：auth_code
     * 是否必填：是
     * 类型：string[32]
     * 描述：
     *   付款码支付授权码，即用户打开微信钱包显示的码。
     *  示例值：130061098828009406
     * </pre>
     */
    @SerializedName(value = "auth_code")
    private String authCode;
  }

  @Data
  @NoArgsConstructor
  public static class SceneInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：商户端设备 IP
     * 变量名：device_ip
     * 是否必填：是
     * 类型：string[1,45]
     * 描述：
     *  用户的客户端IP，支持IPv4和IPv6两种格式的IP地址。
     *  示例值：14.23.150.211
     * </pre>
     */
    @SerializedName(value = "device_ip")
    private String deviceIp;
    /**
     * <pre>
     * 字段名：商户端设备号
     * 变量名：device_id
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  商户端设备号（门店号或收银设备ID）。
     *  示例值：013467007045764
     * </pre>
     */
    @SerializedName(value = "device_id")
    private String deviceId;
    /**
     * <pre>
     * 字段名：商户门店信息
     * 变量名：store_info
     * 是否必填：否
     * 类型：object
     * 描述：
     *  商户门店信息
     * </pre>
     */
    @SerializedName(value = "store_info")
    private StoreInfo storeInfo;
  }

  /**
   * 商户门店信息
   */
  @Data
  @NoArgsConstructor
  public static class StoreInfo implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * <pre>
     * 字段名：门店编号
     * 变量名：id
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  此参数与商家自定义编码(out_id)二选一必填。
     *  微信支付线下场所ID，格式为纯数字。
     *  基于合规要求与风险管理目的，线下条码支付时需传入用户实际付款的场景信息。
     *  指引参见：https://kf.qq.com/faq/230817neeaem2308177ZFfqM.html。
     *  示例值：0001
     * </pre>
     */
    @SerializedName(value = "id")
    private String id;
    /**
     * <pre>
     * 字段名：商家自定义编码
     * 变量名：out_id
     * 是否必填：否
     * 类型：string[1,256]
     * 描述：
     *  此参数与门店(id)二选一必填。
     * 商户系统的门店编码，支持大小写英文字母、数字，仅支持utf-8格式。
     * 基于合规要求与风险管理目的，线下条码支付时需传入用户实际付款的场景信息。
     *  示例值：A1111
     * </pre>
     */
    @SerializedName(value = "out_id")
    private String outId;
  }


  @Data
  @NoArgsConstructor
  public static class SettleInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：是否指定分账
     * 变量名：profit_sharing
     * 是否必填：否
     * 类型：boolean
     * 描述：
     *  是否指定分账
     *  示例值：false
     * </pre>
     */
    @SerializedName(value = "profit_sharing")
    private Boolean profitSharing;
  }


  /**
   * 优惠功能
   */
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
