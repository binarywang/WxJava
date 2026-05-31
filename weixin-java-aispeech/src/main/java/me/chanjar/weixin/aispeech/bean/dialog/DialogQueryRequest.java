package me.chanjar.weixin.aispeech.bean.dialog;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class DialogQueryRequest {
  private String query;
  private String env;
  @SerializedName("first_priority_skills")
  private List<String> firstPrioritySkills;
  @SerializedName("second_priority_skills")
  private List<String> secondPrioritySkills;
  @SerializedName("user_name")
  private String userName;
  private String avatar;
  private String userid;
}
