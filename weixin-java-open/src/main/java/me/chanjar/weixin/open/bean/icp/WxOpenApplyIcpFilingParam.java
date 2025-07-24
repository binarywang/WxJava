package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author xzh
 * @Description
 * @createTime 2024/08/14 15:09
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WxOpenApplyIcpFilingParam implements Serializable {

  private static final long serialVersionUID = -1175687030580685304L;

  /**
   * 备案主体信息
   */
  @SerializedName("icp_subject")
  private Subject icpSubject;

  /**
   * 微信小程序信息
   */
  @SerializedName("icp_applets")
  private Applets icpApplets;

  /**
   * 其他备案媒体材料
   */
  @SerializedName("icp_materials")
  private Materials icpMaterials;

  //region Subject define

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Subject implements Serializable {

    private static final long serialVersionUID = -3760060095514905158L;

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
    private SubjectLegaPersonInfo legalPersonInfo;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectBaseInfo implements Serializable {
    private static final long serialVersionUID = 6040247507213564709L;

    /**
     * 主体性质，示例值：5
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 主办单位名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 备案省份
     * 使用省份代码，示例值："110000"(参考：获取区域信息接口)
     */
    @SerializedName("province")
    private String province;

    /**
     * 备案城市
     * 使用城市代码，示例值："110100"(参考：获取区域信息接口)
     */
    @SerializedName("city")
    private String city;

    /**
     * 备案县区
     * 使用县区代码，示例值："110105"(参考：获取区域信息接口)
     */
    @SerializedName("district")
    private String district;

    /**
     * 通讯地址，必须属于备案省市区，地址开头的省市区不用填入，
     * 例如：通信地址为“北京市朝阳区高碑店路181号1栋12345室”时，
     * 只需要填写 "高碑店路181号1栋12345室" 即可
     */
    @SerializedName("address")
    private String address;

    /**
     * 主体信息备注，根据需要，如实填写
     */
    @SerializedName("comment")
    private String comment;

    /**
     * 主体备案号，示例值：粤B2-20090059（申请小程序备案时不用填写，查询已备案详情时会返回）
     */
    @SerializedName("record_number")
    private String recordNumber;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectPersonalInfo implements Serializable {
    private static final long serialVersionUID = 2453569107311102079L;

    /**
     * 临时居住证明照片 media_id，个人备案且非本省人员，需要提供居住证、暂住证、社保证明、房产证等临时居住证明，
     */
    @SerializedName("residence_permit")
    private String residencePermit;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectOrganizeInfo implements Serializable {
    private static final long serialVersionUID = 562578565445293345L;

    /**
     * 主体证件类型，
     * 示例值：2(参考：获取证件类型接口)
     */
    @SerializedName("certificate_type")
    private Integer certificateType;

    /**
     * 主体证件号码，
     * 示例值："110105199001011234"
     */
    @SerializedName("certificate_number")
    private String certificateNumber;

    /**
     * 主体证件住所，
     * 示例值："北京市朝阳区高碑店路181号1栋12345室"
     */
    @SerializedName("certificate_address")
    private String certificateAddress;

    /**
     * 主体证件照片 media_id，
     * 如果小程序主体为非个人类型，则必填
     */
    @SerializedName("certificate_photo")
    private String certificatePhoto;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectPrincipalInfo implements Serializable {

    private static final long serialVersionUID = -6840245946309353916L;

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
     */
    @SerializedName("certificate_type")
    private Integer certificateType;

    /**
     * 负责人证件号码
     */
    @SerializedName("certificate_number")
    private String certificateNumber;

    /**
     * 负责人证件有效期起始日期
     * 格式为 YYYYmmdd，示例值："20230815"
     */
    @SerializedName("certificate_validity_date_start")
    private String certificateValidityDateStart;

    /**
     * 负责人证件有效期终止日期
     * 格式为 YYYYmmdd，如证件长期有效，请填写 "长期"，示例值："20330815"
     */
    @SerializedName("certificate_validity_date_end")
    private String certificateValidityDateEnd;

    /**
     * 负责人证件正面照片 media_id（身份证为人像面）
     */
    @SerializedName("certificate_photo_front")
    private String certificatePhotoFront;

    /**
     * 负责人证件背面照片 media_id
     */
    @SerializedName("certificate_photo_back")
    private String certificatePhotoBack;

    /**
     * 授权书 media_id，当主体负责人不是法人时需要主体负责人授权书，
     * 当小程序负责人不是法人时需要小程序负责人授权书
     */
    @SerializedName("authorization_letter")
    private String authorizationLetter;

    /**
     * 扫脸认证任务id(扫脸认证接口返回的task_id)，
     * 仅小程序负责人需要扫脸，主体负责人无需扫脸，
     */
    @SerializedName("verify_task_id")
    private String verifyTaskId;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubjectLegaPersonInfo implements Serializable {

    private static final long serialVersionUID = -7386716346559073571L;

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

  //endregion Subject define

  //region Applets define

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Applets implements Serializable {

    private static final long serialVersionUID = -2938469180388648595L;

    /**
     * 微信小程序基本信息
     */
    @SerializedName("base_info")
    private AppletsBaseInfo basInfo;

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
     * 前置审批类型，示例值：2
     * (参考：获取前置审批项接口)
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
     * 负责人证件类型，示例值：2(参考：获取证件类型接口，此处只能填入单位性质属于个人的证件类型)
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
  //endregion Applets define

  //region Materials define
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Materials {

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
  //endregion Materials define
}
