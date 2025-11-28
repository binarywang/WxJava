package com.github.binarywang.wxpay.bean.marketing;

import com.github.binarywang.wxpay.bean.marketing.enums.BackgroundColorEnum;
import com.github.binarywang.wxpay.bean.marketing.enums.JumpTargetEnum;
import com.github.binarywang.wxpay.bean.marketing.enums.StockTypeEnum;
import com.github.binarywang.wxpay.bean.marketing.enums.TradeTypeEnum;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 创建代金券批次
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_1.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavorStocksCreateRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：批次名称
   * 变量名：stock_name
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   *  批次名称
   *  校验规则：
   *  1、批次名称最多9个中文汉字
   *  2、批次名称最多20个字母
   *  3、批次名称中不能包含不当内容和特殊字符 _ , ; |
   *  示例值：微信支付代金券批次
   * </pre>
   */
  @SerializedName(value = "stock_name")
  private String stockName;

  /**
   * <pre>
   * 字段名：批次备注
   * 变量名：comment
   * 是否必填：否
   * 类型：string[1,60]
   * 描述：
   *  仅制券商户可见，用于自定义信息。
   *  校验规则：批次备注最多60个UTF8字符数
   *  示例值：零售批次
   * </pre>
   */
  @SerializedName(value = "comment")
  private String comment;

  /**
   * <pre>
   * 字段名：归属商户号
   * 变量名：belong_merchant
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   *  批次归属商户号
   *  该字段暂未开放
   *  示例值：98568865
   * </pre>
   */
  @SerializedName(value = "belong_merchant")
  private String belongMerchant;

  /**
   * <pre>
   * 字段名：可用时间-开始时间
   * 变量名：available_begin_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  批次开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  校验规则：
   *  1、开始时间不可早于当前时间
   *  2、不能创建365天后开始的批次
   *  示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "available_begin_time")
  private String availableBeginTime;

  /**
   * <pre>
   * 字段名：可用时间-结束时间
   * 变量名：available_end_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  批次结束时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  校验规则：
   *  1、结束时间需晚于开始时间
   *  2、可用时间最长为90天
   *  3、有效时间间隔最短为1s
   *  示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "available_end_time")
  private String availableEndTime;

  /**
   * <pre>
   * 字段名：发放规则
   * 变量名：stock_use_rule
   * 是否必填：是
   * 类型：object
   * 描述：批次使用规则
   * </pre>
   */
  @SerializedName(value = "stock_use_rule")
  private StockUseRule stockUseRule;

  /**
   * <pre>
   * 字段名：样式设置
   * 变量名：pattern_info
   * 是否必填：否
   * 类型：object
   * 描述：代金券详情页
   * </pre>
   */
  @SerializedName(value = "pattern_info")
  private PatternInfo patternInfo;

  /**
   * <pre>
   * 字段名：核销规则
   * 变量名：coupon_use_rule
   * 是否必填：是
   * 类型：object
   * 描述：核销规则
   * </pre>
   */
  @SerializedName(value = "coupon_use_rule")
  private CouponUseRule couponUseRule;

  /**
   * <pre>
   * 字段名：营销经费
   * 变量名：no_cash
   * 是否必填：是
   * 类型：bool
   * 描述：
   *  营销经费。枚举值：
   *  true：免充值
   *  false：预充值
   *  1、免充值：制券方无需提前充值资金，用户核销代金券时，直接从订单原价中扣除优惠减价金额，最终只将用户实际支付的金额结算给核销商户，商户实收少于订单原价。
   *  2、预充值：制券方需将优惠预算提前充值到微信支付商户可用余额中，用户核销代金券时，系统从制券方商户可用余额中扣除优惠减价部分对应的资金，连同用户实际支付的资金，一并结算给核销商户，不影响实收。
   *  示例值：false
   * </pre>
   */
  @SerializedName(value = "no_cash")
  private Boolean noCash;

  /**
   * <pre>
   * 字段名：批次类型
   * 变量名：stock_type
   * 是否必填：是
   * 类型：string[1,16]
   * 描述：
   *  批次类型，仅支持：
   *  NORMAL：固定面额满减券批次
   *  示例值：NORMAL
   * </pre>
   */
  @SerializedName(value = "stock_type")
  private StockTypeEnum stockType;

  /**
   * <pre>
   * 字段名：商户单据号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  商户创建批次凭据号（格式：商户id+日期+流水号），可包含英文字母，数字，|，_，*，-等内容，不允许出现其他不合法符号，商户侧需保持商户单据号全局唯一。
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：扩展属性
   * 变量名：ext_info
   * 是否必填：否
   * 类型：string[1,128]
   * 描述：
   *  扩展属性字段，按json格式，如无需要则不填写。
   *  示例值：{'exinfo1':'1234','exinfo2':'3456'}
   * </pre>
   */
  @SerializedName(value = "ext_info")
  private String extInfo;

  @Data
  @NoArgsConstructor
  public static class StockUseRule implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：发放总上限
     * 变量名：max_coupons
     * 是否必填：是
     * 类型：uint64
     * 描述：
     *  最大发券数
     *  校验规则：
     *  1、发放总个数最少5个
     *  2、发放总个数最多1000万个
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "max_coupons")
    private Integer maxCoupons;

    /**
     * <pre>
     * 字段名：总预算
     * 变量名：max_amount
     * 是否必填：是
     * 类型：uint64
     * 描述：
     *  最大发券预算，当营销经费no_cash选择预充值false时，激活批次时会从制券商户的余额中扣除预算，请保证账户金额充足，单位：分
     *  max_amount需要等于coupon_amount（面额） * max_coupons（发放总上限）
     *  校验规则：批次总预算最多1亿元
     *  示例值：5000
     * </pre>
     */
    @SerializedName(value = "max_amount")
    private Integer maxAmount;

    /**
     * <pre>
     * 字段名：单天预算发放上限
     * 变量名：max_amount_by_day
     * 是否必填：否
     * 类型：uint64
     * 描述：
     *  设置此字段，允许指定单天最大发券预算，单位：分。
     *  校验规则：不能大于总预算
     *  示例值：400
     * </pre>
     */
    @SerializedName(value = "max_amount_by_day")
    private Integer maxAmountByDay;

    /**
     * <pre>
     * 字段名：单个用户可领个数
     * 变量名：max_coupons_per_user
     * 是否必填：是
     * 类型：uint32
     * 描述：
     *  活动期间每个用户可领个数，当开启了自然人限领时，多个微信号同属于一个身份证时，视为同一用户。
     *  校验规则：
     *  1、不能大于发放总个数
     *  2、最少为1个，最多为60个
     *  示例值：3
     * </pre>
     */
    @SerializedName(value = "max_coupons_per_user")
    private Integer maxCouponsPerUser;

    /**
     * <pre>
     * 字段名：是否开启自然人限制
     * 变量名：natural_person_limit
     * 是否必填：是
     * 类型：bool
     * 描述：
     *  当开启了自然人限领时，多个微信号同属于一个身份证时，视为同一用户，枚举值
     *  true：是
     *  false：否
     *  示例值：false
     * </pre>
     */
    @SerializedName(value = "natural_person_limit")
    private Boolean naturalPersonLimit;

    /**
     * <pre>
     * 字段名：是否开启防刷拦截
     * 变量名：prevent_api_abuse
     * 是否必填：是
     * 类型：bool
     * 描述：
     *  若开启防刷拦截，当用户命中恶意、小号、机器、羊毛党、黑产等风险行为时，无法成功发放代金券。
     *  枚举值
     *  true：是
     *  false：否
     *  示例值：false
     * </pre>
     */
    @SerializedName(value = "prevent_api_abuse")
    private Boolean preventApiAbuse;
  }

  @Data
  @NoArgsConstructor
  public static class PatternInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：使用说明
     * 变量名：description
     * 是否必填：是
     * 类型：string[1,3000]
     * 描述：
     *  用于说明详细的活动规则，会展示在代金券详情页。
     *  校验规则：最多1000个UTF8字符
     *  示例值：微信支付营销代金券
     * </pre>
     */
    @SerializedName(value = "description")
    private String description;

    /**
     * <pre>
     * 字段名：商户logo
     * 变量名：merchant_logo
     * 是否必填：否
     * 类型：string[1,128]
     * 描述：
     *  商户logo ，仅支持通过《图片上传API》接口获取的图片URL地址。
     *  1、商户logo大小需为120像素*120像素。
     *  2、支持JPG/JPEG/PNG格式，且图片小于1M。
     *  3、最多128个UTF8字符
     *  示例值：https://qpic.cn/xxx
     * </pre>
     */
    @SerializedName(value = "merchant_logo")
    private String merchantLogo;

    /**
     * <pre>
     * 字段名：品牌名称
     * 变量名：merchant_name
     * 是否必填：否
     * 类型：string[1,128]
     * 描述：
     *  品牌名称，展示在用户卡包
     *  校验规则：
     *  1、最多12个中文汉字
     *  2、最多36个英文字符
     *  示例值：微信支付
     * </pre>
     */
    @SerializedName(value = "merchant_name")
    private String merchantName;

    /**
     * <pre>
     * 字段名：背景颜色
     * 变量名：background_color
     * 是否必填：否
     * 类型：string[1,15]
     * 描述：
     *  券的背景颜色，可设置10种颜色，色值请参考卡券背景颜色图。颜色取值为颜色图中的颜色名称。可选枚举字段不用则不传，不可以传空值
     *  示例值：COLOR020
     * </pre>
     */
    @SerializedName(value = "background_color")
    private BackgroundColorEnum backgroundColor;

    /**
     * <pre>
     * 字段名：券详情图片
     * 变量名：coupon_image
     * 是否必填：是
     * 类型：string[1,128]
     * 描述：
     *  券详情图片， 850像素*350像素，且图片大小不超过2M，支持JPG/PNG格式，仅支持通过《图片上传API》接口获取的图片URL地址。。
     *  示例值：https://qpic.cn/xxx
     * </pre>
     */
    @SerializedName(value = "coupon_image")
    private String couponImage;

    /**
     * 卡包跳转目标
     */
    @SerializedName("jump_target")
    private JumpTargetEnum jumpTarget;

    /**
     * 小程序appid
     */
    @SerializedName("mini_program_appid")
    private String miniProgramAppid;

    /**
     * 小程序path
     */
    @SerializedName("mini_program_path")
    private String miniProgramPath;
  }

  @Data
  @NoArgsConstructor
  public static class CouponUseRule implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：券生效时间
     * 变量名：coupon_available_time
     * 是否必填：否
     * 类型：object
     * 描述：
     *  允许指定券的特殊生效时间规则。
     *  该字段暂未开放
     * </pre>
     */
//    @SerializedName(value = "coupon_available_time")
//    private CouponAvailableTime couponAvailableTime;

    /**
     * <pre>
     * 字段名：固定面额满减券使用规则
     * 变量名：fixed_normal_coupon
     * 是否必填：否
     * 类型：object
     * 描述：
     *  stock_type为NORMAL时必填。
     * </pre>
     */
    @SerializedName(value = "fixed_normal_coupon")
    private FixedNormalCoupon fixedNormalCoupon;

    /**
     * <pre>
     * 字段名：订单优惠标记
     * 变量名：goods_tag
     * 是否必填：否
     * 类型：array
     * 描述：
     *  订单优惠标记，按json格式。
     *  商户下单时需要传入相同的标记(goods_tag)，用户同时符合其他规则才能享受优惠
     *  校验规则：
     *  1、最多允许录入50个
     *  2、每个订单优惠标记支持字母/数字/下划线，不超过128个UTF8字符。
     *  示例值：["123321","456654"]
     * </pre>
     */
    @SerializedName(value = "goods_tag")
    private List<String> goodsTag;

    /**
     * <pre>
     * 字段名：指定付款方式
     * 变量名：limit_pay
     * 是否必填：否
     * 类型：array[1,1]
     * 描述：
     *  指定付款方式的交易可核销/使用代金券，可指定零钱付款、指定银行卡付款，需填入支付方式编码， 不在此列表中的银行卡，暂不支持此功能。
     *  校验规则：条目个数限制为【1，1】。
     *  示例值：ICBC_CREDIT
     * </pre>
     */
    @SerializedName(value = "limit_pay")
    private List<String> limitPay;

    /**
     * <pre>
     * 字段名：指定银行卡BIN
     * 变量名：limit_card
     * 是否必填：否
     * 类型：object
     * 描述：
     *  指定银行卡bin付款的交易可核销/使用代金券，当批次限定了指定银行卡时方可生效
     * </pre>
     */
    @SerializedName(value = "limit_card")
    private LimitCard limitCard;

    /**
     * <pre>
     * 字段名：支付方式
     * 变量名：trade_type
     * 是否必填：否
     * 类型：array
     * 描述：
     *  允许指定支付方式的交易才可核销/使用代金券，不填则默认“不限”。
     *  枚举值：
     *  MICROAPP：小程序支付
     *  APPPAY：APP支付
     *  PPAY：免密支付
     *  CARD：刷卡支付
     *  FACE：人脸支付
     *  OTHER：其他支付
     *  示例值：["MICROAPP","APPPAY"]
     * </pre>
     */
    @SerializedName(value = "trade_type")
    private List<TradeTypeEnum> tradeType;

    /**
     * <pre>
     * 字段名：是否可叠加其他优惠
     * 变量名：combine_use
     * 是否必填：否
     * 类型：bool
     * 描述：
     *  允许指定本优惠是否可以和本商户号创建的其他券同时使用，不填则默认允许同时使用。枚举值：
     *  true：是
     *  false：否
     *  示例值：false
     * </pre>
     */
    @SerializedName(value = "combine_use")
    private Boolean combineUse;

    /**
     * <pre>
     * 字段名：可核销商品编码
     * 变量名：available_items
     * 是否必填：否
     * 类型：array
     * 描述：
     *  包含指定SKU商品编码的交易才可核销/使用代金券：活动商户在交易下单时，需传入用户购买的所有SKU商品编码，当命中代金券中设置的商品编码时可享受优惠。
     *  校验规则：
     *  1、单个商品编码的字符长度为【1，128】
     *  2、条目个数限制为【1，50】
     *  示例值：['123321','456654']
     * </pre>
     */
    @SerializedName(value = "available_items")
    private List<String> availableItems;

    /**
     * <pre>
     * 字段名：不可核销商品编码
     * 变量名：unavailable_items
     * 是否必填：否
     * 类型：array
     * 描述：
     *  该字段暂未开放
     *  包含指定SKU商品编码的交易不可核销/使用代金券。
     *  校验规则：
     *  1、单个商品编码的字符长度为【1，128】
     *  2、条目个数限制为【1，50】
     *  示例值：['789987','56765']
     * </pre>
     */
//    @SerializedName(value = "unavailable_items")
//    private List<String> unavailableItems;

    /**
     * <pre>
     * 字段名：可用商户号
     * 变量名：available_merchants
     * 是否必填：是
     * 类型：array
     * 描述：
     *  可用商户的交易才可核销/使用代金券。当营销经费no_cash=false时，可用商户允许填入任何类型的特约商户或普通商户
     *  当营销经费no_cash=ture时，分为以下几种情况：
     *  1、创建商户是普通商户或服务商特约商户(子商户)：可添加本商户号或同品牌商户。
     *  说明：若可用商户中，有特约商户(子商户)，那么特约商户自己发起的交易、以及服务商帮特约商户发起的交易，都可以使用代金券。
     *  2、创建商户是普通服务商：可添加已授权的子商户，详见《申请免充值代金券产品权限》。
     *  说明：特约商户如果有多个服务商，那么服务商为他发起的交易，只要完成了免充值授权，都可以使用代金券；特约商户自己发起的交易不可以使用代金券。
     *  3、创建商户是渠道商、银行服务商或从业机构：可直接添加旗下任意子商户，不需要子商户授权。
     *  示例值：['9856000','9856111']
     * </pre>
     */
    @SerializedName(value = "available_merchants")
    private List<String> availableMerchants;
  }

//  @Data
//  @NoArgsConstructor
//  public static class CouponAvailableTime implements Serializable {
//    /**
//     * <pre>
//     * 字段名：固定时间段可用
//     * 变量名：fix_available_time
//     * 是否必填：否
//     * 类型：object
//     * 描述：
//     *  允许指定券在特殊时间段生效。当设置固定时间段可用时不可设置领取后N天有效
//     *  该字段暂未开放
//     * </pre>
//     */
//    @SerializedName(value = "fix_available_time")
//    private FixAvailableTime fixAvailableTime;
//
//    /**
//     * <pre>
//     * 字段名：领取后N天有效
//     * 变量名：second_day_available
//     * 是否必填：否
//     * 类型：bool
//     * 描述：
//     *  领取后，券的开始时间为领券后第二天，如7月1日领券，那么在7月2日00:00:00开始。
//     *  当设置领取后N天有效时，不可设置固定时间段可用。
//     *  枚举值：
//     *  true：是
//     *  false：否
//     *  该字段暂未开放
//     *  示例值：false
//     * </pre>
//     */
//    @SerializedName(value = "second_day_available")
//    private Boolean secondDayAvailable;
//
//    /**
//     * <pre>
//     * 字段名：领取后有效时间
//     * 变量名：available_time_after_receive
//     * 是否必填：否
//     * 类型：uint32
//     * 描述：
//     *  领取后，券的结束时间为领取N天后，如设置领取后7天有效，那么7月1日领券，在7月7日23:59:59失效（在可用时间内计算失效时间，若券还未到领取后N天，但是已经到了可用结束时间，那么也会过期）
//     *  领取后有效时间，单位：分钟。
//     *  该字段暂未开放
//     *  示例值：1440
//     * </pre>
//     */
//    @SerializedName(value = "available_time_after_receive")
//    private Integer availableTimeAfterReceive;
//  }
//
//  @Data
//  @NoArgsConstructor
//  public static class FixAvailableTime implements Serializable {
//    /**
//     * <pre>
//     * 字段名：可用星期数
//     * 变量名：available_week_day
//     * 是否必填：否
//     * 类型：uint32
//     * 描述：
//     *  允许指定每周固定星期数生效，0代表周日生效，1代表周一生效，以此类推；不填则代表在可用时间内周一至周日都生效。
//     *  该字段暂未开放
//     *  示例值：1，2
//     * </pre>
//     */
//    @SerializedName(value = "available_week_day")
//    private Integer availableWeekDay;
//
//    /**
//     * <pre>
//     * 字段名：当天开始时间
//     * 变量名：begin_time
//     * 是否必填：否
//     * 类型：uint32
//     * 描述：
//     *  允许指定特殊生效星期数中的具体生效的时间段。
//     *  当天开始时间，单位：秒。
//     *  该字段暂未开放
//     *  示例值：0
//     * </pre>
//     */
//    @SerializedName(value = "begin_time")
//    private Integer beginTime;
//
//    /**
//     * <pre>
//     * 字段名：当天结束时间
//     * 变量名：end_time
//     * 是否必填：否
//     * 类型：uint32
//     * 描述：
//     *  允许指定特殊生效星期数中的具体生效的时间段。
//     *  当天结束时间，单位：秒，默认为23点59分59秒。
//     *  该字段暂未开放
//     *  示例值：3600
//     * </pre>
//     */
//    @SerializedName(value = "end_time")
//    private Integer endTime;
//  }

  @Data
  @NoArgsConstructor
  public static class FixedNormalCoupon implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：面额
     * 变量名：fixed_normal_coupon
     * 是否必填：是
     * 类型：uint64
     * 描述：
     *  面额，单位：分。
     *  校验规则：
     *  1、必须为整数
     *  2、必须大于1分且小于等于1000元
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "coupon_amount")
    private Integer couponAmount;

    /**
     * <pre>
     * 字段名：门槛
     * 变量名：transaction_minimum
     * 是否必填：是
     * 类型：uint64
     * 描述：
     *  使用券金额门槛，单位：分。
     *  若指定可核销商品编码，门槛则为可核销商品部分的消费金额，而不是订单的消费金额。
     *  校验规则：使用门槛必须大于优惠金额
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "transaction_minimum")
    private Integer transactionMinimum;
  }

  @Data
  @NoArgsConstructor
  public static class LimitCard implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：银行卡名称
     * 变量名：name
     * 是否必填：否
     * 类型：string[1,4]
     * 描述：
     *  将在微信支付收银台向用户展示，最多4个中文汉字
     *  示例值：精粹白金
     * </pre>
     */
    @SerializedName(value = "name")
    private String name;

    /**
     * <pre>
     * 字段名：指定卡BIN
     * 变量名：bin
     * 是否必填：否
     * 类型：array
     * 描述：
     *  使用指定卡BIN的银行卡支付方可享受优惠，按json格式
     *  特殊规则：单个卡BIN的字符长度为【6,9】,条目个数限制为【1,10】。
     *  示例值：['62123456','62123457']
     * </pre>
     */
    @SerializedName(value = "bin")
    private List<String> bin;
  }

}
