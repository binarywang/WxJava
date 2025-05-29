package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 关播内容力数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class Ended implements Serializable {

  private static final long serialVersionUID = 576815272236922652L;

  /**
   * 曝光有效CTR（万分比）
   */
  @JsonProperty("recommend_effective_new_watch_2_uv_over_impression_uv")
  private EndedIndexItem recommendEffectiveNewWatch2UvOverImpressionUv;

  /**
   * 人均看播时长
   */
  @JsonProperty("average_watch_seconds")
  private EndedIndexItem averageWatchSeconds;

  /**
   * 评论率（万分比）
   */
  @JsonProperty("comment_uv_over_new_watch_uv")
  private EndedIndexItem commentUvOverNewWatchUv;

  /**
   * 点赞率（万分比）
   */
  @JsonProperty("like_uv_over_new_watch_uv")
  private EndedIndexItem likeUvOverNewWatchUv;

}
