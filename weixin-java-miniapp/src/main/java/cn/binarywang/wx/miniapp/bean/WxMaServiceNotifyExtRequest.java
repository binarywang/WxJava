package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 更新服务卡片扩展信息请求.
 *
 * <p>接口文档：
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/mp-message-management/subscribe-message/api_setusernotifyext.html">
 * 更新服务卡片扩展信息</a>
 *
 * @author GitHub Copilot
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaServiceNotifyExtRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 用户身份标识符.
   * <pre>
   * 参数：openid
   * 是否必填：是
   * 描述：用户身份标识符。
   *       当使用微信支付订单号作为 code 时，需要与实际支付用户一致；
   *       当通过前端获取 code 时，需要与点击 button 的用户一致。
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 卡片ID.
   * <pre>
   * 参数：notify_type
   * 是否必填：是
   * 描述：卡片ID。
   * </pre>
   */
  @SerializedName("notify_type")
  private Integer notifyType;

  /**
   * 动态更新令牌.
   * <pre>
   * 参数：notify_code
   * 是否必填：是
   * 描述：动态更新令牌。
   * </pre>
   */
  @SerializedName("notify_code")
  private String notifyCode;

  /**
   * 扩展信息.
   * <pre>
   * 参数：ext_json
   * 是否必填：是
   * 描述：扩展信息，不同卡片的定义不同。
   * </pre>
   */
  @SerializedName("ext_json")
  private String extJson;

  /**
   * 转为 JSON 字符串.
   *
   * @return JSON 字符串
   */
  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
