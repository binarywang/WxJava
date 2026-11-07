package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 微信客服知识库分组新增返回结果。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpKfKnowledgeGroupAddResp extends WxCpBaseResp {
  private static final long serialVersionUID = 232872665454024387L;

  @SerializedName("group_id")
  private String groupId;

  public static WxCpKfKnowledgeGroupAddResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfKnowledgeGroupAddResp.class);
  }
}
