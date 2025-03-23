package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 质检信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class QualityInsepctInfo implements Serializable {

  private static final long serialVersionUID = 8109819414306253475L;

  /** 质检状态 */
  @JsonProperty("inspect_status")
  private Integer inspectStatus;
}
