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
 * 获取智能表格内容权限请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocSmartSheetAuthRequest implements Serializable {
  private static final long serialVersionUID = -7549610873100253102L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("sheet_id")
  private String sheetId;

  /**
   * 透传扩展参数，便于兼容字段权限/记录权限等筛选条件。
   */
  private transient JsonObject extra;

  public static WxCpDocSmartSheetAuthRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocSmartSheetAuthRequest.class);
  }

  public String toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", this.docId);
    if (this.sheetId != null) {
      jsonObject.addProperty("sheet_id", this.sheetId);
    }
    if (this.extra != null) {
      for (Map.Entry<String, JsonElement> entry : this.extra.entrySet()) {
        jsonObject.add(entry.getKey(), entry.getValue());
      }
    }
    return WxCpGsonBuilder.create().toJson(jsonObject);
  }
}
