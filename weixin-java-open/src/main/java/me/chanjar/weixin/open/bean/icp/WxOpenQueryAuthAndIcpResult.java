package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.util.List;

/**
 * @author xzh
 * @Description
 * @createTime 2024/12/19 16:56
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenQueryAuthAndIcpResult extends WxOpenResult {
  private static final long serialVersionUID = 1626895037788760364L;


  /**
   * 当前任务流程状态，见下方任务流程状态枚举
   * 值	含义
   * 15	等待支付认证审核费用
   * 16	认证审核费用支付成功
   * 17	认证审核中
   * 18	认证审核驳回
   * 19	认证审核通过
   * 20	认证审核最终失败（不能再修改）
   * 21	创建备案审核单失败
   * 22	备案平台审核中
   * 23	备案平台审核驳回
   * 24	备案管局审核中
   * 25	管局审核驳回
   * 26	认证及备案完成
   */
  @SerializedName("procedure_status")
  private Integer procedureStatus;

  /**
   * 小程序后台展示的认证订单号
   */
  @SerializedName("orderid")
  private Long orderId;

  /**
   * 小程序认证审核单被驳回（procedure_status 为 18）时有效
   */
  @SerializedName("refill_reason")
  private String refillReason;
  /**
   * 小程序认证审核最终失败的原因（procedure_status 为 20）时有效
   */
  @SerializedName("fail_reason")
  private String failReason;

  /**
   * 小程序备案相关信息
   */
  @SerializedName("icp_audit")
  private IcpAudit icpAudit;

  @Data
  public static class IcpAudit {

    /**
     * 错误提示，创建备案审核单失败时返回（procedure_status 为 21）
     */
    @SerializedName("hints")
    private List<WxOpenApplyIcpFilingResult.Hint> hints;

    /**
     * 驳回原因，备案不通过时返回（procedure_status 为 23、25）
     */
    @SerializedName("audit_data")
    private List<WxOpenIcpEntranceInfoResult.AuditData> auditData;

    /**
     * 管局短信核验状态，仅当任务流程状态为 24（备案管局审核中）的时候才有效。1：等待核验中，2：核验完成，3：核验超时。
     */
    @SerializedName("sms_verify_status")
    private Integer smsVerifyStatus;
  }

}
