package me.chanjar.weixin.aispeech.bean.dialog;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PublishProgress {
  @SerializedName("end_time")
  private String endTime;
  private Integer progress;
  private Integer status;
}
