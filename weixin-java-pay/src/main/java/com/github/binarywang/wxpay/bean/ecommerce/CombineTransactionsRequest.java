package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 合单支付API
 * <pre>
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e-combine.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class CombineTransactionsRequest implements Serializable {
  private static final long serialVersionUID = -1242741645939606441L;
  /**
   * <pre>
   * 字段名：合单商户appid
   * 变量名：combine_appid
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *   合单发起方的appid。
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "combine_appid")
  private String combineAppid;

  /**
   * <pre>
   * 字段名：合单商户号
   * 变量名：combine_mchid
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  合单发起方商户号。
   *  示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "combine_mchid")
  private String combineMchid;

  /**
   * <pre>
   * 字段名：合单商户订单号
   * 变量名：combine_out_trade_no
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   *  示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "combine_out_trade_no")
  private String combineOutTradeNo;

  /**
   * <pre>
   * 字段名：+场景信息
   * 变量名：scene_info
   * 是否必填：否
   * 类型：object
   * 描述：支付场景信息描述
   * </pre>
   */
  @SerializedName(value = "scene_info")
  private SceneInfo sceneInfo;

  /**
   * <pre>
   * 字段名：+子单信息
   * 变量名：sub_orders
   * 是否必填：是
   * 类型：array
   * 描述：
   *  最多支持子单条数：50
   *
   * </pre>
   */
  @SerializedName(value = "sub_orders")
  private List<SubOrders> subOrders;

  /**
   * <pre>
   * 字段名：+支付者
   * 变量名：combine_payer_info
   * 是否必填：否(JSAPI必填)
   * 类型：object
   * 描述：支付者信息
   * </pre>
   */
  @SerializedName(value = "combine_payer_info")
  private CombinePayerInfo combinePayerInfo;

  /**
   * <pre>
   * 字段名：交易起始时间
   * 变量名：time_start
   * 是否必填：否
   * 类型：string(14)
   * 描述：
   *  订单生成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  示例值：2019-12-31T15:59:60+08:00
   * </pre>
   */
  @SerializedName(value = "time_start")
  private String timeStart;

  /**
   * <pre>
   * 字段名：交易结束时间
   * 变量名：time_expire
   * 是否必填：否
   * 类型：string(14)
   * 描述：
   *  订单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  示例值：2019-12-31T15:59:60+08:00
   * </pre>
   */
  @SerializedName(value = "time_expire")
  private String timeExpire;

  /**
   * <pre>
   * 字段名：通知地址
   * 变量名：notify_url
   * 是否必填：是
   * 类型：string(256)
   * 描述：
   *  接收微信支付异步通知回调地址，通知url必须为直接可访问的URL，不能携带参数。
   *  格式: URL
   *  示例值：https://yourapp.com/notify
   * </pre>
   */
  @SerializedName(value = "notify_url")
  private String notifyUrl;


  @Data
  @NoArgsConstructor
  public static class SceneInfo implements Serializable {
    /**
     * <pre>
     * 字段名：商户端设备号
     * 变量名：device_id
     * 是否必填：否
     * 类型：string(16)
     * 描述：
     *  终端设备号（门店号或收银设备ID）。
     *  特殊规则：长度最小7个字节
     *  示例值：POS1:1
     * </pre>
     */
    @SerializedName(value = "device_id")
    private String deviceId;

    /**
     * <pre>
     * 字段名：用户终端IP
     * 变量名：payer_client_ip
     * 是否必填：是
     * 类型：string(45)
     * 描述：
     *  用户端实际ip
     *  格式: ip(ipv4+ipv6)
     *  示例值：14.17.22.32
     * </pre>
     */
    @SerializedName(value = "payer_client_ip")
    private String payerClientIp;

    /**
     * <pre>
     * 字段名：H5场景信息
     * 变量名：h5_info
     * 是否必填：否(H5支付必填)
     * 类型：object
     * 描述：
     *  H5场景信息
     * </pre>
     */
    @SerializedName(value = "h5_info")
    private H5Info h5Info;
  }

  @Data
  @NoArgsConstructor
  public static class SubOrders implements Serializable {
    /**
     * <pre>
     * 字段名：子单商户号
     * 变量名：mchid
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  子单发起方商户号，必须与发起方appid有绑定关系。
     *  示例值：1900000109
     *  此处一般填写服务商商户号
     * </pre>
     */
    @SerializedName(value = "mchid")
    private String mchid;

    /**
     * <pre>
     * 字段名：附加信息
     * 变量名：attach
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     *  示例值：深圳分店
     * </pre>
     */
    @SerializedName(value = "attach")
    private String attach;

    /**
     * <pre>
     * 字段名：+订单金额
     * 变量名：amount
     * 是否必填：是
     * 类型：object
     * 描述：
     * </pre>
     */
    @SerializedName(value = "amount")
    private Amount amount;

    /**
     * <pre>
     * 字段名：子单商户订单号
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     *  特殊规则：最小字符长度为6
     *  示例值：20150806125346
     * </pre>
     */
    @SerializedName(value = "out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 字段名：二级商户号
     * 变量名：sub_mchid
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  二级商户商户号，由微信支付生成并下发。
     *  注意：仅适用于电商平台 服务商
     *  示例值：1900000109
     * </pre>
     */
    @SerializedName(value = "sub_mchid")
    private String subMchid;

    /**
     * <pre>
     * 字段名：商品描述
     * 变量名：description
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  商品简单描述。需传入应用市场上的APP名字-实际商品名称，例如：天天爱消除-游戏充值。
     *  示例值：腾讯充值中心-QQ会员充值
     * </pre>
     */
    @SerializedName(value = "description")
    private String description;

    /**
     * <pre>
     * 字段名：+结算信息
     * 变量名：settle_info
     * 是否必填：否
     * 类型：Object
     * 描述：结算信息
     * </pre>
     */
    @SerializedName(value = "settle_info")
    private SettleInfo settleInfo;

  }

  @Data
  @NoArgsConstructor
  public static class CombinePayerInfo implements Serializable {
    /**
     * <pre>
     * 字段名：用户标识
     * 变量名：openid
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  使用合单appid获取的对应用户openid。是用户在商户appid下的唯一标识。
     *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * </pre>
     */
    @SerializedName(value = "openid")
    private String openid;

  }

  @Data
  @NoArgsConstructor
  public static class Amount implements Serializable {
    /**
     * <pre>
     * 字段名：标价金额
     * 变量名：total_amount
     * 是否必填：是
     * 类型：int64
     * 描述：
     *  子单金额，单位为分。
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "total_amount")
    private Integer totalAmount;

    /**
     * <pre>
     * 字段名：标价币种
     * 变量名：currency
     * 是否必填：是
     * 类型：string(8)
     * 描述：
     *  符合ISO 4217标准的三位字母代码，人民币：CNY。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;

  }

  @Data
  @NoArgsConstructor
  public static class SettleInfo implements Serializable {
    /**
     * <pre>
     * 字段名：是否指定分账
     * 变量名：profit_sharing
     * 是否必填：否
     * 类型：bool
     * 描述：
     *  是否分账，与外层profit_sharing同时存在时，以本字段为准。
     *  true：是
     *  false：否
     *  示例值：true
     * </pre>
     */
    @SerializedName(value = "profit_sharing")
    private Boolean profitSharing;

    /**
     * <pre>
     * 字段名：补差金额
     * 变量名：subsidy_amount
     * 是否必填：否
     * 类型：int64
     * 描述：
     *  SettleInfo.profit_sharing为true时，该金额才生效。
     *  示例值：10
     * </pre>
     */
    @SerializedName(value = "subsidy_amount")
    private Integer subsidyAmount;

  }

  @Data
  @NoArgsConstructor
  public static class H5Info implements Serializable {

    /**
     * <pre>
     * 字段名：场景类型
     * 变量名：type
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  场景类型，枚举值：
     *  iOS：IOS移动应用；
     *  Android：安卓移动应用；
     *  Wap：WAP网站应用；
     *  示例值：iOS
     * </pre>
     */
    @SerializedName(value = "type")
    private String type;

    /**
     * <pre>
     * 字段名：应用名称
     * 变量名：app_name
     * 是否必填：否
     * 类型：string(64)
     * 描述：
     *  应用名称
     *  示例值：王者荣耀
     * </pre>
     */
    @SerializedName(value = "app_name")
    private String appName;

    /**
     * <pre>
     * 字段名：网站URL
     * 变量名：app_url
     * 是否必填：否
     * 类型：string(128)
     * 描述：
     *  网站URL
     *  示例值：https://pay.qq.com
     * </pre>
     */
    @SerializedName(value = "app_url")
    private String appUrl;

    /**
     * <pre>
     * 字段名：iOS平台BundleID
     * 变量名：bundle_id
     * 是否必填：否
     * 类型：string(128)
     * 描述：
     *  iOS平台BundleID
     *  示例值：com.tencent.wzryiOS
     * </pre>
     */
    @SerializedName(value = "bundle_id")
    private String bundleId;

    /**
     * <pre>
     * 字段名：Android平台PackageName
     * 变量名：package_name
     * 是否必填：否
     * 类型：string(128)
     * 描述：
     *  Android平台PackageName
     *  示例值：com.tencent.tmgp.sgame
     * </pre>
     */
    @SerializedName(value = "package_name")
    private String packageName;

  }

}
