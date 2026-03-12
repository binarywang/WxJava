package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 文档高级功能账号列表.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpDocAdminListResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 6293762486917512845L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("admin_list")
  private List<Admin> adminList;

  public static WxCpDocAdminListResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocAdminListResult.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Getter
  @Setter
  public static class Admin implements Serializable {
    private static final long serialVersionUID = -4984807259145367427L;

    @SerializedName("userid")
    private String userId;

    @SerializedName("open_userid")
    private String openUserId;

    @SerializedName("type")
    private Integer type;
  }
}
