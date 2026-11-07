package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 微信客服知识库分组列表返回结果。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpKfKnowledgeGroupListResp extends WxCpBaseResp {
  private static final long serialVersionUID = -8350717377843545855L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("has_more")
  private Integer hasMore;

  @SerializedName("group_list")
  private List<WxCpKfKnowledgeGroup> groupList;

  public static WxCpKfKnowledgeGroupListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfKnowledgeGroupListResp.class);
  }
}
