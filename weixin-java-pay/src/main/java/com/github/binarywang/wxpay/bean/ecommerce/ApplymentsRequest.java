package com.github.binarywang.wxpay.bean.ecommerce;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * <pre>
 * 电商平台，可使用该接口，帮助其二级商户进件成为微信支付商户。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter7_1_8.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class ApplymentsRequest implements Serializable {
  private static final long serialVersionUID = -3092662029966103592L;
  /**
   * <pre>
   * 字段名：业务申请编号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string(124)
   * 描述：
   *  1、服务商自定义的商户唯一编号。
   *  2、每个编号对应一个申请单，每个申请单审核通过后会生成一个微信支付商户号。
   *  3、若申请单被驳回，可填写相同的“业务申请编号”，即可覆盖修改原申请单信息 。
   *  示例值：APPLYMENT_00000000001
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：主体类型
   * 变量名：organization_type
   * 是否必填：是
   * 类型：string(4)
   * 描述：
   *  非小微的主体类型需与营业执照/登记证书上一致，可参考选择主体指引，枚举值如下。
   *  2401：小微商户，指无营业执照的个人商家。
   *  2500：个人卖家，指无营业执照，已持续从事电子商务经营活动满6个月，且期间经营收入累计超过20万元的个人商家。（若选择该主体，请在“补充说明”填写相关描述）。
   *  4：个体工商户，营业执照上的主体类型一般为个体户、个体工商户、个体经营。
   *  2：企业，营业执照上的主体类型一般为有限公司、有限责任公司。
   *  3：事业单位，包括国内各类事业单位，如：医疗、教育、学校等单位。
   *  2502：政府机关，包括各级、各类政府机关，如机关党委、税务、民政、人社、工商、商务、市监等。
   *  1708：社会组织，包括社会团体、民办非企业、基金会、基层群众性自治组织、农村集体经济组织等组织。
   *  示例值：2
   * </pre>
   */
  @SerializedName(value = "organization_type")
  private String organizationType;

  /**
   * <pre>
   * 字段名：是否金融机构
   * 变量名：finance_institution
   * 是否必填：条件选填
   * 类型：bool
   * 描述：
   *  选填，请根据申请主体的实际情况填写，可参考选择金融机构指引：
   *  1、若商户主体是金融机构，则填写：true。
   *  2、若商户主体不是金融机构，则填写：false。
   *  若未传入将默认填写：false。
   *  示例值：true
   * </pre>
   */
  @SerializedName(value = "finance_institution")
  private Boolean financeInstitution;

  /**
   * <pre>
   * 字段名：+营业执照/登记证书信息
   * 变量名：business_license_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：
   *  1、主体为“小微/个人卖家”时，不填。
   *  2、主体为“个体工商户/企业”时，请上传营业执照。
   *  3、主体为“政府机关/事业单位/社会组织”时，请上传登记证书。
   * </pre>
   */
  @SerializedName(value = "business_license_info")
  private BusinessLicenseInfo businessLicenseInfo;

  /**
   * <pre>
   * 字段名：+金融机构许可证信息
   * 变量名：finance_institution_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：当主体是金融机构时，必填
   * </pre>
   */
  @SerializedName(value = "finance_institution_info")
  private FinanceInstitutionInfo financeInstitutionInfo;

  /**
   * 字段名：证件持有人类型
   * 变量名：id_holder_type
   * 是否必填：条件选填
   * 类型：string
   * 描述：
   *  1. 主体类型为政府机关/事业单位时选传：
   *  （1）若上传的是法人证件，则不需要上传该字段。
   *  （2）若因特殊情况，无法提供法人证件时，可上传经办人。 （经办人：经商户授权办理微信支付业务的人员，授权范围包括但不限于签约，入驻过程需完成账户验证）。
   *  2. 主体类型为企业/个体户/社会组织时，默认为经营者/法人，不需要上传该字段。
   *  LEGAL：法人
   *  SUPER：经办人
   *  示例值：LEGAL
   */
  @SerializedName(value = "id_holder_type")
  private String idHolderType;

  /**
   * <pre>
   * 字段名：经营者/法人证件类型
   * 变量名：id_doc_type
   * 是否必填：条件选填
   * 类型：string(64)
   * 描述：
   *  1、当证件持有人类型为经营者/法人时，需要填写。其他情况，无需上传。
   *  2、主体为“小微/个人卖家”，可选择：身份证。
   *  3、主体为“个体户/企业/事业单位/社会组织”：可选择任一证件类型，主体为“政府机关”仅支持中国大陆居民-身份证类型。
   *  4、若没有填写，系统默认选择：身份证。
   *  枚举值：
   *  IDENTIFICATION_TYPE_MAINLAND_IDCARD：中国大陆居民-身份证
   *  IDENTIFICATION_TYPE_OVERSEA_PASSPORT：其他国家或地区居民-护照
   *  IDENTIFICATION_TYPE_HONGKONG：中国香港居民--来往内地通行证
   *  IDENTIFICATION_TYPE_MACAO：中国澳门居民--来往内地通行证
   *  IDENTIFICATION_TYPE_TAIWAN：中国台湾居民--来往大陆通行证
   *  IDENTIFICATION_TYPE_FOREIGN_RESIDENT：外国人居留证
   *  IDENTIFICATION_TYPE_HONGKONG_MACAO_RESIDENT：港澳居民证
   *  IDENTIFICATION_TYPE_TAIWAN_RESIDENT：台湾居民证
   *  示例值：IDENTIFICATION_TYPE_MAINLAND_IDCARD
   * </pre>
   */
  @SerializedName(value = "id_doc_type")
  private String idDocType;

  /**
   * <pre>
   * 字段名：法定代表人说明函
   * 变量名：authorize_letter_copy
   * 是否必填：条件选填
   * 类型：string(256)
   * 描述：
   *  1、当证件持有人类型为经办人时，必须上传。其他情况，无需上传。
   *  2、若因特殊情况，无法提供法定代表人证件时，请参照示例图打印法定代表人说明函，全部信息需打印，不支持手写商户信息，并加盖公章。
   *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
   *  示例值：47ZC6GC-vnrbEny_Ie_An5-tCpqxucuxi-vByf3Gjm7KEIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
   * </pre>
   */
  @SerializedName(value = "authorize_letter_copy")
  private String authorizeLetterCopy;

  /**
   * <pre>
   * 字段名：+经营者/法人身份证信息
   * 变量名：id_card_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：
   *  当证件持有人类型为经营者/法人且证件类型为“身份证”时填写。
   *
   * </pre>
   */
  @SerializedName(value = "id_card_info")
  @SpecEncrypt
  private IdCardInfo idCardInfo;

  /**
   * <pre>
   * 字段名：+经营者/法人其他类型证件信息
   * 变量名：id_doc_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：当证件持有人类型为经营者/法人且证件类型不为“身份证”时填写。
   * </pre>
   */
  @SerializedName(value = "id_doc_info")
  @SpecEncrypt
  private IdDocInfo idDocInfo;

  /**
   * <pre>
   * 字段名：经营者/法人是否为受益人
   * 变量名：owner
   * 是否必填：条件选填
   * 类型：bool
   * 描述：主体类型为企业时，需要填写：1、若经营者/法人是最终受益人，则填写：true。2、若经营者/法人不是最终受益人，则填写：false。
   * 示例值：true
   * </pre>
   */
  @SerializedName(value = "owner")
  private Boolean owner;

  /**
   * <pre>
   * 字段名：最终受益人信息列表
   * 变量名：ubo_info_list
   * 是否必填：条件选填
   * 类型：bool
   * 描述：
   *  仅企业需要填写。
   *  若经营者/法人不是最终受益所有人，则需提填写受益所有人信息，最多上传4个。
   *  若经营者/法人是最终受益所有人之一，可在此填写其他受益所有人信息，最多上传3个。
   *  根据国家相关法律法规，需要提供公司受益所有人信息，受益所有人需符合至少以下条件之一：
   *  1、直接或者间接拥有超过25%公司股权或者表决权的自然人。
   *  2、通过人事、财务等其他方式对公司进行控制的自然人。
   *  3、公司的高级管理人员，包括公司的经理、副经理、财务负责人、上市公司董事会秘书和公司章程规定的其他人员。
   * </pre>
   */
  @SerializedName(value = "ubo_info_list")
  @SpecEncrypt
  private List<UboInfo> uboInfoList;

  /**
   * <pre>
   * 字段名：+结算账户信息
   * 变量名：account_info
   * 是否必填：是
   * 类型：object
   * 描述：请填写商家提现收款的银行账户信息
   * </pre>
   */
  @SerializedName(value = "account_info")
  @SpecEncrypt
  private AccountInfo accountInfo;

  /**
   * <pre>
   * 字段名：+超级管理员信息
   * 变量名：contact_info
   * 是否必填：是
   * 类型：object
   * 描述：
   *  请填写店铺的超级管理员信息。
   *  超级管理员需在开户后进行签约，并可接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
   * </pre>
   */
  @SerializedName(value = "contact_info")
  @SpecEncrypt
  private ContactInfo contactInfo;

  /**
   * <pre>
   * 字段名：+店铺信息
   * 变量名：sales_scene_info
   * 是否必填：是
   * 类型：object
   * 描述：请填写店铺信息
   * </pre>
   */
  @SerializedName(value = "sales_scene_info")
  private SalesSceneInfo salesSceneInfo;

  /**
   * <pre>
   * 字段名：+结算规则
   * 变量名：settlement_info
   * 是否必填：否
   * 类型：object
   * 描述：请填写商家的结算费率规则、所属行业等信息。若电商平台未传入，系统将填写默认值
   * </pre>
   */
  @SerializedName(value = "settlement_info")
  private SettlementInfo settlementInfo;

  /**
   * <pre>
   * 字段名：商户简称
   * 变量名：merchant_shortname
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  UTF-8格式，中文占3个字节，即最多21个汉字长度。将在支付完成页向买家展示，需与商家的实际售卖商品相符 。
   *  示例值：腾讯
   * </pre>
   */
  @SerializedName(value = "merchant_shortname")
  private String merchantShortname;

  /**
   * <pre>
   * 字段名：特殊资质
   * 变量名：qualifications
   * 是否必填：否
   * 类型：string(1024)
   * 描述：
   *  1、根据商户经营业务要求提供相关资质，详情查看《行业对应特殊资质》。
   *  2、请提供为“申请商家主体”所属的特殊资质，可授权使用总公司/分公司的特殊资 质；
   *  3、最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
   *  示例值：jTpGmxUX3FBWVQ5NJInE4d2I6_H7I4
   * </pre>
   */
  @SerializedName(value = "qualifications")
  private String qualifications;

  /**
   * <pre>
   * 字段名：补充材料
   * 变量名：business_addition_pics
   * 是否必填：否
   * 类型：string(1024)
   * 描述：
   *  根据实际审核情况，额外要求提供。最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
   *  示例值：jTpGmg05InE4d2I6_H7I4
   * </pre>
   */
  @SerializedName(value = "business_addition_pics")
  private String businessAdditionPics;

  /**
   * <pre>
   * 字段名：补充说明
   * 变量名：business_addition_desc
   * 是否必填：否
   * 类型：string(512)
   * 描述：
   *  1、若主体为“个人卖家”，该字段必传，则需填写描述“ 该商户已持续从事电子商务经营活动满6个月，且期间经营收入累计超过20万元。”
   *  示例值：特殊情况，说明原因
   * </pre>
   */
  @SerializedName(value = "business_addition_desc")
  private String businessAdditionDesc;

  @Data
  @NoArgsConstructor
  public static class BusinessLicenseInfo implements Serializable {
    /**
     * <pre>
     * 字段名：证书类型
     * 变量名：cert_type
     * 是否必填：条件选填
     * 类型：string
     * 描述：
     *  1、主体为“政府机关/事业单位/社会组织”时，请上传登记证书类型。
     *  2、主体为“个体工商户/企业”时，不填。
     *
     *  当主体为事业单位时，选择此枚举值：
     *  CERTIFICATE_TYPE_2388：事业单位法人证书
     *
     *  当主体为政府机关，选择此枚举值：
     *  CERTIFICATE_TYPE_2389：统一社会信用代码证书
     *
     *  当主体为社会组织，选择以下枚举值之一：
     *  CERTIFICATE_TYPE_2389：统一社会信用代码证书
     *  CERTIFICATE_TYPE_2394：社会团体法人登记证书
     *  CERTIFICATE_TYPE_2395：民办非企业单位登记证书
     *  CERTIFICATE_TYPE_2396：基金会法人登记证书
     *  CERTIFICATE_TYPE_2399：宗教活动场所登记证
     *  CERTIFICATE_TYPE_2400：政府部门下发的其他有效证明文件
     *  CERTIFICATE_TYPE_2520：执业许可证/执业证
     *  CERTIFICATE_TYPE_2521：基层群众性自治组织特别法人统一社会信用代码证
     *  CERTIFICATE_TYPE_2522：农村集体经济组织登记证
     *  示例值：CERTIFICATE_TYPE_2388
     * </pre>
     */
    @SerializedName(value = "cert_type")
    private String certType;

    /**
     * <pre>
     * 字段名：营业执照扫描件
     * 变量名：business_license_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、主体为“个体工商户/企业”时，请上传营业执照的证件图片。
     *  2、主体为“政府机关/事业单位/社会组织”时，请上传登记证书的证件图片。
     *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID 。
     *  4、图片要求：
     *  （1）请上传证件的彩色扫描件或彩色数码拍摄件，黑白复印件需加盖公章（公章信息需完整） 。
     *  （2）不得添加无关水印（非微信支付商户申请用途的其他水印）。
     *  （3）需提供证件的正面拍摄件，完整、照面信息清晰可见。信息不清晰、扭曲、压缩变形、反光、不完整均不接受。
     *  （4）不接受二次剪裁、翻拍、PS的证件照片。
     *  示例值：47ZC6GC-vnrbEny__Ie_An5-tCpqxucuxi-vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "business_license_copy")
    private String businessLicenseCopy;

    /**
     * <pre>
     * 字段名：营业执照注册号
     * 变量名：business_license_number
     * 是否必填：是
     * 类型：string(18)
     * 描述：
     *  1、主体为“个体工商户/企业”时，请填写营业执照上的注册号/统一社会信用代码，须为18位数字|大写字母。
     *  2、主体为“政府机关/事业单位/社会组织”时，请填写登记证书的证书编号。
     *  示例值：123456789012345678
     * </pre>
     */
    @SerializedName(value = "business_license_number")
    private String businessLicenseNumber;

    /**
     * <pre>
     * 字段名：商户名称
     * 变量名：merchant_name
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请填写营业执照/登记证书的商家名称，2~110个字符，支持括号 。
     *  2、个体工商户/政府机关/事业单位/社会组织，不能以“公司”结尾。
     *  3、个体工商户，若营业执照上商户名称为空或为“无”，请填写"个体户+经营者姓名"，如“个体户张三” 。
     *  示例值：腾讯科技有限公司
     * </pre>
     */
    @SerializedName(value = "merchant_name")
    private String merchantName;

    /**
     * <pre>
     * 字段名：经营者/法定代表人姓名
     * 变量名：legal_person
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  请填写证件的经营者/法定代表人姓名
     *  示例值：张三
     * </pre>
     */
    @SerializedName(value = "legal_person")
    private String legalPerson;

    /**
     * <pre>
     * 字段名：注册地址
     * 变量名：company_address
     * 是否必填：条件选填
     * 类型：string(128)
     * 描述：
     *  主体为“政府机关/事业单位/社会组织”时必填，请填写登记证书的注册地址。
     *  示例值：深圳南山区科苑路
     * </pre>
     */
    @SerializedName(value = "company_address")
    private String companyAddress;

    /**
     * <pre>
     * 字段名：营业期限
     * 变量名：business_time
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、主体为“政府机关/事业单位/社会组织”时必填，请填写证件有效期。
     *  2、若证件有效期为长期，请填写：长期。
     *  3、结束时间需大于开始时间。
     *  示例值：["2014-01-01","长期"]
     * </pre>
     */
    @SerializedName(value = "business_time")
    private String businessTime;

  }

  @Data
  @NoArgsConstructor
  public static class FinanceInstitutionInfo implements Serializable {
    /**
     * <pre>
     * 字段名：金融机构类型
     * 变量名：finance_type
     * 是否必填：是
     * 类型：string
     * 描述：
     *  金融机构类型需与营业执照/登记证书上一致，可参考选择金融机构指引。
     *  BANK_AGENT：银行业, 适用于商业银行、政策性银行、农村合作银行、村镇银行、开发性金融机构等
     *  PAYMENT_AGENT：支付机构, 适用于非银行类支付机构
     *  INSURANCE：保险业, 适用于保险、保险中介、保险代理、保险经纪等保险类业务
     *  TRADE_AND_SETTLE：交易及结算类金融机构, 适用于交易所、登记结算类机构、银行卡清算机构、资金清算中心等
     *  OTHER：其他金融机构, 适用于财务公司、信托公司、金融资产管理公司、金融租赁公司、汽车金融公司、贷款公司、货币经纪公司、消费金融公司、证券业、金融控股公司、股票、期货、货币兑换、小额贷款公司、金融资产管理、担保公司、商业保理公司、典当行、融资租赁公司、财经咨询等其他金融业务
     *  示例值：BANK_AGENT
     * </pre>
     */
    @SerializedName(value = "finance_type")
    private String financeType;

    /**
     * <pre>
     * 字段名：金融机构许可证图片
     * 变量名：finance_license_pics
     * 是否必填：是
     * 类型：array
     * 描述：
     *  1、根据所属金融机构类型的许可证要求提供，详情查看金融机构指引。
     *  2、请提供为“申请商家主体”所属的许可证，可授权使用总公司/分公司的特殊资质。
     *  3、最多可上传5张照片，请填写通过图片上传API预先上传图片生成好的MediaID。
     *  示例值：47ZC6GC-vnrbEny__Ie_An5-tCpqxucuxi-vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "finance_license_pics")
    private List<String> financeLicensePics;

  }

  @Data
  @NoArgsConstructor
  public static class IdCardInfo implements Serializable {
    /**
     * <pre>
     * 字段名：身份证人像面照片
     * 变量名：id_card_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、证件类型为“身份证”时，上传身份证人像面照片。
     *  2、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     *  3、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_card_copy")
    private String idCardCopy;

    /**
     * <pre>
     * 字段名：身份证国徽面照片
     * 变量名：id_card_national
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、证件类型为“身份证”时，上传身份证国徽面照片。
     *  2、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID 。
     *  3、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：47ZC6GC-vnrbEny__Ie_An5-tCpqxuZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "id_card_national")
    private String idCardNational;

    /**
     * <pre>
     * 字段名：身份证姓名
     * 变量名：id_card_name
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请填写经营者/法定代表人对应身份证的姓名，2~30个中文字符、英文字符、符号。
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+F6mfjbzQIzfb3HHLEjC4EL5Kz4jBHLiCyOb+tI0m2qhZ9evAM+Jv1z0NVa8MRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "id_card_name")
    @SpecEncrypt
    private String idCardName;

    /**
     * <pre>
     * 字段名：身份证号码
     * 变量名：id_card_number
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请填写经营者/法定代表人对应身份证的号码。
     *  2、15位数字或17位数字+1位数字|X ，该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：AOZdYGISxo4y44/UgZ69bdu9X+tfMUJ9dl+LetjM45/zMbrYu+wWZ8gn4CT3JZKGZjduGdtkRJJp0/0eow96uY1Pk7Rq79Jtt7+I8juwEc4P4TG5xzchG/5IL9DBd+Z0zZXkw==
     * </pre>
     */
    @SerializedName(value = "id_card_number")
    @SpecEncrypt
    private String idCardNumber;

    /**
     * <pre>
     * 字段名：身份证居住地址
     * 变量名：id_card_address
     * 是否必填：条件选填
     * 类型：string(512)
     * 描述：
     *  1、主体类型为企业时，需要填写。其他主体类型，无需上传。
     *  2、请按照身份证住址填写，如广东省深圳市南山区xx路xx号xx室
     *  3、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：AOZdYGISxo4y44/UgZ69bdu9X+tfMUJ9dl+LetjM45/zMbrYu+wWZ8gn4CTdo+D/m9MrPg+V4p0/0eow96uY1Pk7Rq79Jtt7+I8juwEc4P4TG5xzchG/5IL9DBd+Z0zZXkw==
     * </pre>
     */
    @SerializedName(value = "id_card_address")
    @SpecEncrypt
    private String idCardAddress;

    /**
     * <pre>
     * 字段名：身份证开始时间
     * 变量名：id_card_valid_time_begin
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请按照示例值填写。
     *  2、结束时间大于开始时间。
     *  示例值：2019-06-06
     * </pre>
     */
    @SerializedName(value = "id_card_valid_time_begin")
    private String idCardValidTimeBegin;

    /**
     * <pre>
     * 字段名：身份证结束时间
     * 变量名：id_card_valid_time
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请按照示例值填写，若证件有效期为长期，请填写：长期。
     *  2、结束时间大于开始时间。
     *  示例值：2026-06-06
     * </pre>
     */
    @SerializedName(value = "id_card_valid_time")
    private String idCardValidTime;

  }

  @Data
  @NoArgsConstructor
  public static class IdDocInfo implements Serializable {
    /**
     * <pre>
     * 字段名：证件正面照片
     * 变量名：id_doc_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、证件类型不为“身份证”时，上传证件正面照片。
     *  2、可上传1张图片，请填写通过图片图片上传API预先上传图片生成好的MediaID。
     *  3、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_doc_copy")
    private String idDocCopy;

    /**
     * <pre>
     * 字段名：证件反面照片
     * 变量名：id_doc_copy_back
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、若证件类型为来往通行证、外国人居留证、港澳居住证、台湾居住证时，上传证件反面照片。
     *  2、若证件类型为护照，无需上传反面照片。
     *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID 。
     *  4、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：47ZC6GC-vnrbEny__Ie_An5-tCpqxucuxi-vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "id_doc_copy_back")
    private String idDocCopyBack;

    /**
     * <pre>
     * 字段名：证件姓名
     * 变量名：id_doc_name
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请填写经营者/法人姓名。
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_doc_name")
    @SpecEncrypt
    private String idDocName;

    /**
     * <pre>
     * 字段名：证件号码
     * 变量名：id_doc_number
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  7~11位 数字|字母|连字符 。
     *  该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_doc_number")
    @SpecEncrypt
    private String idDocNumber;

    /**
     * <pre>
     * 字段名：证件居住地址
     * 变量名：id_doc_address
     * 是否必填：条件选填
     * 类型：string(512)
     * 描述：
     *  1、主体类型为企业时，需要填写。其他主体类型，无需上传。
     *  2、请按照证件上住址填写，若证件上无住址则按照实际住址填写，如广东省深圳市南山区xx路xx号xx室。
     *  3、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_doc_address")
    @SpecEncrypt
    private String idDocAddress;

    /**
     * <pre>
     * 字段名：证件有效期开始时间
     * 变量名：doc_period_begin
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请按照示例值填写
     *  2、结束时间大于开始时间
     *  示例值：2019-06-06
     * </pre>
     */
    @SerializedName(value = "doc_period_begin")
    private String docPeriodBegin;

    /**
     * <pre>
     * 字段名：证件有效期结束时间
     * 变量名：doc_period_end
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请按照示例值填写，若证件有效期为长期，请填写：长期。
     *  2、结束时间大于开始时间。
     *  示例值：2020-01-02
     * </pre>
     */
    @SerializedName(value = "doc_period_end")
    private String docPeriodEnd;

  }

  @Data
  @NoArgsConstructor
  public static class UboInfo implements Serializable {
    /**
     * <pre>
     * 字段名：证件类型
     * 变量名：ubo_id_doc_type
     * 是否必填：是
     * 类型：string
     * 描述：
     *  请填写受益人的证件类型。
     *  枚举值：
     *  IDENTIFICATION_TYPE_MAINLAND_IDCARD：中国大陆居民-身份证
     *  IDENTIFICATION_TYPE_OVERSEA_PASSPORT：其他国家或地区居民-护照
     *  IDENTIFICATION_TYPE_HONGKONG：中国香港居民--来往内地通行证
     *  IDENTIFICATION_TYPE_MACAO：中国澳门居民--来往内地通行证
     *  IDENTIFICATION_TYPE_TAIWAN：中国台湾居民--来往大陆通行证
     *  IDENTIFICATION_TYPE_FOREIGN_RESIDENT：外国人居留证
     *  IDENTIFICATION_TYPE_HONGKONG_MACAO_RESIDENT：港澳居民证
     *  IDENTIFICATION_TYPE_TAIWAN_RESIDENT：台湾居民证
     *  示例值：IDENTIFICATION_TYPE_MAINLAND_IDCARD
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_type")
    private String uboIdDocType;

    /**
     * <pre>
     * 字段名：证件正面照片
     * 变量名：ubo_id_doc_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请上传受益人证件的正面照片。
     *  2、若证件类型为身份证，请上传人像面照片。
     *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     *  4、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：jTpGmxUXqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_copy")
    private String uboIdDocCopy;

    /**
     * <pre>
     * 字段名：证件反面照片
     * 变量名：ubo_id_doc_copy_back
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、请上传受益人证件的反面照片。
     *  2、若证件类型为护照，无需上传反面照片。
     *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     *  4、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_copy_back")
    private String uboIdDocCopyBack;

    /**
     * <pre>
     * 字段名：证件姓名
     * 变量名：ubo_id_doc_name
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：AOZdYGISxo4y44/Ug4P4TG5xzchG/5IL9DBd+Z0zZXkw==
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_name")
    @SpecEncrypt
    private String uboIdDocName;

    /**
     * <pre>
     * 字段名：证件号码
     * 变量名：ubo_id_doc_number
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：AOZdYGISxo4y44/Ug4P4TG5xzchG/5IL9DBd+Z0zZXkw==
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_number")
    @SpecEncrypt
    private String uboIdDocNumber;

    /**
     * <pre>
     * 字段名：证件居住地址
     * 变量名：ubo_id_doc_address
     * 是否必填：条件选填
     * 类型：string(512)
     * 描述：
     *  1、请按照证件上住址填写，若证件上无住址则按照实际住址填写，如广东省深圳市南山区xx路xx号xx室。
     *  2、 该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+F6mfjbzQIzfDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_address")
    @SpecEncrypt
    private String uboIdDocAddress;

    /**
     * <pre>
     * 字段名：证件有效期开始时间
     * 变量名：ubo_id_doc_period_begin
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请按照示例值填写。
     *  2、结束时间大于开始时间。
     *  示例值：2019-06-06
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_period_begin")
    private String uboIdDocPeriodBegin;

    /**
     * <pre>
     * 字段名：证件有效期结束时间
     * 变量名：ubo_id_doc_period_end
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请按照示例值填写，若证件有效期为长期，请填写：长期。
     *  2、结束时间大于开始时间。
     *  示例值：2026-06-06
     * </pre>
     */
    @SerializedName(value = "ubo_id_doc_period_end")
    private String uboIdDocPeriodEnd;

  }

  @Data
  @NoArgsConstructor
  public static class AccountInfo implements Serializable {
    /**
     * <pre>
     * 字段名：账户类型
     * 变量名：bank_account_type
     * 是否必填：是
     * 类型：string(2)
     * 描述：
     *  1、若主体为企业/政府机关/事业单位/社会组织，可填写：74-对公账户。
     *  2、主体为小微/个人卖家，可选择：75-对私账户。
     *  3、若主体为个体工商户，可填写：74-对公账户、75-对私账户。
     *  示例值：75
     * </pre>
     */
    @SerializedName(value = "bank_account_type")
    private String bankAccountType;

    /**
     * <pre>
     * 字段名：开户银行
     * 变量名：account_bank
     * 是否必填：是
     * 类型：string(10)
     * 描述：
     *  对私银行调用：查询支持个人业务的银行列表API
     *  对公银行调用：查询支持对公业务的银行列表API。
     *  示例值：工商银行
     * </pre>
     */
    @SerializedName(value = "account_bank")
    private String accountBank;

    /**
     * <pre>
     * 字段名：开户名称
     * 变量名：account_name
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、选择经营者个人银行卡时，开户名称必须与身份证姓名一致。
     *  2、选择对公账户时，开户名称必须与营业执照上的“商户名称”一致。
     *  3、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：AOZdYGISxo4y44/UgZ69bdu9X+tfMUJ9dl+LetjM45/zMbrYu+wWZ8gn4CTdo+D/m9MrPg+V4sm73oxqdQu/hj7aWyDl4GQtPXVdaztB9jVbVZh3QFzV+BEmytMNQp9dt1uWJktlfdDdLR3AMWyMB377xd+m9bSr/ioDTzagEcGe+vLYiKrzcroQv3OR0p3ppFYoQ3IfYeU/04S4t9rNFL+kyblK2FCCqQ11NdbbHoCrJc7NV4oASq6ZFonjTtgjjgKsadIKHXtb3JZKGZjduGdtkRJJp0/0eow96uY1Pk7Rq79Jtt7+I8juwEc4P4TG5xzchG/5IL9DBd+Z0zZXkw==
     * </pre>
     */
    @SerializedName(value = "account_name")
    @SpecEncrypt
    private String accountName;

    /**
     * <pre>
     * 字段名：开户银行省市编码
     * 变量名：bank_address_code
     * 是否必填：是
     * 类型：string(12)
     * 描述：
     *  至少精确到市，详细参见省市区编号对照表。
     *  注：
     *   仅当省市区编号对照表中无对应的省市区编号时，可向上取该银行对应市级编号或省级编号。
     *  示例值：110000
     * </pre>
     */
    @SerializedName(value = "bank_address_code")
    private String bankAddressCode;

    /**
     * <pre>
     * 字段名：开户银行联行号
     * 变量名：bank_branch_id
     * 是否必填：条件选填
     * 类型：string(64)
     * 描述：
     *  1、根据开户银行查询接口中的“是否需要填写支行”判断是否需要填写。如为其他银行，开户银行全称（含支行）和开户银行联行号二选一。
     *  2、详细需调用查询支行列表API查看查询结果。
     *  示例值：402713354941
     * </pre>
     */
    @SerializedName(value = "bank_branch_id")
    private String bankBranchId;

    /**
     * <pre>
     * 字段名：开户银行全称 （含支行)
     * 变量名：bank_name
     * 是否必填：条件选填
     * 类型：string(128)
     * 描述：
     *  1、根据开户银行查询接口中的“是否需要填写支行”判断是否需要填写。如为其他银行，开户银行全称（含支行）和开户银行联行号二选一。
     *  2、详细需调用查询支行列表API查看查询结果。
     *  示例值：中国工商银行股份有限公司北京市分行营业部
     * </pre>
     */
    @SerializedName(value = "bank_name")
    private String bankName;

    /**
     * <pre>
     * 字段名：银行帐号
     * 变量名：account_number
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、数字，长度遵循系统支持的对公/对私卡号长度要求表。
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值： d+xT+MQCvrLHUVDWv/8MR/dB7TkXLVfSrUxMPZy6jWWYzpRrEEaYQE8ZRGYoeorwC+w==
     * </pre>
     */
    @SerializedName(value = "account_number")
    @SpecEncrypt
    private String accountNumber;

    /**
     * <pre>
     * 字段名：+银行帐户证明材料
     * 变量名：account_cert_info
     * 是否必填：否
     * 类型：string(128)
     * 描述：
     *  1. 当主体类型是“政府机关/事业单位”时或所属行业为“党费”时，支持在有合法资金管理关系的情况下结算账户设置为非同名。
     *  2. 若结算账户设置为非同名，则需填写非同名证明材料，若结算账户为同名，则无需填写。
     * </pre>
     */
    @SerializedName(value = "account_cert_info")
    private AccountCertInfo accountCertInfo;

    @Data
    @NoArgsConstructor
    public static class AccountCertInfo implements Serializable {
      /**
       * <pre>
       * 字段名：结算证明函
       * 变量名：settlement_cert_pic
       * 是否必填：是
       * 类型：string(256)
       * 描述：
       *  1. 请参照示例图打印结算证明函。
       *  2、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
       *  示例值：47ZC6GC-vnrbEny_Ie_An5-tCpqxucuxi-vByf3Gjm7KEIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
       * </pre>
       */
      @SerializedName(value = "settlement_cert_pic")
      private String settlementCertPic;

      /**
       * <pre>
       * 字段名：关系证明函
       * 变量名：relation_cert_pic
       * 是否必填：是
       * 类型：string(256)
       * 描述：
       *  1. 请参照示例图打印关系证明函。
       *  2、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
       *  示例值：47ZC6GC-vnrbEny_Ie_An5-tCpqxucuxi-vByf3Gjm7KEIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
       * </pre>
       */
      @SerializedName(value = "relation_cert_pic")
      private String relationCertPic;

      /**
       * <pre>
       * 字段名：其他补充证明
       * 变量名：other_cert_pics
       * 是否必填：是
       * 类型：array
       * 描述：
       *  1. 请提供非同名结算的法律法规、政策通知、政府或上级部门公文等证明文件，以作上述材料的补充证明。
       *  2、可上传1-3张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
       *  示例值：47ZC6GC-vnrbEny_Ie_An5-tCpqxucuxi-vByf3Gjm7KEIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
       * </pre>
       */
      @SerializedName(value = "other_cert_pics")
      private String otherCertPics;

    }

  }

  @Data
  @NoArgsConstructor
  public static class ContactInfo implements Serializable {
    /**
     * <pre>
     * 字段名：超级管理员类型
     * 变量名：contact_type
     * 是否必填：是
     * 类型：string(2)
     * 描述：
     *  1、主体为“小微/个人卖家 ”，可选择：65-经营者/法人。
     *  2、主体为“个体工商户/企业/政府机关/事业单位/社会组织”，可选择：65-经营者/法人、66- 经办人。 （经办人：经商户授权办理微信支付业务的人员）。
     *  示例值：65
     * </pre>
     */
    @SerializedName(value = "contact_type")
    private String contactType;

    /**
     * <pre>
     * 字段名：超级管理员姓名
     * 变量名：contact_name
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、若管理员类型为“法人”，则该姓名需与法人身份证姓名一致。
     *  2、若管理员类型为“经办人”，则可填写实际负责人的姓名。
     *  3、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  （后续该管理员需使用实名微信号完成签约）
     *  示例值： pVd1HJ6zyvPedzGaV+X3IdGdbDnuC4Eelw/wDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "contact_name")
    @SpecEncrypt
    private String contactName;

    /**
     * <pre>
     * 字段名：超级管理员证件类型
     * 变量名：contact_id_doc_type
     * 是否必填：条件选填
     * 类型：string
     * 描述：
     *  当超级管理员类型是经办人时，请上传超级管理员证件类型。
     *  IDENTIFICATION_TYPE_MAINLAND_IDCARD：中国大陆居民-身份证
     *  IDENTIFICATION_TYPE_OVERSEA_PASSPORT：其他国家或地区居民-护照
     *  IDENTIFICATION_TYPE_HONGKONG：中国香港居民--来往内地通行证
     *  IDENTIFICATION_TYPE_MACAO：中国澳门居民--来往内地通行证
     *  IDENTIFICATION_TYPE_TAIWAN：中国台湾居民--来往大陆通行证
     *  IDENTIFICATION_TYPE_FOREIGN_RESIDENT：外国人居留证
     *  IDENTIFICATION_TYPE_HONGKONG_MACAO_RESIDENT：港澳居民证
     *  IDENTIFICATION_TYPE_TAIWAN_RESIDENT：台湾居民证
     *  示例值：IDENTIFICATION_TYPE_MAINLAND_IDCARD
     * </pre>
     */
    @SerializedName(value = "contact_id_doc_type")
    private String contactIdDocType;

    /**
     * <pre>
     * 字段名：超级管理员身份证件号码
     * 变量名：contact_id_card_number
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、若超级管理员类型为法人，则该身份证号码需与法人身份证号码一致。若超级管理员类型为经办人，则可填写实际经办人的身份证号码。
     *  2、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码。
     *  3、超级管理员签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。
     *  4、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+F6mfjbzQIzfb3HHDnuC4EL5Kz4jBHLiCyOb+tI0m2qhZ9evAM+Jv1z0NVa8MRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "contact_id_card_number")
    @SpecEncrypt
    private String contactIdCardNumber;

    /**
     * <pre>
     * 字段名：超级管理员证件正面照片
     * 变量名：contact_id_doc_copy
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、当超级管理员类型是经办人时，请上传超级管理员证件的正面照片。
     *  2、若证件类型为身份证，请上传人像面照片。
     *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     *  4、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "contact_id_doc_copy")
    private String contactIdDocCopy;

    /**
     * <pre>
     * 字段名：超级管理员证件反面照片
     * 变量名：contact_id_doc_copy_back
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、当超级管理员类型是经办人时，请上传超级管理员证件的反面照片。
     *  2、若证件类型为护照，无需上传反面照片。
     *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     *  4、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "contact_id_doc_copy_back")
    private String contactIdDocCopyBack;

    /**
     * <pre>
     * 字段名：超级管理员证件有效期开始时间
     * 变量名：contact_id_doc_period_begin
     * 是否必填：条件选填
     * 类型：string(128)
     * 描述：
     *  1、当超级管理员类型是经办人时，请上传证件有效期开始时间。
     *  2、请按照示例值填写。
     *  3、结束时间大于开始时间。
     *  示例值：2019-06-06
     * </pre>
     */
    @SerializedName(value = "contact_id_doc_period_begin")
    private String contactIdDocPeriodBegin;

    /**
     * <pre>
     * 字段名：超级管理员证件有效期结束时间
     * 变量名：contact_id_doc_period_end
     * 是否必填：条件选填
     * 类型：string(128)
     * 描述：
     *  1、当超级管理员类型是经办人时，请上传证件有效期结束时间。
     *  2、请按照示例值填写，若证件有效期为长期，请填写：长期。
     *  3、结束时间大于开始时间。
     *  示例值：2026-06-06
     * </pre>
     */
    @SerializedName(value = "contact_id_doc_period_end")
    private String contactIdDocPeriodEnd;

    /**
     * <pre>
     * 字段名：业务办理授权函
     * 变量名：business_authorization_letter
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、当超级管理员类型是经办人时，请上传业务办理授权函。
     *  2、请参照示例图打印业务办理授权函，全部信息需打印，不支持手写商户信息，并加盖公章。
     *  3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     *  示例值：47ZC6GC-vnrbEny_Ie_An5-tCpqxucuxi-vByf3Gjm7KEIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "business_authorization_letter")
    private String businessAuthorizationLetter;

    /**
     * <pre>
     * 字段名：超级管理员手机
     * 变量名：mobile_phone
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请填写管理员的手机号，11位数字， 用于接收微信支付的重要管理信息及日常操作验证码 。
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+F6mfjbzQIzfb3HHLEjZ4YiNWWNeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "mobile_phone")
    @SpecEncrypt
    private String mobilePhone;

    /**
     * <pre>
     * 字段名：超级管理员邮箱
     * 变量名：contact_email
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、主体类型为“小微商户/个人卖家”可选填，其他主体需必填。
     *  2、用于接收微信支付的开户邮件及日常业务通知。
     *  3、需要带@，遵循邮箱格式校验 。
     *  4、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+FWWNUlw/wDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "contact_email")
    @SpecEncrypt
    private String contactEmail;

  }

  @Data
  @NoArgsConstructor
  public static class SalesSceneInfo implements Serializable {
    /**
     * <pre>
     * 字段名：店铺名称
     * 变量名：store_name
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  请填写店铺全称。
     *  示例值：爱烧烤
     * </pre>
     */
    @SerializedName(value = "store_name")
    private String storeName;

    /**
     * <pre>
     * 字段名：店铺链接
     * 变量名：store_url
     * 是否必填：二选一
     * 类型：string(1024)
     * 描述：
     *  1、店铺二维码or店铺链接二选一必填。
     *  2、请填写店铺主页链接，需符合网站规范。
     *  示例值：http://www.qq.com
     * </pre>
     */
    @SerializedName(value = "store_url")
    private String storeUrl;

    /**
     * <pre>
     * 字段名：店铺二维码
     * 变量名：store_qr_code
     * 是否必填：二选一
     * 类型：string(256)
     * 描述：
     *  1、店铺二维码 or 店铺链接二选一必填。
     *  2、若为电商小程序，可上传店铺页面的小程序二维码。
     *  3、请填写通过图片上传API预先上传图片生成好的MediaID，仅能上传1张图片 。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "store_qr_code")
    private String storeQrCode;

    /**
     * <pre>
     * 字段名：小程序AppID
     * 变量名：mini_program_sub_appid
     * 是否必填：否
     * 类型：string(256)
     * 描述：
     *  1、商户自定义字段，可填写已认证的小程序AppID，认证主体需与二级商户主体一致；
     *  2、完成入驻后， 系统发起二级商户号与该AppID的绑定（即配置为sub_appid，可在发起支付时传入）
     *  示例值：wxa123344545577
     * </pre>
     */
    @SerializedName(value = "mini_program_sub_appid")
    private String miniProgramSubAppid;

  }

  @Data
  @NoArgsConstructor
  public static class SettlementInfo implements Serializable {
    /**
     * <pre>
     * 字段名：结算规则ID
     * 变量名：settlement_id
     * 是否必填：否
     * 类型：int
     * 描述：
     *  1、选填，请选择二级商户的结算规则ID，需匹配电商平台开通工具箱选择的费率档位，详细参见电商二级商户结算规则对照表；https://kf.qq.com/faq/220228qEfuAz220228bMFji6.html
     *  2、若电商平台未传入，将默认选择0.6%费率对应的结算规则id；
     *  示例值：719
     * </pre>
     */
    @SerializedName(value = "settlement_id")
    private Integer settlementId;

    /**
     * <pre>
     * 字段名：所属行业
     * 变量名：qualification_type
     * 是否必填：二选一
     * 类型：string[1, 200]
     * 描述：
     *  1、选填，请填写二级商户所属的行业名称，映射特殊资质要求，详细参见电商二级商户结算规则对照表；
     *  2、若电商平台未传入，将默认填写无需特殊资质的行业名称；
     *  示例值：零售批发/生活娱乐/其他
     * </pre>
     */
    @SerializedName(value = "qualification_type")
    private String qualificationType;

  }

}
