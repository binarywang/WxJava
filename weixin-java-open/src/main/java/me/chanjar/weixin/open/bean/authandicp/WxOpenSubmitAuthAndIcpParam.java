package me.chanjar.weixin.open.bean.authandicp;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenMaIcpService;

import java.io.Serializable;
import java.util.List;

/**
 * @author 痴货
 * @Description
 * @createTime 2025/06/18 23:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxOpenSubmitAuthAndIcpParam implements Serializable {

  private static final long serialVersionUID = 5545621231231213158L;

  /**
   * 认证数据
   */
  @SerializedName("auth_data")
  private AuthData authData;

  /**
   * 备案主体信息
   */
  @SerializedName("icp_subject")
  private IcpSubject icpSubject;

  /**
   * 微信小程序信息
   */
  @SerializedName("icp_applets")
  private IcpApplets icpApplets;

  /**
   * 其他备案媒体材料
   */
  @SerializedName("icp_materials")
  private IcpMaterials icpMaterials;


  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AuthData implements Serializable {

    private static final long serialVersionUID = 5545289494161613158L;

    /**
     * 联系人信息
     */
    @SerializedName("contact_info")
    private ContactInfo contactInfo;

    /**
     * 发票信息，如果是服务商代缴模式，不需要填写
     */
    @SerializedName("invoice_info")
    private InvoiceInfo invoiceInfo;

    /**
     * 认证主体类型：1.企业；12.个体工商户；15.个人
     */
    @SerializedName("customer_type")
    private Integer customerType;

    /**
     * 支付方式 1：消耗服务商预购包 2：小程序开发者自行支付
     */
    @SerializedName("pay_type")
    private Integer payType;

    /**
     * 主体资质其他证明材料，最多上传10张图片
     */
    @SerializedName("qualification_other")
    private List<String> qualificationOther;

    /**
     * 小程序账号名称
     */
    @SerializedName("account_name")
    private String accountName;

    /**
     * 小程序账号名称命名类型 1：基于自选词汇命名 2：基于商标命名
     */
    @SerializedName("account_name_type")
    private String accountNameType;

    /**
     * 名称命中关键词-补充材料，支持上传多张图片
     */
    @SerializedName("account_supplemental")
    private List<String> accountSupplemental;

    /**
     * 认证类型为个人类型时可以选择要认证的身份，从 查询个人认证身份选项列表 里获取，填叶节点的name
     */
    @SerializedName("auth_identification")
    private String authIdentification;

    /**
     * 填了 auth_identification 则必填。身份证明材料 （1）基于不同认证身份上传不同的材料；（2）认证类型=1时选填，支持上传10张图片
     */
    @SerializedName("auth_ident_material")
    private List<String> authIdentMaterial;

    /**
     * 第三方联系电话
     */
    @SerializedName("third_party_phone")
    private String thirdPartyPhone;

    /**
     * 选择服务商代缴模式时必填。服务市场 appid，该服务市场账号主体必须与服务商账号主体一致
     */
    @SerializedName("service_appid")
    private String serviceAppid;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ContactInfo implements Serializable {

    private static final long serialVersionUID = -2962862643438222305L;

    /**
     * 认证联系人姓名
     */
    @SerializedName("name")
    private String name;

    /**
     * 认证联系人邮箱
     */
    @SerializedName("email")
    private String email;

    /**
     * 认证联系人手机号
     */
    @SerializedName("mobile")
    private String mobile;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class InvoiceInfo implements Serializable {

    private static final long serialVersionUID = 4564894651613131322L;

    /**
     * 发票类型 1: 不开发票 2: 电子发票 4: 增值税专票（数电类型）
     */
    @SerializedName("invoice_type")
    private String invoiceType;

    /**
     * 发票类型=2时必填 电子发票开票信息
     */
    @SerializedName("electronic")
    private Electronic electronic;

    /**
     * 发票类型=4时必填 增值税专票（数电类型）开票信息
     */
    @SerializedName("vat")
    private Vat vat;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Electronic implements Serializable {

    private static final long serialVersionUID = 189498465135131444L;

    /**
     * 纳税识别号（15位、17、18或20位）
     */
    @SerializedName("id")
    private String id;

    /**
     * 发票备注（选填）
     */
    @SerializedName("desc")
    private String desc;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Vat implements Serializable {

    private static final long serialVersionUID = 829892829816551512L;

    /**
     * 企业电话
     */
    @SerializedName("enterprise_phone")
    private String enterprisePhone;

    /**
     * 纳税识别号（15位、17、18或20位）
     */
    @SerializedName("id")
    private String id;

    /**
     * 企业注册地址
     */
    @SerializedName("enterprise_address")
    private String enterpriseAddress;

    /**
     * 企业开户银行（选填）
     */
    @SerializedName("bank_name")
    private String bankName;

    /**
     * 企业银行账号（选填）
     */
    @SerializedName("bank_account")
    private String bankAccount;

    /**
     * 发票备注（选填）
     */
    @SerializedName("desc")
    private String desc;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class IcpSubject implements Serializable {

    private static final long serialVersionUID = -1256165165165165165L;

    /**
     * 主体基本信息
     */
    @SerializedName("base_info")
    private SubjectBaseInfo baseInfo;

    /**
     * 个人主体额外信息
     */
    @SerializedName("personal_info")
    private SubjectPersonalInfo personalInfo;

    /**
     * 主体额外信息（个人备案时，如果存在与主体负责人信息相同的字段，则填入相同的值）
     */
    @SerializedName("organize_info")
    private SubjectOrganizeInfo organizeInfo;

    /**
     * 主体负责人信息
     */
    @SerializedName("principal_info")
    private SubjectPrincipalInfo principalInfo;

    /**
     * 法人信息（非个人备案，且主体负责人不是法人时，必填）
     */
    @SerializedName("legal_person_info")
    private SubjectLegalPersonInfo legalPersonInfo;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectBaseInfo implements Serializable {

    private static final long serialVersionUID = -1561561613212313445L;

    /**
     * 主体性质，选项参考 获取单位性质，**仅支持企业、个体工商户、个人**
     * {@link WxOpenMaIcpService#queryIcpSubjectTypes }
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 主办单位名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 备案省份，使用省份代码
     */
    @SerializedName("province")
    private String province;

    /**
     * 备案城市，使用城市代码
     */
    @SerializedName("city")
    private String city;

    /**
     * 备案县区，使用县区代码
     */
    @SerializedName("district")
    private String district;

    /**
     * 通讯地址，必须属于备案省市区，地址开头的省市区不用填入
     */
    @SerializedName("address")
    private String address;

    /**
     * 主体信息备注，根据需要，如实填写
     */
    @SerializedName("comment")
    private String comment;

    /**
     * 主体备案号
     */
    @SerializedName("record_number")
    private String recordNumber;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectPersonalInfo implements Serializable {

    private static final long serialVersionUID = -2151981561916519288L;

    /**
     * 临时居住证明照片 media_id，个人备案且非本省人员，需要提供居住证、暂住证、社保证明、房产证等临时居住证明
     */
    @SerializedName("residence_permit")
    private String residencePermit;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectOrganizeInfo implements Serializable {

    private static final long serialVersionUID = -1181121318132185161L;

    /**
     * 主体证件类型
     * {@link WxOpenMaIcpService#queryIcpCertificateTypes}
     */
    @SerializedName("certificate_type")
    private Integer certificateType;

    /**
     * 主体证件号码
     */
    @SerializedName("certificate_number")
    private String certificateNumber;

    /**
     * 主体证件住所
     */
    @SerializedName("certificate_address")
    private String certificateAddress;

    /**
     * 主体证件照片 media_id，如果小程序主体为非个人类型
     */
    @SerializedName("certificate_photo")
    private String certificatePhoto;

  }
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectPrincipalInfo implements Serializable {

    private static final long serialVersionUID = -2984191321918916511L;

    /**
     * 负责人姓名
     */
    @SerializedName("name")
    private String name;

    /**
     * 负责人联系方式
     */
    @SerializedName("mobile")
    private String mobile;

    /**
     * 负责人电子邮件
     */
    @SerializedName("email")
    private String email;

    /**
     * 负责人应急联系方式
     */
    @SerializedName("emergency_contact")
    private String emergencyContact;

    /**
     * 负责人证件类型(获取证件类型
     * {@link WxOpenMaIcpService#queryIcpCertificateTypes()})
     */
    @SerializedName("certificate_type")
    private Integer certificateType;

    /**
     * 负责人证件号码
     */
    @SerializedName("certificate_number")
    private String certificateNumber;

    /**
     * 负责人证件有效期起始日期，格式为 YYYYmmdd
     */
    @SerializedName("certificate_validity_date_start")
    private String certificateValidityDateStart;

    /**
     * 负责人证件有效期终止日期，格式为 YYYYmmdd
     */
    @SerializedName("certificate_validity_date_end")
    private String certificateValidityDateEnd;

    /**
     * 负责人证件正面照片 media_id（身份证为人像面）
     */
    @SerializedName("certificate_photo_front")
    private String certificatePhotoFront;

    /**
     * 负责人证件背面照片 media_id（身份证为国徽面）
     */
    @SerializedName("certificate_photo_back")
    private String certificatePhotoBack;

    /**
     * 授权书 media_id，当主体负责人不是法人时需要主体负责人授权书，当小程序负责人不是法人时需要小程序负责人授权书
     */
    @SerializedName("authorization_letter")
    private String authorizationLetter;

    /**
     * 扫脸认证任务id(扫脸认证接口返回的task_id)，仅小程序负责人需要扫脸，主体负责人无需扫脸
     */
    @SerializedName("verify_task_id")
    private String verifyTaskId;

  }
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectLegalPersonInfo implements Serializable {

    private static final long serialVersionUID = -1259198165316516161L;

    /**
     * 法人代表姓名
     */
    @SerializedName("name")
    private String name;

    /**
     * 法人证件号码
     */
    @SerializedName("certificate_number")
    private String certificateNumber;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class IcpApplets implements Serializable {

    private static final long serialVersionUID = -2156129841651651651L;

    /**
     * 微信小程序基本信息
     */
    @SerializedName("base_info")
    private AppletsBaseInfo baseInfo;

    /**
     * 小程序负责人信息
     */
    @SerializedName("principal_info")
    private AppletsPrincipalInfo principalInfo;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AppletsBaseInfo implements Serializable {

    private static final long serialVersionUID = 8404017028547715919L;

    /**
     * 小程序ID，不用填写，后台自动拉取
     */
    @SerializedName("appid")
    private String appId;

    /**
     * 小程序名称，不用填写，后台自动拉取
     */
    @SerializedName("name")
    private String name;

    /**
     * 小程序服务内容类型，只能填写二级服务内容类型，最多5个
     * {@link WxOpenMaIcpService#queryIcpServiceContentTypes}
     */
    @SerializedName("service_content_types")
    private List<Integer> serviceContentTypes;

    /**
     * 前置审批项，列表中不能存在重复的前置审批类型id，如不涉及前置审批项，也需要填“以上都不涉及”
     */
    @SerializedName("nrlx_details")
    private List<AppletsNrlxDetailItem> nrlxDetails;

    /**
     * 请具体描述小程序实际经营内容、主要服务内容，该信息为主管部门审核重要依据，备注内容字数限制20-200字，请认真填写。
     */
    @SerializedName("comment")
    private String comment;

    /**
     * 小程序备案号，示例值：粤B2-20090059-1626X
     * （申请小程序备案时不用填写，查询已备案详情时会返回）
     */
    @SerializedName("record_number")
    private String recordNumber;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AppletsNrlxDetailItem implements Serializable {

    private static final long serialVersionUID = -9144721738792167000L;

    /**
     * 前置审批类型
     * {@link WxOpenMaIcpService#queryIcpNrlxTypes}
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 前置审批号，如果前置审批类型不是“以上都不涉及”，
     * 则必填，示例值："粤-12345号
     */
    @SerializedName("code")
    private String code;

    /**
     * 前置审批媒体材料 media_id
     */
    @SerializedName("media")
    private String media;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AppletsPrincipalInfo implements Serializable {

    private static final long serialVersionUID = 5088256283066784463L;

    /**
     * 负责人姓名
     */
    @SerializedName("name")
    private String name;

    /**
     * 负责人联系方式
     */
    @SerializedName("mobile")
    private String mobile;

    /**
     * 负责人电子邮件
     */
    @SerializedName("email")
    private String email;

    /**
     * 负责人应急联系方式
     */
    @SerializedName("emergency_contact")
    private String emergencyContact;

    /**
     * 负责人证件类型
     * {@link WxOpenMaIcpService#queryIcpCertificateTypes}
     */
    @SerializedName("certificate_type")
    private Integer certificateType;

    /**
     * 负责人证件号码
     */
    @SerializedName("certificate_number")
    private String certificateNumber;

    /**
     * 负责人证件有效期起始日期，
     * 格式为 YYYYmmdd，示例值："20230815"
     */
    @SerializedName("certificate_validity_date_start")
    private String certificateValidityDateStart;

    /**
     * 负责人证件有效期终止日期，
     * 格式为 YYYYmmdd，
     * 如证件长期有效，请填写 "长期"，示例值："20330815"
     */
    @SerializedName("certificate_validity_date_end")
    private String certificateValidityDateEnd;

    /**
     * 负责人证件正面照片 media_id
     * （身份证为人像面）
     */
    @SerializedName("certificate_photo_front")
    private String certificatePhotoFront;

    /**
     * 负责人证件背面照片 media_id
     * （身份证为国徽面）
     */
    @SerializedName("certificate_photo_back")
    private String certificatePhotoBack;

    /**
     * 授权书 media_id，
     * 当主体负责人不是法人时需要主体负责人授权书，
     * 当小程序负责人不是法人时需要小程序负责人授权书
     */
    @SerializedName("authorization_letter")
    private String authorizationLetter;

    /**
     * 扫脸认证任务id(扫脸认证接口返回的task_id)，
     * 仅小程序负责人需要扫脸，主体负责人无需扫脸
     */
    @SerializedName("verify_task_id")
    private String verifyTaskId;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class IcpMaterials implements Serializable {

    private static final long serialVersionUID = -2651654844198165191L;

    /**
     * 互联网信息服务承诺书 media_id，最多上传1个
     */
    @SerializedName("commitment_letter")
    private List<String> commitmentLetter;

    /**
     * 主体更名函 media_id(非个人类型，且发生过更名时需要上传)，最多上传1个
     */
    @SerializedName("business_name_change_letter")
    private List<String> businessNameChangeLetter;

    /**
     * 党建确认函 media_id，最多上传1个
     */
    @SerializedName("party_building_confirmation_letter")
    private List<String> partyBuildingConfirmationLetter;

    /**
     * 承诺视频 media_id，最多上传1个
     */
    @SerializedName("promise_video")
    private List<String> promiseVideo;

    /**
     * 网站备案信息真实性责任告知书 media_id，最多上传1个
     */
    @SerializedName("authenticity_responsibility_letter")
    private List<String> authenticityResponsibilityLetter;

    /**
     * 小程序备案信息真实性承诺书 media_id，最多上传1个
     */
    @SerializedName("authenticity_commitment_letter")
    private List<String> authenticityCommitmentLetter;

    /**
     * 小程序建设方案书 media_id，最多上传1个
     */
    @SerializedName("website_construction_proposal")
    private List<String> websiteConstructionProposal;

    /**
     * 主体其它附件 media_id，最多上传10个
     */
    @SerializedName("subject_other_materials")
    private List<String> subjectOtherMaterials;

    /**
     * 小程序其它附件 media_id，最多上传10个
     */
    @SerializedName("applets_other_materials")
    private List<String> appletsOtherMaterials;

    /**
     * 手持证件照 media_id，最多上传1个
     */
    @SerializedName("holding_certificate_photo")
    private List<String> holdingCertificatePhoto;

  }

}
