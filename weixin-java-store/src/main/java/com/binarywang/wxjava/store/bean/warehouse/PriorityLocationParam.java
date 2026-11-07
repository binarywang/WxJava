package com.binarywang.wxjava.store.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 带优先级的仓库区域
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PriorityLocationParam extends WarehouseLocation {

  private static final long serialVersionUID = -3087702364669180903L;

  /** 按照out_warehouse_id排序优先级从高到低 */
  @JsonProperty("priority_sort")
  private List<String> prioritySort;
}
