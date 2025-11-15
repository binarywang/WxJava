package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 运营工具-商家转账查询请求参数
 *
 * @author WxJava Team
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOperationTransferQueryRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 商户系统内部的商家单号
   * 与transfer_bill_no二选一
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 微信转账单号
   * 与out_bill_no二选一
   */
  @SerializedName("transfer_bill_no")
  private String transferBillNo;

  /**
   * 直连商户的appid
   * 可选
   */
  @SerializedName("appid")
  private String appid;
}