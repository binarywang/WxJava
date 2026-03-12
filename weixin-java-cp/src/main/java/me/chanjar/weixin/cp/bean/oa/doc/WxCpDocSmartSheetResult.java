package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 智能表格通用响应.
 */
@Data
public class WxCpDocSmartSheetResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 1409304699132416644L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("sheet_id")
  private String sheetId;

  @SerializedName("view_id")
  private String viewId;

  @SerializedName("sheet")
  private JsonElement sheet;

  @SerializedName("sheet_list")
  private JsonElement sheetList;

  @SerializedName("properties")
  private JsonElement properties;

  @SerializedName("view")
  private JsonElement view;

  @SerializedName("views")
  private JsonElement views;

  @SerializedName("view_list")
  private JsonElement viewList;

  @SerializedName("field")
  private JsonElement field;

  @SerializedName("fields")
  private JsonElement fields;

  @SerializedName("field_list")
  private JsonElement fieldList;

  @SerializedName("record")
  private JsonElement record;

  @SerializedName("records")
  private JsonElement records;

  @SerializedName("record_list")
  private JsonElement recordList;

  @SerializedName("has_more")
  private Boolean hasMore;

  @SerializedName("next_cursor")
  private JsonElement nextCursor;

  public JsonElement getEffectiveSheets() {
    if (this.sheetList != null) {
      return this.sheetList;
    }
    if (this.sheet != null) {
      return this.sheet;
    }
    return this.properties;
  }

  public JsonElement getEffectiveViews() {
    if (this.views != null) {
      return this.views;
    }
    if (this.viewList != null) {
      return this.viewList;
    }
    return this.view;
  }

  public JsonElement getEffectiveFields() {
    if (this.fields != null) {
      return this.fields;
    }
    if (this.fieldList != null) {
      return this.fieldList;
    }
    return this.field;
  }

  public JsonElement getEffectiveRecords() {
    if (this.records != null) {
      return this.records;
    }
    if (this.recordList != null) {
      return this.recordList;
    }
    return this.record;
  }

  public static WxCpDocSmartSheetResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocSmartSheetResult.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
