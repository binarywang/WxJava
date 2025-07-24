package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 尺码表信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SpuSizeChart implements Serializable {

  private static final long serialVersionUID = -5019617420608575610L;

  /** 是否支持尺码表 */
  @JsonProperty("enable")
  private Boolean enable;

  /** 尺码表 */
  @JsonProperty("specification_list")
  private List<SpuSizeChartItem> specificationList;
}
