package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 服务商开具旅客运输行业电子发票请求。
 *
 * <p>购买方手机号、邮箱以及出行人证件号码由调用方按微信支付文档加密。</p>
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4025863376">开具旅客运输行业电子发票</a>
 */
@Data
public class PassengerTransportInvoiceRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 子商户号。微信支付分配的子商户号，必填，最长32个字符。
   */
  @SerializedName("sub_mchid")
  private String subMchid;

  /**
   * 发票申请单号。唯一标识一次开票行为并关联唯一的购买方信息，必填，最长32个字符；
   * 微信支付账单开票场景下填写微信支付交易单号。
   */
  @SerializedName("fapiao_apply_id")
  private String fapiaoApplyId;

  /**
   * 购买方信息，即发票抬头，必填。
   */
  @SerializedName("buyer_information")
  private BuyerInformation buyerInformation;

  /**
   * 需要开具的旅客运输行业数电发票信息，必填。
   */
  @SerializedName("fapiao_information")
  private FapiaoInformation fapiaoInformation;

  /**
   * 需要开具的旅客运输行业数电发票信息。
   */
  @Data
  public static class FapiaoInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商户发票单号，必填，最长32个字符，在每个商户下必须唯一。
     * 仅支持字母、数字、中划线、下划线、竖线和星号；开票失败或发票已冲红时可更换后重试，
     * 同一发票申请单最多支持5个商户发票单号。
     */
    @SerializedName("fapiao_id")
    private String fapiaoId;

    /**
     * 总价税合计，必填，单位：分。值为所有发票行单行金额合计之和，且全部发票的总价税合计
     * 不能超过交易总金额。
     */
    @SerializedName("total_amount")
    private Long totalAmount;

    /**
     * 发票行信息，必填；单张发票最多包含8行。
     */
    private List<InvoiceItem> items;

    /**
     * 出口业务适用政策代码。可选值：1（退税政策）、2（免税政策）、3（征税政策）。
     */
    @SerializedName("export_business_policy_code")
    private Long exportBusinessPolicyCode;

    /**
     * 增值税即征即退代码。可选值：1（软件产品）、2（资源综合利用产品）、3（管道运输服务）、
     * 4（有形动产融资租赁服务）、5（有形动产融资性售后回租服务）、6（新型墙体材料）、
     * 7（风力发电产品）、8（光伏发电产品）、9（动漫软件产品）、10（飞机维修劳务）、
     * 11（黄金）、12（铂金）。
     */
    @SerializedName("vat_refund_levy_code")
    private Long vatRefundLevyCode;

    /**
     * 开票人ID，必填，最长64个字符，为税局乐企系统登记的开票人ID。
     */
    @SerializedName("billing_person_id")
    private String billingPersonId;

    /**
     * 开票人名称，最长64个字符，为税局乐企系统登记的脱敏后名称；格式为脱敏姓名、空格和身份证后四位。
     */
    @SerializedName("billing_person")
    private String billingPerson;

    /**
     * 发票类型，必填。可选值：COMM_FAPIAO（增值税普通发票）、VAT_FAPIAO（增值税专用发票）。
     */
    @SerializedName("fapiao_bill_type")
    private String fapiaoBillType;

    /**
     * 发票对应的交易信息，必填，最多支持10条。
     */
    @SerializedName("transaction_information")
    private List<TransactionInformation> transactionInformation;

    /**
     * 发票备注。
     */
    private String remark;
  }

  /**
   * 发票行信息。
   */
  @Data
  public static class InvoiceItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 税局侧规定的货物或应税劳务、服务税收分类编码，必填，长度为19个字符；
     * 旅客运输业务仅支持以301开头的税收分类编码。
     */
    @SerializedName("tax_code")
    private String taxCode;

    /**
     * 货物或应税劳务、服务名称，必填，由商户自定义，最长128个字符。
     */
    @SerializedName("goods_name")
    private String goodsName;

    /**
     * 规格型号，展示在发票规格型号列，最长20个字符。
     */
    private String specification;

    /**
     * 单位，展示在发票单位列；折扣行不填写。
     */
    private String unit;

    /**
     * 数量，单位为10的负8次方，100000000表示数量1。非折扣行不填写时默认为100000000；
     * 折扣行不填写。
     */
    private Long quantity;

    /**
     * 单行金额合计，必填，单位：分。折扣行为负数，非折扣行为正数。
     */
    @SerializedName("total_amount")
    private Long totalAmount;

    /**
     * 税率，必填，单位为万分之一，例如1300表示13%。当前支持0、1%、1.5%、3%、5%、6%、
     * 9%、10%、11%、13%、16%和17%。
     */
    @SerializedName("tax_rate")
    private Long taxRate;

    /**
     * 是否为折扣行，必填；折扣行必须紧跟在被折扣行之后。
     */
    private Boolean discount;

    /**
     * 优惠政策标识。可选值：1（简易征收）、2（稀土产品）、3（免税）、4（不征税）、
     * 5（先征后退）、6（100%先征后退）、7（50%先征后退）、8（按3%简易征收）、
     * 9（按5%简易征收）、10（按5%简易征收减按1.5%计征）、11（即征即退30%）、
     * 12（即征即退50%）、13（即征即退70%）、14（即征即退100%）、
     * 15（超税负3%即征即退）、16（超税负8%即征即退）、17（超税负12%即征即退）、
     * 18（超税负6%即征即退）。
     */
    @SerializedName("preferential_policy_code")
    private Long preferentialPolicyCode;

    /**
     * 出行人额外信息，可选；传入时其内部必填字段必须完整填写。
     */
    @SerializedName("passenger_information")
    private PassengerInformation passengerInformation;
  }

  /**
   * 出行人额外信息。证件号码由调用方按微信支付文档加密。
   */
  @Data
  public static class PassengerInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 出行人姓名；填写出行人额外信息时必填，最长20个字符。
     */
    private String name;

    /**
     * 出行人证件类型；填写出行人额外信息时必填。可选值：IDENTITY_CARD（居民身份证）、
     * PASSPORT（护照）、HONG_KONG_PERMIT（中国香港居民来往内地通行证）、
     * MACAO_PERMIT（中国澳门居民来往内地通行证）、TAIWAN_PERMIT（中国台湾居民来往大陆通行证）、
     * FOREIGNER_RESIDENCE_PERMIT（外国人居留证）、HONG_KONG_RESIDENT_CARD（香港居民证）、
     * MACAO_RESIDENT_CARD（澳门居民证）、TAIWAN_RESIDENT_CARD（台湾居民证）、
     * MILITARY_OFFICER_CARD（军官证）、ARMED_POLICE_OFFICER_CARD（武警警官证）、
     * SOLDIER_CARD（士兵证）、HOMECOMING_CERT（港澳同胞回乡证）、
     * TAIWAN_COMPATRIOT_CERT（台胞证）。
     */
    @SerializedName("certificate_type")
    private String certificateType;

    /**
     * 出行人证件号码；填写出行人额外信息时必填。该字段为密文字段，调用方需使用微信支付公钥
     * 或微信支付平台证书公钥加密后传入。
     */
    @SerializedName("certificate_number")
    private String certificateNumber;

    /**
     * 出行日期；填写出行人额外信息时必填，使用 RFC3339 格式：yyyy-MM-DDTHH:mm:ss+TIMEZONE。
     */
    @SerializedName("departure_date")
    private String departureDate;

    /**
     * 出发地详细地址；填写出行人额外信息时必填，最长80个字符。
     */
    @SerializedName("departure_place")
    private String departurePlace;

    /**
     * 目的地详细地址；填写出行人额外信息时必填，最长80个字符。
     */
    private String destination;

    /**
     * 交通工具类型；填写出行人额外信息时必填。可选值：LONG_DISTANCE_BUS（长途汽车）、
     * PUBLIC_TRANSPORTATION（公共交通）、CAR（汽车）、SHIP（船舶）、
     * OTHER_TRANSPORTATION（其他交通工具）。
     */
    @SerializedName("transportation_type")
    private String transportationType;

    /**
     * 交通工具等级。交通工具为 SHIP 时必填，可选值：SHIP_FIRST_CLASS_CABIN（船舶一等舱）、
     * SHIP_SECOND_CLASS_CABIN（船舶二等舱）、SHIP_THIRD_CLASS_CABIN（船舶三等舱）；
     * 其他交通工具不填写。
     */
    @SerializedName("transportation_classes")
    private String transportationClasses;
  }

  /**
   * 发票对应的交易信息。
   */
  @Data
  public static class TransactionInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 支付渠道，必填。当前可选值：WECHAT_PAY（微信支付）。
     */
    @SerializedName("pay_channel")
    private String payChannel;

    /**
     * 支付订单号，最长64个字符。支付渠道为 WECHAT_PAY 时，本字段与商户订单号至少填写一个。
     */
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * 支付商户订单号，最长64个字符。支付渠道为 WECHAT_PAY 时，本字段与支付订单号至少填写一个。
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 交易金额，必填，单位：分。
     */
    private Long amount;
  }
}
