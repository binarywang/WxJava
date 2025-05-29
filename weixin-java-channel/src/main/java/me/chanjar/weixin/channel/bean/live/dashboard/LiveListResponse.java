package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 获取直播大屏直播列表响应
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LiveListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = - 5062337147636715367L;

  /**
   * 追踪ID，报bug带
   */
  @JsonProperty("trace_id")
  private String traceId;

  /**
   * 直播列表
   */
  @JsonProperty("live_items")
  private List<LiveItem> liveItems;

  /**
   * 是否还有更多的直播
   */
  @JsonProperty("has_more")
  private Boolean hasMore;

}
