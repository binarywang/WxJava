package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * 修改智能表格内容权限请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocSmartSheetModifyAuthRequest implements Serializable {
  private static final long serialVersionUID = 1603733018038054224L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("sheet_id")
  private String sheetId;

  /**
   * 字段级/记录级权限载荷。
   */
  @SerializedName("auth_info")
  private JsonElement authInfo;

  /**
   * 透传扩展参数。
   */
  private transient JsonObject extra;

  public static WxCpDocSmartSheetModifyAuthRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocSmartSheetModifyAuthRequest.class);
  }

  public String toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", this.docId);
    if (this.sheetId != null) {
      jsonObject.addProperty("sheet_id", this.sheetId);
    }
    if (this.authInfo != null) {
      jsonObject.add("auth_info", this.authInfo);
    }
    if (this.extra != null) {
      for (Map.Entry<String, JsonElement> entry : this.extra.entrySet()) {
        jsonObject.add(entry.getKey(), entry.getValue());
      }
    }
    return WxCpGsonBuilder.create().toJson(jsonObject);
  }
}
