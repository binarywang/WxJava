package com.binarywang.wxjava.store.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 更新限时抢购任务响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LimitTaskUpdateResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 4429517792042527433L;

  /** 限时抢购任务 ID。 */
  @JsonProperty("task_id")
  private String taskId;

  /** 活动名称。 */
  @JsonProperty("title")
  private String title;
}
