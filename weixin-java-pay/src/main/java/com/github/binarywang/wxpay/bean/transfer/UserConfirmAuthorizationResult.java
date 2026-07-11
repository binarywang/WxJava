package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 免确认收款授权响应结果.
 *
 * <p>发起授权、查询授权和解除授权接口返回的都是同一类授权实体，各接口返回字段会略有差异。</p>
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4015901167">发起免确认收款授权</a>
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4014399423">商户单号查询授权结果</a>
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4015653811">解除免确认收款授权</a>
 */
@Data
@NoArgsConstructor
public class UserConfirmAuthorizationResult implements Serializable {
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
   * 用户展示名称.
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
   * <p>WAIT_USER_CONFIRM：待用户确认；TAKING_EFFECT：授权生效中；CLOSED：授权已关闭。</p>
   */
  @SerializedName("state")
  private String state;

  /**
   * 用户确认授权的时间.
   */
  @SerializedName("authorize_time")
  private String authorizeTime;

  /**
   * 授权关闭信息.
   */
  @SerializedName("close_info")
  private CloseInfo closeInfo;

  /**
   * 转账场景 ID.
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

  /**
   * 用户收款感知.
   */
  @SerializedName("user_recv_perception")
  private String userRecvPerception;

  /**
   * 单据创建时间.
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 跳转授权页面的 package 信息.
   */
  @SerializedName("package_info")
  private String packageInfo;

  /**
   * 授权关闭信息.
   */
  @Data
  @NoArgsConstructor
  public static class CloseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 授权关闭时间.
     */
    @SerializedName("close_time")
    private String closeTime;

    /**
     * 授权关闭原因.
     *
     * <p>CLOSE_VIA_MCH_API：商户通过 API 主动关闭；USER_CLOSE：用户主动关闭；
     * USER_OVERDUE_UNCONFIRMED：用户超时未确认；TRANSFER_RISK：转账风险；
     * USER_ACCOUNT_ABNORMAL：用户账号异常。</p>
     */
    @SerializedName("close_reason")
    private String closeReason;
  }
}
