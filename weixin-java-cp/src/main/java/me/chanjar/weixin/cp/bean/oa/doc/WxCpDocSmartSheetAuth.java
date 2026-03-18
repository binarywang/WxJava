package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 智能表格内容权限信息.
 */
@Data
public class WxCpDocSmartSheetAuth extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 6369842878363076286L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("sheet_id")
  private String sheetId;

  /**
   * 通用内容权限结构，兼容字段/记录权限等多种命名。
   */
  @SerializedName("auth_info")
  private JsonElement authInfo;

  @SerializedName("field_auth")
  private JsonElement fieldAuth;

  @SerializedName("record_auth")
  private JsonElement recordAuth;

  public JsonElement getEffectiveAuthInfo() {
    if (this.authInfo != null) {
      return this.authInfo;
    }
    if (this.fieldAuth != null) {
      return this.fieldAuth;
    }
    return this.recordAuth;
  }

  public static WxCpDocSmartSheetAuth fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocSmartSheetAuth.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
