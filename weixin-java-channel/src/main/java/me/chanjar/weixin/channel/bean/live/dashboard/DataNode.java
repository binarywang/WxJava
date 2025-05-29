package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统计数值
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class DataNode implements Serializable {

  private static final long serialVersionUID = 3192158546911682577L;

  /**
   * 统计数值维度指标
   */
  @JsonProperty("fields")
  private Fields fields;

}
