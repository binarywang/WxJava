package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 电商商品数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class SingleLiveEcSpuDataPageV2 implements Serializable {

  private static final long serialVersionUID = - 761977668198342583L;

  /**
   * 商品明细数据列表
   */
  @JsonProperty("spu_data_list")
  private List<SpuData> spuDataList;

  /**
   * spu_data_list 的总长度
   */
  @JsonProperty("total_cnt")
  private Integer totalCnt;

  /**
   * 数据版本（无实际意义）
   */
  @JsonProperty("data_key")
  private List<String> dataKey;

}
