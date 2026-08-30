package com.binarywang.wxjava.store.bean.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流式分页参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamPageParam implements Serializable {

  private static final long serialVersionUID = -4098060161712929196L;

  /** 每页订单数，上限100 */
  @JsonProperty("page_size")
  protected Integer pageSize;

  /** 分页参数，上一页请求返回 */
  @JsonProperty("next_key")
  protected String nextKey;
}
