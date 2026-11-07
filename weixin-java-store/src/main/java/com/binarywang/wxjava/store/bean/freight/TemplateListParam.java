package com.binarywang.wxjava.store.bean.freight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.OffsetParam;

/**
 * 运费模板 列表 请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class TemplateListParam extends OffsetParam {

  private static final long serialVersionUID = -6716154891499581562L;

  public TemplateListParam() {
  }

  public TemplateListParam(Integer offset, Integer limit) {
    super(offset, limit);
  }
}
