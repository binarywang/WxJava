package me.chanjar.weixin.aispeech.bean.dialog;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class DialogResult {
  private String answer;
  @SerializedName("answer_type")
  private String answerType;
  @SerializedName("skill_name")
  private String skillName;
  @SerializedName("intent_name")
  private String intentName;
  @SerializedName("msg_id")
  private String msgId;
  private List<Option> options;
  private String status;
  private List<SlotDetail> slots;
  private JsonElement rawAnswer;

  public String getAnswer() {
    return answer;
  }

  public void setRawAnswer(JsonElement rawAnswer) {
    this.rawAnswer = rawAnswer;
  }

  @Data
  public static class Option {
    @SerializedName("ans_node_name")
    private String ansNodeName;
    private String title;
    private String answer;
    private Float confidence;
  }

  @Data
  public static class SlotDetail {
    private String name;
    private String value;
    private String norm;
  }
}
