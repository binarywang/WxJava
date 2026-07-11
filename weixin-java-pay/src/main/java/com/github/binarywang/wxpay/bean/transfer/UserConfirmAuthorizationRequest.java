package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 发起免确认收款授权请求参数.
 *
 * <p>该接口只创建免确认收款授权申请，不创建转账单。成功后返回的 {@code package_info}
 * 需要交给业务侧用于 JSAPI/APP 调起用户授权页面。</p>
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4015901167">发起免确认收款授权</a>
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class UserConfirmAuthorizationRequest implements Serializable {
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
   * 收款用户 OpenID.
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 转账场景 ID.
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

  /**
   * 用户展示名称，用于在授权详情中区分用户在商户侧的账号.
   */
  @SerializedName("user_display_name")
  private String userDisplayName;

  /**
   * 用户收款感知.
   */
  @SerializedName("user_recv_perception")
  private String userRecvPerception;

  /**
   * 授权结果通知地址.
   */
  @SerializedName("authorization_notify_url")
  private String authorizationNotifyUrl;

  /**
   * 用户端场景信息.
   */
  @SerializedName("scene_info")
  private SceneInfo sceneInfo;

  /**
   * 用户端场景信息.
   */
  @Data
  @Builder(builderMethodName = "newBuilder")
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SceneInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户终端 IP，支持 IPv4 和 IPv6.
     */
    @SerializedName("client_ip")
    private String clientIp;

    /**
     * 用户设备 ID.
     */
    @SerializedName("device_id")
    private String deviceId;

    /**
     * 用户设备类型，如 IOS、ANDROID、HARMONY、OTHER.
     */
    @SerializedName("device_type")
    private String deviceType;
  }
}
