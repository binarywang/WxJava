package com.github.binarywang.wxpay.bean.applyconfirm;

import com.github.binarywang.wxpay.bean.applyment.enums.*;
import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 间连商户开户意愿确认 提交申请对象
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplySubjectConfirmCreateRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 渠道商户号
   * 1、微信支付分配的渠道商户唯一标识
   * 2、当从业机构调用时，该字段必填，需填写从业机构下的渠道商户号
   * 3、当渠道商户调用时，该字段无需填写
   * 示例值：20001111
   */
  @SerializedName("channel_id")
  private String channelId;

  /**
   * 业务申请编号
   * 1、只能由数字、字母或下划线组成
   * 2、服务商自定义的唯一编号。每个编号对应一个申请单
   * 3、建议前缀带上服务商商户号，参看示例值
   * 示例值：1900013511_10000
   */
  @SerializedName("business_code")
  private String businessCode;
  /**
   * 联系人信息
   * 联系人信息，联系人是商户的联系人，将接收开户信息及日常重要管理信息，
   * 请确定联系人为商户法定代表人或经营者再进行操作。如联系人非商户法定代表人或经营者，
   * 请提交经办人身份证件和业务办理授权函。
   */
  @SerializedName("contact_info")
  @SpecEncrypt
  private ApplySubConfirmContactInfo contactInfo;

  /**
   * 主体资料
   */
  @SerializedName("subject_info")
  @SpecEncrypt
  private ApplySubConfirmSubjectInfo subjectInfo;

  /**
   * 法人身份信息
   */
  @SerializedName("identification_info")
  @SpecEncrypt
  private ApplySubConfirmIdentificationInfo identityInfo;

  /**
   * 最终受益人信息列表(UBO)
   */
  @SerializedName("ubo_info_list")
  @SpecEncrypt
  private List<ApplySubConfirmUboInfo> uboInfoList;

  /**
   * 补充材料
   */
  @SerializedName("addition_info")
  private ApplySubConfirmAdditionInfo additionInfo;


  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class ApplySubConfirmIdentificationInfo implements Serializable {
    private static final long serialVersionUID = 1683704338370383827L;

    /**
     * 证件持有人类型
     * 1. 主体类型为政府机关、事业单位时选传：
     * （1）若上传的是法人证件，则不需要上传该字段。
     * （2）若因特殊情况，无法提供法人证件时，可上传经办人。 （经办人：经商户授权办理微信支付业务的人员，授权范围包括但不限于签约，入驻过程需完成账户验证）。
     * 2. 主体类型为企业、个体户、社会组织时，默认为经营者/法人，不需要上传该字段。
     * LEGAL：经营者/法人
     * SUPER：经办人
     * 示例值：LEGAL
     * @see com.github.binarywang.wxpay.bean.ecommerce.ApplymentsRequest 字段idHolderType
     */
    @SerializedName("id_holder_type")
    private String idHolderType;

    /**
     * 证件类型
     * 1、当证件持有人类型为法人时，填写。其他情况，无需上传。
     * 2、个体户/企业/事业单位/社会组织：可选择任一证件类型，政府机关、小微仅支持中国大陆居民-身份证类型。
     * 枚举值：
     * IDENTIFICATION_TYPE_IDCARD：中国大陆居民-身份证
     * IDENTIFICATION_TYPE_OVERSEA_PASSPORT：其他国家或地区居民-护照
     * IDENTIFICATION_TYPE_HONGKONG_PASSPORT：中国香港居民-来往内地通行证
     * IDENTIFICATION_TYPE_MACAO_PASSPORT：中国澳门居民-来往内地通行证
     * IDENTIFICATION_TYPE_TAIWAN_PASSPORT：中国台湾居民-来往大陆通行证
     * IDENTIFICATION_TYPE_FOREIGN_RESIDENT：外国人居留证
     * IDENTIFICATION_TYPE_HONGKONG_MACAO_RESIDENT：港澳居民证
     * IDENTIFICATION_TYPE_TAIWAN_RESIDENT：台湾居民证
     * 示例值：IDENTIFICATION_TYPE_IDCARD
     */
    @SerializedName("identification_type")
    private IdTypeEnum identificationType;

    /**
     * 法定代表人说明函
     */
    @SerializedName("authorize_letter_copy")
    private String authorizeLetterCopy;

    /**
     * 证件姓名
     * 1、当证件持有人类型为法人时，请填写法人证件上的姓名。其他情况，无需上传。
     * 2、长度为2-100个字符
     * 3、前后不能有空格、制表符、换行符
     * 4、不能仅含数字、特殊字符
     * 5、仅能填写数字、英文字母、汉字及特殊字符
     * 6、该字段需进行加密处理，加密方法详见《敏感信息加密说明》
     * 示例值：pVd1HJ6zyvPedzGaV+X3IdGdbDnuC4Eelw/wDa4SzfeespQO/0kjiwfqdfg==
     */
    @SerializedName("identification_name")
    @SpecEncrypt
    private String identificationName;

    /**
     * 证件号码
     * 1、当证件持有人类型为法人时，请填写法人证件上的证件号码。其他情况，无需上传。
     * 2、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码，号码规范如下：
     * 身份证（限中国大陆居民)：17位数字+1位数字|X
     * 护照（限境外人士）：4-15位 数字|字母|连字符
     * 中国香港居民--来往内地通行证：H/h开头+8或10位数字/字母
     * 中国澳门居民--来往内地通行证：M/m开头+8或10位数字/字母
     * 中国台湾居民--来往大陆通行证：8位数字或10位数字
     * 外国人居留证：15位 数字|字母
     * 港澳居住证/台湾居住证：17位数字+1位数字|X
     * 3、该字段需进行加密处理，加密方法详见《敏感信息加密说明》
     * 示例值：pVd1HJ6zmty7/mYNxLMpRSvMRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     */
    @SerializedName("identification_number")
    @SpecEncrypt
    private String identificationNumber;

    /**
     * 证件有效日期
     * 1、日期格式应满足合法的YYYY-MM-DD格式，参见示例值
     * 2、若证件有效期为长期，请填写：fovever。
     * 3、开始时间不能大于等于当前日期，结束时间需大于开始时间。
     * 示例值：[\"2017-10-28\",\"forever\"]
     */
    @SerializedName("identification_valid_date")
    private String identificationValidDate;

    /**
     * 证件有效日期
     * 1、主体类型为企业时，需要填写。其他主体类型，无需上传。
     * 2、请按照身份证住址填写，如广东省深圳市南山区xx路xx号xx室
     * 3、长度为4-128个字符
     * 4、前后不能有空格、制表符、换行符
     * 5、不能仅含数字、特殊字符
     * 6、仅能填写数字、英文字母、汉字及特殊字符
     * 7、仅支持utf-8格式
     * 8、该字段需进行加密处理，加密方法详见《敏感信息加密说明》。
     * 示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ib0NVa8MRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     */
    @SerializedName("identification_address")
    @SpecEncrypt
    private String identificationAddress;


    /**
     * 证件正面照片
     */
    @SerializedName("identification_front_copy")
    private String identificationFrontCopy;

    /**
     * 证件反面照片
     */
    @SerializedName("identification_back_copy")
    private String identificationBackCopy;

    /**
     * 经营者/法人是否为受益人
     */
    @SerializedName("owner")
    private Boolean owner;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class ApplySubConfirmUboInfo implements Serializable {
    private static final long serialVersionUID = 7918585690831975042L;
    /**
     * 证件类型
     */
    @SerializedName("ubo_id_doc_type")
    private IdTypeEnum uboIdDocType;
    /**
     * 证件正面照片
     */
    @SerializedName("ubo_id_doc_copy")
    private String uboIdDocCopy;
    /**
     * 证件反面照片
     */
    @SerializedName("ubo_id_doc_copy_back")
    private String uboIdDocCopyBack;
    /**
     * 证件姓名
     */
    @SerializedName("ubo_id_doc_name")
    @SpecEncrypt
    private String uboIdDocName;
    /**
     * 证件号码
     */
    @SerializedName("ubo_id_doc_number")
    @SpecEncrypt
    private String uboIdDocNumber;
    /**
     * 证件居住地址
     */
    @SerializedName("ubo_id_doc_address")
    @SpecEncrypt
    private String uboIdDocAddress;
    /**
     * 证件有效期开始时间
     */
    @SerializedName("ubo_period_begin")
    private String uboPeriodBegin;
    /**
     * 证件有效期结束时间
     */
    @SerializedName("ubo_period_end")
    private String uboPeriodEnd;
  }


  /**
   * 超级管理员需在开户后进行签约，并接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class ApplySubConfirmContactInfo implements Serializable {

    private static final long serialVersionUID = -480297586102445959L;
    /**
     * 联系人类型
     * 1、主体为“小微/个人卖家 ”，可选择：
     * LEGAL：经营者/法人。
     * 2、主体为“个体工商户/企业/政府机关/事业单位/社会组织”，可选择：
     * LEGAL：经营者/法人、
     * SUPER：经办人。 （经办人：经商户授权办理微信支付业务的人员）。
     * 示例值：LEGAL
     */
    @SerializedName("contact_type")
    private String contactType;

    /**
     * 超级管理员姓名
     */
    @SerializedName("name")
    @SpecEncrypt
    private String name;

    /**
     * 联系人证件类型
     * 当联系人类型是经办人时，请上传联系人证件类型。
     * 枚举值：
     * IDENTIFICATION_TYPE_IDCARD：中国大陆居民-身份证
     * IDENTIFICATION_TYPE_OVERSEA_PASSPORT：其他国家或地区居民-护照
     * IDENTIFICATION_TYPE_HONGKONG_PASSPORT：中国香港居民-来往内地通行证
     * IDENTIFICATION_TYPE_MACAO_PASSPORT：中国澳门居民-来往内地通行证
     * IDENTIFICATION_TYPE_TAIWAN_PASSPORT：中国台湾居民-来往大陆通行证
     * IDENTIFICATION_TYPE_FOREIGN_RESIDENT：外国人居留证
     * IDENTIFICATION_TYPE_HONGKONG_MACAO_RESIDENT：港澳居民证
     * IDENTIFICATION_TYPE_TAIWAN_RESIDENT：台湾居民证
     * 示例值：IDENTIFICATION_TYPE_IDCARD
     */
    @SerializedName("contact_id_doc_type")
    private String contactIdDocType;

    /**
     * 联系人证件号码
     * 1、若联系人类型为法人，则该身份证号码需与法人身份证号码一致。若联系人类型为经办人，则可填写实际经办人的身份证号码。
     * 2、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码，规范如下：
     * 身份证（限中国大陆居民)：17位数字+1位数字|X
     * 护照（限境外人士）：4-15位 数字|字母|连字符
     * 中国香港居民--来往内地通行证：H/h开头+8或10位数字/字母
     * 中国澳门居民--来往内地通行证：M/m开头+8或10位数字/字母
     * 中国台湾居民--来往大陆通行证：8位数字或10位数字
     * 外国人居留证：15位 数字|字母
     * 港澳居住证/台湾居住证：17位数字+1位数字|X
     * 3、联系人签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。
     * 4、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     * 示例值：pVd1HJ6zmty7/mYNxLMpRSvMRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     */
    @SerializedName("id_card_number")
    @SpecEncrypt
    private String contactIdNumber;

    /**
     * 联系人证件正面照片
     * 1、当联系人类型是经办人时，请上传联系人证件的正面照片。
     * 2、若证件类型为身份证，请上传人像面照片。
     * 3、正面拍摄、清晰、四角完整、无反光或遮挡；不得翻拍、截图、镜像、PS。
     * 4、请上传彩色照片or彩色扫描件，复印件需加盖公章鲜章，可添加“微信支付”相关水印（如微信支付认证），见【指引文档】
     * 5、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     * 示例值：jTpGmxUXqRTvDujqhThn4ReFxikqJ5YW6zFQ
     */
    @SerializedName("contact_id_doc_copy")
    private String contactIdDocCopy;

    /**
     * 1、当联系人类型是经办人时，请上传联系人证件的反面照片。
     * 2、若证件类型为护照，无需上传反面照片。
     * 3、正面拍摄、清晰、四角完整、无反光或遮挡；不得翻拍、截图、镜像、PS。
     * 4、请上传彩色照片or彩色扫描件，复印件需加盖公章鲜章，可添加“微信支付”相关水印（如微信支付认证），见【指引文档】
     * 5、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     * 示例值：jTpGmxUX3FBWVQ5NJTZvvDujqhThn4ReFxikqJ5YW6zFQ
     */
    @SerializedName("contact_id_doc_copy_back")
    private String contactIdDocCopyBack;

    /**
     * 联系人证件有效期开始时间
     * 1、当超级管理员类型是经办人时，请上传证件有效期开始时间。
     * 2、请按照示例值填写，日期格式应满足合法的YYYY-MM-DD格式
     * 3、开始时间不能小于1900-01-01，开始时间不能大于等于当前日期。
     * 示例值：2019-06-06
     */
    @SerializedName("contact_period_begin")
    private String contactPeriodBegin;

    /**
     * 联系人证件有效期结束时间
     * 1、当超级管理员类型是经办人时，请上传证件有效期结束时间。
     * 2、请按照示例值填写，日期格式应满足合法的YYYY-MM-DD格式，若证件有效期为长期，请填写：长期。
     * 3、结束时间大于开始时间。
     * 示例值：2026-06-06
     */
    @SerializedName("contact_period_end")
    private String contactPeriodEnd;


    /**
     * 联系人手机号
     * 1、11位数字。
     * 2、用于接收微信支付的重要管理信息及日常操作验证码。
     */
    @SerializedName("mobile")
    @SpecEncrypt
    private String mobile;

  }

  /**
   * 主体资料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class ApplySubConfirmSubjectInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主体类型
     * 主体类型需与营业执照/登记证书上一致，可参考选择主体指引。
     * SUBJECT_TYPE_ENTERPRISE：企业
     * SUBJECT_TYPE_INSTITUTIONS_CLONED：事业单位
     * SUBJECT_TYPE_INDIVIDUAL：个体工商户
     * SUBJECT_TYPE_OTHERS：社会组织
     * SUBJECT_TYPE_MICRO：小微商户
     * SUBJECT_TYPE_GOVERNMENT：政府机关
     * 示例值：SUBJECT_TYPE_ENTERPRISE
     */
    @SerializedName("subject_type")
    private SubjectTypeEnum subjectType;

    /**
     * 是否是金融机构
     * 选填，请根据申请主体的实际情况填写，可参考选择金融机构指引：
     * 1、若商户主体是金融机构，则填写：true。
     * 2、若商户主体不是金融机构，则填写：false。
     * 若未传入将默认填写：false。
     * 示例值：true
     */
    @SerializedName("is_finance_institution")
    private Boolean financeInstitution;

    /**
     * 营业执照
     */
    @SerializedName("business_licence_info")
    private ApplySubConfirmBusinessLicenseInfo businessLicenseInfo;
    /**
     * 登记证书
     */
    @SerializedName("certificate_info")
    private ApplySubConfirmCertificateInfo certificateInfo;

    /**
     * 单位证明函照片
     * 1、主体类型为政府机关/事业单位时，单位证明函照片必填。
     * 2、单位证明函格式参考示例
     * 3、请填写通过《图片上传API》预先上传图片生成好的MediaID
     * 示例值：0P3ng6KTIW4-Q_l2FjKLZuhHjBWoMAjmVtCz7ScmhEIThCaV-4BBgVwtNkCHO_XXqK5dE5YdOmFJBZR9FwczhJehHhAZN6BKXQPcs-VvdSo
     */
    @SerializedName("company_prove_copy")
    private String companyProveCopy;

    /**
     * 辅助证明材料信息
     * 主体类型为小微商户时，辅助证明材料信息必填
     */
    @SerializedName("assist_prove_info")
    private ApplySubConfirmAssistProveInfo assistProveInfo;

    /**
     * 经营许可证
     */
    @SerializedName("special_operation_list")
    private List<ApplySubConfirmSpecialOperationList> specialOperationList;

    /**
     * 金融机构许可证信息
     */
    @SerializedName("finance_institution_info")
    private ApplySubConfirmFinanceInstitutionInfo financeInstitutionInfo;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ApplySubConfirmBusinessLicenseInfo implements Serializable {
      private static final long serialVersionUID = -1016615300418945838L;
      /**
       * 注册号/统一社会信用代码
       * 1、主体为“个体工商户”时，请填写营业执照上的注册号/统一社会信用代码，格式需满足以下任一条件：
       * -15位数字
       * -18位阿拉伯数字或大写英文字母（不得包含英文字母I/O/Z/S/V），并且以9开头
       * 2、主体为“企业”时，请填写营业执照上的注册号/统一社会信用代码，格式如下：
       * -18位阿拉伯数字或大写英文字母（不得包含英文字母I/O/Z/S/V），并且以9开头
       * 示例值：914201123033363296
       */
      @SerializedName("license_number")
      private String licenseNumber;

      /**
       * 营业执照照片
       * 1、照片应正面拍摄、清晰、四角完整、无反光或遮挡；不得翻拍、截图、镜像、PS；
       * 2、上传彩色照片、彩色扫描件，复印件需加盖公章鲜章。
       * 3、水印仅限于微信支付业务相关。
       * 4、指引与示例可参考【指引文档】
       * 5、请填写通过《图片上传API》预先上传图片生成好的MediaID
       * 示例值：0P3ng6KTIW4-Q_l2FjKLZuhHjBWoMAjmVtCz7ScmhEIThCaV-4BBgVwtNkCHO_XXqK5dE5YdOmFJBZR9FwczhJehHhAZN6BKXQPcs-VvdSo
       */
      @SerializedName("license_copy")
      private String licenseCopy;

      /**
       * 商户名称
       * 1、长度为2-128个字符
       * 2、前后不能有空格、制表符、换行符
       * 3、不能仅含数字、特殊字符
       * 4、仅能填写数字、英文字母、汉字及特殊字符
       * 5、仅支持utf-8格式
       * 6、个体户证件为以下情况时，按照个体户XXX命名（XXX是营业执照经营人姓名）：营业执照登记名称为空、仅含数字、仅含特殊字符、“无”、“无字号”
       * 7、个体户不能使用“企业”“公司”或“农民专业合作社”结尾
       * 示例值：李四网络有限公司
       */
      @SerializedName("merchant_name")
      private String merchantName;
      /**
       * 法人姓名
       * 请填写营业执照的经营者/法定代表人姓名
       * 1、长度为2-100个字符
       * 2、前后不能有空格、制表符、换行符
       * 3、不能仅含特殊字符
       * 4、仅能填写数字、英文字母、汉字及特殊字符
       * 示例值：李四
       */
      @SerializedName("legal_person")
      private String legalPerson;
      /**
       * 注册地址
       * 建议填写营业执照的注册地址
       * 1、长度为4-128个字符
       * 2、前后不能有空格、制表符、换行符
       * 3、不能仅含数字、特殊字符
       * 4、仅能填写数字、英文字母、汉字及特殊字符
       * 5、仅支持utf-8格式
       * 示例值：广东省深圳市南山区xx路xx号
       */
      @SerializedName("company_address")
      private String companyAddress;
      /**
       * 营业执照有效日期
       * 1、日期格式应满足合法的YYYY-MM-DD格式，参见示例值
       * 2、开始时间不能小于1900-01-01
       * 3、若证件有效期为长期，请填写：fovever。
       * 4、开始时间不能大于等于当前日期，结束时间需大于开始时间。
       * 示例值：[\"2017-10-28\",\"2037-10-28\"]
       *
       */
      @SerializedName("licence_valid_date")
      private String periodBegin;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ApplySubConfirmCertificateInfo implements Serializable {
      private static final long serialVersionUID = 5080675335337916895L;

      /**
       * 登记证书照片
       * 1、照片应正面拍摄、清晰、四角完整、无反光或遮挡；不得翻拍、截图、镜像、PS；
       * 2、上传彩色照片、彩色扫描件，复印件需加盖公章鲜章。
       * 3、水印仅限于微信支付业务相关。
       * 4、指引与示例可参考【指引文档】
       * 5、请填写通过《图片上传API》预先上传图片生成好的MediaID
       * 示例值：0P3ng6KTIW4-Q_l2FjKLZuhHjBWoMAjmVtCz7ScmhEIThCaV-4BBgVwtNkCHO_XXqK5dE5YdOmFJBZR9FwczhJehHhAZN6BKXQPcs-VvdSo
       */
      @SerializedName("cert_copy")
      private String certCopy;

      /**
       * 登记证书类型
       * 登记证书的类型。
       * 1、主体为“政府机关/事业单位/社会组织”时，请上传登记证书类型。
       *
       * 当主体为事业单位时，选择此枚举值：
       * CERTIFICATE_TYPE_2388：事业单位法人证书
       *
       * 当主体为政府机关，选择此枚举值：
       * CERTIFICATE_TYPE_2389：统一社会信用代码证书
       *
       * 当主体为社会组织，选择以下枚举值之一：
       * CERTIFICATE_TYPE_2389：统一社会信用代码证书
       * CERTIFICATE_TYPE_2394：社会团体法人登记证书
       * CERTIFICATE_TYPE_2395：民办非企业单位登记证书
       * CERTIFICATE_TYPE_2396：基金会法人登记证书
       * CERTIFICATE_TYPE_2520：执业许可证/执业证
       * CERTIFICATE_TYPE_2521：基层群众性自治组织特别法人统一社会信用代码证
       * CERTIFICATE_TYPE_2522：农村集体经济组织登记证
       * CERTIFICATE_TYPE_2399：宗教活动场所登记证
       * CERTIFICATE_TYPE_2400：政府部门下发的其他有效证明文件
       * 示例值：CERTIFICATE_TYPE_2388
       */
      @SerializedName("cert_type")
      private CertTypeEnum certType;


      /**
       * 证书号
       * 请输入与所选证书类型相匹配且符合国家标准规范的证书号，其中除政府证明文件外，需满足18位阿拉伯数字或大写英文字母（不得包含英文字母I/O/Z/S/V）
       * 示例值：111111111111
       */
      @SerializedName("cert_number")
      private String certNumber;


      /**
       * 商户名称
       * 请填写登记证书上的商户名称
       * 1、长度为2-128个字符
       * 2、前后不能有空格、制表符、换行符
       * 3、不能仅含数字、特殊字符
       * 4、仅能填写数字、英文字母、汉字及特殊字符
       * 5、仅支持utf-8格式
       * 示例值：xx公益团体
       */
      @SerializedName("merchant_name")
      private String merchantName;


      /**
       * 注册地址
       */
      @SerializedName("company_address")
      private String companyAddress;


      /**
       * 法人姓名
       * 请填写登记证书上的法定代表人姓名
       * 1、长度为2-100个字符
       * 2、前后不能有空格、制表符、换行符
       * 3、不能仅含特殊字符
       * 4、仅能填写数字、英文字母、汉字及特殊字符
       * 示例值：李四
       */
      @SerializedName("legal_person")
      private String legalPerson;


      /**
       * 证书有效日期
       * 1、日期格式应满足合法的YYYY-MM-DD格式，参见示例值
       * 2、若证件有效期为长期，请填写：fovever。
       * 3、开始时间不能大于等于当前日期，结束时间需大于开始时间。
       * 示例值：["2017-10-28","2037-10-28"]
       */
      @SerializedName("cert_valid_date")
      private String certValidDate;

    }

    /**
     * 辅助证明材料信息
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ApplySubConfirmAssistProveInfo implements Serializable {

      private static final long serialVersionUID = -3394274542660805766L;
      /**
       * 小微经营类型
       * MICRO_TYPE_STORE：门店场所
       * MICRO_TYPE_MOBILE：流动经营/便民服务
       * MICRO_TYPE_ONLINE：线上商品/服务交易
       * 示例值：MICRO_TYPE_STORE
       */
      @SerializedName("micro_biz_type")
      private String microBizType;
      /**
       * 门店名称
       * 1、填写规范：
       * 门店场所：填写门店名称
       * 流动经营/便民服务：填写经营/服务名称
       * 线上商品/服务交易：填写线上店铺名称
       * 2、格式规范：
       * 长度为1-50个字符
       * 前后不能有空格、制表符、换行符
       * 不能仅含数字、特殊字符
       * 仅能填写数字、英文字母、汉字及特殊字符
       * 仅支持utf-8格式
       * 示例值：大郎烧饼
       */
      @SerializedName("store_name")
      private String storeName;
      /**
       * 门店省市编码
       * 1、只能由数字组成
       * 2、详细参见微信支付提供的省市对照表
       * 3、填写规范：
       * 门店场所：填写门店省市编码
       * 流动经营/便民服务：填写经营/服务所在地省市编码
       * 线上商品/服务交易：填写卖家所在地省市编码
       * 示例值：440305
       */
      @SerializedName("store_address_code")
      private String storeAddressCode;
      /**
       * 门店地址
       * 1、填写规范：
       * 门店场所：填写店铺详细地址，具体区/县及街道门牌号或大厦楼层
       * 流动经营/便民服务：填写“无”
       * 线上商品/服务交易：填写电商平台名称
       * 2、格式规范：
       * 长度为4-512个字符
       * 前后不能有空格、制表符、换行符
       * 不能仅含数字、特殊字符
       * 仅能填写数字、英文字母、汉字及特殊字符
       * 仅支持utf-8格式
       * 示例值：广东省深圳市南山区xx大厦x层xxxx室
       */
      @SerializedName("store_address")
      private String storeAddress;
      /**
       * 门店门头照片
       * 1、请上传门头正面照片（要求门店招牌、门框完整、清晰、可辨识）；若为停车场等无固定门头照片的经营场所，可上传岗亭/出入闸口。具体参考【指引文档】；
       * 2、请填写通过《图片上传API》预先上传图片生成好的MediaID
       * 示例值：0P3ng6KTIW4-Q_l2FjKLZuhHjBWoMAjmVtCz7ScmhEIThCaV-4BBgVwtNkCHO_XXqK5dE5YdOmFJBZR9FwczhJehHhAZN6BKXQPcs-VvdSo
       */
      @SerializedName("store_header_copy")
      private String storeHeaderCopy;
      /**
       * 店内环境照片
       * 1、请上传门店内部环境照片（可辨识经营内容）。若为停车场等无固定门头的经营场所，可上传停车场内部照片。具体参考【指引文档】；
       * 2、请填写通过《图片上传API》预先上传图片生成好的MediaID
       * 示例值：0P3ng6KTIW4-Q_l2FjKLZuhHjBWoMAjmVtCz7ScmhEIThCaV-4BBgVwtNkCHO_XXqK5dE5YdOmFJBZR9FwczhJehHhAZN6BKXQPcs-VvdSo
       */
      @SerializedName("store_indoor_copy")
      private String storeIndoorCopy;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ApplySubConfirmSpecialOperationList implements Serializable {

      private static final long serialVersionUID = 6016563999835704297L;
      /**
       * 行业类目id
       * 参看微信支付提供的特殊行业id对照表
       * 示例值：100
       */
      @SerializedName("category_id")
      private Integer categoryId;

      /**
       * 行业经营许可证资质照片
       * 1、请根据特殊行业id对照表内指引，仅当所选择的行业为【必填经营许可证】的行业时，才需上传该项资料
       * 2、请填写通过《图片上传API》预先上传图片生成好的MediaID
       * 3、每个行业最多支持5张资质照片
       * 示例值：0P3ng6KTIW4-Q_l2FjKLZuhHjBWoMAjmVtCz7ScmhEIThCaV-4BBgVwtNkCHO_XXqK5dE5YdOmFJBZR9FwczhJehHhAZN6BKXQPcs-VvdSo
       */
      @SerializedName("operation_copy_list")
      private List<String> financeLicensePics;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ApplySubConfirmFinanceInstitutionInfo implements Serializable {

      private static final long serialVersionUID = 6016563999835704297L;
      /**
       * 金融机构类型
       *
       * @see FinanceTypeEnum
       */
      @SerializedName("finance_type")
      private FinanceTypeEnum financeType;

      /**
       * 金融机构许可证图片
       */
      @SerializedName("finance_license_pics")
      private List<String> financeLicensePics;
    }




  }


  /**
   * 补充材料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class ApplySubConfirmAdditionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 待确认商户号列表
     */
    @SerializedName("confirm_mchid_list")
    private List<String> confirmMchidList;
  }

}
