package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取直播大屏直播列表请求参数
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LiveListParam implements Serializable {

  private static final long serialVersionUID = - 8451283214646387030L;

  /**
   * 日期，格式 yyyyMMdd
   */
  @JsonProperty("ds")
  private Long ds;

}
