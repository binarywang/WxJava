package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户群体下不同分类的统计数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class DataNodeSecondList implements Serializable {

  private static final long serialVersionUID = 42973481125049275L;

  /**
   * 每个分类对象都有一个 key，表示分类的名称，例如 "sex_distribution"、"age_distribution" {@link me.chanjar.weixin.channel.enums.EcProfileDataNodeKey}
   */
  @JsonProperty("key")
  private String key;

  /**
   * 进一步细分该分类下的数据
   */
  @JsonProperty("row")
  private List<DataNodeList> row;

}
