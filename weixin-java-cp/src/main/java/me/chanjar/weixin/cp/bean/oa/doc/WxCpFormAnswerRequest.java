package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取收集表答案请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFormAnswerRequest implements Serializable {
  private static final long serialVersionUID = 1895793536197315426L;

  @SerializedName("repeated_id")
  private String repeatedId;

  @SerializedName("answer_ids")
  private List<Long> answerIds;

  public static WxCpFormAnswerRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormAnswerRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
