package com.github.binarywang.wxpay.bean.brandapplyment;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 品牌入驻申请资料；图片资料填写预先上传的 MediaID。 */
@Data
public class BrandApplymentRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 服务商自定义业务申请编号，同编号可修改被驳回的申请。 */
  @SerializedName("business_code")
  private String businessCode;
  /** 品牌管理员信息。 */
  @SpecEncrypt
  @SerializedName("admin_info")
  private AdminInfo adminInfo;
  /** 所属企业主体信息。 */
  @SerializedName("subject_info")
  private SubjectInfo subjectInfo;
  /** 品牌基础信息。 */
  @SerializedName("brand_basic_info")
  private BrandBasicInfo brandBasicInfo;
  /** 品牌商标资料。 */
  @SerializedName("trademark")
  private Trademark trademark;

  /** 品牌管理员信息。 */
  @Data
  public static class AdminInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 管理员姓名。 */
    @SpecEncrypt
    @SerializedName("admin_name")
    private String adminName;

    /** 管理员证件类型。 */
    @SerializedName("id_doc_type")
    private String idDocType;

    /** 管理员证件号。 */
    @SpecEncrypt
    @SerializedName("id_card_number")
    private String idCardNumber;
  }

  /** 企业主体信息。 */
  @Data
  public static class SubjectInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主体类型：企业或个体工商户。 */
    @SerializedName("subject_type")
    private String subjectType;

    /** 主体名称。 */
    @SerializedName("subject_name")
    private String subjectName;

    /** 统一社会信用代码。 */
    @SerializedName("unified_social_credit_code")
    private String unifiedSocialCreditCode;
  }

  /** 品牌基础信息。 */
  @Data
  public static class BrandBasicInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 品牌名称。 */
    @SerializedName("brand_name")
    private String brandName;

    /** 品牌 Logo 图片 MediaID。 */
    @SerializedName("brand_logo")
    private String brandLogo;
  }

  /** 品牌商标资料。 */
  @Data
  public static class Trademark implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 有无商标：TRADEMARK_EXISTS 或 TRADEMARK_NONE。 */
    @SerializedName("trademark_exists")
    private String trademarkExists;

    /** 商标注册证信息。 */
    @SerializedName("trademark_registration_certificate")
    private TrademarkRegistrationCertificate trademarkRegistrationCertificate;

    /** Logo 商标注册证信息。 */
    @SerializedName("logo_trademark_registration_certificate")
    private TrademarkRegistrationCertificate logoTrademarkRegistrationCertificate;

    /** 无商标额外证明材料 MediaID，存量服务商字段，不可与列表同时填写。 */
    @SerializedName("no_trademark_addition_prove")
    private String noTrademarkAdditionProve;

    /** 无商标额外证明材料 MediaID 列表，1 至 5 张。 */
    @SerializedName("no_trademark_addition_prove_list")
    private List<String> noTrademarkAdditionProveList;
  }

  /** 商标或 Logo 商标注册证信息。 */
  @Data
  public static class TrademarkRegistrationCertificate implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 商标注册证 MediaID，存量服务商字段，不可与列表同时填写。 */
    @SerializedName("certificate")
    private String certificate;

    /** 商标名称。 */
    @SerializedName("name")
    private String name;

    /** 商标注册号。 */
    @SerializedName("number")
    private String number;

    /** 商标有效期开始日期 YYYY-MM-DD，存量服务商字段。 */
    @SerializedName("valid_begin_time")
    private String validBeginTime;

    /** 商标有效期结束日期 YYYY-MM-DD。 */
    @SerializedName("valid_end_time")
    private String validEndTime;

    /** 商标国际分类，1 至 45。 */
    @SerializedName("international_class")
    private String internationalClass;

    /** 商标注册人。 */
    @SerializedName("holder")
    private String holder;

    /** 商标许可使用授权书 MediaID，存量服务商字段，不可与列表同时填写。 */
    @SerializedName("license")
    private String license;

    /** 授权有效期开始日期 YYYY-MM-DD，存量服务商字段。 */
    @SerializedName("authorization_begin_time")
    private String authorizationBeginTime;

    /** 授权有效期结束日期 YYYY-MM-DD，不得超过商标有效期。 */
    @SerializedName("authorization_end_time")
    private String authorizationEndTime;

    /** 商标注册证 MediaID 列表，1 至 5 张。 */
    @SerializedName("certificate_list")
    private List<String> certificateList;

    /** 许可使用授权书 MediaID 列表，1 至 5 张。 */
    @SerializedName("license_list")
    private List<String> licenseList;
  }
}
