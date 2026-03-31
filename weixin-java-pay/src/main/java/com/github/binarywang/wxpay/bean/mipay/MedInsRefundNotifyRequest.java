package com.github.binarywang.wxpay.bean.mipay;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 医保退款通知请求
 * <p>
 * 从业机构调用该接口向微信医保后台通知医保订单的退款成功结果
 * 文档地址：https://pay.weixin.qq.com/doc/v3/partner/4012166534
 * @author xgl
 * @date 2025/12/20
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class MedInsRefundNotifyRequest {

  /**
   * <pre>
   * 字段名：医疗机构的商户号
   * 变量名：sub_mchid
   * 必填：是
   * 类型：string(32)
   * 描述：医疗机构的商户号
   * </pre>
   */
  @SerializedName("sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：医保退款的总金额
   * 变量名：med_refund_total_fee
   * 必填：是
   * 类型：integer
   * 描述：单位分，医保退款的总金额。
   * </pre>
   */
  @SerializedName("med_refund_total_fee")
  private Integer medRefundTotalFee;

  /**
   * <pre>
   * 字段名：医保统筹退款金额
   * 变量名：med_refund_gov_fee
   * 必填：是
   * 类型：integer
   * 描述：单位分，医保统筹退款金额。
   * </pre>
   */
  @SerializedName("med_refund_gov_fee")
  private Integer medRefundGovFee;

  /**
   * <pre>
   * 字段名：医保个账退款金额
   * 变量名：med_refund_self_fee
   * 必填：是
   * 类型：integer
   * 描述：单位分，医保个账退款金额。
   * </pre>
   */
  @SerializedName("med_refund_self_fee")
  private Integer medRefundSelfFee;

  /**
   * <pre>
   * 字段名：医保其他退款金额
   * 变量名：med_refund_other_fee
   * 必填：是
   * 类型：integer
   * 描述：单位分，医保其他退款金额。
   * </pre>
   */
  @SerializedName("med_refund_other_fee")
  private Integer medRefundOtherFee;

  /**
   * <pre>
   * 字段名：医保退款成功时间
   * 变量名：refund_time
   * 必填：是
   * 类型：string(64)
   * 描述：遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE。
   * </pre>
   */
  @SerializedName("refund_time")
  private String refundTime;

  /**
   * <pre>
   * 字段名：从业机构\服务商退款单号
   * 变量名：out_refund_no
   * 必填：是
   * 类型：string(64)
   * 描述：有自费单时，从业机构\服务商应填与自费退款申请处一致的out_refund_no。否则从业机构透传医疗机构退款单号即可。
   * </pre>
   */
  @SerializedName("out_refund_no")
  private String outRefundNo;
}
