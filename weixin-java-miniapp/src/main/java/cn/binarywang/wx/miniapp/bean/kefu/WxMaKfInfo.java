package cn.binarywang.wx.miniapp.bean.kefu;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序客服信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaKfInfo implements Serializable {
  private static final long serialVersionUID = -7916302137791763175L;

  /**
   * 客服账号.
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * 客服昵称.
   */
  @SerializedName("kf_nick")
  private String kfNick;

  /**
   * 客服密码.
   */
  @SerializedName("kf_id")
  private String kfId;

  /**
   * 客服头像.
   */
  @SerializedName("kf_headimgurl")
  private String kfHeadImgUrl;

  /**
   * 如果客服帐号已绑定了客服人员微信号，则此处显示微信号.
   */
  @SerializedName("kf_wx")
  private String kfWx;

  /**
   * 如果客服帐号尚未绑定微信号，但是已经发起了一个绑定邀请，则此处显示绑定邀请的微信号.
   */
  @SerializedName("invite_wx")
  private String inviteWx;

  /**
   * 邀请的状态，有等待确认"waiting"，被拒绝"rejected"，过期"expired".
   */
  @SerializedName("invite_expire_time")
  private Long inviteExpireTime;

  /**
   * 邀请的过期时间，为unix时间戳.
   */
  @SerializedName("invite_status")
  private String inviteStatus;

  public static WxMaKfInfo fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaKfInfo.class);
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}