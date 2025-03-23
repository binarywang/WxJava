package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.chanjar.weixin.open.bean.auth.MaAuthSubmitParamAuthData;
import org.jetbrains.annotations.NotNull;

/**
 * @author xzh
 * @Description
 * @createTime 2024/12/19 16:42
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WxOpenSubmitAuthAndIcpParam extends WxOpenApplyIcpFilingParam {
  private static final long serialVersionUID = -1302523168779484802L;

  /**
   * 认证信息
   */
  @NotNull
  @SerializedName("auth_data")
  private MaAuthSubmitParamAuthData authData;
}
