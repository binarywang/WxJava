package com.github.binarywang.wxpay.bean.applyment;

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
 * 特约商户进件 提交申请对象
 *
 * @author zhouyongshen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxPayApplyment4SubCreateRequest implements Serializable {
  private static final long serialVersionUID = 4104022969945059126L;

  /**
   * 业务申请编号
   */
  @SerializedName("business_code")
  private String businessCode;
  /**
   * 超级管理员信息
   */
  @SerializedName("contact_info")
  @SpecEncrypt
  private ContactInfo contactInfo;

  /**
   * 主体资料
   */
  @SerializedName("subject_info")
  @SpecEncrypt
  private SubjectInfo subjectInfo;

  /**
   * 经营资料
   */
  @SerializedName("business_info")
  private BusinessInfo businessInfo;

  /**
   * 结算规则
   */
  @SerializedName("settlement_info")
  private SettlementInfo settlementInfo;

  /**
   * 结算银行账户
   */
  @SerializedName("bank_account_info")
  @SpecEncrypt
  private BankAccountInfo bankAccountInfo;

  /**
   * 补充材料
   */
  @SerializedName("addition_info")
  private AdditionInfo additionInfo;

  /**
   * 超级管理员需在开户后进行签约，并接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class ContactInfo implements Serializable {
    private static final long serialVersionUID = -9087348002744428474L;

    /**
     * 超级管理员类型
     * 1、主体为“个体工商户/企业/政府机关/事业单位/社会组织”，可选择：LEGAL：经营者/法人，SUPER：经办人 。（经办人：经商户授权办理微信支付业务的人员）。
     * 枚举值：
     * LEGAL：经营者/法人
     * SUPER：经办人
     * 示例值：LEGAL
     */
    @SerializedName("contact_type")
    private String contactType;

    /**
     * 超级管理员姓名
     */
    @SerializedName("contact_name")
    @SpecEncrypt
    private String contactName;

    /**
     * 超级管理员证件类型
     * 当超级管理员类型是经办人时，请上传超级管理员证件类型。
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
     * 超级管理员身份证件号码
     * 1、“超级管理员身份证号码”与“超级管理员微信openid”，二选一必填。
     * 2、超级管理员签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。
     * 3、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码。
     */
    @SerializedName("contact_id_number")
    @SpecEncrypt
    private String contactIdNumber;

    /**
     * 超级管理员证件正面照片
     * 1、当超级管理员类型是经办人时，请上传超级管理员证件的正面照片。
     * 2、若证件类型为身份证，请上传人像面照片。
     * 3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     * 4、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     * 示例值：jTpGmxUXqRTvDujqhThn4ReFxikqJ5YW6zFQ
     */
    @SerializedName("contact_id_doc_copy")
    private String contactIdDocCopy;

    /**
     * 超级管理员证件反面照片
     * 1、当超级管理员类型是经办人时，请上传超级管理员证件的反面照片。
     * 2、若证件类型为护照，无需上传反面照片。
     * 3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     * 4、请上传彩色照片or彩色扫描件or复印件（需加盖公章鲜章），可添加“微信支付”相关水印（如微信支付认证）。
     * 示例值：jTpGmxUX3FBWVQ5NJTZvvDujqhThn4ReFxikqJ5YW6zFQ
     */
    @SerializedName("contact_id_doc_copy_back")
    private String contactIdDocCopyBack;

    /**
     * 超级管理员证件有效期开始时间
     * 1、当超级管理员类型是经办人时，请上传证件有效期开始时间。
     * 2、请按照示例值填写。
     * 3、结束时间大于开始时间。
     * 示例值：2019-06-06
     */
    @SerializedName("contact_period_begin")
    private String contactPeriodBegin;

    /**
     * 超级管理员证件有效期结束时间
     * 1、当超级管理员类型是经办人时，请上传证件有效期结束时间。
     * 2、请按照示例值填写，若证件有效期为长期，请填写：长期。
     * 3、结束时间大于开始时间。
     * 示例值：2026-06-06
     */
    @SerializedName("contact_period_end")
    private String contactPeriodEnd;

    /**
     * 业务办理授权函
     * 1、当超级管理员类型是经办人时，请上传业务办理授权函。
     * 2、请参照[示例图]打印业务办理授权函，全部信息需打印，不支持手写商户信息，并加盖公章。
     * 3、可上传1张图片，请填写通过图片上传API预先上传图片生成好的MediaID。
     * 示例值：47ZC6GC-vnrbEny_Ie_An5-tCpqxucuxi-vByf3Gjm7KEIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     */
    @SerializedName("business_authorization_letter")
    private String businessAuthorizationLetter;

    /**
     * 超级管理员微信openid
     * 1、“超级管理员身份证件号码”与“超级管理员微信openid”，二选一必填。
     * 2、超级管理员签约时，校验微信号是否与该微信openid一致。
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 1、11位数字。
     * 2、用于接收微信支付的重要管理信息及日常操作验证码。
     */
    @SerializedName("mobile_phone")
    @SpecEncrypt
    private String mobilePhone;

    /**
     * 1、用于接收微信支付的开户邮件及日常业务通知。
     * 2、需要带@，遵循邮箱格式校验，该字段需进行加密处理，
     */
    @SerializedName("contact_email")
    @SpecEncrypt
    private String contactEmail;

  }

  /**
   * 主体资料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class SubjectInfo implements Serializable {
    private static final long serialVersionUID = -6651911735969445765L;

    /**
     * 主体类型
     */
    @SerializedName("subject_type")
    private SubjectTypeEnum subjectType;

    /**
     * 是否是金融机构
     */
    @SerializedName("finance_institution")
    private Boolean financeInstitution;

    /**
     * 营业执照
     */
    @SerializedName("business_license_info")
    private BusinessLicenseInfo businessLicenseInfo;
    /**
     * 登记证书
     */
    @SerializedName("certificate_info")
    private CertificateInfo certificateInfo;

    /**
     * 单位证明函照片
     */
    @SerializedName("certificate_letter_copy")
    private String certificateLetterCopy;

    /**
     * 小微辅助证明材料
     * 主体类型为小微商户时，小微辅助证明材料必填
     */
    @SerializedName("micro_biz_info")
    private MicroBizInfo microBizInfo;

    /**
     * 金融机构许可证信息
     */
    @SerializedName("finance_institution_info")
    private FinanceInstitutionInfo financeInstitutionInfo;

    /**
     * 经营者/法人身份证件
     */
    @SerializedName("identity_info")
    @SpecEncrypt
    private IdentityInfo identityInfo;

    /**
     * 最终受益人信息列表(UBO)
     */
    @SerializedName("ubo_info_list")
    @SpecEncrypt
    private List<UboInfo> uboInfoList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class BusinessLicenseInfo implements Serializable {
      private static final long serialVersionUID = -1016615300418945838L;
      /**
       * 营业执照照片
       */
      @SerializedName("license_copy")
      private String licenseCopy;
      /**
       * 注册号/统一社会信用代码
       */
      @SerializedName("license_number")
      private String licenseNumber;
      /**
       * 商户名称
       */
      @SerializedName("merchant_name")
      private String merchantName;
      /**
       * 个体户经营者/法人姓名
       */
      @SerializedName("legal_person")
      private String legalPerson;
      /**
       * 注册地址
       */
      @SerializedName("license_address")
      private String licenseAddress;
      /**
       * 有效期限开始日期
       */
      @SerializedName("period_begin")
      private String periodBegin;
      /**
       * 有效期限结束日期
       */
      @SerializedName("period_end")
      private String periodEnd;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class CertificateInfo implements Serializable {
      private static final long serialVersionUID = 5080675335337916895L;

      /**
       * 登记证书照片
       */
      @SerializedName("cert_copy")
      private String certCopy;

      /**
       * 登记证书类型
       */
      @SerializedName("cert_type")
      private CertTypeEnum certType;


      /**
       * 证书号
       */
      @SerializedName("cert_number")
      private String certNumber;


      /**
       * 商户名称
       */
      @SerializedName("merchant_name")
      private String merchantName;


      /**
       * 注册地址
       */
      @SerializedName("company_address")
      private String companyAddress;


      /**
       * 法定代表人
       */
      @SerializedName("legal_person")
      private String legalPerson;


      /**
       * 有效期限开始日期
       */
      @SerializedName("period_begin")
      private String periodBegin;


      /**
       * 有效期限结束日期
       */
      @SerializedName("period_end")
      private String periodEnd;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class FinanceInstitutionInfo implements Serializable {

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

    /**
     * 小微辅助证明材料
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class MicroBizInfo implements Serializable {

      private static final long serialVersionUID = 2327302539406612422L;

      /**
       * 小微经营类型
       * 枚举值：
       * MICRO_TYPE_STORE：门店场所
       * MICRO_TYPE_MOBILE：流动经营/便民服务
       * MICRO_TYPE_ONLINE：线上商品/服务交易
       * 示例值：MICRO_TYPE_STORE
       */
      @SerializedName("micro_biz_type")
      private MicroBizTypeEnum microBizType;

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
      @SerializedName("micro_name")
      private String microName;

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
      @SerializedName("micro_address_code")
      private String microAddressCode;

      /**
       * 门店地址
       * 1、填写规范：
       * 门店场所：填写店铺详细地址，具体区/县及街道门牌号或大厦楼层
       * 流动经营/便民服务：填写"无"
       * 线上商品/服务交易：填写电商平台名称
       * 2、格式规范：
       * 长度为4-512个字符
       * 前后不能有空格、制表符、换行符
       * 不能仅含数字、特殊字符
       * 仅能填写数字、英文字母、汉字及特殊字符
       * 仅支持utf-8格式
       * 示例值：广东省深圳市南山区xx大厦x层xxxx室
       */
      @SerializedName("micro_address")
      private String microAddress;

      /**
       * 门店门头照片/经营场景照片
       * 1、门店场所：请上传门头正面照片（要求门店招牌、门框完整、清晰、可辨识）；若为停车场等无固定门头照片的经营场所，可上传岗亭/出入闸口；
       * 2、流动经营/便民服务：填写媒体文件ID列表，最多5张；
       * 3、线上商品/服务交易：请上传线上店铺网页截图（清晰度足够识别店铺名称的首页截图）；
       * 4、请填写通过《图片上传API》预先上传图片生成好的MediaID
       * 示例值：0P3ng6KTIW4-Q_l2FjKLZuhHjBWoMAjmVtCz7ScmhEIThCaV-4BBgVwtNkCHO_XXqK5dE5YdOmFJBZR9FwczhJehHhAZN6BKXQPcs-VvdSo
       */
      @SerializedName("micro_pics")
      private List<String> microPics;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class IdentityInfo implements Serializable {
      private static final long serialVersionUID = 1683704338370383827L;

      /**
       * 证件持有人类型
       * LEGAL：法人
       * SUPER：经办人
       * 示例值：LEGAL
       * @see com.github.binarywang.wxpay.bean.ecommerce.ApplymentsRequest 字段idHolderType
       */
      @SerializedName("id_holder_type")
      private String idHolderType;

      /**
       * 证件类型
       */
      @SerializedName("id_doc_type")
      private IdTypeEnum idDocType;

      /**
       * 法定代表人说明函
       */
      @SerializedName("authorize_letter_copy")
      private String authorizeLetterCopy;

      /**
       * 身份证信息
       */
      @SerializedName("id_card_info")
      @SpecEncrypt
      private IdCardInfo idCardInfo;

      /**
       * 其他类型证件信息
       */
      @SerializedName("id_doc_info")
      @SpecEncrypt
      private IdDocInfo idDocInfo;

      /**
       * 经营者/法人是否为受益人
       */
      @SerializedName("owner")
      private Boolean owner;

      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class IdCardInfo implements Serializable {
        private static final long serialVersionUID = -2897792705297641786L;
        /**
         * 身份证人像面照片
         */
        @SerializedName("id_card_copy")
        private String idCardCopy;
        /**
         * 身份证国徽面照片
         */
        @SerializedName("id_card_national")
        private String idCardNational;
        /**
         * 身份证姓名
         */
        @SerializedName("id_card_name")
        @SpecEncrypt
        private String idCardName;
        /**
         * 身份证号码
         */
        @SerializedName("id_card_number")
        @SpecEncrypt
        private String idCardNumber;
        /**
         * 身份证居住地址
         */
        @SerializedName("id_card_address")
        @SpecEncrypt
        private String idCardAddress;
        /**
         * 身份证有效期开始时间
         */
        @SerializedName("card_period_begin")
        private String cardPeriodBegin;
        /**
         * 身份证有效期结束时间
         */
        @SerializedName("card_period_end")
        private String cardPeriodEnd;

      }

      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class IdDocInfo implements Serializable {
        private static final long serialVersionUID = 7335589815924447719L;
        /**
         * 证件正面照片
         */
        @SerializedName("id_doc_copy")
        private String idDocCopy;
        /**
         * 证件反面照片
         */
        @SerializedName("id_doc_copy_back")
        private String idDocCopyBack;

        /**
         * 证件姓名
         */
        @SerializedName("id_doc_name")
        @SpecEncrypt
        private String idDocName;

        /**
         * 证件号码
         */
        @SerializedName("id_doc_number")
        @SpecEncrypt
        private String idDocNumber;
        /**
         * 证件居住地址
         */
        @SerializedName("id_doc_address")
        @SpecEncrypt
        private String idDocAddress;
        /**
         * 证件有效期开始时间
         */
        @SerializedName("doc_period_begin")
        private String docPeriodBegin;
        /**
         * 证件有效期结束时间
         */
        @SerializedName("doc_period_end")
        private String docPeriodEnd;
      }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class UboInfo implements Serializable {
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
  }

  /**
   * 经营资料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class BusinessInfo implements Serializable {
    private static final long serialVersionUID = -8605049544105644011L;

    /**
     * 商户简称
     */
    @SerializedName("merchant_shortname")
    private String merchantShortname;

    /**
     * 客服电话
     */
    @SerializedName("service_phone")
    private String servicePhone;

    /**
     * 经营场景
     */
    @SerializedName("sales_info")
    private SalesInfo salesInfo;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class SalesInfo implements Serializable {
      private static final long serialVersionUID = 6428044729204137659L;
      /**
       * 经营场景类型
       */
      @SerializedName("sales_scenes_type")
      private List<SalesScenesTypeEnum> salesScenesType;

      /**
       * 线下门店场景
       */
      @SerializedName("biz_store_info")
      private BizStoreInfo bizStoreInfo;

      /**
       * 公众号场景
       */
      @SerializedName("mp_info")
      private MpInfo mpInfo;

      /**
       * 小程序场景
       */
      @SerializedName("mini_program_info")
      private MiniProgramInfo miniProgramInfo;

      /**
       * APP场景
       */
      @SerializedName("app_info")
      private AppInfo appInfo;

      /**
       * 互联网网站场景
       */
      @SerializedName("web_info")
      private WebInfo webInfo;

      /**
       * 企业微信场景
       */
      @SerializedName("wework_info")
      private WeworkInfo weworkInfo;

      /**
       * 线下门店场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class BizStoreInfo implements Serializable {
        private static final long serialVersionUID = 4397253725912709093L;
        /**
         * 门店名称
         */
        @SerializedName("biz_store_name")
        private String bizStoreName;

        /**
         * 门店省市编码
         */
        @SerializedName("biz_address_code")
        private String bizAddressCode;

        /**
         * 门店地址
         */
        @SerializedName("biz_store_address")
        private String bizStoreAddress;

        /**
         * 门店门头照片
         */
        @SerializedName("store_entrance_pic")
        private List<String> storeEntrancePic;

        /**
         * 店内环境照片
         */
        @SerializedName("indoor_pic")
        private List<String> indoorPic;

        /**
         * 线下场所对应的商家APPID
         */
        @SerializedName("biz_sub_appid")
        private String bizSubAppid;

      }

      /**
       * 公众号场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class MpInfo implements Serializable {
        private static final long serialVersionUID = 167582552189873597L;
        /**
         * 服务商公众号APPID
         */
        @SerializedName("mp_appid")
        private String mpAppid;

        /**
         * 商家公众号APPID
         */
        @SerializedName("mp_sub_appid")
        private String mpSubAppid;

        /**
         * 公众号页面截图
         */
        @SerializedName("mp_pics")
        private List<String> mpPics;

      }

      /**
       * 小程序场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class MiniProgramInfo implements Serializable {
        private static final long serialVersionUID = -371749335686796436L;
        /**
         * 服务商小程序APPID
         */
        @SerializedName("mini_program_appid")
        private String miniProgramAppid;

        /**
         * 商家小程序APPID
         */
        @SerializedName("mini_program_sub_appid")
        private String miniProgramSubAppid;

        /**
         * 小程序截图
         */
        @SerializedName("mini_program_pics")
        private List<String> miniProgramPics;


      }

      /**
       * APP场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class AppInfo implements Serializable {
        private static final long serialVersionUID = 3959643687528770473L;
        /**
         * 服务商应用APPID
         */
        @SerializedName("app_appid")
        private String appAppid;

        /**
         * 商家应用APPID
         */
        @SerializedName("app_sub_appid")
        private String appSubAppid;

        /**
         * APP截图
         */
        @SerializedName("app_pics")
        private List<String> appPics;

      }

      /**
       * 互联网网站场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class WebInfo implements Serializable {
        private static final long serialVersionUID = -4183874827185822310L;
        /**
         * 互联网网站域名
         */
        @SerializedName("domain")
        private String domain;

        /**
         * 网站授权函
         */
        @SerializedName("web_authorisation")
        private String webAuthorisation;

        /**
         * 互联网网站对应的商家APPID
         */
        @SerializedName("web_appid")
        private String webAppid;

      }

      /**
       * 企业微信场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class WeworkInfo implements Serializable {
        private static final long serialVersionUID = 9075531305717309383L;
        /**
         * 商家企业微信CorpID
         */
        @SerializedName("sub_corp_id")
        private String subCorpId;

        /**
         * 企业微信页面截图
         */
        @SerializedName("wework_pics")
        private List<String> weworkPics;

      }
    }
  }

  /**
   * 结算规则
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class SettlementInfo implements Serializable {
    private static final long serialVersionUID = -5025743467243760522L;

    /**
     * 入驻结算规则ID
     */
    @SerializedName("settlement_id")
    private String settlementId;

    /**
     * 所属行业
     */
    @SerializedName("qualification_type")
    private String qualificationType;

    /**
     * 特殊资质图片
     */
    @SerializedName("qualifications")
    private List<String> qualifications;

    /**
     * 优惠费率活动ID
     */
    @SerializedName("activities_id")
    private String activitiesId;

    /**
     * 优惠费率活动值
     */
    @SerializedName("activities_rate")
    private String activitiesRate;

    /**
     * 非信用卡活动费率值
     */
    @SerializedName("debit_activities_rate")
    private String debitActivitiesRate;

    /**
     * 信用卡活动费率值
     */
    @SerializedName("credit_activities_rate")
    private String creditActivitiesRate;

    /**
     * 优惠费率活动补充材料
     */
    @SerializedName("activities_additions")
    private List<String> activitiesAdditions;

  }

  /**
   * 结算银行账户
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class BankAccountInfo implements Serializable {
    private static final long serialVersionUID = -5853122395888860086L;

    /**
     * 账户类型
     */
    @SerializedName("bank_account_type")
    private BankAccountTypeEnum bankAccountType;

    /**
     * 开户名称
     */
    @SerializedName("account_name")
    @SpecEncrypt
    private String accountName;

    /**
     * 开户银行
     */
    @SerializedName("account_bank")
    private String accountBank;

    /**
     * 开户银行省市编码
     */
    @SerializedName("bank_address_code")
    private String bankAddressCode;

    /**
     * 开户银行联行号
     */
    @SerializedName("bank_branch_id")
    private String bankBranchId;

    /**
     * 开户银行全称（含支行]
     */
    @SerializedName("bank_name")
    private String bankName;

    /**
     * 银行账号
     */
    @SerializedName("account_number")
    @SpecEncrypt
    private String accountNumber;

  }


  /**
   * 补充材料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class AdditionInfo implements Serializable {
    private static final long serialVersionUID = -7526912529114022379L;

    /**
     * 法人开户承诺函
     */
    @SerializedName("legal_person_commitment")
    private String legalPersonCommitment;

    /**
     * 法人开户意愿视频
     */
    @SerializedName("legal_person_video")
    private String legalPersonVideo;

    /**
     * 补充材料
     */
    @SerializedName("business_addition_pics")
    private List<String> businessAdditionPics;

    /**
     * 补充说明
     */
    @SerializedName("business_addition_msg")
    private String businessAdditionMsg;

  }

}
