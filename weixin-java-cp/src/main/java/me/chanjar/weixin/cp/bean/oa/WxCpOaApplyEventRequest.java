package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.oa.applydata.ApplyDataContent;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 提交审批申请 请求对象类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-07-18
 */
@Data
@Accessors(chain = true)
public class WxCpOaApplyEventRequest implements Serializable {
  private static final long serialVersionUID = 3362660678938569341L;

  /**
   * 申请人userid，此审批申请将以此员工身份提交，申请人需在应用可见范围内
   */
  @SerializedName("creator_userid")
  private String creatorUserId;

  /**
   * 模板id。可在“获取审批申请详情”、“审批状态变化回调通知”中获得，也可在审批模板的模板编辑页面链接中获得。暂不支持通过接口提交[打卡补卡][调班]模板审批单。
   */
  @SerializedName("template_id")
  private String templateId;

  /**
   * 审批人模式：0-通过接口指定审批人、抄送人（此时approver、notifyer等参数可用）; 1-使用此模板在管理后台设置的审批流程，支持条件审批。默认为0
   */
  @SerializedName("use_template_approver")
  private Integer useTemplateApprover;

  /**
   * 提单者提单部门id，不填默认为主部门
   */
  @SerializedName("choose_department")
  private Integer chooseDepartment;

  /**
   * 审批流程信息（新版流程列表），用于指定审批申请的审批流程，支持单人审批、多人会签、多人或签，可能有多个审批节点，仅use_template_approver为0时生效。
   */
  @SerializedName("process")
  private Process process;

  /**
   * 审批流程信息（旧版），用于指定审批申请的审批流程，支持单人审批、多人会签、多人或签，可能有多个审批节点，仅use_template_approver为0时生效。
   */
  @SerializedName("approver")
  private List<Approver> approvers;

  /**
   * 抄送人节点userid列表，仅use_template_approver为0时生效。
   */
  @SerializedName("notifyer")
  private String[] notifiers;

  /**
   * 抄送方式：1-提单时抄送（默认值）； 2-单据通过后抄送；3-提单和单据通过后抄送。仅use_template_approver为0时生效。
   */
  @SerializedName("notify_type")
  private Integer notifyType;

  /**
   * 审批申请数据，可定义审批申请中各个控件的值，其中必填项必须有值，选填项可为空，数据结构同“获取审批申请详情”接口返回值中同名参数“apply_data”
   */
  @SerializedName("apply_data")
  private ApplyData applyData;

  /**
   * 摘要信息，用于显示在审批通知卡片、审批列表的摘要信息，最多3行
   */
  @SerializedName("summary_list")
  private List<SummaryInfo> summaryList;

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The type Approver.
   */
  @Data
  @Accessors(chain = true)
  public static class Approver implements Serializable {
    private static final long serialVersionUID = 7625206971546930988L;

    /**
     * 节点审批方式：1-或签；2-会签，仅在节点为多人审批时有效
     */
    private Integer attr;

    /**
     * 审批节点审批人userid列表，若为多人会签、多人或签，需填写每个人的userid
     */
    @SerializedName("userid")
    private String[] userIds;
  }

  /**
   * The type Apply data.
   */
  @Data
  @Accessors(chain = true)
  public static class ApplyData implements Serializable {
    private static final long serialVersionUID = -2462732405265306981L;

    /**
     * 审批申请数据，可定义审批申请中各个控件的值，其中必填项必须有值，选填项可为空，
     * 数据结构同“获取审批申请详情”接口返回值中同名参数“apply_data”
     */
    @SerializedName("contents")
    private List<ApplyDataContent> contents;
  }

  /**
   * 审批流程信息（新版）.
   */
  @Data
  @Accessors(chain = true)
  public static class Process implements Serializable {
    private static final long serialVersionUID = 4758206091546930988L;

    /**
     * 审批流程节点列表，当use_template_approver为0时必填
     */
    @SerializedName("node_list")
    private List<ProcessNode> nodeList;
  }

  /**
   * 审批流程节点.
   */
  @Data
  @Accessors(chain = true)
  public static class ProcessNode implements Serializable {
    private static final long serialVersionUID = 1758206091546930988L;

    /**
     * 节点类型：1-审批人，2-抄送人，3-抄送人
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 多人审批方式：1-全签，2-或签，3-依次审批
     */
    @SerializedName("apv_rel")
    private Integer apvRel;

    /**
     * 审批节点审批人userid列表，若为多人会签、多人或签，需填写每个人的userid
     */
    @SerializedName("userid")
    private String[] userIds;
  }

}
