package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.bean.notify.OriginNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayBaseNotifyV3Result;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  <pre>
 *    商家转账到零钱接口将转账结果通知用户
 *    文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012716434
 *  </pre>
 */
@Data
public class TransferBillsNotifyResult implements Serializable, WxPayBaseNotifyV3Result<TransferBillsNotifyResult.DecryptNotifyResult> {
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private TransferBillsNotifyResult.DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    /**
     * 商户号
     */
    @SerializedName(value = "mch_id")
    private String mchId;
    /**
     * 商家批次单号
     */
    @SerializedName(value = "out_bill_no")
    private String outBillNo;
    /**
     * 微信批次单号
     */
    @SerializedName(value = "transfer_bill_no")
    private String transferBillNo;
    /**
     * 批次状态
     */
    @SerializedName(value = "state")
    private String state;
    /**
     * 转账金额
     */
    @SerializedName(value = "transfer_amount")
    private Integer transferAmount;

    /**
     * 批次状态
     */
    @SerializedName(value = "openid")
    private String openid;

    /**
     * 单据创建时间
     */
    @SerializedName(value = "create_time")
    private String createTime;
    /**
     * 最后一次状态变更时间
     */
    @SerializedName(value = "update_time")
    private String updateTime;
    /**
     * 错误原因
     */
    @SerializedName(value = "fail_reason")
    private String failReason;
  }
}
