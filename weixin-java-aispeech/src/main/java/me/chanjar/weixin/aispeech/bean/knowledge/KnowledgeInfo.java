package me.chanjar.weixin.aispeech.bean.knowledge;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Data;

@Data
public class KnowledgeInfo {
  private String id;
  @SerializedName("tenant_id")
  private Long tenantId;
  @SerializedName("knowledge_base_id")
  private String knowledgeBaseId;
  private String type;
  private String title;
  private String description;
  private String source;
  @SerializedName("parse_status")
  private String parseStatus;
  @SerializedName("summary_status")
  private String summaryStatus;
  @SerializedName("enable_status")
  private String enableStatus;
  private Map<String, String> metadata;
  @SerializedName("created_at")
  private String createdAt;
  @SerializedName("updated_at")
  private String updatedAt;
  private JsonObject raw;
}
