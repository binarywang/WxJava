package me.chanjar.weixin.aispeech.bean.knowledge;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KnowledgeTagRequest {
  private String name;
  private String color;
  @SerializedName("sort_order")
  private Integer sortOrder;
}
