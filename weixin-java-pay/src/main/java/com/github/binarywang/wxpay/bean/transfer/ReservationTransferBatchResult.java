package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 批量预约商家转账响应结果
 * 文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4015901167
 * </pre>
 *
 * @author wanggang
 * created on 2025/11/28
 */
@Data
@NoArgsConstructor
public class ReservationTransferBatchResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 【商户预约批次单号】 商户系统内部的商家预约批次单号
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * 【微信预约批次单号】 微信预约批次单号，微信商家转账系统返回的唯一标识
   */
  @SerializedName("reservation_batch_no")
  private String reservationBatchNo;

  /**
   * 【批次创建时间】 批次受理成功时返回
   * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 【批次状态】
   * ACCEPTED: 批次已受理
   * PROCESSING: 批次处理中
   * FINISHED: 批次处理完成
   * CLOSED: 批次已关闭
   */
  @SerializedName("batch_state")
  private String batchState;
}
