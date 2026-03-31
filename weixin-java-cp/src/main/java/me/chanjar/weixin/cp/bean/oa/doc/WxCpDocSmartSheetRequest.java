package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.JsonArray;
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
 * 智能表格通用请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocSmartSheetRequest implements Serializable {
  private static final long serialVersionUID = -2713485192832296951L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("sheet_id")
  private String sheetId;

  @SerializedName("view_id")
  private String viewId;

  /**
   * 透传扩展参数，便于兼容 properties、views、fields、records 等结构。
   */
  private transient JsonObject extra;

  public static WxCpDocSmartSheetRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocSmartSheetRequest.class);
  }

  public String toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", this.docId);
    if (this.sheetId != null) {
      jsonObject.addProperty("sheet_id", this.sheetId);
    }
    if (this.viewId != null) {
      jsonObject.addProperty("view_id", this.viewId);
    }
    if (this.extra != null) {
      for (Map.Entry<String, JsonElement> entry : this.extra.entrySet()) {
        jsonObject.add(entry.getKey(), entry.getValue());
      }
    }
    return WxCpGsonBuilder.create().toJson(jsonObject);
  }

  public WxCpDocSmartSheetRequest addExtra(String key, String value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocSmartSheetRequest addExtra(String key, Number value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocSmartSheetRequest addExtra(String key, Boolean value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocSmartSheetRequest addExtra(String key, JsonElement value) {
    ensureExtra().add(key, value);
    return this;
  }

  public WxCpDocSmartSheetRequest addExtraArrayItem(String key, JsonElement value) {
    JsonArray jsonArray = this.extra != null && this.extra.has(key) && this.extra.get(key).isJsonArray()
      ? this.extra.getAsJsonArray(key)
      : new JsonArray();
    jsonArray.add(value);
    ensureExtra().add(key, jsonArray);
    return this;
  }

  private JsonObject ensureExtra() {
    if (this.extra == null) {
      this.extra = new JsonObject();
    }
    return this.extra;
  }
}
