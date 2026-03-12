package me.chanjar.weixin.cp.bean.oa.doc;

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
 * 获取文档数据请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocGetDataRequest implements Serializable {
  private static final long serialVersionUID = -1382929925556032978L;

  /**
   * 文档 docid.
   */
  @SerializedName("docid")
  private String docId;

  /**
   * 透传扩展参数，便于兼容文档内容查询的游标/范围等字段。
   */
  private transient JsonObject extra;

  public static WxCpDocGetDataRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocGetDataRequest.class);
  }

  public String toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", this.docId);
    if (this.extra != null) {
      for (Map.Entry<String, com.google.gson.JsonElement> entry : this.extra.entrySet()) {
        jsonObject.add(entry.getKey(), entry.getValue());
      }
    }
    return WxCpGsonBuilder.create().toJson(jsonObject);
  }
}
