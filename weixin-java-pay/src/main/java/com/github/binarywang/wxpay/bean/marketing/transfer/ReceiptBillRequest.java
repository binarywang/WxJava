package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 转账电子回单申请受理API
 * <pre>
 * 文档地址:https://pay.weixin.qq.com/doc/v3/merchant/4012716452
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Data
@NoArgsConstructor
public class ReceiptBillRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：商户转账单号
   * 变量名：out_bill_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  body商户系统内部的商户转账单号，在商户系统内部唯一。兼容旧字段out_batch_no
   *  示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_bill_no", alternate = {"out_batch_no"})
  private String outBatchNo;
}
