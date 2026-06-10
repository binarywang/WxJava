package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 发起转账并完成免确认收款授权请求参数.
 *
 * <p>该接口和普通 {@link TransferBillsRequest} 一样会创建商家转账单，但额外携带
 * {@code authorization_info}，用于在用户确认收款时同时引导用户完成免确认收款授权。</p>
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4014399293">发起转账并完成免确认收款授权</a>
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class PreTransferWithAuthorizationRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 商户 AppID.
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 商户系统内部的商家单号.
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 转账场景 ID.
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

  /**
   * 收款用户 OpenID.
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 收款用户姓名.
   *
   * <p>该字段为敏感信息，提交前需要使用微信支付公钥或平台证书公钥加密。</p>
   */
  @SpecEncrypt
  @SerializedName("user_name")
  private String userName;

  /**
   * 转账金额，单位为分.
   */
  @SerializedName("transfer_amount")
  private Integer transferAmount;

  /**
   * 转账备注，用户确认收款时可见.
   */
  @SerializedName("transfer_remark")
  private String transferRemark;

  /**
   * 转账结果通知地址.
   */
  @SerializedName("notify_url")
  private String notifyUrl;

  /**
   * 用户收款感知.
   */
  @SerializedName("user_recv_perception")
  private String userRecvPerception;

  /**
   * 转账场景报备信息.
   */
  @SerializedName("transfer_scene_report_infos")
  private List<TransferSceneReportInfo> transferSceneReportInfos;

  /**
   * 免确认收款授权信息.
   */
  @SerializedName("authorization_info")
  private AuthorizationInfo authorizationInfo;

  /**
   * 出资商户号.
   */
  @SerializedName("sponsor_mchid")
  private String sponsorMchid;

  /**
   * 转账场景报备信息.
   */
  @Data
  @Builder(builderMethodName = "newBuilder")
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TransferSceneReportInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 信息类型.
     */
    @SerializedName("info_type")
    private String infoType;

    /**
     * 信息内容.
     */
    @SerializedName("info_content")
    private String infoContent;
  }

  /**
   * 免确认收款授权信息.
   */
  @Data
  @Builder(builderMethodName = "newBuilder")
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AuthorizationInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户展示名称，用于在授权详情中区分用户在商户侧的账号.
     */
    @SerializedName("user_display_name")
    private String userDisplayName;

    /**
     * 商户侧授权单号.
     */
    @SerializedName("out_authorization_no")
    private String outAuthorizationNo;

    /**
     * 免确认收款授权结果通知地址.
     */
    @SerializedName("authorization_notify_url")
    private String authorizationNotifyUrl;
  }
}
