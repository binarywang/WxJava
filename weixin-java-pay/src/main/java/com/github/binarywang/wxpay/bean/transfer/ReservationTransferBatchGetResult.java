package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 预约转账批次单号查询接口响应结果
 * 通过预约批次单号查询批量预约商家转账批次单基本信息。
 * 文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4015901167
 * </pre>
 *
 * @author wanggang
 * created on 2025/11/28
 */
@Data
@NoArgsConstructor
public class ReservationTransferBatchGetResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 【商户号】 微信支付分配的商户号
   */
  @SerializedName("mch_id")
  private String mchId;

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
   * 【商户AppID】 商户在微信申请公众号或移动应用成功后分配的账号ID
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 【批次备注】 批次备注
   */
  @SerializedName("batch_remark")
  private String batchRemark;

  /**
   * 【转账场景ID】 商户在商户平台-产品中心-商家转账中申请的转账场景ID
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

  /**
   * 【批次状态】
   * ACCEPTED: 批次已受理
   * PROCESSING: 批次处理中
   * FINISHED: 批次处理完成
   * CLOSED: 批次已关闭
   */
  @SerializedName("batch_state")
  private String batchState;

  /**
   * 【转账总金额】 转账金额单位为"分"
   */
  @SerializedName("total_amount")
  private Integer totalAmount;

  /**
   * 【转账总笔数】 转账总笔数
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 【转账成功金额】 转账成功金额单位为"分"
   */
  @SerializedName("success_amount")
  private Integer successAmount;

  /**
   * 【转账成功笔数】 转账成功笔数
   */
  @SerializedName("success_num")
  private Integer successNum;

  /**
   * 【转账失败金额】 转账失败金额单位为"分"
   */
  @SerializedName("fail_amount")
  private Integer failAmount;

  /**
   * 【转账失败笔数】 转账失败笔数
   */
  @SerializedName("fail_num")
  private Integer failNum;

  /**
   * 【批次创建时间】 批次受理成功时返回
   * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 【批次更新时间】 批次最后更新时间
   * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("update_time")
  private String updateTime;

  /**
   * 【批次关闭原因】 批次关闭原因
   * MERCHANT_REVOCATION: 商户主动撤销
   * OVERDUE_CLOSE: 系统超时关闭
   */
  @SerializedName("close_reason")
  private String closeReason;

  /**
   * 【是否需要查询明细】
   */
  @SerializedName("need_query_detail")
  private Boolean needQueryDetail;

  /**
   * 【转账明细列表】
   */
  @SerializedName("transfer_detail_list")
  private List<TransferDetail> transferDetailList;

  /**
   * 转账明细
   */
  @Data
  @NoArgsConstructor
  public static class TransferDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 【商户明细单号】 商户系统内部区分转账批次单下不同转账明细单的唯一标识
     */
    @SerializedName("out_detail_no")
    private String outDetailNo;

    /**
     * 【微信转账单号】 微信转账单号，微信商家转账系统返回的唯一标识
     */
    @SerializedName("transfer_bill_no")
    private String transferBillNo;

    /**
     * 【明细状态】
     * PROCESSING: 转账处理中
     * SUCCESS: 转账成功
     * FAIL: 转账失败
     */
    @SerializedName("detail_state")
    private String detailState;
  }
}
