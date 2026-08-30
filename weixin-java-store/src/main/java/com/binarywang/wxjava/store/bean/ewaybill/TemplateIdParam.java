package com.binarywang.wxjava.store.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板ID请求参数。
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateIdParam implements Serializable {
  private static final long serialVersionUID = -2397006631686547550L;

  @JsonProperty("template_id")
  private String templateId;
}
