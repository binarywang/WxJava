package com.binarywang.wxjava.store.bean.product.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取库存流水请求参数。
 */
@Data
@NoArgsConstructor
public class StockFlowParam implements Serializable {

  private static final long serialVersionUID = -7882480822919984178L;

  /** 内部商品 ID。 */
  @JsonProperty("product_id")
  private String productId;

  /** 内部 SKU ID。 */
  @JsonProperty("sku_id")
  private String skuId;

  /** 库存类型。 */
  @JsonProperty("stock_type")
  private Integer stockType;

  /** 达人的视频号 finder_id。 */
  @JsonProperty("finder_id")
  private String finderId;

  /** 查询开始时间，秒级时间戳。 */
  @JsonProperty("begin_time")
  private Long beginTime;

  /** 查询结束时间，秒级时间戳。 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 库存事件类型列表。 */
  @JsonProperty("op_type_list")
  private List<Integer> opTypeList;

  /** 每页数量。 */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 上次请求返回的翻页上下文。 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 库存类型 ID。 */
  @JsonProperty("stock_type_id")
  private String stockTypeId;
}
