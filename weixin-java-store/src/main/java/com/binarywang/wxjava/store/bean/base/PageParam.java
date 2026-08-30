package com.binarywang.wxjava.store.bean.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam implements Serializable {

  private static final long serialVersionUID = -2606033044242617845L;

  /** 页码 */
  @JsonProperty("page")
  protected Integer page;

  /** 每页订单数，上限100 */
  @JsonProperty("page_size")
  protected Integer pageSize;
}
