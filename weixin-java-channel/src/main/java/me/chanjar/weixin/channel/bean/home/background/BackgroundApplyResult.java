package me.chanjar.weixin.channel.bean.home.background;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 背景图审核信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class BackgroundApplyResult implements Serializable {

  private static final long serialVersionUID = 3154900058221168732L;

  /** 申请编号 */
  @JsonProperty("apply_id")
  private Integer applyId;

  /** 申请状态。1审核中 2审核驳回 */
  @JsonProperty("state")
  private Integer state;

  /** 审核结果描述。state为审核驳回时有值。 */
  @JsonProperty("audit_desc")
  private String auditDesc;

  /** 图片url */
  @JsonProperty("img_url")
  private String imgUrl;

}
