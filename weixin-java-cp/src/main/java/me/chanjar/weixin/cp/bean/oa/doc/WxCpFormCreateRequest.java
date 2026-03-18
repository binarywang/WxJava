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
 * 创建收集表请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFormCreateRequest implements Serializable {
  private static final long serialVersionUID = -6170244024774750077L;

  @SerializedName("spaceid")
  private String spaceId;

  @SerializedName("fatherid")
  private String fatherId;

  @SerializedName("form_info")
  private WxCpFormInfo formInfo;

  public static WxCpFormCreateRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormCreateRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
