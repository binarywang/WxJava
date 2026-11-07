package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 微信客服知识库问答列表返回结果。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpKfKnowledgeIntentListResp extends WxCpBaseResp {
  private static final long serialVersionUID = -724770244623284115L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("has_more")
  private Integer hasMore;

  @SerializedName("intent_list")
  private List<WxCpKfKnowledgeIntent> intentList;

  public static WxCpKfKnowledgeIntentListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfKnowledgeIntentListResp.class);
  }
}
