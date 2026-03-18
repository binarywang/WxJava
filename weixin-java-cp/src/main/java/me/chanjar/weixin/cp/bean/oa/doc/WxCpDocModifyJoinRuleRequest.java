package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 修改文档查看规则请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocModifyJoinRuleRequest implements Serializable {
  private static final long serialVersionUID = -3598982127333701683L;

  @SerializedName("docid")
  private String docId;

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

  @SerializedName("update_co_auth_list")
  private Boolean updateCoAuthList;

  @SerializedName("co_auth_list")
  private List<WxCpDocAuthInfo.CoAuthInfo> coAuthList;

  public static WxCpDocModifyJoinRuleRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocModifyJoinRuleRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
