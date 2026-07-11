package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户授权后转账响应结果.
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4014399371">用户授权后转账</a>
 */
@Data
@NoArgsConstructor
public class TransferBillsAfterAuthorizationResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 商户号.
   */
  @SerializedName("mch_id")
  private String mchId;

  /**
   * 商户单号.
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 微信转账单号.
   */
  @SerializedName("transfer_bill_no")
  private String transferBillNo;

  /**
   * 商户 AppID.
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 单据状态.
   *
   * @see WxPayConstants.TransformBillState
   */
  @SerializedName("state")
  private String state;

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
   * 失败原因.
   */
  @SerializedName("fail_reason")
  private String failReason;

  /**
   * 收款用户 OpenID.
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 收款用户姓名.
   */
  @SerializedName("user_name")
  private String userName;

  /**
   * 单据创建时间.
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 最后一次状态变更时间.
   */
  @SerializedName("update_time")
  private String updateTime;
}
