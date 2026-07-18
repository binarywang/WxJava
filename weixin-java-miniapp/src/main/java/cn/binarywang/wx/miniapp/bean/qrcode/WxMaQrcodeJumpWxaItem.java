package cn.binarywang.wx.miniapp.bean.qrcode;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * URL Link 跳转规则中的小程序信息。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaQrcodeJumpWxaItem implements Serializable {
  private static final long serialVersionUID = -675341413130655505L;

  /**
   * 小程序 appid。
   */
  @SerializedName("appid")
  private String appId;

  /**
   * 跳转页面路径。
   */
  @SerializedName("path")
  private String path;
}

