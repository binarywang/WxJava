package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 文档内容数据.
 */
@Data
public class WxCpDocData extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -3853386375188518430L;

  @SerializedName("docid")
  private String docId;

  /**
   * 文档内容，保留为通用 JSON 结构以兼容块级内容格式。
   */
  @SerializedName("content")
  private JsonElement content;

  /**
   * 某些返回中使用 doc_content 命名。
   */
  @SerializedName("doc_content")
  private JsonElement docContent;

  @SerializedName("has_more")
  private Boolean hasMore;

  @SerializedName("next_cursor")
  private String nextCursor;

  public JsonElement getEffectiveContent() {
    return this.content != null ? this.content : this.docContent;
  }

  public static WxCpDocData fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocData.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
