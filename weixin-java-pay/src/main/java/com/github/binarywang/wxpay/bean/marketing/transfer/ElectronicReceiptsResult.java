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
public class ElectronicReceiptsResult implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：受理类型
   * 变量名：accept_type
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  电子回单受理类型：
   *     BATCH_TRANSFER：批量转账明细电子回单
   *     TRANSFER_TO_POCKET：企业付款至零钱电子回单
   *     TRANSFER_TO_BANK：企业付款至银行卡电子回单
   *  示例值：BATCH_TRANSFER
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
   *  需要电子回单的批量转账明细单所在的转账批次单号，该单号为商户申请转账时生成的商户单号。受理类型为BATCH_TRANSFER时该单号必填，否则该单号留空。
   *  示例值：GD2021011610162610BBdkkIwcu3
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：商家转账明细单号
   * 变量名：out_detail_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  该单号为商户申请转账时生成的商家转账明细单号。
   *     1.受理类型为BATCH_TRANSFER时填写商家批量转账明细单号。
   *     2. 受理类型为TRANSFER_TO_POCKET或TRANSFER_TO_BANK时填写商家转账单号。
   *  示例值：mx0911231610162610v4CNkO4HAf
   * </pre>
   */
  @SerializedName(value = "out_detail_no")
  private String outDetailNo;
  /**
   * <pre>
   * 字段名：电子回单受理单号
   * 变量名：signature_no
   * 是否必填：是
   * 类型：string[3, 256]
   * 描述：
   *  电子回单受理单号，受理单据的唯一标识
   *  示例值：1050000010509999485212020110200058820001
   * </pre>
   */
  @SerializedName(value = "signature_no")
  private String signatureNo;
  /**
   * <pre>
   * 字段名：电子回单状态
   * 变量名：signature_status
   * 是否必填：否
   * 类型：string[1, 10]
   * 描述：
   *  枚举值：
   *     ACCEPTED:已受理，电子签章已受理成功
   *     FINISHED:已完成。电子签章已处理完成
   *  示例值：ACCEPTED
   * </pre>
   */
  @SerializedName(value = "signature_status")
  private String signatureStatus;
  /**
   * <pre>
   * 字段名：电子回单文件的hash方法
   * 变量名：hash_type
   * 是否必填：否
   * 类型：string[1, 20]
   * 描述：
   *  电子回单文件的hash方法，回单状态为：FINISHED时返回
   *  例值：SHA256
   * </pre>
   */
  @SerializedName(value = "hash_type")
  private String hashType;
  /**
   * <pre>
   * 字段名：电子回单文件的hash值
   * 变量名：hash_value
   * 是否必填：否
   * 类型：string[3, 1000]
   * 描述：
   *  电子回单文件的hash值，用于下载之后验证文件的完整、正确性，回单状态为：FINISHED时返回
   *  示例值：DE731F35146A0BEFADE5DB9D1E468D96C01CA8898119C674FEE9F11F4DBE5529
   * </pre>
   */
  @SerializedName(value = "hash_value")
  private String hashValue;
  /**
   * <pre>
   * 字段名：电子回单文件的下载地址
   * 变量名：download_url
   * 是否必填：否
   * 类型：string[10, 3000]
   * 描述：
   *  电子回单文件的下载地址，回单状态为：FINISHED时返回。URL有效时长为10分钟，10分钟后需要重新去获取下载地址（但不需要走受理）
   * 示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx
   * </pre>
   */
  @SerializedName(value = "download_url")
  private String downloadUrl;
}
