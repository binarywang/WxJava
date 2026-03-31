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
 * 文档/收集表分享请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocShareRequest implements Serializable {
  private static final long serialVersionUID = 7760968921955136050L;

  @SerializedName("docid")
  private String docId;

  @SerializedName("formid")
  private String formId;

  public static WxCpDocShareRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocShareRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
