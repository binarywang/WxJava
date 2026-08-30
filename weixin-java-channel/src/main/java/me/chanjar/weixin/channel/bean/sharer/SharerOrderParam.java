package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.PageParam;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
@Deprecated
public class SharerOrderParam extends PageParam {

  private static final long serialVersionUID = 5240085870008898601L;
  /** 分享员openid */
  @JsonProperty("openid")
  private String openid;

  /** 分享场景 */
  @JsonProperty("share_scene")
  private Integer shareScene;

  /** 订单创建开始时间 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 订单创建结束时间 */
  @JsonProperty("end_time")
  private Long endTime;
}
