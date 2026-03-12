package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取收集表信息结果.
 */
@Data
public class WxCpFormInfoResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -3707731210828247900L;

  @SerializedName("form_info")
  private WxCpFormInfo formInfo;

  public static WxCpFormInfoResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormInfoResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
