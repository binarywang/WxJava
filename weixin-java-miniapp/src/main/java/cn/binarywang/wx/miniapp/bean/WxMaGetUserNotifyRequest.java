package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询服务卡片状态请求.
 *
 * <p>接口文档：
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/mp-message-management/subscribe-message/api_getusernotify.html">
 * 查询服务卡片状态</a>
 *
 * @author GitHub Copilot
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaGetUserNotifyRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 用户身份标识符.
   * <pre>
   * 参数：openid
   * 是否必填：是
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 动态更新令牌.
   * <pre>
   * 参数：notify_code
   * 是否必填：是
   * </pre>
   */
  @SerializedName("notify_code")
  private String notifyCode;

  /**
   * 卡片ID.
   * <pre>
   * 参数：notify_type
   * 是否必填：是
   * </pre>
   */
  @SerializedName("notify_type")
  private Integer notifyType;

  /**
   * 转为 JSON 字符串.
   *
   * @return JSON 字符串
   */
  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
