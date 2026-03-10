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
 * 发起商家转账API参数
 *
 * @author allovine
 * created on  2025/1/15
 **/
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class TransferBillsRequest implements Serializable {
  private static final long serialVersionUID = -2175582517588397437L;

  /**
   * 直连商户的appid
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 商户系统内部的商家单号
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 转账场景ID
   * 商户平台-产品中心-商家转账 申请
   * 佣金报酬 ID:1005
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

  /**
   * 用户在直连商户应用下的用户标示
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 收款用户姓名
   */
  @SpecEncrypt
  @SerializedName("user_name")
  private String userName;

  /**
   * 转账金额
   */
  @SerializedName("transfer_amount")
  private Integer transferAmount;

  /**
   * 转账备注
   */
  @SerializedName("transfer_remark")
  private String transferRemark;

  /**
   * 异步接收微信支付结果通知的回调地址，通知url必须为公网可访问的url，必须为https，不能携带参数
   */
  @SerializedName("notify_url")
  private String notifyUrl;

  /**
   * 用户收款感知
   */
  @SerializedName("user_recv_perception")
  private String userRecvPerception;


  /**
   * 转账场景报备信息
   */
  @SerializedName("transfer_scene_report_infos")
  private List<TransferSceneReportInfo> transferSceneReportInfos;

  /**
   * 收款授权模式
   * <pre>
   * 字段名：收款授权模式
   * 变量名：receipt_authorization_mode
   * 是否必填：否
   * 类型：string
   * 描述：
   *  控制收款方式的授权模式，可选值：
   *  - CONFIRM_RECEIPT_AUTHORIZATION：需确认收款授权模式（默认值）
   *  - NO_CONFIRM_RECEIPT_AUTHORIZATION：免确认收款授权模式（需要用户事先授权）
   *  为空时，默认为需确认收款授权模式
   * 示例值：NO_CONFIRM_RECEIPT_AUTHORIZATION
   * </pre>
   * 
   * @see com.github.binarywang.wxpay.constant.WxPayConstants.ReceiptAuthorizationMode
   */
  @SerializedName("receipt_authorization_mode")
  private String receiptAuthorizationMode;


  @Data
  @Builder(builderMethodName = "newBuilder")
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TransferSceneReportInfo {
    /**
     * 信息类型
     */
    @SerializedName("info_type")
    private String infoType;

    /**
     * 信息内容
     */
    @SerializedName("info_content")
    private String infoContent;
  }
}
