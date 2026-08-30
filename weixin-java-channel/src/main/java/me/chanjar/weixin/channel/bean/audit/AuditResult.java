package me.chanjar.weixin.channel.bean.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 审核结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class AuditResult implements Serializable {

  private static final long serialVersionUID = 1846416634865665240L;

  /** 审核状态, 0：审核中，1：审核成功，9：审核拒绝, 12:主动取消 */
  @JsonProperty("status")
  private Integer status;

  /** 如果审核拒绝，返回拒绝原因 */
  @JsonProperty("reject_reason")
  private String rejectReason;
}
