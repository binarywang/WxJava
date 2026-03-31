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
 * 修改文档通知范围及权限请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocModifyMemberRequest implements Serializable {
  private static final long serialVersionUID = 8704942578874336412L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("update_file_member_list")
  private List<WxCpDocAuthInfo.DocMember> updateFileMemberList;

  @SerializedName("del_file_member_list")
  private List<WxCpDocAuthInfo.DocMember> delFileMemberList;

  public static WxCpDocModifyMemberRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocModifyMemberRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
