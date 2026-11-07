package com.binarywang.wxjava.store.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LimitTaskListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 3604657299385130217L;


  /** 限时抢购任务 */
  @JsonProperty("limited_discount_tasks")
  private List<LimitTaskInfo> tasks;

  /** 本次翻页的上下文，用于请求下一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 商品总数 */
  @JsonProperty("total_num")
  private Integer totalNum;
}
