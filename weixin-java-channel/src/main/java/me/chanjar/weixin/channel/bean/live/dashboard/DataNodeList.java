package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类下的数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class DataNodeList implements Serializable {

  private static final long serialVersionUID = - 497502210938812386L;

  /**
   * 细分类别的名称，如 "女"、"30-39岁"、"天津市"
   */
  @JsonProperty("key")
  private String key;

  /**
   * 包含具体的统计数值
   */
  @JsonProperty("row")
  private DataNode row;

}
