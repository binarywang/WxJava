package cn.binarywang.wx.miniapp.bean.qrcode;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * URL Link 二维码快速跳转规则。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaQrcodeJumpRule implements Serializable {
  private static final long serialVersionUID = -3450269467817402123L;

  /**
   * 跳转链接规则前缀，最多 32 个字符。
   */
  @SerializedName("prefix")
  private String prefix;

  /**
   * 是否支持子路径匹配。
   */
  @SerializedName("permit_sub_rule")
  private Boolean permitSubRule;

  /**
   * 跳转版本，1：正式版；2：测试版；3：体验版。
   */
  @SerializedName("open_version")
  private Integer openVersion;

  /**
   * 正式版跳转页面。
   */
  @SerializedName("path")
  private String path;

  /**
   * 测试版/体验版可跳转小程序信息。
   */
  @SerializedName("debug_wxa_info")
  private List<WxMaQrcodeJumpWxaItem> debugWxaInfo;

  /**
   * 二维码规则是否失效。
   */
  @SerializedName("is_expire")
  private Boolean isExpire;
}

