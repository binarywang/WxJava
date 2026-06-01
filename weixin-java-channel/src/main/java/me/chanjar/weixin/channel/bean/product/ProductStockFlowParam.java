package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取库存流水请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductStockFlowParam implements Serializable {

  private static final long serialVersionUID = -407227347279113050L;

  /** 内部商品ID. */
  @JsonProperty("product_id")
  private String productId;

  /** 内部sku_id. */
  @JsonProperty("sku_id")
  private String skuId;

  /** 库存类型. */
  @JsonProperty("stock_type")
  private Integer stockType;

  /** 达人finder_id. */
  @JsonProperty("finder_id")
  private String finderId;

  /** 开始时间戳（秒）. */
  @JsonProperty("begin_time")
  private Long beginTime;

  /** 结束时间戳（秒）. */
  @JsonProperty("end_time")
  private Long endTime;

  /** 库存事件类型列表. */
  @JsonProperty("op_type_list")
  private List<Integer> opTypeList;

  /** 翻页上下文. */
  @JsonProperty("next_key")
  private String nextKey;
}
