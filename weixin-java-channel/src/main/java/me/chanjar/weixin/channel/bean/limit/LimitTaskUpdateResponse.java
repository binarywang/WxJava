package me.chanjar.weixin.channel.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 更新限时抢购任务响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LimitTaskUpdateResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4429517792042527433L;

  /** 限时抢购任务 ID。 */
  @JsonProperty("task_id")
  private String taskId;

  /** 活动名称。 */
  @JsonProperty("title")
  private String title;
}
