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
 * 文档高级功能账号请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocAdminRequest implements Serializable {
  private static final long serialVersionUID = -358307253275446442L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("userid")
  private String userId;

  @SerializedName("open_userid")
  private String openUserId;

  @SerializedName("type")
  private Integer type;

  private transient JsonObject extra;

  public static WxCpDocAdminRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocAdminRequest.class);
  }

  public String toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", this.docId);
    if (this.userId != null) {
      jsonObject.addProperty("userid", this.userId);
    }
    if (this.openUserId != null) {
      jsonObject.addProperty("open_userid", this.openUserId);
    }
    if (this.type != null) {
      jsonObject.addProperty("type", this.type);
    }
    if (this.extra != null) {
      for (Map.Entry<String, JsonElement> entry : this.extra.entrySet()) {
        jsonObject.add(entry.getKey(), entry.getValue());
      }
    }
    return WxCpGsonBuilder.create().toJson(jsonObject);
  }

  public WxCpDocAdminRequest addExtra(String key, String value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocAdminRequest addExtra(String key, Number value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocAdminRequest addExtra(String key, Boolean value) {
    ensureExtra().addProperty(key, value);
    return this;
  }

  public WxCpDocAdminRequest addExtra(String key, JsonElement value) {
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
