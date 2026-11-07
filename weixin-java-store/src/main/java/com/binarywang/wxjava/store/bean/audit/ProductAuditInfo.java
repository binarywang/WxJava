package com.binarywang.wxjava.store.bean.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品审核信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ProductAuditInfo implements Serializable {

  private static final long serialVersionUID = -5264206679057480206L;

  /** 审核单id */
  @JsonProperty("audit_id")
  private String auditId;

  /** 上一次提交时间, yyyy-MM-dd HH:mm:ss */
  @JsonProperty("submit_time")
  private String submitTime;

  /** 上一次审核时间, yyyy-MM-dd HH:mm:ss */
  @JsonProperty("audit_time")
  private String auditTime;

  /** 拒绝理由，只有edit_status为3时出现 */
  @JsonProperty("reject_reason")
  private String rejectReason;

  @JsonProperty("func_type")
  private Integer funcType;
}
