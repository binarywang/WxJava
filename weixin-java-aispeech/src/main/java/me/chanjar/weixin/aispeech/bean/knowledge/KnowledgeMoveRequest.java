package me.chanjar.weixin.aispeech.bean.knowledge;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class KnowledgeMoveRequest {
  @SerializedName("knowledge_ids")
  private List<String> knowledgeIds;
  @SerializedName("source_kb_id")
  private String sourceKnowledgeBaseId;
  @SerializedName("target_kb_id")
  private String targetKnowledgeBaseId;
  private String mode;
}
