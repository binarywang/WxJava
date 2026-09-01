package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信客服知识库分组。
 */
@Data
public class WxCpKfKnowledgeGroup implements Serializable {
  private static final long serialVersionUID = -170690715179803477L;

  @SerializedName("group_id")
  private String groupId;

  private String name;

  @SerializedName("is_default")
  private Integer isDefault;
}
