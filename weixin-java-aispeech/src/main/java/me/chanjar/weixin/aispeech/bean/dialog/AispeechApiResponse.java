package me.chanjar.weixin.aispeech.bean.dialog;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AispeechApiResponse<T> {
  private Integer code;
  private String msg;
  @SerializedName("request_id")
  private String requestId;
  private T data;

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public String getRequestId() {
    return requestId;
  }

  public T getData() {
    return data;
  }
}
