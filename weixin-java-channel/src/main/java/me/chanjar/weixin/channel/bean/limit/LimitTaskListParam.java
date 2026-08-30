package me.chanjar.weixin.channel.bean.limit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@Deprecated
public class LimitTaskListParam extends StreamPageParam {

  private static final long serialVersionUID = -7227161890365102302L;


  /** 抢购活动状态 */
  @JsonProperty("status")
  private Integer status;

  public LimitTaskListParam() {
  }

  public LimitTaskListParam(Integer pageSize, String nextKey, Integer status) {
    this.pageSize = pageSize;
    this.nextKey = nextKey;
    this.status = status;
  }
}
