package me.chanjar.weixin.aispeech.bean.knowledge;

import lombok.Data;

@Data
public class KnowledgeManualCreateRequest {
  private String content;
  private String title;
  private String description;
  private String status;
}
