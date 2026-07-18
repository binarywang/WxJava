package me.chanjar.weixin.aispeech.bean.knowledge;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KnowledgeMoveProgress {
  @SerializedName("task_id")
  private String taskId;
  private String status;
  private Double progress;
  private Integer total;
  private Integer processed;
  private String message;
  private String error;
}
