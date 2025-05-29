package com.github.binarywang.wxpay.bean.applyment;

import com.github.binarywang.wxpay.bean.applyment.enums.ApplymentStateEnum;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 查询申请单状态返回对象信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplymentStateQueryResult implements Serializable {
  private static final long serialVersionUID = 6539090917423486409L;
  /**
   * 业务申请编号
   */
  @SerializedName("business_code")
  private String businessCode;
  /**
   * 微信支付申请单号
   */
  @SerializedName("applyment_id")
  private String applymentId;
  /**
   * 特约商户号
   */
  @SerializedName("sub_mchid")
  private String subMchid;
  /**
   * 超级管理员签约链接
   */
  @SerializedName("sign_url")
  private String signUrl;

  /**
   * 申请单状态
   */
  @SerializedName("applyment_state")
  private ApplymentStateEnum applymentState;
  /**
   * 申请状态描述
   */
  @SerializedName("applyment_state_msg")
  private String applymentStateMsg;
  /**
   * 驳回原因详情
   */
  @SerializedName("audit_detail")
  private List<AuditDetail> auditDetail;

  /**
   * 驳回原因详情
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class AuditDetail implements Serializable {
    private static final long serialVersionUID = 8006953382311911508L;

    /**
     * 字段名
     */
    @SerializedName("field")
    private String field;
    /**
     * 字段名称
     */
    @SerializedName("field_name")
    private String fieldName;
    /**
     * 驳回原因
     */
    @SerializedName("reject_reason")
    private String rejectReason;

  }
}
