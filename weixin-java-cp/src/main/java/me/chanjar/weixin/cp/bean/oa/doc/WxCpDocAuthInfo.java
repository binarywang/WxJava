package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 文档权限信息.
 */
@Data
public class WxCpDocAuthInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 7364193025307378330L;

  @SerializedName("access_rule")
  private AccessRule accessRule;

  @SerializedName("secure_setting")
  private SecureSetting secureSetting;

  @SerializedName("doc_member_list")
  private List<DocMember> docMemberList;

  @SerializedName("co_auth_list")
  private List<CoAuthInfo> coAuthList;

  public static WxCpDocAuthInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocAuthInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Getter
  @Setter
  public static class AccessRule implements Serializable {
    private static final long serialVersionUID = -3654043617097778828L;

    @SerializedName("enable_corp_internal")
    private Boolean enableCorpInternal;

    @SerializedName("corp_internal_auth")
    private Integer corpInternalAuth;

    @SerializedName("enable_corp_external")
    private Boolean enableCorpExternal;

    @SerializedName("corp_external_auth")
    private Integer corpExternalAuth;

    @SerializedName("corp_internal_approve_only_by_admin")
    private Boolean corpInternalApproveOnlyByAdmin;

    @SerializedName("corp_external_approve_only_by_admin")
    private Boolean corpExternalApproveOnlyByAdmin;

    @SerializedName("ban_share_external")
    private Boolean banShareExternal;
  }

  @Getter
  @Setter
  public static class SecureSetting implements Serializable {
    private static final long serialVersionUID = -8549373110845211623L;

    @SerializedName("enable_readonly_copy")
    private Boolean enableReadonlyCopy;

    @SerializedName("enable_readonly_comment")
    private Boolean enableReadonlyComment;

    @SerializedName("watermark")
    private Watermark watermark;
  }

  @Getter
  @Setter
  public static class Watermark implements Serializable {
    private static final long serialVersionUID = 4438638988412283788L;

    @SerializedName("margin_type")
    private Integer marginType;

    @SerializedName("show_visitor_name")
    private Boolean showVisitorName;

    @SerializedName("show_text")
    private Boolean showText;

    @SerializedName("text")
    private String text;
  }

  @Getter
  @Setter
  public static class DocMember implements Serializable {
    private static final long serialVersionUID = 222350682486320400L;

    @SerializedName("type")
    private Integer type;

    @SerializedName("userid")
    private String userId;

    @SerializedName("tmp_external_userid")
    private String tmpExternalUserId;

    @SerializedName("auth")
    private Integer auth;
  }

  @Getter
  @Setter
  public static class CoAuthInfo implements Serializable {
    private static final long serialVersionUID = -2726812527126666002L;

    @SerializedName("type")
    private Integer type;

    @SerializedName("departmentid")
    private Long departmentId;

    @SerializedName("auth")
    private Integer auth;
  }
}
