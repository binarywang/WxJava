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
 * 用户授权后转账请求参数.
 *
 * <p>该接口用于给已经完成免确认收款授权的用户发起转账。请求中不再传 openid，
 * 而是通过微信免确认收款授权单号或商户侧授权单号定位已授权用户。</p>
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4014399371">用户授权后转账</a>
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class TransferBillsAfterAuthorizationRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 商户 AppID.
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 商户系统内部的商家转账单号.
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

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
   * 转账备注.
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
   * 转账场景 ID.
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

  /**
   * 转账场景报备信息.
   */
  @SerializedName("transfer_scene_report_infos")
  private List<TransferSceneReportInfo> transferSceneReportInfos;

  /**
   * 微信免确认收款授权单号，通常可从授权成功回调或授权查询接口获取.
   */
  @SerializedName("authorization_id")
  private String authorizationId;

  /**
   * 出资商户号.
   */
  @SerializedName("sponsor_mchid")
  private String sponsorMchid;

  /**
   * 商户侧授权单号，对应发起授权时传入的 out_authorization_no.
   */
  @SerializedName("out_authorization_no")
  private String outAuthorizationNo;

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
}
