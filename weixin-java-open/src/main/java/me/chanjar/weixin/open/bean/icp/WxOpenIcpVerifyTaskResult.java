package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * @author xzh
 * @Description 人脸核身任务的状态和结果
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenIcpVerifyTaskResult extends WxOpenResult {

  private static final long serialVersionUID = 3134332406149779364L;

  /**
   * 人脸核身任务是否已完成
   */
  @SerializedName("is_finish")
  private Boolean finish;

  /**
   * 任务状态枚举：0. 未开始；1. 等待中；2. 失败；3. 成功。返回的 is_finish 字段为 true 时，face_status 才是最终状态。
   */
  @SerializedName("face_status")
  private Integer faceStatus;

  /**
   * 发起时 along_with_auth 填 true 时有效：9. 认证短信核验通过。
   */
  @SerializedName("along_with_auth_result")
  private Integer alongWithAuthResult;
}
