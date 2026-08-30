package com.binarywang.wxjava.store.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 审核通过的分类和资质信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class PassCategoryInfo implements Serializable {

  private static final long serialVersionUID = 1152077957498898216L;

  /** 类目ID */
  @JsonProperty("cat_id")
  private String catId;

  /** 资质ID */
  @JsonProperty("qua_id")
  private String quaId;
}
