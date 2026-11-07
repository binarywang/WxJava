package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 品牌信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.brand.BrandInfo}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class BrandInfo extends Brand {

  private static final long serialVersionUID = 5464505958132626159L;

  /** 申请单状态 1审核中 2审核失败 3已生效 4已撤回 5即将过期(不影响商品售卖) 6已过期 */
  @JsonProperty("status")
  private Integer status;

  /** 创建时间 */
  @JsonProperty("create_time")
  private Long createTime;

  /** 更新时间 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 审核结果 */
  @JsonProperty("audit_result")
  private AuditResult auditResult;

  /** 审核结果 */
  @Data
  @NoArgsConstructor
  public static class AuditResult implements Serializable {

    private static final long serialVersionUID = 3936802571381636820L;
    /** 提审的审核单ID */
    @JsonProperty("audit_id")
    private String auditId;

    /** 审核不通过的原因, 审核成功不返回 */
    @JsonProperty("reject_reason")
    private String rejectReason;
  }

}
