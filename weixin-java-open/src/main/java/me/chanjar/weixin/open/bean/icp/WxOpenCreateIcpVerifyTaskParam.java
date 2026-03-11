package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 痴货
 * @Description 发起小程序管理员人脸核身
 * @createTime 2025/06/21 00:20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxOpenCreateIcpVerifyTaskParam {

  /**
   * 小程序认证及备案二合一场景，填 true，否则为小程序备案场景。默认值为 false
   */
  @SerializedName("along_with_auth")
  private Boolean alongWithAuth;

}
