package com.github.binarywang.wxpay.bean.merchantlimitation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 子商户管控情况
 *
 * @author zhangyl
 */
@Data
public class MerchantLimitationResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 商户ID
   */
  @SerializedName("mchid")
  private String mchId;
  /**
   * 商户被管控能力列表
   */
  @SerializedName("limited_functions")
  private List<String> limitedFunctions;
  /**
   * 商户其他被管控能力描述
   */
  @SerializedName("other_limited_functions")
  private String otherLimitedFunctions;
  /**
   * 被管控原因及解脱路径列表
   */
  @SerializedName("recovery_specifications")
  private List<RecoverySpecification> recoverySpecifications;

  @Data
  @NoArgsConstructor
  public static class RecoverySpecification implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商户被该原因管控的单据号
     * <p>
     * 唯一标记本次管控动作的ID，可用来和“管控流水订阅通知”中的“业务单号”做关联
     * </p>
     */
    @SerializedName("limitation_case_id")
    private String limitationCaseId;
    /**
     * 商户被管控原因类型
     * <p>
     * 若商户被管控时会返回
     * <li> LICENSE_ABNORMAL:经营证照异常</li>
     * <li> NO_TRADE:无交易</li>
     * <li> SETTLE_ACCOUNT_ABNORMAL:结算信息异常</li>
     * <li> RISK_ABNORMAL:风险异常</li>
     * <li> OTHER:其他</li>
     * <li> INSPECT_ABNORMAL:巡检异常</li>
     * <li> INVALID_REPRESENTATIVE_INFORMATION:法定代表人/负责人资料异常</li>
     * <li> INVALID_BUSINESS_STATUS:经营状态异常</li>
     * <li> INVALID_BUSINESS_LICENSE:经营证照资料异常</li>
     * <li> INVALID_BENEFICIARY_INFORMATION:受益所有人资料异常</li>
     * </p>
     */
    @SerializedName("limitation_reason_type")
    private String limitationReasonType;
    /**
     * 商户被管控原因
     * <p>
     * 被管控的原因，若商户被管控时会返回
     * </p>
     */
    @SerializedName("limitation_reason")
    private String limitationReason;
    /**
     * 商户被管控原因描述
     * <p>
     * 在该原因下，被管控的原因描述，若商户被管控时会返回
     * </p>
     */
    @SerializedName("limitation_reason_describe")
    private String limitationReasonDescribe;
    /**
     * 商户被该原因管控的能力列表
     * <p>
     * 在该原因下，若商户以下能力被管控时会返回
     * <li> NO_TRANSACTION_AND_RECHARGE:关闭收单和充值</li>
     * <li> NO_PAYMENT:关闭付款</li>
     * <li> NO_WITHDRAWAL:关闭提现</li>
     * <li> NO_REFUND:关闭退款</li>
     * <li> NO_TRANSACTION:关闭收单</li>
     * <li> NO_PROFIT_SHARING:关闭分账分出</li>
     * <li> NO_PAYMENT_POINT_COMPLETE_ORDER:关闭支付分服务结单</li>
     * </p>
     */
    @SerializedName("relate_limitations")
    private List<String> relateLimitations;

    /**
     * 商户被该原因管控的其他能力描述
     * <p>
     * 在该原因下，若商户除了relate_limitations所罗列的被管控能力，还有其他被管控的能力时会返回（如有多项以英文逗号分隔）
     * </p>
     */
    @SerializedName("other_relate_limitations")
    private String otherRelateLimitations;

    /**
     * 商户被该原因管控的解脱路径
     * <p>
     * 在该原因下，若存在解脱路径时会返回
     * <li> IRRECOVERABLE:不可恢复</li>
     * <li> MODIFY_SUBJECT_INFORMATION:修改主体资料</li>
     * <li> MODIFY_SETTLE_ACCOUNT_INFORMATION:修改结算银行账户</li>
     * <li> VERIFY_INACTIVE_MERCHANT_IDENTITY:核实商户身份</li>
     * <li> SUBMIT_OFFLINE_BUSINESS_SCENARIO_INFORMATION:提交线下经营场景信息</li>
     * <li> SUBMIT_INFORMATION_FOR_APPEAL:提交相关信息申诉</li>
     * <li> RESOLVE_TRANSACTION_DISPUTES:解决交易纠纷</li>
     * <li> MODIFY_ADMINISTRATOR_INFORMATION:修改超级管理员</li>
     * <li> CALL_CUSTOMER_SERVICE_AT_95017:拨打微信支付客服电话95017</li>
     * <li> UPDATE_BUSINESS_SCENARIO_INFORMATION:更新经营场景信息</li>
     * <li> SUBMIT_CDD_INFORMATION:填写尽调信息</li>
     * <li> WAITING_FOR_PLATFORM_REVIEW:等待平台审核</li>
     * <li> SUBMIT_UBO_INFORMATION:补充受益所有人信息</li>
     * <li> SIGN_ANTI_FRAUD_PLEDGE_AND_VERIFY_FACE:签署反诈承诺书并刷脸核实身份</li>
     * <li> CONTACT_APPROPRIATE_AUTHORITY_FOR_CONSULTATION:联系有权机关咨询</li>
     * <li> MODIFY_ABBREVIATION_INFORMATION:修改商户简称</li>
     * </p>
     */
    @SerializedName("recover_way")
    private String recoverWay;

    /**
     * 商户被该原因管控的解脱路径参数
     * <p>
     * 若解脱路径recover_way为“填写尽调信息”、“补充受益所有人信息”，需通过提交尽调来解脱，此处会返回“尽调单号”；若解脱路径recover_way
     * 为“提交相关信息申诉”，需通过提交资料来解脱，此处会返回“商户管理记录单号”；若解脱路径recover_way为“联系有权机关咨询”，此处会返回有权机关信息
     * </p>
     */
    @SerializedName("recover_way_param")
    private String recoverWayParam;

    /**
     * 商户被该原因管控的解脱帮助链接
     * <p>
     * 在该原因下，若存在解脱帮助说明时会返回
     * </p>
     */
    @SerializedName("recover_help_url")
    private String recoverHelpUrl;

    /**
     * 处置方式
     * <p>
     * 管控处置方式类型，默认是立即管控
     * <li>LIMIT_ACTION_TYPE_IMMEDIATE_CONTROL:立即管控</li>
     * <li>LIMIT_ACTION_TYPE_DELAY_CONTROL:延迟管控</li>
     * </p>
     */
    @SerializedName("limitation_action_type")
    private String limitationActionType;

    /**
     * 预计管控开始时间
     */
    @SerializedName("limitation_start_date")
    private String limitationStartDate;

    /**
     * 商户被该原因管控的时间
     * <p>
     * 若商户被管控时会返回，延迟管控但是未到管控时间时不会返回
     * </p>
     */
    @SerializedName("limitation_date")
    private String limitationDate;

  }
}
