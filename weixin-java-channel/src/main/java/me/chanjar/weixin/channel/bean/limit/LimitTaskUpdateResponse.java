package me.chanjar.weixin.channel.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 更新限时抢购任务 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LimitTaskUpdateResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 限时抢购任务ID */
  @JsonProperty("task_id")
  private String taskId;

  /** 活动名称 */
  @JsonProperty("title")
  private String title;
}
