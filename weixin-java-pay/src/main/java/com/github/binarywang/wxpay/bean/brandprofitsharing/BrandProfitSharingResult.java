package com.github.binarywang.wxpay.bean.brandprofitsharing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/** 分账请求或查询结果。 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandProfitSharingResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 品牌主商户号。 */
  @SerializedName("brand_mchid")
  private String brandMchId;

  /** 订单收款商户号，可为品牌主或门店商户号。 */
  @SerializedName("sub_mchid")
  private String subMchId;

  /** 微信支付订单号。 */
  @SerializedName("transaction_id")
  private String transactionId;

  /** 商户分账单号。 */
  @SerializedName("out_order_no")
  private String outOrderNo;

  /** 微信分账单号，与商户分账单号二选一。 */
  @SerializedName("order_id")
  private String orderId;

  /** 分账状态：PROCESSING 或 FINISHED。 */
  @SerializedName("status")
  private String status;

  /** 分账接收方列表。 */
  @SerializedName("receivers")
  private List<Receiver> receivers;

  /** 完结解冻金额，单位为分。 */
  @SerializedName("finish_amount")
  private Integer finishAmount;

  /** 完结原因描述。 */
  @SerializedName("finish_description")
  private String finishDescription;

  /** 接收方分账结果，FINISHED 仍需逐项检查 result。 */
  @Data
  @NoArgsConstructor
  public static class Receiver implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 接收方类型：MERCHANT_ID、PERSONAL_OPENID 或 PERSONAL_SUB_OPENID。 */
    @SerializedName("type")
    private String type;

    /** 接收方账号。 */
    @SerializedName("account")
    private String account;

    /** 金额，单位为分。 */
    @SerializedName("amount")
    private Integer amount;

    /** 分账或回退原因描述。 */
    @SerializedName("description")
    private String description;

    /** 分账或回退处理结果。 */
    @SerializedName("result")
    private String result;

    /** 完成时间，RFC3339 格式。 */
    @SerializedName("finish_time")
    private String finishTime;

    /** 失败原因。 */
    @SerializedName("fail_reason")
    private String failReason;

    /** 微信分账明细单号。 */
    @SerializedName("detail_id")
    private String detailId;
  }
}
