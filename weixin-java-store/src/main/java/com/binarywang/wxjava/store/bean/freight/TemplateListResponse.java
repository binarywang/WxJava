package com.binarywang.wxjava.store.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class TemplateListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 5375602442595264719L;
  /** 运费模板 id 列表 */
  @JsonProperty("template_id_list")
  private List<String> ids;
}
