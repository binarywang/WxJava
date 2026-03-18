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
 * 编辑文档内容请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocModifyRequest implements Serializable {
  private static final long serialVersionUID = -1239410717176267110L;

  /**
   * 文档 docid.
   */
  @SerializedName("docid")
  private String docId;

  /**
   * 编辑动作集合或内容块，保留为通用 JSON 结构以兼容文档接口后续扩展。
   */
  @SerializedName("requests")
  private JsonElement requests;

  /**
   * 透传扩展参数。
   */
  private transient JsonObject extra;

  public static WxCpDocModifyRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocModifyRequest.class);
  }

  public String toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", this.docId);
    if (this.requests != null) {
      jsonObject.add("requests", this.requests);
    }
    if (this.extra != null) {
      for (Map.Entry<String, JsonElement> entry : this.extra.entrySet()) {
        jsonObject.add(entry.getKey(), entry.getValue());
      }
    }
    return WxCpGsonBuilder.create().toJson(jsonObject);
  }

  public WxCpDocModifyRequest addRequest(JsonObject request) {
    JsonArray requestArray = this.requests != null && this.requests.isJsonArray()
      ? this.requests.getAsJsonArray()
      : new JsonArray();
    requestArray.add(request);
    this.requests = requestArray;
    return this;
  }

  public WxCpDocModifyRequest addExtra(String key, String value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocModifyRequest addExtra(String key, Number value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocModifyRequest addExtra(String key, Boolean value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocModifyRequest addExtra(String key, JsonElement value) {
    ensureExtra().add(key, value);
    return this;
  }

  private JsonObject ensureExtra() {
    if (this.extra == null) {
      this.extra = new JsonObject();
    }
    return this.extra;
  }
}
