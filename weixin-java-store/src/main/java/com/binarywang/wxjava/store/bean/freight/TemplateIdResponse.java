package com.binarywang.wxjava.store.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 运费模板 列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TemplateIdResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 5179651364165620640L;
  /** 运费模板id */
  @JsonProperty("template_id")
  private String templateId;

}
