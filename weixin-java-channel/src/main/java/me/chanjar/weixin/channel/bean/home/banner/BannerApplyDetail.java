package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 精选展示位申请详情
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BannerApplyDetail implements Serializable {

  private static final long serialVersionUID = -4622897527243343862L;

  /** 审核状态。 1-审核中；2-审核驳回 */
  @JsonProperty("audit_state")
  private Integer auditState;

  /** 审核结果描述。audit_state为驳回时有值。 */
  @JsonProperty("audit_desc")
  private String auditDesc;

  /** 精选展示位申请明细 */
  @JsonProperty("banner")
  private BannerItem banner;
}
