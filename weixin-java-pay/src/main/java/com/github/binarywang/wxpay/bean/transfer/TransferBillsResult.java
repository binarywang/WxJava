package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商家转账结果
 *
 * @author allovine
 * created on  2025/1/15
 **/
@Data
@NoArgsConstructor
public class TransferBillsResult implements Serializable {
  private static final long serialVersionUID = -2175582517588397437L;

  /**
   * 商户单号
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 微信转账单号
   */
  @SerializedName("transfer_bill_no")
  private String transferBillNo;

  /**
   * 单据创建时间
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 单据状态
   *
   * @see WxPayConstants.TransformBillState
   */
  @SerializedName("state")
  private String state;

  /**
   * 失败原因
   */
  @SerializedName("fail_reason")
  private String failReason;

  /**
   * 跳转领取页面的package信息
   */
  @SerializedName("package_info")
  private String packageInfo;
}
