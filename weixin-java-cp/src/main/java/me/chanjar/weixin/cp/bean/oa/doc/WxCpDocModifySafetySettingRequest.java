package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 修改文档安全设置请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocModifySafetySettingRequest implements Serializable {
  private static final long serialVersionUID = 8040559480117443346L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("enable_readonly_copy")
  private Boolean enableReadonlyCopy;

  @SerializedName("watermark")
  private WxCpDocAuthInfo.Watermark watermark;

  public static WxCpDocModifySafetySettingRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(
      json, WxCpDocModifySafetySettingRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
