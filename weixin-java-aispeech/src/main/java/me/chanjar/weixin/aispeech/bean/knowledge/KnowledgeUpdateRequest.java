package me.chanjar.weixin.aispeech.bean.knowledge;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KnowledgeUpdateRequest {
  private String title;
  private String description;
  @SerializedName("enable_status")
  private String enableStatus;
}
