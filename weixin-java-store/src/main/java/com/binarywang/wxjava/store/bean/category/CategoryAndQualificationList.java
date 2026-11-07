package com.binarywang.wxjava.store.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类资质响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CategoryAndQualificationList implements Serializable {

  private static final long serialVersionUID = 4245906598437404655L;

  /** 分类列表 */
  @JsonProperty("cat_and_qua")
  private List<CategoryQualification> list;
}
