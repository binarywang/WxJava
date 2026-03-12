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
 * 编辑收集表请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFormModifyRequest implements Serializable {
  private static final long serialVersionUID = -6803886338864231128L;

  @SerializedName("oper")
  private Integer oper;

  @SerializedName("formid")
  private String formId;

  @SerializedName("form_info")
  private WxCpFormInfo formInfo;

  public static WxCpFormModifyRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormModifyRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
