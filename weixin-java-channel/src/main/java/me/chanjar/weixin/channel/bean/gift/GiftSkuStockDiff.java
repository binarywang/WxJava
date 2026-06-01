package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 赠品 SKU 库存差值信息（更新时使用）
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftSkuStockDiff implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 库存修改类型。1: 增加；2: 减少 */
  @JsonProperty("diff_type")
  private Integer diffType;

  /** 增加或减少的库存值 */
  @JsonProperty("num")
  private Integer num;
}
