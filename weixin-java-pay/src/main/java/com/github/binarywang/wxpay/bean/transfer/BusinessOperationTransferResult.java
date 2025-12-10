package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 运营工具-商家转账结果
 *
 * @author WxJava Team
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
 */
@Data
@NoArgsConstructor
public class BusinessOperationTransferResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 商户系统内部的商家单号
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 微信转账单号
   * 微信商家转账系统返回的唯一标识
   */
  @SerializedName("transfer_bill_no")
  private String transferBillNo;

  /**
   * 转账状态
   * WAIT_PAY：等待确认
   * PROCESSING：转账中
   * SUCCESS：转账成功
   * FAIL：转账失败
   * REFUND：已退款
   */
  @SerializedName("transfer_state")
  private String transferState;

  /**
   * 发起转账的时间
   * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 转账更新时间
   * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("update_time")
  private String updateTime;

  /**
   * 失败原因
   * 当transfer_state为FAIL时返回
   */
  @SerializedName("fail_reason")
  private String failReason;
}