package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 创建收集表结果.
 */
@Data
public class WxCpFormCreateResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 7996404138664773240L;

  @SerializedName("formid")
  private String formId;

  public static WxCpFormCreateResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormCreateResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
