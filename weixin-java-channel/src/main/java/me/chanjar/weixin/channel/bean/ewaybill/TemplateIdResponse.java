package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 面单模板ID响应。
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TemplateIdResponse extends AbstractEwaybillResponse {
  private static final long serialVersionUID = -6756111662032438585L;

  @JsonProperty("template_id")
  private String templateId;
}
