package me.chanjar.weixin.aispeech.bean.dialog;

import com.google.gson.JsonElement;
import java.util.List;
import lombok.Data;

@Data
public class AsyncTaskResult {
  private Integer state;
  private String msg;
  private Integer progress;
  private Long start;
  private Long end;
  private String url;
  private Integer totalCount;
  private Integer successCount;
  private Integer failCount;
  private JsonElement successSkillInfo;
  private List<SkillInfo> successSkillInfoList;

  @Data
  public static class SkillInfo {
    private Long id;
    private String name;
    private List<IntentInfo> intents;
  }

  @Data
  public static class IntentInfo {
    private Long id;
    private String name;
  }
}
