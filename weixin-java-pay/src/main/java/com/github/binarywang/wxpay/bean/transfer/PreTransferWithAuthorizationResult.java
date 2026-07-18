package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 发起转账并完成免确认收款授权响应结果.
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4014399293">发起转账并完成免确认收款授权</a>
 */
@Data
@NoArgsConstructor
public class PreTransferWithAuthorizationResult implements Serializable {
  private static final long serialVersionUID = 1L;

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
   * 单据创建时间.
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 单据状态.
   *
   * @see WxPayConstants.TransformBillState
   */
  @SerializedName("state")
  private String state;

  /**
   * 跳转领取页面的 package 信息.
   */
  @SerializedName("package_info")
  private String packageInfo;

  /**
   * 用户展示名称.
   */
  @SerializedName("user_display_name")
  private String userDisplayName;

  /**
   * 商户侧授权单号.
   */
  @SerializedName("out_authorization_no")
  private String outAuthorizationNo;
}
