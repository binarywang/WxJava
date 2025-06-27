package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付明细单号查询明细单API
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer_partner/chapter3_3.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/batch-id/{batch_id}/details/detail-id/{detail_id}
 * 请求方式：GET
 * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Data
@NoArgsConstructor
public class BatchDetailsRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：微信支付批次单号
   * 变量名：need_query_detail
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  path微信支付批次单号，微信商家转账系统返回的唯一标识
   *  示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName(value = "batch_id")
  private String batchId;
  /**
   * <pre>
   * 字段名：微信明细单号
   * 变量名：need_query_detail
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  path微信支付系统内部区分转账批次单下不同转账明细单的唯一标识
   *  示例值：1040000071100999991182020050700019500100
   * </pre>
   */
  @SerializedName(value = "detail_id")
  private String detailId;
}
