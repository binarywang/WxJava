package com.github.binarywang.wxpay.bean.marketing.payroll;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 微工卡批量转账API返回结果
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_8.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/transfer-batches
 * 请求方式：POST
 * </pre>
 *
 * @author binarywang
 * created on  2025/01/19
 */
@Data
@NoArgsConstructor
public class PayrollTransferBatchesResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  商户系统内部的商家批次单号
   *  示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：微信批次单号
   * 变量名：batch_id
   * 是否必填：是
   * 类型：string[1, 64]
   * 描述：
   *  微信批次单号，微信商家转账系统返回的唯一标识
   *  示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName(value = "batch_id")
  private String batchId;

  /**
   * <pre>
   * 字段名：批次状态
   * 变量名：batch_status
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  ACCEPTED:已受理，批次已受理成功，若发起批量转账的30分钟后，转账批次单仍处于该状态，可能原因是商户账户余额不足等。商户可查询账户资金流水，若该笔转账批次单的扣款已经发生，则表示批次已经进入转账中，请再次查单确认
   *  PROCESSING:转账中，已开始处理批次内的转账明细单
   *  FINISHED:已完成，批次内的所有转账明细单都已处理完成
   *  CLOSED:已关闭，可查询具体的批次关闭原因确认
   *  示例值：ACCEPTED
   * </pre>
   */
  @SerializedName(value = "batch_status")
  private String batchStatus;

  /**
   * <pre>
   * 字段名：批次类型
   * 变量名：batch_type
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  批次类型
   *  API:API方式发起
   *  WEB:WEB方式发起
   *  示例值：API
   * </pre>
   */
  @SerializedName(value = "batch_type")
  private String batchType;

  /**
   * <pre>
   * 字段名：批次名称
   * 变量名：batch_name
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  该笔批量转账的名称
   *  示例值：2019年1月深圳分部报销单
   * </pre>
   */
  @SerializedName(value = "batch_name")
  private String batchName;

  /**
   * <pre>
   * 字段名：批次备注
   * 变量名：batch_remark
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  转账说明，UTF8编码，最多允许32个字符
   *  示例值：2019年1月深圳分部报销单
   * </pre>
   */
  @SerializedName(value = "batch_remark")
  private String batchRemark;

  /**
   * <pre>
   * 字段名：批次关闭原因
   * 变量名：close_reason
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  如果批次单状态为"CLOSED"（已关闭），则有关闭原因
   *  示例值：OVERDUE_CLOSE
   * </pre>
   */
  @SerializedName(value = "close_reason")
  private String closeReason;

  /**
   * <pre>
   * 字段名：转账总金额
   * 变量名：total_amount
   * 是否必填：是
   * 类型：int64
   * 描述：
   *  转账金额单位为"分"
   *  示例值：4000000
   * </pre>
   */
  @SerializedName(value = "total_amount")
  private Long totalAmount;

  /**
   * <pre>
   * 字段名：转账总笔数
   * 变量名：total_num
   * 是否必填：是
   * 类型：int
   * 描述：
   *  一个转账批次单最多发起一千笔转账
   *  示例值：200
   * </pre>
   */
  @SerializedName(value = "total_num")
  private Integer totalNum;

  /**
   * <pre>
   * 字段名：批次创建时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  批次受理成功时返回，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE
   *  示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：批次更新时间
   * 变量名：update_time
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  批次最近一次状态变更的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE
   *  示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "update_time")
  private String updateTime;

  /**
   * <pre>
   * 字段名：转账成功金额
   * 变量名：success_amount
   * 是否必填：否
   * 类型：int64
   * 描述：
   *  转账成功的金额，单位为"分"
   *  示例值：3900000
   * </pre>
   */
  @SerializedName(value = "success_amount")
  private Long successAmount;

  /**
   * <pre>
   * 字段名：转账成功笔数
   * 变量名：success_num
   * 是否必填：否
   * 类型：int
   * 描述：
   *  转账成功的笔数
   *  示例值：199
   * </pre>
   */
  @SerializedName(value = "success_num")
  private Integer successNum;

  /**
   * <pre>
   * 字段名：转账失败金额
   * 变量名：fail_amount
   * 是否必填：否
   * 类型：int64
   * 描述：
   *  转账失败的金额，单位为"分"
   *  示例值：100000
   * </pre>
   */
  @SerializedName(value = "fail_amount")
  private Long failAmount;

  /**
   * <pre>
   * 字段名：转账失败笔数
   * 变量名：fail_num
   * 是否必填：否
   * 类型：int
   * 描述：
   *  转账失败的笔数
   *  示例值：1
   * </pre>
   */
  @SerializedName(value = "fail_num")
  private Integer failNum;
}
