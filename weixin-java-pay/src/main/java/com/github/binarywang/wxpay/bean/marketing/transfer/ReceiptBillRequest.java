package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 转账电子回单申请受理API
 * <pre>
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer/chapter4_1.shtml
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
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  body商户系统内部的商家批次单号，在商户系统内部唯一。需要电子回单的批次单号
   *  示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;
}
