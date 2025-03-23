package me.chanjar.weixin.open.bean.icp;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * @author xzh
 * @Description 人脸核验任务结果
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenIcpCreateIcpVerifyTaskResult extends WxOpenResult {

  private static final long serialVersionUID = -8960874090439615220L;

  /**
   * 人脸核验任务id
   */
  @SerializedName("task_id")
  private String taskId;

  /**
   * 人脸核验任务url，along_with_auth 填 true 时返回。
   */
  @SerializedName("verify_url")
  private String verifyUrl;
}
