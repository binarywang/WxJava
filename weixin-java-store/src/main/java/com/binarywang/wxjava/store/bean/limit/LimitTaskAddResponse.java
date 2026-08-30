package com.binarywang.wxjava.store.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class LimitTaskAddResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -4742165348862157618L;

  /** 限时抢购任务ID 创建成功后返回 */
  @JsonProperty("task_id")
  private String taskId;

}
