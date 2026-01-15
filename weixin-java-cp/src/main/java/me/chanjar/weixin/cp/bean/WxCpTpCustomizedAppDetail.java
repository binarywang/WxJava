package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 代开发应用详情.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2026-01-14
 */
@Data
public class WxCpTpCustomizedAppDetail extends WxCpBaseResp {

  /**
   * 授权方企业id
   */
  @SerializedName("auth_corpid")
  private String authCorpId;

  /**
   * 授权方企业名称
   */
  @SerializedName("auth_corp_name")
  private String authCorpName;

  /**
   * 授权方企业方形头像
   */
  @SerializedName("auth_corp_square_logo_url")
  private String authCorpSquareLogoUrl;

  /**
   * 授权方企业圆形头像
   */
  @SerializedName("auth_corp_round_logo_url")
  private String authCorpRoundLogoUrl;

  /**
   * 授权方企业类型，1. 企业; 2. 政府以及事业单位; 3. 其他组织, 4.团队小型企业（原企业微信认证版用户）
   */
  @SerializedName("auth_corp_type")
  private Integer authCorpType;

  /**
   * 授权方企业在微工作台（原企业号）的二维码，可用于关注微工作台
   */
  @SerializedName("auth_corp_qrcode_url")
  private String authCorpQrcodeUrl;

  /**
   * 授权方企业用户规模
   */
  @SerializedName("auth_corp_user_limit")
  private Integer authCorpUserLimit;

  /**
   * 授权方企业的主体名称(仅认证或验证过的企业有)，即企业全称
   */
  @SerializedName("auth_corp_full_name")
  private String authCorpFullName;

  /**
   * 企业类型，1. 已验证企业；2. 已认证企业
   */
  @SerializedName("auth_corp_verified_type")
  private Integer authCorpVerifiedType;

  /**
   * 授权方企业所属行业
   */
  @SerializedName("auth_corp_industry")
  private String authCorpIndustry;

  /**
   * 授权方企业所属子行业
   */
  @SerializedName("auth_corp_sub_industry")
  private String authCorpSubIndustry;

  /**
   * 授权方企业所在地址
   */
  @SerializedName("auth_corp_location")
  private String authCorpLocation;

  /**
   * 代开发自建应用详情
   */
  @SerializedName("customized_app_list")
  private List<CustomizedApp> customizedAppList;

  /**
   * From json wx cp tp customized app detail.
   *
   * @param json the json
   * @return the wx cp tp customized app detail
   */
  public static WxCpTpCustomizedAppDetail fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpCustomizedAppDetail.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * 代开发自建应用信息
   */
  @Data
  public static class CustomizedApp implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 代开发自建应用的agentid
     */
    @SerializedName("agentid")
    private Integer agentId;

    /**
     * 代开发自建应用对应的模板id
     */
    @SerializedName("template_id")
    private String templateId;

    /**
     * 代开发自建应用的name
     */
    @SerializedName("name")
    private String name;

    /**
     * 代开发自建应用的description
     */
    @SerializedName("description")
    private String description;

    /**
     * 代开发自建应用的logo url
     */
    @SerializedName("logo_url")
    private String logoUrl;

    /**
     * 代开发自建应用的可见范围
     */
    @SerializedName("allow_userinfos")
    private AllowUserInfos allowUserInfos;

    /**
     * 代开发自建应用是否被禁用
     */
    @SerializedName("close")
    private Integer close;

    /**
     * 代开发自建应用主页url
     */
    @SerializedName("home_url")
    private String homeUrl;

    /**
     * 代开发自建应用的模式，0 = 代开发自建应用；1 = 第三方应用代开发
     */
    @SerializedName("app_type")
    private Integer appType;
  }

  /**
   * 应用可见范围
   */
  @Data
  public static class AllowUserInfos implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 应用可见范围（成员）
     */
    @SerializedName("user")
    private List<User> users;

    /**
     * 应用可见范围（部门）
     */
    @SerializedName("department")
    private List<Department> departments;
  }

  /**
   * 成员信息
   */
  @Data
  public static class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成员userid
     */
    @SerializedName("userid")
    private String userId;
  }

  /**
   * 部门信息
   */
  @Data
  public static class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @SerializedName("id")
    private Integer id;
  }
}
