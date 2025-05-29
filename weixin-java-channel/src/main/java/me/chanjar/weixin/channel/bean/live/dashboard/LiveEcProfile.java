package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 电商画像数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveEcProfile implements Serializable {

  private static final long serialVersionUID = 1996741772652344438L;

  /**
   * 包含不同用户群体的统计数据
   */
  @JsonProperty("profiles")
  private List<DataNodeThirdList> profiles;

  /**
   * 总体数据统计信息
   */
  @JsonProperty("totals")
  private List<DataNodeList> totals;

}
