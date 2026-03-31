package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 审批申请详情响应结果
 *
 * @author element
 */
@Data
public class WxCpApprovalDetailResult implements Serializable {
  private static final long serialVersionUID = 3909779949756252918L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("info")
  private WxCpApprovalDetail info;

  /**
   * The type Wx cp approval detail.
   */
  @Data
  public static class WxCpApprovalDetail implements Serializable {
    private static final long serialVersionUID = 1353393306564207170L;

    /**
     * 审批编号
     */
    @SerializedName("sp_no")
    private String spNo;

    /**
     * 审批申请类型名称（审批模板名称）
     */
    @SerializedName("sp_name")
    private String spName;

    /**
     * 申请单状态：1-审批中；2-已通过；3-已驳回；4-已撤销；6-通过后撤销；7-已删除；10-已支付
     */
    @SerializedName("sp_status")
    private WxCpSpStatus spStatus;

    /**
     * 审批模板id。可在“获取审批申请详情”、“审批状态变化回调通知”中获得，也可在审批模板的模板编辑页面链接中获得。
     */
    @SerializedName("template_id")
    private String templateId;

    /**
     * 审批申请提交时间,Unix时间戳
     */
    @SerializedName("apply_time")
    private Long applyTime;

    /**
     * 申请人信息
     */
    @SerializedName("applyer")
    private WxCpApprovalApplier applier;

    /**
     * 审批流程信息，可能有多个审批节点
     */
    @SerializedName("sp_record")
    private WxCpApprovalRecord[] spRecords;

    /**
     * 抄送信息，可能有多个抄送节点
     */
    @SerializedName("notifyer")
    private WxCpOperator[] notifiers;

    /**
     * 审批申请数据
     */
    @SerializedName("apply_data")
    private WxCpApprovalApplyData applyData;

    /**
     * 审批申请备注信息，可能有多个备注节点
     */
    @SerializedName("comments")
    private List<WxCpApprovalComment> comments;

    /**
     * 审批单据的总金额（单位：分），当审批单包含费用相关控件时返回
     */
    @SerializedName("sum_money")
    private Long sumMoney;

  }

}
