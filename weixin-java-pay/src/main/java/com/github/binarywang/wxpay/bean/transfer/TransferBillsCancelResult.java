package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 商家转账到零钱撤销转账接口
 * 文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012716458
 * </pre>
 *
 * @author Nor
 * @date 2025/1/17
 */
@Data
@NoArgsConstructor
public class TransferBillsCancelResult implements Serializable {
  private static final long serialVersionUID = -4935840810530008418L;

  /**
   * 【商户单号】 商户系统内部的商家单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 【微信转账单号】 微信转账单号，微信商家转账系统返回的唯一标识
   */
  @SerializedName("transfer_bill_no")
  private String transferBillNo;

  /**
   * 【单据状态】 商家转账订单状态
   * 可选取值
   * CANCELING: 商户撤销请求受理成功，该笔转账正在撤销中
   * CANCELLED: 转账撤销完成
   */
  private String state;

  /**
   * 【最后一次单据状态变更时间】 按照使用rfc3339所定义的格式，格式为yyyy-MM-DDThh:mm:ss+TIMEZONE
   */
  @SerializedName("update_time")
  private String updateTime;
}
