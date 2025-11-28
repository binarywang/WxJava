package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 转账明细电子回单受理API
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/transfer-detail/electronic-receipts
 * 请求方式：POST
 * 前置条件：只支持受理最近90天内的转账明细单
 * 接口规则：https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay-1.shtml
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Data
@NoArgsConstructor
public class ElectronicReceiptsRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：受理类型
   * 变量名：accept_type
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  body电子回单受理类型：
   *     BATCH_TRANSFER：批量转账明细电子回单
   *     TRANSFER_TO_POCKET：企业付款至零钱电子回单
   *     TRANSFER_TO_BANK：企业付款至银行卡电子回单
   * 示例值：BATCH_TRANSFER
   * </pre>
   */
  @SerializedName(value = "accept_type")
  private String acceptType;

  /**
   * <pre>
   * 字段名：商家转账批次单号
   * 变量名：out_batch_no
   * 是否必填：否
   * 类型：string[5, 32]
   * 描述：
   *  body需要电子回单的批量转账明细单所在的转账批次单号，该单号为商户申请转账时生成的商户单号。受理类型为BATCH_TRANSFER时该单号必填，否则该单号留空。
   *  示例值：GD2021011610162610BBdkkIwcu3
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：商家转账明细单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  body该单号为商户申请转账时生成的商家转账明细单号。
   *             1.受理类型为BATCH_TRANSFER时填写商家批量转账明细单号。
   *             2. 受理类型为TRANSFER_TO_POCKET或TRANSFER_TO_BANK时填写商家转账单号。
   *  示例值：mx0911231610162610v4CNkO4HAf
   * </pre>
   */
  @SerializedName(value = "out_detail_no")
  private String outDetailNo;
}
