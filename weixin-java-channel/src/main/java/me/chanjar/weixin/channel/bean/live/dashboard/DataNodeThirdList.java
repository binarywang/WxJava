package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 不同用户群体的统计数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class DataNodeThirdList implements Serializable {

  private static final long serialVersionUID = - 7534433586440870881L;

  /**
   * 每个对象包含一个 key，表示用户群体的名称，例如 "已成交用户"、"首次购买用户"、"复购用户"
   */
  @JsonProperty("key")
  private String key;

  /**
   * 包含该用户群体下不同分类的统计数据
   */
  @JsonProperty("row")
  private List<DataNodeSecondList> row;

}
