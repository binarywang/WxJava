package me.chanjar.weixin.aispeech.bean.knowledge;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class KnowledgeListResult {
  private List<KnowledgeInfo> data;
  private Integer page;
  @SerializedName("page_size")
  private Integer pageSize;
  private Integer total;
  private Boolean success;

  public List<KnowledgeInfo> getData() {
    return data;
  }
}
