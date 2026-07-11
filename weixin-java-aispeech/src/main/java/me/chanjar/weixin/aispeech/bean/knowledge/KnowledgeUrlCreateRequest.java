package me.chanjar.weixin.aispeech.bean.knowledge;

import lombok.Data;

@Data
public class KnowledgeUrlCreateRequest {
  private String url;
  private String title;
  private String description;
}
