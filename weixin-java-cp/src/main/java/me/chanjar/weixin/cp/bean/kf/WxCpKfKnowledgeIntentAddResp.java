package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 微信客服知识库问答新增返回结果。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpKfKnowledgeIntentAddResp extends WxCpBaseResp {
  private static final long serialVersionUID = -2693926545826175543L;

  @SerializedName("intent_id")
  private String intentId;

  public static WxCpKfKnowledgeIntentAddResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfKnowledgeIntentAddResp.class);
  }
}
