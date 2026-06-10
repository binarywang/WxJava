package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.bean.notify.OriginNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayBaseNotifyV3Result;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 免确认收款授权结果通知.
 *
 * <p>微信支付会把授权确认或授权关闭结果发送到商户在发起授权时传入的
 * {@code authorization_notify_url}，商户可通过该通知保存 {@code authorization_id}
 * 并用于后续用户授权后转账。</p>
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4014512908">免确认收款授权结果通知</a>
 */
@Data
public class UserAuthorizationNotifyResult implements Serializable,
  WxPayBaseNotifyV3Result<UserAuthorizationNotifyResult.DecryptNotifyResult> {
  private static final long serialVersionUID = 1L;

  /**
   * 源数据.
   */
  private OriginNotifyResponse rawData;

  /**
   * 解密后的数据.
   */
  private UserAuthorizationNotifyResult.DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商户侧授权单号.
     */
    @SerializedName("out_authorization_no")
    private String outAuthorizationNo;

    /**
     * 商户 AppID.
     */
    @SerializedName("appid")
    private String appid;

    /**
     * 用户 OpenID.
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 用户展示昵称.
     */
    @SerializedName("user_display_name")
    private String userDisplayName;

    /**
     * 微信免确认收款授权单号.
     */
    @SerializedName("authorization_id")
    private String authorizationId;

    /**
     * 授权状态.
     *
     * <p>TAKING_EFFECT：授权生效中；CLOSED：授权已关闭。</p>
     */
    @SerializedName("state")
    private String state;

    /**
     * 用户确认授权的时间.
     */
    @SerializedName("authorize_time")
    private String authorizeTime;

    /**
     * 授权关闭原因，授权状态为 CLOSED 时返回.
     *
     * <p>CLOSE_VIA_MCH_API：商户通过 API 主动关闭；USER_CLOSE：用户主动关闭。</p>
     */
    @SerializedName("close_reason")
    private String closeReason;
  }
}
